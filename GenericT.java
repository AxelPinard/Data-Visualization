//This class creates a generic class type so that we can abstract
// our collections in order to hold various datatypes.

public class GenericT<T> {

    //Fields
    T obj;

    //Constructor
    GenericT(T obj) {this.obj = obj;}

    //Getter
    public T getObj() {return this.obj;}
}
