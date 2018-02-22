CREATE PROCEDURE precio(IN nombreProducto VARCHAR(90), OUT precioProducto FLOAT)
begin
SET precioProducto = (SELECT precio FROM producto WHERE nombre LIKE CONCAT (nombreProducto));
SELECT @precioProducto;
end #



CREATE PROCEDURE cajas(IN nombreDeLaSucursal VARCHAR(90), OUT cajaDisponible INT)
begin
SET cajaDisponible = (SELECT MAX(noCaja) FROM caja,sucursal WHERE caja.idSucursal=sucursal.idSucursal AND sucursal.nombreSucursal LIKE CONCAT(nombreDeLaSucursal));
SELECT @cajaDisponible;
end #

call cajas("Lopez Mateos",@cajaDisponible);

CREATE PROCEDURE sumaTotal(IN identificadorTicket INT, OUT sumaPorTicket FLOAT)
begin
SET sumaPorTicket = (SELECT TRUNCATE(SUM(Total),2) FROM detalle WHERE idTicket LIKE CONCAT(identificadorTicket));
SELECT ROUND(@sumaPorTicket,2);
end #

CREATE PROCEDURE direccion(IN nombreDeLaSucursal VARCHAR(90), OUT direccionSucursal VARCHAR(120))
begin
SET direccionSucursal = (SELECT direccion FROM sucursal WHERE nombreSucursal LIKE CONCAT(nombreDeLaSucursal));
SELECT @direccionSucursal;
end #

CREATE PROCEDURE ventasDeldia(IN nombreDeLaSucursal VARCHAR(90), IN dia DATE, OUT ventadelDia FLOAT)
begin
SET ventadelDia = (SELECT SUM(detalle.total) FROM detalle, ticket,sucursal WHERE (detalle.idTicket=ticket.idTicket AND ticket.idSucursal=sucursal.idSucursal AND sucursal.nombreSucursal LIKE CONCAT(nombreDeLaSucursal) AND ticket.fecha LIKE CONCAT (dia)));
SELECT @ventadelDia;
end #

CREATE PROCEDURE ventasDepartamento(IN nombreDeLaSucursal VARCHAR(90),IN departamento VARCHAR(60),OUT ventaDepartamento FLOAT)
begin
SET ventaDepartamento = (SELECT SUM(detalle.total) FROM detalle,ticket,sucursal,producto,categoria WHERE categoria.nombreCategoria LIKE CONCAT(departamento) AND ((detalle.idTicket=ticket.idTicket AND ticket.idSucursal=sucursal.idSucursal AND producto.idCategoria=categoria.idCategoria AND sucursal.nombreSucursal LIKE CONCAT(nombreDeLaSucursal))));
SELECT ROUND(@ventaDepartamento,2);
end #

SELECT SUM(detalle.total),categoria.nombreCategoria FROM detalle,categoria WHERE categoria.nombreCategoria LIKE 'Bebes' GROUP BY categoria.nombreCategoria;


CREATE PROCEDURE ventaProducto(IN nombreProducto VARCHAR(90),OUT totalVentaProducto FLOAT)
begin
SET totalVentaProducto = (SELECT SUM(detalle.total) FROM detalle,producto WHERE detalle.idProducto = producto.idProducto AND producto.nombre LIKE CONCAT (nombreProducto));
SELECT ROUND(@totalVentaProducto,2);
end #


CREATE PROCEDURE ventaProducto(IN nombreProducto VARCHAR(90),OUT totalVentaProducto FLOAT)
begin
SET SELECT SUM(detalle.total) FROM detalle,categoria,producto WHERE detalle.idProducto = producto.idProducto AND producto.idCategoria = categoria.idCategoria AND categoria.nombreCategoria LIKE 'Electronica';



CREATE PROCEDURE ventaProductoSucursal(IN nombreCategoria VARCHAR(90),IN nombreSucursal VARCHAR(90),OUT totalVentas FLOAT)
begin
SET totalVentas = (SELECT SUM(detalle.total) FROM detalle,categoria,producto,ticket,sucursal WHERE detalle.idProducto = producto.idProducto AND producto.idCategoria = categoria.idCategoria AND ticket.idSucursal = sucursal.idSucursal AND detalle.idTicket = ticket.idTicket AND categoria.nombreCategoria LIKE CONCAT(nombreCategoria)  AND sucursal.nombreSucursal LIKE CONCAT(nombreSucursal));
SELECT ROUND(@totalVentas,2);
end #


CREATE PROCEDURE cajasExistentes(IN nombreDeLaSucursal VARCHAR(90))
begin
SELECT caja.noCaja FROM caja,sucursal WHERE caja.idSucursal=sucursal.idSucursal AND sucursal.nombreSucursal LIKE CONCAT (nombreDeLaSucursal) ORDER BY caja.noCaja;
end #


CREATE PROCEDURE cajasExistentes2(IN nombreDeLaSucursal VARCHAR(90), OUT cajasEnExistencias INT)
begin
SET cajasEnExistencias = (SELECT caja.noCaja FROM caja,sucursal WHERE caja.idSucursal=sucursal.idSucursal AND sucursal.nombreSucursal LIKE CONCAT (nombreDeLaSucursal) ORDER BY caja.noCaja LIMIT 10 );
SELECT @cajasEnExistencias;
end #


