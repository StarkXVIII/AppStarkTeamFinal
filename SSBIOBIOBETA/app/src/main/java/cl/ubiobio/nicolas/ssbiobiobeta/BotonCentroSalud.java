package cl.ubiobio.nicolas.ssbiobiobeta;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ListView;
import android.os.Handler;
import java.util.ArrayList;


/*Clase correspondiente a la opción de "Centros de salud", donde se listan los centros de salud de correspondientes a la provincia del Bío Bío con su respectiva ubicación*/
public class BotonCentroSalud extends AppCompatActivity {

    private MediaPlayer mp;


    /*Los centros de saludse cargan en un array de tipo CentroSalud con sus respectivos datos y coordenadas para poder mostrarlos*/
    static ArrayList<CentroSalud> centrosDeSalud;
    private CentroSalud centrouno;
    private CentroSalud centrodos;
    private CentroSalud centrotres;
    private CentroSalud centrocuatro;
    private CentroSalud centrocinco;
    private CentroSalud centroseis;
    private CentroSalud centrosiete;
    private CentroSalud centroocho;
    private CentroSalud centronueve;
    private CentroSalud centrodiez;
    private CentroSalud centroonce;
    private CentroSalud centrodoce;
    private CentroSalud centrotrece;
    private AdaptadorCentros adaptador;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton_centro_salud);
        centrosDeSalud = new ArrayList<>();
        centrouno= new CentroSalud();
        centrodos= new CentroSalud();
        centrotres= new CentroSalud();
        centrocuatro= new CentroSalud();
        centrocinco= new CentroSalud();
        centroseis= new CentroSalud();
        centrosiete= new CentroSalud();
        centroocho= new CentroSalud();
        centronueve= new CentroSalud();
        centrodiez= new CentroSalud();
        centroonce= new CentroSalud();
        centrodoce= new CentroSalud();
        centrotrece= new CentroSalud();
        lista=(ListView) findViewById(R.id.lista);
        arregloCentros();

        CardView btn4 = (CardView) findViewById(R.id.audio);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(BotonCentroSalud.this, R.raw.voz_centrosdesalud);
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

    public void arregloCentros(){



        centrouno.setLatitud(-37.26396517332799);
        centrouno.setLongitud(-72.70894288978565);
        centrouno.setNombre("Hospital de Laja");
        centrouno.setTipo("Hospital");

        centrodos.setLatitud(-37.72017747976133);
        centrodos.setLongitud(-72.24048722738715);
        centrodos.setNombre("Hospital de Mulchen");
        centrodos.setTipo("Hospital");

        centrotres.setLatitud(-37.507646090046);
        centrotres.setLongitud(-72.67705064572718);
        centrotres.setNombre("Hosp. de Nacimiento");
        centrotres.setTipo("Hospital");

        centrocuatro.setLatitud(-37.24029753985731);
        centrocuatro.setLongitud(-71.94249144372948);
        centrocuatro.setNombre("Hospital de Huepil");
        centrocuatro.setTipo("Hospital");

        centrocinco.setLatitud(-37.09646773819262);
        centrocinco.setLongitud(-72.55789529008226);
        centrocinco.setNombre("Hospital de Yumbel");
        centrocinco.setTipo("Hospital");

        centroseis.setLatitud(-37.66771548155897);
        centroseis.setLongitud(-72.01686339946335);
        centroseis.setNombre("Hosp. de Santa Bárbara");
        centroseis.setTipo("Hospital");

        centrosiete.setLatitud(-37.03863082798051);
        centrosiete.setLongitud(-72.39760816524732);
        centrosiete.setNombre("SAR Cabrero");
        centrosiete.setTipo("SAR");

        centroocho.setLatitud(-37.46391253623157);
        centroocho.setLongitud(-72.36150034639502);
        centroocho.setNombre("SAR Norte");
        centroocho.setTipo("SAR");

        centronueve.setLatitud(-37.45717366542049);
        centronueve.setLongitud(-72.34298031662847);
        centronueve.setNombre("SAPU Nor Oriente");
        centronueve.setTipo("SAPU");

        centrodiez.setLatitud(-37.47711475738601);
        centrodiez.setLongitud(-72.36535129973514);
        centrodiez.setNombre("Dos de Septiembre");
        centrodiez.setTipo("SAPU");

        centroonce.setLatitud(-37.485556);
        centroonce.setLongitud(-72.339167);
        centroonce.setNombre("Cesfam Paillihue");
        centroonce.setTipo("SAPU");

        centrodoce.setLatitud(-37.46588415795363);
        centrodoce.setLongitud(-72.58176564117416);
        centrodoce.setNombre("Cesfam Santa Fe");
        centrodoce.setTipo("SAPU");

        centrotrece.setLatitud(-37.46732655571086);
        centrotrece.setLongitud(-72.38350914312044);
        centrotrece.setNombre("Cesfam N. Horizonte");
        centrotrece.setTipo("SAPU");


        centrosDeSalud.add(centrouno);
        centrosDeSalud.add(centrodos);
        centrosDeSalud.add(centrotres);
        centrosDeSalud.add(centrocuatro);
        centrosDeSalud.add(centrocinco);
        centrosDeSalud.add(centroseis);
        centrosDeSalud.add(centrosiete);
        centrosDeSalud.add(centroocho);
        centrosDeSalud.add(centronueve);
        centrosDeSalud.add(centrodiez);
        centrosDeSalud.add(centroonce);
        centrosDeSalud.add(centrodoce);
        centrosDeSalud.add(centrotrece);

/*Se adaptan los datos a travez de un adaptador especial para listarlos en un listview ordenados*/
        adaptador = new AdaptadorCentros(centrosDeSalud, BotonCentroSalud.this);
        lista.setAdapter(adaptador);

    }
}
