/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package beebly.services;

import java.util.List;

/**
 *
 * @author khaled
 */
public interface IEvenement<T> {
    
    public void ajoutEvenement(T t);
    public void modifierEvenement(T t , int id);
    public void supprimerEvenement(int id);
    public List<T> afficherEvenements();

    
}
