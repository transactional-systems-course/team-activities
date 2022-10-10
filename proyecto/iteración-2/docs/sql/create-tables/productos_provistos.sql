CREATE TABLE PRODUCTOS_PROVISTOS (
    ID_PROVEEDOR NUMBER FOREIGN KEY REFERENCES PROVEEDOR(NIT),
    ID_PRODUCTO NUMBER FOREIGN KEY REFERENCES PRODUCTO(CODIGO_BARRAS),
    PRIMARY KEY(ID_PROVEEDOR, ID_PRODUCTO)
);