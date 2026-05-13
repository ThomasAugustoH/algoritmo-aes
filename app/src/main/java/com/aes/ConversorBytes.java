package com.aes;

public class ConversorBytes {

    public byte[][] bytesParaMatrizEstado(byte[] bytesEntrada) {
        int tamanho = (int) Math.sqrt(bytesEntrada.length);
        if (Math.pow(tamanho, 2) != bytesEntrada.length) {
            throw new IllegalArgumentException("Bytes de entrada não podem ser convertidos para uma matriz");
        }

        byte[][] matrizEstado = new byte[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizEstado[i][j] = bytesEntrada[i + tamanho * j];
            }
        }

        return matrizEstado;
    }

    public byte[] matrizEstadoParaBytes(byte[][] matrizEstado) {
        int tamanho = (int) Math.pow(matrizEstado.length, 2);
        byte[] bytes = new byte[tamanho];

        for (int i = 0; i < matrizEstado.length; i++) {
            if (matrizEstado[i].length != matrizEstado.length) {
                throw new IllegalArgumentException("Matriz de Estado deve ter tamanho n x n");
            }

            System.arraycopy(matrizEstado[i], 0, bytes, i * matrizEstado.length, matrizEstado.length);
        }

        return bytes;
    }

    public byte[][] bytesParaPalavras(byte[] bytesEntrada) {
        int tamanho = (int) Math.sqrt(bytesEntrada.length);
        if (Math.pow(tamanho, 2) != bytesEntrada.length) {
            throw new IllegalArgumentException("Bytes de entrada não podem ser convertidos para palavras");
        }

        byte[][] palavras = new byte[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                palavras[i][j] = bytesEntrada[i * tamanho + j];
            }
        }

        return palavras;
    }

    public byte[] bytesParaPalavra(byte[] bytesEntrada, int indice) {
        int tamanho = (int) Math.sqrt(bytesEntrada.length);
        if (indice < 0 || indice >= tamanho) {
            throw new IllegalArgumentException("Índice inválido para a palavra");
        }

        return bytesParaPalavras(bytesEntrada)[indice];
    }

    public byte[] palavrasParaByte(byte[][] palavras) {
        int tamanho = (int) Math.pow(palavras.length, 2);
        byte[] bytes = new byte[tamanho];

        for (int i = 0; i < palavras.length; i++) {
            if (palavras[i].length != palavras.length) {
                throw new IllegalArgumentException("Palavras devem formar uma matriz n x n");
            }

            for (int j = 0; j < palavras.length; j++) {
                bytes[i] = palavras[i][j];
            }
        }

        return bytes;
    }

    public byte realizarXor(byte valorA, byte valorB) {
        return (byte) (valorA ^ valorB);
    }

    public byte[][] realizarXor(byte[][] matrizA, byte[][] matrizB) {
        if (matrizA.length != matrizB.length)
            throw new IllegalArgumentException("Matrizes devem ter tamanhos iguais!");

        byte[][] matrizResultado = new byte[matrizA.length][matrizA.length];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizResultado[i][j] = realizarXor(matrizA[i][j], matrizB[i][j]);
            }
        }

        return matrizResultado;
    }
}
