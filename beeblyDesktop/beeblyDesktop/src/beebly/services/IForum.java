/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import java.util.List;

/**
 *
 * @author emna
 */
public interface IForum <T>{
    public boolean ajout(T t);
    public boolean modifier(T t);
    public boolean supprimer(int id);
    public List<T> afficher();
}
