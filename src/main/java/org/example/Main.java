package org.example;

import data.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args)
      {
        Main program =new Main();
        program.fnc_registrarUsuarios(10);
      }

      public void fnc_registrarUsuarios (int prm_cantidad) {
        Data data = new Data();
        String nombre, email, pass, telefono;
        for (int i = 0; i < prm_cantidad; i++) {
            nombre = "nombre"+i;
            email = "correo"+i+"@mail.com";
            pass = "pass"+i;
            telefono = "0"+i;
            data.fnc_sqlInsertUser(nombre, email, pass, telefono, 0);
        }
      }
}

