package mx.edu.utng.facade;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by NQ-TI005-L on 10/04/2016.
 */
public class Facade {

    public Double realizarDeposito(Double cantidad, Double saldo, String banco){
        if (banco.equals("Banamex")){
            Banamex bank = new Banamex();
            saldo = bank.realizarDeposito(cantidad, saldo);
        } else if (banco.equals("Bancomer")){
            Bancomer bank = new Bancomer();
            saldo = bank.realizarDeposito(cantidad, saldo);
        } else if (banco.equals("Banorte")){
            Banorte bank = new Banorte();
            saldo = bank.realizarDeposito(cantidad, saldo);
        } else if (banco.equals("Santander")){
            Santander bank = new Santander();
            saldo = bank.realizarDeposito(cantidad, saldo);
        } else if (banco.equals("Scotiabank")){
            Scotiabank bank = new Scotiabank();
            saldo = bank.realizarDeposito(cantidad, saldo);
        } else {
            saldo = 0.0;
        }
        return saldo;
    }

    public Double realizarRetiro(Double cantidad, Double saldo, String banco){
        if (banco.equals("Banamex")){
            Banamex bank = new Banamex();
            saldo = bank.realizarRetiro(cantidad, saldo);
        } else if (banco.equals("Bancomer")){
            Bancomer bank = new Bancomer();
            saldo = bank.realizarRetiro(cantidad, saldo);
        } else if (banco.equals("Banorte")){
            Banorte bank = new Banorte();
            saldo = bank.realizarRetiro(cantidad, saldo);
        } else if (banco.equals("Santander")){
            Santander bank = new Santander();
            saldo = bank.realizarRetiro(cantidad, saldo);
        } else if (banco.equals("Scotiabank")){
            Scotiabank bank = new Scotiabank();
            saldo = bank.realizarRetiro(cantidad, saldo);
        } else {
            saldo = 0.0;
        }
        return saldo;
    }
}
