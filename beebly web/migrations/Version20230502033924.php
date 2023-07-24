<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230502033924 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cloture_achat DROP FOREIGN KEY `foreign`');
        $this->addSql('ALTER TABLE detailslivraison DROP FOREIGN KEY fore');
        $this->addSql('ALTER TABLE estimationoffrelivre DROP FOREIGN KEY `for`');
        $this->addSql('ALTER TABLE propositionlivre DROP FOREIGN KEY idcproplivre');
        $this->addSql('DROP TABLE cloture_achat');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE commandelignelivre');
        $this->addSql('DROP TABLE detailslivraison');
        $this->addSql('DROP TABLE estimationoffrelivre');
        $this->addSql('DROP TABLE livre');
        $this->addSql('DROP TABLE propositionlivre');
        $this->addSql('DROP INDEX id_evenement ON ticket');
        $this->addSql('ALTER TABLE ticket ADD idEvenement INT DEFAULT NULL, DROP id_evenement');
        $this->addSql('ALTER TABLE ticket ADD CONSTRAINT FK_97A0ADA3F7CC4348 FOREIGN KEY (idEvenement) REFERENCES evenement (id)');
        $this->addSql('CREATE INDEX idEvenement ON ticket (idEvenement)');
        $this->addSql('ALTER TABLE topic CHANGE titretopic titretopic VARCHAR(255) DEFAULT NULL, CHANGE description description VARCHAR(255) DEFAULT NULL');
        $this->addSql('ALTER TABLE user CHANGE nom nom VARCHAR(25) DEFAULT NULL, CHANGE prenom prenom VARCHAR(25) DEFAULT NULL, CHANGE adrmail adrmail VARCHAR(25) DEFAULT NULL, CHANGE mdp mdp VARCHAR(25) DEFAULT NULL, CHANGE adresse adresse VARCHAR(25) DEFAULT NULL, CHANGE tel tel VARCHAR(25) DEFAULT NULL, CHANGE type type VARCHAR(6) DEFAULT NULL, CHANGE cin cin INT DEFAULT NULL, CHANGE soldepoint soldepoint INT DEFAULT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE cloture_achat (id_cloture INT AUTO_INCREMENT NOT NULL, id_details_livraison INT DEFAULT NULL, pointAchatFinale INT DEFAULT NULL, etatCloture VARCHAR(45) CHARACTER SET utf8mb4 DEFAULT \'NULL\' COLLATE `utf8mb4_general_ci`, etatLivre VARCHAR(45) CHARACTER SET utf8mb4 DEFAULT \'NULL\' COLLATE `utf8mb4_general_ci`, INDEX foreign_idx (id_details_livraison), PRIMARY KEY(id_cloture)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE commande (idCommande INT AUTO_INCREMENT NOT NULL, idClient INT NOT NULL, adresselivraison VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, etatcommande VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, prix DOUBLE PRECISION NOT NULL, INDEX idc (idClient), PRIMARY KEY(idCommande)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = MyISAM COMMENT = \'\' ');
        $this->addSql('CREATE TABLE commandelignelivre (idLigne INT AUTO_INCREMENT NOT NULL, idLivre INT NOT NULL, idCommande INT NOT NULL, INDEX fk_cmd (idLivre), INDEX fk_cmdd (idCommande), PRIMARY KEY(idLigne)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = MyISAM COMMENT = \'\' ');
        $this->addSql('CREATE TABLE detailslivraison (idDetailsLivraison INT AUTO_INCREMENT NOT NULL, etatLivrasion VARCHAR(45) CHARACTER SET utf8mb4 DEFAULT \'NULL\' COLLATE `utf8mb4_general_ci`, idEstimationOffreLivre INT DEFAULT NULL, AdresseLivraison VARCHAR(45) CHARACTER SET utf8mb4 DEFAULT \'NULL\' COLLATE `utf8mb4_general_ci`, INDEX fore_idx (idEstimationOffreLivre), PRIMARY KEY(idDetailsLivraison)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE estimationoffrelivre (idEstimationOffreLivre INT AUTO_INCREMENT NOT NULL, idProposition INT DEFAULT NULL, pointEstime INT DEFAULT NULL, etat VARCHAR(45) CHARACTER SET utf8mb4 DEFAULT \'NULL\' COLLATE `utf8mb4_general_ci`, INDEX for_idx (idProposition), PRIMARY KEY(idEstimationOffreLivre)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE livre (idLivre INT AUTO_INCREMENT NOT NULL, titre VARCHAR(255) CHARACTER SET latin1 DEFAULT \'NULL\' COLLATE `latin1_swedish_ci`, categorie VARCHAR(255) CHARACTER SET latin1 DEFAULT \'NULL\' COLLATE `latin1_swedish_ci`, datePublication DATETIME DEFAULT NULL, image BLOB DEFAULT \'NULL\', description VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, prix DOUBLE PRECISION NOT NULL, descriptionEtatLivre VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, note INT NOT NULL, PRIMARY KEY(idLivre)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = MyISAM COMMENT = \'\' ');
        $this->addSql('CREATE TABLE propositionlivre (idclient INT DEFAULT NULL, idPropositionLivre INT AUTO_INCREMENT NOT NULL, titreLivre VARCHAR(45) CHARACTER SET utf8mb4 DEFAULT \'NULL\' COLLATE `utf8mb4_general_ci`, editon VARCHAR(45) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, dateProposition DATE DEFAULT NULL, descriptionEtat VARCHAR(45) CHARACTER SET utf8mb4 DEFAULT \'NULL\' COLLATE `utf8mb4_general_ci`, INDEX idcproplivre (idclient), PRIMARY KEY(idPropositionLivre)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE cloture_achat ADD CONSTRAINT `foreign` FOREIGN KEY (id_details_livraison) REFERENCES detailslivraison (idDetailsLivraison)');
        $this->addSql('ALTER TABLE detailslivraison ADD CONSTRAINT fore FOREIGN KEY (idEstimationOffreLivre) REFERENCES estimationoffrelivre (idEstimationOffreLivre)');
        $this->addSql('ALTER TABLE estimationoffrelivre ADD CONSTRAINT `for` FOREIGN KEY (idProposition) REFERENCES propositionlivre (idPropositionLivre)');
        $this->addSql('ALTER TABLE propositionlivre ADD CONSTRAINT idcproplivre FOREIGN KEY (idclient) REFERENCES user (id)');
        $this->addSql('ALTER TABLE ticket DROP FOREIGN KEY FK_97A0ADA3F7CC4348');
        $this->addSql('DROP INDEX idEvenement ON ticket');
        $this->addSql('ALTER TABLE ticket ADD id_evenement INT NOT NULL, DROP idEvenement');
        $this->addSql('CREATE INDEX id_evenement ON ticket (id_evenement)');
        $this->addSql('ALTER TABLE topic CHANGE titretopic titretopic VARCHAR(255) DEFAULT \'NULL\', CHANGE description description VARCHAR(255) DEFAULT \'NULL\'');
        $this->addSql('ALTER TABLE user CHANGE nom nom VARCHAR(25) NOT NULL, CHANGE prenom prenom VARCHAR(25) NOT NULL, CHANGE adrmail adrmail VARCHAR(25) NOT NULL, CHANGE mdp mdp VARCHAR(25) NOT NULL, CHANGE adresse adresse VARCHAR(25) NOT NULL, CHANGE tel tel VARCHAR(25) NOT NULL, CHANGE type type VARCHAR(6) NOT NULL, CHANGE cin cin INT NOT NULL, CHANGE soldepoint soldepoint INT NOT NULL');
    }
}
