--
-- PostgreSQL database dump
--

-- Dumped from database version 15
-- Dumped by pg_dump version 15.3

-- Started on 2023-08-01 08:21:21 PDT

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
-- TOC entry 214 (class 1259 OID 65548)
-- Name: avatar_media; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.avatar_media (
    worker_id bigint NOT NULL,
    media_id bigint NOT NULL
);


ALTER TABLE public.avatar_media OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 65552)
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    id bigint NOT NULL,
    archive boolean NOT NULL,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    name character varying(255),
    region_id bigint
);


ALTER TABLE public.department OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 65551)
-- Name: department_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.department_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.department_id_seq OWNER TO postgres;

--
-- TOC entry 3674 (class 0 OID 0)
-- Dependencies: 215
-- Name: department_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.department_id_seq OWNED BY public.department.id;


--
-- TOC entry 217 (class 1259 OID 65558)
-- Name: illustrator_media; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.illustrator_media (
    worker_id bigint NOT NULL,
    media_id bigint NOT NULL
);


ALTER TABLE public.illustrator_media OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 65562)
-- Name: media; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.media (
    id bigint NOT NULL,
    archive boolean NOT NULL,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    image character varying(255)
);


ALTER TABLE public.media OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 65561)
-- Name: media_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.media_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.media_id_seq OWNER TO postgres;

--
-- TOC entry 3675 (class 0 OID 0)
-- Dependencies: 218
-- Name: media_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.media_id_seq OWNED BY public.media.id;


--
-- TOC entry 221 (class 1259 OID 65569)
-- Name: profession; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profession (
    id bigint NOT NULL,
    archive boolean NOT NULL,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    name character varying(255)
);


ALTER TABLE public.profession OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 65568)
-- Name: profession_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.profession_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.profession_id_seq OWNER TO postgres;

--
-- TOC entry 3676 (class 0 OID 0)
-- Dependencies: 220
-- Name: profession_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.profession_id_seq OWNED BY public.profession.id;


--
-- TOC entry 222 (class 1259 OID 65575)
-- Name: profession_media; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.profession_media (
    profession_id bigint NOT NULL,
    media_id bigint NOT NULL
);


ALTER TABLE public.profession_media OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 65579)
-- Name: region; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.region (
    id bigint NOT NULL,
    archive boolean NOT NULL,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    name character varying(255)
);


ALTER TABLE public.region OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 65578)
-- Name: region_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.region_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.region_id_seq OWNER TO postgres;

--
-- TOC entry 3677 (class 0 OID 0)
-- Dependencies: 223
-- Name: region_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.region_id_seq OWNED BY public.region.id;


--
-- TOC entry 226 (class 1259 OID 65586)
-- Name: user_app; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_app (
    id bigint NOT NULL,
    archive boolean NOT NULL,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    password character varying(255),
    phone_number character varying(255),
    role character varying(255),
    CONSTRAINT user_app_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);


ALTER TABLE public.user_app OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 65585)
-- Name: user_app_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_app_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_app_id_seq OWNER TO postgres;

--
-- TOC entry 3678 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_app_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_app_id_seq OWNED BY public.user_app.id;


--
-- TOC entry 228 (class 1259 OID 65596)
-- Name: worker; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.worker (
    id bigint NOT NULL,
    archive boolean NOT NULL,
    created_at timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    address character varying(255),
    cin character varying(255),
    description character varying(255),
    full_name character varying(255),
    phone_number character varying(255),
    recommend boolean,
    department_id bigint,
    profession_id bigint,
    region_id bigint
);


ALTER TABLE public.worker OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 65595)
-- Name: worker_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.worker_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.worker_id_seq OWNER TO postgres;

--
-- TOC entry 3679 (class 0 OID 0)
-- Dependencies: 227
-- Name: worker_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.worker_id_seq OWNED BY public.worker.id;


--
-- TOC entry 3476 (class 2604 OID 65555)
-- Name: department id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department ALTER COLUMN id SET DEFAULT nextval('public.department_id_seq'::regclass);


