SELECT *
FROM RESERVAS;

SET AUTOCOMMIT 1;

INSERT INTO RESERVAS (
    IDRESERVA,
    IDCLIENTE,
    IDFUNCION
) VALUES (
    4,
    1,
    1
);

INSERT INTO SILLASRESERVAS (
    IDSILLA,
    IDFUNCION,
    IDRESERVA
) VALUES (
    1,
    4,
    4
);

INSERT INTO SILLASRESERVAS (
    IDSILLA,
    IDFUNCION,
    IDRESERVA
) VALUES (
    2,
    4,
    4
);

SELECT *
FROM RESERVAS;

SET AUTOCOMMIT 0;

INSERT INTO RESERVAS (
    IDRESERVA,
    IDCLIENTE,
    IDFUNCION
) VALUES (
    5,
    1,
    1
);

INSERT INTO SILLASRESERVAS (
    IDSILLA,
    IDFUNCION,
    IDRESERVA
) VALUES (
    1,
    5,
    5
);

INSERT INTO SILLASRESERVAS (
    IDSILLA,
    IDFUNCION,
    IDRESERVA
) VALUES (
    2,
    5,
    5
);

SELECT *
FROM RESERVAS;

COMMIT;

SELECT *
FROM RESERVAS;