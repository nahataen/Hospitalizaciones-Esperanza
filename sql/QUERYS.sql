/*
1.Informacion del ingreso del un paciente
Tablas usadas
e = Expedentes
p = Pacientes
m = Medicos
ee = expediente_especialidad  ##nota la relacion esta hecha para que te muestre solo una especialidad
es = Especialidades
t = Tratamientos
md = medicamentos
*/
SELECT p.numero
as 'Número de paciente',
concat(IFNULL(concat (p.nombre,' '),' '),  
IFNULL(concat(p.apellido_paterno,' '),' ') , 
IFNULL(concat(p.apellido_materno,' '),' '))
as 'Nombre del paciente',                   
date_format(e.fecha_de_ingreso, "%d-%m-%y")
as 'Fecha de ingreso',
e.habitacion
as 'Número de habitacion',
e.numero_de_cama
as 'Número de cama',
concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' '))
as 'Nombre del médico',
es.nombre as Especialidad,
e.sintomas as Sintoma,
e.diagnosticos as 'Diagnóstico',
t.numero 
as 'número del tratamiento',
md.nombre as Medicamentos,
t.dosis 
as 'Dosis',
t.tiempo_dias 
as 'Duración(Días)'
FROM expedientes as e
inner join pacientes as p on p.numero = e.paciente
inner join medicos as m on m.numero = e.medico
inner join expedientes_especialidad as ee on e.folio = ee.expediente
inner join especialidades as es on es.codigo = ee.especialidad
inner join tratamientos as t on t.expediente = e.folio
inner join medicamentos as md on md.codigo = t.medicamento
WHERE e.folio=6


/*
2.Ingresos que ha tenido un paciente
Tablas usadas
e = Expedentes
p = Pacientes
m = Medicos
es = Especialidades
ee = expediente_especialidad  ##nota la relacion esta hecha para que te muestre solo una especialidad
t = Tratamientos
*/
SELECT  e.folio 
as 'Código de ingreso',
concat(IFNULL(concat (p.nombre,' '),' '),  
IFNULL(concat(p.apellido_paterno,' '),' ') , 
IFNULL(concat(p.apellido_materno,' '),' '))
as 'Nombre del paciente',  
date_format(e.fecha_de_ingreso, "%d-%m-%y")
as 'Fecha de ingreso',
concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') ,
IFNULL(concat(m.apellidoM,' '),' '))
as 'Nombre del medico',
es.nombre as Especialidad,
e.sintomas as Sintoma,
e.diagnosticos as 'Diagnóstico',
Max(t.tiempo_dias)
as 'Dias de hospitalización'
FROM expedientes as e
inner join tratamientos as t on t.expediente = e.folio
inner join pacientes as p on p.numero = e.paciente
inner join medicos as m on m.numero = e.medico
inner join expedientes_especialidad as ee on e.folio = ee.expediente
inner join especialidades as es on es.codigo = ee.especialidad
WHERE p.numero = 2
GROUP BY e.folio
/*
3.Pacientes que se encuentran en la misma habitacion
Tablas usadas
e = Expedentes
p = Pacientes
h = habitaciones
*/
SELECT e.folio 
as 'Código de ingreso',
concat(IFNULL(concat (p.nombre,' '),' '),  
IFNULL(concat(p.apellido_paterno,' '),' ') , 
IFNULL(concat(p.apellido_materno,' '),' '))
as 'Nombre del paciente',
date_format(e.fecha_de_ingreso, "%d-%m-%y")
as 'fecha de ingreso',
e.diagnosticos as Diagnostico,
h.numero 
as 'Número de habitacion',
e.numero_de_cama  
as 'Número cama'
FROM expedientes as e
inner join pacientes as p on p.numero = e.paciente
inner join habitaciones as h on h.numero = e.habitacion
WHERE h.numero = 2


/*
4.Medicos con la misma especialidad
Tablas usadas
es = Especialidades
me = medicos_especialidad
m = Medicos
*/

SELECT es.nombre 
AS 'Nombre de la especialidad', 
concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' '))
AS 'Nombre del médico'
FROM medicos AS m
INNER JOIN medicos_especialidad AS me ON m.numero = me.medico
INNER JOIN especialidades AS es ON me.especialidad = es.codigo
WHERE es.nombre = 'Cardiología'


/*
5.Especialidad de un medico
es = Especialidades
me = medicos_especialidad
m = Medicos
*/

SELECT concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' '))
AS 'Nombre del médico',
es.nombre 
AS 'Nombre de la especialidad' 
FROM medicos AS m
INNER JOIN medicos_especialidad AS me ON m.numero = me.medico
INNER JOIN especialidades AS es ON me.especialidad = es.codigo
WHERE m.numero = 7

 
/*
6.Médicos que han atendido a un paciente
e = Expedientes
p = pacientes
ee = expediente_especialidad  ##nota la relacion esta hecha para que te muestre solo una especialidad
es = Especialidades
m = Medicos
*/

