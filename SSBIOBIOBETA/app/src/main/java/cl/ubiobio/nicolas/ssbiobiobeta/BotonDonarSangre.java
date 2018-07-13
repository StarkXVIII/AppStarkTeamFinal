package cl.ubiobio.nicolas.ssbiobiobeta;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;

public class BotonDonarSangre extends AppCompatActivity {
    /*declaracion del audio de información*/
    private MediaPlayer mp;


    @Override
    /*Clase correspondiente al botón cinco de "Donar Sangre" que muestra información respectiva en caso de quererser donante*/
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton_donar_sangre);

        /*Botones disponibles en la pantalla*/
        CardView btn = (CardView) findViewById(R.id.ver);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivityDonar.class);
                startActivity(intent);
            }
        });


        CardView btn2 = (CardView) findViewById(R.id.llamar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:432336011"));
                startActivity(intent);
            }
        });

        CardView btn3 = findViewById(R.id.info);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder alertBuilder = new android.support.v7.app.AlertDialog.Builder(BotonDonarSangre.this);
                alertBuilder.setMessage(Html.fromHtml("<font color='#000000'>-Documento de identidad con nombre y RUN.<br>-Tienes entre 18 y 65 años.<br>-Has dormido al menos 5 horas.<br>-Pesas más de 50kg.<br>-Has comido en las últimas 5 horas.</font>"))
                        .setTitle(Html.fromHtml("<font color='#000000'>Requisitos</font>"))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                android.support.v7.app.AlertDialog dialog = alertBuilder.create();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        CardView btn4 = (CardView) findViewById(R.id.audio);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(BotonDonarSangre.this, R.raw.voz_donarsangre);
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


}
