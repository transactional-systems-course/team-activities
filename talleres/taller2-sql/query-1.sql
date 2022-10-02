ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT NOMBRETABLA, NUMCOLUMNAS, NUMCOLSNULL, ULTIMO_ANALISIS
FROM (
        SELECT TABLE_NAME AS NOMBRETABLA, COUNT(*) AS NUMCOLUMNAS
        FROM ALL_TAB_COLUMNS
        WHERE OWNER = 'PARRANDEROS'
        GROUP BY TABLE_NAME
    ) TAB_INF
    LEFT OUTER JOIN (
        SELECT TABLE_NAME, COUNT(NULLABLE) AS NUMCOLSNULL
        FROM ALL_TAB_COLUMNS
        WHERE OWNER = 'PARRANDEROS' AND
            NULLABLE = 'Y'
        GROUP BY TABLE_NAME
    ) NLLVAL
    ON NLLVAL.TABLE_NAME = TAB_INF.NOMBRETABLA
    LEFT OUTER JOIN (
        SELECT TABLE_NAME, MAX(TO_CHAR(LAST_ANALYZED, 'DD-MON-YYYY HH24:MI:SS')) AS "ULTIMO_ANALISIS"
        FROM DBA_TAB_COL_STATISTICS
        WHERE OWNER = 'PARRANDEROS'
        GROUP BY TABLE_NAME
    ) US_OBJ
    ON US_OBJ.TABLE_NAME = TAB_INF.NOMBRETABLA
ORDER BY NOMBRETABLA ASC;