import com.sun.source.tree.NewArrayTree;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * PACKAGE_NAME
 * Nombre_project: Pesca_Marcos_Rabadan
 * Pescar
 * Created by: MarcosRa
 * Date : 29/05/2021
 * Description:
 **/
public class Pescar {
    Usuarios u = new Usuarios();
    FileWriter fw;
    FileReader fr;

    public void vaciarArchivo(String path) throws Exception {
        fw = new FileWriter(path, false);
        fw.write("");
        fr.close();
    }

    public void identificacion(String path, int limite, String usuario) throws Exception {
        if (u.leer(path, limite, usuario) != -1) {
            System.out.println("Bienvenido " + usuario);
        }else {
            throw new Exception("No puedes pescar porque no estas registrado");
        }
    }

    public int contarLineas(String path, int limite) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String pez = "";
        String str = "";
        int c = fr.read();
        while(c != -1){
            if (c == '#') {
                contadorHstg++;
            }
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;
            }
            c = fr.read();
        }
        return contadorLinea;
    }
    public int contarHstg(String path) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;

        int c = fr.read();
        while(c != -1){
            if (c == '#') {
                contadorHstg++;
            }
            c = fr.read();
        }
        fr.close();
        return contadorHstg;
    }

    public int buscarLinea(String path, int limite) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String pez = "";
        String str = "";
        int c = fr.read();
        while(c != -1){
            str += (char)c;
            if (c == '#') {
                contadorHstg++;
            }
            if (contadorHstg == 1 && contadorLinea == 2) {
                if (str.startsWith("#")) {
                    str = "";
                }
                return contadorLinea;
            }
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;
            }
            c = fr.read();
        }
        fr.close();
        System.out.println(pez);
        return -1;

    }

    public int obtenerPorcentajeRandom(String path, int limite, int linea, int objetivoHtg) throws Exception {
        double random = Math.random();
        for (int i = 0; i < contarLineas(path, limite) ; i++) {
            double a = Double.parseDouble(optenerPez(path, limite, linea, objetivoHtg));
            //System.out.println(a);
            if (a > random) {
                //System.out.println(a);
                return linea;
            }
            linea++;
        }
        return -1;
    }
    public void pescar(String pathPesquera, String usuario) throws Exception {
        String pathRegistros = "registres.txt";
        fw = new FileWriter(pathRegistros, true);
        int hstgNombrePez = 1;
        int hstgProbabilidad = 2;
        int hstgPesoMin = 3;
        int hstgPesoMax = 4;

        int lineaPez = obtenerPorcentajeRandom(pathPesquera, 5, 0, hstgProbabilidad);
        System.out.println(lineaPez);
        String nombrePez = optenerPez(pathPesquera, 5, lineaPez, hstgNombrePez);
        String pesoMin = optenerPez(pathPesquera, 5, lineaPez, hstgPesoMin);
        String pesoMax = optenerPez(pathPesquera, 5, lineaPez, hstgPesoMax);
        System.out.println(pesoMin);
        double pesoMinDouble = Double.parseDouble(pesoMin);
        double pesoMaxDouble = Double.parseDouble(pesoMax);
        double pesoPez = (Math.random() * ((pesoMaxDouble - pesoMinDouble))) + pesoMinDouble;
        pesoPez = Math.round(pesoPez * 100.00) / 100.00;
        System.out.println(lineaPez);
        System.out.println(nombrePez);
        System.out.println(pesoMin);
        System.out.println(pesoMax);
        System.out.println(pesoPez);
        LocalDate fecha = LocalDate.now();
        fw.write("#" + nombrePez  + "#" + usuario +  "#" +  pesoPez + "#" + fecha + "#" + "\n");
        fw.close();
    }
    public String quitarUsuario(String path, int limite, int linea, int objetivoHtg, String usuario) throws Exception {
        //vaciarArchivo(path);
        String a = "";
        String b = "";
        usuario = "#" + usuario + "#";
        for (int i = 0; i < contarLineas(path, limite); i++) {
            a = "#" + optenerPez(path, limite, linea, objetivoHtg) + "#";
            if (!usuario.equals(a)) {
               b += a + "\n";
            }
            linea++;
        }
        return b;
    }
    public void eliminarUsuario(String path, int limite, int linea, int objetivoHtg, String usuario) throws Exception {
       String a = quitarUsuario(path, limite, linea, objetivoHtg, usuario);
       fw = new FileWriter(path);
       fw.write(a);
       fw.close();
    }
    public String optenerPez(String path, int limite, int linea , int objetivoHtg) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String pez = "";
        String str = "";
        int c = fr.read();
        while(c != -1){
            str += (char)c;
            if (c == '#') {
                contadorHstg++;
            }
            if (contadorHstg == objetivoHtg && contadorLinea == linea) {
                if (str.startsWith("#")) {
                    str = "";
                }
                pez = str;
            }
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;
            }
            c = fr.read();
        }
        fr.close();

        return pez;
    }

    public static void main(String[] args) throws Exception {
        String pathUsuarios = "user.txt";
        String pathFlorida = "florida.txt";
        String pathMediterranea = "mediterranea.txt";
        Pescar pescar = new Pescar();
//        int hstgNombrePez = 1;
//        int hstgProbabilidad = 1;
//        int hstgPesoMin = 3;
//        int hstgPesoMax = 4;
//
//        int lineaPez = pescar.obtenerPorcentajeRandom(pathFlorida, 5, 0, 2);
//        String nombrePez = pescar.optenerPez(pathFlorida, 5, lineaPez, hstgNombrePez);
//        String pesoMin = pescar.optenerPez(pathFlorida, 5, lineaPez, hstgPesoMin);
//        String pesoMax = pescar.optenerPez(pathFlorida, 5, lineaPez, hstgPesoMax);
//        double pesoMinDouble = Double.parseDouble(pesoMin);
//        double pesoMaxDouble = Double.parseDouble(pesoMax);
//        double pesoPez = (Math.random() * ((pesoMaxDouble - pesoMinDouble))) + pesoMinDouble;
//        System.out.println(lineaPez);
//        System.out.println(nombrePez);
//        System.out.println(pesoMin);
//        System.out.println(pesoMax);
//        System.out.println(pesoPez);
        String usuario = "marcos";
        pescar.pescar(pathMediterranea, usuario);
        LocalDate fecha = LocalDate.now();
        System.out.println(fecha);

    }
}

