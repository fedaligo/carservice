create table m_users
(
	id bigserial not null
		constraint m_users_pk
			primary key,
	login varchar(20) not null,
	password varchar,
	created timestamp,
	changed timestamp,
	is_deleted boolean,
	e_mail varchar(50),
	phone_number_user bigint,
	gender varchar default 'NOT_SELECTED'::character varying not null
);

alter table m_users owner to test;

create unique index m_users_id_uindex
	on m_users (id);

create unique index m_users_login_uindex
	on m_users (login);

create table m_roles
(
	id bigserial not null
		constraint m_roles_pk
			primary key,
	name_of_role varchar(30),
	user_id bigint
		constraint m_roles_m_users_id_fk
			references m_users
				on update cascade on delete cascade
);

alter table m_roles owner to test;

create unique index m_roles_id_uindex
	on m_roles (id);

create table m_car
(
	id bigserial not null
		constraint m_car_pk
			primary key,
	car_brand varchar(30),
	brand_model varchar(30),
	type_of_transmission varchar(2),
	type_of_fuel varchar(10),
	user_id bigint
		constraint m_car_m_users_id_fk
			references m_users
				on update cascade on delete cascade,
	car_weight bigint,
	vin_number varchar(17)
);

alter table m_car owner to test;

create table m_tasks
(
	id bigserial not null
		constraint m_tasks_pk
			primary key,
	service_work_name varchar(100),
	necessity_of_evacuation boolean,
	wheel_brake boolean,
	id_car bigint
		constraint m_tasks_m_car_id_fk
			references m_car
				on update cascade on delete cascade,
	created date,
	description varchar(1000),
	latitude varchar(10),
	longitude varchar(10),
	local_description varchar(50)
);

alter table m_tasks owner to test;

create unique index m_tasks_id_uindex
	on m_tasks (id);

create unique index m_car_id_uindex
	on m_car (id);

create table m_organization
(
	id bigserial not null
		constraint m_organization_pk
			primary key,
	login varchar(30) not null,
	web_site varchar(30),
	phone_number bigint,
	location varchar(50),
	working_time varchar(30),
	specialize varchar(50),
	e_mail varchar(50),
	password varchar,
	role varchar
);

alter table m_organization owner to test;

create table tracking_system
(
	id bigserial not null
		constraint tracking_system_pk
			primary key,
	id_task bigint
		constraint tracking_system_m_tasks_id_fk
			references m_tasks
				on update cascade on delete cascade,
	id_organaizer bigint
		constraint tracking_system_m_organization_id_fk
			references m_organization
				on update cascade on delete cascade,
	status varchar(40),
	confirm_date date,
	cost integer
);

alter table tracking_system owner to test;

create unique index tracking_system_id_uindex
	on tracking_system (id);

create unique index m_organization_id_uindex
	on m_organization (id);

create unique index m_organization_login_uindex
	on m_organization (login);

