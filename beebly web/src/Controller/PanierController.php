<?php

namespace App\Controller;

use App\Entity\Livre;
use App\Repository\ItemRepository;
use App\Repository\LivreRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class PanierController extends AbstractController
{
    #[Route('/panier', name: 'app_panier')]
    public function index(SessionInterface $session, LivreRepository $livreRepository): Response
    {
        $panier = $session->get('panier', []);
        $panierWithData = [];
        foreach ($panier as $id => $quantity){
            $panierWithData[]= [
                'livre' => $livreRepository->find($id),
                'quantity' => $quantity
            ];

        }

        $total =0;
        foreach ($panierWithData as $item){
            $totalItem = $item['livre']->getPrix() * $item['quantity'];
            $total += $totalItem;


        }
        return $this->render('panier/index.html.twig', [
            'items' => $panierWithData,
            'total' => $total
        ]);
    }

    #[Route('/panier/add/{id}', name: 'panier_add')]
    public function add($id, SessionInterface $session){

        $panier =$session->get('panier',[]);
        if(!empty($panier[$id])){
            $panier[$id]++;
        }else{
            $panier[$id] = 1;;
        }
        $session->set('panier',$panier);
        return $this->redirectToRoute("app_panier");

    }

    #[Route('/panier/remove/{id}', name: 'panier_remove')]
    public function remove($id, SessionInterface $session){
        $panier = $session->get('panier',[]);

        if(!empty($panier[$id])){
            unset($panier[$id]);
        }
        $session->set('panier',$panier);
        return $this->redirectToRoute("app_panier");
    }

    private function getTotal($panier)
    {
        // Calculer le total du panier
        // ...
    }

    /**
     * @Route("/panier/pdf", name="panier_pdf", methods={"GET", "POST"})
     */
    public function pdf(ItemRepository $itemRepository,SessionInterface $session, LivreRepository $livreRepository)
    {
        $panier = $session->get('panier', []);
        $panierWithData = [];
        foreach ($panier as $id => $quantity){
            $panierWithData[]= [
                'livre' => $livreRepository->find($id),
                'quantity' => $quantity
            ];

        }

        $total =0;
        foreach ($panierWithData as $item){
            $totalItem = $item['livre']->getPrix() * $item['quantity'];
            $total += $totalItem;


        }


        //
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('panier/index2.html.twig', [
            'items' => $panierWithData,
            'total' => $total
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        //      $dompdf->setPaper('A3', 'portrait');
        $dompdf->render();

        $pdf = $dompdf->output();

// Envoyer le fichier PDF en réponse à la requête HTTP
        return new Response(
            $pdf,
            200,
            [
                'Content-Type' => 'application/pdf',
                'Content-Disposition' => 'attachment; filename="document.pdf"',
            ]
        );
    }
}
