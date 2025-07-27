create table public.organization
(
    id         serial primary key,
    name       varchar(255) not null,
    domain     varchar(255),
    created_at timestamp default now()
);

alter table public.organization
    owner to springerp;