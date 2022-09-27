ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT table_name FROM dba_tables WHERE owner='PARRANDEROS';

SELECT CIUDAD FROM BARES;

SELECT bar.CIUDAD, bar.BARES_ALTOS AS BARES_ALTOS , bebedor.BEBEDORES_MEDIOS AS BEBEDORES_MEDIOS FROM 
(SELECT CIUDAD, COUNT(DISTINCT ID) AS BARES_ALTOS FROM BARES WHERE PRESUPUESTO = 'Alto' GROUP BY CIUDAD) bar
LEFT JOIN
(SELECT CIUDAD, COUNT(DISTINCT ID) AS BEBEDORES_MEDIOS FROM BEBEDORES WHERE PRESUPUESTO = 'Medio' GROUP BY CIUDAD) bebedor
ON bebedor.CIUDAD = bar.ciudad;


SELECT NOMBRE, PRESUPUESTO, COUNT(ID_BAR) AS INSTANCIAS FROM 
(SELECT * FROM BARES) bar
FULL OUTER JOIN
(SELECT * FROM FRECUENTAN)frec
ON bar.ID = frec.ID_BAR
GROUP BY ID_BAR, NOMBRE, PRESUPUESTO
ORDER BY INSTANCIAS DESC, NOMBRE
FETCH FIRST 10 ROWS ONLY;

SELECT ID_BEBEDOR, PRESUPUESTO_BEBEDOR, NOMBRE_BEBEDOR, COUNT(ID_BEBEDOR) FROM 
(((SELECT ID, NOMBRE AS NOMBRE_BEBEDOR, CIUDAD, PRESUPUESTO AS PRESUPUESTO_BEBEDOR FROM BEBEDORES WHERE PRESUPUESTO = 'Medio' OR PRESUPUESTO = 'Bajo') bebe
INNER JOIN 
(SELECT * FROM FRECUENTAN) frec
ON bebe.ID = frec.ID_BEBEDOR
INNER JOIN
(SELECT ID, NOMBRE, PRESUPUESTO AS PRESUPUESTO_BAR FROM BARES WHERE PRESUPUESTO = 'Alto')bares
ON frec.ID_BAR = bares.ID)
)
GROUP BY ID_BEBEDOR, PRESUPUESTO_BEBEDOR, NOMBRE_BEBEDOR
ORDER BY ID_BEBEDOR;