CREATE TABLE LOGIN (
codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(20) NOT NULL,
senha VARCHAR(20) NOT NULL,
grupo INTEGER  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO LOGIN(nome,senha,grupo) VALUES("Araujo", "Paja@270265", 1);
INSERT INTO LOGIN(nome,senha,grupo) VALUES("Julia", "Projovem220761",3);
INSERT INTO LOGIN(nome,senha,grupo) VALUES("Marcelo", "Vasco2Divisao",2);
INSERT INTO LOGIN(nome,senha,grupo) VALUES("Milena", "Rosa7",4);