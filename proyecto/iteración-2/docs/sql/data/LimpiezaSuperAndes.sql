--- Sentencias SQL para la creación del esquema de SuperAndes

-- USO
-- Copie el contenido deseado de este archivo en una pestaña SQL de SQL Developer
-- Ejecútelo como un script - Utilice el botón correspondiente de la pestaña utilizada

-- Eliminar todas las tablas de la base de datos
DROP TABLE "ALMACENAMIENTO_PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "COMPRA" CASCADE CONSTRAINTS;
DROP TABLE "CONTENEDOR" CASCADE CONSTRAINTS;
DROP TABLE "DETALLES_PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "MEDICION_PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "PEDIDO" CASCADE CONSTRAINTS;
DROP TABLE "PEDIDOS_PROVEEDOR" CASCADE CONSTRAINTS;
DROP TABLE "PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "PRODUCTOS_COMPRA" CASCADE CONSTRAINTS;
DROP TABLE "PRODUCTOS_PROVISTOS" CASCADE CONSTRAINTS;
DROP TABLE "PROMOCION" CASCADE CONSTRAINTS;
DROP TABLE "PROVEEDOR" CASCADE CONSTRAINTS;
DROP TABLE "REVIEW_PEDIDO" CASCADE CONSTRAINTS;
DROP TABLE "ROL" CASCADE CONSTRAINTS;
DROP TABLE "SUCURSAL" CASCADE CONSTRAINTS;
DROP TABLE "USUARIO" CASCADE CONSTRAINTS;
COMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos
DELETE FROM ALMACENAMIENTO_PRODUCTO;
DELETE FROM COMPRA;
DELETE FROM CONTENEDOR;
DELETE FROM DETALLES_PRODUCTO;
DELETE FROM MEDICION_PRODUCTO;
DELETE FROM PEDIDO;
DELETE FROM PEDIDOS_PROVEEDOR;
DELETE FROM PRODUCTO;
DELETE FROM PRODUCTOS_COMPRA;
DELETE FROM PRODUCTOS_PROVISTOS;
DELETE FROM PROMOCION;
DELETE FROM PROVEEDOR;
DELETE FROM REVIEW_PEDIDO;
DELETE FROM ROL;
DELETE FROM SUCURSAL;
DELETE FROM USUARIO;
COMMIT;