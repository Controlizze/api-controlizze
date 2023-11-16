create table types (
    type_id         bigint              auto_increment      primary key,
    name            varchar(255)
);

create table categories (
    category_id     bigint              auto_increment      primary key,
    name            varchar(255)
);

create table states (
    state_id        bigint              auto_increment      primary key,
    name            varchar(255),
    uf              varchar(2)
);

create table authorities (
    authority_id    bigint              auto_increment      primary key,
    authority       varchar(10)
);

create table cities (
    city_id         bigint              auto_increment      primary key,
    name            varchar(255),
    state_id        bigint,
    foreign key (state_id) references states(state_id)
);

create table users (
    user_id         bigint              auto_increment      primary key,
    name            varchar(255),
    date_birth      date,
    cel             varchar(14),
    email           varchar(255),
    password        varchar(255),
    city_id         bigint,
    foreign key (city_id) references cities(city_id)
);

create table movements (
    mov_id          bigint              auto_increment      primary key,
    date            date,
    description     varchar(255),
    category_id     bigint,
    value           decimal(8,2),
    type_id         bigint,
    user_id         bigint,
    foreign key (category_id) references categories(category_id),
    foreign key (type_id) references types(type_id),
    foreign key (user_id) references users(user_id)
);

create table authority_user (
    user_id         bigint,
    authority_id    bigint,
    primary key (user_id, authority_id),
    foreign key (user_id) references users(user_id),
    foreign key (authority_id) references authorities(authority_id)
);
