/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package dao.pokemon;

import java.nio.file.NoSuchFileException;

/**
 *
 * @author Sergio Cuesta
 */
public class PokemonMain {

    public static void main(String[] args) throws NoSuchFileException {
        String archivoCSV = "pokemons.csv";



        Pokemon p1 = new Pokemon("Charizard", 70, 150, 85, 78, 109, 85, 100);
        Pokemon p2 = new Pokemon("Charmander", 70, 150, 85, 78, 109, 85, 100);
        PokemonDAOFile dao = new PokemonDAOFile();
        //dao.estaVacio();
        //dao.estaLLeno();
        /*try {
            dao.aniadir(p1);
            dao.aniadir(p2);
        } catch (NoMasPokemonsException e) {
            throw new RuntimeException(e);
        } catch (PokemonDuplicadoException e) {
            throw new RuntimeException(e);
        }*/
        // dao.eliminar(p2);
        // dao.pokemonCSV(archivoCSV,"Pikachu",50,100,60,40,60,80,90);
        //dao.imprimirPokemonCSV(archivoCSV);
        dao.imprimirPokemon("Charizard");
    }
}
