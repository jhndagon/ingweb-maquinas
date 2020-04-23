insert into roles(nombre, descripcion) values ("ROLE_ADMIN", "Administrador");
insert into roles(nombre, descripcion) values ("ROLE_EMPLEADO", "Empleado");
insert into roles(nombre, descripcion) values ("ROLE_ADMIN_PUNTO", "Administrador punto de venta");
insert into roles(nombre, descripcion) values ("ROLE_RECURSO_HUMANO", "Recursos humanos");

insert into contratos(nombre) values ("Indefinido");
insert into contratos(nombre) values ("Temporal");
insert into contratos(nombre) values ("Formación y aprendizaje");
insert into contratos(nombre) values ("Prácticas");

insert into puntos_de_venta(nombre, direccion, telefono) values("Este punto de venta uno","","");
insert into puntos_de_venta(nombre, direccion, telefono) values("Este punto de venta dos","","");

<<<<<<< HEAD
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("John","john", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1, 1,1);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("David","david", "$2a$10$7e6XaWDzJQEuJB93yv/QlOoKI6lCA04SkgMQhHAWt1ih7cT03.Plu", 2,2,1);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("Mateo","mateo", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,2,2);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("Mateo","mateo123", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,1,2);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato) values("Paula","paula", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,2);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato) values("Usuario","user", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,1);
=======
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("John","john", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1, 1, 1);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("David","david", "$2a$10$7e6XaWDzJQEuJB93yv/QlOoKI6lCA04SkgMQhHAWt1ih7cT03.Plu", 2,2,1);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("Mateo","mateo", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,2,2);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato, id_punto_venta) values("Mateo","mateo123", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,1,2);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato) values("Paula","paula", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,2,2);
insert into usuarios(nombre, usuario, contrasenia, id_rol, id_contrato) values("Usuario","user", "$2a$10$hZDgewXA2XS4IXsM8cfep.RymaBXzGipYncB4NBCC853YONAjYCMy", 1,1,1);
>>>>>>> d24fccd910a05697a95be11bd1de305c3bf9f94f


insert into maquinas(marca, modelo, tipo) values("brother", "2017", "buena");
insert into maquinas(marca, modelo, tipo) values("Singer", "2007", "Regular");

insert into provedores(nombre, nit, ubicacion, telefono) values("Para coser S.A.S", "7658798765", "Medellin", "867-0022");
insert into provedores(nombre, nit, ubicacion, telefono) values("Servitejer", "8745328765", "Medellin", "045-8866");
insert into provedores(nombre, nit, ubicacion, telefono) values("Macoser", "98453209", "Copacabana", "238-2214");
insert into provedores(nombre, nit, ubicacion, telefono) values("Tecni Costura S.A", "65349870", "Barbosa", "887-1114");

