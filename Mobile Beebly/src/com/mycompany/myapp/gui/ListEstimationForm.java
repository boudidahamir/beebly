package com.mycompany.myapp.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.EstimationOffreLivre;
import com.mycompany.myapp.entities.PropositionLivre;
import com.mycompany.myapp.services.ServiceEstimation;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.services.ServiceProposition;

import java.io.IOException;
import java.util.ArrayList;

public class ListEstimationForm extends BaseFormBack {

        public ListEstimationForm(Resources res) throws IOException {
                super("Estimation client", BoxLayout.y());
                Toolbar tb = new Toolbar(true);
                setToolbar(tb);
                getTitleArea().setUIID("Container");
                setTitle("Estimation");
                getContentPane().setScrollVisible(false);

                super.addSideMenu(res);

                Tabs swipe = new Tabs();
                Label spacer1 = new Label();
                Label spacer2 = new Label();

                addTab(swipe, spacer1);
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
                for(int iter = 0 ; iter < rbs.length ; iter++) {
                        rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
                        rbs[iter].setPressedIcon(selectedWalkthru);
                        rbs[iter].setUIID("Label");
                        radioContainer.add(rbs[iter]);
                }

                rbs[0].setSelected(true);
                swipe.addSelectionListener((i, ii) -> {
                        if(!rbs[ii].isSelected()) {
                                rbs[ii].setSelected(true);
                        }
                });

                Component.setSameSize(radioContainer, spacer1, spacer2);
                add(LayeredLayout.encloseIn(swipe, radioContainer));

                swipe.setUIID("Container");
                swipe.getContentPane().setUIID("Container");
                swipe.hideTabs();
                setTitle("List Estimation client ");
                setLayout(BoxLayout.yCenter());
                ArrayList<EstimationOffreLivre> list;
                list = new ArrayList<>();
                list = ServiceEstimation.getInstance().getAll();

                for ( EstimationOffreLivre c : list) {
                        Label sp2 = new Label("ID: " + "  " + c.getProposition().getIdProposition());
                        sp2.getAllStyles().setFgColor(0x00000);

                        Label spl = new Label("titrelivre: " + "  " + c.getProposition().getTitreLivre());
                        spl.getAllStyles().setFgColor(0x00000);
                        Label spl2 = new Label("editon : " + "  " + c.getProposition().getEditon());
                        spl2.getAllStyles().setFgColor(0x00000);
                        Label spl3 = new Label("descriptionEtat : " + "  " + c.getProposition().getDescriptionEtat());
                        spl3.getAllStyles().setFgColor(0x00000);
                        Label spl4 = new Label("Date: " + "  " + c.getProposition().getDateProposition());
                        spl4.getAllStyles().setFgColor(0x00000);
                        Label spl5 = new Label("point : " + "  " + c.getPointEstime());
                        spl4.getAllStyles().setFgColor(0x00000);


                        Button sup = new Button("Delete");
                        Button modif = new Button("modifier");


                        sup.addActionListener((evt) -> {

                                ServiceEstimation.getInstance().Supprimer(c.getIdEstimationOffreLivre());
                                Dialog.show("Alert", "Delete proposition ?", new Command("OK"));
                                Dialog.show("Success", "Estimation deleted successfully", new Command("OK"));
                                Message m = new Message("Your Estimation has been deleted successfully !");

                        });
                        modif.addActionListener((evt) -> {
                                new EditEstimationForm(res,c).show();

                        });
                        addAll(sp2,spl,spl2,spl3,spl4,spl5,sup,modif)
                        ;}
               // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        }

        private void addTab(Tabs swipe,  Label spacer) {
                int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

                Label overlay = new Label(" ", "ImageOverlay");

                Container page1 =
                        LayeredLayout.encloseIn(

                                overlay,
                                BorderLayout.south(
                                        BoxLayout.encloseY(

                                                spacer
                                        )
                                )
                        );

                swipe.addTab("", page1);
        }
















        }
