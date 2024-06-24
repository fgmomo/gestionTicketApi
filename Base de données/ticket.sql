-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 24 juin 2024 à 18:18
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

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

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `apprenant`
--

CREATE TABLE `apprenant` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `base_connaissance`
--

CREATE TABLE `base_connaissance` (
  `id` int(11) NOT NULL,
  `date_update` datetime(6) DEFAULT NULL,
  `lien` varchar(255) DEFAULT NULL,
  `piece_jointe` varchar(255) DEFAULT NULL,
  `question` varchar(255) NOT NULL,
  `reponse` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

CREATE TABLE `etat` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

CREATE TABLE `formateur` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `priorite`
--

CREATE TABLE `priorite` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

CREATE TABLE `reponse` (
  `id` bigint(20) NOT NULL,
  `contenu` varchar(255) NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `auteur_id` bigint(20) DEFAULT NULL,
  `ticket_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(8, 'Apporte le dans la salle de maintenance !!!', '2024-06-24 16:15:42.000000', 7, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `date_resolution` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `apprenant_id` bigint(20) DEFAULT NULL,
  `categorie_id` bigint(20) DEFAULT NULL,
  `etat_id` bigint(20) DEFAULT NULL,
  `formateur_id` bigint(20) DEFAULT NULL,
  `priorite_id` bigint(20) DEFAULT NULL,
  `reponse_formateur_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`id`, `date_creation`, `date_resolution`, `description`, `apprenant_id`, `categorie_id`, `etat_id`, `formateur_id`, `priorite_id`, `reponse_formateur_id`) VALUES
(1, '2024-06-24 11:45:58.000000', '2024-06-24 16:11:39.000000', 'Mon Server Xamp demarre pas !!!', 1, 1, 3, 7, 1, 5),
(2, '2024-06-24 16:13:51.000000', '2024-06-24 16:15:42.000000', 'Mon PC DEMARRE PASS !!!', 2, 1, 3, 8, 1, 7);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `motdepasse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(10, 'fakoro@gmail.com', '$2a$10$ThtDapSM32LkudoqCFGBjesniCwEfcHKb4weOklpQX//mTG.LpIf.', 'Fakoro', 'Traore', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `apprenant`
--
ALTER TABLE `apprenant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `base_connaissance`
--
ALTER TABLE `base_connaissance`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etat`
--
ALTER TABLE `etat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `priorite`
--
ALTER TABLE `priorite`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa7qgb8ifj7esqqkm7i21gf3qt` (`auteur_id`),
  ADD KEY `FKjyirw9foyfoktillhnecbiidn` (`ticket_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5rf0uqtn7xykjwek3fa0k1kl1` (`apprenant_id`),
  ADD KEY `FKtbdefmamq7yt6upk0e3odvioa` (`categorie_id`),
  ADD KEY `FK20jkfcs0ntha9831ancysgsmh` (`etat_id`),
  ADD KEY `FKksg09j87614naih0xwa78hre` (`formateur_id`),
  ADD KEY `FK4es3wncbq9bmlhdaqeuv1v67p` (`priorite_id`),
  ADD KEY `FKkwlpogf5128exg3l7v5i3nqpg` (`reponse_formateur_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKrma38wvnqfaf66vvmi57c71lo` (`email`),
  ADD KEY `FKaqe8xtajee4k0wlqrvh2pso4l` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `base_connaissance`
--
ALTER TABLE `base_connaissance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `etat`
--
ALTER TABLE `etat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `priorite`
--
ALTER TABLE `priorite`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
