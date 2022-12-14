DROP VIEW ULTIMOS_PEDIDOS;

CREATE VIEW ULTIMOS_PEDIDOS AS
    SELECT CODIGO_BARRAS,
        NOMBRE,
        CATEGORIA,
        ID_PEDIDO,
        SUCURSAL,
        FECHA_ENTREGA
    FROM (
            SELECT ID,
                CODIGO_BARRAS,
                NOMBRE,
                CATEGORIA,
                ID_PEDIDO,
                PRODUCTO.ID_SUCURSAL AS SUCURSAL,
                FECHA_ENTREGA
            FROM PRODUCTO
                INNER JOIN(
                    SELECT *
                    FROM PEDIDO
                )
                ON ID = PRODUCTO.ID_PEDIDO
        )
    WHERE (CODIGO_BARRAS,
        FECHA_ENTREGA) IN (
            SELECT CODIGO_BARRAS,
                MAX(FECHA_ENTREGA)
            FROM PEDIDO
            GROUP BY CODIGO_BARRAS
        );

SELECT *
FROM ULTIMOS_PEDIDOS;

DROP VIEW PENULTIMOS_PEDIDOS;

CREATE VIEW PENULTIMOS_PEDIDOS AS
    SELECT CODIGO_BARRAS,
        NOMBRE,
        CATEGORIA,
        ID_PEDIDO,
        PRODUCTO.ID_SUCURSAL,
        MAX(FECHA_ENTREGA) AS FECHA_ENTREGA
    FROM PRODUCTO
        INNER JOIN (
            SELECT *
            FROM PEDIDO
        )
        ON ID = PRODUCTO.ID_PEDIDO
    WHERE NOT EXISTS (
            SELECT *
            FROM ULTIMOS_PEDIDOS
        )
    GROUP BY CODIGO_BARRAS,
        NOMBRE,
        CATEGORIA,
        ID_PEDIDO,
        PRODUCTO.ID_SUCURSAL,
        FECHA_ENTREGA;

SELECT *
FROM ULTIMOS_PEDIDOS;

SELECT *
FROM (
        SELECT CODIGO_BARRAS,
            NOMBRE,
            CATEGORIA,
            ID_PEDIDO,
            SUCURSAL,
            FECHA_ENTREGA AS ULTIMA_ENTREGA
        FROM ULTIMOS_PEDIDOS
            INNER JOIN (
                SELECT CODIGO_BARRAS AS CBARRAS,
                    FECHA_ENTREGA AS PENULTIMA_ENTREGA
                FROM PENULTIMOS_PEDIDOS
            )
            ON CBARRAS = ULTIMOS_PEDIDOS.CODIGO_BARRAS
        WHERE PENULTIMA_ENTREGA <= FECHA_ENTREGA - INTERVAL '60' DAY
    );

/*
    revisa esto por favor.

    SELECT CODIGO_BARRAS, NOMBRE, CATEGORIA, ID_PEDIDO, SUCURSAL, fecha_entrega AS ULTIMA_ENTREGA
    FROM ULTIMOS_PEDIDOS
    INNER JOIN
    (SELECT CODIGO_BARRAS AS CBARRAS, FECHA_ENTREGA AS PENULTIMA_ENTREGA FROM PENULTIMOS_PEDIDOS)
    ON CBARRAS = ULTIMOS_PEDIDOS.CODIGO_BARRAS
    WHERE PENULTIMA_ENTREGA <= FECHA_ENTREGA - INTERVAL '60' DAY
    AND SUCURSAL = ?
*/

SELECT *
FROM PEDIDO;

SELECT *
FROM PENULTIMOS_PEDIDOS;