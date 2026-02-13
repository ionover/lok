package org.example.base.service.convert;

import org.example.base.dto.NominationDto;
import org.example.base.entity.GeoCache;
import org.example.base.enums.Type;
import org.example.base.repository.GeoCasheRepository;
import org.example.base.service.WebSearcher;
import org.example.base.util.HasheMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.util.Optional;

import static org.flywaydb.core.internal.util.JsonUtils.toJson;

@Service
public class AddressGeocoderService implements IConverter {

    private final Logger log = LoggerFactory.getLogger(AddressGeocoderService.class);

    private final HasheMaker hasheMaker;
    private final GeoCasheRepository repository;
    private final WebSearcher webSearcher;

    public AddressGeocoderService(GeoCasheRepository repository, HasheMaker hasheMaker, WebSearcher webSearcher) {
        this.repository = repository;
        this.hasheMaker = hasheMaker;
        this.webSearcher = webSearcher;
    }

    @Override
    public Object convert(Object data) {
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
        NominationDto answer = webSearcher.searchByAddress(data);

        log.debug("Успешно нашли кеш по интернету - сохраним его потомкам");
        GeoCache geoCache = new GeoCache();
        geoCache.setAddress(answer.getDisplayName());
        geoCache.setAddressHash(hasheMaker.createHash(data));

        JsonNode coordinates = toJson(answer.getLat(), answer.getLon());

        geoCache.setCoordinates(coordinates);
        geoCache.setAddressHash(hasheMaker.createHash(coordinates));

        repository.save(geoCache);


        return answer;
    }

    @Override
    public Type type() {
        return Type.ADDRESS;
    }
}
