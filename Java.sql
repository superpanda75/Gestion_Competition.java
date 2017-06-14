-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 06 Juin 2017 à 13:42
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `java`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCompetition` (IN `nom` VARCHAR(100), IN `dateCloture` DATE, IN `enEquipe` BOOLEAN)  INSERT INTO java_competition (java_competition.nom_competition, date, enEquipe) 
VALUES (nom, dateCloture, enEquipe)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addEquipe` (IN `nom` VARCHAR(50))  BEGIN
 INSERT INTO java_candidat (nom_candidat) VALUES (nom); 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addMembreEquipe` (IN `idPers` INT, IN `idEquipe` INT)  NO SQL
BEGIN
    INSERT INTO java_appartenir (id_personne, id_equipe) VALUES (idPers, idEquipe);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPers` (IN `nomP` VARCHAR(255), IN `prenomP` VARCHAR(255), IN `mailP` VARCHAR(255))  NO SQL
BEGIN
    
    INSERT INTO java_candidat (nom_candidat) VALUES (nomP); 
    SET @last_id_in_candidat = LAST_INSERT_ID();
    INSERT INTO java_personne (id_personne,prenom_personne,mail_personne) 		VALUES (@last_id_in_candidat,prenomP,mailP);
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addPersEquipe` (IN `idP` INT, IN `idE` INT)  BEGIN
    INSERT INTO java_appartenir (id_personne, id_equipe) VALUES (idP, idE);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCand` (IN `id_cand` INT)  NO SQL
BEGIN
DECLARE p1 boolean;
    
    SELECT COUNT(p.id_personne) INTO p1 
    FROM java_personne p, java_candidat c
    WHERE c.id_candidat = p.id_personne
    AND p.id_personne = id_cand;
    
    IF p1 = 1 THEN
        DELETE
    	FROM java_personne 
    	WHERE id_personne = id_cand;
        
        DELETE
    	FROM java_candidat 
    	WHERE id_candidat = id_cand;
        
    ELSE
    	DELETE
    	FROM java_candidat 
    	WHERE id_candidat = id_cand;
        
    END IF;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deletePersonneEquipe` (IN `idE` INT(50), IN `idP` INT(50))  NO SQL
BEGIN
    DELETE 
    FROM java_appartenir
    WHERE id_equipe=idE
    AND id_personne=idP;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InscrireCandComp` (IN `idCand` INT, IN `idComp` INT)  NO SQL
BEGIN

    DECLARE P2 BOOLEAN;
    DECLARE P3 BOOLEAN;
    
    SELECT COUNT(java_candidat.id_candidat) into P2
    FROM java_candidat 
    WHERE java_candidat.id_candidat = idCand;
    
    SELECT COUNT(java_competition.id_competition) into P3
    FROM java_competition 
    WHERE java_competition.id_competition = idComp;   

	IF (P2 = 1 AND P3 = 1) THEN

	INSERT INTO java_inscription (id_candidat, id_competition) VALUES (idCand, idComp);
   
   	END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modifComp` (IN `comp` VARCHAR(50), IN `date` DATE, IN `enEquipe` BOOLEAN, IN `id` INT)  MODIFIES SQL DATA
BEGIN

    UPDATE java_competition co SET co.nom_competition = comp,
    co.date = date, 
    co.enEquipe = enEquipe
    WHERE co.id_competition = id;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `modifPersonne` (IN `id` INT, IN `nomCandidat` VARCHAR(50), IN `prenomPersonne` VARCHAR(50), IN `mail` VARCHAR(30))  BEGIN	
        UPDATE java_candidat c SET c.nom_candidat = nomCandidat
        WHERE id_candidat = id;
        UPDATE java_personne p SET p.prenom_personne = prenomPersonne
        WHERE id_personne = id;
        UPDATE java_personne p SET p.mail_personne = mail
        WHERE id_personne = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeCandToComp` (IN `id_comp` INT, IN `id_cand` INT)  NO SQL
BEGIN
    DELETE
    FROM java_inscription 
    WHERE id_competition=id_comp
    AND id_candidat=id_cand;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `selectInscriptionCandidat` (IN `idCandidat` INT)  NO SQL
