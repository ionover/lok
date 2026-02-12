package org.example.base.service.convert;

import org.example.base.entity.GeoCashe;
import org.example.base.enums.Type;
import org.example.base.repository.GeoCasheRepository;
import org.example.base.service.WebSearcher;
import org.example.base.util.HasheMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.util.Optional;

@Service
public class AdresToCoordService implements IConverter {
    private final Logger log = LoggerFactory.getLogger(AdresToCoordService.class);

    private final HasheMaker hasheMaker;
    private final GeoCasheRepository repository;
    private final WebSearcher webSearcher;


    public AdresToCoordService(GeoCasheRepository repository, HasheMaker hasheMaker, WebSearcher webSearcher) {
        this.repository = repository;
        this.hasheMaker = hasheMaker;
        this.webSearcher = webSearcher;
    }


    @Override
    public Object convert(Object data) {
        Optional<String> oHash = hasheMaker.createHash(data);
        Optional<GeoCashe> geoCashe = Optional.empty();
        if (oHash.isPresent()) {
            geoCashe = repository.getByAddressHash(oHash.get());
        }

        if (geoCashe.isPresent()) {
            log.debug("Успешно нашли кеш внутри базы данных!");

            return geoCashe.get().getCoordinates();
        }

        log.debug("В базе данных не удалось найти сущность по хешу, идём в интернет!");
        JsonNode answer = webSearcher.search(data);

        return "converted ADDRESS -> COORDINATES";
    }

    @Override
    public Type type() {
        return Type.ADDRESS;
    }
}
