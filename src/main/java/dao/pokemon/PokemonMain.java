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

    public static void main(String[] args) throws NoSuchFileException, PokemonDuplicadoException, NoMasPokemonsException, ElementNotFoundException {
        PokemonDAOXML dao = new PokemonDAOXML("pokemons.xml");
        /*dao.aniadir(new Pokemon("Bulbasaur", 5, 45, 49, 49, 65, 65, 45));
        dao.aniadir(new Pokemon("Charmander", 5, 39, 52, 43, 60, 50, 65));
        dao.aniadir(new Pokemon("Squirtle", 5, 44, 48, 65, 50, 64, 43));*/
        dao.leerPokemons();
        //Pokemon p1 = new Pokemon("Bulbasaur", 5, 45, 49, 49, 65, 65, 45);
        //dao.eliminar(p1);






        //PokemonDAOFile pokemonDAO = new PokemonDAOFile("C:\\Users\\fuent\\OneDrive\\Documentos\\GitHub\\PokemonDatos\\pokemons.csv");


        //Pokemon p2 = new Pokemon("Jorge", 3, 90, 65, 48, 79, 45, 80);

        //pokemonDAO.estaVacio();
        /*if (!pokemonDAO.estaLLeno()) {
            System.out.println("La lista de Pokémon no está llena.");
        } else {
            System.out.println("La lista de Pokémon está llena.");
        }*/
        /*try {
            pokemonDAO.aniadir(p2);
        } catch (NoMasPokemonsException e) {
            throw new RuntimeException(e);
        } catch (PokemonDuplicadoException e) {
            throw new RuntimeException(e);
        }*/
        //pokemonDAO.eliminar(p2);
        //pokemonDAO.pokemonCSV("pokemons.csv","Jorge", 3, 90, 65, 48, 79, 45, 80);
        //pokemonDAO.imprimirPokemonCSV("pokemons.csv");
        //pokemonDAO.imprimirPokemon("Char");
        //pokemonDAO.leerPokemons();
    }
}
