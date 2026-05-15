package com.aes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AesTest {

    Aes aes = new Aes();

    @Test
    void addRoundKeyResultadoCorreto() {
        byte[][] matrizEstado = {
            {(byte) 0x44, (byte) 0x4e, (byte) 0x56, (byte) 0x4e},
            {(byte) 0x45, (byte) 0x56, (byte) 0x49, (byte) 0x54},
            {(byte) 0x53, (byte) 0x4f, (byte) 0x4d, (byte) 0x4f},
            {(byte) 0x45, (byte) 0x4c, (byte) 0x45, (byte) 0x21},};

        byte[][] matrizChave = {
            {(byte) 0x41, (byte) 0x45, (byte) 0x49, (byte) 0x4d},
            {(byte) 0x42, (byte) 0x46, (byte) 0x4a, (byte) 0x4e},
            {(byte) 0x43, (byte) 0x47, (byte) 0x4b, (byte) 0x4f},
            {(byte) 0x44, (byte) 0x48, (byte) 0x4c, (byte) 0x50},};

        byte[][] matrizEsperada = {
            {(byte) 0x05, (byte) 0x0b, (byte) 0x1f, (byte) 0x03},
            {(byte) 0x07, (byte) 0x10, (byte) 0x03, (byte) 0x1a},
            {(byte) 0x10, (byte) 0x08, (byte) 0x06, (byte) 0x00},
            {(byte) 0x01, (byte) 0x04, (byte) 0x09, (byte) 0x71},};

        assertArrayEquals(matrizEsperada, aes.addRoundKey(matrizEstado, matrizChave));
    }

    @Test
    void subBytesCorreto() {
        byte[][] matrizEstado = {
            {(byte) 0x05, (byte) 0x0b, (byte) 0x1f, (byte) 0x03},
            {(byte) 0x07, (byte) 0x10, (byte) 0x03, (byte) 0x1a},
            {(byte) 0x10, (byte) 0x08, (byte) 0x06, (byte) 0x00},
            {(byte) 0x01, (byte) 0x04, (byte) 0x09, (byte) 0x71},};

        byte[][] matrizEsperada = {
            {(byte) 0x6b, (byte) 0x2b, (byte) 0xc0, (byte) 0x7b},
            {(byte) 0xc5, (byte) 0xca, (byte) 0x7b, (byte) 0xa2},
            {(byte) 0xca, (byte) 0x30, (byte) 0x6f, (byte) 0x63},
            {(byte) 0x7c, (byte) 0xf2, (byte) 0x01, (byte) 0xa3},};

        assertArrayEquals(matrizEsperada, aes.subBytes(matrizEstado));
    }

    @Test
    void inversoSubBytesCorreto() {
        byte[][] matrizEstado = {
            {(byte) 0xe9, (byte) 0xcb, (byte) 0x3d, (byte) 0xaf},
            {(byte) 0x09, (byte) 0x31, (byte) 0x32, (byte) 0x2e},
            {(byte) 0x89, (byte) 0x07, (byte) 0x7d, (byte) 0x2c},
            {(byte) 0x72, (byte) 0x5f, (byte) 0x94, (byte) 0xb5},};

        byte[][] matrizEsperada = {
            {(byte) 0xeb, (byte) 0x59, (byte) 0x8b, (byte) 0x1b},
            {(byte) 0x40, (byte) 0x2e, (byte) 0xa1, (byte) 0xc3},
            {(byte) 0xf2, (byte) 0x38, (byte) 0x13, (byte) 0x42},
            {(byte) 0x1e, (byte) 0x84, (byte) 0xe7, (byte) 0xd2},};

        assertArrayEquals(matrizEsperada, aes.subBytes(true, matrizEstado));
    }

    @Test
    void shiftRowsCorreto() {
        byte[][] matrizEstado = {
            {(byte) 0x6b, (byte) 0x2b, (byte) 0xc0, (byte) 0x7b},
            {(byte) 0xc5, (byte) 0xca, (byte) 0x7b, (byte) 0xa2},
            {(byte) 0xca, (byte) 0x30, (byte) 0x6f, (byte) 0x63},
            {(byte) 0x7c, (byte) 0xf2, (byte) 0x01, (byte) 0xa3},};

        byte[][] matrizEsperada = {
            {(byte) 0x6b, (byte) 0x2b, (byte) 0xc0, (byte) 0x7b},
            {(byte) 0xca, (byte) 0x7b, (byte) 0xa2, (byte) 0xc5},
            {(byte) 0x6f, (byte) 0x63, (byte) 0xca, (byte) 0x30},
            {(byte) 0xa3, (byte) 0x7c, (byte) 0xf2, (byte) 0x01},};

        assertArrayEquals(matrizEsperada, aes.shiftRows(matrizEstado));
    }

    @Test
    void inversoShiftRowsCorreto() {
        byte[][] matrizEstado = {
            {(byte) 0xe9, (byte) 0xcb, (byte) 0x3d, (byte) 0xaf},
            {(byte) 0x31, (byte) 0x32, (byte) 0x2e, (byte) 0x09},
            {(byte) 0x7d, (byte) 0x2c, (byte) 0x89, (byte) 0x07},
            {(byte) 0xb5, (byte) 0x72, (byte) 0x5f, (byte) 0x94},};

        byte[][] matrizEsperada = {
            {(byte) 0xe9, (byte) 0xcb, (byte) 0x3d, (byte) 0xaf},
            {(byte) 0x09, (byte) 0x31, (byte) 0x32, (byte) 0x2e},
            {(byte) 0x89, (byte) 0x07, (byte) 0x7d, (byte) 0x2c},
            {(byte) 0x72, (byte) 0x5f, (byte) 0x94, (byte) 0xb5},};

        assertArrayEquals(matrizEsperada, aes.shiftRows(true, matrizEstado));
    }

    @Test
    void multiplicacaoGaloisCorreto() {
        byte entrada = (byte) 0x6b;
        byte esperado = (byte) 0xd6;

        assertEquals(esperado, aes.multiplicacaoGalois(entrada, 2));
    }

    @Test
    void mixColumnsCorreto() {
        byte[][] matrizEstado = {
            {(byte) 0x6b, (byte) 0x2b, (byte) 0xc0, (byte) 0x7b},
            {(byte) 0xca, (byte) 0x7b, (byte) 0xa2, (byte) 0xc5},
            {(byte) 0x6f, (byte) 0x63, (byte) 0xca, (byte) 0x30},
            {(byte) 0xa3, (byte) 0x7c, (byte) 0xf2, (byte) 0x01},};

        byte[][] matrizEsperada = {
            {(byte) 0x5f, (byte) 0xc4, (byte) 0x5e, (byte) 0x93},
            {(byte) 0xf6, (byte) 0x04, (byte) 0x28, (byte) 0xbb},
            {(byte) 0x81, (byte) 0x12, (byte) 0xe0, (byte) 0xdd},
            {(byte) 0x45, (byte) 0x9d, (byte) 0xcc, (byte) 0x7a},};

        assertArrayEquals(matrizEsperada, aes.mixColumns(matrizEstado));
    }

    @Test
    void inversoMixColumnsCorreto() {
        byte[][] matrizEstado = {
            {(byte) 0xeb, (byte) 0x59, (byte) 0x8b, (byte) 0x1b},
            {(byte) 0x40, (byte) 0x2e, (byte) 0xa1, (byte) 0xc3},
            {(byte) 0xf2, (byte) 0x38, (byte) 0x13, (byte) 0x42},
            {(byte) 0x1e, (byte) 0x84, (byte) 0xe7, (byte) 0xd2},};

        byte[][] matrizEsperada = {
            {(byte) 0x8b, (byte) 0xe1, (byte) 0xfd, (byte) 0xf1},
            {(byte) 0x15, (byte) 0x55, (byte) 0xa9, (byte) 0xa0},
            {(byte) 0x1c, (byte) 0x0d, (byte) 0xc2, (byte) 0x38},
            {(byte) 0xc5, (byte) 0x72, (byte) 0x48, (byte) 0x21},};

        assertArrayEquals(matrizEsperada, aes.mixColumns(true, matrizEstado));
    }

}
