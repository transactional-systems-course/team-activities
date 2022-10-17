CREATE SEQUENCE SUPERANDES_SEQUENCE;

CREATE TABLE DETALLES_PRODUCTO (
    ID NUMBER PRIMARY KEY,
    PRECIO NUMBER NOT NULL,
    PRECIO_POR_UNIDAD_MEDIDA NUMBER NOT NULL,
    PRESENTACION VARCHAR2(255) NOT NULL,
    CANTIDAD_PRESENTACION NUMBER NOT NULL
);

CREATE TABLE ROL (
    ID NUMBER PRIMARY KEY,
    ROL VARCHAR2(255) UNIQUE NOT NULL
);

CREATE TABLE MEDICION_PRODUCTO (
    ID NUMBER PRIMARY KEY,
    MEDIDA NUMBER NOT NULL,
    UNIDAD_MEDIDA VARCHAR2(255) NOT NULL CHECK(UNIDAD_MEDIDA IN ('gr', 'ml')),
    ESPECIFICACION_EMPACADO NUMBER NOT NULL,
    UNIDAD_ESPECIFICACION_EMPACADO VARCHAR2(255) NOT NULL CHECK(UNIDAD_ESPECIFICACION_EMPACADO IN ('volumenCm3', 'pesoGR'))
);

CREATE TABLE SUCURSAL (
    ID NUMBER PRIMARY KEY,
    CIUDAD VARCHAR2(255) NOT NULL,
    DIRECCION VARCHAR2(255) NOT NULL,
    NOMBRE VARCHAR2(255) NOT NULL,
    SIZE_SUCURSAL VARCHAR2(255) CHECK(SIZE_SUCURSAL IN ('Small', 'Medium', 'Big')),
    SEGMENTO_OBJETIVO VARCHAR2(255)
);

CREATE TABLE PROVEEDOR (
    NIT NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(255) NOT NULL,
    CALIFICACION_PROMEDIO NUMBER CHECK(CALIFICACION_PROMEDIO BETWEEN 0 AND 5)
);

CREATE TABLE PEDIDO (
    ID NUMBER PRIMARY KEY,
    CANTIDAD_RECOMPRA INTEGER NOT NULL,
    PRECIO_COMPRA_PRODUCTO NUMBER NOT NULL,
    PRECIO_TOTAL_PEDIDO NUMBER,
    FECHA_ESPERADA_ENTREGA DATE NOT NULL,
    FECHA_ENTREGA DATE,
    ESTADO VARCHAR2(255) CHECK(ESTADO IN ('CREADO', 'EN_PROCESO', 'ENTREGADO')),
    ID_SUCURSAL NUMBER NOT NULL,
    FOREIGN KEY(ID_SUCURSAL) REFERENCES SUCURSAL(ID)
);

CREATE TABLE PEDIDOS_PROVEEDOR (
    ID_PEDIDO NUMBER,
    ID_PROVEEDOR NUMBER,
    PRIMARY KEY(ID_PEDIDO, ID_PROVEEDOR),
    FOREIGN KEY(ID_PEDIDO) REFERENCES PEDIDO(ID),
    FOREIGN KEY(ID_PROVEEDOR) REFERENCES PROVEEDOR(NIT)
);

CREATE TABLE CONTENEDOR (
    ID NUMBER PRIMARY KEY,
    TIPO_CONTENEDOR VARCHAR2(255) CHECK (TIPO_CONTENEDOR IN ('Bodega', 'LocalVentas', 'Estante')),
    TIPO_PRODUCTO VARCHAR2(255) CHECK (TIPO_PRODUCTO IN ('Perecedero', 'NoPerecedero', 'Aseo', 'Abarrote', 'Congelado', 'PrendaVestir', 'Mueble', 'Herramienta', 'Electrodomestico')),
    CAPACIDAD_MAXIMA_VOLUMEN NUMBER NOT NULL,
    CAPACIDAD_MAXIMA_PESO NUMBER NOT NULL,
    NIVEL_ABASTECIMIENTO INTEGER,
    ID_SUCURSAL NUMBER NOT NULL,
    FOREIGN KEY(ID_SUCURSAL) REFERENCES SUCURSAL(ID)
);

CREATE TABLE ALMACENAMIENTO_PRODUCTO (
    ID NUMBER PRIMARY KEY,
    ID_CONTENEDOR_ACTUAL NUMBER NOT NULL,
    FOREIGN KEY(ID_CONTENEDOR_ACTUAL) REFERENCES CONTENEDOR(ID),
    EXISTENCIAS_ACTUALES INTEGER NOT NULL,
    NIVEL_REORDEN INTEGER NOT NULL
);

