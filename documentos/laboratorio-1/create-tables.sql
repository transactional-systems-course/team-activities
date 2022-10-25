CREATE TABLE RESERVAS (
    IDRESERVA NUMBER(5) CONSTRAINT PK_RESERVAS PRIMARY KEY,
    IDFUNCION NUMBER(3),
    IDCLIENTE NUMBER(8),
    FECHA DATE,
    ESTADO VARCHAR(8) CONSTRAINT CK_R_ESTADO CHECK (ESTADO IN ('Reserva', 'Compra'))
);

CREATE TABLE SILLASRESERVAS (
    IDSILLA VARCHAR(3),
    IDFUNCION NUMBER(3),
    IDRESERVA NUMBER(5) NOT NULL CONSTRAINT FK_SR_RESERVAS REFERENCES RESERVAS,
    CONSTRAINT PK_SILLASRESERVAS PRIMARY KEY (IDSILLA, IDFUNCION)
);

DELETE FROM SILLASRESERVAS;
DELETE FROM RESERVAS;