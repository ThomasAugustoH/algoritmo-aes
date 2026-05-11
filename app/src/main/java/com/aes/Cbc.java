package com.aes;

import java.util.ArrayList;

public class Cbc extends ModoOperacao {

    @Override
    public void cifrar(byte[] textoSimples, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }
        if (this.vetorInicializacao == null) {
            throw new IllegalArgumentException("Vetor de inicialização não informado!");
        }

    }

    @Override
    public void decifrar(byte[] textoCifrado, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }
        if (this.vetorInicializacao == null) {
            throw new IllegalArgumentException("Vetor de inicialização não informado!");
        }

        ArrayList<byte[]> blocos = super.dividirBlocos(textoCifrado);

    }
}
