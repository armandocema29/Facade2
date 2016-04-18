package mx.edu.utng.facade;

/**
 * Created by NQ-TI005-L on 14/04/2016.
 */
public class CajaSaldo implements IAdapter {

    @Override
    public Double realizarRetiro(Double saldo, Double cantidad) {
        return saldo - cantidad;
    }
}
