package mx.edu.utng.facade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ///----- Se declaran las variables que se utilizarán -----///
    private EditText txtCantidad;
    private Spinner spinner;
    private RadioButton rbnDepositar;
    private RadioButton rbnRetirar;
    private Button btnAceptar;
    private EditText txtBanamex;
    private EditText txtBancomer;
    private EditText txtBanorte;
    private EditText txtSantander;
    private EditText txtScotiabank;
    private TextView lblSaldo;
    private FactoryBancos factory;
    private ImageView logo;

    private CajaSaldo cajaSaldo=new CajaSaldo();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///----- Se relaciona la clase con el layout -----///
        setContentView(R.layout.activity_main);
        ///----- Se manda llamar al metódo para inicializar los componentes -----///
        initComponents();
    }

    ///----- Obtener inicializar componentes -----///
    private void initComponents(){
        ///----- Se inicializa el spinner ------///
        spinner = (Spinner) findViewById(R.id.spinner);
        ///----- Se manda llamar el método que asigna datos y funcionalidad al spinner -----///
        spinnerMethod();
        ///----- Se crea una instancia de la clase FactoryBancos para asignar la imagen por default -----///
        factory = new FactoryBancos();
        factory.crearLogo("1");
        ///----- Relacionar variables con campos -----///
        txtCantidad = (EditText) findViewById(R.id.txt_Cantidad);
        rbnDepositar = (RadioButton) findViewById(R.id.rbnDepositar);
        rbnRetirar = (RadioButton) findViewById(R.id.rbnRetirar);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        txtBanamex = (EditText) findViewById(R.id.txt_banamex);
        txtBancomer = (EditText) findViewById(R.id.txt_bancomer);
        txtBanorte = (EditText) findViewById(R.id.txt_banorte);
        txtSantander = (EditText) findViewById(R.id.txt_santander);
        txtScotiabank = (EditText) findViewById(R.id.txt_scotiabank);
        lblSaldo = (TextView) findViewById(R.id.lblSaldo);
        ///----- Ocultar EdiText y TextView que no son por default -----///
        txtBancomer.setVisibility(EditText.INVISIBLE);
        txtBanorte.setVisibility(EditText.INVISIBLE);
        txtSantander.setVisibility(EditText.INVISIBLE);
        txtScotiabank.setVisibility(EditText.INVISIBLE);
        ///----- Agregar función al botoón -----///
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            Double cantidad;
            Double saldo;
            public void onClick(View v) {
                ///----- Se instancia la clase Facade -----///
                Facade fachada = new Facade();
                ///----- Se obtiene el valor del campo Cantidad -----///
                if (txtCantidad.getText().toString().equals(""))
                    cantidad = 0.0;
                else
                    cantidad = Double.parseDouble(txtCantidad.getText().toString());
                ///----- Se hace visible la etiqueta de saldo -----///
                lblSaldo.setVisibility(View.VISIBLE);
                ///----- Se limpia el campo de Cantidad -----///
                txtCantidad.setText("");
                ///----- Se obtiene el valor seleccionado del spinner -----///
                String bancoSelect = spinner.getSelectedItem().toString();
                ///----- Se obtien el saldo actual dependiendo del banco sleccionado -----///
                if (bancoSelect.toString().equals("Banamex"))
                    saldo = Double.parseDouble(txtBanamex.getText().toString());
                else if (bancoSelect.toString().equals("Bancomer"))
                    saldo = Double.parseDouble(txtBancomer.getText().toString());
                else if (bancoSelect.toString().equals("Banorte"))
                    saldo = Double.parseDouble(txtBanorte.getText().toString());
                else if (bancoSelect.toString().equals("Santander"))
                    saldo = Double.parseDouble(txtSantander.getText().toString());
                else if (bancoSelect.toString().equals("Scotiabank"))
                    saldo = Double.parseDouble(txtScotiabank.getText().toString());
                else
                    saldo = 0.0;
                ///----- Se realiza el deposito o retiro de efectivo -----///
                if (rbnDepositar.isChecked())
                    ///----- Se realiza el deposito con FACADE -----///
                    saldo = fachada.realizarDeposito(cantidad, saldo, bancoSelect);
                else
                    ///----- Se realiza el retiro con ADAPTER -----///
                    saldo = cajaSaldo.realizarRetiro(saldo, cantidad);
                ///----- Dependiendo el banco seleccionado se muestra el saldo -----///
                if (bancoSelect.toString().equals("Banamex"))
                    txtBanamex.setText(saldo.toString());
                else if (bancoSelect.toString().equals("Bancomer"))
                    txtBancomer.setText(saldo.toString());
                else if (bancoSelect.toString().equals("Banorte"))
                    txtBanorte.setText(saldo.toString());
                else if (bancoSelect.toString().equals("Santander"))
                    txtSantander.setText(saldo.toString());
                else if (bancoSelect.toString().equals("Scotiabank"))
                    txtScotiabank.setText(saldo.toString());
            }
        });
    }

    ///----- Funcionalidad del Spinner -----///
    public void spinnerMethod(){
        ///----- Se relaciona la variable spinner con el layout -----///
        spinner = (Spinner) findViewById(R.id.spinner);
        ///----- Se asigna la lista de bancos al spinner -----///
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bancos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ///----- Agregar funcionalidad a Spinner al ser seleccionado -----///
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ///----- Se crea una instancia de la clase FactoryBancos -----///
                //factory.crearLogo(String.valueOf(position));
                cambiarImagen(String.valueOf(position));
                ///----- Se obtien el saldo actual dependiendo del banco sleccionado -----///
                if (position == 0) {
                    txtBanamex.setVisibility(EditText.VISIBLE);
                    txtBancomer.setVisibility(EditText.GONE);
                    txtBanorte.setVisibility(EditText.GONE);
                    txtSantander.setVisibility(EditText.GONE);
                    txtScotiabank.setVisibility(EditText.GONE);
                } else if (position == 1) {
                    txtBanamex.setVisibility(EditText.GONE);
                    txtBancomer.setVisibility(EditText.VISIBLE);
                    txtBanorte.setVisibility(EditText.GONE);
                    txtSantander.setVisibility(EditText.GONE);
                    txtScotiabank.setVisibility(EditText.GONE);
                } else if (position == 2) {
                    txtBanamex.setVisibility(EditText.GONE);
                    txtBancomer.setVisibility(EditText.GONE);
                    txtBanorte.setVisibility(EditText.VISIBLE);
                    txtSantander.setVisibility(EditText.GONE);
                    txtScotiabank.setVisibility(EditText.GONE);
                } else if (position == 3) {
                    txtBanamex.setVisibility(EditText.GONE);
                    txtBancomer.setVisibility(EditText.GONE);
                    txtBanorte.setVisibility(EditText.GONE);
                    txtSantander.setVisibility(EditText.VISIBLE);
                    txtScotiabank.setVisibility(EditText.GONE);
                } else if (position == 4) {
                    txtBanamex.setVisibility(EditText.GONE);
                    txtBancomer.setVisibility(EditText.GONE);
                    txtBanorte.setVisibility(EditText.GONE);
                    txtSantander.setVisibility(EditText.GONE);
                    txtScotiabank.setVisibility(EditText.VISIBLE);
                }

            }

            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    public void cambiarImagen(String position){
        ImageView logo = (ImageView)findViewById(R.id.imageView);
        if (position == "0"){
            logo.setImageResource(R.drawable.banamex);
        } else if (position == "1"){
            logo.setImageResource(R.drawable.bancomer);
        } else if (position == "2"){
            logo.setImageResource(R.drawable.banorte);
        } else if (position == "3"){
            logo.setImageResource(R.drawable.santander);
        } else if (position == "4"){
            logo.setImageResource(R.drawable.scotiabank);
        }
        logo.setMaxHeight(200);
        logo.setMaxWidth(500);
    }

}
