/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.pokemon;

/**
 *
 * @author Sergio Cuesta
 */
class PokemonDuplicadoException extends Exception {
    public PokemonDuplicadoException (){
        super("Ya existe un pokemon con ese nombre.");
    }

    /*public PokemonDuplicadoException (String mensaje){
        super(mensaje);
    }*/
}
