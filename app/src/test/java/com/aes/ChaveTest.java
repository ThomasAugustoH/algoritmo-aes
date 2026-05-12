package com.aes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ChaveTest {

    /*
        Chave deve ter tamanho == 16
     */
    @Test
    void lancarExcecaoChaveStringMenorQueOPermitido() {
        String chave17 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17";

        assertThrows(IllegalArgumentException.class, () -> new Chave(chave17));

        String chave15 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15";

        assertThrows(IllegalArgumentException.class, () -> new Chave(chave15));
    }

    /*
        Chave deve ter tamanho == 16
     */
    @Test
    void lancarExcecaoChaveByteMenorQueOPermitido() {
        byte[] chave17 = new byte[17];

        assertThrows(IllegalArgumentException.class, () -> new Chave(chave17));

        byte[] chave15 = new byte[15];

        assertThrows(IllegalArgumentException.class, () -> new Chave(chave15));
    }

    /*
        Chave deve ter valores entre 0 e 255
     */
    @Test
    void lancarExcecaoChaveValoresInvalidos() {
        byte[] chave1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, -1, 11, 12, 13, 14, 15, 16};

        assertThrows(IllegalArgumentException.class, () -> new Chave(chave1));

        String chave2 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 256, 11, 12, 13, 14, 15, 16";

        assertThrows(IllegalArgumentException.class, () -> new Chave(chave2));

    }

    /*
        Chave deve ter 4 palavras
     */
    @Test
    void chaveComNumeroInvalidoDePalavras() {
        byte[][] palavras = {
            {
                1, 2, 3, 4
            },
            {
                5, 6, 7, 8
            },
            {
                9, 10, 11, 12
            }
        };
        assertThrows(IllegalArgumentException.class, () -> new Chave(palavras));
    }

    /*
        Chave deve ter palavras de 4 bytes
     */
    @Test
    void chaveComPalavrasInvalidas() {
        byte[][] palavras = {
            {
                1, 2, 3, 4
            },
            {
                5, 6, 7, 8
            },
            {
                9, 10, 11, 12
            },
            {
                13, 14, 15, 16, 17
            }
        };
        assertThrows(IllegalArgumentException.class, () -> new Chave(palavras));
    }

    /*
        Chave válida
     */
    @Test
    void chaveComEntradaValida() {
        byte[] chave1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        assertDoesNotThrow(() -> new Chave(chave1));

        String chave2 = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16";

        assertDoesNotThrow(() -> new Chave(chave2));

    }

    /*
        Chave válida formada por palavras
     */
    @Test
    void chaveComEntradaDePalavrasValidas() {
        byte[][] palavras = {
            {
                1, 2, 3, 4
            },
            {
                5, 6, 7, 8
            },
            {
                9, 10, 11, 12
            },
            {
                13, 14, 15, 16
            }
        };
        Chave chave = new Chave(palavras);

        assertArrayEquals(palavras[2], chave.getPalavra(2));
    }

    /*
        Validar chaves equivalentes
     */
    @Test
    void chavesEquivalentes() {
        byte[][] palavras = {
            {
                1, 2, 3, 4
            },
            {
                5, 6, 7, 8
            },
            {
                9, 10, 11, 12
            },
            {
                13, 14, 15, 16
            }
        };
        Chave chave1 = new Chave(palavras);

        byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Chave chave2 = new Chave(bytes);

        String string = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16";
        Chave chave3 = new Chave(string);


        assertArrayEquals(chave1.getMatrizEstado(), chave2.getMatrizEstado());
        assertArrayEquals(chave1.getMatrizEstado(), chave3.getMatrizEstado());
    }

    /*
        Chave deve retornar apenas palavras de índice 0 a 3
     */
    @Test
    void palavraTemIndiceValido() {
        byte[] chaveByte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Chave chave = new Chave(chaveByte);
        byte[] palavra = {9, 10, 11, 12};

        assertThrows(IllegalArgumentException.class, () -> chave.getPalavra(-1));
        assertThrows(IllegalArgumentException.class, () -> chave.getPalavra(4));
    }

    /*
        Chave deve retornar palavra correta
     */
    @Test
    void palavraCorreta() {
        byte[] chaveByte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Chave chave = new Chave(chaveByte);
        byte[] palavra = {9, 10, 11, 12};

        assertArrayEquals(palavra, chave.getPalavra(2));
    }

    /*
        Chave deve retornar matriz de estados correta
     */
    @Test
    void matrizDeEstadosCorreta() {
        byte[] chaveByte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Chave chave = new Chave(chaveByte);
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

        assertArrayEquals(matrizEstado, chave.getMatrizEstado());
    }

}
