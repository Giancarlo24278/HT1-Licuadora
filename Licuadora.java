package com.example;
import java.util.Scanner;

public class Licuadora {
    public static void main(String[] args) {
        LicuadoraBasica licuadora = new LicuadoraBasica();
        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        String menu = "Seleccione una opción:\n"
                    + "1. Encender la licuadora\n"
                    + "2. Llenar la licuadora\n"
                    + "3. Incrementar velocidad\n"
                    + "4. Obtener velocidad actual\n"
                    + "5. Consultar si está llena\n"
                    + "6. Vaciar la licuadora\n"
                    + "7. Apagar la licuadora\n"
                    + "8. Salir";

        int opcion = -1;

        // Bucle de la licuadora
        while (opcion != 8) {
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    licuadora.encenderLicuadora();
                    System.out.println("La licuadora está encendida.");
                    break;
                case 2:
                    System.out.print("Ingrese el contenido a licuar: ");
                    String contenido = scanner.nextLine();
                    if (licuadora.llenarLicuadora()) {
                        System.out.println("Licuadora llena con: " + contenido);
                    } else {
                        System.out.println("La licuadora ya está llena.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la velocidad a incrementar (1-10): ");
                    int velocidad = scanner.nextInt();
                    if (licuadora.incrementarVelocidad(velocidad)) {
                        System.out.println("Velocidad incrementada. Velocidad actual: " + licuadora.obtenerVelocidadActual());
                    } else {
                        System.out.println("No se puede incrementar la velocidad. Verifique que la licuadora esté encendida y no exceda la velocidad máxima.");
                    }
                    break;
                case 4:
                    System.out.println("Velocidad actual: " + licuadora.obtenerVelocidadActual());
                    break;
                case 5:
                    System.out.println("¿La licuadora está llena? " + (licuadora.estaLlena() ? "Sí" : "No"));
                    break;
                case 6:
                    licuadora.vaciarLicuadora();
                    System.out.println("La licuadora ha sido vaciada.");
                    break;
                case 7:
                    licuadora.apagarLicuadora();
                    System.out.println("La licuadora está apagada.");
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}

interface LicuadoraInterface {
    public boolean encenderLicuadora();
    public boolean apagarLicuadora();
    public boolean llenarLicuadora();
    public boolean vaciarLicuadora();
    public boolean incrementarVelocidad(int velocidad);
    public int obtenerVelocidadActual();
    public boolean estaLlena();
}

class LicuadoraBasica implements LicuadoraInterface {
    private boolean encendida = false;
    private int velocidadActual = 0;
    private boolean llena = false;

    @Override
    public boolean encenderLicuadora() {
        if (!encendida) {
            encendida = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean apagarLicuadora() {
        if (encendida) {
            encendida = false;
            velocidadActual = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean llenarLicuadora() {
        if (!llena) {
            llena = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean vaciarLicuadora() {
        if (llena) {
            llena = false;
            velocidadActual = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean incrementarVelocidad(int velocidad) {
        if (encendida && llena && velocidadActual + velocidad <= 10) {
            velocidadActual += velocidad;
            return true;
        }
        return false;
    }

    @Override
    public int obtenerVelocidadActual() {
        return velocidadActual;
    }

    @Override
    public boolean estaLlena() {
        return llena;
    }
}
