package cl.ubiobio.nicolas.ssbiobiobeta;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.text.Html;
import android.widget.ImageButton;

/*Clase "VisorLlamada" donde se cargar mediante un webView la pagina de solicitud de llamada del servicio de Salud Responde
* junto con un a progressBar que se muestramientras se carga el visor*/
public class VisorLlamar extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_llamar);

         /*Se crea un progressDialog para que se ejecute mientras se carga el formulario y desaparezca una vez
        cargado completamente */


        /*Se crea un webView el cual mostrará el formulario online para la solicitud de la llamada del
        servicio de Salud Responde, y se aplica el progressDialog antes
        * de mostrarlo*/
        WebView web = findViewById(R.id.visorweb);
        web.setWebViewClient(new MyWebViewClient());
        WebSettings settings =web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.loadUrl("http://eccnetserver.entelcallcenter.cl/minsalc2c/index.aspx");
        /*web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //elimina ProgressBar.
                progressDialog.dismiss();
            }
        });*/

        if(isConnected(VisorLlamar.this)){
            final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            progressDialog.setIcon(R.mipmap.ic_launcher);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();

            web.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    //elimina ProgressBar.
                    progressDialog.dismiss();
                }
            });
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
                            mp = MediaPlayer.create(VisorLlamar.this, R.raw.voz_mensaje);
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








    /*Funcion MyWebViewCliente que adminitra el url via string de una web*/
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }


}
