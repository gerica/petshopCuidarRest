-- Database: "petshopCuidar_desenv"

-- DROP DATABASE "petshopCuidar_desenv";

CREATE DATABASE "petshopCuidar_desenv"
  WITH OWNER = rogerio
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;

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