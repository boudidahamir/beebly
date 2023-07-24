package com.mycompany.myapp.services;

import com.codename1.io.*;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.PropositionLivre;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Topic;
import com.mycompany.myapp.entities.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceProposition {
    public ArrayList<PropositionLivre> lists;
    public static ServiceProposition instance;
    public boolean resultOK;
    private ConnectionRequest req;
    public static final String BASE_URL = "http://127.0.0.1:8000/propositionlivreapi";


    private ServiceProposition() {
        req = new ConnectionRequest();
    }

    /* Singleton patron de conception de creation */
    public static ServiceProposition getInstance() {
        if (instance == null) {
            instance = new ServiceProposition();
        }
        return instance;
    }

    public boolean add(PropositionLivre propositionLivre) {

        String url = BASE_URL + "/new?descriptionetat=" + propositionLivre.getDescriptionEtat() + "&editon="
                + propositionLivre.getEditon() + "&titrelivre=" + propositionLivre.getTitreLivre();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        System.out.println("" + resultOK);
        NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }

    public boolean modify(PropositionLivre propositionLivre) {
        String url = BASE_URL + "/" + propositionLivre.getIdProposition() + "/edit?descriptionetat=" + propositionLivre.getDescriptionEtat() + "&editon="
                + propositionLivre.getEditon() + "&titrelivre=" + propositionLivre.getTitreLivre();
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        System.out.println("" + resultOK);
        NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }


    public ArrayList<PropositionLivre> parseEntitie(String jsonText) {
        try {
            lists = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) CategorieListJson.get("root");

            for (Map<String, Object> obj : list) {
                Map<String, Object> idUserObj = (Map<String, Object>) obj.get("idclient");
                User u = new User();
                /* Parse User */
                float id = Float.parseFloat(idUserObj.get("id").toString());
                float cin = Float.parseFloat(idUserObj.get("cin").toString());
                float soldepoint = Float.parseFloat(idUserObj.get("soldepoint").toString());
                u.setCin((int) cin);
                u.setSoldepoint((int) soldepoint);
                u.setId((int) id);
                u.setNom(idUserObj.get("nom").toString());
                u.setPrenom(idUserObj.get("prenom").toString());

                u.setAdresse(idUserObj.get("adresse").toString());
                u.setAdrmail(idUserObj.get("adrmail").toString());
                u.setTel(idUserObj.get("tel").toString());
                u.setType(idUserObj.get("type").toString());
                u.setMdp(idUserObj.get("mdp").toString());

                /* End Parse User */
                PropositionLivre c = new PropositionLivre();
                float idpropositionlivre = Float.parseFloat(obj.get("idpropositionlivre").toString());

                c.setIdProposition(Math.round(idpropositionlivre));
                c.setTitreLivre(obj.get("titrelivre").toString());
                c.setEditon(obj.get("editon").toString());
                c.setDescriptionEtat(obj.get("descriptionetat").toString());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

                c.setDateProposition(format.parse(obj.get("dateproposition").toString()));

                c.setIdUser(u);

                lists.add(c);
                System.out.println("hhhhh"+list);

            }
        } catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lists;

    }


    public ArrayList<PropositionLivre> getAll(){
        String url = BASE_URL+"/liste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lists = parseEntitie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lists;
    }

    public ArrayList<PropositionLivre> getNonTraité(){
        String url = BASE_URL+"/listenontraité";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lists = parseEntitie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lists;
    }

    public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/"+id);

        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}