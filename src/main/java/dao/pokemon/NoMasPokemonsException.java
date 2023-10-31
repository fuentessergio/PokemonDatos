/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.pokemon;

/**
 *
 * @author Sergio Cuesta
 */
class NoMasPokemonsException extends Exception {
    public NoMasPokemonsException (){
        super("No se permiten mas pokemons, has alcanzado el limite");
    }
}
