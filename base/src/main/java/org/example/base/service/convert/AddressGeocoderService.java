package org.example.base.service.convert;

import org.example.base.dto.NominationDto;
import org.example.base.dto.SimpleCoordination;
import org.example.base.entity.GeoCache;
import org.example.base.enums.Type;
import org.example.base.exceptions.NotFoundException;
import org.example.base.exceptions.TransformationException;
import org.example.base.repository.GeoCasheRepository;
import org.example.base.service.WebSearcher;
import org.example.base.util.HasheMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.example.base.mappers.ToNominationMapper.mapToNominationDto;

@Service
public class AddressGeocoderService implements IConverter {

    private final Logger log = LoggerFactory.getLogger(AddressGeocoderService.class);

    private final HasheMaker hasheMaker;
    private final GeoCasheRepository repository;
    private final WebSearcher webSearcher;
    private final ObjectMapper objectMapper;

    public AddressGeocoderService(GeoCasheRepository repository, HasheMaker hasheMaker, WebSearcher webSearcher,
                                  ObjectMapper objectMapper) {
        this.repository = repository;
        this.hasheMaker = hasheMaker;
        this.webSearcher = webSearcher;
        this.objectMapper = objectMapper;
    }

    @Override
    public JsonNode convert(JsonNode data) {
        Optional<String> oHash = hasheMaker.createHash(data);
        Optional<GeoCache> oGeoCashe = Optional.empty();
        if (oHash.isPresent()) {
            oGeoCashe = repository.getByAddressHash(oHash.get());
        }
        if (oGeoCashe.isPresent()) {
            log.debug("Успешно нашли кеш внутри базы данных!");

            return oGeoCashe.get().getCoordinates();
        }

        log.debug("В базе данных не удалось найти сущность по хешу, идём в интернет!");
        JsonNode coordinates = null;
        try {
            NominationDto answer = mapToNominationDto(webSearcher.searchByAddress(data));
            coordinates = objectMapper.valueToTree(new SimpleCoordination(answer.getLat(), answer.getLon()));

            log.debug("Успешно нашли кеш по интернету - сохраним его потомкам.");
            saveDataLikeCache(data, oHash, coordinates);

            return coordinates;
        } catch (NotFoundException e) {
            log.error(e.getMessage());

            throw new TransformationException("При запросе информации в сети произошла ошибка: " + e.getMessage());
        } catch (DataAccessException e) {
            log.warn("Сервис успешно нашёл информацию по сети, но не смог сохранить её в базу. Причина: {}",
                     e.getMessage());

            if (coordinates != null) {
                return coordinates;
            }

            throw new TransformationException("Сервис вернул ответ, но он не содержит координат " + e.getMessage());
        }
    }

    private void saveDataLikeCache(JsonNode data, Optional<String> oHash, JsonNode coordinates) {
        GeoCache geoCache = new GeoCache();

        geoCache.setAddressHash(oHash.orElse(""));
        geoCache.setAddress(data);

        geoCache.setCoordinates(coordinates);
        geoCache.setCoordinatesHash(hasheMaker.createHash(coordinates).orElse(""));

        repository.save(geoCache);
    }

    @Override
    public Type type() {
        return Type.ADDRESS;
    }
}
