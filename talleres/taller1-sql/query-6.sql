ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT CONTEO.CIUDAD, CONTEO.NOMBRE, CONTEO.GRADO_ALCOHOL, CONTEO.NUM_BEBEDORES_BEBIDA
FROM (
        SELECT CIUDAD, BEBIDAS.NOMBRE, ID_BEBIDA, GRADO_ALCOHOL, COUNT(*) AS NUM_BEBEDORES_BEBIDA
        FROM GUSTAN
            INNER JOIN BEBIDAS
            ON BEBIDAS.ID = GUSTAN.ID_BEBIDA
            INNER JOIN BEBEDORES
            ON BEBEDORES.ID = GUSTAN.ID_BEBEDOR
        WHERE BEBEDORES.PRESUPUESTO = 'Alto'
        GROUP BY CIUDAD, BEBIDAS.NOMBRE, ID_BEBIDA, GRADO_ALCOHOL
    ) CONTEO
WHERE CONTEO.ID_BEBIDA IN (
        SELECT CIUDAD_BEBIDAS.ID_BEBIDA
        FROM GUSTAN
            INNER JOIN BEBEDORES
            ON GUSTAN.ID_BEBEDOR = BEBEDORES.ID
            INNER JOIN (
                SELECT BEBIDAS.NOMBRE, BEBIDAS.GRADO_ALCOHOL, BARES.CIUDAD, ID_BEBIDA
                FROM SIRVEN
                    INNER JOIN BEBIDAS
                    ON SIRVEN.ID_BEBIDA = BEBIDAS.ID
                    INNER JOIN BARES
                    ON SIRVEN.ID_BAR = BARES.ID
                WHERE BARES.PRESUPUESTO = 'Alto'
                    AND GRADO_ALCOHOL BETWEEN 15 AND 25
            ) CIUDAD_BEBIDAS
            ON GUSTAN.ID_BEBIDA = CIUDAD_BEBIDAS.ID_BEBIDA
        WHERE BEBEDORES.PRESUPUESTO = 'Alto'
            AND BEBEDORES.ID IN (
                SELECT ID_BEBEDOR
                FROM GUSTAN
                    INNER JOIN BEBEDORES
                    ON GUSTAN.ID_BEBEDOR = BEBEDORES.ID
                    INNER JOIN (
                        SELECT BEBIDAS.NOMBRE, BEBIDAS.GRADO_ALCOHOL, BARES.CIUDAD, ID_BEBIDA
                        FROM SIRVEN
                            INNER JOIN BEBIDAS
                            ON SIRVEN.ID_BEBIDA = BEBIDAS.ID
                            INNER JOIN BARES
                            ON SIRVEN.ID_BAR = BARES.ID
                        WHERE BARES.PRESUPUESTO = 'Alto'
                            AND GRADO_ALCOHOL BETWEEN 15 AND 25
                    ) CIUDAD_BEBIDAS
                    ON GUSTAN.ID_BEBIDA = CIUDAD_BEBIDAS.ID_BEBIDA
                WHERE BEBEDORES.PRESUPUESTO = 'Alto'
                GROUP BY ID_BEBEDOR
                HAVING COUNT(ID_BEBEDOR) > 2
            )
    )
ORDER BY CIUDAD ASC, NOMBRE ASC;