package com.aes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GerenciadorTabelasTest {

    private final GerenciadorTabelas gerenciador = new GerenciadorTabelas();

    @Test
    void substituirByteComSbox() {
        assertEquals((byte) 0x6b, gerenciador.substituir((byte) 0x05, TabelasEnum.S_BOX));
        assertEquals((byte) 0x2b, gerenciador.substituir((byte) 0x0b, TabelasEnum.S_BOX));
        assertEquals((byte) 0xc0, gerenciador.substituir((byte) 0x1f, TabelasEnum.S_BOX));
        assertEquals((byte) 0x7b, gerenciador.substituir((byte) 0x03, TabelasEnum.S_BOX));
        assertEquals((byte) 0xc5, gerenciador.substituir((byte) 0x07, TabelasEnum.S_BOX));
        assertEquals((byte) 0xca, gerenciador.substituir((byte) 0x10, TabelasEnum.S_BOX));
        assertEquals((byte) 0x7b, gerenciador.substituir((byte) 0x03, TabelasEnum.S_BOX));
        assertEquals((byte) 0xa2, gerenciador.substituir((byte) 0x1a, TabelasEnum.S_BOX));
        assertEquals((byte) 0xca, gerenciador.substituir((byte) 0x10, TabelasEnum.S_BOX));
        assertEquals((byte) 0x30, gerenciador.substituir((byte) 0x08, TabelasEnum.S_BOX));
        assertEquals((byte) 0x6f, gerenciador.substituir((byte) 0x06, TabelasEnum.S_BOX));
        assertEquals((byte) 0x63, gerenciador.substituir((byte) 0x00, TabelasEnum.S_BOX));
        assertEquals((byte) 0x7c, gerenciador.substituir((byte) 0x01, TabelasEnum.S_BOX));
        assertEquals((byte) 0xf2, gerenciador.substituir((byte) 0x04, TabelasEnum.S_BOX));
        assertEquals((byte) 0x01, gerenciador.substituir((byte) 0x09, TabelasEnum.S_BOX));
        assertEquals((byte) 0xa3, gerenciador.substituir((byte) 0x71, TabelasEnum.S_BOX));
    }

    @Test
    void substituirMatrizComSbox() {
        byte[][] matriz = {
            {(byte) 0x05, (byte) 0x0b, (byte) 0x1f, (byte) 0x03},
            {(byte) 0x07, (byte) 0x10, (byte) 0x03, (byte) 0x1a},
            {(byte) 0x10, (byte) 0x08, (byte) 0x06, (byte) 0x00},
            {(byte) 0x01, (byte) 0x04, (byte) 0x09, (byte) 0x71},};

        byte[][] matrizEsperada = {
            {(byte) 0x6b, (byte) 0x2b, (byte) 0xc0, (byte) 0x7b},
            {(byte) 0xc5, (byte) 0xca, (byte) 0x7b, (byte) 0xa2},
            {(byte) 0xca, (byte) 0x30, (byte) 0x6f, (byte) 0x63},
            {(byte) 0x7c, (byte) 0xf2, (byte) 0x01, (byte) 0xa3},};

        assertArrayEquals(matrizEsperada, gerenciador.substituir(matriz, TabelasEnum.S_BOX));
    }

}
