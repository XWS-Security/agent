INSERT INTO public.authority(id, name) VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO public.authority(id, name) VALUES (2, 'ROLE_AGENT');

INSERT INTO public.privilege(id, name) VALUES (1, 'PRODUCT_CRUD_PRIVILEGE');

INSERT INTO public.roles_privileges(role_id, privilege_id) VALUES (2, 1);