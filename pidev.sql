-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 26 Février 2018 à 00:33
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
  `etat` int(11) NOT NULL DEFAULT '0',
  `DateDeCommande` date NOT NULL,
  `idTransaction` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

CREATE TABLE `commentaires` (
  `idCommentaire` int(11) NOT NULL,
  `contenuCommentaire` varchar(2000) NOT NULL,
  `dateAjoutCommentaire` date NOT NULL,
  `dateModificationCommentaire` date NOT NULL,
  `idUser` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL
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
  `dateReponseFaq` date NOT NULL
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
  `prixUnitaire` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1, 'Magasin Chaussure', '', 'magasin des chaussures situé à jaafar', '2018-02-21', '', 18),
(2, 'Magasin habit', '', 'habit pret à porter', '2018-02-15', '', 18);

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
  `prixProduit` double NOT NULL,
  `photoProduit` varchar(50) NOT NULL,
  `quantiteProduit` int(11) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '0',
  `idpromotion` int(11) NOT NULL DEFAULT '0',
  `categorieMagasin` varchar(50) NOT NULL,
  `idMagasin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `produits`
--

INSERT INTO `produits` (`idProduit`, `referenceProduit`, `nomProduit`, `prixProduit`, `photoProduit`, `quantiteProduit`, `active`, `idpromotion`, `categorieMagasin`, `idMagasin`) VALUES
(1, 0, 'Reebok', 1500, '', 45, 0, 0, '', 1),
(2, 0, 'Nike', 3500, '', 55, 0, 0, '', 1),
(3, 0, 'Addidas', 2000, '', 45, 0, 0, '', 1),
(4, 0, 'Dior', 1500, '', 20, 0, 0, '', 2),
(5, 0, 'versace', 2000, '', 50, 0, 0, '', 2);

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
  `typeReclamation` varchar(70) NOT NULL,
  `objetReclamation` varchar(100) NOT NULL,
  `contenuReclamation` varchar(500) NOT NULL,
  `dateEnvoiReclamation` date NOT NULL,
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
  `dateDeNaissance` varchar(10) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `zip` int(11) NOT NULL,
  `numeroDuTelephone` varchar(13) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `type` varchar(10) NOT NULL,
  `imageUser` varchar(1000) DEFAULT NULL,
  `numeroDeCarteBancaire` varchar(16) DEFAULT NULL,
  `dateDeValidation` varchar(10) DEFAULT NULL,
  `codeSecret` varchar(300) DEFAULT NULL,
  `situaitionFiscal` varchar(20) DEFAULT NULL,
  `ribBancaire` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `dateDeNaissance`, `sexe`, `adresse`, `ville`, `zip`, `numeroDuTelephone`, `email`, `password`, `type`, `imageUser`, `numeroDeCarteBancaire`, `dateDeValidation`, `codeSecret`, `situaitionFiscal`, `ribBancaire`) VALUES
(14, 'salwa', 'laribi', '1964-01-12', 'Femme', 'rue du grand maghreb', 'nabeul', 8011, '50664665', 'salwalaribi2@yahoo.fr', '123', 'admin', NULL, NULL, NULL, NULL, NULL, NULL),
(18, 'boumaiza', 'oussama', '1993-04-04', 'Homme', 'rue du grand maghreb', 'nabeul', 8011, '20950389', 'b@soukmedina.tn', '6853af2923e0204c3ca7c6a315cf7', 'vendeur', NULL, NULL, NULL, NULL, 'Moyenne', '1234567890azerty11'),
(19, 'fadhlawi', 'ahmed', '1993-09-09', 'Homme', 'rue', 'tunis', 1000, '45859632', 'fa@soukmedina.tn', 'c6f057b86584942e415435ffb1fa93d4', 'vendeur', NULL, NULL, NULL, NULL, 'Moyenne', '3214569870iuytreza');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`idCommande`),
  ADD KEY `commandes_ibfk_1` (`idUser`);

--
-- Index pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD PRIMARY KEY (`idCommentaire`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `commentaires_ibfk_2` (`idProduit`);

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`idEvenement`);

--
-- Index pour la table `faqs`
--
ALTER TABLE `faqs`
  ADD PRIMARY KEY (`idFaq`);

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
  MODIFY `idCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
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
  MODIFY `idLigneCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `magasins`
--
ALTER TABLE `magasins`
  MODIFY `idMagasin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
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
  MODIFY `idProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `commandes_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `commentaires_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `commentaires_ibfk_2` FOREIGN KEY (`idProduit`) REFERENCES `produits` (`idProduit`) ON DELETE CASCADE;

--
-- Contraintes pour la table `lignecommandes`
--
ALTER TABLE `lignecommandes`
  ADD CONSTRAINT `lignecommandes_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commandes` (`idCommande`) ON DELETE CASCADE,
  ADD CONSTRAINT `lignecommandes_ibfk_2` FOREIGN KEY (`idMagasin`) REFERENCES `magasins` (`idMagasin`) ON DELETE CASCADE,
  ADD CONSTRAINT `lignecommandes_ibfk_3` FOREIGN KEY (`idProduit`) REFERENCES `produits` (`idProduit`) ON DELETE CASCADE;

--
-- Contraintes pour la table `magasins`
--
ALTER TABLE `magasins`
  ADD CONSTRAINT `magasins_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `participations`
--
ALTER TABLE `participations`
  ADD CONSTRAINT `participations_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `participations_ibfk_2` FOREIGN KEY (`idEvenement`) REFERENCES `evenements` (`idEvenement`) ON DELETE CASCADE;

--
-- Contraintes pour la table `payement`
--
ALTER TABLE `payement`
  ADD CONSTRAINT `payement_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commandes` (`idCommande`) ON DELETE CASCADE,
  ADD CONSTRAINT `payement_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `produits_ibfk_1` FOREIGN KEY (`idMagasin`) REFERENCES `magasins` (`idMagasin`) ON DELETE CASCADE;

--
-- Contraintes pour la table `promotions`
--
ALTER TABLE `promotions`
  ADD CONSTRAINT `promotions_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `promotions_ibfk_2` FOREIGN KEY (`IdProduit`) REFERENCES `produits` (`idProduit`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reclamations`
--
ALTER TABLE `reclamations`
  ADD CONSTRAINT `reclamations_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
