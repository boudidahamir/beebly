package com.mycompany.myapp.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.PropositionLivre;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.services.ServiceProposition;

import java.io.IOException;
import java.util.ArrayList;

public class ListPropositionAdminForm extends BaseFormBack {

        public ListPropositionAdminForm(Resources res) throws IOException {
                super("proposition client", BoxLayout.y());
                Toolbar tb = new Toolbar(true);
                setToolbar(tb);
                getTitleArea().setUIID("Container");
                setTitle("proposition");
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
                setTitle("List propositions client ");
                setLayout(BoxLayout.yCenter());
                ArrayList<PropositionLivre> list;
                list = new ArrayList<>();
                list = ServiceProposition.getInstance().getNonTraité();

                for ( PropositionLivre c : list) {
                        Label sp2 = new Label("ID: " + "  " + c.getIdProposition());
                        sp2.getAllStyles().setFgColor(0x00000);

                        Label spl = new Label("titrelivre: " + "  " + c.getTitreLivre());
                        spl.getAllStyles().setFgColor(0x00000);
                        Label spl2 = new Label("editon : " + "  " + c.getEditon());
                        spl2.getAllStyles().setFgColor(0x00000);
                        Label spl3 = new Label("descriptionEtat : " + "  " + c.getDescriptionEtat());
                        spl3.getAllStyles().setFgColor(0x00000);
                        Label spl4 = new Label("Date: " + "  " + c.getDateProposition());
                        spl4.getAllStyles().setFgColor(0x00000);


                        Button sup = new Button("Delete");
                        Button estimer = new Button("Estimer");


                        sup.addActionListener((evt) -> {
                                ServiceEvenement.getInstance().Supprimer(c.getIdProposition());
                                Dialog.show("Alert", "Delete proposition ?", new Command("OK"));
                                Dialog.show("Success", "proposition deleted successfully", new Command("OK"));
                                Message m = new Message("Your proposition has been deleted successfully !");

                        });
                        estimer.addActionListener((evt) -> {
                                new AddEstimationForm(res,c).show();

                        });
                        addAll(sp2,spl,spl2,spl3,spl4,sup,estimer)
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