--
-- TOC entry 3477 (class 2604 OID 65565)
-- Name: media id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.media ALTER COLUMN id SET DEFAULT nextval('public.media_id_seq'::regclass);


--
-- TOC entry 3478 (class 2604 OID 65572)
-- Name: profession id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profession ALTER COLUMN id SET DEFAULT nextval('public.profession_id_seq'::regclass);


--
-- TOC entry 3479 (class 2604 OID 65582)
-- Name: region id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.region ALTER COLUMN id SET DEFAULT nextval('public.region_id_seq'::regclass);


--
-- TOC entry 3480 (class 2604 OID 65589)
-- Name: user_app id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_app ALTER COLUMN id SET DEFAULT nextval('public.user_app_id_seq'::regclass);


--
-- TOC entry 3481 (class 2604 OID 65599)
-- Name: worker id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.worker ALTER COLUMN id SET DEFAULT nextval('public.worker_id_seq'::regclass);


--
-- TOC entry 3662 (class 2613 OID 32778)
-- Name: 32778; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('32778');


ALTER LARGE OBJECT 32778 OWNER TO postgres;

--
-- TOC entry 3663 (class 2613 OID 32779)
-- Name: 32779; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('32779');


ALTER LARGE OBJECT 32779 OWNER TO postgres;

--
-- TOC entry 3664 (class 2613 OID 41085)
-- Name: 41085; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('41085');


ALTER LARGE OBJECT 41085 OWNER TO postgres;

--
-- TOC entry 3665 (class 2613 OID 41086)
-- Name: 41086; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('41086');


ALTER LARGE OBJECT 41086 OWNER TO postgres;

--
-- TOC entry 3666 (class 2613 OID 41087)
-- Name: 41087; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('41087');


ALTER LARGE OBJECT 41087 OWNER TO postgres;

--
-- TOC entry 3667 (class 2613 OID 41088)
-- Name: 41088; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('41088');


ALTER LARGE OBJECT 41088 OWNER TO postgres;

--
-- TOC entry 3647 (class 0 OID 65548)
-- Dependencies: 214
-- Data for Name: avatar_media; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.avatar_media (worker_id, media_id) FROM stdin;
5	19
6	13
\.


