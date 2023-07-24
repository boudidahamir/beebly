<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230507164319 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cloture_achat DROP FOREIGN KEY `foreign`');
        $this->addSql('ALTER TABLE cloture_achat ADD CONSTRAINT FK_3D072CDB2D3BFBD3 FOREIGN KEY (id_details_livraison) REFERENCES detailslivraison (idDetailsLivraison)');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY idccom');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY idsujcom');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BCF8163584 FOREIGN KEY (idsujet) REFERENCES sujet (idsujet)');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BC5E5C27E9 FOREIGN KEY (iduser) REFERENCES user (id)');
        $this->addSql('ALTER TABLE detailslivraison DROP FOREIGN KEY fore');
        $this->addSql('ALTER TABLE detailslivraison ADD CONSTRAINT FK_E29AA9F41737DF93 FOREIGN KEY (idEstimationOffreLivre) REFERENCES estimationoffrelivre (idEstimationOffreLivre)');
        $this->addSql('ALTER TABLE dislikee DROP FOREIGN KEY idcdis');
        $this->addSql('ALTER TABLE dislikee DROP FOREIGN KEY idcomm');
        $this->addSql('ALTER TABLE dislikee ADD CONSTRAINT FK_D9270B407FE2A54B FOREIGN KEY (id_commentaire) REFERENCES commentaire (idcom)');
        $this->addSql('ALTER TABLE dislikee ADD CONSTRAINT FK_D9270B406B3CA4B FOREIGN KEY (id_user) REFERENCES user (id)');
        $this->addSql('ALTER TABLE estimationoffrelivre DROP FOREIGN KEY `for`');
        $this->addSql('ALTER TABLE estimationoffrelivre ADD CONSTRAINT FK_B9C1167787903C2B FOREIGN KEY (idProposition) REFERENCES propositionlivre (idPropositionLivre)');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY idcevent');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681E6B3CA4B FOREIGN KEY (id_user) REFERENCES user (id)');
        $this->addSql('ALTER TABLE item DROP FOREIGN KEY FK_1F1B251E37D925CB');
        $this->addSql('ALTER TABLE item ADD CONSTRAINT FK_1F1B251E37D925CB FOREIGN KEY (livre_id) REFERENCES livre (id)');
        $this->addSql('ALTER TABLE likee DROP FOREIGN KEY idclike');
        $this->addSql('ALTER TABLE likee DROP FOREIGN KEY idcomlike');
        $this->addSql('ALTER TABLE likee ADD CONSTRAINT FK_BD1EFB2C6B3CA4B FOREIGN KEY (id_user) REFERENCES user (id)');
        $this->addSql('ALTER TABLE likee ADD CONSTRAINT FK_BD1EFB2C7FE2A54B FOREIGN KEY (id_commentaire) REFERENCES commentaire (idcom)');
        $this->addSql('ALTER TABLE livre DROP FOREIGN KEY FK_AC634F9967B3B43D');
        $this->addSql('ALTER TABLE livre ADD CONSTRAINT FK_AC634F99A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE propositionlivre DROP FOREIGN KEY idcproplivre');
        $this->addSql('ALTER TABLE propositionlivre CHANGE descriptionEtat descriptionEtat VARCHAR(300) DEFAULT \'NULL\'');
        $this->addSql('ALTER TABLE propositionlivre ADD CONSTRAINT FK_AAE4FFF0A3F9A9F9 FOREIGN KEY (idclient) REFERENCES user (id)');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY idcrec');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY idreprec');
        $this->addSql('ALTER TABLE reclamation CHANGE photo photo LONGBLOB NOT NULL');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE6064046B3CA4B FOREIGN KEY (id_user) REFERENCES user (id)');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE606404812B77B7 FOREIGN KEY (id_reponse) REFERENCES reponse (id)');
        $this->addSql('ALTER TABLE sujet DROP FOREIGN KEY idcsujet');
        $this->addSql('ALTER TABLE sujet DROP FOREIGN KEY idsujtopic');
        $this->addSql('ALTER TABLE sujet ADD CONSTRAINT FK_2E13599D4B45B202 FOREIGN KEY (idtopic) REFERENCES topic (idtopic)');
        $this->addSql('ALTER TABLE sujet ADD CONSTRAINT FK_2E13599D5E5C27E9 FOREIGN KEY (iduser) REFERENCES user (id)');
        $this->addSql('ALTER TABLE ticket CHANGE idEvenement idEvenement INT DEFAULT NULL');
        $this->addSql('ALTER TABLE ticket ADD CONSTRAINT FK_97A0ADA3F7CC4348 FOREIGN KEY (idEvenement) REFERENCES evenement (id)');
        $this->addSql('ALTER TABLE ticket RENAME INDEX id_evenement TO idEvenement');
        $this->addSql('ALTER TABLE topic DROP FOREIGN KEY idctopic');
        $this->addSql('ALTER TABLE topic CHANGE titretopic titretopic VARCHAR(255) DEFAULT NULL, CHANGE description description VARCHAR(255) DEFAULT NULL');
        $this->addSql('ALTER TABLE topic ADD CONSTRAINT FK_9D40DE1B5E5C27E9 FOREIGN KEY (iduser) REFERENCES user (id)');
        $this->addSql('ALTER TABLE user CHANGE nom nom VARCHAR(25) DEFAULT NULL, CHANGE prenom prenom VARCHAR(25) DEFAULT NULL, CHANGE adrmail adrmail VARCHAR(25) DEFAULT NULL, CHANGE mdp mdp VARCHAR(25) DEFAULT NULL, CHANGE adresse adresse VARCHAR(25) DEFAULT NULL, CHANGE tel tel VARCHAR(25) DEFAULT NULL, CHANGE type type VARCHAR(6) DEFAULT NULL, CHANGE cin cin INT DEFAULT NULL, CHANGE soldepoint soldepoint INT DEFAULT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cloture_achat DROP FOREIGN KEY FK_3D072CDB2D3BFBD3');
        $this->addSql('ALTER TABLE cloture_achat ADD CONSTRAINT `foreign` FOREIGN KEY (id_details_livraison) REFERENCES detailslivraison (idDetailsLivraison) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BCF8163584');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BC5E5C27E9');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT idccom FOREIGN KEY (iduser) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT idsujcom FOREIGN KEY (idsujet) REFERENCES sujet (idsujet) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE detailslivraison DROP FOREIGN KEY FK_E29AA9F41737DF93');
        $this->addSql('ALTER TABLE detailslivraison ADD CONSTRAINT fore FOREIGN KEY (idEstimationOffreLivre) REFERENCES estimationoffrelivre (idEstimationOffreLivre) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE dislikee DROP FOREIGN KEY FK_D9270B407FE2A54B');
        $this->addSql('ALTER TABLE dislikee DROP FOREIGN KEY FK_D9270B406B3CA4B');
        $this->addSql('ALTER TABLE dislikee ADD CONSTRAINT idcdis FOREIGN KEY (id_user) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE dislikee ADD CONSTRAINT idcomm FOREIGN KEY (id_commentaire) REFERENCES commentaire (idcom) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE estimationoffrelivre DROP FOREIGN KEY FK_B9C1167787903C2B');
        $this->addSql('ALTER TABLE estimationoffrelivre ADD CONSTRAINT `for` FOREIGN KEY (idProposition) REFERENCES propositionlivre (idPropositionLivre) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681E6B3CA4B');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT idcevent FOREIGN KEY (id_user) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE item DROP FOREIGN KEY FK_1F1B251E37D925CB');
        $this->addSql('ALTER TABLE item ADD CONSTRAINT FK_1F1B251E37D925CB FOREIGN KEY (livre_id) REFERENCES livre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE likee DROP FOREIGN KEY FK_BD1EFB2C6B3CA4B');
        $this->addSql('ALTER TABLE likee DROP FOREIGN KEY FK_BD1EFB2C7FE2A54B');
        $this->addSql('ALTER TABLE likee ADD CONSTRAINT idclike FOREIGN KEY (id_user) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE likee ADD CONSTRAINT idcomlike FOREIGN KEY (id_commentaire) REFERENCES commentaire (idcom) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE livre DROP FOREIGN KEY FK_AC634F99A76ED395');
        $this->addSql('ALTER TABLE livre ADD CONSTRAINT FK_AC634F9967B3B43D FOREIGN KEY (user_id) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE propositionlivre DROP FOREIGN KEY FK_AAE4FFF0A3F9A9F9');
        $this->addSql('ALTER TABLE propositionlivre CHANGE descriptionEtat descriptionEtat VARCHAR(45) DEFAULT \'NULL\'');
        $this->addSql('ALTER TABLE propositionlivre ADD CONSTRAINT idcproplivre FOREIGN KEY (idclient) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE6064046B3CA4B');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE606404812B77B7');
        $this->addSql('ALTER TABLE reclamation CHANGE photo photo LONGBLOB DEFAULT NULL');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT idcrec FOREIGN KEY (id_user) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT idreprec FOREIGN KEY (id_reponse) REFERENCES reponse (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE sujet DROP FOREIGN KEY FK_2E13599D4B45B202');
        $this->addSql('ALTER TABLE sujet DROP FOREIGN KEY FK_2E13599D5E5C27E9');
        $this->addSql('ALTER TABLE sujet ADD CONSTRAINT idcsujet FOREIGN KEY (iduser) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE sujet ADD CONSTRAINT idsujtopic FOREIGN KEY (idtopic) REFERENCES topic (idtopic) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE ticket DROP FOREIGN KEY FK_97A0ADA3F7CC4348');
        $this->addSql('ALTER TABLE ticket CHANGE idEvenement idEvenement INT NOT NULL');
        $this->addSql('ALTER TABLE ticket RENAME INDEX idevenement TO id_evenement');
        $this->addSql('ALTER TABLE topic DROP FOREIGN KEY FK_9D40DE1B5E5C27E9');
        $this->addSql('ALTER TABLE topic CHANGE titretopic titretopic VARCHAR(255) DEFAULT \'NULL\', CHANGE description description VARCHAR(255) DEFAULT \'NULL\'');
        $this->addSql('ALTER TABLE topic ADD CONSTRAINT idctopic FOREIGN KEY (iduser) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE user CHANGE nom nom VARCHAR(25) NOT NULL, CHANGE prenom prenom VARCHAR(25) NOT NULL, CHANGE adrmail adrmail VARCHAR(25) NOT NULL, CHANGE mdp mdp VARCHAR(25) NOT NULL, CHANGE adresse adresse VARCHAR(25) NOT NULL, CHANGE tel tel VARCHAR(25) NOT NULL, CHANGE type type VARCHAR(6) NOT NULL, CHANGE cin cin INT NOT NULL, CHANGE soldepoint soldepoint INT NOT NULL');
    }
}
