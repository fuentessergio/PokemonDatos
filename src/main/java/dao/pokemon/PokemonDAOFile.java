/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.pokemon;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergio Cuesta
 */
public class PokemonDAOFile implements PokemonDAO {
    private String archivoCSV;

    public PokemonDAOFile (String archivoCSV){
        this.archivoCSV=archivoCSV;
    }



    @Override
    public boolean estaVacio() {
        File archivo = new File(archivoCSV);
        if(!archivo.exists() && archivo.length() == 0){
            return true; // esta vacio
        } return false;
    }


    @Override
    public boolean estaLLeno() {
        List<Pokemon> listaPokemon = leerPokemons();
        if(listaPokemon != null && listaPokemon.size() >= Pokemon.LIMITE_POKEMONS){
          return true; // si esta lleno
        } else return false;
    }

    @Override
    public void aniadir(Pokemon pokemon) throws NoMasPokemonsException, PokemonDuplicadoException {
        List<Pokemon> listaPokemon = leerPokemons();
        for(Pokemon p : listaPokemon){
            if(p.getNombre().equals(pokemon.getNombre())){
                throw new PokemonDuplicadoException();
            }
        }
        listaPokemon.add(pokemon);
        guardarPokemons(listaPokemon);
    }

    @Override
    public boolean eliminar(Pokemon pokemon) {
        String nombre = pokemon.getNombre();
        List<Pokemon> listaPokemon = leerPokemons();
        if(listaPokemon.removeIf(p -> p.getNombre().equals(nombre))){ // elementos que cumpla la condicion se eliminan
            guardarPokemons(listaPokemon);
            return true; // se elimino
        }
        return false;
    }

    @Override
    public void pokemonCSV(String ruta, String nombre, int nivel, int vida, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad) {
        File archivoCSV = new File(ruta);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV,true))){
            String linea = String.format("%s;%d;%d;%d;%d;%d;%d;%d",
                    nombre, nivel, vida, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad); // da formato para ;
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void imprimirPokemonCSV(String ruta) throws NoSuchFileException {
        // no he conseguido hacer lo de la cadena "saur"
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) !=null){
                String [] partes = linea.split(";");
                if(partes.length == 8) {
                    // esto seria mejor hacerlo en un metodo a parte yo creo??
                    Pokemon pokemon = procesarLineaCSV(partes);
                    System.out.println(); // para separar cada pokemon
                    System.out.println(pokemon);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Pokemon procesarLineaCSV(String[] partes) {
        String nombre = partes[0];
        int nivel = Integer.parseInt(partes[1]);
        int vida = Integer.parseInt(partes[2]);
        int ataque = Integer.parseInt(partes[3]);
        int defensa = Integer.parseInt(partes[4]);
        int ataqueEspecial = Integer.parseInt(partes[5]);
        int defensaEspecial = Integer.parseInt(partes[6]);
        int velocidad = Integer.parseInt(partes[7]);

        Pokemon nuevoPokemon = new Pokemon(nombre, nivel, vida, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad);
        return nuevoPokemon;
        // System.out.println(nuevoPokemon);
    }

    @Override
    public void imprimirPokemon(String nombre) {
        List<Pokemon> listaPokemon = leerPokemons();
        for (Pokemon p : listaPokemon){
            if(p.getNombre().toLowerCase().contains(nombre.toLowerCase())){ // esto sirve para buscar por una letra o cadena "s" y sale todos los pokemon con s
                System.out.println(p);
                System.out.println();
            }
        }
    }

    @Override
    public List<Pokemon> leerPokemons() {
        List<Pokemon> listaPokemon = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) !=null){
                String [] partes = linea.split(";");
                if(partes.length == 8) {
                    // esto seria mejor hacerlo en un metodo a parte yo creo??
                    Pokemon pokemon = procesarLineaCSV(partes);
                    listaPokemon.add(pokemon);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listaPokemon;
    }

    @Override
    public List<Pokemon> leerPokemons(String nombre) {
        List<Pokemon> pokemons = leerPokemons();
        List<Pokemon> pokemonsFiltrados = new ArrayList<>();

        for (Pokemon pokemon : pokemons){ // recorro pokemons
            if(pokemon.getNombre().toLowerCase().contains(nombre.toLowerCase())){ // si pokemon de la lista contiene algo que metes en el string
                pokemonsFiltrados.add(pokemon);
            }
        }
        System.out.println(pokemonsFiltrados);
        return pokemonsFiltrados;
    }

    private void guardarPokemons (List<Pokemon> pokemons){
        try(PrintWriter pw = new PrintWriter(new FileWriter(archivoCSV))){
            for (Pokemon pokemon : pokemons){
                pw.println(pokemon.getNombre() + ";" + pokemon.getNivel() + ";" + pokemon.getVida() + ";" +
                pokemon.getAtaque() + ";" + pokemon.getDefensa() + ";" + pokemon.getAtaqueEspecial() + ";"
                + pokemon.getDefensaEspecial() + ";" + pokemon.getVelocidad());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    
}