--
-- TOC entry 3649 (class 0 OID 65552)
-- Dependencies: 216
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.department (id, archive, created_at, updated_at, name, region_id) FROM stdin;
2	f	2023-07-29 13:33:53.09658-07	2023-07-29 13:33:53.096597-07	Dakar	1
3	f	2023-07-29 13:34:09.022116-07	2023-07-29 13:34:09.022126-07	Pikine	1
4	f	2023-07-29 13:34:19.566657-07	2023-07-29 13:34:19.566667-07	Guediawaye	1
5	f	2023-07-29 13:34:27.630497-07	2023-07-29 13:34:27.630505-07	Rufisque	1
6	f	2023-07-29 13:37:09.100301-07	2023-07-29 13:37:09.100312-07	Bambey	2
7	f	2023-07-29 13:37:16.125325-07	2023-07-29 13:37:16.125335-07	Diourbel	2
8	f	2023-07-29 13:37:33.231504-07	2023-07-29 13:37:33.231515-07	Mbacke	2
9	f	2023-07-29 13:38:44.262568-07	2023-07-29 13:38:44.262578-07	Fatick	3
10	f	2023-07-29 13:38:55.329631-07	2023-07-29 13:38:55.32964-07	Foundiougne	3
11	f	2023-07-29 13:39:06.878339-07	2023-07-29 13:39:06.878346-07	Gossas	3
13	f	2023-07-29 13:42:11.535674-07	2023-07-29 13:42:11.535685-07	Birkelane	4
14	f	2023-07-29 13:42:18.617337-07	2023-07-29 13:42:18.617348-07	Kaffrine	4
15	f	2023-07-29 13:42:31.464562-07	2023-07-29 13:42:31.464572-07	Koungheul	4
16	f	2023-07-29 13:43:29.180512-07	2023-07-29 13:43:29.180521-07	Guinguineo	5
17	f	2023-07-29 13:43:36.763815-07	2023-07-29 13:43:36.763822-07	Kaolack	5
18	f	2023-07-29 13:43:46.500592-07	2023-07-29 13:43:46.500601-07	Nioro du Rip	5
19	f	2023-07-29 13:46:35.456861-07	2023-07-29 13:46:35.456885-07	Kedougou	6
20	f	2023-07-29 13:46:51.955247-07	2023-07-29 13:46:51.955258-07	Salemata	6
21	f	2023-07-29 13:47:03.172825-07	2023-07-29 13:47:03.172832-07	Saraya	6
22	f	2023-07-29 13:47:29.311166-07	2023-07-29 13:47:29.31118-07	Kolda	7
23	f	2023-07-29 13:47:46.030655-07	2023-07-29 13:47:46.030663-07	Medina Yoro Foulah	7
24	f	2023-07-29 13:48:00.475683-07	2023-07-29 13:48:00.475691-07	Velingara	7
25	f	2023-07-29 13:48:25.31884-07	2023-07-29 13:48:25.318848-07	Kebemer	8
26	f	2023-07-29 13:48:36.340147-07	2023-07-29 13:48:36.340155-07	Linguere	8
27	f	2023-07-29 13:48:45.050314-07	2023-07-29 13:48:45.050322-07	Louga	8
28	f	2023-07-29 13:48:57.158997-07	2023-07-29 13:48:57.159007-07	Kanel	9
29	f	2023-07-29 13:49:04.539304-07	2023-07-29 13:49:04.539312-07	Matam	9
30	f	2023-07-29 13:49:14.445418-07	2023-07-29 13:49:14.445425-07	Ranerou	9
31	f	2023-07-29 13:49:27.292962-07	2023-07-29 13:49:27.292976-07	Dagana	10
32	f	2023-07-29 13:49:37.553693-07	2023-07-29 13:49:37.553702-07	Podor	10
33	f	2023-07-29 13:49:49.38702-07	2023-07-29 13:49:49.38703-07	Saint-Louis	10
34	f	2023-07-29 13:50:05.681265-07	2023-07-29 13:50:05.681274-07	Bounkiling	11
35	f	2023-07-29 13:50:14.345953-07	2023-07-29 13:50:14.34597-07	Goudomp	11
36	f	2023-07-29 13:50:23.016538-07	2023-07-29 13:50:23.016546-07	Sedhiou	11
37	f	2023-07-29 13:50:34.178907-07	2023-07-29 13:50:34.17892-07	Bakel	12
38	f	2023-07-29 13:50:40.315979-07	2023-07-29 13:50:40.316013-07	Goudiry	12
39	f	2023-07-29 13:50:47.567736-07	2023-07-29 13:50:47.567751-07	Koumpentoum	12
40	f	2023-07-29 13:50:54.260084-07	2023-07-29 13:50:54.260093-07	Tambacounda	12
41	f	2023-07-29 13:52:44.896464-07	2023-07-29 13:52:44.896473-07	Mbour	13
42	f	2023-07-29 13:52:55.497918-07	2023-07-29 13:52:55.497924-07	Thies	13
43	f	2023-07-29 13:53:02.347488-07	2023-07-29 13:53:02.347495-07	Tivaouane	13
44	f	2023-07-29 13:53:22.245137-07	2023-07-29 13:53:22.245149-07	Oussouye	14
45	f	2023-07-29 13:56:04.333321-07	2023-07-29 13:56:04.333328-07	Bignona	14
46	f	2023-07-29 13:56:13.185941-07	2023-07-29 13:56:13.18595-07	Ziguinchor	14
\.


--
-- TOC entry 3650 (class 0 OID 65558)
-- Dependencies: 217
-- Data for Name: illustrator_media; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.illustrator_media (worker_id, media_id) FROM stdin;
5	20
5	21
5	22
5	22
5	23
6	14
6	15
6	16
6	17
6	18
\.


