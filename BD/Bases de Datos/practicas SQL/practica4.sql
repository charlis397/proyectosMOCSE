create database Cinemex;
USE cinemex;

1.

 CREATE TABLE Cinemex(
 idCinemex int NOT NULL PRIMARY KEY,
 nombre varchar(45) NOT NULL,
 direccion varchar(45) NOT NULL,
 tel varchar(20),
 email  varchar(50)
 );

CREATE TABLE Gerente(
idGerente INT NOT NULL PRIMARY KEY,
nombre VARCHAR(45) NOT NULL,
turno VARCHAR(15) NOT NULL,
noCel INT,
salario DOUBLE NOT NULL,
Cinemex_idCinemex INT NOT NULL,
FOREIGN KEY(Cinemex_idCinemex) REFERENCES Cinemex(idCinemex)
);

 CREATE TABLE Empleado(
 idEmpleado VARCHAR(20) NOT NULL PRIMARY KEY,
 nombre VARCHAR(50) NOT NULL,
 direccion VARCHAR(100) NOT NULL,
 tel VARCHAR(20),
 sex VARCHAR(1)
 );

CREATE TABLE CinemexEmpleado(
Cinemex_idCinemex INT NOT NULL,
Empleado_idEmpleado VARCHAR(20) NOT NULL,
PRIMARY KEY(Cinemex_idCinemex, Empleado_idEmpleado),
FOREIGN KEY(Cinemex_idCinemex) REFERENCES Cinemex(idCinemex) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(Empleado_idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE ON UPDATE CASCADE
);

2.

ALTER TABLE Empleado ADD COLUMN
salario DOUBLE;

ALTER TABLE Empleado ADD COLUMN
correo VARCHAR(50);

3.

 ALTER TABLE Gerente MODIFY noCel VARCHAR(30);

4.

ALTER TABLE empleado rename as asociado;

5.

ALTER TABLE Asociado MODIFY direccion VARCHAR(150);

6.

 SHOW CREATE TABLE Gerente;

 ALTER TABLE Gerente DROP FOREIGN KEY gerente_ibfk_1;

SHOW CREATE TABLE Cinemex_Empleado;

ALTER TABLE CinemexEmpleado DROP FOREIGN KEY cinemexempleado_ibfk_1;

ALTER TABLE Cinemex DROP PRIMARY KEY;

ALTER TABLE Cinemex ADD PRIMARY KEY(idCinemex, Nombre);

7.

CREATE TABLE Cartelera(
idCartelera INT NOT NULL PRIMARY KEY,
nombre VARCHAR(50) NOT NULL,
fechaInicio VARCHAR(20) NOT NULL,
fechaFin VARCHAR(20) NOT NULL,
clasificacion VARCHAR(30) NOT NULL,
id_CinemexCartelera INT NOT NULL,
nombreCinemexCartelera VARCHAR(45) NOT NULL,
FOREIGN KEY(id_CinemexCartelera, nombreCinemexCartelera) REFERENCES Cinemex(idCinemex,nombre)
);

