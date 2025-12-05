-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-12-2025 a las 03:01:48
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

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

--
-- Volcado de datos para la tabla `artist`
--

INSERT INTO `artist` (`id`, `name`, `country`, `biography`) VALUES
(1, 'Shakira', 'Colombia', NULL),
(2, 'Juanes', 'Colombia', NULL),
(3, 'Julio Jaramillo', 'Ecuador', NULL),
(4, 'Juan Gabriel', 'Mexico', NULL),
(5, 'TWKD', 'Canada', NULL),
(6, 'Chino y Nacho', 'Venezuela', NULL),
(7, 'Dread Mar I', 'Argentina', NULL);

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `name`, `description`) VALUES
(1, 'Alternativa', NULL),
(2, 'Electrónica', NULL),
(3, 'Funk', NULL),
(4, 'Hip Hop', NULL),
(5, 'Latina', NULL),
(6, 'Reggae', NULL),
(7, 'Rock', NULL),
(8, 'Reggaeton', NULL),
(9, 'Vallenato', NULL),
(10, 'Salsa', NULL),
(11, 'Merengue', NULL),
(12, 'Tropical', NULL),
(13, 'Pop', NULL),
(14, 'Bolero', NULL),
(15, 'Urbano', NULL),
(16, 'Cantina', NULL),
(17, 'Ranchera', NULL);

--
-- Volcado de datos para la tabla `payment_plan`
--

INSERT INTO `payment_plan` (`id`, `name`, `description`, `monthly_price`, `duration_months`, `is_active`) VALUES
(1, 'Plan Ritmo', 'Ideal para quienes quieren probar la experiencia premium sin compromiso. Acceso ilimitado a música, playlists exclusivas y sin anuncios.', 22700.00, 1, 1),
(2, 'Plan Melodía', 'Perfecto para quienes disfrutan la música a diario. Ahorra frente al plan mensual y asegura medio año de sonido sin interrupciones.', 113950.00, 6, 1),
(3, 'Plan Sinfonía	', 'Diseñado para los que buscan más tiempo de música continua con un precio aún más conveniente. La mejor opción intermedia para grandes ahorros.', 140400.00, 8, 1),
(4, 'Plan Épico	', 'El plan definitivo: un año entero de música ilimitada, sin anuncios y con el mayor ahorro. Tu soundtrack personal para todo el año.\r\n', 189750.00, 12, 1);

--
-- Volcado de datos para la tabla `playlist`
--

INSERT INTO `playlist` (`id`, `user_id`, `name`, `creation_date`) VALUES
(1, 1, 'favorito', '2025-12-04 20:41:38'),
(2, 2, 'favorito', '2025-12-04 20:41:38'),
(3, 1, 'Para Rumbear', '2025-12-04 20:56:45');

--
-- Volcado de datos para la tabla `playlist_song`
--

INSERT INTO `playlist_song` (`id`, `playlist_id`, `song_id`) VALUES
(1, 1, 13),
(5, 1, 23),
(4, 1, 29),
(3, 1, 37),
(2, 1, 38),
(7, 2, 9),
(6, 2, 18),
(15, 2, 21),
(8, 2, 36),
(9, 3, 21),
(10, 3, 30),
(13, 3, 31),
(12, 3, 32),
(11, 3, 33);

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `name`, `description`) VALUES
(1, 'Superadministrator', NULL),
(2, 'Administrator', NULL),
(3, 'Auxiliary', NULL),
(4, 'Customer', NULL);

--
-- Volcado de datos para la tabla `song`
--

