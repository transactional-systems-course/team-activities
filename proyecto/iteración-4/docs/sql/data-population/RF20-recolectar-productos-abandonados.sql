SELECT ID_PRODUCTO, CANT_UNIDADES_COMPRADAS
FROM COMPRA
    FULL OUTER JOIN PRODUCTOS_COMPRA
    ON COMPRA.ID = ID_COMPRA
WHERE ESTADO_COMPRA = 'CANCELADA';