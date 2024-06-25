-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 25 juin 2024 à 12:36
-- Version du serveur : 8.0.31
-- Version de PHP : 8.2.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ticket`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `apprenant`
--

DROP TABLE IF EXISTS `apprenant`;
CREATE TABLE IF NOT EXISTS `apprenant` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `base_connaissance`
--

DROP TABLE IF EXISTS `base_connaissance`;
CREATE TABLE IF NOT EXISTS `base_connaissance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_update` datetime(6) DEFAULT NULL,
  `lien` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `piece_jointe` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `question` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `reponse` tinytext COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `libelle` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `description`, `libelle`) VALUES
(1, NULL, 'Technique'),
(2, NULL, 'Théorique'),
(3, NULL, 'Pratique');

-- --------------------------------------------------------

--
-- Structure de la table `etat`
--

DROP TABLE IF EXISTS `etat`;
CREATE TABLE IF NOT EXISTS `etat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `libelle` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etat`
--

INSERT INTO `etat` (`id`, `description`, `libelle`) VALUES
(1, NULL, 'Ouvert'),
(2, NULL, 'en cours'),
(3, NULL, 'resolu');

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

DROP TABLE IF EXISTS `formateur`;
CREATE TABLE IF NOT EXISTS `formateur` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `priorite`
--

DROP TABLE IF EXISTS `priorite`;
CREATE TABLE IF NOT EXISTS `priorite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `libelle` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `priorite`
--