--
-- TOC entry 3652 (class 0 OID 65562)
-- Dependencies: 219
-- Data for Name: media; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.media (id, archive, created_at, updated_at, image) FROM stdin;
1	f	2023-07-29 14:37:52.768827-07	2023-07-29 14:37:52.76897-07	1690666672749air-conditioner_5361406.png
2	f	2023-07-29 14:37:52.780945-07	2023-07-29 14:37:52.780952-07	1690666672780camera_8816396.png
3	f	2023-07-29 14:37:52.782475-07	2023-07-29 14:37:52.782481-07	1690666672781carpenter_360484.png
4	f	2023-07-29 14:37:52.787069-07	2023-07-29 14:37:52.787075-07	1690666672786fridge_3098484.png
5	f	2023-07-29 14:37:52.789041-07	2023-07-29 14:37:52.789046-07	1690666672788maintenance_2724427.png
6	f	2023-07-29 14:37:52.791094-07	2023-07-29 14:37:52.791099-07	1690666672790maintenance_3653581.png
7	f	2023-07-29 14:37:52.793031-07	2023-07-29 14:37:52.793035-07	1690666672792monitor_4275338.png
8	f	2023-07-29 14:37:52.794946-07	2023-07-29 14:37:52.794951-07	1690666672794painter_1995491.png
9	f	2023-07-29 14:37:52.797016-07	2023-07-29 14:37:52.797021-07	1690666672796plumber_2702301.png
10	f	2023-07-29 14:37:52.798976-07	2023-07-29 14:37:52.79898-07	1690666672798salon_1057470.png
11	f	2023-07-29 14:37:52.801983-07	2023-07-29 14:37:52.801988-07	1690666672801sewing-machine_9661224.png
12	f	2023-07-29 14:37:52.804052-07	2023-07-29 14:37:52.804057-07	1690666672803trimming_10144798.png
13	f	2023-07-30 13:44:06.345766-07	2023-07-30 13:44:06.345863-07	1690749846224angel.jpeg
14	f	2023-07-30 13:44:06.504532-07	2023-07-30 13:44:06.50454-07	1690749846503coiffeuse1.jpeg
15	f	2023-07-30 13:44:06.507153-07	2023-07-30 13:44:06.507158-07	1690749846505coiffeuse2.jpeg
16	f	2023-07-30 13:44:06.519492-07	2023-07-30 13:44:06.519501-07	1690749846518coiffeuse3.jpeg
17	f	2023-07-30 13:44:06.523316-07	2023-07-30 13:44:06.523325-07	1690749846522coiffeuse4.jpeg
18	f	2023-07-30 13:44:06.52728-07	2023-07-30 13:44:06.527287-07	1690749846525coiffeuse5.jpeg
19	f	2023-07-30 14:33:53.026671-07	2023-07-30 14:33:53.027039-07	1690752832973siga.jpeg
20	f	2023-07-30 14:33:53.091139-07	2023-07-30 14:33:53.09115-07	1690752833089coiff1.jpeg
21	f	2023-07-30 14:33:53.093584-07	2023-07-30 14:33:53.093593-07	1690752833092coiff2.jpeg
22	f	2023-07-30 14:33:53.09572-07	2023-07-30 14:33:53.095732-07	1690752833095coiff3.jpeg
23	f	2023-07-30 14:33:53.09812-07	2023-07-30 14:33:53.098131-07	1690752833097coiff4.jpeg
\.


