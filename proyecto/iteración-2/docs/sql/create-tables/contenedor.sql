CREATE TABLE CONTENEDOR (
    ID NUMBER PRIMARY KEY,
    TIPO_CONTENEDOR VARCHAR2(255) CHECK (TIPO_CONTENEDOR IN ('Perecedero', 'NoPerecedero', 'Aseo', 'Abarrote', 'Congelado', 'PrendaVestir', 'Mueble', 'Herramienta', 'Electrodomestico')),
    CAPACIDAD_MAXIMA_VOLUMEN NUMBER NOT NULL,
    CAPACIDAD_DISPONIBLE_VOLUMEN NUMBER CHECK(CAPACIDAD_DISPONIBLE_VOLUMEN BETWEEN 0 AND CAPACIDAD_MAXIMA_VOLUMEN),
    CAPACIDAD_MAXIMA_PESO NUMBER NOT NULL,
    CAPACIDAD_DISPONIBLE_PESO NUMBER CHECK(CAPACIDAD_DISPONIBLE_VOLUMEN BETWEEN 0 AND CAPACIDAD_MAXIMA_PESO),
    NIVEL_ABASTECIMIENTO NUMBER,
    ID_SUCURSAL NUMBER NOT NULL,
    FOREIGN KEY(ID_SUCURSAL) REFERENCES SUCURSAL(ID)
);