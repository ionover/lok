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

import java.time.LocalDateTime;
import java.util.Optional;

import static org.example.base.mappers.ToNominationMapper.mapToNominationDto;
import static org.example.base.util.JsonConverter.toJsonNode;

@Service
public class AddressGeocoderService implements IConverter {

    private final Logger log = LoggerFactory.getLogger(AddressGeocoderService.class);

    private final HasheMaker hasheMaker;
    private final GeoCasheRepository repository;
    private final WebSearcher webSearcher;

    public AddressGeocoderService(GeoCasheRepository repository,
                                  HasheMaker hasheMaker,
                                  WebSearcher webSearcher) {
        this.repository = repository;
        this.hasheMaker = hasheMaker;
        this.webSearcher = webSearcher;
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

            return toJsonNode(oGeoCashe.get().getCoordinates());
        }

        log.debug("В базе данных не удалось найти сущность по хешу, идём в интернет!");
        SimpleCoordination coordinates = new SimpleCoordination();
        try {
            NominationDto answer = mapToNominationDto(webSearcher.searchByAddress(data));
            coordinates = new SimpleCoordination(answer.getLon(), answer.getLat());

            log.debug("Успешно нашли кеш по интернету - сохраним его потомкам.");
            saveAddressAndCoordinates(data, oHash, coordinates);

            return toJsonNode(coordinates);
        } catch (NotFoundException e) {
            log.error(e.getMessage());

            throw new TransformationException("При запросе информации в сети произошла ошибка: " + e.getMessage());
        } catch (DataAccessException e) {
            log.warn("Сервис успешно нашёл информацию по сети, но не смог сохранить её в базу. Причина: {}",
                     e.getMessage());

            if (!coordinates.getX().isBlank() || !coordinates.getY().isBlank()) {
                return toJsonNode(coordinates);
            }

            throw new TransformationException("Сервис вернул ответ, но он не содержит координат " + e.getMessage());
        }
    }

    @Override
    public Type type() {
        return Type.ADDRESS;
    }

    private void saveAddressAndCoordinates(JsonNode data, Optional<String> oHash, SimpleCoordination coordinates) {
        String coordinatesString = coordinates.toString();
        GeoCache geoCache = new GeoCache();

        geoCache.setAddressHash(oHash.orElse(""));
        geoCache.setAddress(data.toString());

        geoCache.setCoordinates(coordinatesString);
        geoCache.setCoordinatesHash(hasheMaker.createHash(coordinatesString).orElse(""));

        geoCache.setCreatedAt(LocalDateTime.now());
        repository.save(geoCache);
    }
}
