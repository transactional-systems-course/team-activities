CREATE TABLE CONTENEDOR (
    ID INT,
    TIPO_CONTENEDOR VARCHAR(255) CHECK (TIPO_CONTENEDOR IN ('Perecedero', 'NoPerecedero', 'Aseo', 'Abarrote', 'Congelado', 'PrendaVestir', 'Mueble', 'Herramienta', 'Electrodomestico')),
    CAPACIDAD_MAXIMA_VOLUMEN INT NOT NULL,
    CAPACIDAD_DISPONIBLE_VOLUMEN INT CHECK(CAPACIDAD_DISPONIBLE_VOLUMEN BETWEEN 0 AND CAPACIDAD_MAXIMA_VOLUMEN),
    CAPACIDAD_MAXIMA_PESO INT NOT NULL,
    CAPACIDAD_DISPONIBLE_PESO INT CHECK(CAPACIDAD_DISPONIBLE_VOLUMEN BETWEEN 0 AND CAPACIDAD_MAXIMA_PESO),
    NIVEL_ABASTECIMIENTO INT,
    SUCURSAL INT NOT NULL FOREIGN KEY REFERENCES SUCURSAL(ID),

    PRIMARY KEY ID
);