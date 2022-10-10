CREATE TABLE ALMACENAMIENTO_PRODUCTO (
    ID NUMBER PRIMARY KEY,
    ID_CONTENEDOR_ACTUAL NUMBER NOT NULL,
    FOREIGN KEY(ID_CONTENEDOR_ACTUAL) REFERENCES CONTENEDOR(ID),
    EXISTENCIAS_ACTUALES NUMBER NOT NULL,
    NIVEL_REORDEN NUMBER NOT NULL
);