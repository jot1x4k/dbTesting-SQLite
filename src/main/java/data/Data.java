package data;

import java.sql.*;

public class Data {


    // CRUD Usuario: Registrar usuario en la base de datos
    public int fnc_sqlInsertUser (
            String prm_name,
            String prm_email,
            String prm_password,
            String prm_phoneNumber,
            int prm_isAdmin
            )
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO usuarios (nombre, email, password, telefono, es_admin, fecha_registro) " +
                    "VALUES ('" + prm_name + "', '" + prm_email + "', '" + prm_password + "', '" +
                    prm_phoneNumber + "', " + prm_isAdmin + ", date('now'))";

            try (Statement pstmt = connection.createStatement()) {
                pstmt.executeUpdate(sql);
                System.out.println("Usuario registrado con exito");

                // +-- testing
                System.out.println("Query final: " + sql);
                // --+
                return 1;
            } catch (SQLException e) {
                System.out.println(e.getMessage());

                // +-- testing
                System.out.println("Query final: " + sql);
                // --+
                return 0;
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace(System.err);
            return 0;
        }
    }
}
