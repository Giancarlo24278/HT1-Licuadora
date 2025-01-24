package com.example;
import java.util.Scanner;

public class Licuadora {
    public static void main(String[] args) {
        Blender blender = new BasicBlender();
        Scanner scanner = new Scanner(System.in);

        // Menu de opciones
        String menu = "Seleccione una opción:\n"
                    + "1. Encender la licuadora\n"
                    + "2. Llena la licuadora con lo que se desea licuar\n"
                    + "3. Incrementa velocidad\n"
                    + "4. Consulta en qué velocidad está la licuadora\n"
                    + "5. Consulta si la licuadora está llena\n"
                    + "6. Vaciar la licuadora\n"
                    + "7. Salir";

        int option = -1;

        // Bucle de licuadora
        while (option != 7) {
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            // Accion para encender la licuadora
            if (option == 1) {
                blender.Encender();
                System.out.println("La licuadora está encendida.");

            // Accion para llenar la licuadora manualmente escribir los ingredientes
            } else if (option == 2) {
                System.out.print("Ingrese el contenido a licuar: ");
                String content = scanner.nextLine();
                try {
                    blender.ingredientes(content); // Agregar contenido a la licuadora
                    System.out.println("Contenido agregado a la licuadora.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            // Accion para incrementar la velocidad de la licuadora  1 al 10
            } else if (option == 3) {
                System.out.println("Introduce la velocidad entre los números 1 a 10: ");
                int nuevaVelocidad = scanner.nextInt();
                if (nuevaVelocidad >= 1 && nuevaVelocidad <= 10) {
                    while (blender.velocidadActual() < nuevaVelocidad) {
                        blender.incrementarVelocidad();
                    }
                    System.out.println("Velocidad incrementada. Velocidad actual: " + blender.velocidadActual());
                } else {
                    System.out.println("La velocidad debe estar entre 1 y 10.");
                }
            //Revisar la velocidad actual de la licuadora
            }else if (option == 4) {
                System.out.println("Velocidad actual: " + blender.velocidadActual());

            //Revisar si la licuadora está llena
            } else if (option == 5) {
                System.out.println("¿La licuadora está llena? " + (blender.lleno() ? "Sí" : "No"));

            //Vaciar la licuadora
            } else if (option == 6) {
                blender.vaciar();
                System.out.println("La licuadora ha sido vaciada.");

            //Salir del programa
            } else if (option == 7) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }

    void Encender() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
interface Blender {
    void Encender();
    void ingredientes(String content);
    void incrementarVelocidad();
    int velocidadActual();
    boolean lleno();
    void vaciar();
}

class BasicBlender implements Blender {
    private boolean isOn = false;
    private int velocidadActual = 0;
    private String content = null;

    @Override
    public void Encender() {
        isOn = true;
    }

    @Override
    public void ingredientes(String content) {
        if (!isOn) {
            throw new IllegalStateException("La licuadora debe estar encendida para agregar contenido.");
        }
        this.content = content;
    }

    @Override
    public void incrementarVelocidad() {
        if (!isOn) {
            throw new IllegalStateException("La licuadora debe estar encendida para aumentar la velocidad.");
        }
        if (content == null) {
            throw new IllegalStateException("La licuadora no puede funcionar vacía.");
        }
        if (velocidadActual < 10) {
            velocidadActual++;
        } else {
            throw new IllegalStateException("La velocidad máxima es 10.");
        }
    }

    @Override
    public int velocidadActual() {
        return velocidadActual;
    }

    @Override
    public boolean lleno() {
        return content != null;
    }

    @Override
    public void vaciar() {
        content = null;
        velocidadActual = 0;
    }
}
