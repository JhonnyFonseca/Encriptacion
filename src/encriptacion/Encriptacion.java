package encriptacion;

import java.util.Scanner;

public class Encriptacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String texto = "";
        String textoEncriptado = "";

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Insertar un texto");
            System.out.println("2. Encriptar el mensaje");
            System.out.println("3. Desencriptar el mensaje");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            
            // Manejar la excepción InputMismatchException
            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de la entrada de la opción
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                scanner.nextLine(); // Limpiar el búfer del scanner
                continue; // Volver al inicio del bucle
            }

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese un texto:");
                    texto = scanner.nextLine();
                    System.out.println("Texto insertado correctamente.");
                    break;
                case 2:
                    if (texto.isEmpty()) {
                        System.out.println("Error: No hay texto ingresado para encriptar.");
                    } else {
                        textoEncriptado = encriptar(texto);
                        System.out.println("El texto encriptado es:");
                        System.out.println(textoEncriptado);
                    }
                    break;
                case 3:
                    if (textoEncriptado.isEmpty()) {
                        System.out.println("Error: No hay texto encriptado para desencriptar.");
                    } else {
                        String textoDesencriptado = desencriptar(textoEncriptado);
                        System.out.println("El texto desencriptado es:");
                        System.out.println(textoDesencriptado);
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    public static String encriptar(String texto) {
        StringBuilder textoEncriptado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (Character.isLetter(caracter)) {
                int sumaResta = i % 2 == 0 ? 4 : -4;
                char base = Character.isLowerCase(caracter) ? 'a' : 'A';
                char caracterEncriptado = (char) (base + (caracter - base + sumaResta + 26) % 26);
                textoEncriptado.append(caracterEncriptado);
            } else if (Character.isDigit(caracter)) { // Reemplazar números
                int numero = Character.getNumericValue(caracter);
                int nuevoNumero = 9 - numero; // Cambiar el número según la regla establecida
                textoEncriptado.append(nuevoNumero);
            } else {
                textoEncriptado.append(caracter); // Mantener otros caracteres sin cambios
            }
        }
        return textoEncriptado.reverse().toString();
    }

    public static String desencriptar(String textoEncriptado) {
        StringBuilder textoDesencriptado = new StringBuilder(textoEncriptado).reverse();

        for (int i = 0; i < textoDesencriptado.length(); i++) {
            char caracter = textoDesencriptado.charAt(i);
            if (Character.isLetter(caracter)) {
                int sumaResta = i % 2 == 0 ? 4 : -4;
                char base = Character.isLowerCase(caracter) ? 'a' : 'A';
                char caracterDesencriptado = (char) (base + (caracter - base - sumaResta + 26) % 26);
                textoDesencriptado.setCharAt(i, caracterDesencriptado);
            } else if (Character.isDigit(caracter)) {
                int numero = Character.getNumericValue(caracter);
                int nuevoNumero = 9 - numero; // Revertir el cambio realizado al encriptar
                textoDesencriptado.setCharAt(i, (char)(nuevoNumero + '0')); // Convertir a char
            }
        }

        return textoDesencriptado.toString();
    }
    
}
