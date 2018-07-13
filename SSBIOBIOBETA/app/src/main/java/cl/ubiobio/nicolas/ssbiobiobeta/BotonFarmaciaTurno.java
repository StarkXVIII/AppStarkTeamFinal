package cl.ubiobio.nicolas.ssbiobiobeta;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;
import java.util.ArrayList;


/*Clase correspondiente al botón tres de "Farmacias de turno", donde se listan las farmacias de turno en el horario actual
* correspondientes a la provincia del Bío Bío con su respectiva ubicación*/
public class BotonFarmaciaTurno extends AppCompatActivity {

    /*Declaración de variables a utilizar para extraer las farmacias de turno de la web, almacenarlas y listarlas
    * donde se declara un Array con objetos tipo farmacia para almacenar las farmacias de turno;
     * Un Array tipo string que contiene los nombres de las comunas de la provincia del Bío Bío para filtrarlas
     * al momento de extraer de la web; El adaptador para formatear los datos y su correspondiente ListView*/
    static ArrayList<Farmacia> farmaciasDeTurno;
    private ArrayList<String> biobio;
    private Adaptador adaptador;
    private ListView lista;
    private RelativeLayout progressbar;
    private MediaPlayer mp;
    private MediaPlayer mpdos;


    @Override
    /*Función on create donde se integran las funciones que ejecutará el botón Tres*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton_farmacia_turno);
        progressbar = findViewById(R.id.progressBar1);
        /*Se llena el array biobio con los nombres de las ciudades de la provincia del Bío Bío*/
        farmaciasDeTurno = new ArrayList<>();
        biobio = new ArrayList<>();
        biobio.add("YUMBEL");
        biobio.add("CABRERO");
        biobio.add("LAJA");
        biobio.add("SAN ROSENDO");
        biobio.add("LOS ANGELES");
        biobio.add("NACIMIENTO");
        biobio.add("NEGRETE");
        biobio.add("QUILLECO");
        biobio.add("TUCAPEL");
        biobio.add("ANTUCO");
        biobio.add("SANTA BARBARA");
        biobio.add("QUILACO");
        biobio.add("MULCHEN");

        CardView btn4 = (CardView) findViewById(R.id.audio);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpdos = MediaPlayer.create(BotonFarmaciaTurno.this, R.raw.voz_farmaciasdeturno);
                mpdos.start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 5000);
            }
        });

if(isConnected(BotonFarmaciaTurno.this)){
    lista=(ListView) findViewById(R.id.lista);
    serviceFarmacias();
}else{
    android.support.v7.app.AlertDialog.Builder alertBuilder = new android.support.v7.app.AlertDialog.Builder(this);
    alertBuilder.setMessage(Html.fromHtml("<font color='#000000'>Para usar algunas funciones de la aplicación, necesitas estar conectado a internet.</font>"))
            .setTitle(Html.fromHtml("<font color='#000000'>No hay conexión a internet</font>"))
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            })
            .setNegativeButton("AUDIO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mp = MediaPlayer.create(BotonFarmaciaTurno.this, R.raw.voz_mensaje);
                    mp.start();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 5000);
                }
            });
    android.support.v7.app.AlertDialog dialog = alertBuilder.create();
    dialog.setCancelable(false);
    dialog.setCanceledOnTouchOutside(false);
    dialog.show();

}


    }


    /*Función serviceFarmacias que extrae los datos de la web donde están contenidas las farmacias a nivel nacional
    * que se encuentran en turno al momento de la consulta, estás se extraen mediante un JSON, que convierte las farmacias
    * en una lista de objetos con sus atributos y los guarda en un array llamado responseJson, luego se filtran los datos
    * segun las comunas de la provincia dl Bío Bío contenidas en el Array "biobio" y por el codigo obtenido del atributo
    * "fk_region=10", las farmacias correspondientes se guardan en el array "farmaciasdeTurno", luego se le aplica el
    * adaptador al array y se setean al listview correspoondiente*/
    private void serviceFarmacias(){
        progressbar.setVisibility(View.VISIBLE);
        Log.d("LOG WS", "entre");
        String WS_URL = "http://farmanet.minsal.cl/index.php/ws/getLocalesTurnos";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(
                Request.Method.GET,
                WS_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray responseJson = new JSONArray(response);

                            for(int i = 0; i < responseJson.length(); i++){
                                JSONObject o = responseJson.getJSONObject(i);
                                if(o.getString("fk_region").equals("10") && biobio.contains(o.getString("comuna_nombre"))){
                                    Log.d("LOG","uno");
                                    Farmacia far = new Farmacia();

                                    far.setFecha(o.getString("fecha"));
                                    far.setLocal_id(o.getString("local_id"));
                                    far.setRegion_id(o.getString("fk_region"));
                                    far.setComuna_id(o.getString("fk_comuna"));
                                    far.setLocalidad_id(o.getString("fk_localidad"));
                                    far.setNombre_farmacia(o.getString("local_nombre"));
                                    far.setNombre_comuna(o.getString("comuna_nombre"));
                                    far.setDireccion_farmacia(o.getString("local_direccion"));
                                    far.setHorario_apertura(o.getString("funcionamiento_hora_apertura"));
                                    far.setHorario_cierre(o.getString("funcionamiento_hora_cierre"));
                                    far.setTelefono(o.getString("local_telefono"));
                                    String lat = o.getString("local_lat");
                                    String lng = o.getString("local_lng");

                                    try{
                                        far.setLatitud(Double.parseDouble(lat));
                                        far.setLongitud(Double.parseDouble(lng));
                                    }catch (NumberFormatException e){
                                        far.setLatitud(0);
                                        far.setLongitud(0);
                                    }
                                    farmaciasDeTurno.add(far);
                                }
                            }
                            adaptador = new Adaptador(farmaciasDeTurno, BotonFarmaciaTurno.this);
                            lista.setAdapter(adaptador);
                            Log.d("LOG", "cantidad: " + farmaciasDeTurno.size());
                            progressbar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG WS", error.toString());
                progressbar.setVisibility(View.GONE);
                generateToast("Error en el WEB Service");
            }
        }
        );
        requestQueue.add(request);
    }
    /*Función generateToast para mostrar mensajes temporales al momento de ejecutar el serviceFarmacias*/
    private void generateToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }
}
