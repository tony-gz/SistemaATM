package modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String pin;
    private double saldo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String pin, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.pin = pin;
        this.saldo = saldo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", saldo=" + saldo +
               '}';
    }
}
