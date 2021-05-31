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

    public void scannerString(String string){

    }
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
            double a = Double.parseDouble(leer(path, limite, linea, objetivoHtg));
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
        String nombrePez = leer(pathPesquera, 5, lineaPez, hstgNombrePez);
        String pesoMin = leer(pathPesquera, 5, lineaPez, hstgPesoMin);
        String pesoMax = leer(pathPesquera, 5, lineaPez, hstgPesoMax);
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
            a = "#" + leer(path, limite, linea, objetivoHtg) + "#";
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
    public String leer(String path, int limite, int linea , int objetivoHtg) throws Exception{
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
    public void mostrarEstadisticasGenerales(String path) throws Exception {

        int linea = 0;

        String pez = "";
        String pez2 = "";
        double peso = 0;

        double d = 0;
//        for (int i = 0; i < contarLineas(path, 5); i++) {
//            pez = leer(path, 5, linea, hstgNombrePez);
//            double pesoPez1 = Double.parseDouble(leer(path, 5, linea, 3));
//            //System.out.println(pez + " " + pesoPez1);
//            int linea2 = 0;
//            double pesoMax = 0;
//            for (int j = 0; j < contarLineas(path, 5); j++) {
//                pez2 = leer(path, 5, linea2, hstgNombrePez);
//                double pesoPez2 = Double.parseDouble(leer(path, 5, linea2, 3));
//                if (pez.equals(pez2)) {
//                    if (pesoPez1 > pesoPez2) {
//                        pesoMax=  pesoPez1;
//                    }else{
//                        pesoMax=  pesoPez2;
//                    }
//                }
//                linea2++;
//            }
//            System.out.println(pez + " " + pesoMax);
//            linea++;
//        }
        int hstgNombrePez = 1;
        int hstgProbabilidad = 2;
        int hstgPesoMin = 3;
        int hstgPesoMax = 4;
        for (int i = 0; i < contarLineas(path, 5); i++) {
            pez = leer(path, 5, linea, hstgNombrePez);
            int linea2 = 0;
            double pesoMax = 0;
            for (int j = 0; j < contarLineas(path, 5); j++) {
                if (pez.equals(leer(path,5, linea2, hstgNombrePez))) {
                    if (Double.parseDouble(leer(path,5, linea2, 3)) > pesoMax) {
                        pesoMax = Double.parseDouble(leer(path,5, linea2, 3));
                    }
                }
                linea2++;
            }
            linea++;
            if (pesoMax > 0) {
                System.out.println(pez + " peso Maximo: " + pesoMax);

            }
        }
    }
    public void menu(){
        System.out.println("************************************************** \n" +
                "* Benvinguts a el programa de pesca * \n" +
                "* Menú principal * \n" +
                "************************************************** \n" +
                "* 1) Alta d'usuari * \n" +
                "* 2) Baixa d'usuari * \n" +
                "* 3) Pescar en una pesquera * \n" +
                "* 4) Estadístiques per usuari * \n" +
                "* 5) Estadístiques globals * \n" +
                "* s) Sortir del programa * \n" +
                "************************************************** \n" +
                "OPCIÓ?\n");
    }
    public static void main(String[] args) throws Exception {
        String pathUsuarios = "user.txt";
        String pathFlorida = "florida.txt";
        String pathMediterranea = "mediterranea.txt";
        String pathRegistros = "registres.txt";
        Pescar pescar = new Pescar();

        String usuario = "marcos";
        //pescar.pescar(pathMediterranea, usuario);
        //pescar.mostrarEstadisticasGenerales(pathRegistros);
        pescar.menu();
    }
}

