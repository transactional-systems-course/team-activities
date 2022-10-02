ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT NOMBRETABLA, NUMCOLUMNAS, NUMCOLSNULL, ULTIMO_ANALISIS FROM
    (
    (SELECT TABLE_NAME AS NOMBRETABLA, 
        COUNT(*) AS NUMCOLUMNAS,  
        COUNT(NULLABLE) AS NUMCOLSNULL
    FROM ALL_TAB_COLUMNS 
    WHERE OWNER = 'PARRANDEROS' 
    GROUP BY TABLE_NAME)TAB_INF
    LEFT JOIN
    (   
    SELECT table_name, MAX(to_char(last_analyzed,'DD-MON-YYYY HH24:MI:SS')) AS "ULTIMO_ANALISIS"
    from DBA_TAB_COL_STATISTICS
    WHERE OWNER = 'PARRANDEROS'
    GROUP BY TABLE_NAME
        ) US_OBJ
    ON US_OBJ.TABLE_NAME = TAB_INF.NOMBRETABLA
    );