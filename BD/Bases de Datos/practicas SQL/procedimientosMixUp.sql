CREATE PROCEDURE empleados(IN mixupNombre VARCHAR(60))
begin
SELECT count(*) FROM asociado,mixup WHERE asociado.idmixUp = mixup.idmixUp AND mixup.nombre LIKE CONCAT(mixupNombre);
SELECT asociado.nombre FROM mixup,asociado WHERE asociado.idmixUp = mixup.idmixUp AND
mixup.nombre LIKE CONCAT(mixupNombre);
end #

CREATE PROCEDURE socios (IN mixupNombre VARCHAR(60))
begin
SELECT count(*) FROM socio,mixup,sociomix WHERE mixup.idmixUp=sociomix.idmixUp AND socio.idsocio=sociomix.idsocio AND	
mixup.nombre LIKE CONCAT(mixupNombre);
SELECT socio.nombre, socio.email FROM socio,mixup,sociomix WHERE mixup.idmixUp=sociomix.idmixUp AND socio.idsocio=sociomix.idsocio
AND mixup.nombre LIKE CONCAT(mixupNombre);
end #

CREATE PROCEDURE precio(IN nombreProducto VARCHAR(90))
begin
SELECT precio FROM producto WHERE nombre LIKE CONCAT (nombreProducto);
end #