-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 18 Février 2018 à 10:11
-- Version du serveur :  5.7.11
-- Version de PHP :  7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `idCommande` int(11) NOT NULL,
  `prixTotal` double NOT NULL,
  `idUser` int(11) NOT NULL,
  `etat` tinyint(11) NOT NULL DEFAULT '0',
  `dateDeCommande` date NOT NULL,
  `idTransaction` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `commandes`
--

INSERT INTO `commandes` (`idCommande`, `prixTotal`, `idUser`, `etat`, `dateDeCommande`, `idTransaction`) VALUES
(1, 15000, 3, 0, '2018-02-02', 1254354563),
(2, 65000, 4, 0, '2018-02-28', 200000000),
(3, 15500, 3, 0, '2018-02-17', 61600),
(4, 12000, 3, 0, '2018-02-17', 36185),
(5, 2000, 3, 0, '2018-02-17', 15171),
(6, 12000, 3, 0, '2018-02-17', 20321),
(7, 2000, 3, 0, '2018-02-17', 72268),
(8, 12000, 3, 0, '2018-02-17', 95212),
(9, 12000, 3, 0, '2018-02-17', 41007),
(10, 12000, 3, 0, '2018-02-17', 64400),
(11, 12000, 3, 0, '2018-02-17', 76611),
(12, 12000, 3, 0, '2018-02-17', 42506),
(13, 12000, 3, 0, '2018-02-17', 64431),
(14, 12000, 3, 0, '2018-02-17', 43120),
(15, 12000, 3, 0, '2018-02-17', 9635),
(16, 2000, 3, 0, '2018-02-17', 99386),
(17, 27, 3, 0, '2018-02-17', 45852),
(18, 110000, 3, 0, '2018-02-17', 25536),
(19, 75500, 3, 0, '2018-02-17', 97289),
(20, 48000, 3, 0, '2018-02-17', 31727),
(21, 15500, 3, 0, '2018-02-17', 28676);

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

