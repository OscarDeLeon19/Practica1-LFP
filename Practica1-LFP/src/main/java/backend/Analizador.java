package backend;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class Analizador {

    public Analizador() {

    }

    public enum Token {
        CADENA,
        ENTERO,
        DECIMAL,
        SIMBOLO,
        ERROR;
    }

    public void analizar(String texto, JTextArea AreaResultado, JTextArea AreaHistorial) {

        System.out.println(texto);
        ArrayList<Integer> tokens = new ArrayList<Integer>();
        tokens.add(0);
        int x = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (" ".equals(texto.substring(i, i + 1))) {
                if (i + 1 != texto.length()) {
                    if (" ".equals(texto.substring(i + 1, i + 2))) {

                    } else {

                        tokens.add(i);
                        tokens.add(i + 1);
                        x++;
                    }
                }
            }
        }
        tokens.add(texto.length());
        ArrayList<String> palabras = new ArrayList<String>();
        for (int i = 0; i < tokens.size(); i++) {
            palabras.add(texto.substring(tokens.get(i), tokens.get(i + 1)));
            i++;
        }

        for (int i = 0; i < palabras.size(); i++) {
            String palabra = palabras.get(i);
            char caracteres[] = palabra.toCharArray();
            if (Character.isLetter(caracteres[0])) {
                boolean prueba = probarCadena(caracteres);
                if (prueba == true) {
                    System.out.println("Cadena");
                } else {
                    System.out.println("No cadena");
                }
            }

        }
    }

    public boolean probarCadena(char[] caracteres) {
        boolean comprobacion = true;
        for (int i = 0; i < caracteres.length; i++) {
            if (comprobarSimboloNoValido(caracteres[i]) == true) {
                comprobacion = false;
            }
            if (comprobarSimboloNoValido(caracteres[i]) == true) {
                comprobacion = false;
            }
        }
        return comprobacion;
    }

    public boolean comprobarSimboloNoValido(char c) {
        boolean comprobacion = false;
        if (c == '!') {
            comprobacion = true;
        } else if (c == '#') {
            comprobacion = true;
        } else if (c == '$') {
            comprobacion = true;
        } else if (c == '%') {
            comprobacion = true;
        } else if (c == '&') {
            comprobacion = true;
        } else if (c == '/') {
            comprobacion = true;
        } else if (c == '=') {
            comprobacion = true;
        } else if (c == '?') {
            comprobacion = true;
        } else if (c == '¡') {
            comprobacion = true;
        } else if (c == '¿') {
            comprobacion = true;
        } else if (c == '+') {
            comprobacion = true;
        } else if (c == '-') {
            comprobacion = true;
        } else if (c == '*') {
            comprobacion = true;
        } else if (c == '>') {
            comprobacion = true;
        } else if (c == '<') {
            comprobacion = true;
        } else if (c == '@') {
            comprobacion = true;
        } else if (c == ':') {
            comprobacion = true;
        }
        return comprobacion;
    }

    public boolean comprobarSimboloValido(char c) {
        boolean comprobacion = false;
        if (c == '[') {
            comprobacion = true;
        } else if (c == ']') {
            comprobacion = true;
        } else if (c == '{') {
            comprobacion = true;
        } else if (c == '}') {
            comprobacion = true;
        } else if (c == ';') {
            comprobacion = true;
        } else if (c == ',') {
            comprobacion = true;
        } else if (c == '.') {
            comprobacion = true;
            return comprobacion;
        }
        return comprobacion;
    }
}
