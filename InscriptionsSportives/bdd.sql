-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.7.14 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Export de la structure de la base pour hbenromdhane
CREATE DATABASE IF NOT EXISTS `hbenromdhane` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hbenromdhane`;

-- Export de la structure de la table hbenromdhane. java_appartenir
CREATE TABLE IF NOT EXISTS `java_appartenir` (
  `id_equipe` int(11) unsigned NOT NULL,
  `id_personne` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_equipe`,`id_personne`),
  KEY `cle_personne` (`id_personne`),
  CONSTRAINT `cle_personne` FOREIGN KEY (`id_personne`) REFERENCES `java_personne` (`id_personne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table hbenromdhane.java_appartenir : ~41 rows (environ)
/*!40000 ALTER TABLE `java_appartenir` DISABLE KEYS */;
INSERT INTO `java_appartenir` (`id_equipe`, `id_personne`) VALUES
	(2, 1),
	(3, 4),
	(3, 7),
	(5, 10),
	(3, 12),
	(7, 14),
	(7, 16),
	(2, 18),
	(7, 19),
	(3, 23),
	(7, 24),
	(5, 25),
	(2, 26),
	(7, 29),
	(3, 32),
	(5, 38),
	(5, 39),
	(2, 42),
	(5, 43),
	(2, 45),
	(2, 46),
	(4, 52),
	(1, 56),
	(6, 57),
	(6, 67),
	(1, 69),
	(1, 81),
	(4, 82),
	(8, 83),
	(6, 85),
	(4, 87),
	(1, 90),
	(4, 91),
	(6, 93),
	(4, 94),
	(8, 95),
	(8, 96),
	(8, 97),
	(1, 98),
	(8, 99),
	(6, 100);
/*!40000 ALTER TABLE `java_appartenir` ENABLE KEYS */;

-- Export de la structure de la table hbenromdhane. java_candidat
CREATE TABLE IF NOT EXISTS `java_candidat` (
  `id_candidat` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom_candidat` varchar(50) DEFAULT 'Pas de nom',
  PRIMARY KEY (`id_candidat`),
  CONSTRAINT `id_personne` FOREIGN KEY (`id_candidat`) REFERENCES `java_personne` (`id_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- Export de données de la table hbenromdhane.java_candidat : ~42 rows (environ)
/*!40000 ALTER TABLE `java_candidat` DISABLE KEYS */;
INSERT INTO `java_candidat` (`id_candidat`, `nom_candidat`) VALUES
	(1, 'vitae mauris'),
	(2, 'rutrum urna,'),
	(3, 'Integer id'),
	(4, 'auctor velit.'),
	(5, 'purus sapien,'),
	(6, 'tempus risus.'),
	(7, 'natoque penatibus'),
	(8, 'Duis sit'),
	(9, 'diam. Sed'),
	(10, 'Nulla aliquet.'),
	(11, 'lobortis quam'),
	(12, 'augue eu'),
	(13, 'nibh enim,'),
	(14, 'nunc nulla'),
	(15, 'commodo at,'),
	(16, 'id, ante.'),
	(17, 'augue. Sed'),
	(18, 'risus. Nulla'),
	(19, 'nec metus'),
	(20, 'dui, semper'),
	(21, 'egestas hendrerit'),
	(22, 'id enim.'),
	(23, 'Sed et'),
	(24, 'enim commodo'),
	(25, 'orci lacus'),
	(26, 'Praesent interdum'),
	(27, 'fringilla euismod'),
	(28, 'interdum. Sed'),
	(29, 'ullamcorper. Duis'),
	(30, 'ipsum. Curabitur'),
	(31, 'Mauris vel'),
	(32, 'mauris. Integer'),
	(33, 'Mauris vel'),
	(34, 'Morbi neque'),
	(35, 'molestie tellus.'),
	(36, 'libero. Integer'),
	(37, 'ac metus'),
	(38, 'sed consequat'),
	(39, 'tincidunt congue'),
	(40, 'dui nec'),
	(41, 'aliquet lobortis,'),
	(42, 'mauris. Morbi');
/*!40000 ALTER TABLE `java_candidat` ENABLE KEYS */;

-- Export de la structure de la table hbenromdhane. java_competition
CREATE TABLE IF NOT EXISTS `java_competition` (
  `id_competition` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom_competition` varchar(50) DEFAULT 'pas de nom',
  `en_solo` int(10) unsigned DEFAULT '1',
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_competition`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- Export de données de la table hbenromdhane.java_competition : ~100 rows (environ)
/*!40000 ALTER TABLE `java_competition` DISABLE KEYS */;
INSERT INTO `java_competition` (`id_competition`, `nom_competition`, `en_solo`, `date`) VALUES
	(1, 'Maecenas malesuada fringilla', 1, NULL),
	(2, 'nonummy. Fusce fermentum', 1, NULL),
	(3, 'ac', 1, NULL),
	(4, 'est', 1, NULL),
	(5, 'nec', 1, NULL),
	(6, 'vestibulum, neque sed dictum', 1, NULL),
	(7, 'est. Nunc laoreet', 1, NULL),
	(8, 'imperdiet dictum magna.', 1, NULL),
	(9, 'amet', 1, NULL),
	(10, 'ut', 1, NULL),
	(11, 'neque', 1, NULL),
	(12, 'arcu. Morbi sit', 1, NULL),
	(13, 'pede et risus. Quisque', 1, NULL),
	(14, 'ad litora torquent per', 1, NULL),
	(15, 'dictum ultricies ligula. Nullam', 1, NULL),
	(16, 'pede ac urna.', 1, NULL),
	(17, 'Quisque fringilla', 1, NULL),
	(18, 'tristique aliquet. Phasellus fermentum', 1, NULL),
	(19, 'nec, diam. Duis mi', 1, NULL),
	(20, 'id nunc interdum', 1, NULL),
	(21, 'eu lacus. Quisque imperdiet,', 1, NULL),
	(22, 'Mauris molestie pharetra nibh.', 1, NULL),
	(23, 'Mauris nulla. Integer', 1, NULL),
	(24, 'rhoncus. Nullam velit', 1, NULL),
	(25, 'semper pretium neque. Morbi', 1, NULL),
	(26, 'ipsum cursus vestibulum.', 1, NULL),
	(27, 'ut odio vel est', 1, NULL),
	(28, 'eu', 1, NULL),
	(29, 'turpis nec mauris', 1, NULL),
	(30, 'id', 1, NULL),
	(31, 'Donec egestas. Aliquam', 1, NULL),
	(32, 'molestie', 1, NULL),
	(33, 'non nisi.', 1, NULL),
	(34, 'lectus', 1, NULL),
	(35, 'euismod', 1, NULL),
	(36, 'sit amet metus.', 1, NULL),
	(37, 'Ut nec urna et', 1, NULL),
	(38, 'hendrerit. Donec', 1, NULL),
	(39, 'at risus. Nunc ac', 1, NULL),
	(40, 'adipiscing', 1, NULL),
	(41, 'bibendum ullamcorper.', 1, NULL),
	(42, 'accumsan convallis, ante', 1, NULL),
	(43, 'consectetuer, cursus', 1, NULL),
	(44, 'imperdiet ullamcorper. Duis at', 1, NULL),
	(45, 'cursus non, egestas', 1, NULL),
	(46, 'ipsum. Suspendisse sagittis. Nullam', 1, NULL),
	(47, 'sed leo. Cras vehicula', 1, NULL),
	(48, 'dolor, tempus non,', 1, NULL),
	(49, 'sit amet diam eu', 1, NULL),
	(50, 'mauris', 1, NULL),
	(51, 'dictum. Proin eget odio.', 1, NULL),
	(52, 'Pellentesque', 1, NULL),
	(53, 'sapien.', 1, NULL),
	(54, 'Suspendisse sagittis. Nullam', 1, NULL),
	(55, 'blandit', 1, NULL),
	(56, 'lectus ante', 1, NULL),
	(57, 'amet ultricies sem', 1, NULL),
	(58, 'ornare lectus justo', 1, NULL),
	(59, 'neque. Sed', 1, NULL),
	(60, 'aliquet. Proin velit. Sed', 1, NULL),
	(61, 'Quisque imperdiet, erat', 1, NULL),
	(62, 'pede,', 1, NULL),
	(63, 'nonummy ut, molestie', 1, NULL),
	(64, 'non arcu.', 1, NULL),
	(65, 'arcu et', 1, NULL),
	(66, 'nisl. Nulla eu', 1, NULL),
	(67, 'hendrerit', 1, NULL),
	(68, 'consectetuer', 1, NULL),
	(69, 'mattis semper, dui', 1, NULL),
	(70, 'consectetuer rhoncus. Nullam velit', 1, NULL),
	(71, 'tempus scelerisque, lorem', 1, NULL),
	(72, 'Aliquam tincidunt, nunc ac', 1, NULL),
	(73, 'dui nec urna suscipit', 1, NULL),
	(74, 'Maecenas ornare egestas ligula.', 1, NULL),
	(75, 'ultrices posuere cubilia', 1, NULL),
	(76, 'malesuada fringilla', 1, NULL),
	(77, 'Nunc pulvinar arcu et', 1, NULL),
	(78, 'placerat. Cras dictum', 1, NULL),
	(79, 'erat neque', 1, NULL),
	(80, 'imperdiet nec,', 1, NULL),
	(81, 'risus. Donec', 1, NULL),
	(82, 'tellus non magna. Nam', 1, NULL),
	(83, 'dictum.', 1, NULL),
	(84, 'elit,', 1, NULL),
	(85, 'Cras eu tellus eu', 1, NULL),
	(86, 'sit', 1, NULL),
	(87, 'fringilla cursus purus.', 1, NULL),
	(88, 'nunc sit amet', 1, NULL),
	(89, 'risus.', 1, NULL),
	(90, 'nonummy ultricies ornare,', 1, NULL),
	(91, 'nibh. Quisque nonummy ipsum', 1, NULL),
	(92, 'diam lorem, auctor quis,', 1, NULL),
	(93, 'fringilla. Donec', 1, NULL),
	(94, 'amet, consectetuer adipiscing elit.', 1, NULL),
	(95, 'Duis', 1, NULL),
	(96, 'eget,', 1, NULL),
	(97, 'per', 1, NULL),
	(98, 'consectetuer adipiscing', 1, NULL),
	(99, 'eu', 1, NULL),
	(100, 'nec tempus', 1, NULL);
/*!40000 ALTER TABLE `java_competition` ENABLE KEYS */;

-- Export de la structure de la table hbenromdhane. java_inscription
CREATE TABLE IF NOT EXISTS `java_inscription` (
  `id_candidat` int(10) unsigned NOT NULL,
  `id_competition` int(10) unsigned NOT NULL,
  KEY `id_candidiat` (`id_candidat`),
  KEY `id_competition` (`id_competition`),
  CONSTRAINT `id_candidiat` FOREIGN KEY (`id_candidat`) REFERENCES `java_candidat` (`id_candidat`),
  CONSTRAINT `id_competition` FOREIGN KEY (`id_competition`) REFERENCES `java_competition` (`id_competition`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Export de données de la table hbenromdhane.java_inscription : ~0 rows (environ)
/*!40000 ALTER TABLE `java_inscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `java_inscription` ENABLE KEYS */;

-- Export de la structure de la table hbenromdhane. java_personne
CREATE TABLE IF NOT EXISTS `java_personne` (
  `id_personne` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `prenom_personne` varchar(50) DEFAULT 'pas de prenom',
  PRIMARY KEY (`id_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- Export de données de la table hbenromdhane.java_personne : ~100 rows (environ)
/*!40000 ALTER TABLE `java_personne` DISABLE KEYS */;
INSERT INTO `java_personne` (`id_personne`, `prenom_personne`) VALUES
	(1, 'Grace'),
	(2, 'Catherine'),
	(3, 'Fallon'),
	(4, 'Zachary'),
	(5, 'Regan'),
	(6, 'Axel'),
	(7, 'Jenette'),
	(8, 'Josiah'),
	(9, 'Nelle'),
	(10, 'Leah'),
	(11, 'Mari'),
	(12, 'Olivia'),
	(13, 'Rafael'),
	(14, 'Cheyenne'),
	(15, 'Quyn'),
	(16, 'Hyacinth'),
	(17, 'Zenaida'),
	(18, 'Jonas'),
	(19, 'Ramona'),
	(20, 'Serina'),
	(21, 'Alexis'),
	(22, 'Yoshi'),
	(23, 'Amela'),
	(24, 'Laura'),
	(25, 'Mira'),
	(26, 'Ivana'),
	(27, 'Gil'),
	(28, 'Meghan'),
	(29, 'Katelyn'),
	(30, 'Madeline'),
	(31, 'Rahim'),
	(32, 'Indira'),
	(33, 'Adam'),
	(34, 'Lana'),
	(35, 'Aileen'),
	(36, 'Ignatius'),
	(37, 'Gay'),
	(38, 'Orlando'),
	(39, 'Mercedes'),
	(40, 'Jessamine'),
	(41, 'Reece'),
	(42, 'Georgia'),
	(43, 'Jeanette'),
	(44, 'Mark'),
	(45, 'Stuart'),
	(46, 'Kyra'),
	(47, 'Keelie'),
	(48, 'Georgia'),
	(49, 'Macaulay'),
	(50, 'Amanda'),
	(51, 'Lydia'),
	(52, 'Roary'),
	(53, 'Gretchen'),
	(54, 'Grady'),
	(55, 'Teagan'),
	(56, 'Ifeoma'),
	(57, 'Echo'),
	(58, 'Yuri'),
	(59, 'Demetrius'),
	(60, 'Leroy'),
	(61, 'Xandra'),
	(62, 'Francesca'),
	(63, 'Iris'),
	(64, 'Driscoll'),
	(65, 'Keegan'),
	(66, 'Azalia'),
	(67, 'Tanner'),
	(68, 'Basil'),
	(69, 'Kelly'),
	(70, 'Noelle'),
	(71, 'Jasmine'),
	(72, 'Jeanette'),
	(73, 'Quemby'),
	(74, 'Jackson'),
	(75, 'Brian'),
	(76, 'Kiona'),
	(77, 'Rashad'),
	(78, 'Timon'),
	(79, 'Nita'),
	(80, 'Signe'),
	(81, 'Courtney'),
	(82, 'Shay'),
	(83, 'Phoebe'),
	(84, 'Camden'),
	(85, 'Shoshana'),
	(86, 'Petra'),
	(87, 'Jarrod'),
	(88, 'Malachi'),
	(89, 'Joy'),
	(90, 'Vera'),
	(91, 'Jane'),
	(92, 'Natalie'),
	(93, 'Leo'),
	(94, 'Keaton'),
	(95, 'Brenden'),
	(96, 'Honorato'),
	(97, 'Jana'),
	(98, 'Casey'),
	(99, 'Kareem'),
	(100, 'Maya');
/*!40000 ALTER TABLE `java_personne` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
