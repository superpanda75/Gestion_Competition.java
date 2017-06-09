-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 08 Juin 2017 à 17:33
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
(1, 'test1');

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
('test1', 'test1@gmail.com', 1);

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
  MODIFY `id_candidat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `java_competition`
--
ALTER TABLE `java_competition`
  MODIFY `id_competition` int(11) NOT NULL AUTO_INCREMENT;
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
