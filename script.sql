CREATE USER 'DesafioJava'@'%' IDENTIFIED BY 'deafiojava2023';
GRANT ALL PRIVILEGES ON DesafioJava.* TO 'DesafioJava'@'%';
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS DesafioJava;
CREATE DATABASE IF NOT EXISTS  DesafioJava;
USE DesafioJava;

CREATE TABLE Componente(
	idComponente INT PRIMARY KEY AUTO_INCREMENT,
    nomeComponente VARCHAR(30),
	unidadeMedida VARCHAR(5),
    metricaComponente FLOAT
);

CREATE TABLE Registro (
	idRegistro INT PRIMARY KEY AUTO_INCREMENT,
    registro FLOAT,
	dateDado DATETIME,
	fkComponente INT,
	CONSTRAINT fkComponente FOREIGN KEY (fkComponente) REFERENCES Componente(idComponente)
);

select * from Componente;