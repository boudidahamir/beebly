<?php

namespace App\Controller;

use App\Entity\User;
use App\Entity\Dislikee;

use App\Entity\Likee;
use App\Entity\Commentaire;
use App\Form\CommentaireType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\HttpFoundation\Session\SessionInterface;
#[Route('/commentaire')]
class CommentaireController extends AbstractController
{
    #[Route('/', name: 'app_commentaire_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $commentaires = $entityManager
            ->getRepository(Commentaire::class)
            ->findAll();

        return $this->render('commentaire/index.html.twig', [
            'commentaires' => $commentaires,
        ]);
    }

    #[Route('/new', name: 'app_commentaire_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $commentaire = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            dd($commentaire);
            $entityManager->persist($commentaire);
            $entityManager->flush();

            return $this->redirectToRoute('app_commentaire_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('commentaire/new.html.twig', [
            'commentaire' => $commentaire,
            'form' => $form,
        ]);
    }

    #[Route('/{idcom}', name: 'app_commentaire_show', methods: ['GET'])]
    public function show(Commentaire $commentaire): Response
    {
        return $this->render('commentaire/show.html.twig', [
            'commentaire' => $commentaire,
        ]);
    }

    #[Route('/commentLike/{idcom}', name: 'commentLike', methods: ['GET'])]
    public function commentLike(Commentaire $commentaire,SessionInterface $session, EntityManagerInterface $entityManager): Response
    {
        
        $id =$session->get('id');
        if ($id!= null){
            $user = $entityManager
            ->getRepository(User::class)
            ->find($id);
            // Find dislikee by user and comment
    $dislikee = $entityManager
    ->getRepository(Dislikee::class)
    ->findOneBy(['idUser' => $user, 'idCommentaire' => $commentaire]);

// Find likee by user and comment
$likee = $entityManager
    ->getRepository(Likee::class)
    ->findOneBy(['idUser' => $user, 'idCommentaire' => $commentaire]);

if ($likee === null) {
    if ($dislikee !== null) {
       
             // Update comment nblike
     $commentaire->setNbDislike($commentaire->getNbDislike() -1);
     $entityManager->persist($commentaire);
     $entityManager->flush();

        // Remove existing dislikee
        $entityManager->remove($dislikee);
        $entityManager->flush();
    }
     // Create new likee
     $like = new Likee();
     $like->setIdCommentaire($commentaire);
     $like->setIduser($user);
     $entityManager->persist($like);
     $entityManager->flush();

     // Update comment nblike
     $commentaire->setNblike($commentaire->getNblike() + 1);
     $entityManager->persist($commentaire);
     $entityManager->flush();

        }
       
       
            return $this->redirectToRoute('topicFront', [], Response::HTTP_SEE_OTHER);

    }else{
        return $this->redirectToRoute('app_user_signin', [], Response::HTTP_SEE_OTHER);
    }
    }

    #[Route('/commentDisLike/{idcom}', name: 'commentDisLike', methods: ['GET'])]
    public function commentDisLike(Commentaire $commentaire,SessionInterface $session, EntityManagerInterface $entityManager): Response
    {
        $id =$session->get('id');
        if ($id!= null){
            $user = $entityManager
            ->getRepository(User::class)
            ->find($id);
      
        // Find likee by user and comment
        $likee = $entityManager
            ->getRepository(Likee::class)
            ->findOneBy(['idUser' => $user, 'idCommentaire' => $commentaire]);

        // Find dislikee by user and comment
        $dislikee = $entityManager
            ->getRepository(Dislikee::class)
            ->findOneBy(['idUser' => $user, 'idCommentaire' => $commentaire]);

        if ($dislikee === null) {
            if ($likee !== null) {
                // Update comment nblike
                $commentaire->setNblike($commentaire->getNblike() - 1);
                $entityManager->persist($commentaire);
                $entityManager->flush();

                // Remove existing likee
                $entityManager->remove($likee);
                $entityManager->flush();
            }
            // Create new dislikee
            $dislike = new Dislikee();
            $dislike->setIdCommentaire($commentaire);
            $dislike->setIduser($user);
            $entityManager->persist($dislike);
            $entityManager->flush();

            // Update comment nbdislike
            $commentaire->setNbdislike($commentaire->getNbdislike() + 1);
            $entityManager->persist($commentaire);
            $entityManager->flush();
        }
            return $this->redirectToRoute('topicFront', [], Response::HTTP_SEE_OTHER);

    }else{
        return $this->redirectToRoute('app_user_signin', [], Response::HTTP_SEE_OTHER);
    }
    }
}
