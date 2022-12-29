/*
Microsoft sql obliga a hacer pausas entre ejecuciones
*/
USE master;  
GO  
/*Añadimos la bbdd alumnos*/
CREATE DATABASE alumnos;
go
/*Pausa */
USE alumnos;  
/*Añadimos tablas*/
go
CREATE TABLE alumno (  
    id_alumno int PRIMARY KEY IDENTITY(1,1),
    nombre nvarchar(50),
	apellido nvarchar(50),
	id_clase int
   ) ;
go
/*Pausa */
CREATE TABLE clase (  
    id_clase int PRIMARY KEY IDENTITY(1,1),
    nombre nvarchar(50),
	Descripccion nvarchar(50)
   ) ;
go
/*Añadimos FK*/
ALTER TABLE alumno ADD CONSTRAINT clase_fk FOREIGN KEY (id_clase) REFERENCES clase (id_clase);

/*Insertar datos*/
go
SET IDENTITY_INSERT clase ON
go
INSERT INTO clase (id_clase,Nombre, Descripccion) 
VALUES 
(4,'Idioma Japones', 'Curso de Idiomas Japones'),
(5,'C F', 'Curso de Fisica'),
(6,'C M', 'Curso de Matematicas');
go
SET IDENTITY_INSERT clase OFF
go
SET IDENTITY_INSERT alumno ON
go
insert into alumno (nombre,apellido,id_clase)
values
('Miko','Yamada',4),
('Amity','Blight',5),
('Sasha','',6),
('Mike','Mira',4);
go
SET IDENTITY_INSERT alumno OFF
go
select * from alumno;
go
select * from clase;
/*Commit puede fallar si tienes el autocommit activado o
	la configuración del server incorrecta
*/
go
commit;
go
select a.id_alumno,a.nombre,a.apellido,c.nombre as"nameC",c.Descripccion as "descC" from alumno a,clase c where a.id_clase = c.id_clase;
