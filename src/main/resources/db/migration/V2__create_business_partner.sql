create table public.business_partner
(
    id              serial
        constraint business_partner_pk
            primary key,
    name            varchar(255) not null,
    created_at      timestamp default now(),
    organization_id integer
        constraint business_partner___fk
            references public.organization
);

alter table public.business_partner
    owner to springerp;

