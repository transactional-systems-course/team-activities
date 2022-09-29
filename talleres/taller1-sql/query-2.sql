ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT DISTINCT ID, NOMBRE_BEBEDOR, CIUDAD, PRESUPUESTO_BEBEDOR, BARES_ALTOS, BARES_MEDIOS, BARES_BAJOS
FROM (
        SELECT BEBEDORES.ID, BEBEDORES.NOMBRE AS NOMBRE_BEBEDOR, CIUDAD, PRESUPUESTO AS PRESUPUESTO_BEBEDOR, PRESUPUESTO_BAR, BARES_ALTOS, BARES_MEDIOS, BARES_BAJOS
        FROM BEBEDORES
            INNER JOIN FRECUENTAN
            ON BEBEDORES.ID = FRECUENTAN.ID_BEBEDOR
            INNER JOIN (
                SELECT ID, PRESUPUESTO AS PRESUPUESTO_BAR
                FROM BARES
            ) BARES
            ON FRECUENTAN.ID_BAR = BARES.ID
            FULL OUTER JOIN (
                SELECT BEBEDORES.ID, COUNT(PRESUPUESTO_BAR) AS BARES_ALTOS
                FROM BEBEDORES
                    INNER JOIN FRECUENTAN
                    ON BEBEDORES.ID = FRECUENTAN.ID_BEBEDOR
                    INNER JOIN (
                        SELECT ID, PRESUPUESTO AS PRESUPUESTO_BAR
                        FROM BARES
                    ) BARES
                    ON FRECUENTAN.ID_BAR = BARES.ID
                WHERE PRESUPUESTO_BAR = 'Alto'
                GROUP BY BEBEDORES.ID, PRESUPUESTO_BAR
            ) ALTO
            ON ALTO.ID = BARES.ID
            FULL OUTER JOIN (
                SELECT BEBEDORES.ID, COUNT(PRESUPUESTO_BAR) AS BARES_MEDIOS
                FROM BEBEDORES
                    INNER JOIN FRECUENTAN
                    ON BEBEDORES.ID = FRECUENTAN.ID_BEBEDOR
                    INNER JOIN (
                        SELECT ID, PRESUPUESTO AS PRESUPUESTO_BAR
                        FROM BARES
                    ) BARES
                    ON FRECUENTAN.ID_BAR = BARES.ID
                WHERE PRESUPUESTO_BAR = 'Medio'
                GROUP BY BEBEDORES.ID, PRESUPUESTO_BAR
            ) MEDIO
            ON MEDIO.ID = BARES.ID
            FULL OUTER JOIN (
                SELECT BEBEDORES.ID, COUNT(PRESUPUESTO_BAR) AS BARES_BAJOS
                FROM BEBEDORES
                    INNER JOIN FRECUENTAN
                    ON BEBEDORES.ID = FRECUENTAN.ID_BEBEDOR
                    INNER JOIN (
                        SELECT ID, PRESUPUESTO AS PRESUPUESTO_BAR
                        FROM BARES
                    ) BARES
                    ON FRECUENTAN.ID_BAR = BARES.ID
                WHERE PRESUPUESTO_BAR = 'Bajo'
                GROUP BY BEBEDORES.ID, PRESUPUESTO_BAR
            ) BAJO
            ON BAJO.ID = BARES.ID
        GROUP BY BEBEDORES.ID, BEBEDORES.NOMBRE, BEBEDORES.CIUDAD, BEBEDORES.PRESUPUESTO, PRESUPUESTO_BAR, BARES_ALTOS, BARES_MEDIOS, BARES_BAJOS
    )
WHERE PRESUPUESTO_BEBEDOR = 'Medio'
    AND BARES_BAJOS IS NULL
    AND BARES_MEDIOS IS NULL
    OR PRESUPUESTO_BEBEDOR = 'Bajo'
    AND BARES_BAJOS IS NULL
GROUP BY ID, NOMBRE_BEBEDOR, CIUDAD, PRESUPUESTO_BEBEDOR, BARES_ALTOS, BARES_MEDIOS, BARES_BAJOS
ORDER BY CIUDAD ASC, PRESUPUESTO_BEBEDOR ASC, NOMBRE_BEBEDOR ASC;