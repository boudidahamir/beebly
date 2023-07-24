<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230425091818 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE livre ADD users_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE livre ADD CONSTRAINT FK_AC634F9967B3B43D FOREIGN KEY (users_id) REFERENCES user (id)');
        $this->addSql('CREATE INDEX IDX_AC634F9967B3B43D ON livre (users_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE livre DROP FOREIGN KEY FK_AC634F9967B3B43D');
        $this->addSql('DROP INDEX IDX_AC634F9967B3B43D ON livre');
        $this->addSql('ALTER TABLE livre DROP users_id');
    }
}
