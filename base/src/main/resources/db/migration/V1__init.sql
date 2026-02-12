CREATE TABLE IF NOT EXISTS public.geo_cashe
(
    id               bigserial          NOT NULL,
    coordinates      jsonb,
    coordinates_hash string
    address          jsonb,
    address_hash     jsonb,
    created_at       timestamp without time zone,

    CONSTRAINT geo_cashe_pkey PRIMARY KEY (id)
    ) TABLESPACE pg_default;


    CONSTRAINT geo_cashe_pkey PRIMARY KEY (coordinates_hash)
    ) TABLESPACE pg_default;


    CONSTRAINT geo_cashe_pkey PRIMARY KEY (address_hash)
    ) TABLESPACE pg_default;

ALTER TABLE public.geo_cashe
    OWNER to ${db_owner};