INSERT INTO `priorite` (`id`, `description`, `libelle`) VALUES
(1, NULL, 'Haute'),
(2, NULL, 'Moyenne'),
(3, NULL, 'Basse');

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `auteur_id` bigint DEFAULT NULL,
  `ticket_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa7qgb8ifj7esqqkm7i21gf3qt` (`auteur_id`),
  KEY `FKjyirw9foyfoktillhnecbiidn` (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id`, `contenu`, `date_creation`, `auteur_id`, `ticket_id`) VALUES
(1, '{\r\n    \"reponseFormateur\":\"Essaye de redemarer le PC !!!\"\r\n}', '2024-06-24 12:10:40.000000', 7, 1),
(2, '{\r\n    \"reponseFormateur\":\"Essaye de redemarer le PC !!!\"\r\n}', '2024-06-24 12:10:40.000000', 7, 1),
(3, 'Essaye de redemarer le PC !!!', '2024-06-24 12:16:12.000000', 7, 1),
(4, 'Essaye de redemarer le PC !!!', '2024-06-24 12:16:12.000000', 7, 1),
(5, 'Essaye de redemarer le PC !!!', '2024-06-24 16:11:39.000000', 7, 1),
(6, 'Essaye de redemarer le PC !!!', '2024-06-24 16:11:39.000000', 7, 1),
(7, 'Apporte le dans la salle de maintenance !!!', '2024-06-24 16:15:42.000000', 7, 2),
(8, 'Apporte le dans la salle de maintenance !!!', '2024-06-24 16:15:42.000000', 7, 2),
(9, 'IMPOSSIBLE.....', '2024-06-24 23:48:57.290200', 8, 2),
(10, 'IMPOSSIBLE.....', '2024-06-24 23:48:57.290200', 8, 2),
(11, 'IMPOSSIBLE.....', '2024-06-25 00:09:10.819335', 8, 3),
(12, 'IMPOSSIBLE.....', '2024-06-25 00:09:10.819335', 8, 3),
(13, 'AUCUNE IDEE Monsieur va réviser..', '2024-06-25 08:33:13.255046', 7, 4),
(14, 'AUCUNE IDEE Monsieur va réviser..', '2024-06-25 08:33:13.255046', 7, 4),
(15, 'AUCUNE IDEE  ..', '2024-06-25 12:23:58.026737', 3, 7),
(16, 'BBBBRHERTUH5RUJ', '2024-06-25 12:34:19.882294', 3, 8);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `libelle`) VALUES
(1, 'Apprenant'),
(2, 'Formateur'),
(3, 'Admin'),
(4, 'oups'),
(5, 'erreur');

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_creation` datetime(6) NOT NULL,
  `date_resolution` datetime(6) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `apprenant_id` bigint DEFAULT NULL,
  `categorie_id` bigint DEFAULT NULL,
  `etat_id` bigint DEFAULT NULL,
  `formateur_id` bigint DEFAULT NULL,
  `priorite_id` bigint DEFAULT NULL,
  `reponse_formateur_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5rf0uqtn7xykjwek3fa0k1kl1` (`apprenant_id`),
  KEY `FKtbdefmamq7yt6upk0e3odvioa` (`categorie_id`),
  KEY `FK20jkfcs0ntha9831ancysgsmh` (`etat_id`),
  KEY `FKksg09j87614naih0xwa78hre` (`formateur_id`),
  KEY `FK4es3wncbq9bmlhdaqeuv1v67p` (`priorite_id`),
  KEY `FKkwlpogf5128exg3l7v5i3nqpg` (`reponse_formateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`id`, `date_creation`, `date_resolution`, `description`, `apprenant_id`, `categorie_id`, `etat_id`, `formateur_id`, `priorite_id`, `reponse_formateur_id`) VALUES
(1, '2024-06-24 11:45:58.000000', '2024-06-24 16:11:39.000000', 'Mon Server Xamp demarre pas !!!', 1, 1, 3, 7, 1, 5),
(2, '2024-06-24 16:13:51.000000', '2024-06-24 23:48:57.290200', 'Mon PC DEMARRE PASS !!!', 2, 1, 3, 8, 1, 9),
(3, '2024-06-25 00:07:19.078023', '2024-06-25 00:09:10.819335', 'essai last', 1, 3, 3, 8, 1, 11),
(4, '2024-06-25 08:23:26.646491', '2024-06-25 08:33:13.255046', 'comment déclarer une variable de type liste en Scala', 11, 2, 3, 7, 3, 13),
(5, '2024-06-25 09:34:57.841476', NULL, 'comment déclarer une variable', 11, 2, 1, NULL, 3, NULL),
(6, '2024-06-25 09:35:29.377483', NULL, 'comment déclarer une variable', 11, 2, 1, NULL, 3, NULL),
(7, '2024-06-25 11:36:00.628274', '2024-06-25 12:23:58.571780', 'j\'abandonne', 11, 2, 3, 3, 3, 15),
(8, '2024-06-25 12:31:25.869885', '2024-06-25 12:34:19.928295', 'j\'abandonne b', 11, 2, 3, 3, 3, 16);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `motdepasse` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKrma38wvnqfaf66vvmi57c71lo` (`email`),
  KEY `FKaqe8xtajee4k0wlqrvh2pso4l` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `motdepasse`, `nom`, `prenom`, `role_id`) VALUES
(1, 'Mana@gmail.com', '$2a$10$GTMBfWwyrIzfe/8gZ3bMpOB1jlueD3EJhirhXP9Yzv0RjbjHXeVlG', 'Coulibaly', 'Mana', 1),
(2, 'Abdou@gmail.com', '$2a$10$36It1d9Fc4RPoO/BXf5HQeL5mb8uwwvBulhnlkF16AMQM3I75Niou', 'Sidibé', 'Abdou', 1),
(3, 'formateurdiarra@gmail.com', '$2a$10$RkJ/J96lbb0O3H3hvELQHe3W.iUl1HgeDiKdE13TKFyY1KeeNzBo6', 'Monsieur', 'Diarra', 2),
(4, 'admin1@gmail.com', '$2a$10$p6ULxG3WiUIgtOy3YyeeuOfJWy9684Vwmu6re3y7Sqpqraj0qvIci', 'Admin', 'first', 3),
(5, 'admin2@gmail.com', '$2a$10$HuEm8ZNwThuqdzTcu74wC.tUguJbUaSxAI8MFAD4Fa.XphxM6Pu2G', 'Admin', 'Two', 3),
(6, 'admin3@gmail.com', '$2a$10$qLLrrjQNYByP4ga0ec4uT.SDSDeFRvYnwSe.ovJqMfhrbIAEa5bGy', 'Admin', 'Three', 3),
(7, 'formatricekonate@gmail.com', '$2a$10$ql.oijgqooXNMaH4AMGdfuFYGEfyv8LJMfJfTCHkGUVaFOIAhb9yq', 'Formatrice', 'Konate', 2),
(8, 'formatriceSissoko@gmail.com', '$2a$10$Q6b6wX.ChYXNoxi8iRuw.uo9qdosMv/vhJ4FDsjCTFxqbJ1HnRcF.', 'Formatrice', 'Sissoko', 2),
(10, 'fakoro@gmail.com', '$2a$10$ThtDapSM32LkudoqCFGBjesniCwEfcHKb4weOklpQX//mTG.LpIf.', 'Fakoro', 'Traore', 1),
(11, 'Mamoutou@gmail.com', '$2a$10$9feRP.hqxIvHdWGrUEcip.SN7VWq2UPgXOM4Ncg3OFgjslonOUCqS', 'Yalcouyé', 'Mamoutou', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FKgodqjbbtwk30kf3s0xuxklkr3` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `apprenant`
--
ALTER TABLE `apprenant`
  ADD CONSTRAINT `FKggx3woettppxw6vddt0t983lt` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD CONSTRAINT `FKl2dovj3y7isqbwa0lrpc04m60` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `FKa7qgb8ifj7esqqkm7i21gf3qt` FOREIGN KEY (`auteur_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKjyirw9foyfoktillhnecbiidn` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`);

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK20jkfcs0ntha9831ancysgsmh` FOREIGN KEY (`etat_id`) REFERENCES `etat` (`id`),
  ADD CONSTRAINT `FK4es3wncbq9bmlhdaqeuv1v67p` FOREIGN KEY (`priorite_id`) REFERENCES `priorite` (`id`),
  ADD CONSTRAINT `FK5rf0uqtn7xykjwek3fa0k1kl1` FOREIGN KEY (`apprenant_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKksg09j87614naih0xwa78hre` FOREIGN KEY (`formateur_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKkwlpogf5128exg3l7v5i3nqpg` FOREIGN KEY (`reponse_formateur_id`) REFERENCES `reponse` (`id`),
  ADD CONSTRAINT `FKtbdefmamq7yt6upk0e3odvioa` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKaqe8xtajee4k0wlqrvh2pso4l` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
