import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Blender blender = new BasicBlender();
        Scanner scanner = new Scanner(System.in);
       
        // Menú de opciones
        String menu = "Seleccione una opción:\n"
                + "1. Prende la licuadora\n"
                + "2. Llena la licuadora con lo que se desea licuar\n"
                + "3. Incrementa velocidad\n"
                + "4. Consulta en qué velocidad está la licuadora\n"
                + "5. Consulta si la licuadora está llena\n"
                + "6. Vaciar la licuadora\n"
                + "7. Salir";

        int option = -1;

        // Bucle de interacción
        while (option != 7) {
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            // Acciones según opción seleccionada
            if (option == 1) {
                blender.turnOn();
                System.out.println("La licuadora está encendida.");
            } else if (option == 2) {
                System.out.print("Ingrese el contenido a licuar: ");
                String content = scanner.nextLine();
                try {
                    blender.addContent(content);
                    System.out.println("Contenido agregado a la licuadora.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
