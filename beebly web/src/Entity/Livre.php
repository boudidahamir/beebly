<?php

namespace App\Entity;


use DateTime;
use DateTimeInterface;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\Collection;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Livre
 *
 * @ORM\Table(name="livre")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\LivreRepository")
 */
class Livre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups({"post:read"})
     */
    private $id;

    /**
     * @var string|null
     *
     * @ORM\Column(name="titre", type="string", length=255, nullable=true, options={"default"="NULL"})
     * @Groups({"post:read"})
     */
    private $titre ;

    /**
     * @var string|null
     *
     * @ORM\Column(name="categorie", type="string", length=255, nullable=true, options={"default"="NULL"})
     * @Groups({"post:read"})
     */
    private $categorie ;

    /**
     * @var DateTime|null
     *
     * @ORM\Column(name="date_publication", type="datetime", nullable=true)
     * @Groups({"post:read"})
     */
    private $datepublication;

    /**
     * @var string|null
     *
     * @ORM\Column(name="image", type="string", length=65535, nullable=true, options={"default"="NULL"})
     * @Groups({"post:read"})
     */
    private $image;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     * @Groups({"post:read"})
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="description_etat_livre", type="string", length=255, nullable=false)
     * @Groups({"post:read"})
     */
    private $descriptionetatlivre;

    /**
     * @var int
     *
     * @ORM\Column(name="note", type="integer", nullable=false)
     * @Groups({"post:read"})
     */
    private $note;
    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="user_id", referencedColumnName="id")
     * })
     * @Groups({"post:read"})
     */
    private $user;

    public function __construct()
    {
        $this->items = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(?string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function getCategorie(): ?string
    {
        return $this->categorie;
    }

    public function setCategorie(?string $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    public function getDatePublication(): DateTimeInterface
    {
        return $this->datepublication;
    }

    public function setDatePublication(DateTimeInterface $DatePublication): self
    {
        $this->datepublication = $DatePublication;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(?float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getDescriptionEtatLivre(): ?string
    {
        return $this->descriptionetatlivre;
    }

    public function setDescriptionEtatLivre(?string $DescriptionEtatLivre): self
    {
        $this->descriptionetatlivre = $DescriptionEtatLivre;

        return $this;
    }

    public function getNote(): ?int
    {
        return $this->note;
    }

    public function setNote(?int $note): self
    {
        $this->note = $note;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }
    
    public function setUser(?User $user): self
    {
        $this->user = $user;
    
        return $this;
    }
    

    /**
     * @return Collection<int, Item>
     */
    public function getItems(): Collection
    {
        return $this->items;
    }

    public function addItem(Item $item): self
    {
        if (!$this->items->contains($item)) {
            $this->items->add($item);
            $item->setLivre($this);
        }

        return $this;
    }

    public function removeItem(Item $item): self
    {
        if ($this->items->removeElement($item)) {
            // set the owning side to null (unless already changed)
            if ($item->getLivre() === $this) {
                $item->setLivre(null);
            }
        }

        return $this;
    }

}