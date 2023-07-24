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
public interface ITicket<T> {
    
     public void ajoutTicket(T t);
    public void modifierTicket(T t , int id);
    public void supprimerTicket(int id);
    public List<T> afficherTicket();
    
}
