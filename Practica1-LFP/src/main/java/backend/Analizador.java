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
        ArrayList<Integer> tokens = new ArrayList<Integer>();
        tokens.add(0);
        int x = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (" ".equals(texto.substring(i, i + 1))) {
                if (" ".equals(texto.substring(i + 1, i + 2))) {

                } else {
                    tokens.add(i);
                    tokens.add(i + 1);
                    x++;
                }

            }
        }
        tokens.add(texto.length());
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i));
        }
        ArrayList<String> palabras = new ArrayList<String>();
        for (int i = 0; i < tokens.size(); i++) {
            palabras.add(texto.substring(tokens.get(i), tokens.get(i + 1)));
            i++;
        }
        for (int i = 0; i < palabras.size(); i++) {
            System.out.println(palabras.get(i));
        }
    }

}
