package org.example.base.service;

import org.example.base.entity.GeoCache;
import org.example.base.repository.GeoCacheRepository;
import org.example.base.util.HasheMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GeoCacheService {

    private final Logger log = LoggerFactory.getLogger(GeoCacheService.class);

    private final GeoCacheRepository repository;
    private final HasheMaker hasheMaker;

    public GeoCacheService(GeoCacheRepository repository, HasheMaker hasheMaker) {
        this.repository = repository;
        this.hasheMaker = hasheMaker;
    }

    public Optional<GeoCache> findByCoordinatesHash(String hash) {
        return repository.getByCoordinatesHash(hash);
    }

    public Optional<GeoCache> findByAddressHash(String hash) {
        return repository.getByAddressHash(hash);
    }

    public void saveCache(String coordinates, String coordinatesHash, String address, String addressHash) {
        GeoCache geoCache = new GeoCache();
        geoCache.setCoordinates(coordinates);
        geoCache.setCoordinatesHash(coordinatesHash);
        geoCache.setAddress(address);
        geoCache.setAddressHash(addressHash);
        geoCache.setCreatedAt(LocalDateTime.now());

        repository.save(geoCache);
        log.debug("Кеш успешно сохранён в базу данных");
    }

    public Optional<String> createHash(Object data) {
        return hasheMaker.createHash(data);
    }
}