CREATE TABLE `commentaires` (
  `idCommentaire` int(11) NOT NULL,
  `contenuCommentaire` varchar(2000) NOT NULL,
  `dateAjoutCommentaire` date NOT NULL,
  `dateModificationCommentaire` date NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `evenements`
--

CREATE TABLE `evenements` (
  `idEvenement` int(11) NOT NULL,
  `nomEvenement` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `nombreDePlaces` int(11) NOT NULL,
  `tarifEvenement` float NOT NULL,
  `descriptionEvenement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `faqs`
--

CREATE TABLE `faqs` (
  `idFaq` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `contenuFaq` varchar(800) NOT NULL,
  `dateEnvoiFaq` date NOT NULL,
  `dateReponseFaq` date NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `lignecommandes`
--

CREATE TABLE `lignecommandes` (
  `idLigneCommande` int(11) NOT NULL,
  `idCommande` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `prixTotal` double NOT NULL,
  `quantite` int(11) NOT NULL,
  `idMagasin` int(11) NOT NULL,
  `prixUnitaire` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `lignecommandes`
--

INSERT INTO `lignecommandes` (`idLigneCommande`, `idCommande`, `idProduit`, `prixTotal`, `quantite`, `idMagasin`, `prixUnitaire`) VALUES
(6, 16, 5, 2000, 1, 1, NULL),
(7, 17, 7, 27, 1, 1, NULL),
(8, 18, 5, 2000, 55, 1, NULL),
(9, 20, 3, 12000, 1, 1, 12000),
(10, 20, 5, 6000, 3, 1, 2000),
(11, 20, 4, 30000, 2, 1, 15000),
(12, 21, 4, 15000, 1, 1, 15000),
(13, 21, 6, 500, 1, 1, 500);

-- --------------------------------------------------------

--
-- Structure de la table `magasins`
--

CREATE TABLE `magasins` (
  `idMagasin` int(11) NOT NULL,
  `nomMagasin` varchar(50) NOT NULL,
  `photoMagasin` varchar(50) NOT NULL,
  `descriptionMagasin` varchar(50) NOT NULL,
  `dateCreationMagasin` date NOT NULL,
  `contactMagasin` varchar(50) NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `magasins`
--

INSERT INTO `magasins` (`idMagasin`, `nomMagasin`, `photoMagasin`, `descriptionMagasin`, `dateCreationMagasin`, `contactMagasin`, `idUser`) VALUES
(1, 'Magasin chaussures', '', 'ce magasin contient les chaussures antiques', '2018-02-21', '', 11);

-- --------------------------------------------------------

--
-- Structure de la table `participations`
--

CREATE TABLE `participations` (
  `idParticipation` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idEvenement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `payement`
--

CREATE TABLE `payement` (
  `idPayement` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idCommande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE `produits` (
  `idProduit` int(11) NOT NULL,
  `referenceProduit` int(11) NOT NULL DEFAULT '0',
  `nomProduit` varchar(50) NOT NULL,
  `prixProduit` float NOT NULL,
  `photoProduit` varchar(50) DEFAULT NULL,
  `quantiteProduit` int(11) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '0',
  `idpromotion` int(11) NOT NULL DEFAULT '0',
  `categorieMagasin` varchar(50) DEFAULT NULL,
  `idMagasin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `produits`
--

INSERT INTO `produits` (`idProduit`, `referenceProduit`, `nomProduit`, `prixProduit`, `photoProduit`, `quantiteProduit`, `active`, `idpromotion`, `categorieMagasin`, `idMagasin`) VALUES
(3, 0, 'Magasin chaussure', 12000, '', 40, 0, 0, '', 1),
(4, 0, 'reebok', 15000, '', 20, 0, 0, '', 1),
(5, 0, 'BG-star', 2000, '', 10, 0, 0, '', 1),
(6, 0, 'addidas', 500, '', 12, 0, 0, '', 1),
(7, 0, 'axe', 27, NULL, 200, 0, 0, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `promotions`
--

CREATE TABLE `promotions` (
  `idPromotion` int(11) NOT NULL,
  `nomPromotion` varchar(50) NOT NULL,
  `imagePromotion` varchar(50) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `idUser` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `reclamations`
--

CREATE TABLE `reclamations` (
  `idReclamation` int(11) NOT NULL,
  `typeReclamation` varchar(20) NOT NULL,
  `objetReclamation` varchar(100) NOT NULL,
  `contenuReclamation` varchar(500) NOT NULL,
  `dateEnvoiReclamation` date NOT NULL,
  `dateReponseReclamation` date NOT NULL,
  `idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `dateDeNaissance` date NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `zip` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `type` varchar(10) NOT NULL,
  `imageUser` varchar(1000) NOT NULL,
  `numeroDeCarteBancaire` varchar(14) DEFAULT NULL,
  `dateDeValidation` date DEFAULT NULL,
  `codeSecret` int(3) DEFAULT NULL,
  `situaitionFiscal` varchar(20) DEFAULT NULL,
  `ribBancaire` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `dateDeNaissance`, `adresse`, `ville`, `zip`, `email`, `password`, `type`, `imageUser`, `numeroDeCarteBancaire`, `dateDeValidation`, `codeSecret`, `situaitionFiscal`, `ribBancaire`) VALUES
(3, 'boumaiza', 'oussama', '1993-04-03', 'rue du grand maghreb', 'nabeul', 8011, 'boumaizaoussama@yahoo.fr', 'azerty', 'client', '', '54158796325874', '2019-04-03', 158, '', ''),
(4, 'Jeanlemignon', 'Bikie', '1970-01-01', 'rue du grand maghreb', 'nabeul', 8011, 'boumaizaoussamab@gmail.com', 'General', 'client', '', '54788969852014', '1970-01-01', 125, NULL, NULL),
(6, 'Boumaiza', 'Oussama', '3893-05-03', 'rue du grand maghreb', 'nabeul', 8011, '\0boumaizaoussamabb@gmail.com', 'null', 'client', '', '54788969852014', '1970-01-01', 125, NULL, NULL),
(7, 'Boumaiza', 'Oussama', '3893-05-03', 'rue du grand maghreb', 'nabeul', 8011, '\0boumaizaoussama@gmail.com', 'starac', 'client', '', '54788969852014', '1970-01-01', 125, NULL, NULL),
(11, 'salwa', 'laibi', '1970-01-01', 'rue du grand maghreb', 'nabeul', 8011, 'laribisalwa@yahoo.fr', 'salwa', 'vendeur', '', NULL, NULL, NULL, 'tres bien', '152465547852314');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`idCommande`),
  ADD KEY `fk_commande_user` (`idUser`);

--
-- Index pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD PRIMARY KEY (`idCommentaire`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`idEvenement`);

--
-- Index pour la table `faqs`
--
ALTER TABLE `faqs`
  ADD PRIMARY KEY (`idFaq`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `lignecommandes`
--
ALTER TABLE `lignecommandes`
  ADD PRIMARY KEY (`idLigneCommande`),
  ADD KEY `idCommande` (`idCommande`),
  ADD KEY `idMagasin` (`idMagasin`),
  ADD KEY `idProduit` (`idProduit`);

--
-- Index pour la table `magasins`
--
ALTER TABLE `magasins`
  ADD PRIMARY KEY (`idMagasin`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `participations`
--
ALTER TABLE `participations`
  ADD PRIMARY KEY (`idParticipation`,`idUser`,`idEvenement`) USING BTREE,
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idEvenement` (`idEvenement`);

--
-- Index pour la table `payement`
--
ALTER TABLE `payement`
  ADD PRIMARY KEY (`idPayement`),
  ADD KEY `idCommande` (`idCommande`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`idProduit`),
  ADD KEY `idMagasin` (`idMagasin`);

--
-- Index pour la table `promotions`
--
ALTER TABLE `promotions`
  ADD PRIMARY KEY (`idPromotion`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `IdProduit` (`IdProduit`);

--
-- Index pour la table `reclamations`
--
ALTER TABLE `reclamations`
  ADD PRIMARY KEY (`idReclamation`),
  ADD KEY `idUser` (`idUser`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `commandes`
--
ALTER TABLE `commandes`
  MODIFY `idCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT pour la table `commentaires`
--
ALTER TABLE `commentaires`
  MODIFY `idCommentaire` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `idEvenement` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `faqs`
--
ALTER TABLE `faqs`
  MODIFY `idFaq` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `lignecommandes`
--
ALTER TABLE `lignecommandes`
  MODIFY `idLigneCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `magasins`
--
ALTER TABLE `magasins`
  MODIFY `idMagasin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `participations`
--
ALTER TABLE `participations`
  MODIFY `idParticipation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `payement`
--
ALTER TABLE `payement`
  MODIFY `idPayement` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `idProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `promotions`
--
ALTER TABLE `promotions`
  MODIFY `idPromotion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `reclamations`
--
ALTER TABLE `reclamations`
  MODIFY `idReclamation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `fk_commande_user` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `commentaires_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `faqs`
--
ALTER TABLE `faqs`
  ADD CONSTRAINT `faqs_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `lignecommandes`
--
ALTER TABLE `lignecommandes`
  ADD CONSTRAINT `lignecommandes_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commandes` (`idCommande`),
  ADD CONSTRAINT `lignecommandes_ibfk_2` FOREIGN KEY (`idMagasin`) REFERENCES `magasins` (`idMagasin`),
  ADD CONSTRAINT `lignecommandes_ibfk_3` FOREIGN KEY (`idProduit`) REFERENCES `produits` (`idProduit`);

--
-- Contraintes pour la table `magasins`
--
ALTER TABLE `magasins`
  ADD CONSTRAINT `magasins_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `participations`
--
ALTER TABLE `participations`
  ADD CONSTRAINT `participations_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `participations_ibfk_2` FOREIGN KEY (`idEvenement`) REFERENCES `evenements` (`idEvenement`);

--
-- Contraintes pour la table `payement`
--
ALTER TABLE `payement`
  ADD CONSTRAINT `payement_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commandes` (`idCommande`),
  ADD CONSTRAINT `payement_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `produits_ibfk_1` FOREIGN KEY (`idMagasin`) REFERENCES `magasins` (`idMagasin`);

--
-- Contraintes pour la table `promotions`
--
ALTER TABLE `promotions`
  ADD CONSTRAINT `promotions_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `promotions_ibfk_2` FOREIGN KEY (`IdProduit`) REFERENCES `produits` (`idProduit`);

--
-- Contraintes pour la table `reclamations`
--
ALTER TABLE `reclamations`
  ADD CONSTRAINT `reclamations_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
