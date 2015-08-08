/** BEGIN role table **/
create table role (
	id bigserial not null,
	name varchar(100) not null unique,
	constraint pk_role primary key (id)
);

/** END **/

/** BEGIN person table **/
create table person (
	id bigserial not null,
	username varchar(100) unique,
	email varchar(100) unique, 
	encrypted_password varchar(250) not null,
	enabled boolean,
	confirmed boolean,
	constraint pk_person primary key (id)
);

create index person_username_index on person (username);
create index person_email_index on person (email);
/** END **/

/** BEGIN person_role table **/
create table person_role (
	id bigserial not null,
	person_id bigserial not null,
	role_id bigserial not null,
	constraint pk_person_role primary key (id), 
	constraint fk_person_id foreign key (person_id) references person (id),
	constraint fk_role_id foreign key (role_id) references role (id)
);

/** END **/