BEGIN

	DECLARE P1 bool;
     SELECT COUNT(p.id_personne) INTO P1
    FROM java_personne p
    WHERE p.id_personne = idCandidat;
    IF P1 = 0 THEN
      SELECT c.nom_candidat, co.nom_competition, co.date, co.enEquipe FROM 				java_competition co, java_candidat c, java_inscription i WHERE 			co.id_competition = i.id_competition AND i.id_candidat = 			c.id_candidat 	AND c.id_candidat = idCandidat;
    ELSE
    	SELECT c.nom_candidat, p.prenom_personne, p.mail_personne, 			co.nom_competition, co.date, co.enEquipe FROM java_competition co, 				java_candidat c, java_inscription i, java_personne p WHERE 			co.id_competition = i.id_competition AND i.id_candidat = 			c.id_candidat AND p.id_personne = c.id_candidat AND 				c.id_candidat = c.id_candidat; 
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `suppComp` (IN `pID` INT)  BEGIN
	DELETE FROM java_competition  WHERE java_competition.id_competition = pID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `suppPersonne` (IN `id` INT(11))  BEGIN	  
        DELETE
    	FROM java_personne 
    	WHERE java_personne.id_personne = id;
        
        DELETE
    	FROM java_candidat
    	WHERE java_candidat.id_candidat = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCand` (IN `id` INT, IN `nom` VARCHAR(50))  NO SQL
BEGIN
 UPDATE java_candidat SET nom_candidat = nom WHERE id_candidat = id; 
 END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `java_appartenir`
--

CREATE TABLE `java_appartenir` (
  `id_equipe` int(11) NOT NULL,
  `id_personne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `java_appartenir`
--

INSERT INTO `java_appartenir` (`id_equipe`, `id_personne`) VALUES
(18, 15),
(19, 15),
(19, 16),
(20, 16);

--
-- Déclencheurs `java_appartenir`
--
DELIMITER $$
CREATE TRIGGER `before_Insert_appartenir` BEFORE INSERT ON `java_appartenir` FOR EACH ROW BEGIN
	DECLARE P1 boolean;

    SELECT COUNT(p.id_personne) INTO P1 FROM java_personne p 
    WHERE p.id_personne=NEW.id_personne;
    
    IF (P1 <> 1) THEN 
    	SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = "Vous ne pouvez pas ajouter une equipe en tant que membre d'une autre equipe ou d'une personne";
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `java_candidat`
--

