<?php

namespace App\Controller;

use App\Entity\Propositionlivre;
use App\Entity\User;
use App\Form\PropositionlivreType;
use Doctrine\ORM\EntityManagerInterface;
use  Doctrine\Persistence\ManagerRegistry;
use DateTime;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;


#[Route('/propositionlivre')]
class PropositionlivreController extends AbstractController
{
    #[Route('/liste', name: 'app_propositionlivre_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager, Request $request): Response
    {
        $propositionlivres = $entityManager
            ->getRepository(Propositionlivre::class)
            ->findAll();


        if($request->get('ajax')){
            if($request->get('check')=='not-treated')
                $propositionlivres = $entityManager
                    ->getRepository(Propositionlivre::class)
                    ->findNonTreated($request->get('search15'));

        elseif ($request->get('check')=='treated')
                $propositionlivres = $entityManager
                    ->getRepository(Propositionlivre::class)
                    ->findTreated($request->get('search15'));

        elseif ($request->get('check')=='all')
                $propositionlivres = $entityManager
                    ->getRepository(Propositionlivre::class)
                    ->findAllPropositions($request->get('search15'));




                return new JsonResponse([
                'content' => $this->renderView('propositionlivre/filtredContent.html.twig',
                    compact('propositionlivres'))
            ]);
        }


        return $this->render('propositionlivre/index.html.twig', [
            'propositionlivres' => $propositionlivres,
        ]);
    }

    #[Route('/mespropositions', name: 'show_my_propositions', methods: ['GET'])]
    public function mesPropositions(EntityManagerInterface $entityManager,SessionInterface $session): Response
    {
        $id=$session->get('id');
        $propositionlivres = $entityManager
            ->getRepository(Propositionlivre::class)
            ->findBy(
                ['idclient' => $id]
            );

        return $this->render('propositionlivre/show_my_propositions.html.twig', [
            'propositionlivres' => $propositionlivres,
        ]);
    }
    #[Route('/statistique', name: 'show_my_statistique', methods: ['GET'])]
    public function statistique(EntityManagerInterface $entityManager,Request $request): Response
    {


        if($request->get('ajax')){
            $year=intVal(substr($request->get('date'), 0, 4));
            $month=intVal(substr($request->get('date'), 5, 2));

            $propositionlivresStat = $entityManager
                ->getRepository(Propositionlivre::class)->getStat($month,$year);

            $dates = [];
            $dates[] = substr($request->get('date'), 0, 4)."-".substr($request->get('date'), 5, 2)."-01";

            $propositionCount = [];

            foreach($propositionlivresStat as $propositionStat){
                //dd(end($dates),$propositionStat['dateproposition']->format('Y-m-d'));

                while(!(end($dates) ==$propositionStat['dateproposition']->format('Y-m-d'))){

                    $dates[] = date('Y-m-d', strtotime(end($dates) . ' +1 day'));
                    $propositionCount[] = 0;

                }

                $propositionCount[] = $propositionStat[1];
            }

        return new JsonResponse([
            'content' => $this->renderView('propositionlivre/statistiquePropositionCount.html.twig',
                compact('propositionCount')),
            'dates'=>$dates,
            'propositionCount'=>$propositionCount,

        ]);}

        $propositionlivresStat = $entityManager
            ->getRepository(Propositionlivre::class)->getStat(date('m'),date('Y'));


        $dates = [];
        $dates[] = date('Y')."-".date('m')."-01";

        $propositionCount = [];

        foreach($propositionlivresStat as $propositionStat){
            //dd(end($dates),$propositionStat['dateproposition']->format('Y-m-d'));

            while(!(end($dates) ==$propositionStat['dateproposition']->format('Y-m-d'))){

                $dates[] = date('Y-m-d', strtotime(end($dates) . ' +1 day'));
                $propositionCount[] = 0;

            }

            $propositionCount[] = $propositionStat[1];
        }
        return $this->render('propositionlivre/statistique.html.twig', [
            'app' => ['dates' => $dates,'propositionCount'=>$propositionCount]

        ]);
    }


    #[Route('/new', name: 'app_propositionlivre_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager,ManagerRegistry $doctrine): Response
    {
        $propositionlivre = new Propositionlivre();
        $form = $this->createForm(PropositionlivreType::class, $propositionlivre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $c = $doctrine
                ->getRepository(User::class)
                ->find(1);
            $current_date = date('Y-m-d');
            $date_object = DateTime::createFromFormat('Y-m-d', $current_date);

            $propositionlivre->setIdclient($c);
            $propositionlivre->setDateproposition($date_object);
            $entityManager->persist($propositionlivre);
            $entityManager->flush();

            return $this->redirectToRoute('show_my_propositions', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('propositionlivre/new.html.twig', [
            'propositionlivre' => $propositionlivre,
            'form' => $form,
        ]);
    }

  #[Route('/{idpropositionlivre}', name: 'app_propositionlivre_show', methods: ['GET'])]
    public function show(Propositionlivre $propositionlivre): Response
    {
        return $this->render('propositionlivre/show.html.twig', [
            'propositionlivre' => $propositionlivre,
        ]);
    }

    #[Route('/{idpropositionlivre}/edit', name: 'app_propositionlivre_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Propositionlivre $propositionlivre, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PropositionlivreType::class, $propositionlivre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_propositionlivre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('propositionlivre/edit.html.twig', [
            'propositionlivre' => $propositionlivre,
            'form' => $form,
        ]);
    }

    #[Route('/{idpropositionlivre}', name: 'app_propositionlivre_delete', methods: ['POST'])]
    public function delete(Request $request, Propositionlivre $propositionlivre, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$propositionlivre->getIdpropositionlivre(), $request->request->get('_token'))) {
            $entityManager->remove($propositionlivre);
            $entityManager->flush();
        }

        return $this->redirectToRoute('show_my_propositions', [], Response::HTTP_SEE_OTHER);
    }



    #[Route('/{idpropositionlivre}/showMyProposition', name: 'app_propositionlivre_show_my_proposition', methods: ['GET'])]
    public function showMyProposition(Propositionlivre $propositionlivre): Response
    {
        return $this->render('propositionlivre/show_details_of_my_proposition.html.twig', [
            'propositionlivre' => $propositionlivre,
        ]);
    }




}