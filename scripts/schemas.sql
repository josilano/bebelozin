--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-03-12 15:52:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 184 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1994 (class 0 OID 0)
-- Dependencies: 184
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 57414)
-- Name: convenios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE convenios (
    conv_id integer NOT NULL,
    conv_tipo character varying(30)
);


ALTER TABLE public.convenios OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 57412)
-- Name: convenios_conv_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE convenios_conv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.convenios_conv_id_seq OWNER TO postgres;

--
-- TOC entry 1995 (class 0 OID 0)
-- Dependencies: 172
-- Name: convenios_conv_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE convenios_conv_id_seq OWNED BY convenios.conv_id;


--
-- TOC entry 177 (class 1259 OID 57449)
-- Name: pacientes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pacientes (
    pac_id integer NOT NULL,
    pac_nome character varying(60),
    pac_tel character varying(20),
    pac_doenca character varying(60),
    conv_tipo character varying(30)
);


ALTER TABLE public.pacientes OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 57447)
-- Name: pacientes_pac_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pacientes_pac_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pacientes_pac_id_seq OWNER TO postgres;

--
-- TOC entry 1996 (class 0 OID 0)
-- Dependencies: 176
-- Name: pacientes_pac_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pacientes_pac_id_seq OWNED BY pacientes.pac_id;


--
-- TOC entry 182 (class 1259 OID 57471)
-- Name: permissoes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE permissoes (
    perm_id integer NOT NULL,
    usu_id integer,
    perm_modulo character varying(60),
    perm_submodulo character varying(60)
);


ALTER TABLE public.permissoes OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 57469)
-- Name: permissoes_perm_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE permissoes_perm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permissoes_perm_id_seq OWNER TO postgres;

--
-- TOC entry 1997 (class 0 OID 0)
-- Dependencies: 181
-- Name: permissoes_perm_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE permissoes_perm_id_seq OWNED BY permissoes.perm_id;


--
-- TOC entry 180 (class 1259 OID 57463)
-- Name: semanal; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE semanal (
    sem_data date NOT NULL,
    pac_id integer NOT NULL,
    sem_pagmto boolean,
    conv_tipo character varying(30),
    sem_id integer NOT NULL,
    pac_nome character varying(60),
    pac_doenca character varying(60)
);


ALTER TABLE public.semanal OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 57512)
-- Name: semanal_sem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE semanal_sem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.semanal_sem_id_seq OWNER TO postgres;

--
-- TOC entry 1998 (class 0 OID 0)
-- Dependencies: 183
-- Name: semanal_sem_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE semanal_sem_id_seq OWNED BY semanal.sem_id;


--
-- TOC entry 179 (class 1259 OID 57457)
-- Name: sess_sem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sess_sem (
    sess_sem_id integer NOT NULL,
    sem_data date,
    pac_id integer,
    sess_tipo character varying(60),
    sem_pagmto boolean,
    conv_tipo character varying(30),
    pac_nome character varying(60),
    pac_doenca character varying(60)
);


ALTER TABLE public.sess_sem OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 57455)
-- Name: sess_sem_sess_sem_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sess_sem_sess_sem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sess_sem_sess_sem_id_seq OWNER TO postgres;

--
-- TOC entry 1999 (class 0 OID 0)
-- Dependencies: 178
-- Name: sess_sem_sess_sem_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sess_sem_sess_sem_id_seq OWNED BY sess_sem.sess_sem_id;


--
-- TOC entry 175 (class 1259 OID 57439)
-- Name: sessoes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sessoes (
    sess_id integer NOT NULL,
    sess_tipo character varying(60)
);


ALTER TABLE public.sessoes OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 57437)
-- Name: sessoes_sess_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sessoes_sess_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sessoes_sess_id_seq OWNER TO postgres;

--
-- TOC entry 2000 (class 0 OID 0)
-- Dependencies: 174
-- Name: sessoes_sess_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sessoes_sess_id_seq OWNED BY sessoes.sess_id;


--
-- TOC entry 171 (class 1259 OID 57406)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    usu_id integer NOT NULL,
    usu_nome character varying(60),
    usu_tel character varying(20),
    usu_senha character varying(12),
    usu_valor_consulta double precision
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 57404)
-- Name: usuario_id_usu_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_id_usu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_usu_seq OWNER TO postgres;

--
-- TOC entry 2001 (class 0 OID 0)
-- Dependencies: 170
-- Name: usuario_id_usu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_id_usu_seq OWNED BY usuario.usu_id;


--
-- TOC entry 1860 (class 2604 OID 57417)
-- Name: conv_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY convenios ALTER COLUMN conv_id SET DEFAULT nextval('convenios_conv_id_seq'::regclass);


--
-- TOC entry 1862 (class 2604 OID 57452)
-- Name: pac_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pacientes ALTER COLUMN pac_id SET DEFAULT nextval('pacientes_pac_id_seq'::regclass);


--
-- TOC entry 1865 (class 2604 OID 57474)
-- Name: perm_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissoes ALTER COLUMN perm_id SET DEFAULT nextval('permissoes_perm_id_seq'::regclass);


--
-- TOC entry 1864 (class 2604 OID 57514)
-- Name: sem_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY semanal ALTER COLUMN sem_id SET DEFAULT nextval('semanal_sem_id_seq'::regclass);


--
-- TOC entry 1863 (class 2604 OID 57460)
-- Name: sess_sem_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sess_sem ALTER COLUMN sess_sem_id SET DEFAULT nextval('sess_sem_sess_sem_id_seq'::regclass);


--
-- TOC entry 1861 (class 2604 OID 57442)
-- Name: sess_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sessoes ALTER COLUMN sess_id SET DEFAULT nextval('sessoes_sess_id_seq'::regclass);


--
-- TOC entry 1859 (class 2604 OID 57409)
-- Name: usu_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN usu_id SET DEFAULT nextval('usuario_id_usu_seq'::regclass);


--
-- TOC entry 1869 (class 2606 OID 57419)
-- Name: convenios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY convenios
    ADD CONSTRAINT convenios_pkey PRIMARY KEY (conv_id);


--
-- TOC entry 1873 (class 2606 OID 57454)
-- Name: pacientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pacientes
    ADD CONSTRAINT pacientes_pkey PRIMARY KEY (pac_id);


--
-- TOC entry 1879 (class 2606 OID 57476)
-- Name: permissoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY permissoes
    ADD CONSTRAINT permissoes_pkey PRIMARY KEY (perm_id);


--
-- TOC entry 1877 (class 2606 OID 57467)
-- Name: semanal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY semanal
    ADD CONSTRAINT semanal_pkey PRIMARY KEY (sem_data, pac_id);


--
-- TOC entry 1875 (class 2606 OID 57462)
-- Name: sess_sem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sess_sem
    ADD CONSTRAINT sess_sem_pkey PRIMARY KEY (sess_sem_id);


--
-- TOC entry 1871 (class 2606 OID 57444)
-- Name: sessoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sessoes
    ADD CONSTRAINT sessoes_pkey PRIMARY KEY (sess_id);


--
-- TOC entry 1867 (class 2606 OID 57411)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usu_id);


--
-- TOC entry 1993 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-03-12 15:52:52

--
-- PostgreSQL database dump complete
--

