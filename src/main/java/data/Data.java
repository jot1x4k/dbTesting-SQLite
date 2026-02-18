package data;

import javax.sql.DataSource;
import java.sql.*;

public class Data {
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

    public int fnc_sqlMostrarTabla (String prm_tabla) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM " + prm_tabla + " ORDER BY USU_ID DESC";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                int id = resultSet.getInt("USU_ID");
                String usuario = resultSet.getString("USU_USER");
                String nombre = resultSet.getString("USU_NOMBRECOMPLETO");
                String rol = resultSet.getString("USU_ROL");
                String estado = resultSet.getString("USU_ESTADO");

                //Mostrar registros
                System.out.println("USU_ID\t\tUSU_USER\t\tUSU_NOMBRECOMPLETO\t\t\t\tUSU_ROL\t\tUSU_ESTADO");
                System.out.println(id+"\t\t\t"+usuario+"\t\t\t"+nombre+"\t\t"+rol+"\t\t"+estado);
            }
            return 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int fnc_sqlActualizarUsuario (String prm_id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();

            String sql = "UPDATE USUARIOS SET ";

            return 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int fnc_sqlDesactivarUsuario (int prm_id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();

            String sql = "UPDATE USUARIOS SET USU_ESTADO = 0 WHERE USU_ID = "+ prm_id;
            try (Statement pstmt = connection.createStatement()) {
                pstmt.executeUpdate(sql);
                System.out.println("Usuario desactivado con exito");

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
