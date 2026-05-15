package com.aes;

public class BlocoAes {

    private String textoEmString;
    private byte[] textoEmBytes;
    private ConversorBytes conversorBytes = new ConversorBytes();

    public BlocoAes(byte[] bytes) {
        setBloco(bytes);
    }

    public BlocoAes(String string) {
        setBloco(string);
    }

    public void setBloco(byte[] textoEmBytes) {
        String formatoTextual = "";

        for (int i = 0; i < textoEmBytes.length; i++) {
            formatoTextual += Byte.toUnsignedInt(textoEmBytes[i]);
            formatoTextual += ",";
        }
        formatoTextual = formatoTextual.substring(0, formatoTextual.length() - 1);

        setBloco(formatoTextual);
    }

    public void setBloco(String textoEmString) {
        textoEmString = textoEmString.trim();
        int tamTexto = textoEmString.length();
        int tamTextoLimpo = textoEmString.replace(",", "").length();

        if (tamTexto - tamTextoLimpo != 15)
            throw new IllegalArgumentException("Tamanho de texto inválido! Separe 16 valores por vírgula.");

        String[] valores = textoEmString.split(",");
        byte[] valoresByte = new byte[tamTexto - tamTextoLimpo + 1];

        for (int i = 0; i < valoresByte.length; i++) {
            int valor = Integer.parseInt(valores[i].trim());
            if (valor < 0 || valor > 255)
                throw new IllegalArgumentException("Valor de texto inválido no índice " + i);
            valoresByte[i] = (byte) valor;
        }

        this.textoEmString = textoEmString;
        this.textoEmBytes = valoresByte;
    }

    public String gettextoEmString() {
        return textoEmString;
    }

    public byte[] getTextoEmBytes() {
        return textoEmBytes;
    }

    public byte[] getPalavra(int index) {
        if (index < 0 || index > 3) {
            throw new IllegalArgumentException("Palavra de índice " + index + " inválida");
        }

        byte[] palavra = conversorBytes.bytesParaPalavra(textoEmBytes, index);

        return palavra;
    }

    public byte[][] getMatrizEstado() {

        byte[][] matrizEstado = conversorBytes.bytesParaMatrizEstado(textoEmBytes);

        return matrizEstado;
    }
}
