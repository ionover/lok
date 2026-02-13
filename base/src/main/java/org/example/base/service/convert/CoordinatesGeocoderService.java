package org.example.base.service.convert;

import org.example.base.dto.NominationDto;
import org.example.base.dto.OsmAddressDto;
import org.example.base.entity.GeoCache;
import org.example.base.enums.Type;
import org.example.base.repository.GeoCasheRepository;
import org.example.base.service.WebSearcher;
import org.example.base.util.HasheMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.example.base.mappers.ToNominationMapper.mapToNominationDto;
import static org.example.base.util.JsonConverter.toJsonNode;

@Service
public class CoordinatesGeocoderService implements IConverter {

    private final Logger log = LoggerFactory.getLogger(CoordinatesGeocoderService.class);

    private final HasheMaker hasheMaker;
    private final GeoCasheRepository repository;
    private final WebSearcher webSearcher;

    public CoordinatesGeocoderService(HasheMaker hasheMaker, GeoCasheRepository repository, WebSearcher webSearcher) {
        this.hasheMaker = hasheMaker;
        this.repository = repository;
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
        NominationDto answer = mapToNominationDto(webSearcher.searchByCoordinates(data));

        log.debug("Успешно нашли кеш по интернету - сохраним его потомкам.");
        saveCoordinatesAndAddress(data, oHash, answer.getAddress());

        return toJsonNode(answer.getAddress());
    }

    @Override
    public Type type() {
        return Type.COORDINATES;
    }

    private void saveCoordinatesAndAddress(JsonNode data, Optional<String> oHash, OsmAddressDto osmAddress) {
        String coordinatesString = data.toString();
        GeoCache geoCache = new GeoCache();

        geoCache.setCoordinatesHash(oHash.orElse(""));
        geoCache.setCoordinates(coordinatesString);

        String addressString = osmAddress.toString();
        geoCache.setAddress(addressString);
        geoCache.setAddressHash(hasheMaker.createHash(addressString).orElse(""));

        geoCache.setCreatedAt(LocalDateTime.now());
        repository.save(geoCache);
    }
}
