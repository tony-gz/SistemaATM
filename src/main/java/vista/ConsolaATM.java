package vista;

import dao.UsuarioDAO;
import modelo.ATM;
import modelo.Usuario;

import java.util.Scanner;

public class ConsolaATM {

    private Scanner scanner;
    private UsuarioDAO usuarioDAO;

    public ConsolaATM() {
        scanner = new Scanner(System.in);
        usuarioDAO = new UsuarioDAO();
    }
    private void tarjetaRetenida() {
    System.out.println("PIN incorrecto 3 veces. Su tarjeta ha sido retenida.");
    }


    public void iniciar() {
        System.out.println("==== Bienvenido al Cajero ATM ====");

        while (true) {
            System.out.print("Ingrese su ID de usuario (o 0 para salir): ");
            int id = Integer.parseInt(scanner.nextLine());

            if (id == 0) {
                System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                break;
            }

            Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorId(id);
            if (usuarioExistente == null) {
                System.out.println("El usuario con ID " + id + " no existe. Intente de nuevo.");
                continue;
            }

            int intentos = 0;
            Usuario usuario = null;

            while (intentos < 3 && usuario == null) {
                System.out.print("Ingrese su PIN: ");
                String pin = scanner.nextLine();

                usuario = usuarioDAO.autenticarUsuario(id, pin);
                if (usuario == null) {
                    intentos++;
                    if (intentos < 3) {
                        System.out.println("PIN incorrecto. Intento " + intentos + " de 3.");
                    }
                }
            }

            if (usuario == null) {
                tarjetaRetenida();
                break;
            }

            System.out.println("¡Autenticación exitosa! Bienvenido, " + usuario.getNombre());
            ATM atm = new ATM(usuario);
            mostrarMenu(atm);
            break; 
        }
    }


    private void mostrarMenu(ATM atm) {
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.printf("Su saldo actual es: $%.2f%n", atm.consultarSaldo());
                    break;
                case 2:
                    System.out.print("Ingrese el monto a retirar: ");
                    double retiro = Double.parseDouble(scanner.nextLine());
                    if (atm.retirar(retiro)) {
                        System.out.println("Retiro exitoso.");
                    } else {
                        System.out.println("Error: saldo insuficiente o monto inválido.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el monto a depositar: ");
                    double deposito = Double.parseDouble(scanner.nextLine());
                    if (atm.depositar(deposito)) {
                        System.out.println("Depósito exitoso.");
                    } else {
                        System.out.println("Error: monto inválido.");
                    }
                    break;
                case 4:
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }
}
