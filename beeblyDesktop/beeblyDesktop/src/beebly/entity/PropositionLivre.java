package beebly.entity;

import java.util.Date;
import java.util.Objects;

public class PropositionLivre {


  public PropositionLivre(int idProposition, Client client, String titreLivre, String editon, String descriptionEtat) {
    this.idProposition = idProposition;
    this.client = client;
    this.titreLivre = titreLivre;
    this.editon = editon;
    this.descriptionEtat = descriptionEtat;
  }

  public PropositionLivre(Client client, String titreLivre, String editon, String descriptionEtat) {
    this.client = client;
    this.titreLivre = titreLivre;
    this.editon = editon;
    this.descriptionEtat = descriptionEtat;
  }

  private int  idProposition;

  public int getIdProposition() {
    return idProposition;
  }

  public void setIdProposition(int idProposition) {
    this.idProposition = idProposition;
  }

  public Client getClient() {
    return client;
  }

  public void setIdClient(Client client) {
    this.client = client;
  }

  public String getTitreLivre() {
    return titreLivre;
  }

  public void setTitreLivre(String titreLivre) {
    this.titreLivre = titreLivre;
  }

  public String getEditon() {
    return editon;
  }

  public void setEditon(String editon) {
    this.editon = editon;
  }

  public String getDescriptionEtat() {
    return descriptionEtat;
  }

  public void setDescriptionEtat(String descriptionEtat) {
    this.descriptionEtat = descriptionEtat;
  }

  public Date getDateProposition() {
    return dateProposition;
  }

  public void setDateProposition(Date dateProposition) {
    this.dateProposition = dateProposition;
  }





  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PropositionLivre)) return false;
    PropositionLivre that = (PropositionLivre) o;
    return idProposition == that.idProposition;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idProposition);
  }

  public PropositionLivre(int idProposition, Client client, String titreLivre, String editon, String descriptionEtat, Date dateProposition) {
    this.idProposition = idProposition;
    this.client = client;
    this.titreLivre = titreLivre;
    this.editon = editon;
    this.descriptionEtat = descriptionEtat;
    this.dateProposition = dateProposition;
  }

  @Override
  public String toString() {
    return "PropositionLivre{" +
            "idProposition=" + idProposition +
            ", client=" + client +
            ", titreLivre='" + titreLivre + '\'' +
            ", editon='" + editon + '\'' +
            ", descriptionEtat='" + descriptionEtat + '\'' +
            ", dateProposition=" + dateProposition +
            '}';
  }

  private Client  client;
  private String titreLivre;
  private String editon;
  private String descriptionEtat;
  private Date dateProposition;

}
