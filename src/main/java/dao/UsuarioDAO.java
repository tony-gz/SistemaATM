package dao;

import conexion.ConexionBD;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    /**
     * Verifica si existe un usuario con el ID y PIN proporcionados.
     * Si existe, devuelve el objeto Usuario; si no, devuelve null.
     */
    public Usuario autenticarUsuario(int id, String pin) {
        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE id = ? AND pin = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, pin);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPin(rs.getString("pin"));
                usuario.setSaldo(rs.getDouble("saldo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al autenticar usuario.");
            e.printStackTrace();
        }

        return usuario;
    }
    
    
    public Usuario buscarUsuarioPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPin(rs.getString("pin"));
                usuario.setSaldo(rs.getDouble("saldo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por ID.");
            e.printStackTrace();
        }

        return usuario;
    }


    /**
     * Actualiza el saldo de un usuario dado su ID.
     */
    public boolean actualizarSaldo(int id, double nuevoSaldo) {
        String sql = "UPDATE usuarios SET saldo = ? WHERE id = ?";
        

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, nuevoSaldo);
            stmt.setInt(2, id);
            //System.out.println("¿Conexión cerrada?: " + conn.isClosed());
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el saldo.");
            e.printStackTrace();
            return false;
        }
    }
}
