
/*
Creamos bbdd de alumnos
*/
CREATE SCHEMA if not exists `alumnos` DEFAULT CHARACTER SET utf8 ;
use alumnos;
/*
Añadimos tablas alumno y clase
*/
CREATE TABLE if not exists `alumnos`.`alumno` (
  `id_alumno` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `id_clase` INT NULL,
  PRIMARY KEY (`id_alumno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
commit;

CREATE TABLE `alumnos`.`clase` (
  `id_clase` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripccion` VARCHAR(45) NULL,
  PRIMARY KEY (`id_clase`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
commit;

/*
Añadimos FK
*/
ALTER TABLE `alumnos`.`alumno` 
ADD CONSTRAINT `clase`
  FOREIGN KEY (`id_clase`)
  REFERENCES `alumnos`.`clase` (`id_clase`);
  
/*
Insertamos Datos de la BBDD
*/
INSERT INTO `alumnos`.`clase` (`id_clase`,`Nombre`, `Descripccion`) 
VALUES 
(1,'E1ºB', 'Curso 1 de Arqueologia, clase B'),
(2,'C3', 'Curso 3 de ciencias'),
(3,'ENGA2', 'Curso 2 de ingles americano');

select * from `alumnos`.`clase`;

insert into `alumnos`.`alumno` (`nombre`,`apellido`,`id_clase`)
values
('Luz','Noceda',2),
('Marcy','Wu',2),
('Anne','Bunchuy',1),
('Sandra','Castamar',3);
select * from `alumnos`.`alumno`;
commit;
use alumnos;
/*
 QUERRY SPECIAL
 */
select a.id_alumno,a.nombre,a.apellido,c.nombre as"nameC",c.Descripccion as "descC" from alumno a,clase c where a.id_clase = c.id_clase;