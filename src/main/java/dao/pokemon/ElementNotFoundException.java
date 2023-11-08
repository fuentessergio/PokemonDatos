package dao.pokemon;

public class ElementNotFoundException extends Exception{
    public ElementNotFoundException (){
        super("No se ha encontrado el elemento");
    }
}
