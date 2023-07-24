package beebly.entity;

import beebly.entity.enums.EtatCloture;

public class ClotureAchat {

    @Override
    public String toString() {
        return "ClotureAchat{" +
                "idCloture=" + idCloture +
                ", DetailsLivraison=" + détailsLivraison +
                ", pointAchatFinale=" + pointAchatFinale +
                ", etatCloture=" + etatCloture +
                ", etatLivre='" + etatLivre + '\'' +
                '}';
    }



    public ClotureAchat(DétailsLivraison détailsLivraison, int pointAchatFinale, EtatCloture etatCloture, String etatLivre) {
        this.détailsLivraison = détailsLivraison;
        this.pointAchatFinale = pointAchatFinale;
        this.etatCloture = etatCloture;
        this.etatLivre = etatLivre;
    }
    private int idCloture;
    private DétailsLivraison détailsLivraison;
    private  int pointAchatFinale;
    private EtatCloture etatCloture;
    private String etatLivre;


    public ClotureAchat(int idCloture, DétailsLivraison détailsLivraison, int pointAchatFinale, EtatCloture etatCloture, String etatLivre) {
        this.idCloture = idCloture;
        this.détailsLivraison = détailsLivraison;
        this.pointAchatFinale = pointAchatFinale;
        this.etatCloture = etatCloture;
        this.etatLivre = etatLivre;
    }


    public ClotureAchat(int idCloture) {
        this.idCloture = idCloture;
    }

    public int getIdCloture() {
        return idCloture;
    }

    public void setIdCloture(int idCloture) {
        this.idCloture = idCloture;
    }

    public DétailsLivraison getDetailsLivraison() {
        return détailsLivraison;
    }

    public void setDetailsLivraison(DétailsLivraison détailsLivraison) {
        this.détailsLivraison = détailsLivraison;
    }

    public int getPointAchatFinale() {
        return pointAchatFinale;
    }

    public void setPointAchatFinale(int pointAchatFinale) {
        this.pointAchatFinale = pointAchatFinale;
    }

    public EtatCloture getEtatCloture() {
        return etatCloture;
    }

    public void setEtatCloture(EtatCloture etatCloture) {
        this.etatCloture = etatCloture;
    }

    public String getEtatLivre() {
        return etatLivre;
    }

    public void setEtatLivre(String etatLivre) {
        this.etatLivre = etatLivre;
    }




}
