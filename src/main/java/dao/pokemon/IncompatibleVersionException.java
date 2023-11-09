package dao.pokemon;

public class IncompatibleVersionException extends Exception {
    public IncompatibleVersionException(){
        super("La versión es incompatible");
    }
}
