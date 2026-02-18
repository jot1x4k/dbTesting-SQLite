package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {
    // CRUD Usuario: Registrar usuario en la base de datos
    public int fnc_sqlInsertarUsuario (
            String prm_usuario,
            String prm_clave,
            String prm_nombre,
            String prm_rol
    )
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO USUARIOS (USU_USER, USU_CLAVE, USU_NOMBRECOMPLETO, USU_ROL) " +
                    "VALUES ('" + prm_usuario + "', '" + prm_clave + "', '" + prm_nombre + "', '" +prm_rol + "')";

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
