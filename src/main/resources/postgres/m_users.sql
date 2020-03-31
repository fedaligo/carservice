--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-30 19:43:46

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
-- TOC entry 202 (class 1259 OID 16395)
-- Name: m_users; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.m_users (
    id bigint NOT NULL,
    login character varying(20),
    password character varying(20),
    created timestamp without time zone,
    changed timestamp without time zone,
    is_deleted boolean,
    e_mail character varying(50),
    phone_number_user bigint,
    gender character varying DEFAULT 'NOT_SELECTED'::character varying NOT NULL
);


ALTER TABLE public.m_users OWNER TO test;

--
-- TOC entry 213 (class 1259 OID 16462)
-- Name: m_users_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.m_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.m_users_id_seq OWNER TO test;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 213
-- Name: m_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.m_users_id_seq OWNED BY public.m_users.id;


--
-- TOC entry 2715 (class 2604 OID 16464)
-- Name: m_users id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_users ALTER COLUMN id SET DEFAULT nextval('public.m_users_id_seq'::regclass);


--
-- TOC entry 2846 (class 0 OID 16395)
-- Dependencies: 202
-- Data for Name: m_users; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.m_users (id, login, password, created, changed, is_deleted, e_mail, phone_number_user, gender) FROM stdin;
4	butterfly	444q444	2019-04-05 20:04:17	2019-04-05 20:04:17	\N	butterfly@gmail.com	80294444444	NOT_SELECTED
5	ram	555q555	2019-04-10 20:04:17	2019-04-10 20:04:17	\N	ram@gmail.com	80295555555	NOT_SELECTED
10	bull	1010q10	2019-05-05 20:04:17	2019-05-05 20:04:17	\N	bull@gmail.com	80291010101	NOT_SELECTED
11	wolf	1111q11	2019-05-10 20:04:17	2019-05-10 20:04:17	\N	wolf@gmail.com	80291101101	NOT_SELECTED
8	beaver	888q888	2019-04-25 20:04:17	2019-04-25 20:04:17	\N	beaver@gmail.com	80298888888	NOT_SELECTED
9	ladybird	999q999	2019-04-30 20:04:17	2019-04-30 20:04:17	\N	ladybird@gmail.com	80299999999	NOT_SELECTED
14	raven	1414q14	2019-05-25 20:04:17	2019-05-25 20:04:17	t	raven@gmail.com	80291414141	NOT_SELECTED
15	crow	1515q15	2019-05-30 20:04:17	2019-05-30 20:04:17	\N	crow@gmail.com	80291515151	NOT_SELECTED
12	budgie	1212q12	2019-05-15 20:04:17	2019-05-15 20:04:17	\N	budgie@gmail.com	80291212121	NOT_SELECTED
13	sparrow	1313q13	2019-05-20 20:04:17	2019-05-20 20:04:17	\N	sparrow@gmail.com	80291313131	NOT_SELECTED
18	gorilla	1818q18	2019-06-15 20:04:17	2019-06-15 20:04:17	\N	gorilla@gmail.com	80291818181	NOT_SELECTED
19	vulture	1919q19	2019-06-20 20:04:17	2019-06-20 20:04:17	\N	vulture@gmail.com	80291919191	NOT_SELECTED
16	viper	1616q16	2019-06-05 20:04:17	2019-06-05 20:04:17	t	viper@gmail.com	80291616161	NOT_SELECTED
17	pigeon	1717q17	2019-06-10 20:04:17	2019-06-10 20:04:17	t	pigeon@gmail.com	80291717171	NOT_SELECTED
22	blackbird	2222q22	2019-07-05 20:04:17	2019-07-05 20:04:17	\N	blackbird@gmail.com	80292202202	NOT_SELECTED
23	woodpeacker	2323q23	2019-07-10 20:04:17	2019-07-10 20:04:17	t	woodpeacker@gmail.com	80292323232	NOT_SELECTED
20	caterpillar	2020q20	2019-06-25 20:04:17	2019-06-25 20:04:17	\N	caterpillar@gmail.com	80292020202	NOT_SELECTED
21	goose	2121q21	2019-06-30 20:04:17	2019-06-30 20:04:17	\N	goose@gmail.com	80292121212	NOT_SELECTED
26	giraffe	2626q26	2019-07-25 20:04:17	2019-07-25 20:04:17	\N	giraffe@gmail.com	80292626262	NOT_SELECTED
27	beetle	2727q27	2019-07-30 20:04:17	2019-07-30 20:04:17	t	beetle@gmail.com	80292727272	NOT_SELECTED
24	hedgehog	2424q24	2019-07-15 20:04:17	2019-07-15 20:04:17	\N	hedgehog@gmail.com	80292424242	NOT_SELECTED
25	lark	2525q25	2019-07-20 20:04:17	2019-07-20 20:04:17	\N	lark@gmail.com	80292525252	NOT_SELECTED
2	shark	222q222	2019-03-26 20:03:43	2019-03-26 20:03:43	\N	shark@gmail.com	80292222222	NOT_SELECTED
3	antelope	333q333	2019-03-31 20:03:55	2019-03-31 20:03:55	\N	antelope@gmail.com	80293333333	NOT_SELECTED
1	stork	111q111	2019-03-21 20:03:27	2019-03-21 20:03:27	\N	stork@gmail.com	80291111111	NOT_SELECTED
6	badger	666q666	2019-04-15 20:04:17	2019-04-15 20:04:17	t	badger@gmail.com	80296666666	NOT_SELECTED
7	squirrel	777q777	2019-04-20 20:04:17	2019-04-20 20:04:17	\N	squirrel@gmail.com	80297777777	NOT_SELECTED
48	mmmm	mmmmm	2020-03-29 19:46:59.402	2020-03-29 19:46:59.402	\N	mmmmm@mail.ru	80295555555	NOT_SELECTED
49	mmmm	mmmmm	2020-03-29 20:18:36.384	2020-03-29 20:18:36.384	\N	mmmmm@mail.ru	80295555555	NOT_SELECTED
43	werwer	erer12123g	2020-03-28 19:35:01.98	2020-03-28 19:35:01.98	\N	btbtbtbt@mail.com	80291234567	NOT_SELECTED
40	rtudj	aesldkkf111	2020-03-23 19:49:39.287	2020-03-23 19:49:39.287	\N	dmmfkjwfd@mail.ru	80291234567	NOT_SELECTED
41	test	test111	2020-03-28 17:08:00.268	2020-03-28 17:08:00.268	\N	test@mail.ru	80299999999	NOT_SELECTED
47	gfafafa	fafafa123124	2020-03-29 18:23:36.182	2020-03-29 18:23:36.182	\N	fafa@mail.com	80441235555	NOT_SELECTED
44	styutyu	ryrtyrty345345	2020-03-28 19:36:23.189	2020-03-29 18:20:28.744	\N	betrtrt456@mail.ru	80294561256	NOT_SELECTED
29	jdbcTest	jdbc1jdbc	2020-03-23 18:27:22.225	2020-03-23 18:27:22.225	\N	jdbc@mail.ru	80291234567	NOT_SELECTED
50	mmmm	mmmmm	2020-03-29 20:21:21.802	2020-03-29 20:21:21.802	t	mmmmm@mail.ru	80295555555	NOT_SELECTED
\.


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 213
-- Name: m_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.m_users_id_seq', 50, true);


--
-- TOC entry 2719 (class 2606 OID 16470)
-- Name: m_users m_users_pk; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_users
    ADD CONSTRAINT m_users_pk PRIMARY KEY (id);


--
-- TOC entry 2717 (class 1259 OID 16468)
-- Name: m_users_id_uindex; Type: INDEX; Schema: public; Owner: test
--

CREATE UNIQUE INDEX m_users_id_uindex ON public.m_users USING btree (id);


-- Completed on 2020-03-30 19:43:46

--
-- PostgreSQL database dump complete
--

