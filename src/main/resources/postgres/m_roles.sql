--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-30 19:42:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16398)
-- Name: m_roles; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.m_roles (
    id bigint NOT NULL,
    name_of_role character varying(30),
    user_id bigint
);


ALTER TABLE public.m_roles OWNER TO test;

--
-- TOC entry 212 (class 1259 OID 16453)
-- Name: m_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.m_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.m_roles_id_seq OWNER TO test;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 212
-- Name: m_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.m_roles_id_seq OWNED BY public.m_roles.id;


--
-- TOC entry 2715 (class 2604 OID 16455)
-- Name: m_roles id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_roles ALTER COLUMN id SET DEFAULT nextval('public.m_roles_id_seq'::regclass);


--
-- TOC entry 2846 (class 0 OID 16398)
-- Dependencies: 203
-- Data for Name: m_roles; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.m_roles (id, name_of_role, user_id) FROM stdin;
1	ROLE_ADMIN	1
2	ROLE_USER	2
3	ROLE_USER	3
4	ROLE_USER	4
5	ROLE_USER	5
6	ROLE_USER	6
7	ROLE_USER	7
8	ROLE_USER	8
9	ROLE_USER	9
10	ROLE_USER	10
11	ROLE_USER	11
12	ROLE_USER	12
13	ROLE_USER	13
14	ROLE_USER	14
15	ROLE_USER	15
16	ROLE_USER	16
17	ROLE_USER	17
18	ROLE_USER	18
19	ROLE_USER	19
20	ROLE_USER	20
21	ROLE_USER	21
22	ROLE_ADMIN	22
23	ROLE_USER	23
24	ROLE_USER	24
25	ROLE_USER	25
26	ROLE_USER	26
27	ROLE_USER	27
30	ROLE_USER	40
31	ROLE_USER	43
32	ROLE_USER	44
33	ROLE_USER	47
34	ROLE_USER	48
35	ROLE_USER	49
36	ROLE_USER	50
\.


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 212
-- Name: m_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.m_roles_id_seq', 36, true);


--
-- TOC entry 2718 (class 2606 OID 16461)
-- Name: m_roles m_roles_pk; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_roles
    ADD CONSTRAINT m_roles_pk PRIMARY KEY (id);


--
-- TOC entry 2716 (class 1259 OID 16459)
-- Name: m_roles_id_uindex; Type: INDEX; Schema: public; Owner: test
--

CREATE UNIQUE INDEX m_roles_id_uindex ON public.m_roles USING btree (id);


--
-- TOC entry 2719 (class 2606 OID 16505)
-- Name: m_roles m_roles_m_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_roles
    ADD CONSTRAINT m_roles_m_users_id_fk FOREIGN KEY (user_id) REFERENCES public.m_users(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-03-30 19:42:56

--
-- PostgreSQL database dump complete
--

