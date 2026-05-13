package com.aes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BlocoAesTest {

    /*
        bloco deve ter tamanho == 16
     */
    @Test
    void lancarExcecaoblocoStringMenorQueOPermitido() {
        String bloco17 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17";

        assertThrows(IllegalArgumentException.class, () -> new BlocoAes(bloco17));

        String bloco15 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15";

        assertThrows(IllegalArgumentException.class, () -> new BlocoAes(bloco15));
    }

    /*
        bloco deve ter tamanho == 16
     */
    @Test
    void lancarExcecaoblocoByteMenorQueOPermitido() {
        byte[] bloco17 = new byte[17];

        assertThrows(IllegalArgumentException.class, () -> new BlocoAes(bloco17));

        byte[] bloco15 = new byte[15];

        assertThrows(IllegalArgumentException.class, () -> new BlocoAes(bloco15));
    }

    /*
        bloco deve ter valores entre 0 e 255
     */
    @Test
    void lancarExcecaoblocoValoresInvalidos() {
        byte[] bloco1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, -1, 11, 12, 13, 14, 15, 16};

        assertThrows(IllegalArgumentException.class, () -> new BlocoAes(bloco1));

        String bloco2 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 256, 11, 12, 13, 14, 15, 16";

        assertThrows(IllegalArgumentException.class, () -> new BlocoAes(bloco2));

    }

    /*
        bloco válida
     */
    @Test
    void blocoComEntradaValida() {
        byte[] bloco1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        assertDoesNotThrow(() -> new BlocoAes(bloco1));

        String bloco2 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16";

        assertDoesNotThrow(() -> new BlocoAes(bloco2));

    }

    /*
        Validar blocos equivalentes
     */
    @Test
    void blocosEquivalentes() {
        byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        BlocoAes bloco1 = new BlocoAes(bytes);

        String string = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16";
        BlocoAes bloco2 = new BlocoAes(string);


        assertArrayEquals(bloco1.getMatrizEstado(), bloco2.getMatrizEstado());
    }

    /*
        bloco deve retornar apenas palavras de índice 0 a 3
     */
    @Test
    void palavraTemIndiceValido() {
        byte[] blocoByte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        BlocoAes bloco = new BlocoAes(blocoByte);
        byte[] palavra = {9, 10, 11, 12};

        assertThrows(IllegalArgumentException.class, () -> bloco.getPalavra(-1));
        assertThrows(IllegalArgumentException.class, () -> bloco.getPalavra(4));
    }

    /*
        bloco deve retornar palavra correta
     */
    @Test
    void palavraCorreta() {
        byte[] blocoByte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        BlocoAes bloco = new BlocoAes(blocoByte);
        byte[] palavra = {9, 10, 11, 12};

        assertArrayEquals(palavra, bloco.getPalavra(2));
    }

    /*
        bloco deve retornar matriz de estados correta
     */
    @Test
    void matrizDeEstadosCorreta() {
        byte[] blocoByte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        BlocoAes bloco = new BlocoAes(blocoByte);
        byte[][] matrizEstado = {
            {
                1, 5, 9, 13
            },
            {
                2, 6, 10, 14
            },
            {
                3, 7, 11, 15
            },
            {
                4, 8, 12, 16
            }
        };

        assertArrayEquals(matrizEstado, bloco.getMatrizEstado());
    }

}
