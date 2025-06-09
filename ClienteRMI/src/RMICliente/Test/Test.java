package RMICliente.Test;

import RMICliente.Cliente.Cliente;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        String op;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Buscar datos del empleado por correo:");
            String correo = scanner.nextLine();
            System.out.println(Cliente.consultar(correo));
            System.out.println("¿Desea salir? Si(s) / No(n)");
            op = scanner.nextLine().toLowerCase();
        } while (op.equals("no") || op.equals("n"));
        scanner.close();
    }
}