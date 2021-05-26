import jdk.swing.interop.SwingInterOpUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * PACKAGE_NAME
 * Nombre_project: Pesca_Marcos_Rabadan
 * Usuario
 * Created by: MarcosRa
 * Date : 24/05/2021
 * Description:
 **/
public class Usuarios {
    String nombre;
    FileWriter fw;
    FileReader fr;
    public Usuarios() {
    }

    public Usuarios(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public boolean leerUsuarios(String path, String usuario) throws Exception{
        fr = new FileReader(path);
        int i = fr.read();
        String str = "";
        while (i !=-1) {
            str += (char)i;
            if (str.equals("#" + usuario + "#" + System.lineSeparator())){
                return true;
            }
            if (i == '\n'){
                str = "";
            }
            i = fr.read();
        }
        fr.close();
        return false;
    }
    public int leer(String path, int limite, String objetivo) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String str = "";
        boolean bucle = true;
        int c = fr.read();
        while(c != -1){

            if (!str.startsWith("#")){
                str = "";
            }
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;
                if (str.equals("#" + objetivo + "#")) {
                    return contadorLinea;
                }
                str = "";
            }
            str += (char)c;
            if (c == '#') {
                contadorHstg++;
            }
            c = fr.read();
        }
        fr.close();
        return -1;
    }

    public int leerLineas(String path, int limite) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 1;
        int contadorHstg = 0;
        String str = "";
        int c = fr.read();
        while (c != -1){
            str += (char)c;
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;
            }
            if (str.equals("#")) {
                contadorHstg++;
            }else{
                str = "";
            }
            c = fr.read();
        }

        fr.close();
        return contadorLinea;

    }
    public void saberLineaObjetivo(String path, int limite, String objetivo) throws Exception {

        switch (limite){
            case 2:
                leer(path, limite, objetivo);
                break;
            default:
                System.out.println("Error inserta un limite valido");
        }


    }
    public int leer1Linea(String path, int limite, int linea) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String str = "";
        String noEliminados = "";
        int c = fr.read();
        while (c != -1) {
            str += (char)c;
            if (str.equals("#")) {
                contadorHstg++;
            }else{
                str = "";
            }
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;
            }
            if (contadorLinea != linea - 1) {
                noEliminados += (char)c;
            }
            c = fr.read();
        }
        fr.close();
        System.out.println(noEliminados);
        return contadorLinea;
    }

    public void registrarUsuario(String path, int limite, String usuario) throws Exception {



//        if (!leerUsuarios(path, usuario)) {
//            fw = new FileWriter(path, true);
//            fw.write("#" + usuario + "#" + System.lineSeparator());
//            fw.close();
//        } else {
//            throw new Exception("El usuario introducido ya existe");
//        }
    }

    public void eliminarUsuario(String path, String usuario) throws Exception {
        if (leerUsuarios(path, usuario)) {
            System.out.println("existe");
        }else{
            throw new Exception("El usuario no se puede eliminar poque no existe");
        }
    }
    @Override
    public String toString() {
        return "Usuarios{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
        Usuarios u = new Usuarios();
        String path = "user.txt";
       // System.out.println(u.leerLineas(path, 3));
        //System.out.println(u.contarHstg(path));
        //System.out.println(u.contarHstgLinea(path));
        //System.out.println(u.leer1Linea(path, 2, 2));
       // u.registrarUsuario(path,"mig");
       //u.registrarUsuario(path, 3, "m");
       // u.saberLineaObjetivo(path, 2, "gus");
        //System.out.println("hola".equals("hola"));
        System.out.println(u.leer(path, 2, "gus"));
    }
}
