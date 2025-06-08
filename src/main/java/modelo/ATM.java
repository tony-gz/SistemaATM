package modelo;

import dao.UsuarioDAO;

public class ATM {
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public ATM(Usuario usuario) {
        this.usuario = usuario;
        this.usuarioDAO = new UsuarioDAO();
    }

    public double consultarSaldo() {
        return usuario.getSaldo();
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= usuario.getSaldo()) {
            double nuevoSaldo = usuario.getSaldo() - monto;
            boolean exito = usuarioDAO.actualizarSaldo(usuario.getId(), nuevoSaldo);
            if (exito) {
                usuario.setSaldo(nuevoSaldo);
            }
            return exito;
        }
        return false;
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            double nuevoSaldo = usuario.getSaldo() + monto;
            boolean exito = usuarioDAO.actualizarSaldo(usuario.getId(), nuevoSaldo);
            if (exito) {
                usuario.setSaldo(nuevoSaldo);
            }
            return exito;
        }
        return false;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
