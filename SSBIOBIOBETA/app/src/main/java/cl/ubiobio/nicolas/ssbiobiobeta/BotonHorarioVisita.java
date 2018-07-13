package cl.ubiobio.nicolas.ssbiobiobeta;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.View;

public class BotonHorarioVisita extends AppCompatActivity {
    private MediaPlayer mp;
    @Override
    /*Clase correspondiente al botón cuatro de "Horario Visita", el cual se implementará en futuras versiones*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton_horario_visita);

        CardView btn4 = (CardView) findViewById(R.id.audio);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(BotonHorarioVisita.this, R.raw.voz_horariovisita);
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
