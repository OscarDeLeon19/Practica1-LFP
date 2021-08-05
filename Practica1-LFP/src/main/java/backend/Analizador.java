package backend;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class Analizador {

    /**
     * Constructor de la clase Analizador
     */
    public Analizador() {
        
    }

    /**
     * Enum con los diferentes tipos de tokens que se van a utilizar
     */
    public enum Token {
        CADENA,
        ENTERO,
        DECIMAL,
        SIMBOLO,
        ERROR,
        IGNORAR;
    }

    /**
     * Metodo utilizado para analizar la cadena de texto proveniente de la
     * ventana
     *
     * @param texto El texto enviado
     * @param AreaResultado El area donde salen los resultados del analisis
     * @param AreaHistorial El area en donde se guardan los resultados correctos
     * del analisis anteriores.
     */
    public void analizar(String texto, JTextArea AreaResultado, JTextArea AreaHistorial) {
        AreaResultado.setText(null);
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(0);
        int x = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (" ".equals(texto.substring(i, i + 1)) || "\n".equals(texto.substring(i, i + 1))) {
                if (i + 1 != texto.length()) {
                    if (" ".equals(texto.substring(i + 1, i + 2)) || "\n".equals(texto.substring(i + 1, i + 2))) {
                        
                    } else {
                        numeros.add(i);
                        numeros.add(i + 1);
                        x++;
                    }
                }
            }
        }
        numeros.add(texto.length());
        ArrayList<String> palabras = new ArrayList<>();
        for (int i = 0; i < numeros.size(); i++) {
            String nueva_palabra = texto.substring(numeros.get(i), numeros.get(i + 1));
            String arreglo = arreglarCadena(nueva_palabra);
            palabras.add(arreglo);
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

    /**
     * Este metodo analiza una cadena de texto para ver si esta es correcta
     *
     * @param caracteres Los caracteres de la cadena de texto enviada
     * @return True si es una cadena de texto y False si la cadena tiene errores
     */
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

    /**
     * Comprueba si un numero es decimal o entero y si no tiene errores.
     *
     * @param caracteres Los caracteres de la cadena de digitos enviados
     * @return Si los digitos son correctos retorna true, si hay errores retorna
     * false
     */
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

    /**
     * Prueba si un numero decimal enviado es correcto
     *
     * @param inicio El punto de la cadena en donde se comienza a ver si es
     * decimal
     * @param caracteres Los caracteres que se pondran a prueba
     * @return Si el numero decimal es correcto o erroneo
     */
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

    /**
     * Comprueba los simbolos no validos en el programa
     *
     * @param c El caracter que se comparara
     * @return true si el caracter es un simbolo, y false si no es un simbolo
     */
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
        } else if (c == '"') {
            comprobacion = true;
        }
        return comprobacion;
    }

    /**
     * Comprueba si un caracter es un simbolo valido
     *
     * @param c El caracter que se comprobara
     * @return True si el caracter es un simbolo y false si no lo es
     */
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
    /**
     * Metodo para quitar los espacios en blanco que pueda tener una palabra
     * @param palabra La palabra que se mejorara
     * @return La palabra mejorada
     */
    public String arreglarCadena(String palabra) {
        String nueva_palabra = "";
        boolean arreglo = false;
        int inicio = 0;
        int fin = 0;
        for (int i = 0; i < palabra.length(); i++) {
            if (" ".equals(palabra.substring(i, i + 1))) {
                inicio = i + 1;
                arreglo = true;
            } else {
                i = palabra.length();
            }
        }
        String nuevo = palabra.substring(inicio, palabra.length());
        char caracteres[] = nuevo.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            if (Character.isWhitespace(caracteres[i])) {
                fin = i;
                arreglo = true;
                i = caracteres.length;
            }
        }
        if (arreglo == true) {
            nueva_palabra = palabra.substring(inicio, inicio + fin);
        } else {
            nueva_palabra = palabra;
        }
        return nueva_palabra;
    }
}
