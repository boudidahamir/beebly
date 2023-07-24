package beebly.entity;

import beebly.entity.enums.EtatLivrasion;

public class DétailsLivraison {

    private int idDetailsLivraison;
    private EstimationOffreLivre estimationOffreLivre;

    public DétailsLivraison(EstimationOffreLivre estimationOffreLivre, String adresseLivraison, EtatLivrasion etatLivrasion) {
        this.estimationOffreLivre = estimationOffreLivre;
        AdresseLivraison = adresseLivraison;
        this.etatLivrasion = etatLivrasion;
    }

    private String AdresseLivraison;
    private EtatLivrasion etatLivrasion;


    @Override
    public String toString() {
        return "DétailsLivraison{" +
                "idDetailsLivraison=" + idDetailsLivraison +
                ", EstimationOffreLivre=" + estimationOffreLivre +
                ", AdresseLivraison='" + AdresseLivraison + '\'' +
                ", etatLivrasion=" + etatLivrasion +
                '}';
    }


    public DétailsLivraison(int idDetailsLivraison, EstimationOffreLivre estimationOffreLivre, String adresseLivraison, EtatLivrasion etatLivrasion) {
        this.idDetailsLivraison = idDetailsLivraison;
        this.estimationOffreLivre = estimationOffreLivre;
        AdresseLivraison = adresseLivraison;
        this.etatLivrasion = etatLivrasion;
    }


    public int getIdDetailsLivraison() {
        return idDetailsLivraison;
    }

    public void setIdDetailsLivraison(int idDetailsLivraison) {
        this.idDetailsLivraison = idDetailsLivraison;
    }

    public EstimationOffreLivre getEstimationOffreLivre() {
        return estimationOffreLivre;
    }

    public void setEstimationOffreLivre(EstimationOffreLivre estimationOffreLivre) {
        this.estimationOffreLivre = estimationOffreLivre;
    }

    public String getAdresseLivraison() {
        return AdresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        AdresseLivraison = adresseLivraison;
    }

    public EtatLivrasion getEtatLivrasion() {
        return etatLivrasion;
    }

    public void setEtatLivrasion(EtatLivrasion etatLivrasion) {
        this.etatLivrasion = etatLivrasion;
    }

}
