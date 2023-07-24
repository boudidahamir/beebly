package com.mycompany.myapp.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.PropositionLivre;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceProposition;

public class EditPropositionForm extends BaseForm {




    public EditPropositionForm( Resources res,PropositionLivre p) {
        super("Proposition", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Proposition");
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
        setTitle("update Proposition");
        setLayout(BoxLayout.yCenter());



        TextField tftitrelivre = new TextField(""," titrelivre ");
        tftitrelivre.setText(p.getTitreLivre());
        TextField tfediton = new TextField("","editon");
        tfediton.setText(p.getEditon());

        TextField tfdescriptionetat = new TextField(""," descriptionetat");
        tfdescriptionetat.setText(p.getDescriptionEtat());



//Vector<String> vectorCommande = new Vector<>();
//ArrayList<User> enn = ServiceUser.getInstance().getAll();
//for (User user : enn) {
//    vectorCommande.add(user.getNom());
//}
//
//ComboBox<String> users = new ComboBox<>(vectorCommande);
//users.setUIID("ComboBox");

        Button btnValider = new Button("update proposition");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftitrelivre.getText().length()==0)||(tfediton.getText().length()==0) ||
                        (tfdescriptionetat.getText().length()==0))

                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else {
                    try {
                        p.setDescriptionEtat(tfdescriptionetat.getText());
                        p.setEditon( tfediton.getText());
                        p.setTitreLivre(tftitrelivre.getText());

                        // int idUser = ServiceUser.connectedUser.getId();//enn.get(users.getSelectedIndex()).getId();
                        int idUser =26;
                        if(ServiceProposition.getInstance().modify(p))
                            Dialog.show("Success","Connection accepted",new Command("OK"));

                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "price must be a number", new Command("OK"));

                    }
                    Dialog.show("Success","Connection accepted",new Command("OK"));
                }}
        });
        addAll(tftitrelivre,tfediton,tfdescriptionetat);
        addAll(btnValider);
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
}