--
-- TOC entry 3654 (class 0 OID 65569)
-- Dependencies: 221
-- Data for Name: profession; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profession (id, archive, created_at, updated_at, name) FROM stdin;
4	f	2023-07-29 14:44:26.839812-07	2023-07-29 14:44:26.839821-07	Frigoriste
3	f	2023-07-29 14:44:11.889101-07	2023-07-29 14:44:11.88911-07	Menuisier
1	f	2023-07-29 14:38:28.874038-07	2023-07-29 14:38:28.874047-07	Repare climatiseur
2	f	2023-07-29 14:42:50.635396-07	2023-07-29 14:42:50.635403-07	Camrepair
12	f	2023-07-29 15:02:01.967297-07	2023-07-29 15:02:01.967305-07	couturier
5	f	2023-07-29 14:52:40.039059-07	2023-07-29 14:52:40.039066-07	Mecanicien
6	f	2023-07-29 14:54:31.404048-07	2023-07-29 14:54:31.404055-07	Repare ventilateur
7	f	2023-07-29 14:56:14.497537-07	2023-07-29 14:56:14.497544-07	Repare television
8	f	2023-07-29 14:56:32.112672-07	2023-07-29 14:56:32.112682-07	Peintre
9	f	2023-07-29 14:58:03.171125-07	2023-07-29 14:58:03.171132-07	Plombier
10	f	2023-07-29 14:58:19.281406-07	2023-07-29 14:58:19.281418-07	coiffeuse
11	f	2023-07-29 15:01:28.679843-07	2023-07-29 15:01:28.67985-07	jardinier
\.


--
-- TOC entry 3655 (class 0 OID 65575)
-- Dependencies: 222
-- Data for Name: profession_media; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.profession_media (profession_id, media_id) FROM stdin;
1	1
2	2
3	3
4	4
5	5
6	6
7	7
8	8
9	9
10	10
11	11
12	12
\.


--
-- TOC entry 3657 (class 0 OID 65579)
-- Dependencies: 224
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.region (id, archive, created_at, updated_at, name) FROM stdin;
1	f	2023-07-29 13:15:58.352864-07	2023-07-29 13:15:58.352972-07	Dakar
2	f	2023-07-29 13:16:08.533653-07	2023-07-29 13:16:08.533667-07	Diourbel
3	f	2023-07-29 13:16:17.297855-07	2023-07-29 13:16:17.297867-07	Fatick
4	f	2023-07-29 13:16:25.36387-07	2023-07-29 13:16:25.363883-07	Kaffrine
5	f	2023-07-29 13:16:34.132832-07	2023-07-29 13:16:34.132846-07	Kaolack
6	f	2023-07-29 13:16:42.048589-07	2023-07-29 13:16:42.04861-07	Kédougou
7	f	2023-07-29 13:16:49.290887-07	2023-07-29 13:16:49.2909-07	Kolda
8	f	2023-07-29 13:16:57.074532-07	2023-07-29 13:16:57.074543-07	Louga
9	f	2023-07-29 13:17:04.886873-07	2023-07-29 13:17:04.886892-07	Matam
10	f	2023-07-29 13:17:12.20815-07	2023-07-29 13:17:12.208165-07	Saint-Louis
11	f	2023-07-29 13:17:29.80875-07	2023-07-29 13:17:29.808763-07	Sedhiou
12	f	2023-07-29 13:17:37.589449-07	2023-07-29 13:17:37.58946-07	Tambacounda
13	f	2023-07-29 13:17:49.027881-07	2023-07-29 13:17:49.027892-07	Thies
14	f	2023-07-29 13:17:57.07896-07	2023-07-29 13:17:57.078972-07	Ziguinchor
\.