CREATE TABLE REVIEW_PEDIDO (
    ID NUMBER PRIMARY KEY,
    PORCENTAJE_CALIFICACION_CALIDAD NUMBER CHECK(PORCENTAJE_CALIFICACION_CALIDAD BETWEEN 0 AND 100),
    COMENTARIOS_CALIFICACION_CALIDAD VARCHAR2(255),
    ID_PEDIDO NUMBER NOT NULL,
    FOREIGN KEY(ID_PEDIDO) REFERENCES PEDIDO(ID),
    ID_PROVEEDOR NUMBER NOT NULL,
    FOREIGN KEY(ID_PROVEEDOR) REFERENCES PROVEEDOR(NIT)
);

CREATE TABLE USUARIO (
    NUMERO_DOCUMENTO NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(255) NOT NULL,
    TIPO_CLIENTE VARCHAR2(255) CHECK(TIPO_CLIENTE IN ('PersonaNatural', 'Empresa')),
    TIPO_DOCUMENTO VARCHAR2(255) NOT NULL,
    PALABRA_CLAVE VARCHAR2(255) NOT NULL,
    CORREO VARCHAR2(255) NOT NULL,
    ID_ROL NUMBER NOT NULL,
    FOREIGN KEY(ID_ROL) REFERENCES ROL(ID),
    DIRECCION VARCHAR2(255),
    PUNTOS_ACUMULADOS INTEGER,
    ID_SUCURSAL NUMBER,
    FOREIGN KEY(ID_SUCURSAL) REFERENCES SUCURSAL(ID)
);

CREATE TABLE COMPRA (
    ID NUMBER PRIMARY KEY,
    VALOR_COMPRA_TOTAL NUMBER,
    URL_FACTURA_ELECTRONICA VARCHAR2(255),
    ESTADO_COMPRA VARCHAR2(255) CHECK(ESTADO_COMPRA IN ('CREADA', 'PAGADA', 'CANCELADA')) NOT NULL,
    COMPRADOR NUMBER NOT NULL,
    FOREIGN KEY(COMPRADOR) REFERENCES USUARIO(NUMERO_DOCUMENTO)
);

CREATE TABLE PRODUCTO (
    CODIGO_BARRAS NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(255) NOT NULL,
    MARCA VARCHAR2(255) NOT NULL,
    CATEGORIA VARCHAR2(255) NOT NULL CHECK(CATEGORIA IN ('Perecedero', 'NoPerecedero', 'Aseo', 'Abarrote', 'Congelado', 'PrendaVestir', 'Mueble', 'Herramienta', 'Electrodomestico')),
    TIPO_PRODUCTO VARCHAR2(255) NOT NULL,
    FECHA_VENCIMIENTO DATE,
    ID_PEDIDO NUMBER NOT NULL,
    FOREIGN KEY(ID_PEDIDO) REFERENCES PEDIDO(ID),
    ID_SUCURSAL NUMBER NOT NULL,
    FOREIGN KEY(ID_SUCURSAL) REFERENCES SUCURSAL(ID),
    INFO_DETALLES NUMBER NOT NULL,
    FOREIGN KEY(INFO_DETALLES) REFERENCES DETALLES_PRODUCTO(ID),
    INFO_MEDICION NUMBER NOT NULL,
    FOREIGN KEY(INFO_MEDICION) REFERENCES MEDICION_PRODUCTO(ID),
    INFO_ALMACENAMIENTO NUMBER NOT NULL,
    FOREIGN KEY(INFO_ALMACENAMIENTO) REFERENCES ALMACENAMIENTO_PRODUCTO(ID)
);

CREATE TABLE PROMOCION (
    ID NUMBER,
    REBAJA_EN_PRECIO NUMBER,
    TIPO_PROMOCION VARCHAR2(255) NOT NULL CHECK(TIPO_PROMOCION IN ('PagueLleveUnidad', 'DescuentoPorcentaje', 'PagueLleveCantidad', 'Pague1LleveDescuento2', 'Combo')),
    FECHA_INICIO DATE NOT NULL,
    FECHA_FIN DATE NOT NULL,
    ID_PRODUCTO NUMBER,
    FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO(CODIGO_BARRAS),
    PRIMARY KEY(ID, ID_PRODUCTO)
);

CREATE TABLE PRODUCTOS_COMPRA (
    ID_COMPRA NUMBER,
    ID_PRODUCTO NUMBER,
    CANT_UNIDADES_COMPRADAS INTEGER,
    PRIMARY KEY(ID_COMPRA, ID_PRODUCTO),
    FOREIGN KEY(ID_COMPRA) REFERENCES COMPRA(ID),
    FOREIGN KEY(ID_PRODUCTO) REFERENCES PRODUCTO(CODIGO_BARRAS)
);

CREATE TABLE PRODUCTOS_PROVISTOS (
    ID_PROVEEDOR NUMBER,
    FOREIGN KEY(ID_PROVEEDOR) REFERENCES PROVEEDOR(NIT),
    ID_PRODUCTO NUMBER,
    FOREIGN KEY(ID_PRODUCTO) REFERENCES PRODUCTO(CODIGO_BARRAS),
    PRIMARY KEY(ID_PROVEEDOR, ID_PRODUCTO)
);

COMMIT;