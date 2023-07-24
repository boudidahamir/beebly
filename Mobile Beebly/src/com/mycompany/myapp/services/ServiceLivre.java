/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Livre;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sahar
 */
public class ServiceLivre {

    public ArrayList<Livre> lists;
    public static ServiceLivre instance;
    public boolean resultOK;
    private ConnectionRequest req;
    public static final String BASE_URL = "http://127.0.0.1:8000/api/livre";

    private ServiceLivre() {
        req = new ConnectionRequest();
    }

    /* Singleton patron de conception de creation */
    public static ServiceLivre getInstance() {
        if (instance == null) {
            instance = new ServiceLivre();
        }
        return instance;
    }

    public ArrayList<Livre> parseEntitie(String jsonText) throws ParseException {
    ArrayList<Livre> lists = new ArrayList<>();

    try {
        JSONParser parser = new JSONParser();
        Map<String, Object> LivresListJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) LivresListJson.get("root");

        for (Map<String, Object> obj : list) {
            Map<String, Object> idUserObj = (Map<String, Object>) obj.get("user");
            User u = new User();
            
            /* Parse User */
              float id = Float.parseFloat(idUserObj.get("id").toString());
             float cin = Float.parseFloat(idUserObj.get("cin").toString());
             float soldepoint = Float.parseFloat(idUserObj.get("soldepoint").toString());
               u.setCin((int)cin);
                u.setSoldepoint((int)soldepoint);
             u.setId((int)id);
            u.setNom(idUserObj.get("nom").toString());
            u.setPrenom(idUserObj.get("prenom").toString());
            u.setAdresse(idUserObj.get("adresse").toString());
            u.setAdrmail(idUserObj.get("adrmail").toString());
            u.setTel(idUserObj.get("tel").toString());
            u.setType(idUserObj.get("type").toString());
            u.setMdp(idUserObj.get("mdp").toString());
            /* End Parse User */
            
            Livre c = new Livre();
            float idlivre = Float.parseFloat(obj.get("id").toString());
            float note = Float.parseFloat(obj.get("note").toString());
            float prix = Float.parseFloat(obj.get("prix").toString());
            
            c.setNote((int)note);
            c.setPrix((int)prix);
            c.setId((int)idlivre);
            c.setDescriptionEtatLivre(obj.get("descriptionetatlivre").toString());
            c.setCategorie(obj.get("categorie").toString());
            c.setTitre(obj.get("titre").toString());

            String dateStr = obj.get("datepublication").toString();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateStr);
            c.setDatePublication(date);
            c.setImage(obj.get("image").toString());
            c.setIdUser(u);

            lists.add(c);
        }
    } catch (IOException ex) {
        // Handle the exception
    }

    return lists;
}


    public ArrayList<Livre> getAll() {
        String url = BASE_URL + "/livreApi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    lists = parseEntitie(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lists;
    }

    public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL + "/deleteLivre/" + id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public boolean add(TextField tfTitre, TextField tfCategorie, TextField tfPrix, TextField tfDescriptionEtatLivre, int idUser) {

        String url = BASE_URL + "/addLivre?titre=" + tfTitre.getText() + "&categorie=" + tfCategorie.getText() + "&prix=" + tfPrix.getText() + "&descriptionEtatLivre=" + tfDescriptionEtatLivre.getText() + "&idUser=" + idUser;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        System.out.println("" + resultOK);
        NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }

    public boolean update(int id, TextField tfTitre, TextField tfCategorie, TextField tfPrix, TextField tfDescriptionEtatLivre, int idUser) {

        String url = BASE_URL + "/editLivreApi/" + id + "?titre=" + tfTitre.getText() + "&categorie=" + tfCategorie.getText() + "&prix=" + tfPrix.getText() + "&descriptionEtatLivre=" + tfDescriptionEtatLivre.getText() + "&idUser=" + idUser;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        System.out.println("" + resultOK);
        NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }

}
