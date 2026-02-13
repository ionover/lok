package org.example.base.service.convert;

import org.example.base.dto.NominationDto;
import org.example.base.dto.OsmAddressDto;
import org.example.base.entity.GeoCache;
import org.example.base.enums.Type;
import org.example.base.service.GeoCacheService;
import org.example.base.service.WebSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.util.Optional;

import static org.example.base.mappers.ToNominationMapper.mapToNominationDto;
import static org.example.base.util.JsonConverter.toJsonNode;

@Service
public class CoordinatesGeocoderService implements IConverter {

    private final Logger log = LoggerFactory.getLogger(CoordinatesGeocoderService.class);

    private final GeoCacheService cacheService;
    private final WebSearcher webSearcher;

    public CoordinatesGeocoderService(GeoCacheService cacheService, WebSearcher webSearcher) {
        this.cacheService = cacheService;
        this.webSearcher = webSearcher;
    }

    @Override
    public JsonNode convert(JsonNode data) {
        Optional<String> oHash = cacheService.createHash(data);
        Optional<GeoCache> oGeoCashe = Optional.empty();
        if (oHash.isPresent()) {
            oGeoCashe = cacheService.findByCoordinatesHash(oHash.get());
        }
        if (oGeoCashe.isPresent()) {
            log.debug("Успешно нашли кеш внутри базы данных!");
            return toJsonNode(oGeoCashe.get().getAddress());
        }

        log.debug("В базе данных не удалось найти сущность по хешу, идём в интернет!");
        NominationDto answer = mapToNominationDto(webSearcher.searchByCoordinates(data));

        log.debug("Успешно нашли кеш по интернету - сохраним его потомкам.");
        saveToCache(data, oHash, answer.getAddress());

        return toJsonNode(answer.getAddress());
    }

    @Override
    public Type type() {
        return Type.COORDINATES;
    }

    private void saveToCache(JsonNode data, Optional<String> coordinatesHash, OsmAddressDto osmAddress) {
        String coordinatesString = data.toString();
        String addressString = osmAddress.toString();
        String addressHash = cacheService.createHash(addressString).orElse("");

        cacheService.saveCache(coordinatesString, coordinatesHash.orElse(""), addressString, addressHash);
    }
}
