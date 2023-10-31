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
        PokemonDAOFile pokemonDAO = new PokemonDAOFile("C:\\Users\\2DAM\\IdeaProjects\\Pokemon\\pokemons.csv");


        Pokemon p2 = new Pokemon("Jorge", 3, 90, 65, 48, 79, 45, 80);

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
        //pokemonDAO.leerPokemons("char");
    }
}
