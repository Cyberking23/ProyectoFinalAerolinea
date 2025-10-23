/* ==========================================================
   BASE DE DATOS
   ========================================================== */
CREATE DATABASE IF NOT EXISTS vuelosdb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE vuelosdb;

/* ==========================================================
   TABLA: Aerolíneas
   ========================================================== */
CREATE TABLE IF NOT EXISTS aerolineas (
                                          id               BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          nombre           VARCHAR(100)        NOT NULL,
    codigo_iata_icao VARCHAR(10)         NOT NULL UNIQUE,
    pais_origen      VARCHAR(60)         NOT NULL,
    estado           ENUM('Activo','Inactivo') DEFAULT 'Activo'
    );

/* ==========================================================
   TABLA: Aviones
   ========================================================== */
CREATE TABLE IF NOT EXISTS aviones (
                                       id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       matricula     VARCHAR(20)  NOT NULL UNIQUE,
    modelo        VARCHAR(50)  NOT NULL,
    capacidad     INT          NOT NULL,
    estado        ENUM('Activo','Inactivo') DEFAULT 'Activo',
    aerolinea_id  BIGINT,
    CONSTRAINT fk_avion_aerolinea
    FOREIGN KEY (aerolinea_id) REFERENCES aerolineas(id) ON DELETE CASCADE
    );

/* ==========================================================
   TABLA: Tripulación
   ========================================================== */
CREATE TABLE IF NOT EXISTS tripulantes (
                                           id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           nombre         VARCHAR(100) NOT NULL,
    rol            VARCHAR(50)  NOT NULL,
    disponibilidad ENUM('Activo','Inactivo') DEFAULT 'Activo',
    aerolinea_id   BIGINT,
    CONSTRAINT fk_tripulante_aerolinea
    FOREIGN KEY (aerolinea_id) REFERENCES aerolineas(id) ON DELETE CASCADE
    );

/* ==========================================================
   TABLA: Vuelos (alineada con entidad Java)
   ========================================================== */
CREATE TABLE IF NOT EXISTS vuelos (
                                      id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      identificador_vuelo VARCHAR(50)   NOT NULL UNIQUE,
    origen              VARCHAR(100)  NOT NULL,
    destino             VARCHAR(100)  NOT NULL,
    distancia_km        INT           NULL,
    duracion_horas      VARCHAR(20)   NULL,
    avion_id            BIGINT        NULL,
    aerolinea_id        BIGINT        NULL,
    fecha_salida        DATETIME      NOT NULL,
    fecha_llegada       DATETIME      NOT NULL,
    tarifa              DECIMAL(10,2) NULL,
    puerta              VARCHAR(10)   NULL,
    check_in            VARCHAR(30)   NULL,
    CONSTRAINT fk_vuelo_avion
    FOREIGN KEY (avion_id) REFERENCES aviones(id) ON DELETE CASCADE,
    CONSTRAINT fk_vuelo_aerolinea
    FOREIGN KEY (aerolinea_id) REFERENCES aerolineas(id) ON DELETE CASCADE
    );


/* ==========================================================
   TABLA: Quejas / Reclamos
   =============||============================================= */
CREATE TABLE IF NOT EXISTS quejas (
                                      id             BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      fecha_reclamo  DATE         NOT NULL,
                                      asunto         VARCHAR(150) NOT NULL,
    descripcion    TEXT         NULL,
    estado         ENUM('Pendiente','En revisión','Resuelto') DEFAULT 'Pendiente',
    vuelo_id       BIGINT       NULL,
    CONSTRAINT fk_queja_vuelo
    FOREIGN KEY (vuelo_id) REFERENCES vuelos(id) ON DELETE SET NULL
    );

/* ==========================================================
   TABLA: Reservaciones
   ========================================================== */
CREATE TABLE IF NOT EXISTS reservaciones (
                                             id                BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             fecha_reservacion DATE        NOT NULL,
                                             pasajero          VARCHAR(100) NOT NULL,
    vuelo_id          BIGINT       NULL,
    estado            ENUM('Activa','Cancelada') DEFAULT 'Activa',
    CONSTRAINT fk_reserva_vuelo
    FOREIGN KEY (vuelo_id) REFERENCES vuelos(id) ON DELETE CASCADE
    );



USE vuelosdb;

/* ==========================================================
   TABLAS DE AUTENTICACIÓN / PERFILES
   ========================================================== */


    /*
    usuarios-1:1--Clientes
        |
        1:1
        |
    Administradores

    en usuarios se almacena la contraseña, y en clientes y administradores datos proopios de  ello

    */

-- Drop dependent tables first (administradores and clientes)
DROP TABLE IF EXISTS administradores;

DROP TABLE IF EXISTS clientes;

-- Drop the parent table last (usuarios)
DROP TABLE IF EXISTS usuarios;
CREATE TABLE IF NOT EXISTS usuarios (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    password   VARCHAR(200) NOT NULL,
    rol        ENUM('ADMIN','CLIENTE') NOT NULL,
    estado     ENUM('Activo','Inactivo') DEFAULT 'Activo',
    creado_en  DATETIME DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS administradores (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    usuario_id  BIGINT NOT NULL,
    CONSTRAINT fk_admin_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS clientes (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    usuario_id  BIGINT NOT NULL,
    CONSTRAINT fk_cliente_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
    );

/* Comprobaciones rápidas */
SELECT * FROM aerolineas;
SELECT * FROM aviones;
