package org.example.base.repository;

import org.example.base.entity.GeoCache;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GeoCacheRepository extends CrudRepository<GeoCache, Long> {

    Optional<GeoCache> getByAddressHash(String addressHash);

    Optional<GeoCache> getByCoordinatesHash(String coordinatesHash);
}
