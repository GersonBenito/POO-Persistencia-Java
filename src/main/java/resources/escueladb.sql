CREATE DATABASE escueladb;

USE escueladb;

CREATE TABLE alumno(
	id int primary key auto_increment,
    nombre varchar(255) not null,
    apellido varchar(255) not null,
    fecha_nacimiento date not null,
    carrera_id int not null
);

CREATE TABLE carrera(
	id int primary key auto_increment,
    nombre varchar(255) not null
);

CREATE TABLE materia(
	id int primary key auto_increment,
    nombre varchar(255) not null,
    tipo varchar(255) not null,
    carrera_id int not null
);

ALTER TABLE alumno ADD CONSTRAINT FK_alumnoCarrera FOREIGN KEY (carrera_id) REFERENCES carrera(id);
ALTER TABLE materia ADD CONSTRAINT FK_materiaCarrera FOREIGN KEY (carrera_id) REFERENCES carrera(id);