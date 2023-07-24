<?php

namespace App\Repository;

use App\Entity\Livre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\NonUniqueResultException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Livre>
 *
 * @method Livre|null find($id, $lockMode = null, $lockVersion = null)
 * @method Livre|null findOneBy(array $criteria, array $orderBy = null)
 * @method Livre[]    findAll()
 * @method Livre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class LivreRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Livre::class);
    }

    public function save(Livre $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Livre $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function findOneWithFavoris(int $id): ?Livre
    {
        return $this->createQueryBuilder('l')
            ->addSelect('f')
            ->leftJoin('l.favoris', 'f')
            ->andWhere('l.id = :id')
            ->setParameter('id', $id)
            ->getQuery()
            ->getOneOrNullResult()
            ;
    }
//
//    public function search(string $query)
//    {
//        $entityManager = $this->getEntityManager();
//
//        $queryBuilder = $entityManager->createQueryBuilder()
//            ->select('livre')
//            ->from(Livre::class, 'livre')
//            ->where('livre.titre LIKE :query')
//            ->orWhere('livre.auteur LIKE :query')
//            ->setParameter('query', '%'.$query.'%');
//
//        return $queryBuilder->getQuery()->getResult();
//    }

    public function findByTitle($title)
    {
        return $this->createQueryBuilder('l')
            ->andWhere('l.titre LIKE :title')
            ->setParameter('title', '%'.$title.'%')
            ->getQuery()
            ->getResult();
    }


    /**
     * @throws NonUniqueResultException
     */
    public function findOneByTitle($title)
    {
        return $this->createQueryBuilder('l')
            ->andWhere('l.titre LIKE :title')
            ->setParameter('title', '%'.$title.'%')
            ->getQuery()
            ->getOneOrNullResult();
    }


    public function search($searchQuery)
    {


        return $this->createQueryBuilder('q')
            ->where('q.titre LIKE :searchQuery')
            ->setParameter('searchQuery', '%' . $searchQuery . '%')
            ->getQuery()
            ->getResult();


    }



//    /**
//     * @return Livre[] Returns an array of Livre objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('l.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Livre
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
