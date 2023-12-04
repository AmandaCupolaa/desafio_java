CREATE DATABASE IF NOT EXISTS  DesafioJava;
USE DesafioJava;

CREATE USER 'DesafioJava'@'%' IDENTIFIED BY 'deafiojava2023';
GRANT ALL PRIVILEGES ON DesafioJava.* TO 'DesafioJava'@'%';
FLUSH PRIVILEGES;

CREATE TABLE Metrica(
	idMetrica INT PRIMARY KEY AUTO_INCREMENT,
	limiteMaximo float
);

CREATE TABLE Componente(
	idComponente INT PRIMARY KEY AUTO_INCREMENT,
    nomeComponente VARCHAR(10),
	unidadeMedida VARCHAR(5),
    fkMetrica INT,
	CONSTRAINT fkMetrica FOREIGN KEY (fkMetrica) REFERENCES Metrica(idMetrica)
);

CREATE TABLE Registro (
	idRegistro INT PRIMARY KEY AUTO_INCREMENT,
    registro float,
	dateDado DATETIME,
	fkComponente INT,
	CONSTRAINT fkComponente FOREIGN KEY (fkComponente) REFERENCES Componente(idComponente)
);
