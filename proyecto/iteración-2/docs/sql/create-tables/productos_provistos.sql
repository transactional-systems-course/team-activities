CREATE TABLE PRODUCTOS_PROVISTOS (
    PROVEEDOR INT FOREIGN KEY REFERENCES PROVEEDOR(NIT),
    PRODUCTO INT FOREIGN KEY REFERENCES PRODUCTO(CODIGO_BARRAS),
    PRIMARY KEY(PROVEEDOR, PRODUCTO)
);