CREATE VIEW COMPRAS_USUARIO AS
    SELECT *
    FROM ( (
            SELECT *
            FROM USUARIO
        ) FULL OUTER JOIN (
            SELECT *
            FROM COMPRA
        ) ON NUMERO_DOCUMENTO = COMPRADOR FULL OUTER JOIN (
            SELECT *
            FROM PRODUCTOS_COMPRA
        ) ON ID_COMPRA = ID );

CREATE VIEW USUARIOS_CON_COMPRA AS
    SELECT *
    FROM (
            SELECT *
            FROM ( (
                    SELECT *
                    FROM USUARIO
                ) INNER JOIN (
                    SELECT *
                    FROM COMPRA
                ) ON NUMERO_DOCUMENTO = COMPRADOR INNER JOIN (
                    SELECT *
                    FROM PRODUCTOS_COMPRA
                ) ON ID_COMPRA = ID )
        );

SELECT *
FROM (
        SELECT *
        FROM COMPRAS_USUARIO
        WHERE NUMERO_DOCUMENTO NOT IN (
                SELECT NUMERO_DOCUMENTO
                FROM USUARIOS_CON_COMPRA
                WHERE FECHA_COMPRA BETWEEN '01-01-2020' AND
                    '01-01-2022' AND
                    ESTADO_COMPRA = 'PAGADA'
            )
    );

/*
SELECT
    *
FROM
    (
        SELECT
            *
        FROM
            COMPRAS_USUARIO
        WHERE
            NUMERO_DOCUMENTO NOT IN (
                SELECT
                    NUMERO_DOCUMENTO
                FROM
                    USUARIOS_CON_COMPRA
                WHERE
                    FECHA_COMPRA BETWEEN ?
                    AND ?
                    AND ESTADO_COMPRA = 'PAGADA'
            )
    );
*/

SELECT *
FROM USUARIOS_CON_COMPRA