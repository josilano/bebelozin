--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-03-12 16:20:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 1990 (class 0 OID 57414)
-- Dependencies: 173
-- Data for Name: convenios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO convenios (conv_id, conv_tipo) VALUES (1, 'CAURN');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (2, 'HAPVIDA');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (3, 'UNIMED');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (4, 'medmais');
INSERT INTO convenios (conv_id, conv_tipo) VALUES (5, 'PARTICULAR');


--
-- TOC entry 2005 (class 0 OID 0)
-- Dependencies: 172
-- Name: convenios_conv_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('convenios_conv_id_seq', 6, true);


--
-- TOC entry 1994 (class 0 OID 57449)
-- Dependencies: 177
-- Data for Name: pacientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pacientes (pac_id, pac_nome, pac_tel, pac_doenca, conv_tipo) VALUES (24, 'goku', '(234) 5453-6577', 'ki baixo', 'HAPVIDA');
INSERT INTO pacientes (pac_id, pac_nome, pac_tel, pac_doenca, conv_tipo) VALUES (25, 'piccolo', '(234) 5345-3661', 'muito verde', 'PARTICULAR');
INSERT INTO pacientes (pac_id, pac_nome, pac_tel, pac_doenca, conv_tipo) VALUES (26, 'madimbu', '(123) 4567-8989', 'gordura morbida', 'HAPVIDA');


--
-- TOC entry 2006 (class 0 OID 0)
-- Dependencies: 176
-- Name: pacientes_pac_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pacientes_pac_id_seq', 27, true);


--
-- TOC entry 1999 (class 0 OID 57471)
-- Dependencies: 182
-- Data for Name: permissoes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2007 (class 0 OID 0)
-- Dependencies: 181
-- Name: permissoes_perm_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permissoes_perm_id_seq', 1, false);


--
-- TOC entry 1997 (class 0 OID 57463)
-- Dependencies: 180
-- Data for Name: semanal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO semanal (sem_data, pac_id, sem_pagmto, conv_tipo, sem_id, pac_nome, pac_doenca) VALUES ('2015-03-11', 24, false, 'HAPVIDA', 28, 'goku', 'ki baixo');
INSERT INTO semanal (sem_data, pac_id, sem_pagmto, conv_tipo, sem_id, pac_nome, pac_doenca) VALUES ('2015-03-11', 26, true, 'HAPVIDA', 29, 'madimbu', 'gordura morbida');
INSERT INTO semanal (sem_data, pac_id, sem_pagmto, conv_tipo, sem_id, pac_nome, pac_doenca) VALUES ('2015-03-11', 25, true, 'PARTICULAR', 30, 'piccolo', 'muito verde');


--
-- TOC entry 2008 (class 0 OID 0)
-- Dependencies: 183
-- Name: semanal_sem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('semanal_sem_id_seq', 30, true);


--
-- TOC entry 1996 (class 0 OID 57457)
-- Dependencies: 179
-- Data for Name: sess_sem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sess_sem (sess_sem_id, sem_data, pac_id, sess_tipo, sem_pagmto, conv_tipo, pac_nome, pac_doenca) VALUES (55, '2015-03-11', 24, 'Deslizamento do calcanhar', false, 'HAPVIDA', 'goku', 'ki baixo');
INSERT INTO sess_sem (sess_sem_id, sem_data, pac_id, sess_tipo, sem_pagmto, conv_tipo, pac_nome, pac_doenca) VALUES (56, '2015-03-11', 26, 'Elevação de Perna Estendida', true, 'HAPVIDA', 'madimbu', 'gordura morbida');
INSERT INTO sess_sem (sess_sem_id, sem_data, pac_id, sess_tipo, sem_pagmto, conv_tipo, pac_nome, pac_doenca) VALUES (57, '2015-03-11', 25, 'Flexão de Bruços do Joelho', true, 'PARTICULAR', 'piccolo', 'muito verde');


--
-- TOC entry 2009 (class 0 OID 0)
-- Dependencies: 178
-- Name: sess_sem_sess_sem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sess_sem_sess_sem_id_seq', 57, true);


--
-- TOC entry 1992 (class 0 OID 57439)
-- Dependencies: 175
-- Data for Name: sessoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sessoes (sess_id, sess_tipo) VALUES (2, 'Deslizamento do calcanhar');
INSERT INTO sessoes (sess_id, sess_tipo) VALUES (3, 'Contração do Quadríceps');
INSERT INTO sessoes (sess_id, sess_tipo) VALUES (4, 'Elevação de Perna Estendida');
INSERT INTO sessoes (sess_id, sess_tipo) VALUES (5, 'Flexão de Bruços do Joelho');


--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 174
-- Name: sessoes_sess_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sessoes_sess_id_seq', 12, true);


--
-- TOC entry 1988 (class 0 OID 57406)
-- Dependencies: 171
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (usu_id, usu_nome, usu_tel, usu_senha, usu_valor_consulta) VALUES (1, 'admin', '(084) 3212-3423', 'senha', 15);


--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 170
-- Name: usuario_id_usu_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_id_usu_seq', 1, true);


-- Completed on 2015-03-12 16:20:12

--
-- PostgreSQL database dump complete
--

