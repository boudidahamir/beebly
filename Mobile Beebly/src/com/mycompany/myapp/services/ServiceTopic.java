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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Topic;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author emna
 */
public class ServiceTopic {
    public ArrayList<Topic> lists;
    public static ServiceTopic instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/topic/api";
 private ServiceTopic() {
        req = new ConnectionRequest() ; 
         }
    /* Singleton patron de conception de creation */
    public static ServiceTopic getInstance() {
        if (instance == null)
        {
            instance = new ServiceTopic();
        }
         return instance;
    }
 
 public ArrayList<Topic> parseEntitie(String jsonText){
        try {
            lists= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
          
           for ( Map<String,Object> obj: list){
               Map<String,Object> idUserObj = (Map<String,Object>) obj.get("iduser");
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
               Topic c = new Topic();
             float idTopic = Float.parseFloat(obj.get("idtopic").toString());
                         
             c.setIdtopic((int)idTopic);
             c.setHide((int)Float.parseFloat(obj.get("hide").toString()));
             c.setNbsujet((int)Float.parseFloat(obj.get("nbsujet").toString()));
             c.setTitretopic(obj.get("titretopic").toString());
             //c.setPhoto(obj.get("photo").toString());
             //c.setAccepter(obj.get("status").toString());
             c.setDate(obj.get("date").toString());
             c.setDescription(obj.get("description").toString());
             c.setIduser(u);
            
             lists.add(c);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return lists;
 }
     public ArrayList<Topic> getAll(){
          String url = BASE_URL+"/topicApi";
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
        con.setUrl(BASE_URL+"/deleteTopic/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean add (TextField tfTitre,TextField  tfDescription, int idUser )
    { 
  
       String url = BASE_URL+"/addTopic?titretopic="+tfTitre.getText()+"&description="+tfDescription.getText()+"&iduser="+idUser;
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
           public boolean update (int id,TextField tfTitre,TextField tfDescription,int idUser )
    { 

       String url = BASE_URL+"/editTopicApi/"+id+"?titretopic="+tfTitre.getText()+"&description="+tfDescription.getText()+"&iduser="+idUser;
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }

}