--
-- TOC entry 3659 (class 0 OID 65586)
-- Dependencies: 226
-- Data for Name: user_app; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_app (id, archive, created_at, updated_at, password, phone_number, role) FROM stdin;
1	f	2023-08-01 03:36:19.565418-07	2023-08-01 03:36:19.565588-07	$2a$10$sQQdGm54LftfeSHUmNhhcO.enIDmhdkByiJI0bXECUcsOCy1mYBG2	+221770000000	USER
2	f	2023-08-01 03:36:46.895883-07	2023-08-01 03:36:46.895895-07	$2a$10$RuMfqF8q7xE5JuTFTnLFpO668a.Q7do0wbHGl7OYNn8s/gDc2.FDq	+221772344545	USER
3	f	2023-08-01 03:42:40.821303-07	2023-08-01 03:42:40.821314-07	$2a$10$8KuR3KRs3y8s3Ca9jCFzHuqqQCfTlymDYc4LmKozFgPfR4m/pNB9S	+221770000001	USER
4	f	2023-08-01 03:43:49.817787-07	2023-08-01 03:43:49.817799-07	$2a$10$kB1AccOLy2k5HqvVI7McDuUd70y.dJiYeGgqFKI/RctH6jBEmyBXK	+221772344500	USER
5	f	2023-08-01 03:59:02.581946-07	2023-08-01 03:59:02.581957-07	$2a$10$fHjAzN4lZozY6orAPk0RJ.nB6TkawaldT7vBfP8USJEPmhz1T4zQK	+221772344503	USER
6	f	2023-08-01 04:00:16.289384-07	2023-08-01 04:00:16.289396-07	$2a$10$tzycdepLtxUChHBayRgaXObiqDy5K707VHySRpz.872dIHAK.7Hdi	+221772344504	USER
7	f	2023-08-01 04:00:38.181707-07	2023-08-01 04:00:38.181726-07	$2a$10$DMaHfNNoZLb3ZnZmDjkyG.4A4B9jI76zx74l2hlVB1RDhSPi7OrXS	+221772344505	USER
8	f	2023-08-01 04:08:05.779681-07	2023-08-01 04:08:05.779716-07	$2a$10$PF0R0tbid4u.xCbLB.dH1.nUViVSyet0WJnK8tQ8gv1FJB1h24Hfe	+221770000022	USER
9	f	2023-08-01 04:13:28.021697-07	2023-08-01 04:13:28.021708-07	$2a$10$jvROtRO4DWBvaAo06yuIV.AOhqmzP1UrD4/AsZdXwcEZ2aqHPGUxG	+221776544545	USER
10	f	2023-08-01 04:17:25.902355-07	2023-08-01 04:17:25.902377-07	$2a$10$MMtVF80ztfQdUVem1JZcdeTFiMrriApwO.Kr24Ddo8h/eux1IYbLu	+221765554343	USER
11	f	2023-08-01 04:22:23.658807-07	2023-08-01 04:22:23.658819-07	$2a$10$wN3uhaqbYrWOabEUkV1DuO9F0Gjt4mAjS7pi3wwzbSnf2Om43CfxS	+221764443232	USER
12	f	2023-08-01 04:23:45.543018-07	2023-08-01 04:23:45.543028-07	$2a$10$u9DebUvXrWikRdA30oz0dOpg7wd/KfUk63z9Pjtfws26yDxgbcOke	+221783433225	USER
13	f	2023-08-01 05:10:55.158905-07	2023-08-01 05:10:55.158939-07	$2a$10$dlTEv/tndqYMSmRpJLMb3OX8EAvD5/DoH.xT/6zl2ni9pYME5e.6S	+221783433224	USER
\.


--
-- TOC entry 3661 (class 0 OID 65596)
-- Dependencies: 228
-- Data for Name: worker; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.worker (id, archive, created_at, updated_at, address, cin, description, full_name, phone_number, recommend, department_id, profession_id, region_id) FROM stdin;
5	f	2023-07-31 04:21:42.636729-07	2023-07-31 04:21:42.636737-07	Ndayane, Tilene	12323444533436	Khardiata Youm est une mere polyvalente et expérimentee, exerçant le metier de coiffeuse depuis de nombreuses annees. Elle partage son savoir-faire en formant plusieurs femmes dans ce domaine, laissant ainsi une empreinte positive sur la communaute.	Khardijata Youm	+221783710296	f	41	10	13
6	f	2023-07-31 04:24:25.12061-07	2023-07-31 04:24:25.120617-07	foire, derriere la federation senegalaise de foot ball	12323444533435	Coumba Angele Sene est une coiffeuse passionnee par son metier, experte dans les techniques de tresses, meches, greffage et cheveux naturels.	Coumba Angele Sene	+221783433225	f	2	10	1
\.


--
-- TOC entry 3680 (class 0 OID 0)
-- Dependencies: 215
-- Name: department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.department_id_seq', 46, true);


--
-- TOC entry 3681 (class 0 OID 0)
-- Dependencies: 218
-- Name: media_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.media_id_seq', 23, true);


