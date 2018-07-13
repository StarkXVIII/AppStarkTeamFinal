package cl.ubiobio.nicolas.ssbiobiobeta;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorCentros extends BaseAdapter {

    private ArrayList<CentroSalud> listaCentros;
    private Context context;

    /*Constructor del Adaptador y variables que recibirá*/
    public AdaptadorCentros(ArrayList<CentroSalud> listaCentros, Context context) {
        this.listaCentros = listaCentros;
        this.context = context;
    }
    /*Función que obtiene la cantidad de objetos en el array*/
    @Override
    public int getCount() {
        return listaCentros.size();
    }

    /*Obtiene la posicion de cierto objeto del array*/
    @Override
    public Object getItem(int position) {
        return listaCentros.get(position);
    }

    /*Obtiene el item en la posicion i del arreglo*/
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*Funcion que infla el listview para su respectivo rellenado y lo devuelve adaptado*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(
                    R.layout.adaptador_centros,
                    parent,
                    false);
        }

        /*Objeto farmacia del cual se extraeran los atributos requeridos en los TextView*/
        final CentroSalud centro = (CentroSalud) getItem(position);

        /*Seteo de los datos requeridos hacia los TextView correspoondientes*/
        TextView nombre =(TextView) convertView.findViewById(R.id.nombre);
        TextView tipo =(TextView) convertView.findViewById(R.id.tipo);


        /*Obtención de los datos en los textview declarados mediante los metodos de la clase Farmacia*/
        nombre.setText(centro.getNombre());
        tipo.setText(centro.getTipo());


        /*Se guarda la latitud y longitud de las farmacias extraidas para lanzarlas como marcador en la app de
         * navegación preferida por el usuario ej:waze, maps,etc, para facilitar la navegación en caso de no saber llegar */

        CardView btn = (CardView) convertView.findViewById(R.id.ir);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:"+centro.getLatitud()+","+centro.getLongitud()+"?z=16&q="+centro.getLatitud()+","+centro.getLongitud());
                context.startActivity( new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        CardView btn2 = (CardView) convertView.findViewById(R.id.ver);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivityCentros.class);
                intent.putExtra("latitud",centro.getLatitud());
                intent.putExtra("longitud",centro.getLongitud());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    /*Reconoce las secuencias char y los retorna de manera individual en caso de su uso*/
    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
