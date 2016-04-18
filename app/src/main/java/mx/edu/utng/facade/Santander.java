package mx.edu.utng.facade;

import android.widget.EditText;

/**
 * Created by NQ-TI005-L on 10/04/2016.
 */
public class Santander {
    public Double realizarDeposito(Double cantidad, Double saldo){
        saldo = cantidad + saldo;
        return saldo;
    }

    public Double realizarRetiro(Double cantidad, Double saldo){
        saldo =  saldo - cantidad;
        return  saldo;
    }
}