--
-- TOC entry 3682 (class 0 OID 0)
-- Dependencies: 220
-- Name: profession_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.profession_id_seq', 13, true);


--
-- TOC entry 3683 (class 0 OID 0)
-- Dependencies: 223
-- Name: region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.region_id_seq', 15, true);


--
-- TOC entry 3684 (class 0 OID 0)
-- Dependencies: 225
-- Name: user_app_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_app_id_seq', 13, true);


--
-- TOC entry 3685 (class 0 OID 0)
-- Dependencies: 227
-- Name: worker_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.worker_id_seq', 6, true);


--
-- TOC entry 3668 (class 0 OID 0)
-- Data for Name: BLOBS; Type: BLOBS; Schema: -; Owner: -
--

BEGIN;

SELECT pg_catalog.lo_open('32778', 131072);
SELECT pg_catalog.lowrite(0, '\x4c65204c6f72656d20497073756d206573742073696d706c656d656e74206475206661757820746578746520656d706c6f79c3a92064616e73206c6120636f6d706f736974696f6e206574206c61206d69736520656e2070616765206176616e7420696d7072657373696f6e2e204c65204c6f72656d20497073756d20657374206c652066617578207465787465207374616e64617264206465206c27696d7072696d6572696520646570756973206c657320616e6ec3a965732031353030');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('32779', 131072);
SELECT pg_catalog.lowrite(0, '\x4c65204c6f72656d20497073756d206573742073696d706c656d656e74206475206661757820746578746520656d706c6f79c3a92064616e73206c6120636f6d706f736974696f6e206574206c61206d69736520656e2070616765206176616e7420696d7072657373696f6e2e204c65204c6f72656d20497073756d20657374206c652066617578207465787465207374616e64617264206465206c27696d7072696d6572696520646570756973206c657320616e6ec3a965732031353030');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('41085', 131072);
SELECT pg_catalog.lowrite(0, '\x4c65204c6f72656d20497073756d206573742073696d706c656d656e74206475206661757820746578746520656d706c6f79c3a92064616e73206c6120636f6d706f736974696f6e206574206c61206d69736520656e2070616765206176616e7420696d7072657373696f6e2e204c65204c6f72656d20497073756d20657374206c652066617578207465787465207374616e64617264206465206c27696d7072696d6572696520646570756973206c657320616e6ec3a965732031353030');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('41086', 131072);
SELECT pg_catalog.lowrite(0, '\x4c65204c6f72656d20497073756d206573742073696d706c656d656e74206475206661757820746578746520656d706c6f79c3a92064616e73206c6120636f6d706f736974696f6e206574206c61206d69736520656e2070616765206176616e7420696d7072657373696f6e2e204c65204c6f72656d20497073756d20657374206c652066617578207465787465207374616e64617264206465206c27696d7072696d6572696520646570756973206c657320616e6ec3a965732031353030');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('41087', 131072);
SELECT pg_catalog.lowrite(0, '\x4c65204c6f72656d20497073756d206573742073696d706c656d656e74206475206661757820746578746520656d706c6f79c3a92064616e73206c6120636f6d706f736974696f6e206574206c61206d69736520656e2070616765206176616e7420696d7072657373696f6e2e204c65204c6f72656d20497073756d20657374206c652066617578207465787465207374616e64617264206465206c27696d7072696d6572696520646570756973206c657320616e6ec3a965732031353030');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('41088', 131072);
SELECT pg_catalog.lowrite(0, '\x4c65204c6f72656d20497073756d206573742073696d706c656d656e74206475206661757820746578746520656d706c6f79c3a92064616e73206c6120636f6d706f736974696f6e206574206c61206d69736520656e2070616765206176616e7420696d7072657373696f6e2e204c65204c6f72656d20497073756d20657374206c652066617578207465787465207374616e64617264206465206c27696d7072696d6572696520646570756973206c657320616e6ec3a965732031353030');
SELECT pg_catalog.lo_close(0);

COMMIT;

