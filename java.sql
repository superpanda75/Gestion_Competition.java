-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 24 Mars 2017 à 14:45
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

-- --------------------------------------------------------

--
-- Structure de la table `java_appartenir`
--

CREATE TABLE `java_appartenir` (
  `id_equipe` int(11) NOT NULL,
  `id_personne` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `java_appartenir`
--

INSERT INTO `java_appartenir` (`id_equipe`, `id_personne`) VALUES
(1, 1),
(3, 3),
(2, 2),
(0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `java_candidat`
--

CREATE TABLE `java_candidat` (
  `id_candidat` int(11) NOT NULL,
  `nom_candidat` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `java_candidat`
--

INSERT INTO `java_candidat` (`id_candidat`, `nom_candidat`) VALUES
(1, 'benrambdan'),
(2, 'abigael'),
(31, 'gg');

-- --------------------------------------------------------

--
-- Structure de la table `java_competition`
--

CREATE TABLE `java_competition` (
  `id_competition` int(11) NOT NULL,
  `nom_competition` varchar(50) NOT NULL,
  `enEquipe` tinyint(10) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `java_competition`
--

INSERT INTO `java_competition` (`id_competition`, `nom_competition`, `enEquipe`, `date`) VALUES
(1, 'tennis', 1, '2017-03-29 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `java_inscription`
--

CREATE TABLE `java_inscription` (
  `id_candidat` int(11) NOT NULL,
  `id_competition` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `java_inscription`
--

INSERT INTO `java_inscription` (`id_candidat`, `id_competition`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `java_personne`
--

CREATE TABLE `java_personne` (
  `id_personne` int(11) NOT NULL,
  `prenom_personne` varchar(255) NOT NULL,
  `mail_personne` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `java_personne`
--

INSERT INTO `java_personne` (`id_personne`, `prenom_personne`, `mail_personne`) VALUES
(1, 'haithem', 'haithem95170@gmail.com'),
(2, 'aby', 'abigaellehouri@gmail.com'),
(3, 'olivier', 'olivier@hotmail.fr'),
(31, 'ggg', 'ggg');

--
-- Index pour les tables exportées
--

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
  MODIFY `id_candidat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT pour la table `java_competition`
--
ALTER TABLE `java_competition`
  MODIFY `id_competition` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `java_personne`
--
ALTER TABLE `java_personne`
  MODIFY `id_personne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
