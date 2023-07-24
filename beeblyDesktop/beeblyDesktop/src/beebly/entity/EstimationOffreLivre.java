package beebly.entity;

import beebly.entity.enums.Etat;

public class EstimationOffreLivre {
    public EstimationOffreLivre(int idEstimationOffreLivre, PropositionLivre propositionLivre, int pointEstime, Etat etat) {
        this.idEstimationOffreLivre = idEstimationOffreLivre;
        this.propositionLivre = propositionLivre;
        this.pointEstime = pointEstime;
        this.etat = etat;
    }

    private int  idEstimationOffreLivre;

    public int getIdEstimationOffreLivre() {
        return idEstimationOffreLivre;
    }

    public void setIdEstimationOffreLivre(int idEstimationOffreLivre) {
        this.idEstimationOffreLivre = idEstimationOffreLivre;
    }

    @Override
    public String toString() {
        return "EstimationOffreLivre{" +
                "idEstimationOffreLivre=" + idEstimationOffreLivre +
                ", propositionLivre=" + propositionLivre +
                ", pointEstime=" + pointEstime +
                ", etat=" + etat +
                '}';
    }

    public PropositionLivre getProposition() {
        return propositionLivre;
    }

    public void setProposition(PropositionLivre propositionLivre) {
        this.propositionLivre = propositionLivre;
    }

    public int getPointEstime() {
        return pointEstime;
    }

    public void setPointEstime(int pointEstime) {
        this.pointEstime = pointEstime;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public EstimationOffreLivre(PropositionLivre propositionLivre, int pointEstime, Etat etat) {
        this.propositionLivre = propositionLivre;
        this.pointEstime = pointEstime;
        this.etat = etat;
    }

    private PropositionLivre  propositionLivre;
    private int pointEstime;
    private Etat etat;
}
