import com.sun.source.tree.NewArrayTree;

import java.io.FileReader;
import java.io.FileWriter;

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
        System.out.println(random);
        for (int i = 0; i < contarLineas(path, limite) ; i++) {
            double a = Double.parseDouble(optenerPez(path, limite, linea, objetivoHtg));
            //System.out.println(a);

            if (a > random) {
                System.out.println(a);
                System.out.println(linea);
                return linea;
            }
            linea++;
        }
        //System.out.println(random);
        return -1;
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
    public void pescarAccion(){

    }

    public static void main(String[] args) throws Exception {
        String pathUsuarios = "user.txt";
        String pathFlorida = "florida.txt";
        Pescar pescar = new Pescar();

        //pescar.identificacion(pathUsuarios, 2, "juli");
        //pescar.optenerPez(pathFlorida, 5);
        //System.out.println(pescar.buscarLinea(pathFlorida, 5));
        //System.out.println(pescar.contarLineas(pathFlorida, 5));
        //System.out.println(pescar.optenerPez(pathFlorida, 5, 2, 3));
        //System.out.println(pescar.obtenerPorcentajeRandom(pathFlorida, 5, 0, 2));
        //System.out.println(pescar.contarHstg(pathUsuarios));
        System.out.println(pescar.optenerPez(pathFlorida, 5, pescar.obtenerPorcentajeRandom(pathFlorida, 5, 0, 2), 1));
    }
}

