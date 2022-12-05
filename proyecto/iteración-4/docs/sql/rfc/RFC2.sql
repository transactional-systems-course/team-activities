SELECT ID                                                 AS ID_PROMOCION,
    ID_PRODUCTO,
    NOMBRE,
    TOTAL_UNIDADES_OFRECIDAS-CANT_UNIDADES_DISPONIBLES AS CANTIDADES_VENDIDAS
FROM (
        SELECT *
        FROM PROMOCION
            INNER JOIN PRODUCTO
            ON PRODUCTO.CODIGO_BARRAS = PROMOCION.ID_PRODUCTO
    )
ORDER BY CANTIDADES_VENDIDAS DESC FETCH FIRST 20 ROWS ONLY;