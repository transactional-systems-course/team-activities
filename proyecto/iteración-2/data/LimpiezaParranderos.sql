--- Sentencias SQL para la creaci�n del esquema de parranderos
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido deseado de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada
    
-- Eliminar todas las tablas de la base de datos
DROP TABLE "A_BAR" CASCADE CONSTRAINTS;
DROP TABLE "A_BEBIDA" CASCADE CONSTRAINTS;
DROP TABLE "A_BEBEDOR" CASCADE CONSTRAINTS;
DROP TABLE "A_TIPOBEBIDA" CASCADE CONSTRAINTS;
DROP TABLE "A_SIRVEN" CASCADE CONSTRAINTS;
DROP TABLE "A_GUSTAN" CASCADE CONSTRAINTS;
DROP TABLE "A_VISITAN" CASCADE CONSTRAINTS;
COMMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos
-- El orden es importante. Por qu�?
delete from a_gustan;
delete from a_sirven;
delete from a_visitan;
delete from a_bebida;
delete from a_tipobebida;
delete from a_bebedor;
delete from a_bar;
commit;

