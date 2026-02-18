package data;

import java.util.Scanner;

public class Usuario {
    Scanner read = new Scanner(System.in);
    Data db = new Data();

    public int fnc_registrarUsuario () {
        String usuario, clave, nombre, rol;

        // Interfaz de consola
        System.out.println("==============================REGISTRO USUARIO==============================");
        System.out.println("Ingrese el usuario de acceso: ");                   usuario = read.nextLine();
        System.out.println("Ingrese la clave: ");                               clave = read.nextLine();
        System.out.println("Ingrese su nombre completo: ");                     nombre = read.nextLine();
        System.out.println("Ingrese su rol: [MEDICO], [AGENDADOR], [ROL]");     rol = read.nextLine();
        System.out.println("============================================================================");

        return db.fnc_sqlInsertarUsuario(usuario, clave, nombre, rol);
    }

    public int fnc_mostrarUsuario () {
        // Interfaz de consola
        System.out.println("==============================LECTURA DATOS USUARIO==============================");
        return db.fnc_mostrarTabla("USUARIOS");
    }

    public int fnc_actualizarUsuario () {
        return -1;
    }

    public int fnc_desactivarUsuario () {
        return -1;
    }
}
