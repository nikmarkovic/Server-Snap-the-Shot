# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table city (
  id                        bigint auto_increment not null,
  name                      varchar(255) not null,
  constraint uq_city_name unique (name),
  constraint pk_city primary key (id))
;

create table image (
  id                        bigint auto_increment not null,
  name                      varchar(255) not null,
  image_data                blob not null,
  constraint uq_image_name unique (name),
  constraint pk_image primary key (id))
;

create table log (
  id                        bigint auto_increment not null,
  log_time                  timestamp not null,
  entity_type               varchar(5) not null,
  entity_id                 bigint not null,
  constraint ck_log_entity_type check (entity_type in ('city','sight','image')),
  constraint pk_log primary key (id))
;

create table sight (
  id                        bigint auto_increment not null,
  name                      varchar(255) not null,
  description               clob,
  latitude                  double not null,
  longitude                 double not null,
  city_id                   bigint,
  image_id                  bigint,
  constraint pk_sight primary key (id))
;

alter table sight add constraint fk_sight_city_1 foreign key (city_id) references city (id) on delete restrict on update restrict;
create index ix_sight_city_1 on sight (city_id);
alter table sight add constraint fk_sight_image_2 foreign key (image_id) references image (id) on delete restrict on update restrict;
create index ix_sight_image_2 on sight (image_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists city;

drop table if exists image;

drop table if exists log;

drop table if exists sight;

SET REFERENTIAL_INTEGRITY TRUE;

