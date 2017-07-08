DROP DATABASE IF EXISTS BiosSecurity;

CREATE DATABASE BiosSecurity;

USE BiosSecurity;

# --------- CREACION DE TABLAS --------- #

CREATE TABLE empleados(
	cedula BIGINT PRIMARY KEY,
	clave VARCHAR(10) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	fechaIngreso DATE NOT NULL,
	sueldo DOUBLE
);

CREATE TABLE administrativos(
	cedula BIGINT PRIMARY KEY,
    FOREIGN KEY(cedula) REFERENCES empleados(cedula)
);

CREATE TABLE cobradores(
	cedula BIGINT PRIMARY KEY,
    tipoTransporte VARCHAR(30) DEFAULT "No tiene",
    FOREIGN KEY(cedula) REFERENCES empleados(cedula)
);

CREATE TABLE tecnicos(
	cedula BIGINT PRIMARY KEY,
    alarmas BIT DEFAULT 0,
    camaras BIT DEFAULT 0,
    FOREIGN KEY(cedula) REFERENCES empleados(cedula)
);

CREATE TABLE dispositivos(
	numeroInventario BIGINT PRIMARY KEY,
    descripcion VARCHAR(300) NOT NULL
);

CREATE TABLE alarmas(
	numeroInventario BIGINT PRIMARY KEY,
    FOREIGN KEY(numeroInventario) REFERENCES dispositivos(numeroInventario)
);

CREATE TABLE camaras(
	numeroInventario BIGINT PRIMARY KEY,
    interior BIT DEFAULT 0,
    FOREIGN KEY(numeroInventario) REFERENCES dispositivos(numeroInventario)
);

CREATE TABLE clientes(
	cedula BIGINT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL,
    direccionCobro VARCHAR(255) NOT NULL,
	barrioCobro VARCHAR(30) NOT NULL,
    telefono BIGINT NOT NULL
);

CREATE TABLE propiedades(
	numero INT NOT NULL,
    tipoPropiedad VARCHAR(30) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    cedula BIGINT NOT NULL,
    FOREIGN KEY(cedula) REFERENCES clientes(cedula),
    PRIMARY KEY(numero, cedula)
);

CREATE TABLE servicios(
	numero INT PRIMARY KEY AUTO_INCREMENT,
	cedulaCliente BIGINT, -- Guardar cedula de cliente en cada servicio porque el número de propiedad se puede repetir
    numeroPropiedad INT NOT NULL,
    fechaContratacion DATE NOT NULL,
    monitoreo BIT DEFAULT 0,
    eliminado BIT DEFAULT 0,
	FOREIGN KEY(cedulaCliente) REFERENCES clientes(cedula),
    FOREIGN KEY(numeroPropiedad) REFERENCES propiedades(numero)
);

CREATE TABLE serviciosAlarma(
	numero INT PRIMARY KEY,
    codigoAnulacion INT NOT NULL,
    FOREIGN KEY(numero) REFERENCES servicios(numero)
);

CREATE TABLE serviciosVideo(
	numero INT PRIMARY KEY,
    terminalGrabacion BIT DEFAULT 0,
    FOREIGN KEY(numero) REFERENCES servicios(numero)
);

CREATE TABLE listaAlarmas(
	numeroServicio INT NOT NULL,
    numeroAlarma BIGINT NOT NULL,
    cedulaTecnico BIGINT NOT NULL,
    PRIMARY KEY(numeroServicio, numeroAlarma),
    FOREIGN KEY(numeroServicio) REFERENCES servicios(numero),
    FOREIGN KEY(numeroAlarma) REFERENCES alarmas(numeroInventario),
    FOREIGN KEY(cedulaTecnico) REFERENCES tecnicos(cedula)
);

CREATE TABLE listaCamaras(
	numeroServicio INT NOT NULL,
    numeroCamara BIGINT NOT NULL,
    cedulaTecnico BIGINT NOT NULL,
    PRIMARY KEY(numeroServicio, numeroCamara),
    FOREIGN KEY(numeroServicio) REFERENCES servicios(numero),
    FOREIGN KEY(numeroCamara) REFERENCES camaras(numeroInventario),
    FOREIGN KEY(cedulaTecnico) REFERENCES tecnicos(cedula)
);

