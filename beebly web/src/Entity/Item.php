<?php

namespace App\Entity;
use App\Repository\ItemRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Item
 *
 * @ORM\Table(name="item", indexes={@ORM\Index(name="IDX_1F1B251E37D925CB", columns={"livre_id"})})
 * @ORM\Entity
 */
class Item
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
     * @var int|null
     *
     * @ORM\Column(name="quantity", type="integer", nullable=true)
     * @Groups({"post:read"})
     */
    private $quantity;

    /**
     * @var Livre
     *
     * @ORM\ManyToOne(targetEntity="Livre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="livre_id", referencedColumnName="id")
     * })
     * @Groups({"post:read"})
     */
    private $livre;


}
