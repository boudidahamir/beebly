-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 10 mai 2023 à 22:09
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `web`
--

-- --------------------------------------------------------

--
-- Structure de la table `cloture_achat`
--

CREATE TABLE `cloture_achat` (
  `id_cloture` int(11) NOT NULL,
  `id_details_livraison` int(11) DEFAULT NULL,
  `pointAchatFinale` int(11) DEFAULT NULL,
  `etatCloture` varchar(45) DEFAULT 'NULL',
  `etatLivre` varchar(45) DEFAULT 'NULL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cloture_achat`
--

INSERT INTO `cloture_achat` (`id_cloture`, `id_details_livraison`, `pointAchatFinale`, `etatCloture`, `etatLivre`) VALUES
(4, 601, 800, 'Cloturé', 'en bon etat');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `idcom` int(11) NOT NULL,
  `contenu` text NOT NULL,
  `date` date NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idsujet` int(11) DEFAULT NULL,
  `nblike` int(11) NOT NULL,
  `nbdislike` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`idcom`, `contenu`, `date`, `iduser`, `idsujet`, `nblike`, `nbdislike`) VALUES
(4, 'Pdsfdsqg', '2024-01-01', 47, 6, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `detailslivraison`
--

CREATE TABLE `detailslivraison` (
  `idDetailsLivraison` int(11) NOT NULL,
  `etatLivrasion` varchar(45) DEFAULT 'NULL',
  `idEstimationOffreLivre` int(11) DEFAULT NULL,
  `AdresseLivraison` varchar(45) DEFAULT 'NULL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `detailslivraison`
--

INSERT INTO `detailslivraison` (`idDetailsLivraison`, `etatLivrasion`, `idEstimationOffreLivre`, `AdresseLivraison`) VALUES
(601, 'EnAttente', 11, 'ghazela');

-- --------------------------------------------------------

--
-- Structure de la table `dislikee`
--

CREATE TABLE `dislikee` (
  `id_dislike` int(11) NOT NULL,
  `id_commentaire` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20230411104958', '2023-04-11 12:50:16', 67),
('DoctrineMigrations\\Version20230411105453', '2023-04-11 12:55:10', 13),
('DoctrineMigrations\\Version20230411105535', '2023-04-11 12:55:42', 15);

-- --------------------------------------------------------

--
-- Structure de la table `estimationoffrelivre`
--

CREATE TABLE `estimationoffrelivre` (
  `idEstimationOffreLivre` int(11) NOT NULL,
  `idProposition` int(11) DEFAULT NULL,
  `pointEstime` int(11) DEFAULT NULL,
  `etat` varchar(45) DEFAULT 'NULL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `estimationoffrelivre`
--

INSERT INTO `estimationoffrelivre` (`idEstimationOffreLivre`, `idProposition`, `pointEstime`, `etat`) VALUES
(11, 11, 700, 'Acceptée');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `emplacement` varchar(255) NOT NULL,
  `nb_place` int(11) NOT NULL,
  `duree` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `libelle`, `image`, `date`, `description`, `emplacement`, `nb_place`, `duree`, `id_user`) VALUES
(2, 'Journee', '', '23/02/2023', 'Book store', 'Tunis', 60, 1, 22),
(3, 'Journee book', '', '23/02/2023', 'Auteur sign book', 'Ghazela', 100, 3, 22),
(6, 'Validation', '', '23/02/2023', 'Pidev', 'Tunis', 10, 10, 22),
(7, 'Aaaaaaaaaaaa', '', '23/02/2023', 'Aaaaaaaaaaaa', 'Tunis', 10, 13, 44);

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `livre_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `likee`
--

CREATE TABLE `likee` (
  `id_like` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_commentaire` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `likee`
--

INSERT INTO `likee` (`id_like`, `id_user`, `id_commentaire`) VALUES
(5, 47, 4);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `categorie` varchar(255) DEFAULT NULL,
  `date_publication` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `description_etat_livre` varchar(255) DEFAULT NULL,
  `note` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `user_id`, `titre`, `categorie`, `date_publication`, `image`, `prix`, `description_etat_livre`, `note`) VALUES
(7, 22, 'sdqf', 'fdsqfqsd', '2023-05-19 00:00:00', '9782011554918-T-6443c8c007ae9-645835ba8ad53.jpg', 1234, 'fdghdr', 1232),
(8, 22, 'abc', 'qsdf', '2023-05-09 00:00:00', '64582f6ea08e3-645a457e53ac6.jpg', 1234, 'qsdf', 122232),
(10, NULL, 'qsdf', 'fdsqf', '2023-05-09 01:06:42', 'null', 1234, 'qsdfqsd', NULL),
(11, NULL, 'qsdf', 'fdsqf', '2023-05-09 01:07:04', 'null', 1234, 'qsdfqsd', NULL),
(12, NULL, 'fsdq', 'qsdf', '2023-05-09 01:40:49', 'null', 123, 'qsdf', NULL),
(13, NULL, 'fsdq', 'qsdf', '2023-05-09 01:42:10', 'null', 123, 'qsdf', NULL),
(15, NULL, 'sahar', 'roman', '2023-05-09 15:17:04', 'null', 55, 'aaa', NULL),
(16, NULL, 'sahar', 'roman', '2023-05-09 15:17:24', 'null', 55, 'aaa', NULL),
(17, NULL, 'sahar', 'roman', '2023-05-09 15:19:24', 'null', 55, 'aaa', NULL),
(18, NULL, NULL, NULL, '2023-05-10 22:07:06', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext NOT NULL,
  `headers` longtext NOT NULL,
  `queue_name` varchar(190) NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `propositionlivre`
--

CREATE TABLE `propositionlivre` (
  `idPropositionLivre` int(11) NOT NULL,
  `idclient` int(11) DEFAULT NULL,
  `titreLivre` varchar(45) DEFAULT 'NULL',
  `editon` varchar(45) NOT NULL,
  `dateProposition` date DEFAULT NULL,
  `descriptionEtat` varchar(45) DEFAULT 'NULL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `propositionlivre`
--

INSERT INTO `propositionlivre` (`idPropositionLivre`, `idclient`, `titreLivre`, `editon`, `dateProposition`, `descriptionEtat`) VALUES
(9, 22, 'utfcvhc', 'htfych', '2023-05-09', 'hftyhcyh'),
(10, 22, 'fqdsf', 'qsdfqsd', '2023-05-09', 'fqsdf'),
(11, 22, 'validtiob test 444', 'X', '2023-05-09', 'bon etat');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `type` varchar(30) NOT NULL,
  `sujet` varchar(100) NOT NULL,
  `description` varchar(8000) NOT NULL,
  `date` date DEFAULT NULL,
  `photo` longblob DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_reponse` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `type`, `sujet`, `description`, `date`, `photo`, `status`, `id_user`, `id_reponse`) VALUES
(3, 'qds', 'sqdf', 'qsdf', '2023-05-09', '', 'sqd', 44, NULL),
(4, 'Livre', 'Pidev', 'Validation', '2023-05-09', '', 'Traité', 47, 6);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE `reponse` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `contenu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reponse`
--

INSERT INTO `reponse` (`id`, `date`, `contenu`) VALUES
(1, '2022-01-01', 'Okokok'),
(2, '2022-01-01', 'Okokok'),
(3, '2023-05-02', 'Sorry'),
(4, '2023-05-02', 'Sorry'),
(5, '2018-01-01', 'AEREF'),
(6, '2023-05-01', 'Reponse validation');

-- --------------------------------------------------------

--
-- Structure de la table `sujet`
--

CREATE TABLE `sujet` (
  `idsujet` int(11) NOT NULL,
  `titresujet` varchar(255) NOT NULL,
  `contenu` text NOT NULL,
  `date` date NOT NULL,
  `accepter` int(11) NOT NULL,
  `nbcom` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idtopic` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `sujet`
--

INSERT INTO `sujet` (`idsujet`, `titresujet`, `contenu`, `date`, `accepter`, `nbcom`, `iduser`, `idtopic`) VALUES
(6, 'Reponse a pidev', 'Pidev', '2024-01-01', 0, 0, 47, 4);

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `idEvenement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`id`, `prix`, `type`, `idEvenement`) VALUES
(2, 10, 'Standard', 2);

-- --------------------------------------------------------

--
-- Structure de la table `topic`
--

CREATE TABLE `topic` (
  `idtopic` int(11) NOT NULL,
  `titretopic` varchar(255) DEFAULT 'NULL',
  `description` varchar(255) DEFAULT 'NULL',
  `date` date DEFAULT NULL,
  `accepter` tinyint(1) NOT NULL,
  `nbsujet` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `hide` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `topic`
--

INSERT INTO `topic` (`idtopic`, `titretopic`, `description`, `date`, `accepter`, `nbsujet`, `iduser`, `hide`) VALUES
(2, 'FF2212134', 'TERZTEZT', '2027-01-01', 1, 0, 22, 0),
(3, 'The little prince', 'Book description', '2023-11-01', 0, 0, 22, 0),
(4, 'Pidev', 'ValidationFinal', '2023-05-09', 1, 0, 22, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `adrmail` varchar(25) NOT NULL,
  `mdp` varchar(25) NOT NULL,
  `adresse` varchar(25) NOT NULL,
  `tel` varchar(25) NOT NULL,
  `type` varchar(6) NOT NULL,
  `cin` int(11) NOT NULL,
  `soldepoint` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `adrmail`, `mdp`, `adresse`, `tel`, `type`, `cin`, `soldepoint`) VALUES
(22, 'boudidah', 'amirrrr', 'amirboudidah@gmail.com', '1234567', 'ezzahra', '12345678', 'admin', 12345678, 0),
(40, 'amir', 'boudidah', 'amir.boudidah@esprit.tn', '123', '12345', '12345678', 'client', 12345978, 0),
(41, 'amir', 'boudidah', 'aziz.kchouk@esprit.tn', '12345', 'aaaxxx', '12345578', 'client', 12345678, 0),
(44, 'amir', 'boudidah', 'am@gmail.com', '12345', 'dsfgsdfg', '12345678', 'client', 12345678, 0),
(47, 'validation', 'Pidev', 'sahar.ourak@esprit.tn', 'pidevpidev', 'Rades', '25819166', 'client', 12345678, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cloture_achat`
--
ALTER TABLE `cloture_achat`
  ADD PRIMARY KEY (`id_cloture`),
  ADD KEY `foreign_idx` (`id_details_livraison`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`idcom`),
  ADD KEY `idccom` (`iduser`),
  ADD KEY `idsujcom` (`idsujet`);

--
-- Index pour la table `detailslivraison`
--
ALTER TABLE `detailslivraison`
  ADD PRIMARY KEY (`idDetailsLivraison`),
  ADD KEY `fore_idx` (`idEstimationOffreLivre`);

--
-- Index pour la table `dislikee`
--
ALTER TABLE `dislikee`
  ADD PRIMARY KEY (`id_dislike`),
  ADD KEY `idcdis` (`id_user`),
  ADD KEY `idcomm` (`id_commentaire`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `estimationoffrelivre`
--
ALTER TABLE `estimationoffrelivre`
  ADD PRIMARY KEY (`idEstimationOffreLivre`),
  ADD KEY `for_idx` (`idProposition`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idcevent` (`id_user`);

--
-- Index pour la table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_1F1B251E37D925CB` (`livre_id`);

--
-- Index pour la table `likee`
--
ALTER TABLE `likee`
  ADD PRIMARY KEY (`id_like`),
  ADD KEY `idclike` (`id_user`),
  ADD KEY `idcomlike` (`id_commentaire`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_AC634F9967B3B43D` (`user_id`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `propositionlivre`
--
ALTER TABLE `propositionlivre`
  ADD PRIMARY KEY (`idPropositionLivre`) USING BTREE,
  ADD KEY `idcproplivre` (`idclient`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idcrec` (`id_user`),
  ADD KEY `idreprec` (`id_reponse`);

--
-- Index pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `sujet`
--
ALTER TABLE `sujet`
  ADD PRIMARY KEY (`idsujet`),
  ADD KEY `idcsujet` (`iduser`),
  ADD KEY `idsujtopic` (`idtopic`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_evenement` (`idEvenement`);

--
-- Index pour la table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`idtopic`),
  ADD KEY `idctopic` (`iduser`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cloture_achat`
--
ALTER TABLE `cloture_achat`
  MODIFY `id_cloture` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `idcom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `detailslivraison`
--
ALTER TABLE `detailslivraison`
  MODIFY `idDetailsLivraison` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=602;

--
-- AUTO_INCREMENT pour la table `dislikee`
--
ALTER TABLE `dislikee`
  MODIFY `id_dislike` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `estimationoffrelivre`
--
ALTER TABLE `estimationoffrelivre`
  MODIFY `idEstimationOffreLivre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `likee`
--
ALTER TABLE `likee`
  MODIFY `id_like` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `propositionlivre`
--
ALTER TABLE `propositionlivre`
  MODIFY `idPropositionLivre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `reponse`
--
ALTER TABLE `reponse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `sujet`
--
ALTER TABLE `sujet`
  MODIFY `idsujet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `topic`
--
ALTER TABLE `topic`
  MODIFY `idtopic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cloture_achat`
--
ALTER TABLE `cloture_achat`
  ADD CONSTRAINT `foreign` FOREIGN KEY (`id_details_livraison`) REFERENCES `detailslivraison` (`idDetailsLivraison`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `idccom` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idsujcom` FOREIGN KEY (`idsujet`) REFERENCES `sujet` (`idsujet`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `detailslivraison`
--
ALTER TABLE `detailslivraison`
  ADD CONSTRAINT `fore` FOREIGN KEY (`idEstimationOffreLivre`) REFERENCES `estimationoffrelivre` (`idEstimationOffreLivre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `dislikee`
--
ALTER TABLE `dislikee`
  ADD CONSTRAINT `idcdis` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idcomm` FOREIGN KEY (`id_commentaire`) REFERENCES `commentaire` (`idcom`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `estimationoffrelivre`
--
ALTER TABLE `estimationoffrelivre`
  ADD CONSTRAINT `for` FOREIGN KEY (`idProposition`) REFERENCES `propositionlivre` (`idPropositionLivre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `idcevent` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FK_1F1B251E37D925CB` FOREIGN KEY (`livre_id`) REFERENCES `livre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `likee`
--
ALTER TABLE `likee`
  ADD CONSTRAINT `idclike` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idcomlike` FOREIGN KEY (`id_commentaire`) REFERENCES `commentaire` (`idcom`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FK_AC634F9967B3B43D` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `propositionlivre`
--
ALTER TABLE `propositionlivre`
  ADD CONSTRAINT `idcproplivre` FOREIGN KEY (`idclient`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `idcrec` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idreprec` FOREIGN KEY (`id_reponse`) REFERENCES `reponse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sujet`
--
ALTER TABLE `sujet`
  ADD CONSTRAINT `idcsujet` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idsujtopic` FOREIGN KEY (`idtopic`) REFERENCES `topic` (`idtopic`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `idctopic` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
