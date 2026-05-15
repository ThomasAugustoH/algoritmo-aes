package com.aes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ConversorBytesTest {

    private ConversorBytes conversorBytes = new ConversorBytes();

    @Test
    void bytesParaMatrizTamanhoErrado() {
        byte[] bloco = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79};

        assertThrows(IllegalArgumentException.class, () -> conversorBytes.bytesParaMatrizEstado(bloco));
    }

    @Test
    void bytesParaMatrizEstado() {
        byte[] bloco = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
        byte[][] matrizEsperada = {
            {65, 69, 73, 77},
            {66, 70, 74, 78},
            {67, 71, 75, 79},
            {68, 72, 76, 80}
        };

        assertArrayEquals(matrizEsperada, conversorBytes.bytesParaMatrizEstado(bloco));
    }

    @Test
    void matrizParaBytesTamanhoErrado() {
        byte[][] matriz = {
            {65, 69, 73},
            {66, 70, 74},
            {67, 71, 75},
            {68, 72, 76}
        };

        assertThrows(IllegalArgumentException.class, () -> conversorBytes.matrizEstadoParaBytes(matriz));
    }

    @Test
    void matrizParaBytes() {
        byte[][] matriz = {
            {65, 69, 73, 77},
            {66, 70, 74, 78},
            {67, 71, 75, 79},
            {68, 72, 76, 80}
        };
        byte[] blocoEsperado = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};

        assertArrayEquals(blocoEsperado, conversorBytes.matrizEstadoParaBytes(matriz));
    }

    @Test
    void bytesParaPalavraTamanhoErrado() {
        byte[] bloco = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79};

        assertThrows(IllegalArgumentException.class, () -> conversorBytes.bytesParaPalavras(bloco));
    }

    @Test
    void bytesParaPalavras() {
        byte[] bloco = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
        byte[][] palavrasEsperadas = {
            {65, 66, 67, 68},
            {69, 70, 71, 72},
            {73, 74, 75, 76},
            {77, 78, 79, 80}
        };

        assertArrayEquals(palavrasEsperadas, conversorBytes.bytesParaPalavras(bloco));
    }

    @Test
    void bytesParaPalavraIndiceInvalido() {
        byte[] bloco = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};

        assertThrows(IllegalArgumentException.class, () -> conversorBytes.bytesParaPalavra(bloco, 4));
        assertThrows(IllegalArgumentException.class, () -> conversorBytes.bytesParaPalavra(bloco, -1));
    }

    @Test
    void bytesParaPalavra() {
        byte[] bloco = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
        byte[] palavraEsperada = {69, 70, 71, 72};

        assertArrayEquals(palavraEsperada, conversorBytes.bytesParaPalavra(bloco, 1));
    }

    @Test
    void palavrasParaBytesTamanhoErrado() {
        byte[][] palavras = {
            {65, 66, 67},
            {69, 70, 71},
            {73, 74, 75},
            {77, 77, 79}
        };

        assertThrows(IllegalArgumentException.class, () -> conversorBytes.palavrasParaByte(palavras));
    }

    @Test
    void palavrasParaBytes() {
        byte[][] palavras = {
            {65, 66, 67, 68},
            {69, 70, 71, 72},
            {73, 74, 75, 76},
            {77, 78, 79, 80}
        };
        byte[] blocoEsperado = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};

        assertArrayEquals(blocoEsperado, conversorBytes.palavrasParaByte(palavras));
    }

    @Test
    void realizarXor() {
        byte termo1 = 68;
        byte termo2 = 65;
        byte termoEsperado = 5;

        assertEquals(termoEsperado, conversorBytes.realizarXor(termo1, termo2));
    }

    @Test
    void realizarXorMatrizes() {
        byte[][] matriz1 = {
            {68, 78, 86, 78},
            {69, 86, 73, 84},
            {83, 79, 77, 79},
            {69, 76, 69, 33}
        };
        byte[][] matriz2 = {
            {65, 69, 73, 77},
            {66, 70, 74, 78},
            {67, 71, 75, 79},
            {68, 72, 76, 80}
        };
        byte[][] matrizEsperada = {
            {5, 11, 31, 3},
            {7, 16, 3, 26},
            {16, 8, 6, 0},
            {1, 4, 9, 113}
        };

        assertArrayEquals(matrizEsperada, conversorBytes.realizarXor(matriz1, matriz2));
    }

}
