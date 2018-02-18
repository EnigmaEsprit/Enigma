-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 18, 2018 at 06:44 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pidev`
--
CREATE DATABASE IF NOT EXISTS `pidev` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `pidev`;

-- --------------------------------------------------------

--
-- Table structure for table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
CREATE TABLE IF NOT EXISTS `commandes` (
  `idCommande` int(11) NOT NULL AUTO_INCREMENT,
  `prixTotal` float NOT NULL,
  `idUser` int(11) NOT NULL,
  `etat` int(11) NOT NULL DEFAULT '0',
  `adresseDeLivraison` tinyint(4) NOT NULL,
  `DateDeCommande` date NOT NULL,
  `idTransaction` int(11) NOT NULL,
  PRIMARY KEY (`idCommande`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `commentaires`
--

DROP TABLE IF EXISTS `commentaires`;
CREATE TABLE IF NOT EXISTS `commentaires` (
  `idCommentaire` int(11) NOT NULL AUTO_INCREMENT,
  `contenuCommentaire` varchar(2000) NOT NULL,
  `dateAjoutCommentaire` date NOT NULL,
  `dateModificationCommentaire` date NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idCommentaire`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `evenements`
--

DROP TABLE IF EXISTS `evenements`;
CREATE TABLE IF NOT EXISTS `evenements` (
  `idEvenement` int(11) NOT NULL AUTO_INCREMENT,
  `nomEvenement` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `nombreDePlaces` int(11) NOT NULL,
  `tarifEvenement` float NOT NULL,
  `descriptionEvenement` int(11) NOT NULL,
  PRIMARY KEY (`idEvenement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `faqs`
--

DROP TABLE IF EXISTS `faqs`;
CREATE TABLE IF NOT EXISTS `faqs` (
  `idFaq` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `contenuFaq` varchar(800) NOT NULL,
  `dateEnvoiFaq` date NOT NULL,
  `dateReponseFaq` date NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idFaq`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `lignecommandes`
--

DROP TABLE IF EXISTS `lignecommandes`;
CREATE TABLE IF NOT EXISTS `lignecommandes` (
  `idLigneCommande` int(11) NOT NULL AUTO_INCREMENT,
  `idCommande` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `prixTotale` float NOT NULL,
  `quantite` int(11) NOT NULL,
  `idMagasin` int(11) NOT NULL,
  PRIMARY KEY (`idLigneCommande`),
  KEY `idCommande` (`idCommande`),
  KEY `idMagasin` (`idMagasin`),
  KEY `idProduit` (`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `magasins`
--

DROP TABLE IF EXISTS `magasins`;
CREATE TABLE IF NOT EXISTS `magasins` (
  `idMagasin` int(11) NOT NULL AUTO_INCREMENT,
  `nomMagasin` varchar(50) NOT NULL,
  `photoMagasin` varchar(50) NOT NULL,
  `descriptionMagasin` varchar(50) NOT NULL,
  `dateCreationMagasin` date NOT NULL,
  `contactMagasin` varchar(50) NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idMagasin`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `participations`
--

DROP TABLE IF EXISTS `participations`;
CREATE TABLE IF NOT EXISTS `participations` (
  `idParticipation` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idEvenement` int(11) NOT NULL,
  PRIMARY KEY (`idParticipation`,`idUser`,`idEvenement`) USING BTREE,
  KEY `idUser` (`idUser`),
  KEY `idEvenement` (`idEvenement`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `payement`
--

DROP TABLE IF EXISTS `payement`;
CREATE TABLE IF NOT EXISTS `payement` (
  `idPayement` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idCommande` int(11) NOT NULL,
  PRIMARY KEY (`idPayement`),
  KEY `idCommande` (`idCommande`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `idProduit` int(11) NOT NULL AUTO_INCREMENT,
  `referenceProduit` int(11) NOT NULL,
  `nomProduit` varchar(50) NOT NULL,
  `prixProduit` int(11) NOT NULL,
  `photoProduit` varchar(50) NOT NULL,
  `quantiteProduit` int(11) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '0',
  `idpromotion` int(11) NOT NULL DEFAULT '0',
  `categorieMagasin` varchar(50) NOT NULL,
  `idMagasin` int(11) NOT NULL,
  PRIMARY KEY (`idProduit`),
  KEY `idMagasin` (`idMagasin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
CREATE TABLE IF NOT EXISTS `promotions` (
  `idPromotion` int(11) NOT NULL AUTO_INCREMENT,
  `nomPromotion` varchar(50) NOT NULL,
  `imagePromotion` varchar(50) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `idUser` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  PRIMARY KEY (`idPromotion`),
  KEY `idUser` (`idUser`),
  KEY `IdProduit` (`IdProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reclamations`
--

DROP TABLE IF EXISTS `reclamations`;
CREATE TABLE IF NOT EXISTS `reclamations` (
  `idReclamation` int(11) NOT NULL AUTO_INCREMENT,
  `typeReclamation` varchar(20) NOT NULL,
  `objetReclamation` varchar(100) NOT NULL,
  `contenuReclamation` varchar(500) NOT NULL,
  `dateEnvoiReclamation` date NOT NULL,
  `dateReponseReclamation` date NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idReclamation`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `dateDeNaissance` varchar(10) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `zip` int(11) NOT NULL,
  `numeroDuTelephone` varchar(13) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `type` varchar(10) NOT NULL,
  `imageUser` varchar(1000) NOT NULL,
  `numeroDeCarteBancaire` varchar(14) DEFAULT NULL,
  `dateDeValidation` varchar(10) DEFAULT NULL,
  `codeSecret` int(3) DEFAULT NULL,
  `situaitionFiscal` varchar(20) DEFAULT NULL,
  `ribBancaire` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `dateDeNaissance`, `sexe`, `adresse`, `ville`, `zip`, `numeroDuTelephone`, `email`, `password`, `type`, `imageUser`, `numeroDeCarteBancaire`, `dateDeValidation`, `codeSecret`, `situaitionFiscal`, `ribBancaire`) VALUES
(3, 'boumaiza', 'oussama', '1993-04-03', '', 'rue du grand maghreb', 'nabeul', 8011, NULL, 'boumaizaoussama@yahoo.fr', 'azerty', 'client', '', '54158796325874', '2019-04-03', 158, '', ''),
(4, 'Ali', 'Salah', '1970-01-01', '', 'rue du grand maghreb', 'nabeul', 8011, NULL, 'boumaizaoussamab@gmail.com', 'null', '', '', '54788969852014', '1970-01-01', 125, NULL, NULL),
(6, 'Boumaiza', 'Oussama', '3893-05-03', '', 'rue du grand maghreb', 'nabeul', 8011, NULL, '\0boumaizaoussamabb@gmail.com', 'null', 'client', '', '54788969852014', '1970-01-01', 125, NULL, NULL),
(7, 'Boumaiza', 'Oussama', '3893-05-03', '', 'rue du grand maghreb', 'nabeul', 8011, NULL, '\0boumaizaoussama@gmail.com', 'starac', 'admin', '', '54788969852014', '1970-01-01', 125, NULL, NULL),
(8, 'boumaiza', 'oussama', '1993-04-03', 'Homme', 'rue du grand maghreb', 'nabeul', 8011, '50664665', 'boumaizaoussama@yahoo.com', '123', 'vendeur', '', NULL, NULL, NULL, 'Bien', '1234567890azertyui'),
(9, 'salwa', 'larib', '1964-01-12', 'Femme', 'nabeul', 'nabeul', 8011, '22226333', 'laribisalwa@gmail.com', '123', 'admin', '', NULL, NULL, NULL, NULL, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `commentaires_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Constraints for table `faqs`
--
ALTER TABLE `faqs`
  ADD CONSTRAINT `faqs_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Constraints for table `lignecommandes`
--
ALTER TABLE `lignecommandes`
  ADD CONSTRAINT `lignecommandes_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commandes` (`idCommande`),
  ADD CONSTRAINT `lignecommandes_ibfk_2` FOREIGN KEY (`idMagasin`) REFERENCES `magasins` (`idMagasin`),
  ADD CONSTRAINT `lignecommandes_ibfk_3` FOREIGN KEY (`idProduit`) REFERENCES `produits` (`idProduit`);

--
-- Constraints for table `magasins`
--
ALTER TABLE `magasins`
  ADD CONSTRAINT `magasins_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Constraints for table `participations`
--
ALTER TABLE `participations`
  ADD CONSTRAINT `participations_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `participations_ibfk_2` FOREIGN KEY (`idEvenement`) REFERENCES `evenements` (`idEvenement`);

--
-- Constraints for table `payement`
--
ALTER TABLE `payement`
  ADD CONSTRAINT `payement_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commandes` (`idCommande`),
  ADD CONSTRAINT `payement_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Constraints for table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `produits_ibfk_1` FOREIGN KEY (`idMagasin`) REFERENCES `magasins` (`idMagasin`);

--
-- Constraints for table `promotions`
--
ALTER TABLE `promotions`
  ADD CONSTRAINT `promotions_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `promotions_ibfk_2` FOREIGN KEY (`IdProduit`) REFERENCES `produits` (`idProduit`);

--
-- Constraints for table `reclamations`
--
ALTER TABLE `reclamations`
  ADD CONSTRAINT `reclamations_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);
--
-- Database: `symfony`
--
CREATE DATABASE IF NOT EXISTS `symfony` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `symfony`;

-- --------------------------------------------------------

--
-- Table structure for table `modele`
--

DROP TABLE IF EXISTS `modele`;
CREATE TABLE IF NOT EXISTS `modele` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pays` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `modele`
--

INSERT INTO `modele` (`id`, `libelle`, `pays`) VALUES
(1, 'BMW', 'Allemange'),
(2, 'Fiat', 'France'),
(3, 'Citroen', 'France');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
