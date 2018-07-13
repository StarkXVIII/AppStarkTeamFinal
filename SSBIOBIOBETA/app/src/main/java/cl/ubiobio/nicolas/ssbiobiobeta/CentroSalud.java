package cl.ubiobio.nicolas.ssbiobiobeta;

/**
 * Created by Nicolas on 27/06/2018.
 */

/*Clase CentroSalud que corresponde a un objeto con los atributos de un centro de salud requeridos para listarlos en la opción
* de Centros de salud*/
public class CentroSalud {

   /*Declaración de atributos de un centro de salud presentes porporcionados por el SSBIOBIO*/

    private double latitud;
    private double longitud;
    private String nombre;
    private String tipo;

    public CentroSalud(double latitud, double longitud, String nombre, String tipo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    /*Funciones getter, setter y contructor de la clase CentroSalud de cada uno de los atributos para su eventual extración o
    * ingreso de los datos*/
    public CentroSalud() {
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

