-- Show purchases made by SuperAndes to suppliers
SELECT *
FROM PEDIDO
    INNER JOIN PEDIDOS_PROVEEDOR
    ON PEDIDO.ID = PEDIDOS_PROVEEDOR.ID_PEDIDO
WHERE ID_PROVEEDOR = '1280637439';