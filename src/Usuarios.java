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


    public int leer(String path, int limite, String objetivo) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String str = "";
        int c = fr.read();
        while(c != -1){
            str += (char)c;
            if (c == '#') {
                contadorHstg++;
            }
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

            c = fr.read();
        }
        fr.close();
        return -1;
    }

    public void quitarObjetivo(String path, int limite, String objetivo) throws Exception {
        fr = new FileReader(path);

        int contadorLinea = 0;
        int contadorHstg = 0;
        String str = "";
        String noEliminados = "";
        String a = "";
        boolean bucle = true;
        int c = fr.read();
        while(c != -1){
            str += (char)c;
            if (c == '#') {
                contadorHstg++;
            }
            if (!str.startsWith("#")){
                str = "";
            }
            if (contadorHstg == limite) {
                contadorLinea++;
                contadorHstg = 0;
                if (!str.equals("#" + objetivo + "#")) {
                    noEliminados += str;
                    a += noEliminados + "\n";

                    noEliminados = "";
                }
                str = "";
            }
            c = fr.read();
        }
        fr.close();
        fw = new FileWriter(path);
        fw.write(a);
        fw.close();
    }
    public void registrarUsuario(String path, int limite, String usuario) throws Exception {

        if (leer(path, limite,usuario) == -1) {
            fw = new FileWriter(path, true);
            fw.write("#" + usuario + "#" + System.lineSeparator());
            fw.close();
        } else {
            throw new Exception("El usuario introducido ya existe");
        }
    }

    public void eliminarUsuario(String path, int limite, String usuario) throws Exception {
        if (leer(path, 2 , usuario) != -1) {
            quitarObjetivo(path, limite, usuario);
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

    }
}
