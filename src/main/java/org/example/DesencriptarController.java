package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesencriptarController {

    @GetMapping("/desencriptar/{textoEncriptado}")
    public String desencriptarTexto(@PathVariable String textoEncriptado) {
        return desencriptar(textoEncriptado);
    }

    public static String desencriptar(String textoEncriptado) {
        return getString(textoEncriptado);
    }

    static String getString(String textoEncriptado) {
        int desplazamiento = 3; // Suponiendo un desplazamiento de 3 como en tu método original
        StringBuilder textoOriginal = new StringBuilder();

        for (char caracter : textoEncriptado.toCharArray()) {
            if (Character.isLetter(caracter)) {
                char caracterOriginal = (char) (((caracter - 'a' - desplazamiento + 26) % 26) + 'a');
                textoOriginal.append(caracterOriginal);
            } else {
                textoOriginal.append(caracter);
            }
        }

        return textoOriginal.toString();
    }
}
