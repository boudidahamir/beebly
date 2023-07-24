package com.mycompany.myapp.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.EstimationOffreLivre;
import com.mycompany.myapp.entities.Etat;
import com.mycompany.myapp.entities.PropositionLivre;
import com.mycompany.myapp.services.ServiceEstimation;
import com.mycompany.myapp.services.ServiceProposition;

public class AddEstimationForm extends BaseForm {




    public AddEstimationForm(Resources res, PropositionLivre p) {
        super("Estimer", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Estimer");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        //tb.addSearchCommand(e -> {});
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();

        //  addTab(swipe,  spacer1);
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);


        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        setTitle("Estimer ");
        setLayout(BoxLayout.yCenter());



        Label tftitrelivre = new Label("titrelivre: " + "  " + (p.getTitreLivre()));

        Label tfediton = new Label("edition: " + "  " + (p.getTitreLivre()));

        Label nomClient = new Label("nom client: " + "  " + (p.getUser().getNom()));

        Label dateProposition = new Label("date proposition: " + "  " + p.getDateProposition().toString());

        Label tfdescriptionetat = new Label("description état livre : " + "  " + p.getDescriptionEtat());

        TextField tfpoint = new TextField("","point ");






//Vector<String> vectorCommande = new Vector<>();
//ArrayList<User> enn = ServiceUser.getInstance().getAll();
//for (User user : enn) {
//    vectorCommande.add(user.getNom());
//}
//
//ComboBox<String> users = new ComboBox<>(vectorCommande);
//users.setUIID("ComboBox");

        Button btnValider = new Button("ajouter estimation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                    try {
                        EstimationOffreLivre estimationOffreLivre=new EstimationOffreLivre(p
                                ,Integer.valueOf(tfpoint.getText()), Etat.En_attente);
                        // int idUser = ServiceUser.connectedUser.getId();//enn.get(users.getSelectedIndex()).getId();

                        if(ServiceEstimation.getInstance().add(estimationOffreLivre))
                            Dialog.show("Success","Connection accepted",new Command("OK"));

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "price must be a number", new Command("OK"));

                    }
                    Dialog.show("Success","Connection accepted",new Command("OK"));
                }
        });
        addAll(tftitrelivre,tfediton,tfdescriptionetat,dateProposition,nomClient,tfpoint);
        addAll(btnValider);
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
}
