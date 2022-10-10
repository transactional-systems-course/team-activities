CREATE TABLE PRODUCTOS_PROVISTOS (
    PROVEEDOR NUMBER FOREIGN KEY REFERENCES PROVEEDOR(NIT),
    PRODUCTO NUMBER FOREIGN KEY REFERENCES PRODUCTO(CODIGO_BARRAS),
    PRIMARY KEY(PROVEEDOR, PRODUCTO)
);