<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\UserRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * User
 *
 * @ORM\Table(name="user")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\UserRepository")

 */
class User
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     *  @Groups({"post:read"})
    */ 
    private $id;

    /**
     * @var string
     * @ORM\Column(name="nom",type="string", length=25, nullable=true)
     * @Assert\NotBlank(message="The name field is required.")
     * @Assert\Length(max=25, maxMessage="The name field cannot exceed {{ limit }} characters.")
     * @Assert\Type(
     *     type="String",
     *     message="The value {{ value }} is not a valid {{ type }}."
     * )
     * @Assert\Regex(
     *     pattern="/\d/",
     *     match=false,
     *     message="Your name cannot contain a number"
     * )
     *  @Groups({"post:read"})
    */ 

    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom",type="string", length=25, nullable=true)
     * @Assert\NotBlank(message="The first name field is required.")
     * @Assert\Length(max=25, maxMessage="The first name field cannot exceed {{ limit }} characters.")
     * @Assert\Type(
     *     type="String",
     *     message="The value {{ value }} is not a valid {{ type }}."
     * )
     * @Assert\Regex(
     *     pattern="/\d/",
     *     match=false,
     *     message="Your name cannot contain a number"
     * )
     *  @Groups({"post:read"})
    */ 
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="adrmail",type="string", length=25, nullable=true)
     * @Assert\NotBlank(message="The email field is required.")
     * @Assert\Email(message="The email '{{ value }}' is not a valid email address.")
     *  @Groups({"post:read"})
    */ 
    private $adrmail;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp",type="string", length=25, nullable=true)
     * @Assert\NotBlank(message="The password field is required.")
     * @Assert\Length(max=25, maxMessage="The password field cannot exceed {{ limit }} characters.")
     *  @Groups({"post:read"})
    */ 
    private $mdp;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse",type="string", length=25, nullable=true)
     * @Assert\NotBlank(message="The address field is required.")
     * @Assert\Length(max=100, maxMessage="The address field cannot exceed {{ limit }} characters.")
     *  @Groups({"post:read"})
    */ 
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="tel",type="string", length=25, nullable=true)
     * @Assert\NotBlank(message="The phone number field is required.")
     * @Assert\Length(min=8,max=8,minMessage="The phone number field cannot exceed {{ limit }} characters.", maxMessage="The phone number field cannot exceed {{ limit }} characters.")
     *  @Groups({"post:read"})
    */ 
    private $tel;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=6, nullable=false)
     *  @Groups({"post:read"})
    */ 
    private $type;

    /**
     * @var int
     *
     * @ORM\Column(name="cin",type="integer", nullable=true)
     * @Assert\NotBlank(message="The CIN field is required.")
     * @Assert\Length(min=8,max=8,minMessage="The phone number field cannot exceed {{ limit }} characters.", maxMessage="The phone number field cannot exceed {{ limit }} characters.")
     * @Assert\Type(
     *     type="integer",
     *     message="The value {{ value }} is not a valid {{ type }}."
     * )
     * @Assert\Positive
     *  @Groups({"post:read"})
    */ 
    private $cin;

    /**
     * @var int
     *
     * @ORM\Column(name="soldepoint", type="integer", nullable=false)
     *  @Groups({"post:read"})
    */ 
    private $soldepoint;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getAdrmail(): ?string
    {
        return $this->adrmail;
    }

    public function setAdrmail(string $adrmail): self
    {
        $this->adrmail = $adrmail;

        return $this;
    }

    public function getMdp(): ?string
    {
        return $this->mdp;
    }

    public function setMdp(string $mdp): self
    {
        $this->mdp = $mdp;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getTel(): ?string
    {
        return $this->tel;
    }

    public function setTel(string $tel): self
    {
        $this->tel = $tel;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getCin(): ?int
    {
        return $this->cin;
    }

    public function setCin(int $cin): self
    {
        $this->cin = $cin;

        return $this;
    }

    public function getSoldepoint(): ?int
    {
        return $this->soldepoint;
    }

    public function setSoldepoint(int $soldepoint): self
    {
        $this->soldepoint = $soldepoint;

        return $this;
    }
    public function __toString(): string
    {
        return $this->id;
    }

}
