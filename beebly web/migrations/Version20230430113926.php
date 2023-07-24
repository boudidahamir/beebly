<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230430113926 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE livre_like (id INT AUTO_INCREMENT NOT NULL, livre_id INT DEFAULT NULL, user_id INT DEFAULT NULL, INDEX IDX_A604BB3E37D925CB (livre_id), INDEX IDX_A604BB3EA76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE livre_like ADD CONSTRAINT FK_A604BB3E37D925CB FOREIGN KEY (livre_id) REFERENCES livre (id)');
        $this->addSql('ALTER TABLE livre_like ADD CONSTRAINT FK_A604BB3EA76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE livre_like DROP FOREIGN KEY FK_A604BB3E37D925CB');
        $this->addSql('ALTER TABLE livre_like DROP FOREIGN KEY FK_A604BB3EA76ED395');
        $this->addSql('DROP TABLE livre_like');
    }
}
