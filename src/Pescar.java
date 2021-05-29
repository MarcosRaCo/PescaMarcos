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
    public void optenerPez(String path, int limite) throws Exception{
        fr = new FileReader(path);
        int contadorLinea = 0;
        int contadorHstg = 0;
        String str = "";
        int c = fr.read();
        while(c != 1){

        }
        fr.close();
    }
    public void pescarAccion(){

    }

    public static void main(String[] args) throws Exception {
        String pathUsuarios = "user.txt";
        Pescar pescar = new Pescar();
        pescar.identificacion(pathUsuarios, 2, "juli");
    }
}

