--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-30 19:40:08

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
-- TOC entry 207 (class 1259 OID 16410)
-- Name: m_organization; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.m_organization (
    id bigint NOT NULL,
    name character varying(30),
    web_site character varying(30),
    phone_number bigint,
    location character varying(50),
    working_time character varying(30),
    specialize character varying(50),
    e_mail character varying(50)
);


ALTER TABLE public.m_organization OWNER TO test;

--
-- TOC entry 209 (class 1259 OID 16423)
-- Name: m_organization_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.m_organization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.m_organization_id_seq OWNER TO test;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 209
-- Name: m_organization_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.m_organization_id_seq OWNED BY public.m_organization.id;


--
-- TOC entry 2715 (class 2604 OID 16425)
-- Name: m_organization id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_organization ALTER COLUMN id SET DEFAULT nextval('public.m_organization_id_seq'::regclass);


--
-- TOC entry 2845 (class 0 OID 16410)
-- Dependencies: 207
-- Data for Name: m_organization; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.m_organization (id, name, web_site, phone_number, location, working_time, specialize, e_mail) FROM stdin;
22	sto22	sto22.by	80292225620	minsk	09.00-19.00	any car	sto22@gmail.com
7	sto7	sto7.by	80297775677	minsk	09.00-19.00	any car	sto7@gmail.com
4	sto4	sto4.by	80294445644	minsk	09.00-19.00	aston martin	sto4@gmail.com
27	sto27	sto27.by	80292725627	minsk	00.00-00.00	any car	sto27@gmail.com
21	sto21	sto21.by	80292125621	minsk	09.00-19.00	doodge	sto21@gmail.com
1	sto1	sto1.by	80291115611	minsk	09.00-19.00	acura	sto1@gmail.com
28	sto28	sto28.by	80292825628	minsk	00.00-00.00	any car	sto28@gmail.com
6	sto6	sto6.by	80296665666	minsk	09.00-19.00	bentley	sto6@gmail.com
2	sto2	sto2.by	80292225622	minsk	00.00-00.00	alfa romeo	sto2@gmail.com
26	sto26	sto26.by	80292625626	minsk	09.00-19.00	any car	sto26@gmail.com
5	sto5	sto5.by	80295555655	minsk	09.00-19.00	audi	sto5@gmail.com
23	sto23	sto23.by	80292325623	minsk	09.00-19.00	faw	sto23@gmail.com
3	sto3	sto3.by	80293335633	minsk	09.00-19.00	any car	sto3@gmail.com
12	sto12	sto12.by	80291215612	minsk	09.00-19.00	changan	sto12@gmail.com
15	sto15	sto15.by	80291515615	minsk	00.00-00.00	any car	sto15@gmail.com
20	sto20	sto20.by	80292025620	minsk	09.00-19.00	datsun	sto20@gmail.com
19	sto19	sto19.by	80291915619	minsk	00.00-00.00	any car	sto19@gmail.com
18	sto18	sto18.by	80291815618	minsk	09.00-19.00	daewoo	sto18@gmail.com
17	sto17	sto17.by	80291715617	minsk	09.00-19.00	dacia	sto17@gmail.com
25	sto25	sto25.by	80292525625	minsk	00.00-00.00	ford	sto25@gmail.com
24	sto24	sto24.by	80292425624	minsk	09.00-19.00	fiat	sto24@gmail.com
10	sto10	sto10.by	80291015610	minsk	09.00-19.00	byd	sto10@gmail.com
9	sto9	sto9.by	80299995699	minsk	09.00-19.00	any car	sto9@gmail.com
11	sto11	sto11.by	80291105611	minsk	09.00-19.00	cadillac	sto11@gmail.com
14	sto14	sto14.by	80291415614	minsk	09.00-19.00	any car	sto14@gmail.com
8	sto8	sto8.by	80298885688	minsk	00.00-00.00	brilliance	sto8@gmail.com
13	sto13	sto13.by	80291315613	minsk	09.00-19.00	any car	sto13@gmail.com
16	sto16	sto16.by	80291615616	minsk	09.00-19.00	citroen	sto16@gmail.com
\.


--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 209
-- Name: m_organization_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.m_organization_id_seq', 31, true);


--
-- TOC entry 2718 (class 2606 OID 16431)
-- Name: m_organization m_organization_pk; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_organization
    ADD CONSTRAINT m_organization_pk PRIMARY KEY (id);


--
-- TOC entry 2716 (class 1259 OID 16429)
-- Name: m_organization_id_uindex; Type: INDEX; Schema: public; Owner: test
--

CREATE UNIQUE INDEX m_organization_id_uindex ON public.m_organization USING btree (id);


-- Completed on 2020-03-30 19:40:09

--
-- PostgreSQL database dump complete
--

