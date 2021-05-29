/**
 * PACKAGE_NAME
 * Nombre_project: Pesca_Marcos_Rabadan
 * Pesca_Marcos_Rabadan
 * Created by: MarcosRa
 * Date : 24/05/2021
 * Description:
 **/
public class Pesca_Marcos_Rabadan {
    public Pesca_Marcos_Rabadan() {
    }

    public static void imprimirln(String mensaje) {
        System.out.println(mensaje);
    }

    public static void imprimir(String mensaje) {
        System.out.print(mensaje);
    }


    public static void main(String[] args) throws Exception {
        Usuarios u = new Usuarios();
        String path = "user.txt";
        //  System.out.println(u.leer(path, 2 , "mig"));
        // u.eliminarUsuario(path, 2 , "pep");

        u.eliminarUsuario(path, 2 , "pep");
        u.registrarUsuario(path, 2, "juli");
    }
}