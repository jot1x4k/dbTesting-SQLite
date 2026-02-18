package org.example;

import data.Data;
import data.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {

    public void fnc_menu() {
        Scanner read = new Scanner(System.in);
        Usuario user = new Usuario();
        System.out.println("=================================CRUD TALLER LABORATORIO SW2=================================");
        System.out.println("1. Registro");
        System.out.println("2. Lectura");
        System.out.println("3. Actualizacion");
        System.out.println("4. Desactivacion");
        System.out.println("5. Salir");
        System.out.println("Digite el numero de la opcion del menu: "); int opcion = read.nextInt();

        switch (opcion) {
            case 1: {
                user.fnc_registrarUsuario();
                fnc_menu();
            } break;

            case 2: {
                user.fnc_mostrarUsuario();
                fnc_menu();
            } break;

            case 3: {
                user.fnc_actualizarUsuario();
                fnc_menu();
            } break;

            case 4: {
                user.fnc_desactivarUsuario();
                fnc_menu();
            } break;

            case 5: {
                System.exit(1);
            } break;

            default: {
                System.out.println("Seleccione una opcion valida");
                fnc_menu();
            } break;
        }
    }

    public static void main(String[] args)
      {
          Main org = new Main();
          org.fnc_menu();
      }
}

