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
        ERROR,
        IGNORAR;
    }

    public void analizar(String texto, JTextArea AreaResultado, JTextArea AreaHistorial) {
        AreaResultado.setText(null);
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        numeros.add(0);
        int x = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (" ".equals(texto.substring(i, i + 1)) || "\n".equals(texto.substring(i, i + 1))) {
                if (i + 1 != texto.length()) {
                    if (" ".equals(texto.substring(i + 1, i + 2)) || "\n".equals(texto.substring(i+1, i + 2))) {

                    } else {
                        numeros.add(i);
                        numeros.add(i + 1);
                        x++;
                    }
                }
            }
        }
        numeros.add(texto.length());
        ArrayList<String> palabras = new ArrayList<String>();
        for (int i = 0; i < numeros.size(); i++) {
            palabras.add(texto.substring(numeros.get(i), numeros.get(i + 1)));
            i++;
        }
        Token tokens[] = new Token[palabras.size()];
        for (int i = 0; i < palabras.size(); i++) {
            System.out.println(palabras.get(i));
            String palabra = palabras.get(i);
            char caracteres[] = palabra.toCharArray();
            if (Character.isLetter(caracteres[0])) {
                boolean prueba = probarCadena(caracteres);
                if (prueba == true) {
                    tokens[i] = Token.CADENA;
                } else {
                    tokens[i] = Token.ERROR;
                }
            } else if (Character.isDigit(caracteres[0])) {
                int prueba = probarDigito(caracteres);
                if (prueba == 1) {
                    tokens[i] = Token.ENTERO;
                } else if (prueba == 2) {
                    tokens[i] = Token.DECIMAL;
                } else if (prueba == 3) {
                    tokens[i] = Token.ERROR;
                }
            } else if (comprobarSimboloValido(caracteres[0]) == true) {
                if (caracteres.length > 1 && (caracteres[1] != ' ')) {
                    tokens[i] = Token.ERROR;
                } else {
                    tokens[i] = Token.SIMBOLO;
                }
            } else if (comprobarSimboloNoValido(caracteres[0]) == true) {
                tokens[i] = Token.ERROR;
            } else if (Character.isWhitespace(caracteres[0])) {
                tokens[i] = Token.IGNORAR;
            }
            if (tokens[i] != Token.IGNORAR) {
                if (tokens[i] == Token.ERROR) {
                    AreaResultado.append("Token: " + palabras.get(i) + " Tipo: " + tokens[i]);
                    AreaResultado.append("\n");
                } else {
                    AreaResultado.append("Token: " + palabras.get(i) + " Tipo: " + tokens[i]);
                    AreaResultado.append("\n");
                    AreaHistorial.append("Token: " + palabras.get(i) + " Tipo: " + tokens[i]);
                    AreaHistorial.append("\n");
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
            if (comprobarSimboloValido(caracteres[i]) == true) {
                comprobacion = false;
            }
        }
        return comprobacion;
    }

    public int probarDigito(char[] caracteres) {
        int numero = 1;
        for (int i = 0; i < caracteres.length; i++) {
            if (comprobarSimboloNoValido(caracteres[i]) == true) {
                numero = 3;
            }
            if (comprobarSimboloValido(caracteres[i]) == true) {
                numero = 3;
            }
            if (Character.isLetter(caracteres[i])) {
                numero = 3;
            }
            if (caracteres[i] == '.') {
                numero = probarDecimal(i + 1, caracteres);
                i = caracteres.length;
            }
        }
        return numero;
    }

    public int probarDecimal(int inicio, char[] caracteres) {
        int numero = 2;
        for (int i = inicio; i < caracteres.length; i++) {
            if (comprobarSimboloNoValido(caracteres[i]) == true) {
                numero = 3;
            }
            if (comprobarSimboloValido(caracteres[i]) == true) {
                numero = 3;
            }
            if (Character.isLetter(caracteres[i])) {
                numero = 3;
            }
        }
        return numero;
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
        } else if (c == '(') {
            comprobacion = true;
        } else if (c == ')') {
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
        }
        return comprobacion;
    }
}