INSERT INTO `song` (`id`, `artist_id`, `category_id`, `title`, `duration`, `audio_url`, `release_date`, `views`) VALUES
(7, 1, 13, 'Hips Don’t Lie', 221, 'hips_dont_lie.mp4', '2006-02-14', 0),
(8, 1, 6, 'Waka Waka (This Time for Africa)', 203, 'waka_waka.mp4', '2010-05-07', 0),
(9, 1, 8, 'La Tortura', 215, 'la_tortura.mp4', '2005-04-12', 0),
(10, 1, 13, 'Loba', 189, 'loba.mp4', '2009-07-06', 0),
(11, 1, 13, 'Ojos Así', 234, 'ojos_asi.mp4', '1999-07-23', 0),
(12, 2, 13, 'La Camisa Negra', 222, 'la_camisa_negra.mp4', '2005-01-25', 0),
(13, 2, 13, 'A Dios le Pido', 196, 'a_dios_le_pido.mp4', '2002-07-19', 0),
(14, 2, 13, 'Me Enamora', 194, 'me_enamora.mp4', '2007-09-10', 0),
(15, 2, 13, 'Es Por Ti', 240, 'es_por_ti.mp4', '2002-07-19', 0),
(16, 2, 13, 'Volverte a Ver', 228, 'volverte_a_ver.mp4', '2004-11-30', 0),
(17, 2, 13, 'Nada Valgo Sin Tu Amor', 230, 'nada_valgo_sin_tu_amor.mp4', '2004-07-26', 0),
(18, 2, 13, 'Fotografía (feat. Nelly Furtado)', 234, 'fotografia.mp4', '2002-07-19', 0),
(19, 2, 13, 'Mala Gente', 210, 'mala_gente.mp4', '2002-07-19', 0),
(20, 2, 13, 'La Paga', 200, 'la_paga.mp4', '2002-07-19', 0),
(21, 2, 13, 'Yerbatero', 215, 'yerbatero.mp4', '2010-06-10', 0),
(22, 3, 14, 'Nuestro Juramento', 210, 'nuestro_juramento.mp4', '1957-01-01', 0),
(23, 3, 14, 'Odiame', 198, 'odiame.mp4', '1950-01-01', 0),
(24, 3, 14, 'Cinco Centavitos', 185, 'cinco_centavitos.mp4', '1959-01-01', 0),
(25, 4, 14, 'Querida', 270, 'querida.mp4', '1984-01-01', 0),
(26, 4, 14, 'Amor Eterno', 320, 'amor_eterno.mp4', '1984-01-01', 0),
(27, 4, 14, 'Hasta Que Te Conocí', 420, 'hasta_que_te_conoci.mp4', '1986-01-01', 0),
(28, 4, 14, 'Se Me Olvidó Otra Vez', 240, 'se_me_olvido_otra_vez.mp4', '1974-01-01', 0),
(29, 5, 4, 'Blinding Lights', 200, 'blinding_lights.mp4', '2019-11-29', 0),
(30, 5, 4, 'Save Your Tears', 215, 'save_your_tears.mp4', '2020-03-20', 0),
(31, 6, 12, 'Mi Niña Bonita', 240, 'mi_nina_bonita.mp4', '2010-04-06', 0),
(32, 6, 15, 'Me Voy Enamorando', 230, 'me_voy_enamorando.mp4', '2015-03-08', 0),
(33, 6, 12, 'El Poeta', 250, 'el_poeta.mp4', '2011-07-19', 0),
(36, 7, 6, 'Tú Sin Mí', 240, 'tu_sin_mi.mp4', '2010-01-01', 0),
(37, 7, 6, 'Así Fue Tu Amor', 230, 'asi_fue_tu_amor.mp4', '2012-01-01', 0),
(38, 7, 6, 'Hoja en Blanco', 250, 'hoja_en_blanco.mp4', '2015-01-01', 0),
(39, 7, 6, 'Perfecta', 220, 'perfecta.mp4', '2009-01-01', 0);

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `role_id`, `id_type`, `id_number`, `first_name`, `last_name`, `gender`, `email`, `password`, `registration_date`, `is_premium`) VALUES
(1, 4, 'CC', '1061777777', 'Gustavo Adolfo', 'Rodriguez Quinayas', 'M', 'tavoz@gmail.com', '123test', '2025-12-04 20:39:48', 0),
(2, 4, 'TI', '10600000', 'Gabriela', 'Rodriguez M', 'F', 'gabyrod@gmail.com', 'test123', '2025-12-04 20:40:21', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
