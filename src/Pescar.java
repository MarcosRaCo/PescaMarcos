import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

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
    FileWriter fwU;
    FileReader fr;
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
    public void vaciarArchivo(String path) throws Exception {
        fw = new FileWriter(path, false);
        fw.write("");
        fr.close();
    }
    public int lineaObjetivo(String path, int limite, String objetivo) throws Exception {
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String str = "";
        int c = fr.read();
        while(c != -1){
            str += (char)c;
            if (!str.startsWith("#")) {
                str = "";
            }
            if (c == '#') {
                contadorHstg++;
            }
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;

                if (str.equals("#" + objetivo + "#")) {
                    return contadorLinea;
                }
                str = "";
            }
            c = fr.read();
        }
        fr.close();
        return -1;
    }
    public void registrarUsuario(String path, int limite, String usuario) throws Exception {
        if (lineaObjetivo(path, limite, usuario)  == -1) {
            fw = new FileWriter(path, true);
            fw.write("#" + usuario + "#" + System.lineSeparator());
            fw.close();
            System.out.println("Usuario registrado correctamente");
        } else {
            throw new Exception("El usuario introducido ya existe");
        }
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
        String nombrePez = leer(pathPesquera, 5, lineaPez, hstgNombrePez);
        String pesoMin = leer(pathPesquera, 5, lineaPez, hstgPesoMin);
        String pesoMax = leer(pathPesquera, 5, lineaPez, hstgPesoMax);
        double pesoMinDouble = Double.parseDouble(pesoMin);
        double pesoMaxDouble = Double.parseDouble(pesoMax);
        double pesoPez = (Math.random() * ((pesoMaxDouble - pesoMinDouble))) + pesoMinDouble;
        pesoPez = Math.round(pesoPez * 100.00) / 100.00;
        LocalDate fecha = LocalDate.now();
        System.out.println(("Has pescado un " + nombrePez  + " que pesa "  +  pesoPez));
        fw.write("#" + nombrePez  + "#" + usuario +  "#" +  pesoPez + "#" + fecha + "#" + "\n");
        fw.close();
    }
    public String quitarUsuario(String path, int limite, int linea, int objetivoHtg, String usuario) throws Exception {
        String a = "";
        String b = "";
        usuario = "#" + usuario + "#";
            a = "#" + leer(path, limite, linea, objetivoHtg) + "#";

            if (!usuario.equals(a)) {
                b += a;
                return b;
            }else {
                return null;
            }
    }
    public void eliminarUsuario(String path, int limite, int linea, int objetivoHtg, String usuario) throws Exception {
        String pathTemp = "usuariosTemp.txt";
        if (lineaObjetivo(path, 2, usuario) != -1) {
            for (int i = 0; i < contarLineas(path, limite); i++) {
                String a = quitarUsuario(path, 2, linea, 1, usuario) + "\n";
                if (!a.equals(null +"\n")) {
                    fw = new FileWriter(pathTemp, true);
                    fw.write(a);
                    fw.close();
                }
                linea++;
            }
            int con = 0;
            vaciarArchivo(path);
            for (int i = 0; i < contarLineas(pathTemp, limite); i++) {
                String a = leer(pathTemp, 2, con, objetivoHtg);
                fwU = new FileWriter(path, true);
                fwU.write("#" + a + "#\n");
                fwU.close();
                con++;
            }
            vaciarArchivo(pathTemp);
        }else {
            throw new Exception("El usuario no se puede eliminar poque no existe");
        }

    }

    public void mostrarEstadisticasGenerales(String path) throws Exception {
        int linea = 0;
        String pez = "";
        int hstgNombrePez = 1;
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
                System.out.println(pez + pesoMax);
            }
        }
    }public void mostrarEstadisticasUsuarios(String path, String usuarios) throws Exception {
        int linea = 0;
        String pez = "";
        int hstgNombrePez = 1;
        int hstgNombreUsuario = 2;
        for (int i = 0; i < contarLineas(path, 5); i++) {
            pez = leer(path, 5, linea, hstgNombrePez);
            int linea2 = 0;
            double pesoMax = 0;
            for (int j = 0; j < contarLineas(path, 5); j++) {
                if (usuarios.equals(leer(path, 5, linea2, hstgNombreUsuario))) {
                    if (pez.equals(leer(path, 5, linea2, hstgNombrePez))) {
                        if (Double.parseDouble(leer(path, 5, linea2, 3)) > pesoMax) {
                            pesoMax = Double.parseDouble(leer(path, 5, linea2, 3));
                        }
                    }

                }linea2++;
            }
            linea++;
            if (pesoMax > 0) {
                System.out.println(pez + " con un peso maximo de " + pesoMax + " por " + usuarios);
            }
        }
    }

    public void menu() throws Exception {
        boolean bucle = true;
        while (bucle){
            /** PATHS**/
            String pathFlorida = "florida.txt";
            String pathMediterranea = "mediterranea.txt";
            String pathCaribe = "caribe.txt";
            String pathAfrica = "africa.txt";

            String pathUsuarios = "user.txt";
            String pathRegistros = "registres.txt";
            Scanner sc = new Scanner(System.in);
            boolean bandera = true;
            int opcion;
            while (bandera){
                System.out.print("************************************************** \n" +
                        "* Benvinguts a el programa de pesca * \n" +
                        "* Menú principal * \n" +
                        "************************************************** \n" +
                        "* 1) Alta d'usuari * \n" +
                        "* 2) Baixa d'usuari * \n" +
                        "* 3) Pescar en una pesquera * \n" +
                        "* 4) Estadístiques per usuari * \n" +
                        "* 5) Estadístiques globals * \n" +
                        "* 6) Sortir del programa * \n" +
                        "************************************************** \n" +
                        "OPCIÓ? ");
                opcion = sc.nextInt();
                switch (opcion){
                    case 1:
                        Scanner scNuevoUsuario = new Scanner(System.in);
                        System.out.print("Escribe el usuario que quieres dar de alta: ");
                        String opcionNuevoUsuario = scNuevoUsuario.nextLine();
                        registrarUsuario(pathUsuarios, 2, opcionNuevoUsuario);

                        break;
                    case 2:
                        System.out.println("Dar de baja a un usuario");
                        Scanner scEliminar = new Scanner(System.in);
                        String opcionEliminarUser = scEliminar.nextLine();
                        eliminarUsuario(pathUsuarios, 2, 0, 1, opcionEliminarUser);
                        break;
                    case 3:
                        System.out.println("*** PESCAR ***");
                        Scanner scPesquera = new Scanner(System.in);
                        boolean banderaPesquera = true;
                        int opcionPesquera;
                        while (banderaPesquera){
                            Scanner scUsuario = new Scanner(System.in);
                            System.out.print("Usuario: ");
                            String opcionUsuarioPesca = scUsuario.nextLine();
                            identificacion(pathUsuarios,2, opcionUsuarioPesca);
                            System.out.print("Selecciona una pesquera \n" +
                                    "1) Florida \n" +
                                    "2) Mediterraneo \n" +
                                    "3) Caribe \n" +
                                    "4) Africa \n" +
                                    "5) Salir \n" +
                                    "Opcion "
                            );
                            opcionPesquera = scPesquera.nextInt();

                            switch (opcionPesquera){
                                case 1:
                                    System.out.println("Has seleccionado Florida");
                                    pescar(pathFlorida, opcionUsuarioPesca);
                                    break;
                                case 2:
                                    System.out.println("Has seleccionado Mediterraneo");
                                    pescar(pathMediterranea, opcionUsuarioPesca);
                                    break;
                                case 3:
                                    System.out.println("Has seleccionado Caribe");
                                    pescar(pathCaribe, opcionUsuarioPesca);
                                    break;
                                case 4:
                                    System.out.println("Has seleccionado Africa");
                                    pescar(pathAfrica, opcionUsuarioPesca);
                                    break;
                                case 5:
                                    banderaPesquera = false;
                                    break;
                            }
                        }
                        break;
                    case 4:
                        Scanner scUsuario = new Scanner(System.in);
                        System.out.print("Usuario: ");
                        String opcionUsuarioEstadistica = scUsuario.nextLine();
                        identificacion(pathUsuarios,2, opcionUsuarioEstadistica);
                        mostrarEstadisticasUsuarios(pathRegistros, "pepe");
                        break;
                    case 5:
                        mostrarEstadisticasGenerales(pathRegistros);
                        break;
                    case 6:
                        bucle = false;
                        break;
                }

            }

        }

    }
    public static void main(String[] args) throws Exception {
        String pathUsuarios = "user.txt";
        String pathFlorida = "florida.txt";
        String pathMediterranea = "mediterranea.txt";
        String pathRegistros = "registres.txt";
        Pescar pescar = new Pescar();

        String usuario = "gus";
        //System.out.println(pescar.quitarUsuario(pathUsuarios, 2, 1, 1, "gus"));
        pescar.menu();
        //pescar.eliminarUsuario(pathUsuarios, 2, 0, 1, "pepe");
        int linea = 0;
        String pathTemp = "usuariosTemp.txt";
        //System.out.println(pescar.leer(pathTemp, 2,4, 1));



    }
}