CREATE TABLE recibos(
	idRecibo INT PRIMARY KEY AUTO_INCREMENT,
	fechaCobro DATE NOT NULL,
    cedulaCliente BIGINT NOT NULL,
    cedulaCobrador BIGINT,
    totalPagar DOUBLE,
    FOREIGN KEY(cedulaCliente) REFERENCES clientes(cedula),
    FOREIGN KEY(cedulaCobrador) REFERENCES cobradores(cedula)
);

CREATE TABLE lineasRecibo(
	idRecibo INT,
    numeroServicio INT,
    precio DOUBLE,
    PRIMARY KEY(idRecibo, numeroServicio),
    FOREIGN KEY(numeroServicio) REFERENCES servicios(numero),
    FOREIGN KEY(idRecibo) REFERENCES recibos(idRecibo)
);

# --------- VISTAS --------- #

CREATE VIEW listaEmpleados AS
	SELECT e.cedula, e.nombre, e.fechaIngreso, e.sueldo, null AS 'tipoTransporte', null AS 'alarmas', null AS 'camaras', 'A' AS 'tipo' FROM administrativos a INNER JOIN empleados e ON a.cedula = e.cedula
	UNION ALL
	SELECT e.cedula, e.nombre, e.fechaIngreso, e.sueldo, c.tipoTransporte, null, null, 'C' AS 'tipo' FROM cobradores c INNER JOIN empleados e ON c.cedula = e.cedula
	UNION ALL
	SELECT e.cedula, e.nombre, e.fechaIngreso, e.sueldo, null, t.alarmas, t.camaras, 'T' AS 'tipo' FROM tecnicos t INNER JOIN empleados e ON t.cedula = e.cedula;

# --------- DATOS DE PRUEBA --------- #

USE BiosSecurity;
INSERT INTO empleados VALUES(4198767, "adm1n", "Ernesto Goycoechea", "2010-02-05", 30000);
INSERT INTO empleados VALUES(4567845, "adm2n", "Jorge López", "2012-05-10", 22000);
INSERT INTO empleados VALUES(3547458, "adm3n", "Eduardo Hernandez", "2002-10-01", 60000);
INSERT INTO empleados VALUES(2456754, "adm4n", "Maria Gimenez", "2015-01-02", 20000);
INSERT INTO empleados VALUES(3457913, "adm5n", "Laura Pacheco", "2010-05-04", 27500);
INSERT INTO empleados VALUES(2645453, "adm6n", "Mauro Fernandez", "2005-02-05", 35000);

INSERT INTO administrativos VALUES(4198767);
INSERT INTO administrativos VALUES(4567845);
INSERT INTO cobradores VALUES(3547458, 'Moto');
INSERT INTO cobradores VALUES(2456754, 'Auto');
INSERT INTO tecnicos VALUES(3457913, 1, 1);
INSERT INTO tecnicos VALUES(2645453, 0, 1);

INSERT INTO clientes VALUES(4523684,"Juan Perez","Isabela 3452","Brazo Oriental",099562314);
INSERT INTO clientes VALUES(4963125,"Maria del Carmen","Av Rio Negro MJ4","Pinar Sur",098564852);
INSERT INTO clientes VALUES(2563487,"Jose Cardenal","Paysandu 1414","Centro",094568754);

INSERT INTO propiedades VALUES(1,"local","Ejido 2314",4523684);
INSERT INTO propiedades VALUES(2,"Apartamento","Rondeau 1813",4523684);
INSERT INTO propiedades VALUES(1,"Casa","Lima 2345",4963125);

INSERT INTO servicios VALUES(DEFAULT,4523684,1,"2016/08/08",1,DEFAULT);
INSERT INTO servicios VALUES(DEFAULT,4523684,1,"2016/08/08",1,DEFAULT);
INSERT INTO servicios VALUES(DEFAULT,4523684,2,"2017/01/11",0,DEFAULT);
INSERT INTO servicios VALUES(DEFAULT,4963125,1,"2016/10/04",1,DEFAULT);

INSERT INTO serviciosAlarma VALUES(1,5023);
INSERT INTO serviciosVideo VALUES(2,1);
INSERT INTO serviciosAlarma VALUES(3,1203);
INSERT INTO serviciosVideo VALUES(4,0);

