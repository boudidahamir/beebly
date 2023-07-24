<?php

namespace App\Controller;

use App\Entity\Propositionlivre;
use App\Entity\User;
use App\Form\PropositionlivreType;
use App\Repository\PropositionlivreRepository;
use Doctrine\ORM\EntityManagerInterface;
use  Doctrine\Persistence\ManagerRegistry;
use DateTime;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\SerializerAwareInterface;
use Symfony\Component\Serializer\SerializerInterface;


#[Route('/propositionlivreapi')]
class PropositionlivreApiController extends AbstractController
{
    #[Route('/new', name: 'app_propositionlivreapi_new', methods: ['GET'])]
    public function new(Request $request, EntityManagerInterface $entityManager,
                        ManagerRegistry $doctrine,SerializerInterface $serializer): Response
    {
        $propositionlivre = new Propositionlivre();


        $c = $doctrine
            ->getRepository(User::class)
            ->find(22);
        $current_date = date('Y-m-d');
        $date_object = DateTime::createFromFormat('Y-m-d', $current_date);

        $propositionlivre->setIdclient($c);
        $propositionlivre->setDateproposition($date_object);
        $propositionlivre->setDescriptionetat($request->get('descriptionetat'));
        $propositionlivre->setEditon($request->get('editon'));
        $propositionlivre->setTitrelivre($request->get('titrelivre'));

        $entityManager->persist($propositionlivre);
        $entityManager->flush();
        $json=$serializer->serialize($propositionlivre,'json',['groups' => "post:read"]);


        return new Response($json);
    }

    #[Route('/liste', name: 'app_propositionlivreapi', methods: ['GET'])]
    public function getProposition(EntityManagerInterface $entityManager, SerializerInterface $serializer): Response
    {
        $propositionlivres = $entityManager
            ->getRepository(Propositionlivre::class)
            ->findAll();
        $json=$serializer->serialize($propositionlivres,'json',['groups' => "post:read"]);




                return new Response($json);
        }

    #[Route('/listenontraité', name: 'app_propositionlivreapi1', methods: ['GET'])]
    public function getPropositionnon(EntityManagerInterface $entityManager,
                                      PropositionlivreRepository $propositionlivreRepository,
                                      SerializerInterface $serializer): Response
    {
        $propositionlivres =
            $propositionlivreRepository->findNonTreated("");
        $json=$serializer->serialize($propositionlivres,'json',['groups' => "post:read"]);




        return new Response($json);
    }







  #[Route('/{idpropositionlivre}', name: 'app_propositionlivreapi_show', methods: ['GET'])]
    public function show(Propositionlivre $propositionlivre,EntityManagerInterface $entityManager, SerializerInterface $serializer): Response
    {
        $propositionlivres = $entityManager
            ->getRepository(Propositionlivre::class)
            ->find($propositionlivre);
        $json=$serializer->serialize($propositionlivres,'json',['groups' => "post:read"]);



        return new Response($json);
    }

    #[Route('/{idpropositionlivre}/edit', name: 'app_propositionlivreapi_edit', methods: ['GET'])]
    public function edit(Request $request, EntityManagerInterface $entityManager
    ,$idpropositionlivre,ManagerRegistry $doctrine,SerializerInterface $serializer): Response
    {


        $propositionlivre = $doctrine
            ->getRepository(Propositionlivre::class)
            ->find($idpropositionlivre);

        $propositionlivre->setDescriptionetat($request->get('descriptionetat'));
        $propositionlivre->setEditon($request->get('editon'));
        $propositionlivre->setTitrelivre($request->get('titrelivre'));

        $entityManager->flush();


        $json=$serializer->serialize($propositionlivre,'json',['groups' => "post:read"]);


        return new Response($json);





        }




    #[Route('/{idpropositionlivre}', name: 'app_propositionlivreapi_delete', methods: ['POST'])]
    public function delete(Request $request, Propositionlivre $propositionlivre,
                           EntityManagerInterface $entityManager,SerializerInterface $serializer): Response
    {
        if ($this->isCsrfTokenValid('delete'.$propositionlivre->getIdpropositionlivre(), $request->request->get('_token'))) {
            $entityManager->remove($propositionlivre);
            $entityManager->flush();
        }


        $jsonContent=$serializer->serialize($propositionlivre,'json',['groups' => "post:read"]);
        return new Response("proposition deleted successfully".json_encode($jsonContent));    }






}


