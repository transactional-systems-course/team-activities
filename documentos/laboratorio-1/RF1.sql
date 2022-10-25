BEGIN
    SAVEPOINT START_TRAN;
    INSERT INTO RESERVAS (
        IDRESERVA,
        IDCLIENTE,
        IDFUNCION
    ) VALUES (
        1,
        1,
        1
    );
    INSERT INTO SILLASRESERVAS (
        IDSILLA,
        IDFUNCION,
        IDRESERVA
    ) VALUES (
        1,
        1,
        1
    );
    INSERT INTO SILLASRESERVAS (
        IDSILLA,
        IDFUNCION,
        IDRESERVA
    ) VALUES (
        2,
        1,
        1
    );
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK TO START_TRAN;
        RAISE;
END;