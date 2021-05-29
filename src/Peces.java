import java.text.DecimalFormat;

/**
 * PACKAGE_NAME
 * Nombre_project: Pesca_Marcos_Rabadan
 * Peces
 * Created by: MarcosRa
 * Date : 24/05/2021
 * Description:
 **/
public class Peces {
    String nombre;
    String probabilidad;
    double pesoMax;
    double pesoMin;

    public Peces() {
    }

    public Peces(String nombre, String probabilidad, double pesoMax, double pesoMin) {
        this.nombre = nombre;
        this.probabilidad = probabilidad;
        this.pesoMax = pesoMax;
        this.pesoMin = pesoMin;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProbabilidad() {
        return this.probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
    }

    public double getPesoMax() {
        return this.pesoMax;
    }

    public void setPesoMax(double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public double getPesoMin() {
        return this.pesoMin;
    }

    public void setPesoMin(double pesoMin) {
        this.pesoMin = pesoMin;
    }

    public String toString() {
        return "Peces{nombre='" + this.nombre + "', probabilidad='" + this.probabilidad + "', pesoMax=" + this.pesoMax + ", pesoMin=" + this.pesoMin + "}";
    }

    public static void main(String[] args) {
//        new DecimalFormat("0.00");
//        double r = Math.random() * 1.0D;
//        double redondeado = (double)Math.round(r * 100.0D) / 100.0D;
//        System.out.println(redondeado);
    }
}

