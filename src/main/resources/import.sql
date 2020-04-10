insert into roles(nombre, descripcion) values ("ROLE_ADMIN", "Administrador");
insert into roles(nombre, descripcion) values ("ROLE_EMPLEADO", "Empleado");
insert into roles(nombre, descripcion) values ("ROLE_ADMIN_PUNTO", "Administrador punto de venta");
insert into roles(nombre, descripcion) values ("ROLE_RECURSO_HUMANO", "Recursos humanos");

insert into usuarios(nombre, usuario, contrasenia, id_rol) values("John","john", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1);
insert into usuarios(nombre, usuario, contrasenia, id_rol) values("David","david", "$2a$10$7e6XaWDzJQEuJB93yv/QlOoKI6lCA04SkgMQhHAWt1ih7cT03.Plu", 2);
insert into usuarios(nombre, usuario, contrasenia, id_rol) values("Mateo","mateo", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1);
insert into usuarios(nombre, usuario, contrasenia, id_rol) values("Mateo","mateo123", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1);
insert into usuarios(nombre, usuario, contrasenia, id_rol) values("Paula","paula", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1);
insert into usuarios(nombre, usuario, contrasenia, id_rol) values("Usuario","user", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1);


insert into maquinas(marca, modelo, tipo) values("brother", "2017", "buena")
insert into maquinas(marca, modelo, tipo) values("Singer", "2007", "Regular")