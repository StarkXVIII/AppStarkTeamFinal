package cl.ubiobio.nicolas.ssbiobiobeta;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

public class BotonConsultaPaciente extends AppCompatActivity implements View.OnClickListener{

    public static EditText rutParte1,rutParte2,folioPaciente;
    public static String rutText,digitoVerificador,folioText;
    private MediaPlayer mp;
    @Override
    /*Clase correspondiente al botón dos de "Consulta estado paciente", el cual se implementará en futuras versiones*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton_consulta_paciente);

        CardView botonConsultar = findViewById(R.id.cardViewFolio);

        botonConsultar.setOnClickListener(this);
        rutParte1 = findViewById(R.id.rutPacienteParteUno);
        rutParte2 = findViewById(R.id.rutPacienteParteDos);
        folioPaciente= findViewById(R.id.folioPaciente);

        CardView btn4 = (CardView) findViewById(R.id.audio);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(BotonConsultaPaciente.this, R.raw.voz_estadopaciente);
                mp.start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 5000);
            }
        });

    }

    /*
   En esta funcion creamos un Intent el cual una vez ingresados los datos
   en los cuadros editables, se asignaran a las variables String para depues enviarse al
   servidor para realizar la consulta, se creo un intent para lanzar otra activity la cual
   mostrara en pantalla los datos recogidos de la consulta.
    */
    @Override
    public void onClick(View v) {
        folioText=folioPaciente.getText().toString();
        rutText= rutParte1.getText().toString();
        digitoVerificador=rutParte2.getText().toString();
        if(!rutText.equals("")||!digitoVerificador.equals("")||!folioText.equals("")){
            if (analizar(digitoVerificador)){
                if (!rutText.equals("")||!folioText.equals("")){
                    if(rutText.length()==7||rutText.length()==8){
                        if(!folioText.equals("")){
                            generateToast("No hay acceso a la base de datos.");
                        }else{
                            generateToast("Debe ingresar un folio.");
                        }

                    }else{
                        if(rutText.length()<7||rutText.length()>8){
                            generateToast("Rut erróneo.");
                        }
                    }
                }else{
                    generateToast("Necesita llenar todos los campos.");
                }
            }else{
                generateToast("Código verificador erróneo.");
            }
        }else{
            generateToast("Necesita llenar todos los campos.");
        }
    }

    private boolean analizar(String dv){
        boolean result = false;
        String [] arr = {"0","1","2","3","4","5","6","7","8","9","k","K"};
        for(String a : arr){
            if(dv.equals(a)){
                result = true;
                break;
            }
        }
        return result;
    }

    /*Función generateToast para mostrar mensajes temporales al momento de ejecutar el serviceFarmacias*/
    private void generateToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }
}


