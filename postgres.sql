-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  id SERIAL NOT NULL,
  tipo INT NOT NULL,
  nome VARCHAR(250) NOT NULL,
  senha VARCHAR(300) NOT NULL,
  cpf VARCHAR(250) NULL,
  rg VARCHAR(250) NULL,
  telefone VARCHAR(250) NULL,
  celular VARCHAR(250) NULL,
  data_cadastro TIMESTAMP NULL,
  status INT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table categorias
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS categorias (
  id SERIAL NOT NULL ,
  descricao VARCHAR(50) NULL,
  desconto DECIMAL(10,2) NULL,
  nome VARCHAR(50) NOT NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table fornecedores
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fornecedores (
  id SERIAL NOT NULL ,
  nome VARCHAR(250) NULL,
  cnpj VARCHAR(45) NULL,
  email VARCHAR(50) NULL,
  telefone VARCHAR(50) NULL,
  rua VARCHAR(100) NULL,
  bairro VARCHAR(100) NULL,
  cidade VARCHAR(100) NULL,
  estado CHAR(2) NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtos (
  id SERIAL NOT NULL ,
  nome VARCHAR(250) NULL,
  valor_unitario DECIMAL(10,2) NULL,
  tamanho VARCHAR(2) NULL,
  id_categoria INT NOT NULL,
  id_fornecedor INT NOT NULL,
  statuss INT NULL,
  qtde INT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (id_categoria)
    REFERENCES categorias (id),   
    FOREIGN KEY (id_fornecedor)
    REFERENCES fornecedores (id))
;


-- -----------------------------------------------------
-- Table clientes
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS clientes (
  id SERIAL NOT NULL ,
  nome VARCHAR(250) NULL,
  cpf VARCHAR(250) NULL,
  telefone VARCHAR(250) NULL,
  celular VARCHAR(250) NULL,
  data_cadastro TIMESTAMP NULL,
  rua VARCHAR(100) NULL,
  bairro VARCHAR(100) NULL,
  cidade VARCHAR(100) NULL,
  estado CHAR(2) NULL,
  PRIMARY KEY (id))
;


-- -----------------------------------------------------
-- Table vendas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS vendas (
  id SERIAL NOT NULL ,
  id_usuario INT NOT NULL,
  id_cliente INT NULL,
  data TIMESTAMP NULL,
  desconto DECIMAL(10,2) NULL,
  PRIMARY KEY (id), 
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id),   
    FOREIGN KEY (id_cliente)
    REFERENCES clientes (id))
;


-- -----------------------------------------------------
-- Table produtos_venda
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtos_venda (
  id SERIAL NOT NULL ,
  id_produto INT NOT NULL,
  id_venda INT NOT NULL,
  valor_unitario DECIMAL(10,2) NULL,
  quantidade INT NULL,
  PRIMARY KEY (id),   
    FOREIGN KEY (id_produto)
    REFERENCES produtos (id),
    FOREIGN KEY (id_venda)
    REFERENCES vendas (id)
    )
;



drop table produtos_venda;
drop table vendas;
drop table produtos;
drop table clientes;
drop table usuario;
drop table fornecedores;
drop table categorias;

