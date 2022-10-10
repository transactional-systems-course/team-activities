CREATE TABLE USUARIO (
    NUMERO_DOCUMENTO INT,
    NOMBRE VARCHAR(255) NOT NULL,
    TIPO_CLIENTE VARCHAR(255) CHECK(TIPO_CLIENTE IN ('PersonaNatural', 'Empresa')),
    TIPO_DOCUMENTO VARCHAR(255) NOT NULL,
    PALABRA_CLAVE VARCHAR(255) NOT NULL,
    CORREO VARCHAR(255) NOT NULL,
    ROL VARCHAR(255) NOT NULL CHECK(ROL IN ('Administrador', 'GerenteGeneral', 'GerenteSucursal', 'Operador', 'Cajero', 'Cliente')),
    DIRECCION VARCHAR(255),
    PUNTOS_ACUMULADOS VARCHAR(255),
    SUCURSAL VARCHAR(255) FOREIGN KEY REFERENCES SUCURSAL(ID),
    PRIMARY KEY NUMERO_DOCUMENTO
);