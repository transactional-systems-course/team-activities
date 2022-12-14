SELECT ID_PRODUCTO, SUM(CANT_UNIDADES_COMPRADAS), ID_PROVEEDOR
FROM COMPRA
    FULL OUTER JOIN PRODUCTOS_COMPRA
    ON COMPRA.ID = ID_COMPRA
    FULL OUTER JOIN PRODUCTO
    ON PRODUCTO.CODIGO_BARRAS = ID_PRODUCTO
    INNER JOIN PEDIDOS_PROVEEDOR
    ON PRODUCTO.ID_PEDIDO = PEDIDOS_PROVEEDOR.ID_PEDIDO
WHERE ESTADO_COMPRA = 'PAGADA'
GROUP BY ID_PROVEEDOR, ID_PRODUCTO;