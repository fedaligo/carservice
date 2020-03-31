--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-30 19:43:19

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
-- TOC entry 204 (class 1259 OID 16401)
-- Name: m_tasks; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.m_tasks (
    id bigint NOT NULL,
    service_work_name character varying(100),
    necessity_of_evacuation boolean,
    wheel_brake boolean,
    id_car bigint,
    created date,
    description character varying(1000),
    latitude character varying(10),
    longitude character varying(10),
    local_description character varying(50)
);


ALTER TABLE public.m_tasks OWNER TO test;

--
-- TOC entry 211 (class 1259 OID 16441)
-- Name: m_tasks_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.m_tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.m_tasks_id_seq OWNER TO test;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 211
-- Name: m_tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.m_tasks_id_seq OWNED BY public.m_tasks.id;


--
-- TOC entry 2715 (class 2604 OID 16443)
-- Name: m_tasks id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_tasks ALTER COLUMN id SET DEFAULT nextval('public.m_tasks_id_seq'::regclass);


--
-- TOC entry 2846 (class 0 OID 16401)
-- Dependencies: 204
-- Data for Name: m_tasks; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.m_tasks (id, service_work_name, necessity_of_evacuation, wheel_brake, id_car, created, description, latitude, longitude, local_description) FROM stdin;
1	change oil and filters	\N	\N	45	2019-11-01	\N	\N	\N	\N
4	brake fluid replacement	\N	\N	52	2019-11-15	\N	\N	\N	\N
6	gear repair	\N	\N	14	2019-11-25	\N	\N	\N	\N
8	radiator replacement	\N	\N	59	2019-12-01	\N	\N	\N	\N
9	running gear repair	\N	\N	29	2019-12-05	\N	\N	\N	\N
12	steering repair	\N	\N	16	2019-12-20	\N	\N	\N	\N
15	replacing spark plugs	\N	\N	26	2020-01-05	\N	\N	\N	\N
16	air canditioner refueling	\N	\N	15	2020-01-10	\N	\N	\N	\N
18	timing belt replacement	\N	\N	24	2020-01-20	\N	\N	\N	\N
13	diagnostic	t	t	29	2019-12-25	i smell of smoke in the car	53.8371	27.6951	shabani
7	diagnostic	t	t	15	2019-11-30	traffic accident	53.8533	27.4912	malinovka
5	diagnostic	t	\N	29	2019-11-20	i hear strange sounds from the engine	53.8715	27.4658	kamennaya gorka
10	diagnostic	t	\N	3	2019-12-10	right wheel is broken	53.8513	27.6093	chizovka
14	diagnostic	t	\N	3	2019-12-30	traffic accident	53.8995	27.6526	avtozavod
11	diagnostic	t	\N	40	2019-12-15	the car is stoped	53.8978	27.5626	center
17	diagnostic	t	t	31	2020-01-15	traffic accident	53.9399	27.7027	uruchie
2	diagnostic	t	\N	30	2019-11-05	the car is stoped	53.9338	27.4527	vesnianka
21	qqqqqq	t	t	22	2020-03-30	qqqqqqq	23320	43340	qqqqqqq
\.


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 211
-- Name: m_tasks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.m_tasks_id_seq', 21, true);


--
-- TOC entry 2718 (class 2606 OID 16452)
-- Name: m_tasks m_tasks_pk; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_tasks
    ADD CONSTRAINT m_tasks_pk PRIMARY KEY (id);


--
-- TOC entry 2716 (class 1259 OID 16450)
-- Name: m_tasks_id_uindex; Type: INDEX; Schema: public; Owner: test
--

CREATE UNIQUE INDEX m_tasks_id_uindex ON public.m_tasks USING btree (id);


--
-- TOC entry 2719 (class 2606 OID 16513)
-- Name: m_tasks m_tasks_m_car_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_tasks
    ADD CONSTRAINT m_tasks_m_car_id_fk FOREIGN KEY (id_car) REFERENCES public.m_car(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-03-30 19:43:19

--
-- PostgreSQL database dump complete
--

