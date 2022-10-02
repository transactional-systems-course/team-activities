ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;
SELECT NOMBRETABLA, NOMBRECOLSPK, TIPODEDATO FROM
(
    (
        SELECT 
            ALL_CONS_COLUMNS.TABLE_NAME AS NOMBRETABLA,
            ALL_CONS_COLUMNS.COLUMN_NAME AS NOMBRECOLSPK
        FROM 
            ALL_CONSTRAINTS, ALL_CONS_COLUMNS
        WHERE
            all_constraints.constraint_type = 'P'
            and all_constraints.constraint_name = all_cons_columns.constraint_name
            and all_constraints.owner = all_cons_columns.owner
            AND all_cons_columns.owner = 'PARRANDEROS'
    ) PKS
    INNER JOIN
    (
        SELECT
            TABLE_NAME, COLUMN_NAME, DATA_TYPE AS TIPODEDATO
        FROM
            ALL_TAB_COLUMNS
        WHERE 
            OWNER = 'PARRANDEROS'
    ) DTYPE
    ON PKS.NOMBRECOLSPK = DTYPE.COLUMN_NAME
    AND PKS.NOMBRETABLA = DTYPE.TABLE_NAME
) ORDER BY NOMBRETABLA;
    
    