SELECT *
FROM (
        SELECT *
        FROM (
                SELECT *
                FROM USUARIOS_CON_COMPRA
            )
            INNER JOIN (
                SELECT *
                FROM PRODUCTO
            )
            ON ID_PRODUCTO = CODIGO_BARRAS
        WHERE ESTADO_COMPRA = 'PAGADA'
    )
WHERE VALOR_COMPRA_TOTAL >= 100000 OR
    CATEGORIA IN ('Tecnologia', 'Herramienta')