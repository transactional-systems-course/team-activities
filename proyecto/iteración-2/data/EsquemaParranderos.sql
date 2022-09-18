--- Sentencias SQL para la creaci�n del esquema de parranderos
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada

-- Creaci�n del secuenciador
create sequence parranderos_sequence;

-- Creaaci�n de la tabla TIPOBEBIDA y especificaci�n de sus restricciones
CREATE TABLE A_TIPOBEBIDA
   (ID NUMBER, 
	NOMBRE VARCHAR2(255 BYTE), 
	CONSTRAINT A_TIPOBEBIDA_PK PRIMARY KEY (ID));
	
ALTER TABLE A_TIPOBEBIDA
	ADD CONSTRAINT UN_TIPOBEB_NOMBRE 
	UNIQUE (NOMBRE)
ENABLE;

-- Creaaci�n de la tabla BEBIDA y especificaci�n de sus restricciones
CREATE TABLE A_BEBIDA 
   (ID NUMBER, 
	NOMBRE VARCHAR2(20 BYTE), 
	IDTIPOBEBIDA NUMBER, 
	GRADOALCOHOL NUMBER, 
	CONSTRAINT A_BEBIDA_PK PRIMARY KEY (ID));
	
ALTER TABLE A_BEBIDA
ADD CONSTRAINT fk_a_tipobebida
    FOREIGN KEY (idtipobebida)
    REFERENCES a_tipobebida(id)
ENABLE;

-- Creaaci�n de la tabla BAR y especificaci�n de sus restricciones
CREATE TABLE A_BAR
   (ID NUMBER, 
	CANTSEDES NUMBER(3,0), 
	CIUDAD VARCHAR2(255 BYTE), 
	NOMBRE VARCHAR2(255 BYTE), 
	PRESUPUESTO VARCHAR2(255 BYTE), 
	CONSTRAINT A_BAR_PK PRIMARY KEY (ID));
	 
ALTER TABLE A_BAR
	ADD CONSTRAINT CK_BAR_PPTO 
	CHECK (PRESUPUESTO IN ('Alto', 'Medio', 'Bajo'))
ENABLE;

-- Creaaci�n de la tabla BEBEDOR y especificaci�n de sus restricciones
CREATE TABLE A_BEBEDOR
   (ID NUMBER, 
	CIUDAD VARCHAR2(255 BYTE), 
	NOMBRE VARCHAR2(255 BYTE), 
	PRESUPUESTO VARCHAR2(255 BYTE), 
	CONSTRAINT A_BEBEDOR_PK PRIMARY KEY (ID));

ALTER TABLE A_BEBEDOR
	ADD CONSTRAINT CK_BDOR_PPTO 
	CHECK (PRESUPUESTO IN ('Alto', 'Medio', 'Bajo'))
ENABLE;

-- Creaaci�n de la tabla GUSTAN y especificaci�n de sus restricciones
CREATE TABLE A_GUSTAN
(
  IDBEBEDOR NUMBER,
  IDBEBIDA NUMBER,
  CONSTRAINT A_GUSTAN_PK PRIMARY KEY (IDBEBEDOR, IDBEBIDA));

ALTER TABLE A_GUSTAN
ADD CONSTRAINT fk_g_bebedor
    FOREIGN KEY (idbebedor)
    REFERENCES a_bebedor(id)
ENABLE;
    
ALTER TABLE A_GUSTAN
ADD CONSTRAINT fk_g_bebida
    FOREIGN KEY (idbebida)
    REFERENCES a_bebida(id)
ENABLE;

-- Creaaci�n de la tabla SIRVEN y especificaci�n de sus restricciones
CREATE TABLE A_SIRVEN 
(
  IDBAR NUMBER,
  IDBEBIDA NUMBER,
  HORARIO VARCHAR2(20 BYTE), 
  CONSTRAINT A_SIRVEN_PK PRIMARY KEY (IDBAR, IDBEBIDA, HORARIO));

ALTER TABLE A_SIRVEN
ADD CONSTRAINT fk_s_bar
    FOREIGN KEY (idbar)
    REFERENCES a_bar(id)
ENABLE;
    
ALTER TABLE A_SIRVEN
ADD CONSTRAINT fk_s_bebida
    FOREIGN KEY (idbebida)
    REFERENCES a_bebida(id)
ENABLE;
    
ALTER TABLE A_SIRVEN
ADD CONSTRAINT CK_S_HORARIO 
	CHECK (horario IN ('diurno', 'nocturno', 'todos'))
ENABLE;

-- Creaaci�n de la tabla VISITAN y especificaci�n de sus restricciones
CREATE TABLE A_VISITAN 
(
  IDBAR NUMBER,
  IDBEBEDOR NUMBER,
  HORARIO VARCHAR2(20 BYTE), 
  FECHAVISITA DATE, 
  CONSTRAINT A_VISITAN_PK PRIMARY KEY (IDBAR, IDBEBEDOR, FECHAVISITA, HORARIO));

ALTER TABLE A_VISITAN
ADD CONSTRAINT fk_v_bebedor
    FOREIGN KEY (idbebedor)
    REFERENCES a_bebedor(id)
ENABLE;
    
ALTER TABLE A_VISITAN
ADD CONSTRAINT fk_v_bar
    FOREIGN KEY (idbar)
    REFERENCES a_bar(id)
ENABLE;
    
ALTER TABLE A_VISITAN
ADD CONSTRAINT CK_V_HORARIO
	CHECK (horario IN ('diurno', 'nocturno', 'todos'))
ENABLE;

COMMIT;
