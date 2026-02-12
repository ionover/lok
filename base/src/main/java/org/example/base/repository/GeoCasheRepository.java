package org.example.base.repository;

import org.example.base.entity.GeoCashe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GeoCasheRepository extends CrudRepository<GeoCashe, Long> {

    Optional<GeoCashe> getByAddressHash(String addressHash);
}
