
package dao.pokemon;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Completa la clase Pokemon para que sea un Java Bean que permita almacenar
 * los siguientes atributos:
 */
public class Pokemon implements Externalizable {

    private String nombre;
    private int nivel;
    private int vida;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;
    public final static int LIMITE_POKEMONS = 1010;

    private List<Pokemon> listaPokemon = new ArrayList<>();

    public Pokemon(){
        // constructor vacío, porque es un JavaBean
    }


    public Pokemon(String nombre, int nivel, int vida, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws PokemonDuplicadoException {
        if(nombre ==null && nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre del pokemon no puede ser nulo o estar vacío");
        }
        // Verifico si en mi lista ya hay un pokemon con ese nombre
        for (Pokemon p : listaPokemon){
            if(p.getNombre().equals(nombre)){
                throw new PokemonDuplicadoException();
            }
        }
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if(nivel<0 || nivel>100){
            throw new IllegalArgumentException("El Pokemon no puede tener nivel 0 o ser superior a 100");
        }
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if(vida<0 || vida>250){
            throw new IllegalArgumentException("El Pokemon no puede tener vida 0 o ser superior a 250");
        }
        this.vida = vida;
    }


    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if(ataque<5 || ataque>190){
            throw new IllegalArgumentException("El ataque no puede estar entre esos valores");
        }
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if(defensa<5 || defensa>250){
            throw new IllegalArgumentException("Valores para la defensa del pokemon incorrectos");
        }
        this.defensa=defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        if(ataqueEspecial<10 || ataqueEspecial>190){
            throw new IllegalArgumentException("Valores introducidos para el ataque especial del Pokemon no son válidos");
        }
        this.ataqueEspecial=ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        if(defensaEspecial<20 || defensaEspecial>250){
            throw new IllegalArgumentException("Valores introducidos incorrectos");
        }
        this.defensaEspecial = defensaEspecial;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        if(velocidad<5 || velocidad>200){
            throw new IllegalArgumentException("La velocidad del Pokemon introducida no es válida");
        }
        this.velocidad = velocidad;
    }


    @Override
    public String toString() {
        return "Nombre: " + getNombre() + "\n" +
                "Nivel: " + getNivel() + "\n" +
                "Vida: " + getVida() + "\n" +
                "Ataque: " + getAtaque() + "\n" +
                "Defensa: " + getDefensa() + "\n" +
                "Ataque especial: " + getAtaqueEspecial() + "\n" +
                "Defensa especial: " + getDefensaEspecial() + "\n" +
                "Velocidad: " + getVelocidad();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(nombre);
        out.writeInt(nivel);
        out.writeInt(vida);
        out.writeInt(ataque);
        out.writeInt(defensa);
        out.writeInt(ataqueEspecial);
        out.writeInt(defensaEspecial);
        out.writeInt(velocidad);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nombre = (String) in.readObject(); // puedo usar tambien in.readUTF();
        nivel = in.readInt();
        vida = in.readInt();
        ataque = in.readInt();
        defensa = in.readInt();
        ataqueEspecial = in.readInt();
        defensaEspecial = in.readInt();
        velocidad = in.readInt();
    }
}

