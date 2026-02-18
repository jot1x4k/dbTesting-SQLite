package data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserRepository implements IUserRepository {
    @Override
    public void save(User user) {
        String sql = "INSERT INTO USUARIOS (USU_USER, USU_CLAVE, USU_NOMBRECOMPLETO, USU_ROL, USU_ESTADO) VALUES (?, ?, ?, ?, '1')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFullName());
            pstmt.setString(4, user.getRole());
            pstmt.executeUpdate();
            System.out.println(">> Usuario registrado con éxito.");

        } catch (SQLException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS ORDER BY USU_ID DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USUARIOS SET USU_USER = ?, USU_CLAVE = ?, USU_NOMBRECOMPLETO = ?, USU_ROL = ? WHERE USU_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFullName());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">> Usuario actualizado con éxito.");
            } else {
                System.out.println(">> No se encontró el usuario con ID: " + user.getId());
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void deactivate(int id) {
        String sql = "UPDATE USUARIOS SET USU_ESTADO = '0' WHERE USU_ID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println(">> Usuario desactivado.");

        } catch (SQLException e) {
            System.err.println("Error al desactivar: " + e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM USUARIOS WHERE USU_ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper para no repetir código (DRY)
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("USU_ID"),
                rs.getString("USU_USER"),
                rs.getString("USU_CLAVE"),
                rs.getString("USU_NOMBRECOMPLETO"),
                rs.getString("USU_ROL"),
                rs.getString("USU_ESTADO")
        );
    }
}
