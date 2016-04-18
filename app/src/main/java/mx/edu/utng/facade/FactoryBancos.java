package mx.edu.utng.facade;

/**
 * Created by NQ-TI005-L on 11/04/2016.
 */
public class FactoryBancos {

    public Banco crearLogo(String banco){
        if(banco == "0")
            return new LogoBanamex();
        else if(banco == "1")
            return new LogoBancomer();
        else if(banco == "2")
            return new LogoBanorte();
        else if(banco == "3")
            return new LogoSantander();
        else if(banco == "4")
            return new LogoScotiabank();
        return null;
    }

}
