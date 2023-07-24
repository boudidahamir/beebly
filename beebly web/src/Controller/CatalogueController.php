<?php

namespace App\Controller;

use App\Entity\Livre;
use App\Form\LivreType;
use App\Repository\LivreRepository;
use App\Form\SearchType;
use Doctrine\ORM\NonUniqueResultException;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Doctrine\ORM\EntityManagerInterface;
use mysql_xdevapi\Collection;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Finder\Exception\AccessDeniedException;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\User\UserInterface;

/**
 * @Route("/catalogue")
 */
class CatalogueController extends AbstractController
{
    protected EntityManagerInterface $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }

    /**
     * @Route("/{id}", name="app_catalogue_show", methods={"GET"})
     */
    public function show(Livre $livre): Response
    {
        return $this->render('catalogue/show.html.twig', [
            'livre' => $livre,
        ]);
    }
    #[Route('/search', name: 'reqSearch_search', methods: ['GET', 'POST'])]
    public function search(Request $request, LivreRepository $repo): JsonResponse
    {
        $searchQuery = $request->query->get('q');

        if ($searchQuery) {
            $livre = $repo->search($searchQuery);
        } else {
            $livre = $repo->findAll();
        }

        $livreArray = [];

        foreach ($livre as $liv) {
            $livreArray[] = [
                'id' => $liv->getId(),
                'titre' => $liv->getTitre(),
                'categorie' => $liv->getCategorie(),
                'description' => $liv->getDescriptionEtatLivre(),
                'prix' => $liv->getPrix(),
            ];
        }

        return new JsonResponse($livreArray);
    }

    /**
     * @Route("/", name="app_catalogue_index", methods={"GET", "POST"})
     */
    public function livSearch(Request $request, EntityManagerInterface $entityManager, LivreRepository $repo): Response
    {
        $searchQuery = $request->get('q');

        if ($searchQuery) {
            $liv = $repo->search($searchQuery);
        } else {
            $liv = $repo->findAll();
        }

        return $this->render('catalogue/index.html.twig', [
            'livres' => $liv,
            'searchQuery' => $searchQuery,
        ]);
    }




}