CREATE TABLE `java_candidat` (
  `id_candidat` int(11) NOT NULL,
  `nom_candidat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `java_candidat`
--

INSERT INTO `java_candidat` (`id_candidat`, `nom_candidat`) VALUES
(8, 'TeamRo'),
(9, 'teamYellow'),
(13, 'TeamThorin'),
(15, 'eren '),
(16, 'didi'),
(17, 'joueur4'),
(18, 'TeamExploration'),
(19, 'TeamEscadron'),
(20, 'TeamValor'),
(21, 'TeamAAA'),
(22, 'TeamDEF'),
(23, 'Tony'),
(24, 'didi'),
(25, 'Les Manouches'),
(27, 'e'),
(28, 'test'),
(29, 'test'),
(30, 'aby'),
(31, 'aby'),
(32, 'bb'),
(33, 'bb'),
(34, 'gg'),
(35, 'gg'),
(36, 'ff'),
(38, 'gael'),
(39, 'user'),
(40, 'salut'),
(41, 'sa'),
(42, 'sasa'),
(43, 'sasasa'),
(44, NULL),
(45, 'TeamRo'),
(46, 'teamYellow'),
(47, 'TeamThorin'),
(48, 'TeamExploration'),
(49, 'TeamEscadron'),
(50, 'TeamValor'),
(51, 'TeamAAA'),
(52, 'TeamDEF'),
(53, 'Les Manouches'),
(54, 'test'),
(55, 'aby'),
(56, 'aby'),
(57, 'bb'),
(58, 'sa'),
(59, 'sasa'),
(60, 'sasasa'),
(61, 'TeamRo'),
(62, 'teamYellow'),
(63, 'TeamThorin'),
(64, 'TeamExploration'),
(65, 'TeamEscadron'),
(66, 'TeamValor'),
(67, 'TeamAAA'),
(68, 'TeamDEF'),
(69, 'Les Manouches'),
(70, 'test'),
(71, 'aby'),
(72, 'aby'),
(73, 'bb'),
(74, 'sa'),
(75, 'sasa'),
(76, 'sasasa'),
(77, 'TeamRo'),
(78, 'teamYellow'),
(79, 'TeamThorin'),
(80, 'TeamExploration'),
(81, 'TeamEscadron'),
(82, 'TeamValor'),
(83, 'TeamAAA'),
(84, 'TeamDEF'),
(85, 'Les Manouches'),
(86, 'test'),
(87, 'aby'),
(88, 'aby'),
(89, 'bb'),
(90, 'sa'),
(91, 'sasa'),
(92, 'sasasa'),
(93, 'TeamRo'),
(94, 'teamYellow'),
(95, 'TeamThorin'),
(96, 'TeamExploration'),
(97, 'TeamEscadron'),
(98, 'TeamValor'),
(99, 'TeamAAA'),
(100, 'TeamDEF'),
(101, 'Les Manouches'),
(102, 'test'),
(103, 'aby'),
(104, 'aby'),
(105, 'bb'),
(106, 'sa'),
(107, 'sasa'),
(108, 'sasasa'),
(109, 'TeamRo'),
(110, 'teamYellow'),
(111, 'TeamThorin'),
(112, 'TeamExploration'),
(113, 'TeamEscadron'),
(114, 'TeamValor'),
(115, 'TeamAAA'),
(116, 'TeamDEF'),
(117, 'Les Manouches'),
(118, 'test'),
(119, 'aby'),
(120, 'aby'),
(121, 'bb'),
(122, 'sa'),
(123, 'sasa'),
(124, 'sasasa'),
(125, 'TeamRo'),
(126, 'teamYellow'),
(127, 'TeamThorin'),
(128, 'TeamExploration'),
(129, 'TeamEscadron'),
(130, 'TeamValor'),
(131, 'TeamAAA'),
(132, 'TeamDEF'),
(133, 'Les Manouches'),
(134, 'test'),
(135, 'aby'),
(136, 'aby'),
(137, 'bb'),
(138, 'sa'),
(139, 'sasa'),
(140, 'sasasa'),
(141, 'k');

--
-- Déclencheurs `java_candidat`
--
DELIMITER $$
CREATE TRIGGER `before_Insert_candidat` BEFORE INSERT ON `java_candidat` FOR EACH ROW BEGIN
   IF NEW.nom_candidat = ""
   THEN 
   SET NEW.nom_candidat = NULL;
   END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_Update_candidat` BEFORE UPDATE ON `java_candidat` FOR EACH ROW BEGIN	
    IF NEW.nom_candidat = "" THEN
   		SET NEW.nom_candidat = OLD.nom_candidat;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `java_competition`
--

CREATE TABLE `java_competition` (
  `id_competition` int(11) NOT NULL,
  `nom_competition` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `enEquipe` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `java_competition`
--

INSERT INTO `java_competition` (`id_competition`, `nom_competition`, `date`, `enEquipe`) VALUES
(3, 'solo', '2017-12-05', 0),
(4, 'solo1', '2017-07-23', 0),
(6, 'equipe', '2017-10-30', 1),
(7, 'karathe', '2018-03-03', 0),
(18, 'tennis', '2018-12-31', 0),
(19, 'natation', '2017-12-31', 0),
(20, 'esport', '2017-12-21', 1),
(21, 'boxe', '2018-07-20', 1),
(22, 'dance', '2018-05-02', 1),
(23, 'solo', '2017-12-05', 0),
(24, 'solo1', '2017-07-23', 0),
(25, 'equipe', '2017-10-30', 1),
(26, 'karathe', '2018-03-03', 0),
(27, 'tennis', '2018-12-31', 0),
(28, 'natation', '2017-12-31', 0),
(29, 'esport', '2017-12-21', 1),
(30, 'boxe', '2018-07-20', 1),
(31, 'dance', '2018-05-02', 1),
(32, 'solo', '2017-12-05', 0),
(33, 'solo1', '2017-07-23', 0),
(34, 'equipe', '2017-10-30', 1),
(35, 'karathe', '2018-03-03', 0),
(36, 'tennis', '2018-12-31', 0),
(37, 'natation', '2017-12-31', 0),
(38, 'esport', '2017-12-21', 1),
(39, 'boxe', '2018-07-20', 1),
(40, 'dance', '2018-05-02', 1),
(41, 'solo', '2017-12-05', 0),
(42, 'solo1', '2017-07-23', 0),
(43, 'equipe', '2017-10-30', 1),
(44, 'karathe', '2018-03-03', 0),
(45, 'tennis', '2018-12-31', 0),
(46, 'natation', '2017-12-31', 0),
(47, 'esport', '2017-12-21', 1),
(48, 'boxe', '2018-07-20', 1),
(49, 'dance', '2018-05-02', 1),
(50, 'solo', '2017-12-05', 0),
(51, 'solo1', '2017-07-23', 0),
(52, 'equipe', '2017-10-30', 1),
(53, 'karathe', '2018-03-03', 0),
(54, 'tennis', '2018-12-31', 0),
(55, 'natation', '2017-12-31', 0),
(56, 'esport', '2017-12-21', 1),
(57, 'boxe', '2018-07-20', 1),
(58, 'dance', '2018-05-02', 1),
(59, 'solo', '2017-12-05', 0),
(60, 'solo1', '2017-07-23', 0),
(61, 'equipe', '2017-10-30', 1),
(62, 'karathe', '2018-03-03', 0),
(63, 'tennis', '2018-12-31', 0),
(64, 'natation', '2017-12-31', 0),
(65, 'esport', '2017-12-21', 1),
(66, 'boxe', '2018-07-20', 1),
(67, 'dance', '2018-05-02', 1),
(68, 'solo', '2017-12-05', 0),
(69, 'solo1', '2017-07-23', 0),
(70, 'equipe', '2017-10-30', 1),
(71, 'karathe', '2018-03-03', 0),
(72, 'tennis', '2018-12-31', 0),
(73, 'natation', '2017-12-31', 0),
(74, 'esport', '2017-12-21', 1),
(75, 'boxe', '2018-07-20', 1),
(76, 'dance', '2018-05-02', 1),
(77, 'solo', '2017-12-05', 0),
(78, 'solo1', '2017-07-23', 0),
(79, 'equipe', '2017-10-30', 1),
(80, 'karathe', '2018-03-03', 0),
(81, 'tennis', '2018-12-31', 0),
(82, 'natation', '2017-12-31', 0),
(83, 'esport', '2017-12-21', 1),
(84, 'boxe', '2018-07-20', 1),
(85, 'dance', '2018-05-02', 1),
(86, 'solo', '2017-12-05', 0),
(87, 'solo1', '2017-07-23', 0),
(88, 'equipe', '2017-10-30', 1),
(89, 'karathe', '2018-03-03', 0),
(90, 'tennis', '2018-12-31', 0),
(91, 'natation', '2017-12-31', 0),
(92, 'esport', '2017-12-21', 1),
(93, 'boxe', '2018-07-20', 1),
(94, 'dance', '2018-05-02', 1),
(95, 'solo', '2017-12-05', 0),
(96, 'solo1', '2017-07-23', 0),
(97, 'equipe', '2017-10-30', 1),
(98, 'karathe', '2018-03-03', 0),
(99, 'tennis', '2018-12-31', 0),
(100, 'natation', '2017-12-31', 0),
(101, 'esport', '2017-12-21', 1),
(102, 'boxe', '2018-07-20', 1),
(103, 'dance', '2018-05-02', 1),
(104, 'solo', '2017-12-05', 0),
(105, 'solo1', '2017-07-23', 0);

--
-- Déclencheurs `java_competition`
--
DELIMITER $$
CREATE TRIGGER `before_Insert_competition` BEFORE INSERT ON `java_competition` FOR EACH ROW BEGIN
   IF NEW.date > NEW.date
   THEN 
   SIGNAL SQLSTATE '45000'
   SET MESSAGE_TEXT = 'Date Cloture doit être après la Date Ouverture';
   END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_Update_competition` BEFORE UPDATE ON `java_competition` FOR EACH ROW BEGIN
	IF NEW.date > NEW.date
	THEN 
 	SIGNAL SQLSTATE '45000'
 	SET MESSAGE_TEXT = 'Date Cloture doit être après la Date Ouverture';
 	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `java_inscription`
--

CREATE TABLE `java_inscription` (
  `id_candidat` int(11) NOT NULL,
  `id_competition` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `java_inscription`
--

INSERT INTO `java_inscription` (`id_candidat`, `id_competition`) VALUES
(15, 3),
(16, 3),
(15, 4),
(16, 4),
(17, 4),
(8, 6),
(9, 6),
(13, 6),
(15, 7);

--
-- Déclencheurs `java_inscription`
--
DELIMITER $$
CREATE TRIGGER `before_Insert_inscrire` BEFORE INSERT ON `java_inscription` FOR EACH ROW BEGIN
	DECLARE P1 boolean;
    DECLARE P2 int;
    DECLARE P3 date;

	SELECT co.enEquipe INTO P1 FROM java_competition co 
         WHERE co.id_competition=NEW.id_competition;
        SELECT COUNT(p.id_personne) INTO P2 FROM java_personne p 
         WHERE p.id_personne=NEW.id_candidat;
        SELECT co.date INTO P3 
          FROM java_competition co
            WHERE co.id_competition=NEW.id_competition;
    
    IF (P3 < CURRENT_DATE) THEN
    	SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Date de clôture dépassé';
    ELSE IF (P1 = P2) THEN 
    	SIGNAL SQLSTATE '45001'
        SET MESSAGE_TEXT = 'Inscription impossible';
    END IF;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `java_personne`
--

CREATE TABLE `java_personne` (
  `prenom_personne` varchar(50) DEFAULT NULL,
  `mail_personne` varchar(30) DEFAULT NULL,
  `id_personne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `java_personne`
--

INSERT INTO `java_personne` (`prenom_personne`, `mail_personne`, `id_personne`) VALUES
('joueur2', 'joueur@gmail.com', 15),
('didi', 'didi@gmail.com', 16),
('j', 'j', 17),
('Dent de plomb', 'azerty', 23),
('didi123', 'djlz', 24),
('le couteau', 'qsdf', 27),
('test', 'test@gmail.com', 28),
('bb', 'bb', 33),
('ggg', 'ggg', 34),
('gg', 'gg', 35),
('ff', 'ff', 36),
('garl', 'gael', 38),
('user', 'user@gmail.com', 39),
('salut', 'salut@gmail.com', 40),
('k', 'k', 141);

--
-- Déclencheurs `java_personne`
--
DELIMITER $$
CREATE TRIGGER `before_Update_personne` BEFORE UPDATE ON `java_personne` FOR EACH ROW BEGIN	
    IF NEW.prenom_personne = "" THEN
   		SET NEW.prenom_personne = OLD.prenom_personne;
    END IF;
    
    IF NEW.mail_personne = "" THEN
   		SET NEW.mail_personne = OLD.mail_personne;
    END IF;
END
$$
DELIMITER ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `java_appartenir`
--
ALTER TABLE `java_appartenir`
  ADD PRIMARY KEY (`id_equipe`,`id_personne`),
  ADD KEY `FK_appartenir_id_personne` (`id_personne`);

--
-- Index pour la table `java_candidat`
--
ALTER TABLE `java_candidat`
  ADD PRIMARY KEY (`id_candidat`);

--
-- Index pour la table `java_competition`
--
ALTER TABLE `java_competition`
  ADD PRIMARY KEY (`id_competition`);

--
-- Index pour la table `java_inscription`
--
ALTER TABLE `java_inscription`
  ADD PRIMARY KEY (`id_candidat`,`id_competition`),
  ADD KEY `FK_inscrire_id_competition` (`id_competition`);

--
-- Index pour la table `java_personne`
--
ALTER TABLE `java_personne`
  ADD PRIMARY KEY (`id_personne`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `java_candidat`
--
ALTER TABLE `java_candidat`
  MODIFY `id_candidat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;
--
-- AUTO_INCREMENT pour la table `java_competition`
--
ALTER TABLE `java_competition`
  MODIFY `id_competition` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `java_appartenir`
--
ALTER TABLE `java_appartenir`
  ADD CONSTRAINT `FK_appartenir_id_equipe` FOREIGN KEY (`id_equipe`) REFERENCES `java_candidat` (`id_candidat`),
  ADD CONSTRAINT `FK_appartenir_id_personne` FOREIGN KEY (`id_personne`) REFERENCES `java_candidat` (`id_candidat`),
  ADD CONSTRAINT `java_appartenir_ibfk_1` FOREIGN KEY (`id_equipe`) REFERENCES `java_candidat` (`id_candidat`),
  ADD CONSTRAINT `java_appartenir_ibfk_2` FOREIGN KEY (`id_personne`) REFERENCES `java_candidat` (`id_candidat`);

--
-- Contraintes pour la table `java_inscription`
--
ALTER TABLE `java_inscription`
  ADD CONSTRAINT `FK_inscrire_id_candidat` FOREIGN KEY (`id_candidat`) REFERENCES `java_candidat` (`id_candidat`),
  ADD CONSTRAINT `FK_inscrire_id_competition` FOREIGN KEY (`id_competition`) REFERENCES `java_competition` (`id_competition`),
  ADD CONSTRAINT `java_inscription_ibfk_1` FOREIGN KEY (`id_competition`) REFERENCES `java_competition` (`id_competition`);

--
-- Contraintes pour la table `java_personne`
--
ALTER TABLE `java_personne`
  ADD CONSTRAINT `FK_personne_id_candidat` FOREIGN KEY (`id_personne`) REFERENCES `java_candidat` (`id_candidat`),
  ADD CONSTRAINT `java_personne_ibfk_1` FOREIGN KEY (`id_personne`) REFERENCES `java_candidat` (`id_candidat`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
