--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-03-30 19:34:43

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
-- TOC entry 206 (class 1259 OID 16407)
-- Name: m_car; Type: TABLE; Schema: public; Owner: test
--

CREATE TABLE public.m_car (
    id bigint NOT NULL,
    car_brand character varying(30),
    brand_model character varying(30),
    type_of_transmission character varying(2),
    type_of_fuel character varying(10),
    user_id bigint,
    car_weight bigint,
    vin_number character varying(17)
);


ALTER TABLE public.m_car OWNER TO test;

--
-- TOC entry 208 (class 1259 OID 16413)
-- Name: m_car_id_seq; Type: SEQUENCE; Schema: public; Owner: test
--

CREATE SEQUENCE public.m_car_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.m_car_id_seq OWNER TO test;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 208
-- Name: m_car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: test
--

ALTER SEQUENCE public.m_car_id_seq OWNED BY public.m_car.id;


--
-- TOC entry 2715 (class 2604 OID 16415)
-- Name: m_car id; Type: DEFAULT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_car ALTER COLUMN id SET DEFAULT nextval('public.m_car_id_seq'::regclass);


--
-- TOC entry 2846 (class 0 OID 16407)
-- Dependencies: 206
-- Data for Name: m_car; Type: TABLE DATA; Schema: public; Owner: test
--

COPY public.m_car (id, car_brand, brand_model, type_of_transmission, type_of_fuel, user_id, car_weight, vin_number) FROM stdin;
45	lexus	rx350	at	gasoline	16	1908	987h7dsh8j78f
51	mclaren	mp4-12c	mt	gasoline	18	1063	876fg86df6gg6
60	peugeot	307	at	diesel	11	1341	6dfg876d7fg67
23	faw	besturn b50	mt	diesel	18	1475	s0df0s98df098
5	audy	a4	mt	gasoline	3	1654	sd7f987ds9f7h
53	mercury	mariner	mt	diesel	20	1839	s7d65f86s8gh8
32	honda	accord	at	diesel	24	1075	h7m9g8hj97ghj
19	daihatsu	applause	at	gasoline	7	1585	87s6g87s6g86s
28	gmc	acadia	at	gasoline	6	1718	g76h87fg6hjf7
17	dacia	logan	mt	diesel	9	2000	gf876dh87gh68
46	lifan	breez	at	diesel	15	1088	g08h7fg87gf9h
15	chrysler	cirrus	mt	gasoline	14	1699	5g4hfg64h6fgf
8	brilliance	v5	mt	diesel	11	1805	s76df876sd6fd
14	chevrolet	tahoe	mt	gasoline	19	1927	8s5g65sf7g7df
13	chery	tiggo	at	diesel	27	1730	sdf7s6g8d6gh6
16	citroen	c5	mt	gasoline	24	1549	76sdf5gs76df5
55	mini	cooper	mt	diesel	6	1978	8sd7f97sd7f7s
40	jeep	cherokee	at	diesel	3	1466	g9hk769h7j97d
43	lancia	delta	mt	diesel	24	1332	d9y9g7987agad
21	dodge	caliber	mt	gasoline	23	1726	c9vb79d7g87dg
34	hyundai	solaris	mt	gasoline	15	1442	sd877f68sd76g
4	asoton martin	dbs	at	gasoline	5	1969	s9d8f7gf987hf
41	kia	ceed	at	gasoline	2	1531	97sdgh97sgh7g
24	fiat	punto	at	diesel	14	1084	hj087gh8jgh8j
37	isuzu	amigo	mt	diesel	23	1102	h9jy9ghjk9d98
42	lada	granta	mt	gasoline	13	1938	hj987dghj98dh
10	byd	flyer	mt	diesel	10	1939	gsfy89gdf9gh9
26	fso	syrena	at	gasoline	20	1278	fg87h8fg76h87
7	bmw	320	mt	diesel	7	1401	7g68fd7g687df
3	aro	24	mt	diesel	18	1499	7sdf97sdf98s9
22	dong feng	h30	mt	gasoline	2	1662	sd9g87dg9h9g7
9	buick	enclave	at	diesel	9	1293	7hf8j79gh8jhh
1	acura	cl	mt	gasoline	3	1822	s098gs08df0s8
2	alfa romeo	145	mt	gasoline	3	1591	sd9ff7g6d87fg
47	lincoln	aviator	at	diesel	21	1677	dfg87df87gdf8
20	datsun	on-do	at	gasoline	3	1784	g86hfg86j8fg6
29	great wall	florid	mt	gasoline	8	1677	7f6hj97hj7gh9
50	mazda	cx-5	mt	gasoline	3	1645	sdg8g8dg767g8
44	land rover	freelander	at	gasoline	19	1341	hj0ghj08g08jg
36	iran khodro	samand	mt	gasoline	3	1520	786jk7gh6jgh7
35	infinity	fx35	mt	gasoline	5	1699	8gh6j6hg8jgjj
11	cadillac	catera	at	diesel	7	1510	sd79f69dghhh9
52	mercedes-benz	gls	mt	gasoline	8	1046	gf76h86fg8h6h
6	bentley	bentayga	at	gasoline	20	2000	sd9f87sd978hd
31	haval	h6	mt	diesel	9	1766	76df86dsg86fh
12	changan	cs35	at	diesel	6	1857	fhh97h756d6s8
33	hummer	h2	mt	gasoline	19	1896	f6gh7fg6j867g
48	mahindra	pik-ip	at	diesel	11	1560	as87af6a86f8s
30	hafei	lobo	mt	gasoline	4	1743	76sg768g76h7g
58	oldsmobile	achieva	at	gasoline	5	1470	sd5f56sd6f65d
57	nissan	almera	mt	diesel	8	1511	76g65d7fg6dfg
59	opel	omega	at	diesel	16	1344	67sd8f76sd6d6
18	daewoo	espero	mt	diesel	5	1865	7f6g7hfj798fg
27	geely	emgrand	at	diesel	16	1358	ds98f79f87gfg
39	jaguar	f-type	mt	diesel	20	1242	gf98hf9gh9fg7
38	jac	refine	mt	diesel	27	1239	dfh7jdf7j87df
25	ford	mondeo	at	diesel	7	1261	df6s987g687fg
54	mg	750	at	gasoline	2	1083	7dfg8766dfg87
61	zzzzzz	zzzzzz	zz	zzzzz	5	22222	zzzzzzzz
64	zzzzzz	zzzzzz	zz	zzzzzz	5	22220	zzzzz4334
65	kkkkkkk	kkkkkkkk	kk	kkkkkk	1	5660	kkkkkkk
\.


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 208
-- Name: m_car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: test
--

SELECT pg_catalog.setval('public.m_car_id_seq', 65, true);


--
-- TOC entry 2718 (class 2606 OID 16512)
-- Name: m_car m_car_pk; Type: CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_car
    ADD CONSTRAINT m_car_pk PRIMARY KEY (id);


--
-- TOC entry 2716 (class 1259 OID 16510)
-- Name: m_car_id_uindex; Type: INDEX; Schema: public; Owner: test
--

CREATE UNIQUE INDEX m_car_id_uindex ON public.m_car USING btree (id);


--
-- TOC entry 2719 (class 2606 OID 16499)
-- Name: m_car m_car_m_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: test
--

ALTER TABLE ONLY public.m_car
    ADD CONSTRAINT m_car_m_users_id_fk FOREIGN KEY (user_id) REFERENCES public.m_users(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-03-30 19:34:44

--
-- PostgreSQL database dump complete
--

