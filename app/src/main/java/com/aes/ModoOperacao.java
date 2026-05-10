package com.aes;

import java.util.ArrayList;

public abstract class ModoOperacao {

    protected byte[] textoSimples;
    protected byte[] textoCifrado;
    protected Chave chave;
    protected Chave vetorInicializacao;
    protected Aes aes = new Aes();
    protected Padding padding = new Padding();

    public abstract void cifrar(byte[] textoSimples, String nomeArquivoSaida);

    public abstract void decifrar(byte[] textoCifrado, String nomeArquivoSaida);

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public void setVetorInicializacao(Chave vetorInicializacao) {
        this.vetorInicializacao = vetorInicializacao;
    }

    protected ArrayList<byte[]> dividirBlocos(byte[] texto) {
        ArrayList<byte[]> blocos = new ArrayList<>();
        int numeroBlocos = (int) Math.ceil(texto.length / aes.TAM_BLOCO);

        if (texto.length % aes.TAM_BLOCO == 0) {
            numeroBlocos++;
        }

        for (int i = 0; i < numeroBlocos; i++) {
            byte[] bloco = new byte[aes.TAM_BLOCO];
            for (int j = 0; j < aes.TAM_BLOCO; j++) {
                int indiceTexto = aes.TAM_BLOCO * i + j;
                if (texto.length < indiceTexto) {
                    bloco[j] = texto[indiceTexto]; 
                }else {
                    bloco = padding.preencher(bloco, j);
                    break;
                }
            }
            blocos.add(bloco);
        }

        return blocos;
    }

}
