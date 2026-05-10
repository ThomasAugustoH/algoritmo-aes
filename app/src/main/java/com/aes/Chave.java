package com.aes;

public class Chave {

    private String chaveString;
    private byte[] chaveBytes;

    public Chave(String chaveString) {
        setChave(chaveString);
    }

    public Chave(byte[] chaveBytes) {
        setChave(chaveBytes);
    }

    private void setChave(String chaveString) {
        chaveString = chaveString.trim();
        int tamChave = chaveString.length();
        int tamChaveLimpa = chaveString.replace(",", "").length();

        if (tamChave - tamChaveLimpa != 15) {
            throw new IllegalArgumentException("Tamanho de chave inválido! Separe 16 valores por vírgula.");
        }

        String[] valores = chaveString.split(",");
        byte[] valoresByte = new byte[tamChave - tamChaveLimpa + 1];

        for (int i = 0; i < valoresByte.length; i++) {
            int valor = Integer.parseInt(valores[i].trim());
            if (valor < 0 || valor > 255) {
                throw new IllegalArgumentException("Valor de chave inválido no índice " + i);
            }
            valoresByte[i] = (byte) valor;
        }

        this.chaveString = chaveString;
        this.chaveBytes = valoresByte;
    }

    private void setChave(byte[] chaveBytes) {
        String chaveFormatada = "";

        for (int i = 0; i < chaveBytes.length; i++) {
            chaveFormatada += (int) chaveBytes[i];
            chaveFormatada += ",";
        }
        chaveFormatada = chaveFormatada.substring(0, chaveFormatada.length() - 1);

        setChave(chaveFormatada);
    }

    public String getChaveString() {
        return chaveString;
    }

    public byte[] getChaveBytes() {
        return chaveBytes;
    }

    public byte[] getPalavra(int index) {
        if (index < 0 || index > 3) {
            throw new IllegalArgumentException("Palavra de índice " + index + " inválida");
        }

        byte[] palavra = new byte[4];
        for (int i = 0; i < 4; i++) {
            palavra[i] = chaveBytes[index * 4 + i];
        }

        return palavra;
    }

    public byte[][] getMatrizEstado() {

        byte[][] matrizEstado = new byte[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizEstado[j][i] = chaveBytes[i * 4  + j];
            }
        }

        return matrizEstado;
    }
}
