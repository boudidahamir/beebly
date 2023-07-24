<?php

namespace App\Controller;

use App\Entity\Livre;
use App\Entity\User;
use App\Form\LivreType;
use App\Service\FileUploader;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ObjectRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Component\Routing\Annotation\Route;

use App\Repository\LivreRepository;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class LivreController extends AbstractController
{
    protected $entityManager;

    public function __construct(EntityManagerInterface $entityManager)
    {
        $this->entityManager = $entityManager;
    }

    /**
     * @Route("/livre", name="app_livre_index", methods={"GET"})
     */
    public function index(LivreRepository $livreRepository): Response
    {
        return $this->render('livre/index.html.twig', [
            'livres' => $livreRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="app_livre_new", methods={"GET", "POST"})
     */
    public function new(EntityManagerInterface $entityManager,Request $request, FileUploader $fileUploader, LivreRepository $livreRepository,SessionInterface $session): Response
    {
        $user = new User();
        $livre = new Livre();
        $form = $this->createForm(LivreType::class, $livre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $image = $form->get('image')->getData();
            if ($image) {
                $imageName = $fileUploader->upload($image);
                $livre->setImage($imageName);
            }
            $id =$session->get('id');
            $user = $entityManager
            ->getRepository(User::class)->find($id);
            $livre->setUsers($user);
            $livreRepository->save($livre, true);

            return $this->redirectToRoute('app_livre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('livre/new.html.twig', [
            'livre' => $livre,
            'form' => $form,
        ]);
    }

    /**
     * @Route("/livre/{id}", name="app_livre_show", methods={"GET"})
     */
    public function show(Livre $livre): Response
    {
        return $this->render('livre/show.html.twig', [
            'livre' => $livre,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_livre_edit", methods={"GET", "POST"})
     */
    public function edit(EntityManagerInterface $entityManager,Request $request,SessionInterface $session, FileUploader $fileUploader, Livre $livre, LivreRepository $livreRepository): Response
    {
        $user=new User();
        $form = $this->createForm(LivreType::class, $livre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $image = $form->get('image')->getData();
            if ($image) { 
                $imageName = $fileUploader->upload($image);
                $livre->setImage($imageName);
            }
            $id =$session->get('id');
            $user = $entityManager
            ->getRepository(User::class)->find($id);
            $livre->setUsers($user);
            $livreRepository->save($livre, true);
            return $this->redirectToRoute('app_livre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('livre/edit.html.twig', [
            'livre' => $livre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_livre_delete", methods={"POST"})
     */
    public function delete(Request $request, Livre $livre, LivreRepository $livreRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$livre->getId(), $request->request->get('_token'))) {
            $livreRepository->remove($livre, true);
        }

        return $this->redirectToRoute('app_livre_index', [], Response::HTTP_SEE_OTHER);
    }

    /*mobile*/
    #[Route('/api/livre/livreApi', name: 'livreApi')]
    public function AlllivreJSON(Request $request, NormalizerInterface $normalizer): Response
    {
  
      $em = $this->getDoctrine()->getManager()->getRepository(Livre::class); // ENTITY MANAGER ELY FIH FONCTIONS PREDIFINES
  
      $Livre = $em->findAll(); // Select * from users;
      $jsonContent = $normalizer->normalize($Livre, 'json', ['groups' => 'post:read']);
      return new Response(json_encode($jsonContent));
    }

    

    

    #[Route('/api/livre/addLivre', name: 'addLivre')]
    public function addLivre(NormalizerInterface $normalizer, Request $request, EntityManagerInterface $entityManager): Response
    {
        $em = $this->getDoctrine()->getManager();
        
        $livre = new Livre();
        $livre->setCategorie($request->get('categorie'));
        $livre->setDescriptionEtatLivre($request->get('descriptionEtatLivre'));
        $livre->setPrix($request->get('prix'));
        $livre->setTitre($request->get('titre'));
        
        $currentDate = new \DateTime();
        $livre->setDatePublication($currentDate);
        $livre->setImage("aucune image");
        $livre->setNote(0);
    
        $user = $em->getRepository(User::class)->find($request->get('idUser'));
        $livre->setUser($user);
        
        $em->persist($livre);
        $em->flush();
        
        $jsonContent = $normalizer->normalize($livre, null, ['groups' => 'post:read']);
        return new JsonResponse($jsonContent);
    }
    
    

    #[Route('/api/livre/deleteLivre/{id}', name: 'deleteLivre')]
    public function DeletelivresJSON(Request $request,NormalizerInterface $normalizer,$id): Response
    {

        $Livre = $this->getDoctrine()->getManager()->getRepository(Livre::class)->find($id); // ENTITY MANAGER ELY FIH FONCTIONS PREDIFINES
        $em = $this->getDoctrine()->getManager();

            $em->remove($Livre);
            $em->flush();
            $jsonContent =$normalizer->normalize($Livre, 'json' ,['groups'=>'post:read']);
            return new Response("information deleted successfully".json_encode($jsonContent));
    }

    #[Route('/api/livre/editLivreApi/{id}', name: 'editLivreApi')]
    public function editLivreApi($id,NormalizerInterface $Normalizer,Request $request,EntityManagerInterface $entityManager): Response
    {
        $em = $this->getDoctrine()->getManager();

        $livre = $em->getRepository(Livre::class)->find($id);
        $user=new User();
        $em = $this->getDoctrine()->getManager();
        $livre->setTitre($request->get('titre'));
        $livre->setCategorie($request->get('categorie'));
        $livre->setPrix($request->get('prix'));
        $livre->setDescriptionEtatLivre($request->get('descriptionEtatLivre'));
        $user = $em->getRepository(User::class)->find($request->get('idUser'));

        $livre->setUser($user);
        
        $em->persist($livre);
        $em->flush();
            $jsonContent = $Normalizer->normalize($livre, 'json',['groups'=>'post:read']);
            return new Response(json_encode($jsonContent));

    }
}
