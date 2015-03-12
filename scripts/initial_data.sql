--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-03-12 16:17:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 1969 (class 0 OID 57414)
-- Dependencies: 173
-- Data for Name: convenios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO convenios (conv_id, conv_tipo) VALUES (1, 'CAURN');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (2, 'HAPVIDA');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (3, 'UNIMED');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (4, 'medmais');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (5, 'PARTICULAR');


--
-- TOC entry 1978 (class 0 OID 0)
-- Dependencies: 172
-- Name: convenios_conv_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('convenios_conv_id_seq', 6, true);


--
-- TOC entry 1973 (class 0 OID 57471)
-- Dependencies: 182
-- Data for Name: permissoes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1979 (class 0 OID 0)
-- Dependencies: 181
-- Name: permissoes_perm_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permissoes_perm_id_seq', 1, false);


--
-- TOC entry 1971 (class 0 OID 57439)
-- Dependencies: 175
-- Data for Name: sessoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sessoes (sess_id, sess_tipo) VALUES (2, 'Deslizamento do calcanhar');
INSERT INTO sessoes (sess_id, sess_tipo) VALUES (3, 'Contração do Quadríceps');
INSERT INTO sessoes (sess_id, sess_tipo) VALUES (4, 'Elevação de Perna Estendida');
INSERT INTO sessoes (sess_id, sess_tipo) VALUES (5, 'Flexão de Bruços do Joelho');


--
-- TOC entry 1980 (class 0 OID 0)
-- Dependencies: 174
-- Name: sessoes_sess_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sessoes_sess_id_seq', 12, true);


-- Completed on 2015-03-12 16:17:46

--
-- PostgreSQL database dump complete
--

