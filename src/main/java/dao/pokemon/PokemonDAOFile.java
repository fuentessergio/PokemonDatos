/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.pokemon;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergio Cuesta
 */
public class PokemonDAOFile implements PokemonDAO {
    private List<Pokemon> listaPokemon;
    private Pokemon pokemon = new Pokemon();

    public PokemonDAOFile (){
        listaPokemon = new ArrayList<>();
    }



    @Override
    public boolean estaVacio() {
        return listaPokemon.isEmpty();
    }

    @Override
    public boolean estaLLeno() {
        if(listaPokemon.size() >= Pokemon.LIMITE_POKEMONS){
          return true; // si esta lleno
        } else return false;
    }

    @Override
    public void aniadir(Pokemon pokemon) throws NoMasPokemonsException, PokemonDuplicadoException {
        //comprobar si no superamos el limite de 1010
        if(listaPokemon.size() >= Pokemon.LIMITE_POKEMONS){
            throw new NoMasPokemonsException();
        } else {
            // recorremos la lista y comprobamos si hay un pokemon con el mismo nombre
            for(Pokemon p : listaPokemon){
                if(p.getNombre().equals(pokemon.getNombre())){
                    throw new PokemonDuplicadoException();
                }
            }
        listaPokemon.add(pokemon);
            //System.out.println(listaPokemon);
        }
    }

    @Override
    public boolean eliminar(Pokemon pokemon) {
        for (Pokemon p : listaPokemon){
            if(p.getNombre().equals(pokemon.getNombre())) {
                listaPokemon.remove(p);
                //System.out.println(listaPokemon);
                return true;
            }
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
        // no he conseguido hacer lo de la cadena
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) !=null){
                String [] partes = linea.split(";");
                if(partes.length == 8){
                    // esto seria mejor hacerlo en un metodo a parte yo creo??
                    String nombre = partes [0];
                    pokemon.setNombre(nombre);
                    pokemon.setNivel(Integer.parseInt(partes[1]));
                    pokemon.setVida(Integer.parseInt(partes[2]));
                    pokemon.setAtaque(Integer.parseInt(partes[3]));
                    pokemon.setDefensa(Integer.parseInt(partes[4]));
                    pokemon.setAtaqueEspecial(Integer.parseInt(partes[5]));
                    pokemon.setDefensaEspecial(Integer.parseInt(partes[6]));
                    pokemon.setVelocidad(Integer.parseInt(partes[7]));
                    System.out.println(pokemon.toString());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (PokemonDuplicadoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void imprimirPokemon(String nombre) {
        for (Pokemon p : listaPokemon){
            if(p.getNombre().equals(nombre)){
                System.out.println(p.toString());
            }
        }
    }

    @Override
    public List<PokemonMain> leerPokemons() {
        return null;
    }

    @Override
    public List<PokemonMain> leerPokemons(String nombre) {
        return null;
    }


    
}
