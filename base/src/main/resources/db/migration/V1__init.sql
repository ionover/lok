CREATE TABLE IF NOT EXISTS public.geo_cache
(
    id               bigserial                   NOT NULL,
    coordinates      jsonb,
    coordinates_hash varchar(255),
    address          jsonb,
    address_hash     varchar(255),
    created_at       timestamp without time zone,

    CONSTRAINT geo_cache_pkey PRIMARY KEY (id),
    CONSTRAINT geo_cache_coordinates_hash_unique UNIQUE (coordinates_hash),
    CONSTRAINT geo_cache_address_hash_unique UNIQUE (address_hash)
);

