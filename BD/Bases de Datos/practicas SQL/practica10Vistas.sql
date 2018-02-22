En el reporte incluir:
¿Qué es una vista ?
(si es un archvo temporal, o si es permanente , para que sirve )

sintaxis 	CREATE VIEW nombreVista as SELECT
llamar una vista SELECT * FROM nombrevista;

CREATE VIEW vista1 as
SELECT nombre AS NOMBRE ,direccion AS DIRECCION FROM sociosc;

CREATE VIEW vista2 as
SELECT idSocio AS SOCIO,nombre AS NOMBRE  FROM sociosc;

CREATE VIEW vista3 as
SELECT nombre AS NOMBRE,tel AS TELEFONO  FROM sociosc;

CREATE VIEW vista4 as
SELECT idProducto AS ID_PRODUCTO,producto AS PRODUCTO  FROM producto;

CREATE VIEW vista5 as
SELECT producto AS PRODUCTO,precioUnitario AS PRECIO  FROM producto;

CREATE VIEW vista6 as
SELECT proveedor.nombre AS PROVEEDOR,producto.producto AS PRODUCTO FROM producto,proveedor WHERE proveedor.idProveedor = producto.idProveedor;

CREATE VIEW vista7 as
SELECT club.nombre AS CLUB, proveedor.nombre AS PROVEEDOR FROM club,proveedor,proveedorsams 
WHERE club.idClub = proveedorsams.idClub AND proveedor.idProveedor=proveedorsams.idProveedor;

CREATE VIEW vista8 as
SELECT club.nombre AS CLUB, estado.nombre AS ESTADO FROM club,estado
WHERE club.idEdo = estado.idEdo;

CREATE VIEW vista9 as
SELECT club.nombre AS CLUB, servicio.nombre AS SERVICIO FROM club,servicio,servicioclub
WHERE club.idClub= servicioclub.idClub AND servicio.idServicio= servicioclub.idServicio;

CREATE VIEW vista10 as
SELECT club.nombre AS CLUB, gerente.nombre AS GERENTE FROM club,gerente
WHERE club.idClub= gerente.idClub;

11
SELECT  DISTINCT estado from vista8;
12
SELECT CLUB FROM vista7 WHERE PROVEEDOR LIKE 'SABRITAS';
14
SELECT SERVICIO,ESTADO FROM vista9,vista8
 WHERE ESTADO LIKE 'PUEBLA' AND vista8.CLUB = vista9.CLUB;
15
SELECT vista10.GERENTE,vista10.CLUB,vista8.ESTADO 
FROM vista10,vista8 
WHERE ESTADO LIKE 'DURANGO' AND vista8.CLUB = vista10.club
ORDER BY vista10.CLUB;
16
SELECT GERENTE,CLUB 
FROM vista10
WHERE GERENTE LIKE '__R%';
17
SELECT CLUB,COUNT(GERENTE) AS GERENTES
FROM vista10 GROUP BY CLUB;
18
SELECT vista8.CLUB,vista8.ESTADO,vista9.SERVICIO 
FROM vista8,vista9
WHERE  (vista9.SERVICIO LIKE 'JOYER%A' OR vista9.SERVICIO LIKE 'APPLE SHOP')
AND vista8.CLUB = vista9.CLUB;
19
SELECT vista4.ID_PRODUCTO, vista4.PRODUCTO,vista5.PRECIO,vista7.CLUB
FROM vista4,vista5,vista7,vista6
WHERE vista4.PRODUCTO = vista5.PRODUCTO 
AND vista7.PROVEEDOR=vista6.PROVEEDOR 
AND vista6.PRODUCTO = vista5.PRODUCTO
AND (vista7.CLUB LIKE 'CULIAC%N' OR vista7.CLUB LIKE 'CIUDAD OBREG%N' OR vista7.CLUB LIKE 'TUXPAN');
		
20 
SELECT vista1.NOMBRE, vista1.DIRECCION, vista3.TELEFONO 
FROM vista1,vista3
WHERE vista1.NOMBRE = vista3.NOMBRE AND vista1.NOMBRE LIKE '% HERNANDEZ %';

