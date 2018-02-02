-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 01-02-2018 a las 20:24:29
-- Versión del servidor: 5.7.21-0ubuntu0.16.04.1
-- Versión de PHP: 7.0.22-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `restabar`
--
CREATE DATABASE IF NOT EXISTS `restabar` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `restabar`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camareros`
--

CREATE TABLE `camareros` (
  `ncodcam` int(3) NOT NULL,
  `cnomcam` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `cpuesto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `dfecIni` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tfno` int(10) NOT NULL,
  `ccorreo` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cpasscam` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `camareros`
--

INSERT INTO `camareros` (`ncodcam`, `cnomcam`, `cpuesto`, `dfecIni`, `tfno`, `ccorreo`, `cpasscam`) VALUES
(1, 'alejandro', 'mesas', '11/05/1993', 625252250, 'donColManei@gmail.com', '1234'),
(2, 'luis', 'mesas', '22/07/1999', 691069909, NULL, '1234'),
(3, 'pepito', 'Barra', '19/05/1992', 54654, 'asd@gas.com', 'pepito'),
(4, 'hola', 'Terraza', '19/05/1111', 123321, 'asd@gmai.com', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linpedido`
--

CREATE TABLE `linpedido` (
  `idlin` int(5) NOT NULL,
  `ncodped` int(10) NOT NULL,
  `ncodprod` int(10) NOT NULL,
  `ncantidad` int(10) NOT NULL,
  `cobservaciones` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `linpedido`
--

INSERT INTO `linpedido` (`idlin`, `ncodped`, `ncodprod`, `ncantidad`, `cobservaciones`) VALUES
(140, 2, 1, 1, ''),
(141, 2, 3, 1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `ncodmen` int(11) NOT NULL,
  `ncodcam` int(11) NOT NULL,
  `cmensaje` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`ncodmen`, `ncodcam`, `cmensaje`) VALUES
(5, 2, 'mesa 3 no quiere hielo'),
(6, 4, 'jkk'),
(7, 2, 'hola luis'),
(11, 2, 'luis pedazo de mamon');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `mensaje_camarero`
--
CREATE TABLE `mensaje_camarero` (
`ncodcam` int(3)
,`cnomcam` varchar(100)
,`cpuesto` varchar(50)
,`cmensaje` varchar(200)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesas`
--

CREATE TABLE `mesas` (
  `ncodmesa` int(3) NOT NULL,
  `cocupada` varchar(1) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mesas`
--

INSERT INTO `mesas` (`ncodmesa`, `cocupada`) VALUES
(1, 'n'),
(2, 's'),
(3, 'n'),
(4, 'n'),
(5, 'n'),
(6, 'n');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `ncodped` int(10) NOT NULL,
  `ncodmesa` int(3) NOT NULL,
  `ncodcam` int(3) NOT NULL,
  `pagado` varchar(1) COLLATE utf8_spanish_ci NOT NULL,
  `dfecpedido` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`ncodped`, `ncodmesa`, `ncodcam`, `pagado`, `dfecpedido`) VALUES
(2, 2, 2, 'n', '2018-02-01 19:54:40');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidosPagados`
--

CREATE TABLE `pedidosPagados` (
  `npedpag` int(11) NOT NULL,
  `ncodmesa` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `importe` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `pedidosPagados`
--

INSERT INTO `pedidosPagados` (`npedpag`, `ncodmesa`, `fecha`, `importe`) VALUES
(4, 1, '2018-01-31', 21.98),
(17, 1, '2018-01-31', 9.4),
(26, 3, '2018-02-01', 14.8),
(29, 6, '2018-02-01', 1.6),
(39, 1, '2018-02-01', 10),
(40, 1, '2018-02-01', 21.49);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ncodprod` int(10) NOT NULL,
  `cdesc` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `nstock` float(10,2) NOT NULL,
  `nprecio` float(10,2) NOT NULL,
  `curlfoto` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `ctipoprod` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ncodprod`, `cdesc`, `nstock`, `nprecio`, `curlfoto`, `ctipoprod`) VALUES
(1, 'Coca-Cola', 100.00, 1.80, '/restabar/productos/1.png', 'bebida'),
(2, 'Fanta', 150.00, 1.80, '/restabar/productos/2.png', 'bebida'),
(3, 'Nestea', 150.00, 2.00, '/restabar/productos/3.png', 'bebida'),
(4, 'San Miguel', 60.00, 1.60, '/restabar/productos/4.png', 'bebida'),
(5, 'Carne en salsa', 10.00, 1.00, '/restabar/productos/5.jpg', 'tapa'),
(6, 'Calamares en salsa', 20.00, 1.00, '/restabar/productos/6.jpg', 'tapa'),
(7, 'Hamburguesa completa', 20.00, 1.00, '/restabar/productos/7.jpg', 'tapa'),
(8, 'Merluza papas', 20.00, 1.00, '/restabar/productos/8.jpg', 'tapa'),
(9, 'Patatas y huevo', 20.00, 4.50, '/restabar/productos/9.jpg', 'menu'),
(10, 'Paella', 22.00, 5.99, '/restabar/productos/10.jpg', 'menu'),
(11, 'Espaguetis Carbonara', 55.00, 4.50, '/restabar/productos/11.jpg', 'menu'),
(12, 'Merluza al horno', 6.00, 6.50, '/restabar/productos/12.jpg', 'menu'),
(13, 'Tarta chocolate', 5.00, 4.00, '/restabar/productos/13.jpg', 'postre'),
(14, 'Mouse limon', 8.00, 2.50, '/restabar/productos/14.jpg', 'postre'),
(15, 'Tarta queso', 6.00, 7.00, '/restabar/productos/15.jpg', 'postre'),
(16, 'Crepes chocolate', 9.00, 10.99, '/restabar/productos/16.jpg', 'postre');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `v_pedido_completo`
--
CREATE TABLE `v_pedido_completo` (
`ncodped` int(10)
,`ncodmesa` int(3)
,`ncodcam` int(3)
,`pagado` varchar(1)
,`dfecpedido` varchar(20)
,`ncodprod` int(10)
,`ncantidad` int(10)
,`cdesc` varchar(100)
,`nprecio` float(10,2)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `mensaje_camarero`
--
DROP TABLE IF EXISTS `mensaje_camarero`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mensaje_camarero`  AS  select `camareros`.`ncodcam` AS `ncodcam`,`camareros`.`cnomcam` AS `cnomcam`,`camareros`.`cpuesto` AS `cpuesto`,`mensajes`.`cmensaje` AS `cmensaje` from (`camareros` join `mensajes` on((`camareros`.`ncodcam` = `mensajes`.`ncodcam`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `v_pedido_completo`
--
DROP TABLE IF EXISTS `v_pedido_completo`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_pedido_completo`  AS  select `pedidos`.`ncodped` AS `ncodped`,`pedidos`.`ncodmesa` AS `ncodmesa`,`pedidos`.`ncodcam` AS `ncodcam`,`pedidos`.`pagado` AS `pagado`,`pedidos`.`dfecpedido` AS `dfecpedido`,`linpedido`.`ncodprod` AS `ncodprod`,`linpedido`.`ncantidad` AS `ncantidad`,`productos`.`cdesc` AS `cdesc`,`productos`.`nprecio` AS `nprecio` from ((`pedidos` join `linpedido` on((`pedidos`.`ncodped` = `linpedido`.`ncodped`))) join `productos` on((`linpedido`.`ncodprod` = `productos`.`ncodprod`))) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camareros`
--
ALTER TABLE `camareros`
  ADD PRIMARY KEY (`ncodcam`);

--
-- Indices de la tabla `linpedido`
--
ALTER TABLE `linpedido`
  ADD PRIMARY KEY (`idlin`),
  ADD KEY `fk_lined_prod` (`ncodprod`),
  ADD KEY `fk_linped_pedid2` (`ncodped`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`ncodmen`),
  ADD KEY `fk_camareros_mensajes` (`ncodcam`);

--
-- Indices de la tabla `mesas`
--
ALTER TABLE `mesas`
  ADD PRIMARY KEY (`ncodmesa`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`ncodped`),
  ADD KEY `fk_pedidos_camareros` (`ncodcam`),
  ADD KEY `fk_pedidos_mesa` (`ncodmesa`);

--
-- Indices de la tabla `pedidosPagados`
--
ALTER TABLE `pedidosPagados`
  ADD PRIMARY KEY (`npedpag`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ncodprod`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `linpedido`
--
ALTER TABLE `linpedido`
  MODIFY `idlin` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;
--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `ncodmen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `pedidosPagados`
--
ALTER TABLE `pedidosPagados`
  MODIFY `npedpag` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `linpedido`
--
ALTER TABLE `linpedido`
  ADD CONSTRAINT `fk_lined_prod` FOREIGN KEY (`ncodprod`) REFERENCES `productos` (`ncodprod`),
  ADD CONSTRAINT `fk_linped_pedid2` FOREIGN KEY (`ncodped`) REFERENCES `pedidos` (`ncodped`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_lnped_pedidos` FOREIGN KEY (`ncodped`) REFERENCES `pedidos` (`ncodped`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `fk_camareros_mensajes` FOREIGN KEY (`ncodcam`) REFERENCES `camareros` (`ncodcam`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_pedidos_camareros` FOREIGN KEY (`ncodcam`) REFERENCES `camareros` (`ncodcam`),
  ADD CONSTRAINT `fk_pedidos_mesa` FOREIGN KEY (`ncodmesa`) REFERENCES `mesas` (`ncodmesa`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
