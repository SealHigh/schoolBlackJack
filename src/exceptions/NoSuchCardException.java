package exceptions;

/**
 * Created by Martin on 2016-09-26.
 */
public class NoSuchCardException extends RuntimeException {

    public  NoSuchCardException(String msg){
        super(msg);
    }

    public  NoSuchCardException(){
        super();
    }
}
