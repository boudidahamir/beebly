package com.mycompany.myapp.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.EstimationOffreLivre;
import com.mycompany.myapp.entities.Etat;
import com.mycompany.myapp.entities.PropositionLivre;
import com.mycompany.myapp.entities.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceEstimation {
    public ArrayList<EstimationOffreLivre> lists;
    public static ServiceEstimation instance;
    public boolean resultOK;
    private ConnectionRequest req;
    public static final String BASE_URL = "http://127.0.0.1:8000/estimationoffrelivreapi";


    private ServiceEstimation() {
        req = new ConnectionRequest();
    }

    /* Singleton patron de conception de creation */
    public static ServiceEstimation getInstance() {
        if (instance == null) {
            instance = new ServiceEstimation();
        }
        return instance;
    }

    public boolean add(EstimationOffreLivre estimationOffreLivre) {

        String url = BASE_URL + "/" +estimationOffreLivre.getProposition().getIdProposition()+ "/new?point="
                + estimationOffreLivre.getPointEstime();
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

    public boolean modify(EstimationOffreLivre estimationOffreLivre) {

        String url = BASE_URL + "/" +estimationOffreLivre.getProposition().getIdProposition()+ "/edit?point="
                + estimationOffreLivre.getPointEstime();
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


    public ArrayList<EstimationOffreLivre> parseEntitie(String jsonText) {
        try {
            lists = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) CategorieListJson.get("root");

            for (Map<String, Object> obj : list) {
                Map<String, Object> propositionObj = (Map<String, Object>) obj.get("idproposition");

                Map<String, Object> idUserObj = (Map<String, Object>) propositionObj.get("idclient");
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

                Map<String, Object> propostionobjet = (Map<String, Object>) obj.get("idproposition");

                PropositionLivre propositionLivre = new PropositionLivre();
                float idpropositionlivre = Float.parseFloat(propostionobjet.get("idpropositionlivre").toString());

                propositionLivre.setIdProposition(Math.round(idpropositionlivre));
                propositionLivre.setTitreLivre(propostionobjet.get("titrelivre").toString());
                propositionLivre.setEditon(propostionobjet.get("editon").toString());
                propositionLivre.setDescriptionEtat(propostionobjet.get("descriptionetat").toString());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

                propositionLivre.setDateProposition(format.parse(propostionobjet.get("dateproposition").toString()));
                propositionLivre.setIdUser(u);


                EstimationOffreLivre estimationOffreLivre=new EstimationOffreLivre(propositionLivre,
                        Math.round(Float.parseFloat(obj.get("pointestime").toString())), Etat.En_attente);
                lists.add(estimationOffreLivre);
                System.out.println("hhhhh"+list);

            }
        } catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lists;

    }


    public ArrayList<EstimationOffreLivre> getAll(){
        String url = BASE_URL+"/mesoffresapi";
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
