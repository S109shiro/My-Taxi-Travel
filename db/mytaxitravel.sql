create database my_taxi_travel;
use my_taxi_travel;


-- Creacion tabla usuario YA ESTA
create table usuario(
	id_usuario int unique auto_increment,
    nombre varchar(50) not null,
    primer_apellido varchar(50) not null,
    segundo_apellido varchar(50) not null,
    edad int not null,
    numero_identificacion int unique not null,
    email varchar(255) unique not null,
    sexo varchar(10) not null,
    documento_identidad text not null,
    numero_telefono varchar(11) not null,
    fecha_nacimiento date not null,
    calificacion_media float not null,
    historial_viajes json,
    primary key(id_usuario)
);

-- Creacion tabla taxi YA ESTA
create table taxi(
	id_taxi int unique auto_increment,
    placa varchar(6) not null,  
    modelo varchar(255) not null,
    ultima_tecnico_mecanica date not null,
    primary key(id_taxi)
);

-- Creacion tabla conductor YA ESTA
create table conductor(
	id_conductor int unique auto_increment,
    nombre varchar(50) not null,
    primer_apellido varchar(50) not null,
    segundo_apellido varchar(50) not null,
    edad int not null,
    numero_identificacion int unique not null,
    email varchar(255) unique not null,
    sexo varchar(10) not null,
    documento_identidad text not null,
    numero_telefono varchar(11) not null,
    fecha_nacimiento date not null,
    calificacion_media float not null,
    historial_viajes json, 
    ganancias_mes float not null,
    ganancias_totales float not null,
    taxi_en_uso int not null,
    primary key(id_conductor),
    foreign key(taxi_en_uso) references taxi(id_taxi)
);

-- Creacion tabla GPS YA ESTA
create table registro_GPS(
	id_registro_viaje int unique auto_increment,
    lugar_inicio  point not null,
    lugar_destino  point not null,
    fecha_hora_inicio datetime not null,
    fecha_hora_final datetime not null,
    primary key(id_registro_viaje)
);

--  Creacion tabla calificacion YA ESTA
create table calificacion(
	id_calificacion int unique auto_increment,
    calificacion_usuario float not null,
    calificacion_conductor float not null,
    comentario varchar(255),
    primary key(id_calificacion)
);

-- Creacion tabla administrador YA ESTA
create table administrador(
	id_administrador int unique auto_increment,
    nombre varchar(50) not null,
    primer_apellido varchar(50) not null,
    segundo_apellido varchar(50) not null,
    edad int not null,
    numero_identificacion int unique not null,
    email varchar(255) unique not null,
    sexo varchar(10) not null,
    documento_identidad text not null,
    numero_telefono varchar(11) not null,
    fecha_nacimiento date not null, 
    lista_reportes_gestionados json,
    primary key(id_administrador)
);

-- Creacion tabla reportes YA ESTA
create table reportes(
	id_reporte int unique auto_increment,
    fecha_reporte datetime not null,
    tipo_reporte varchar(20) not null,
    detalles varchar(255) not null,
    encargado_reporte int not null,
    primary key(id_reporte),
    foreign key(encargado_reporte) references administrador(id_administrador)
);

-- Creacion tabla viaje YA ESTA
create table viaje(
	id_viaje int unique auto_increment,
    id_usuario int not null,
    id_conductor int not null,
    estado_viaje boolean not null,
    costo_final float not null,
    tipo_pago varchar(60) not null,
    calificacion_final int not null,
    reporte int,
    registroGPS int not null,
    primary key(id_viaje),
    foreign key(id_usuario) references usuario(id_usuario),
    foreign key(id_conductor) references conductor(id_conductor),
    foreign key(calificacion_final) references calificacion(id_calificacion),
	foreign key(reporte) references reportes(id_reporte),
    foreign key(registroGPS) references registro_GPS(id_registro_viaje)
);
