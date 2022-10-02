ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT * FROM
(
    (SELECT TABLE_NAME AS NOMBRETABLA, DATA_TYPE AS TIPODEDATOS, COUNT(DATA_TYPE) AS NUMCOLSTIPODATO, AVG(AVG_COL_LEN) AS PromedioLongitudCol
    FROM ALL_TAB_COLUMNS 
    WHERE OWNER = 'PARRANDEROS'
    GROUP BY TABLE_NAME, DATA_TYPE
    ORDER BY TABLE_NAME) Q
);