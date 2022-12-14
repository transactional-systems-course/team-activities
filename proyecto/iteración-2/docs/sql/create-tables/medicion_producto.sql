CREATE TABLE MEDICION_PRODUCTO (
    ID NUMBER PRIMARY KEY,
    MEDIDA NUMBER NOT NULL,
    UNIDAD_MEDIDA VARCHAR2(255) NOT NULL CHECK(UNIDAD_MEDIDA IN ('gr', 'ml')),
    ESPECIFICACION_EMPACADO NUMBER NOT NULL,
    UNIDAD_ESPECIFICACION_EMPACADO VARCHAR2(255) NOT NULL CHECK(UNIDAD_ESPECIFICACION_EMPACADO IN ('volumenCm3', 'pesoGR'))
);