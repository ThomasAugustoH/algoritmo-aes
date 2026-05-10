package com.aes;

public class Padding {

    public byte[] preencher(byte[] bloco, int espacoPreenchido) {

        int vazios = bloco.length - espacoPreenchido;
        byte[] blocoFinal = bloco;

        for (int i = espacoPreenchido; i < bloco.length; i++) {
            blocoFinal[i] = (byte) vazios;
        }

        return blocoFinal;
    }
}
