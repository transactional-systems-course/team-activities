ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT TABLE_NAME
FROM DBA_TABLES
WHERE OWNER='PARRANDEROS';

SELECT * FROM BEBIDAS;