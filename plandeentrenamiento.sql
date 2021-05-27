-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-05-2021 a las 04:06:14
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `plandeentrenamiento`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id_equipo` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id_equipo`, `nombre`, `descripcion`) VALUES
(1, 'Cucuta Dep', 'Doblemente Glorioso'),
(2, 'Medellín', 'DIM');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipoplan`
--

CREATE TABLE `equipoplan` (
  `id_equipoplan` int(11) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  `id_planentrenamiento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `equipoplan`
--

INSERT INTO `equipoplan` (`id_equipoplan`, `id_equipo`, `id_planentrenamiento`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 2, 9),
(5, 2, 12),
(6, 1, 13),
(7, 1, 14),
(8, 2, 15),
(9, 2, 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadomesociclo`
--

CREATE TABLE `estadomesociclo` (
  `id_estadomesociclo` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estadomesociclo`
--

INSERT INTO `estadomesociclo` (`id_estadomesociclo`, `descripcion`) VALUES
(1, 'activo'),
(2, 'inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadomicrociclo`
--

CREATE TABLE `estadomicrociclo` (
  `id_estadomicrociclo` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estadomicrociclo`
--

INSERT INTO `estadomicrociclo` (`id_estadomicrociclo`, `descripcion`) VALUES
(1, 'activo'),
(2, 'inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesociclo`
--

CREATE TABLE `mesociclo` (
  `id_mesociclo` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `id_estadomesociclo` int(11) NOT NULL,
  `id_planentrenamiento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mesociclo`
--

INSERT INTO `mesociclo` (`id_mesociclo`, `id_tipo`, `id_estadomesociclo`, `id_planentrenamiento`) VALUES
(1, 1, 2, 1),
(2, 2, 1, 1),
(3, 1, 1, 2),
(4, 2, 2, 2),
(5, 2, 1, 1),
(6, 1, 1, 9),
(7, 1, 1, 2),
(8, 1, 1, 12),
(9, 1, 1, 15),
(10, 1, 1, 16),
(11, 2, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `microciclo`
--

CREATE TABLE `microciclo` (
  `id_microciclo` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `id_estadomicrociclo` int(11) NOT NULL,
  `id_mesociclo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `microciclo`
--

INSERT INTO `microciclo` (`id_microciclo`, `nombre`, `id_estadomicrociclo`, `id_mesociclo`) VALUES
(1, 'microciclo 1', 1, 1),
(2, 'microciclo 2', 2, 2),
(3, 'microciclo 3', 1, 3),
(4, 'microciclo 4', 2, 4),
(5, 'microciclo 5', 1, 1),
(6, 'microciclo 6', 1, 4),
(7, 'Microciclo test', 1, 1),
(9, 'Microciclo11', 1, 10),
(10, 'Microciclo22', 2, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planentrenamiento`
--

CREATE TABLE `planentrenamiento` (
  `id_planentrenamiento` int(11) NOT NULL,
  `fechainicio` date NOT NULL,
  `fechafin` date NOT NULL,
  `observaciones` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `planentrenamiento`
--

INSERT INTO `planentrenamiento` (`id_planentrenamiento`, `fechainicio`, `fechafin`, `observaciones`, `descripcion`) VALUES
(1, '2021-05-22', '2021-05-28', 'observación de prueba 1', 'descripción de prueba 1'),
(2, '2021-05-24', '2021-06-30', 'Observación de prueba 2', 'Descripción de prueba 2'),
(3, '2021-05-27', '2021-05-28', 'observación ejemplo', 'descripción ejemplo'),
(9, '2021-02-03', '2021-03-16', 'Observación prueba 123', 'Descripción prueba 123'),
(12, '2021-02-03', '2021-03-16', 'Observación prueba video', 'Descripción prueba video'),
(13, '2021-02-03', '2021-03-16', 'Observación test 1', 'Descripción test 1'),
(14, '2021-02-03', '2021-03-16', 'Observación test 2', 'Descripción test 2'),
(15, '2021-02-03', '2021-03-16', 'Observación test 3', 'Descripción test 3'),
(16, '2021-02-03', '2021-03-16', 'Observación test 12', 'Descripción test 12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sesionentrenamiento`
--

CREATE TABLE `sesionentrenamiento` (
  `id_sesionentrenamiento` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `id_microciclo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sesionentrenamiento`
--

INSERT INTO `sesionentrenamiento` (`id_sesionentrenamiento`, `nombre`, `descripcion`, `id_microciclo`) VALUES
(1, 'sesión 1', 'descripción 1', 1),
(5, 'Sesión test', 'Resistencia', 2),
(6, 'Sesión 22', 'Aguante', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

CREATE TABLE `tipo` (
  `id_tipo` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`id_tipo`, `descripcion`) VALUES
(1, 'muscular'),
(2, 'tactico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipomesociclo`
--

CREATE TABLE `tipomesociclo` (
  `id_tipo` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipomesociclo`
--

INSERT INTO `tipomesociclo` (`id_tipo`, `descripcion`) VALUES
(1, 'fisico'),
(2, 'muscular');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id_equipo`);

--
-- Indices de la tabla `equipoplan`
--
ALTER TABLE `equipoplan`
  ADD PRIMARY KEY (`id_equipoplan`),
  ADD KEY `id_equipo` (`id_equipo`),
  ADD KEY `id_planEntrenamieento` (`id_planentrenamiento`);

--
-- Indices de la tabla `estadomesociclo`
--
ALTER TABLE `estadomesociclo`
  ADD PRIMARY KEY (`id_estadomesociclo`);

--
-- Indices de la tabla `estadomicrociclo`
--
ALTER TABLE `estadomicrociclo`
  ADD PRIMARY KEY (`id_estadomicrociclo`);

--
-- Indices de la tabla `mesociclo`
--
ALTER TABLE `mesociclo`
  ADD PRIMARY KEY (`id_mesociclo`),
  ADD KEY `id_tipo` (`id_tipo`),
  ADD KEY `id_estadoMesociclo` (`id_estadomesociclo`),
  ADD KEY `id_planentrenamiento` (`id_planentrenamiento`);

--
-- Indices de la tabla `microciclo`
--
ALTER TABLE `microciclo`
  ADD PRIMARY KEY (`id_microciclo`),
  ADD KEY `id_estadoMicrociclo` (`id_estadomicrociclo`),
  ADD KEY `id_mesociclo` (`id_mesociclo`);

--
-- Indices de la tabla `planentrenamiento`
--
ALTER TABLE `planentrenamiento`
  ADD PRIMARY KEY (`id_planentrenamiento`);

--
-- Indices de la tabla `sesionentrenamiento`
--
ALTER TABLE `sesionentrenamiento`
  ADD PRIMARY KEY (`id_sesionentrenamiento`),
  ADD KEY `id_microciclofk` (`id_microciclo`);

--
-- Indices de la tabla `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`id_tipo`);

--
-- Indices de la tabla `tipomesociclo`
--
ALTER TABLE `tipomesociclo`
  ADD PRIMARY KEY (`id_tipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id_equipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `equipoplan`
--
ALTER TABLE `equipoplan`
  MODIFY `id_equipoplan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `estadomesociclo`
--
ALTER TABLE `estadomesociclo`
  MODIFY `id_estadomesociclo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estadomicrociclo`
--
ALTER TABLE `estadomicrociclo`
  MODIFY `id_estadomicrociclo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `mesociclo`
--
ALTER TABLE `mesociclo`
  MODIFY `id_mesociclo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `microciclo`
--
ALTER TABLE `microciclo`
  MODIFY `id_microciclo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `planentrenamiento`
--
ALTER TABLE `planentrenamiento`
  MODIFY `id_planentrenamiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `sesionentrenamiento`
--
ALTER TABLE `sesionentrenamiento`
  MODIFY `id_sesionentrenamiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tipo`
--
ALTER TABLE `tipo`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipomesociclo`
--
ALTER TABLE `tipomesociclo`
  MODIFY `id_tipo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipoplan`
--
ALTER TABLE `equipoplan`
  ADD CONSTRAINT `FK1xon7ub50mc37u2iapo7wbtkb` FOREIGN KEY (`id_planentrenamiento`) REFERENCES `planentrenamiento` (`id_planentrenamiento`),
  ADD CONSTRAINT `equipoplan_ibfk_1` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `equipoplan_ibfk_2` FOREIGN KEY (`id_planentrenamiento`) REFERENCES `planentrenamiento` (`id_planentrenamiento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mesociclo`
--
ALTER TABLE `mesociclo`
  ADD CONSTRAINT `FKo2is60lq7mvdevjiaeuqg7n28` FOREIGN KEY (`id_estadomesociclo`) REFERENCES `estadomesociclo` (`id_estadomesociclo`),
  ADD CONSTRAINT `mesociclo_ibfk_1` FOREIGN KEY (`id_planentrenamiento`) REFERENCES `planentrenamiento` (`id_planentrenamiento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mesociclo_ibfk_2` FOREIGN KEY (`id_tipo`) REFERENCES `tipo` (`id_tipo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mesociclo_ibfk_3` FOREIGN KEY (`id_estadomesociclo`) REFERENCES `estadomesociclo` (`id_estadomesociclo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `microciclo`
--
ALTER TABLE `microciclo`
  ADD CONSTRAINT `FK4qax6x8r4ytutc3wbffcxv8qk` FOREIGN KEY (`id_estadomicrociclo`) REFERENCES `estadomicrociclo` (`id_estadomicrociclo`),
  ADD CONSTRAINT `microciclo_ibfk_1` FOREIGN KEY (`id_mesociclo`) REFERENCES `mesociclo` (`id_mesociclo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sesionentrenamiento`
--
ALTER TABLE `sesionentrenamiento`
  ADD CONSTRAINT `id_microciclofk` FOREIGN KEY (`id_microciclo`) REFERENCES `microciclo` (`id_microciclo`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