--
-- TOC entry 3484 (class 2606 OID 65557)
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (id);


--
-- TOC entry 3486 (class 2606 OID 65567)
-- Name: media media_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);


--
-- TOC entry 3488 (class 2606 OID 65574)
-- Name: profession profession_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profession
    ADD CONSTRAINT profession_pkey PRIMARY KEY (id);


--
-- TOC entry 3490 (class 2606 OID 65584)
-- Name: region region_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.region
    ADD CONSTRAINT region_pkey PRIMARY KEY (id);


--
-- TOC entry 3492 (class 2606 OID 65594)
-- Name: user_app user_app_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_app
    ADD CONSTRAINT user_app_pkey PRIMARY KEY (id);


--
-- TOC entry 3494 (class 2606 OID 65603)
-- Name: worker worker_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.worker
    ADD CONSTRAINT worker_pkey PRIMARY KEY (id);


--
-- TOC entry 3500 (class 2606 OID 65629)
-- Name: profession_media fk3ajgefbx0klwtnundp7bi5c32; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profession_media
    ADD CONSTRAINT fk3ajgefbx0klwtnundp7bi5c32 FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- TOC entry 3502 (class 2606 OID 65649)
-- Name: worker fkaci0xwl5o3fi09lfdqlbbpp7m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.worker
    ADD CONSTRAINT fkaci0xwl5o3fi09lfdqlbbpp7m FOREIGN KEY (region_id) REFERENCES public.region(id);


--
-- TOC entry 3503 (class 2606 OID 65644)
-- Name: worker fkaukil7brdpiubow2ptraxdrn3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.worker
    ADD CONSTRAINT fkaukil7brdpiubow2ptraxdrn3 FOREIGN KEY (profession_id) REFERENCES public.profession(id);


--
-- TOC entry 3504 (class 2606 OID 65639)
-- Name: worker fkbrvrkgp08rv8dw9msd556xwdg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.worker
    ADD CONSTRAINT fkbrvrkgp08rv8dw9msd556xwdg FOREIGN KEY (department_id) REFERENCES public.department(id);


--
-- TOC entry 3498 (class 2606 OID 65619)
-- Name: illustrator_media fkf3p4ygyoxkect6c6bkxtryt4e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.illustrator_media
    ADD CONSTRAINT fkf3p4ygyoxkect6c6bkxtryt4e FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- TOC entry 3497 (class 2606 OID 65614)
-- Name: department fki6ei9qre5amjsj4nx35qeaqb8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT fki6ei9qre5amjsj4nx35qeaqb8 FOREIGN KEY (region_id) REFERENCES public.region(id);


--
-- TOC entry 3495 (class 2606 OID 65604)
-- Name: avatar_media fkku156ifhvy37wta55cghlp9n9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avatar_media
    ADD CONSTRAINT fkku156ifhvy37wta55cghlp9n9 FOREIGN KEY (media_id) REFERENCES public.media(id);


--
-- TOC entry 3496 (class 2606 OID 65609)
-- Name: avatar_media fkn7brp5ylrwfmpa9h2f0jl1val; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avatar_media
    ADD CONSTRAINT fkn7brp5ylrwfmpa9h2f0jl1val FOREIGN KEY (worker_id) REFERENCES public.worker(id);


--
-- TOC entry 3499 (class 2606 OID 65624)
-- Name: illustrator_media fkpcbmddoxhidakyhvqcidiycqa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.illustrator_media
    ADD CONSTRAINT fkpcbmddoxhidakyhvqcidiycqa FOREIGN KEY (worker_id) REFERENCES public.worker(id);


--
-- TOC entry 3501 (class 2606 OID 65634)
-- Name: profession_media fkqbd3fl3q8l6uc3gced2srnqa1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.profession_media
    ADD CONSTRAINT fkqbd3fl3q8l6uc3gced2srnqa1 FOREIGN KEY (profession_id) REFERENCES public.profession(id);


-- Completed on 2023-08-01 08:21:21 PDT

--
-- PostgreSQL database dump complete
--

