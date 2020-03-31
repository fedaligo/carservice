--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-30 19:44:18

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
-- TOC entry 205 (class 1259 OID 16404)
-- Name: tracking_system; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.tracking_system (
    id bigint NOT NULL,
    id_task bigint,
    id_organaizer bigint,
    status character varying(40),
    confirm_date date,
    cost integer
);


ALTER TABLE public.tracking_system OWNER TO test;

--
-- TOC entry 210 (class 1259 OID 16432)
-- Name: tracking_system_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.tracking_system_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tracking_system_id_seq OWNER TO test;

--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 210
-- Name: tracking_system_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.tracking_system_id_seq OWNED BY public.tracking_system.id;


--
-- TOC entry 2715 (class 2604 OID 16434)
-- Name: tracking_system id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.tracking_system ALTER COLUMN id SET DEFAULT nextval('public.tracking_system_id_seq'::regclass);


--
-- TOC entry 2847 (class 0 OID 16404)
-- Dependencies: 205
-- Data for Name: tracking_system; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.tracking_system (id, id_task, id_organaizer, status, confirm_date, cost) FROM stdin;
1	1	1	confirm	2019-11-01	180
4	4	4	confirm	2019-11-15	222
5	5	5	confirm	2019-11-20	277
6	6	6	confirm	2019-11-25	207
7	7	7	confirm	2019-11-30	283
8	8	8	confirm	2019-12-05	152
9	9	9	confirm	2019-12-10	62
10	10	10	confirm	2019-12-15	90
11	11	11	confirm	2019-12-20	207
12	12	12	confirm	2019-12-25	180
13	13	13	confirm	2019-12-30	147
14	14	14	confirm	2020-01-05	139
15	15	15	confirm	2020-01-10	145
16	16	16	confirm	2020-01-15	137
17	17	17	confirm	2020-01-20	133
18	18	18	confirm	2020-01-25	54
2	2	1	confirm	2019-11-05	111
\.


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 210
-- Name: tracking_system_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.tracking_system_id_seq', 20, true);


--
-- TOC entry 2718 (class 2606 OID 16440)
-- Name: tracking_system tracking_system_pk; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.tracking_system
    ADD CONSTRAINT tracking_system_pk PRIMARY KEY (id);


--
-- TOC entry 2716 (class 1259 OID 16438)
-- Name: tracking_system_id_uindex; Type: INDEX; Schema: public; Owner: test
--

CREATE UNIQUE INDEX tracking_system_id_uindex ON public.tracking_system USING btree (id);


--
-- TOC entry 2719 (class 2606 OID 16518)
-- Name: tracking_system tracking_system_m_organization_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.tracking_system
    ADD CONSTRAINT tracking_system_m_organization_id_fk FOREIGN KEY (id_organaizer) REFERENCES public.m_organization(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2720 (class 2606 OID 16523)
-- Name: tracking_system tracking_system_m_tasks_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.tracking_system
    ADD CONSTRAINT tracking_system_m_tasks_id_fk FOREIGN KEY (id_task) REFERENCES public.m_tasks(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-03-30 19:44:19

--
-- PostgreSQL database dump complete
--

