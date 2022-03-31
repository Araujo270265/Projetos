CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo boolean not null,
	logradouro VARCHAR(50),
	numero VARCHAR(5),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(9),
	cidade VARCHAR(50),
	estado VARCHAR(2)	
) ENGINE=InnoDB CHARSET=utf8;

INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Antonio Araujo',true,'Rua Tres','02','Quadra - 11 Lote - 34','Jardim Atlantico/Itaipuaçu','24900-100','Marica','RJ');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Edith Julia',true,'Rua Tres','02','Quadra - 11 Lote - 34','Jardim Atlantico/Itaipuaçu','24900-100','Marica','RJ');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Joao das Couves',true,'Rua do Não Sei','02','Bloco 05 Apto. 504','Castelo','24310-100','Blumenau','SC');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Marcelo',true,'Av Joao Gourlart','02','Quadra - 11 Lote - 34','Centro','24900-110','Belo Horizonte','MG');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Carlos',true,'Rua Peropeba','02','Quadra - 11 Lote - 34','Jardim Esperança','24925-100','São Paulo','SP');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Milena',true,'Rua das Flores','02','Quadra - 11 Lote - 34','Balneario Paraiso','24400-100','Olinda','PE');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Valeria',true,'Rua Alegria','02','Quadra - 11 Lote - 34','Alegralandia','24911-100','Sergipe','SE');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Sonia',true,'Rua dos Sonhos','02','Quadra - 11 Lote - 34','Caledonia','24900-100','Nova Friburgo','RJ');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Vitor',true,'Av dos Desesperados','02','Quadra - 11 Lote - 34','Olaria','24995-100','Rio de Janeiro','RJ');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values('Marcos',true,'Rua das Acacias','02','Quadra - 11 Lote - 34','Balneario de Camboriu','24999-100','Santa Catarina','SC');