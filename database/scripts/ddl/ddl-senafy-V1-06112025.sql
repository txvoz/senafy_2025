-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-11-2025 a las 03:07:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `senafy`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ad`
--

CREATE TABLE `ad` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del anuncio',
  `provider_id` int(11) NOT NULL COMMENT 'Relación con el proveedor que publica el anuncio',
  `title` varchar(150) NOT NULL COMMENT 'Título del anuncio publicitario',
  `content` text NOT NULL COMMENT 'Contenido textual o HTML del anuncio',
  `start_date` date NOT NULL COMMENT 'Fecha de inicio de publicación',
  `end_date` date NOT NULL COMMENT 'Fecha de finalización del anuncio',
  `is_active` tinyint(1) DEFAULT 1 COMMENT 'Indica si el anuncio está activo o no'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que almacena los anuncios publicitarios mostrados a usuarios no premium.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ad_rate`
--

CREATE TABLE `ad_rate` (
  `id` int(11) NOT NULL COMMENT 'Identificador único de la tarifa por visualización',
  `provider_id` int(11) NOT NULL COMMENT 'Relación con el proveedor al que se aplica la tarifa',
  `cost_per_view` decimal(10,2) NOT NULL COMMENT 'Costo por cada visualización de anuncio',
  `effective_date` date NOT NULL COMMENT 'Fecha a partir de la cual aplica la tarifa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que define las tarifas aplicadas por visualización de anuncios.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ad_view`
--

CREATE TABLE `ad_view` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del registro de visualización de anuncio',
  `ad_id` int(11) NOT NULL COMMENT 'Relación con el anuncio visualizado',
  `user_id` int(11) NOT NULL COMMENT 'Usuario que visualizó el anuncio',
  `view_date` datetime DEFAULT current_timestamp() COMMENT 'Fecha y hora en que se visualizó el anuncio'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que registra las visualizaciones de anuncios por usuario.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artist`
--

CREATE TABLE `artist` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del artista',
  `name` varchar(150) NOT NULL COMMENT 'Nombre del artista o banda musical',
  `country` varchar(100) DEFAULT NULL COMMENT 'País de origen del artista',
  `biography` text DEFAULT NULL COMMENT 'Reseña biográfica o descripción del artista'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla de artistas musicales disponibles en la plataforma.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `audit_log`
--

CREATE TABLE `audit_log` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del log',
  `user_id` int(11) DEFAULT NULL COMMENT 'Usuario que ejecutó la acción (si aplica)',
  `action_type` varchar(50) NOT NULL COMMENT 'Tipo de acción: INSERT, UPDATE, DELETE, LOGIN, etc.',
  `table_name` varchar(100) NOT NULL COMMENT 'Nombre de la tabla afectada',
  `record_id` int(11) DEFAULT NULL COMMENT 'ID del registro afectado (si aplica)',
  `description` text DEFAULT NULL COMMENT 'Descripción detallada del evento o cambio',
  `ip_address` varchar(45) DEFAULT NULL COMMENT 'Dirección IP desde donde se ejecutó la acción',
  `user_agent` text DEFAULT NULL COMMENT 'Información del navegador o cliente',
  `created_at` datetime DEFAULT current_timestamp() COMMENT 'Fecha y hora del evento'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla de auditoría para registrar eventos del sistema.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL COMMENT 'Identificador único de la categoría',
  `name` varchar(100) NOT NULL COMMENT 'Nombre de la categoría musical o temática',
  `description` text DEFAULT NULL COMMENT 'Descripción de la categoría'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que agrupa las canciones y podcasts según su categoría.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `payment_plan`
--

CREATE TABLE `payment_plan` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del plan de pago',
  `name` varchar(100) NOT NULL COMMENT 'Nombre del plan de suscripción (Ej: Basic, Premium, Family)',
  `description` text DEFAULT NULL COMMENT 'Descripción del plan y sus beneficios',
  `monthly_price` decimal(10,2) NOT NULL COMMENT 'Valor mensual del plan en USD',
  `duration_months` int(11) DEFAULT 1 COMMENT 'Duración del plan en meses',
  `is_active` tinyint(1) DEFAULT 1 COMMENT 'Indica si el plan se encuentra disponible'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que define los planes de pago disponibles para usuarios premium.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `payment_record`
--

CREATE TABLE `payment_record` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del registro de pago mensual',
  `user_plan_id` int(11) NOT NULL COMMENT 'Relación con la suscripción del usuario',
  `payment_date` date DEFAULT curdate() COMMENT 'Fecha en que se realizó el pago',
  `amount` decimal(10,2) NOT NULL COMMENT 'Valor pagado en la transacción',
  `payment_status` varchar(50) DEFAULT 'completed' COMMENT 'Estado del pago (completed, pending, failed)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que registra los pagos mensuales realizados por los usuarios premium.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `playlist`
--

CREATE TABLE `playlist` (
  `id` int(11) NOT NULL COMMENT 'Identificador único de la lista de reproducción',
  `user_id` int(11) NOT NULL COMMENT 'Usuario que creó la lista',
  `name` varchar(150) NOT NULL COMMENT 'Nombre asignado por el usuario a la lista de reproducción',
  `creation_date` datetime DEFAULT current_timestamp() COMMENT 'Fecha de creación de la lista'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que almacena las listas de reproducción creadas por los usuarios.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `playlist_song`
--

CREATE TABLE `playlist_song` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del registro playlist-song',
  `playlist_id` int(11) NOT NULL COMMENT 'Relación con la lista de reproducción',
  `song_id` int(11) NOT NULL COMMENT 'Relación con la canción agregada a la lista'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Relación muchos a muchos entre listas de reproducción y canciones.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provider`
--

CREATE TABLE `provider` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del proveedor o cliente publicitario',
  `name` varchar(150) NOT NULL COMMENT 'Nombre del proveedor o cliente',
  `email` varchar(150) NOT NULL COMMENT 'Correo de contacto del proveedor',
  `phone` varchar(50) DEFAULT NULL COMMENT 'Número telefónico del proveedor'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que almacena los proveedores o clientes que publican anuncios en SENAFY.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del rol',
  `name` varchar(100) NOT NULL COMMENT 'Nombre del rol dentro del sistema (Ej: admin, user)',
  `description` text DEFAULT NULL COMMENT 'Descripción del rol y sus permisos generales'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que almacena los roles de usuario en el sistema SENAFY';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `song`
--

CREATE TABLE `song` (
  `id` int(11) NOT NULL COMMENT 'Identificador único de la canción o podcast',
  `artist_id` int(11) NOT NULL COMMENT 'Relación con el artista que interpreta la canción',
  `category_id` int(11) NOT NULL COMMENT 'Relación con la categoría a la que pertenece la canción',
  `title` varchar(200) NOT NULL COMMENT 'Título de la canción o podcast',
  `duration` time NOT NULL COMMENT 'Duración total del audio',
  `audio_url` varchar(255) NOT NULL COMMENT 'Ruta o URL donde se encuentra almacenado el archivo de audio',
  `release_date` date DEFAULT NULL COMMENT 'Fecha de publicación del contenido',
  `views` int(11) DEFAULT 0 COMMENT 'Número de reproducciones o vistas registradas'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que almacena las canciones o podcasts disponibles en la plataforma.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'Identificador único del usuario',
  `role_id` int(11) NOT NULL COMMENT 'Relación con el rol asignado al usuario',
  `id_type` varchar(50) NOT NULL COMMENT 'Tipo de documento (CC, CE, TI, etc.)',
  `id_number` varchar(50) NOT NULL COMMENT 'Número de identificación del usuario',
  `first_name` varchar(100) NOT NULL COMMENT 'Nombres del usuario',
  `last_name` varchar(100) NOT NULL COMMENT 'Apellidos del usuario',
  `gender` varchar(20) DEFAULT NULL COMMENT 'Género del usuario',
  `email` varchar(150) NOT NULL COMMENT 'Correo electrónico del usuario',
  `password` varchar(255) NOT NULL COMMENT 'Contraseña encriptada del usuario',
  `registration_date` datetime DEFAULT current_timestamp() COMMENT 'Fecha de registro del usuario',
  `is_premium` tinyint(1) DEFAULT 0 COMMENT 'Indica si el usuario posee una membresía premium'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla de usuarios registrados en el sistema, con sus datos básicos y relación de rol.';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_plan`
--

CREATE TABLE `user_plan` (
  `id` int(11) NOT NULL COMMENT 'Identificador único de la suscripción del usuario',
  `user_id` int(11) NOT NULL COMMENT 'Relación con el usuario premium',
  `payment_plan_id` int(11) NOT NULL COMMENT 'Relación con el plan de pago seleccionado',
  `start_date` date NOT NULL COMMENT 'Fecha de inicio del plan',
  `end_date` date NOT NULL COMMENT 'Fecha de finalización del plan',
  `is_active` tinyint(1) DEFAULT 1 COMMENT 'Indica si la suscripción está activa'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tabla que asocia a cada usuario con su plan de pago premium actual.';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ad`
--
ALTER TABLE `ad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `provider_id` (`provider_id`);

--
-- Indices de la tabla `ad_rate`
--
ALTER TABLE `ad_rate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_adrate_provider` (`provider_id`);

--
-- Indices de la tabla `ad_view`
--
ALTER TABLE `ad_view`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_ad_id` (`ad_id`),
  ADD KEY `idx_user_id` (`user_id`);

--
-- Indices de la tabla `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `audit_log`
--
ALTER TABLE `audit_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_user_id` (`user_id`),
  ADD KEY `idx_table_name` (`table_name`),
  ADD KEY `idx_action_type` (`action_type`);

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `payment_plan`
--
ALTER TABLE `payment_plan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `payment_record`
--
ALTER TABLE `payment_record`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_paymentrecord_userplan` (`user_plan_id`);

--
-- Indices de la tabla `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_playlist_user` (`user_id`);

--
-- Indices de la tabla `playlist_song`
--
ALTER TABLE `playlist_song`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `playlist_id` (`playlist_id`,`song_id`),
  ADD KEY `fk_ps_song` (`song_id`);

--
-- Indices de la tabla `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_song_artist` (`artist_id`),
  ADD KEY `fk_song_category` (`category_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_number` (`id_number`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_user_role` (`role_id`);

--
-- Indices de la tabla `user_plan`
--
ALTER TABLE `user_plan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_userplan_user` (`user_id`),
  ADD KEY `fk_userplan_plan` (`payment_plan_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ad`
--
ALTER TABLE `ad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del anuncio';

--
-- AUTO_INCREMENT de la tabla `ad_rate`
--
ALTER TABLE `ad_rate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la tarifa por visualización';

--
-- AUTO_INCREMENT de la tabla `ad_view`
--
ALTER TABLE `ad_view`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del registro de visualización de anuncio';

--
-- AUTO_INCREMENT de la tabla `artist`
--
ALTER TABLE `artist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del artista';

--
-- AUTO_INCREMENT de la tabla `audit_log`
--
ALTER TABLE `audit_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del log';

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la categoría';

--
-- AUTO_INCREMENT de la tabla `payment_plan`
--
ALTER TABLE `payment_plan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del plan de pago';

--
-- AUTO_INCREMENT de la tabla `payment_record`
--
ALTER TABLE `payment_record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del registro de pago mensual';

--
-- AUTO_INCREMENT de la tabla `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la lista de reproducción';

--
-- AUTO_INCREMENT de la tabla `playlist_song`
--
ALTER TABLE `playlist_song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del registro playlist-song';

--
-- AUTO_INCREMENT de la tabla `provider`
--
ALTER TABLE `provider`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del proveedor o cliente publicitario';

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del rol';

--
-- AUTO_INCREMENT de la tabla `song`
--
ALTER TABLE `song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la canción o podcast';

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del usuario';

--
-- AUTO_INCREMENT de la tabla `user_plan`
--
ALTER TABLE `user_plan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la suscripción del usuario';

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ad`
--
ALTER TABLE `ad`
  ADD CONSTRAINT `fk_ad_provider` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`);

--
-- Filtros para la tabla `ad_rate`
--
ALTER TABLE `ad_rate`
  ADD CONSTRAINT `fk_adrate_provider` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`);

--
-- Filtros para la tabla `ad_view`
--
ALTER TABLE `ad_view`
  ADD CONSTRAINT `fk_adview_ad` FOREIGN KEY (`ad_id`) REFERENCES `ad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_adview_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `audit_log`
--
ALTER TABLE `audit_log`
  ADD CONSTRAINT `fk_auditlog_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL;

--
-- Filtros para la tabla `payment_record`
--
ALTER TABLE `payment_record`
  ADD CONSTRAINT `fk_paymentrecord_userplan` FOREIGN KEY (`user_plan_id`) REFERENCES `user_plan` (`id`);

--
-- Filtros para la tabla `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `fk_playlist_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `playlist_song`
--
ALTER TABLE `playlist_song`
  ADD CONSTRAINT `fk_ps_playlist` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`),
  ADD CONSTRAINT `fk_ps_song` FOREIGN KEY (`song_id`) REFERENCES `song` (`id`);

--
-- Filtros para la tabla `song`
--
ALTER TABLE `song`
  ADD CONSTRAINT `fk_song_artist` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`),
  ADD CONSTRAINT `fk_song_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Filtros para la tabla `user_plan`
--
ALTER TABLE `user_plan`
  ADD CONSTRAINT `fk_userplan_plan` FOREIGN KEY (`payment_plan_id`) REFERENCES `payment_plan` (`id`),
  ADD CONSTRAINT `fk_userplan_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
