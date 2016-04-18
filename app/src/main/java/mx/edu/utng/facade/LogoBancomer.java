package mx.edu.utng.facade;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by NQ-TI005-L on 11/04/2016.
 */
public class LogoBancomer extends AppCompatActivity implements Banco {
    public void crearLogo(){
        setContentView(R.layout.activity_main);
        ImageView logo= (ImageView)findViewById(R.id.imageView);
        logo.setImageResource(R.drawable.bancomer);
        logo.setMaxHeight(200);
        logo.setMaxWidth(500);
    }
}
