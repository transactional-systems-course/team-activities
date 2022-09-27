ALTER SESSION SET CURRENT_SCHEMA = PARRANDEROS;

SELECT bar.CIUDAD, bar.BARES_ALTOS AS BARES_ALTOS , bebedor.BEBEDORES_MEDIOS AS BEBEDORES_MEDIOS FROM 
(SELECT CIUDAD, COUNT(DISTINCT ID) AS BARES_ALTOS FROM BARES WHERE PRESUPUESTO = 'Alto' GROUP BY CIUDAD) bar
LEFT JOIN
(SELECT CIUDAD, COUNT(DISTINCT ID) AS BEBEDORES_MEDIOS FROM BEBEDORES WHERE PRESUPUESTO = 'Medio' GROUP BY CIUDAD) bebedor
ON bebedor.CIUDAD = bar.ciudad;
