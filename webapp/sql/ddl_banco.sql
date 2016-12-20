-- Database: "petshopCuidar_desenv"

-- DROP DATABASE "petshopCuidar_desenv";

CREATE DATABASE "petshopCuidar_desenv"
  WITH OWNER = rogerio
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;
       
-- Schema: cliente

-- DROP SCHEMA cliente;

CREATE SCHEMA cliente
  AUTHORIZATION rogerio;
  
  -- Schema: pet

-- DROP SCHEMA pet;

CREATE SCHEMA pet
  AUTHORIZATION postgres;

       

 -- Sequence: public.hibernate_sequence
-- DROP SEQUENCE public.hibernate_sequence;
CREATE SEQUENCE public.hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2486
  CACHE 1;
ALTER TABLE public.hibernate_sequence
  OWNER TO rogerio;
  
  -- Table: cliente.tb_tipo_documento

-- DROP TABLE cliente.tb_tipo_documento;

CREATE TABLE cliente.tb_tipo_documento
(
  id_tipo_documento bigint NOT NULL,
  ds_tipo_documento character varying(255),
  CONSTRAINT tb_tipo_documento_pkey PRIMARY KEY (id_tipo_documento)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente.tb_tipo_documento
  OWNER TO rogerio;

  
  -- Table: cliente.tb_pessoa

-- DROP TABLE cliente.tb_pessoa;

CREATE TABLE cliente.tb_pessoa
(
  id_pessoa bigint NOT NULL,
  dt_nascimento timestamp without time zone,
  ds_nome character varying(255),
  ds_sexo character varying(255),
  ds_tipo_pessoa character varying(255),
  CONSTRAINT tb_pessoa_pkey PRIMARY KEY (id_pessoa)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente.tb_pessoa
  OWNER TO rogerio;

  
  -- Table: cliente.tb_estado

-- DROP TABLE cliente.tb_estado;

CREATE TABLE cliente.tb_estado
(
  id_estado bigint NOT NULL,
  ds_descricao character varying(255),
  ds_sigla character varying(255),
  CONSTRAINT tb_estado_pkey PRIMARY KEY (id_estado)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente.tb_estado
  OWNER TO rogerio;

  
  -- Table: cliente.tb_telefone

-- DROP TABLE cliente.tb_telefone;

CREATE TABLE cliente.tb_telefone
(
  id_telefone bigint NOT NULL,
  nr_ddd character varying(255),
  nr_numero character varying(255),
  ds_tipo_telefone character varying(255),
  id_pessoa bigint NOT NULL,
  CONSTRAINT tb_telefone_pkey PRIMARY KEY (id_telefone),
  CONSTRAINT fkdt60jokl39pgquqyxhr22xom9 FOREIGN KEY (id_pessoa)
      REFERENCES cliente.tb_pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente.tb_telefone
  OWNER TO rogerio;

  
  -- Table: cliente.tb_endereco

-- DROP TABLE cliente.tb_endereco;

CREATE TABLE cliente.tb_endereco
(
  id_endereco bigint NOT NULL,
  ds_bairro character varying(255),
  nr_cep character varying(255),
  ds_complemento character varying(255),
  ds_logradouro character varying(255),
  nr_numero integer,
  id_cidade bigint NOT NULL,
  id_pessoa bigint NOT NULL,
  CONSTRAINT tb_endereco_pkey PRIMARY KEY (id_endereco),
  CONSTRAINT fk1wlvil0y56h89uki97895gl5o FOREIGN KEY (id_cidade)
      REFERENCES cliente.tb_cidade (id_cidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fklulmoqiwd20dykrxhyfxvo9r FOREIGN KEY (id_pessoa)
      REFERENCES cliente.tb_pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente.tb_endereco
  OWNER TO rogerio;

  
  -- Table: cliente.tb_documento

-- DROP TABLE cliente.tb_documento;

CREATE TABLE cliente.tb_documento
(
  id_documento bigint NOT NULL,
  ds_complemento character varying(255),
  nr_numero character varying(255) NOT NULL,
  id_estado bigint,
  id_pessoa bigint NOT NULL,
  id_tipo_documento bigint NOT NULL,
  CONSTRAINT tb_documento_pkey PRIMARY KEY (id_documento),
  CONSTRAINT fk52ne7omxpm83r7nax09nmkhhp FOREIGN KEY (id_tipo_documento)
      REFERENCES cliente.tb_tipo_documento (id_tipo_documento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkdo5x9yvwhu6sy0ox97fnhmo9k FOREIGN KEY (id_pessoa)
      REFERENCES cliente.tb_pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkdt1agxsma5lmv2345py89rdgj FOREIGN KEY (id_estado)
      REFERENCES cliente.tb_estado (id_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente.tb_documento
  OWNER TO rogerio;

  
  -- Table: cliente.tb_cidade

-- DROP TABLE cliente.tb_cidade;

CREATE TABLE cliente.tb_cidade
(
  id_cidade bigint NOT NULL,
  ds_descricao character varying(255),
  id_estado bigint NOT NULL,
  CONSTRAINT tb_cidade_pkey PRIMARY KEY (id_cidade),
  CONSTRAINT fk12tfv6wlsvaml7nsmq106hvib FOREIGN KEY (id_estado)
      REFERENCES cliente.tb_estado (id_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente.tb_cidade
  OWNER TO rogerio;

  -- Table: pet.tb_tipo_pet

-- DROP TABLE pet.tb_tipo_pet;

CREATE TABLE pet.tb_tipo_pet
(
  id_tipo_pet bigint NOT NULL,
  ds_nome character varying(255),
  CONSTRAINT tb_tipo_pet_pkey PRIMARY KEY (id_tipo_pet)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pet.tb_tipo_pet
  OWNER TO rogerio;

  -- Table: pet.tb_raca

-- DROP TABLE pet.tb_raca;

CREATE TABLE pet.tb_raca
(
  id_raca bigint NOT NULL,
  ds_nome character varying(255),
  id_tipo_pet bigint,
  CONSTRAINT tb_raca_pkey PRIMARY KEY (id_raca),
  CONSTRAINT fkngjw53qkwkcbw2j6jkasqtfyg FOREIGN KEY (id_tipo_pet)
      REFERENCES pet.tb_tipo_pet (id_tipo_pet) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pet.tb_raca
  OWNER TO rogerio;

  -- Table: pet.tb_pet

-- DROP TABLE pet.tb_pet;

CREATE TABLE pet.tb_pet
(
  id bigint NOT NULL,
  ds_nome character varying(255),
  dt_nacimento date,
  id_pessoa bigint,
  id_raca bigint,
  id_usuario bigint,
  CONSTRAINT tb_pet_pkey PRIMARY KEY (id),
  CONSTRAINT fk3xvduiouo4ejyrdeloxqu3n0y FOREIGN KEY (id_pessoa)
      REFERENCES cliente.tb_pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkmld8iokd5se7sifuee60rguo1 FOREIGN KEY (id_usuario)
      REFERENCES public.tb_usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkrqn8luyv00pl44imptrvtw625 FOREIGN KEY (id_raca)
      REFERENCES pet.tb_raca (id_raca) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pet.tb_pet
  OWNER TO rogerio;