# --------- CREACION DE PROCEDIMIENTOS Y FUNCIONES --------- #

# ---- ALTAS ---- #

DELIMITER //

CREATE PROCEDURE altaAdministrativo(pCedula BIGINT, pClave VARCHAR(10), pNombre VARCHAR(50), pFechaIngreso DATE, pSueldo DOUBLE, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado ya existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al agregar el empleado.";
    
    INSERT INTO empleados VALUES(pCedula, pClave, pNombre, pFechaIngreso, pSueldo);
    
    SET mensajeError = "Error al agregar el administrativo.";
    
    INSERT INTO administrativos VALUES(pCedula);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE altaCobrador(pCedula BIGINT, pClave VARCHAR(10), pNombre VARCHAR(50), pFechaIngreso DATE, pSueldo DOUBLE, pTipoTransporte VARCHAR(30), OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado ya existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al agregar el empleado.";
    
    INSERT INTO empleados VALUES(pCedula, pClave, pNombre, pFechaIngreso, pSueldo);
    
    SET mensajeError = "Error al agregar el cobrador.";
    
    INSERT INTO cobradores VALUES(pCedula, pTipoTransporte);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE altaTecnico(pCedula BIGINT, pClave VARCHAR(10), pNombre VARCHAR(50), pFechaIngreso DATE, pSueldo DOUBLE, pAlarmas BIT, pCamaras BIT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado ya existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al agregar el empleado.";
    
    INSERT INTO empleados VALUES(pCedula, pClave, pNombre, pFechaIngreso, pSueldo);
    
    SET mensajeError = "Error al agregar el técnico.";
    
    INSERT INTO tecnicos VALUES(pCedula, pAlarmas, pCamaras);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

-- Agregar solamente el número de inventario en el alta ya que los otros datos los agrega el técnico
CREATE PROCEDURE altaCamara(pNumeroInventario BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF EXISTS (SELECT * FROM dispositivos WHERE numeroInventario = pNumeroInventario) THEN
		SET pError = "El dispositivo ya existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al agregar el dispositivo.";
    
    INSERT INTO dispositivos (numeroInventario) VALUES(pNumeroInventario);
    
    SET mensajeError = "Error al agregar la cámara.";
    
    INSERT INTO camaras (numeroInventario) VALUES(pNumeroInventario);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

-- Agregar solamente el número de inventario en el alta ya que los otros datos los agrega el técnico
CREATE PROCEDURE altaAlarma(pNumeroInventario BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF EXISTS (SELECT * FROM dispositivos WHERE numeroInventario = pNumeroInventario) THEN
		SET pError = "El dispositivo ya existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al agregar el dispositivo.";
    
    INSERT INTO dispositivos (numeroInventario) VALUES(pNumeroInventario);
    
    SET mensajeError = "Error al agregar la alarma.";
    
    INSERT INTO alarmas (numeroInventario) VALUES(pNumeroInventario);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE altaCliente(pCedula BIGINT, pNombre VARCHAR(50), pDireccionCobro VARCHAR(255), pBarrioCobro VARCHAR(30), pTelefono BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    
    IF EXISTS (SELECT * FROM clientes WHERE cedula = pCedula) THEN -- Un empleado puede ser cliente?
		SET pError = "El cliente ya existe.";
		LEAVE cuerpo;
	END IF;
    
    START TRANSACTION;
    
    SET pError = "Error al agregar el cliente.";
    
    INSERT INTO clientes VALUES(pCedula, pNombre, pDireccionCobro, pBarrioCobro, pTelefono);

    SET pError = "";
   
END//

CREATE PROCEDURE altaPropiedad(pTipoPropiedad VARCHAR(30), pDireccion VARCHAR(255), pCedula BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE numeroPropiedad INT;
    
    IF NOT EXISTS (SELECT * FROM clientes WHERE cedula = pCedula) THEN
		SET pError = "El cliente no existe.";
		LEAVE cuerpo;
	END IF;
    
    IF EXISTS (SELECT * FROM propiedades WHERE direccion = pDireccion) THEN
		SET pError = "La propiedad ya existe.";
		LEAVE cuerpo;
	END IF;
    
    SET pError = "Error al agregar la propiedad.";
    SET numeroPropiedad = (SELECT IFNULL((SELECT MAX(numero) FROM propiedades WHERE cedula = pCedula), 0)) + 1; -- Si no hay propiedades para el cliente devuelve 0 en vez de NULL
    
    INSERT INTO propiedades VALUES(numeroPropiedad, pTipoPropiedad, pDireccion, pCedula);

    SET pError = "";

END//

-- Si tiene baja lógica, se saca o se agrega un nuevo servicio?
CREATE PROCEDURE altaServicioAlarma(pNumeroPropiedad INT, pCedulaCliente BIGINT, pFechaContratacion DATE, pMonitoreo BIT, pCodigoAnulacion INT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE numeroServicio INT;
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF EXISTS (SELECT * FROM servicios s INNER JOIN serviciosAlarma sa ON s.numero = sa.numero WHERE s.numeroPropiedad = pNumeroPropiedad AND s.cedulaCliente = pCedulaCliente AND eliminado = 0) THEN
		SET pError = "La propiedad ya tiene contratado un servicio de alarmas.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al agregar el servicio.";
    
    INSERT INTO servicios (cedulaCliente, numeroPropiedad, fechaContratacion, monitoreo) VALUES(pNumeroPropiedad, pCedulaCliente, pFechaContratacion, pMonitoreo);
    
    SET mensajeError = "Error al agregar la servicio de alarmas.";
    SET numeroServicio = (SELECT MAX(numero) FROM servicios);

    INSERT INTO serviciosAlarma VALUES(numeroServicio, pCodigoAnulacion);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

-- Agregar alarma al servicio correspondiente
CREATE PROCEDURE registrarAlarma(pNumeroServicio INT, pNumeroAlarma BIGINT, pCedulaTecnico BIGINT, pDescripcion VARCHAR(300), OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;

    IF NOT EXISTS (SELECT * FROM alarmas WHERE numeroInventario = pNumeroAlarma) THEN
		SET pError = "La alarma no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM serviciosAlarma WHERE numero = pNumeroServicio) THEN
		SET pError = "El servicio no existe o no es servicio de alarma.";
		LEAVE cuerpo;
	END IF;

    IF EXISTS (SELECT * FROM listaAlarmas WHERE numeroAlarma = pNumeroAlarma) THEN
		SET pError = "La alarma ya está siendo utilizada en un servicio.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al ingresar la descripción del dispositivo.";
    
    UPDATE dispositivos SET
    descripcion = pDescripcion WHERE
    numeroInventario = pNumeroAlarma;
    
    SET mensajeError = "Error al registrar la alarma al servicio.";

    INSERT INTO listaAlarmas VALUES(pNumeroServicio, pNumeroAlarma, pCedulaTecnico);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE altaServicioVideo(pNumeroPropiedad INT, pCedulaCliente BIGINT, pFechaContratacion DATE, pMonitoreo BIT, pTerminalGrabacion BIT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE numeroServicio INT;
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF EXISTS (SELECT * FROM servicios s INNER JOIN serviciosVideo sv ON s.numero = sv.numero WHERE s.numeroPropiedad = pNumeroPropiedad AND s.cedulaCliente = pCedulaCliente AND eliminado = 0) THEN
		SET pError = "La propiedad ya tiene contratado un servicio de videovigilancia.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al agregar el servicio.";
    
    INSERT INTO servicios (cedulaCliente, numeroPropiedad, fechaContratacion, monitoreo) VALUES(pNumeroPropiedad, pCedulaCliente, pFechaContratacion, pMonitoreo);
    
    SET mensajeError = "Error al agregar la servicio de videovigilancia.";
    SET numeroServicio = (SELECT MAX(numero) FROM servicios);

    INSERT INTO serviciosVideo VALUES(numeroServicio, pTerminalGrabacion);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

-- Agregar cámara al servicio correspondiente
CREATE PROCEDURE registrarCamara(pNumeroServicio INT, pNumeroCamara BIGINT, pCedulaTecnico BIGINT, pDescripcion VARCHAR(300), pInterior BIT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;

    IF NOT EXISTS (SELECT * FROM camaras WHERE numeroInventario = pNumeroCamara) THEN
		SET pError = "La cámara no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM serviciosCamara WHERE numero = pNumeroServicio) THEN
		SET pError = "El servicio no existe o no es servicio de cámara.";
		LEAVE cuerpo;
	END IF;
    
    IF EXISTS (SELECT * FROM listaCamaras WHERE numeroCamara = pNumeroCamara) THEN
		SET pError = "La cámara ya está siendo utilizada en un servicio.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al ingresar la descripción del dispositivo.";
    
    UPDATE dispositivos SET
    descripcion = pDescripcion WHERE
    numeroInventario = pNumeroCamara;

    SET mensajeError = "Error al ingresar la descripción de la cámara.";
    
    UPDATE camaras SET
    interior = pInterior WHERE
    numeroInventario = pNumeroCamara;
    
    SET mensajeError = "Error al registrar la cámara al servicio.";

    INSERT INTO listaCamaras VALUES(pNumeroServicio, pNumeroCamara, pCedulaTecnico);

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE generarRecibo(pFechaCobro DATE, pCedulaCliente BIGINT, pTotalPagar DOUBLE, OUT pIdRecibo INT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);

    IF NOT EXISTS (SELECT * FROM servicios WHERE cedulaCliente = pCedulaCliente AND eliminado = 0) THEN
		SET pError = "El cliente no tiene servicios activos.";
		LEAVE cuerpo;
	END IF;
    
    IF EXISTS (SELECT * FROM recibos WHERE cedulaCliente = pCedulaCliente AND MONTH(fechaCobro) = MONTH(CURRENT_DATE()) AND YEAR(fechaCobro) = YEAR(CURRENT_DATE())) THEN
		SET pError = "El cliente ya tiene recibos correspondientes a este mes.";
		LEAVE cuerpo;
	END IF;
    
    SET pError = "Error al agregar el recibo.";
    
    INSERT INTO recibos (fechaCobro, cedulaCliente, totalPagar) VALUES(pFechaCobro, pCedulaCliente, pTotalPagar);

    SET pIdRecibo = (SELECT MAX(idRecibo) FROM recibos);

    SET pError = "";

END//

CREATE PROCEDURE agregarLineaRecibo(pIdRecibo INT, pNumeroServicio INT, pPrecio DOUBLE, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);

    IF NOT EXISTS (SELECT * FROM recibos WHERE idRecibo = pIdRecibo) THEN
		SET pError = "El recibo no existe.";
		LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS (SELECT * FROM servicios WHERE numero = pNumeroServicio AND eliminado = 0) THEN
		SET pError = "El servicio no existe o se dio de baja.";
		LEAVE cuerpo;
	END IF;
    
    SET pError = "Error al agregar la línea al recibo.";
    
    INSERT INTO lineasRecibo VALUES(pIdRecibo, pNumeroServicio, pPrecio);

    SET pError = "";

END//

CREATE PROCEDURE cobrarRecibo(pIdRecibo INT, pCedulaCobrador BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);

    IF NOT EXISTS (SELECT * FROM recibos WHERE idRecibo = pIdRecibo) THEN
		SET pError = "El recibo no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM empleados WHERE cedula = pCedulaCobrador) THEN
		SET pError = "El empleado no existe.";
		LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS (SELECT * FROM cobradores WHERE cedula = pCedulaCobrador) THEN
		SET pError = "El empleado no es cobrador.";
		LEAVE cuerpo;
	END IF;
    
    SET pError = "Error al actualizar el recibo.";
    
    UPDATE recibos SET cedulaCobrador = pCedulaCobrador WHERE idRecibo = pIdRecibo;

    SET pError = "";

END//

# ---- BAJAS ---- #

CREATE PROCEDURE bajaAdministrativo(pCedula BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF NOT EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM administrativos WHERE cedula = pCedula) THEN -- Necesario?
		SET pError = "El administrativo no existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al eliminar el administrativo.";
    
    DELETE FROM administrativos WHERE cedula = pCedula;
    
    SET mensajeError = "Error al eliminar el empleado.";
    
    DELETE FROM empleados WHERE cedula = pCedula;

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE bajaCobrador(pCedula BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF NOT EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM cobradores WHERE cedula = pCedula) THEN -- Necesario?
		SET pError = "El cobrador no existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al eliminar el cobrador.";
    
    DELETE FROM cobradores WHERE cedula = pCedula;
    
    SET mensajeError = "Error al eliminar el empleado.";
    
    DELETE FROM empleados WHERE cedula = pCedula;

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE bajaTecnico(pCedula BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF NOT EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM tecnicos WHERE cedula = pCedula) THEN
		SET pError = "El técnico no existe.";
		LEAVE cuerpo;
	END IF;
    
    IF EXISTS (SELECT * FROM listaAlarmas WHERE cedulaTecnico = pCedula) THEN
		SET pError = "No se puede eliminar al técnico, existe una alarma instalada vinculada al mismo.";
		LEAVE cuerpo;
	END IF;
    
    IF EXISTS (SELECT * FROM listaCamaras WHERE cedulaTecnico = pCedula) THEN
		SET pError = "No se puede eliminar al técnico, existe una cámara instalada vinculada al mismo.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al eliminar el técnico.";
    
    DELETE FROM tecnicos WHERE cedula = pCedula;
    
    SET mensajeError = "Error al eliminar el empleado.";
    
    DELETE FROM empleados WHERE cedula = pCedula;

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE bajaAlarma(pNumeroInventario BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;

    IF EXISTS (SELECT * FROM listaAlarmas WHERE numeroAlarma = pNumeroInventario) THEN
        SET pError = "La alarma está siendo utilizada.";
		LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS (SELECT * FROM dispositivos WHERE numero = pNumeroInventario) THEN
		SET pError = "El dispositivo no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM alarmas WHERE numero = pNumeroInventario) THEN
		SET pError = "La alarma no existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al eliminar la alarma.";
    
    DELETE FROM alarmas WHERE numeroInventario = pNumeroInventario;
    
    SET mensajeError = "Error al eliminar el dispositivo.";
    
    DELETE FROM dispositivos WHERE numeroInventario = pNumeroInventario;

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE bajaCamara(pNumeroInventario BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;

    IF EXISTS (SELECT * FROM listaCamaras WHERE numeroCamara = pNumeroInventario) THEN
        SET pError = "La cámara está siendo utilizada.";
		LEAVE cuerpo;
	END IF;
    
    IF NOT EXISTS (SELECT * FROM dispositivos WHERE numero = pNumeroInventario) THEN
		SET pError = "El dispositivo no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM camaras WHERE numero = pNumeroInventario) THEN
		SET pError = "La cámara no existe.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al eliminar la cámara.";
    
    DELETE FROM camaras WHERE numeroInventario = pNumeroInventario;
    
    SET mensajeError = "Error al eliminar el dispositivo.";
    
    DELETE FROM dispositivos WHERE numeroInventario = pNumeroInventario;

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE bajaServicioAlarma(pNumero INT, OUT pError VARCHAR(50))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;


	IF NOT EXISTS(SELECT * FROM servicios WHERE numero = pNumero AND eliminado = 0) THEN
		SET pError = "El servicio no existe.";
        LEAVE cuerpo;
	END IF;
    
	SET transaccionActiva = 1;
    
    START TRANSACTION;
    
	SET mensajeError = "Error al liberar las alarmas asociadas al servicio.";
    
    DELETE 
    FROM listaAlarmas
    WHERE numeroServicio = pNumero;

    SET mensajeError = "Error al dar de baja el servicio.";
    
    UPDATE servicios
    SET eliminado = 1
    WHERE numero = pNumero;
    
    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE bajaServicioVideo(pNumero INT, OUT pError VARCHAR(50))
cuerpo:BEGIN

	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    
	BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;


	IF NOT EXISTS(SELECT * FROM servicios WHERE numero = pNumero AND eliminado = 0) THEN
		SET pError = "El servicio no existe.";
        LEAVE cuerpo;
	END IF;
    
	SET transaccionActiva = 1;
    
    START TRANSACTION;
    
	SET mensajeError = "Error al liberar las camaras asociadas al servicio.";
    
    DELETE 
    FROM listaCamaras
    WHERE numeroServicio = pNumero;

    SET mensajeError = "Error al dar de baja el servicio.";
    
    UPDATE servicios
    SET eliminado = 1
    WHERE numero = pNumero;
    
    COMMIT;
    
    SET transaccionActiva = 0;
END//

# ---- MODIFICACIONES ---- #

CREATE PROCEDURE modificarAdministrativo(pCedula BIGINT, pClave VARCHAR(10), pNombre VARCHAR(50), pFechaIngreso DATE, pSueldo DOUBLE, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    
    IF NOT EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM administrativos WHERE cedula = pCedula) THEN
		SET pError = "El empleado no es administrativo.";
		LEAVE cuerpo;
	END IF;
    
    SET mensajeError = "Error al modificar el empleado administrativo.";
    
    UPDATE empleados SET
    clave = pClave, nombre = pNombre, fechaIngreso = pFechaIngreso, sueldo = pSueldo
    WHERE cedula = pCedula;    
    
    SET mensajeError = "";
END//

CREATE PROCEDURE modificarCobrador(pCedula BIGINT, pClave VARCHAR(10), pNombre VARCHAR(50), pFechaIngreso DATE, pSueldo DOUBLE, pTipoTransporte VARCHAR(30), OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF NOT EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM cobradores WHERE cedula = pCedula) THEN
		SET pError = "El empleado no es cobrador.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al modificar el empleado.";
    
    UPDATE empleados SET
    clave = pClave, nombre = pNombre, fechaIngreso = pFechaIngreso, sueldo = pSueldo
    WHERE cedula = pCedula; 
    
    SET mensajeError = "Error al modificar el cobrador.";
    
    UPDATE cobradores SET
    tipoTransporte = pTipoTransporte
    WHERE cedula = pCedula;

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE modificarTecnico(pCedula BIGINT, pClave VARCHAR(10), pNombre VARCHAR(50), pFechaIngreso DATE, pSueldo DOUBLE, pAlarmas BIT, pCamaras BIT, OUT pError VARCHAR(50))
cuerpo:BEGIN
	DECLARE mensajeError VARCHAR(50);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pError = mensajeError;
    END;
    
    IF NOT EXISTS (SELECT * FROM empleados WHERE cedula = pCedula) THEN
		SET pError = "El empleado no existe.";
		LEAVE cuerpo;
	END IF;

    IF NOT EXISTS (SELECT * FROM tecnicos WHERE cedula = pCedula) THEN
		SET pError = "El empleado no es técnico.";
		LEAVE cuerpo;
	END IF;
    
    SET transaccionActiva = 1;
    
    START TRANSACTION;
    
    SET mensajeError = "Error al modificar el empleado.";
    
    UPDATE empleados SET
    clave = pClave, nombre = pNombre, fechaIngreso = pFechaIngreso, sueldo = pSueldo
    WHERE cedula = pCedula; 
    
    SET mensajeError = "Error al modificar el técnico.";
    
    UPDATE tecnicos SET
    alarmas = pAlarmas, camaras = pCamaras
    WHERE cedula = pCedula; 

    COMMIT;
    
    SET transaccionActiva = 0;
END//

CREATE PROCEDURE modificarCliente(pCedula BIGINT, pNombre VARCHAR(50), pDireccionCobro VARCHAR(255), 
																	pBarrioCobro VARCHAR(30), pTelefono BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
    
    IF NOT EXISTS (SELECT * FROM clientes WHERE cedula = pCedula) THEN
		SET pError = "El cliente no existe.";
        LEAVE cuerpo;
	END IF;
    
    SET pError = "Error al modificar el cliente.";
    
    UPDATE clientes 
    SET nombre = pNombre, direccionCobro = pDireccionCobro, barrioCobro = pBarrioCobro, telefono = pTelefono
    WHERE cedula = pCedula;
    
    SET pError = "";
END//

CREATE PROCEDURE modificarPropiedad(pNumero INT, pTipoPropiedad VARCHAR(30), pDireccion VARCHAR(255),
																		pCedula BIGINT, OUT pError VARCHAR(50))
cuerpo:BEGIN
    
    IF NOT EXISTS(SELECT * FROM propiedades WHERE cedula = pCedula AND numero = pNumero) THEN
		SET pError = "El cliente y/o su número de propiedad no existen";
        LEAVE cuerpo;
        END IF;
        
        SET pError = "Error al modificar la propiedad";
        
        UPDATE propiedades
        SET tipoPropiedad = pTipoPropiedad, direccion = pDireccion
        WHERE cedula = pCedula AND numero = pNumero;
        
        SET pError = "";
END//

DELIMITER ;