SELECT concat(IFNULL(concat (p.nombre,' '),' '),  
IFNULL(concat(p.apellido_paterno,' '),' ') , 
IFNULL(concat(p.apellido_materno,' '),' '))
as 'Nombre del paciente',
e.folio 
as 'Código de ingreso',
e.sintomas as Sintoma,
e.diagnosticos as 'Diagnóstico',
es.nombre as Especialidad,
concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' ')) 
as 'Nombre del médico',
date_format(e.fecha_de_ingreso, "%d-%m-%y")
as 'fecha de ingreso',
IFNULL (date_format(e.fecha_de_alta, "%d-%m-%y"), " ")
as 'fecha de salida'
FROM expedientes as e
inner join pacientes as p on p.numero = paciente
inner join expedientes_especialidad as ee on e.folio = ee.expediente
inner join especialidades as es on ee.especialidad = es.codigo
inner join medicos as m on e.medico = m.numero
WHERE p.numero = 2 

/*
7.Pacientes que ingresaron el mismos dia
Tablas usadas
e = Expedientes
p = pacientes
h = habitaciones
ee = expediente_especialidad  ##nota la relacion esta hecha para que te muestre solo una especialidad
m = Medicos
es = Especialidades
*/
SELECT date_format(e.fecha_de_ingreso, "%d-%m-%y") 
as 'Fecha de ingreso', 
e.folio 
as 'Codigo de ingreso',
concat(IFNULL(concat (p.nombre,' '),' '),  
IFNULL(concat(p.apellido_paterno,' '),' ') , 
IFNULL(concat(p.apellido_materno,' '),' '))
as 'Nombre del paciente',
e.diagnosticos as 'Diagnóstico',
h.numero
as 'Número de habitaciones',
e.numero_de_cama
as 'Número de cama',
concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' '))
AS 'Nombre del médico',
es.nombre as Especialidad
FROM expedientes as e 
inner join habitaciones as h on h.numero = e.habitacion
inner join pacientes as p on p.numero = e.paciente
inner join expedientes_especialidad as ee on e.folio = ee.expediente
inner join medicos as m on e.medico = m.numero
inner join especialidades as es on ee.especialidad = es.codigo
WHERE e.fecha_de_ingreso ='2023-08-11'
/*
8.Lista de niños menores de 13 años, hospitalizados actualemnte (sin fecha de salida)
Tablas usadas
e = Expedientes
p = pacientes
h = habitaciones
ee = expediente_especialidad  ##nota la relacion esta hecha para que te muestre solo una especialidad
m = Medicos
es = Especialidades
*/
SELECT e.folio 
as 'Código de ingreso',
concat(IFNULL(concat (p.nombre,' '),' '),  
IFNULL(concat(p.apellido_paterno,' '),' ') , 
IFNULL(concat(p.apellido_materno,' '),' '))
as 'Nombre del paciente',
date_format(p.fecha_de_nacimiento , "%d-%m-%y") 
as 'Fecha de nacimiento',
e.edad as Edad,
date_format(e.fecha_de_ingreso , "%d-%m-%y")
as 'Fecha de ingreso',
e.sintomas as Sintoma, 
e.diagnosticos as 'Diagnósticos',
concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' '))
AS 'Nombre del médico',
es.nombre as Especialidad
FROM expedientes as e 
inner join habitaciones as h on h.numero = e.habitacion
inner join pacientes as p on p.numero = e.paciente
inner join expedientes_especialidad as ee on e.folio = ee.expediente
inner join medicos as m on e.medico = m.numero
inner join especialidades as es on ee.especialidad = es.codigo
WHERE e.edad<13 and e.fecha_de_alta is null
/*tidad de paciente atendidos por medico
m 
9.Can= Medicos COREGIR Poner mas datos
e = expedientes
*/
SELECT concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' '))
AS 'Nombre médico',
COUNT(e.paciente)
AS 'Pacientes atendidos'
FROM medicos AS m
INNER JOIN expedientes AS e ON m.numero = e.medico
GROUP BY m.numero



/*
10.Ingresos que ha tenido un paciente
Tablas usadas
ee = expedientes_especialidad

*/
SELECT es.nombre as Especialidad,
concat(IFNULL(concat (m.nombre_doctor,' '),' '),  
IFNULL(concat(m.apellidoP,' '),' ') , 
IFNULL(concat(m.apellidoM,' '),' '))
AS 'Nombre médico',
e.folio 
as 'Código de ingreso',
concat(IFNULL(concat (p.nombre,' '),' '),  
IFNULL(concat(p.apellido_paterno,' '),' ') , 
IFNULL(concat(p.apellido_materno,' '),' '))
as 'Nombre del paciente',
date_format(e.fecha_de_ingreso , "%d-%m-%y")
as 'Fecha de ingreso',
e.sintomas as Sintoma,
e.diagnosticos as 'diagnósticos'
FROM expedientes as e
inner join expedientes_especialidad as ee on ee.expediente = e.folio
inner join especialidades as es on es.codigo = ee.especialidad
inner join medicos as m on e.medico = m.numero
inner join pacientes as p on p.numero = e.paciente
WHERE es.codigo = "ESP02"


