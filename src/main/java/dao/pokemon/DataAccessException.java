package dao.pokemon;

public class DataAccessException extends Exception {
    public DataAccessException (){
        super("No se ha podido acceder a los datos");
    }
}
