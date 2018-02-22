SELECT articulo.nombre,departamento.nombre FROM departamento,articulo WHERE articulo.idDepto=departamento.idDepto ORDER BY articulo.nombre  ASC;

SELECT mixup.nombre, ubicacion, departamento.nombre FROM mixup, mixupdepto, departamento WHERE mixup.idmixup = mixupdepto.idmixup AND departamento.idDepto = mixupdepto.idDepto AND mixup.ubicacion = 'VERACRUZ';

SELECT socio.nombre, socio.tel,mixup.nombre FROM socio,mixup,sociomix WHERE socio.idSocio=sociomix.idSocio AND mixup.idmixUp=sociomix.idmixUp AND socio.nombre LIKE '%HERNANDEZ%';

4.-
SELECT s.nombre, m.nombre FROM socio s, mixup m, sociomix sm WHERE s.idSocio = sm.idSocio AND m.idmixup = sm.idmixup AND m.nombre like 'GALERIAS';


SELECT a.nombre, a.precioUnitario, d.nombre FROM articulo a, departamento d, mixupdepto md, mixup m WHERE m.idmixup = md.idmixup AND d.idDepto = md.idDepto AND a.idDepto = d.idDepto AND m.nombre = 'PLAZA ANGELOPOLIS' AND a.precioUnitario between 500 AND 2000;


SELECT nombre, dir FROM mixup WHERE dir like '%39670' or dir like '%64620' or dir like '%72450';


SELECT a.nombre, m.nombre, ubicacion FROM asociado a, mixup m WHERE ubicacion = 'GUADALAJARA' AND a.idmixup = m.idmixup;


SELECT a.nombre, d.nombre, a.descripcion FROM articulo a, departamento d WHERE a.iddepto = d.iddepto AND d.nombre = 'Cds';


SELECT * FROM socio WHERE nombre like '% % %E%' AND nombre like '%GARCIA%';


SELECT a.nombre, m.nombre FROM articulo a, departamento d, mixup m, mixupdepto md WHERE m.idmixup = md.idmixup AND md.iddepto = d.iddepto AND d.iddepto = a.iddepto AND d.nombre = 'iShop'; 



SELECT a.nombre FROM asociado a, mixup m, mixupdepto;


SELECT count(*) FROM mixup;


SELECT ubicacion, count(nombre) FROM mixup group by ubicacion ORDER BY 2 desc;


14.-
//group by m.nombre;