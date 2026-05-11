package com.aes;

import java.util.ArrayList;

public class Ecbb extends ModoOperacao {

    @Override
    public void cifrar(byte[] textoSimples, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }

        ArrayList<byte[]> blocos = super.dividirBlocos(textoCifrado);
    }

    @Override
    public void decifrar(byte[] textoCifrado, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }

    }

}
