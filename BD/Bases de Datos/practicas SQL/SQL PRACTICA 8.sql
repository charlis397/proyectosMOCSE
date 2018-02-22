delimiter #

CREATE PROCEDURE procedimiento1()
begin
SELECT count(*) AS GERENTES FROM gerente;
SELECT club.nombre, sociosc.nombre FROM club,sociosc,estado,socioclub WHERE  club.idClub=socioclub.idClub AND
sociosc.idSocio = socioclub.idSocio AND club.idEdo=estado.idEdo AND estado.nombre LIKE 'M%XICO';
SELECT club.nombre AS ServicioAppleShop FROM club,servicioclub,servicio WHERE club.idClub=servicioclub.idClub AND
servicio.idServicio=servicioclub.idServicio AND servicio.nombre='APPLE SHOP';
end #

delimiter ;




delimiter #

CREATE PROCEDURE procedimiento2()
begin
SELECT gerente.nombre FROM club, gerente
WHERE club.idclub = gerente.idclub AND club.nombre LIKE 'TOLUCA';
SELECT servicio.nombre FROM club,servicioclub,servicio
WHERE club.idClub=servicioclub.idClub AND servicio.idServicio = servicioclub.idServicio AND
club.nombre LIKE 'TOLUCA';
SELECT proveedor.nombre FROM proveedorsams, proveedor,club
WHERE proveedor.idProveedor=proveedorsams.idProveedor AND club.idClub =proveedorsams.idClub AND
club.nombre LIKE 'TOLUCA';
end #

delimiter ;


delimiter #

CREATE PROCEDURE procedimiento3(IN apellido VARCHAR(50))
begin
SELECT nombre FROM sociosc WHERE
nombre LIKE CONCAT('%',apellido,'%');
SELECT count(*) FROM sociosc;
SELECT producto.producto, producto.precioUnitario FROM producto,proveedor WHERE
producto.idproveedor = proveedor.idproveedor AND proveedor.nombre LIKE 'SABRITAS';
SELECT club.nombre, gerente.nombre, proveedor.nombre FROM club,gerente,proveedor,estado,proveedorsams WHERE
club.idclub =gerente.idclub AND estado.idEdo=club.idEdo AND proveedor.idProveedor=proveedorsams.idProveedor AND
club.idClub=proveedorsams.idClub AND estado.nombre LIKE 'CHIAPAS';
end #

delimiter ;