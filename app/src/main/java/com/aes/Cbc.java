package com.aes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Cbc extends ModoOperacao {

    @Override
    public void cifrar(byte[] textoSimples, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }
        if (this.vetorInicializacao == null) {
            throw new IllegalArgumentException("Vetor de inicialização não informado!");
        }
        this.textoSimples = textoSimples;

        aes.setChave(this.chave);

        byte[] textoPreenchido = aplicarPadding(textoSimples);
        byte[] textoCifradoLocal = new byte[textoPreenchido.length];

        byte[] blocoAnterior = Arrays.copyOf(vetorInicializacao.getTextoEmBytes(), aes.TAM_BLOCO);
        for (int offset = 0; offset < textoPreenchido.length; offset += aes.TAM_BLOCO) {
            byte[] bloco = Arrays.copyOfRange(textoPreenchido, offset, offset + aes.TAM_BLOCO);
            byte[] blocoXor = realizarXor(bloco, blocoAnterior);
            byte[] blocoCifrado = aes.cifrar(blocoXor);
            System.arraycopy(blocoCifrado, 0, textoCifradoLocal, offset, aes.TAM_BLOCO);
            blocoAnterior = blocoCifrado;
        }

        this.textoCifrado = textoCifradoLocal;
        salvarArquivo(textoCifradoLocal, nomeArquivoSaida);
    }

    @Override
    public void decifrar(byte[] textoCifrado, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }
        if (this.vetorInicializacao == null) {
            throw new IllegalArgumentException("Vetor de inicialização não informado!");
        }
        if (textoCifrado.length % aes.TAM_BLOCO != 0) {
            throw new IllegalArgumentException("Texto cifrado invalido.");
        }
        this.textoCifrado = textoCifrado;

        BlocoAes[] roundKeys = new ExpansorChave().expandirChave(this.chave);
        byte[] textoDecifrado = new byte[textoCifrado.length];

        byte[] blocoAnterior = Arrays.copyOf(vetorInicializacao.getTextoEmBytes(), aes.TAM_BLOCO);
        for (int offset = 0; offset < textoCifrado.length; offset += aes.TAM_BLOCO) {
            byte[] bloco = Arrays.copyOfRange(textoCifrado, offset, offset + aes.TAM_BLOCO);
            byte[] blocoDecifrado = decifrarBloco(bloco, roundKeys);
            byte[] blocoSimples = realizarXor(blocoDecifrado, blocoAnterior);
            System.arraycopy(blocoSimples, 0, textoDecifrado, offset, aes.TAM_BLOCO);
            blocoAnterior = bloco;
        }

        byte[] textoSemPadding = removerPadding(textoDecifrado);
        this.textoSimples = textoSemPadding;
        salvarArquivo(textoSemPadding, nomeArquivoSaida);
    }

    private byte[] aplicarPadding(byte[] dados) {
        int tamanhoBloco = aes.TAM_BLOCO;
        int padding = tamanhoBloco - (dados.length % tamanhoBloco);
        if (padding == 0) {
            padding = tamanhoBloco;
        }

        byte[] resultado = Arrays.copyOf(dados, dados.length + padding);
        Arrays.fill(resultado, dados.length, resultado.length, (byte) padding);

        return resultado;
    }

    private byte[] removerPadding(byte[] dados) {
        if (dados.length == 0 || dados.length % aes.TAM_BLOCO != 0) {
            throw new IllegalArgumentException("Padding invalido.");
        }

        int padding = Byte.toUnsignedInt(dados[dados.length - 1]);
        if (padding == 0 || padding > aes.TAM_BLOCO) {
            throw new IllegalArgumentException("Padding invalido.");
        }

        int inicioPadding = dados.length - padding;
        for (int i = inicioPadding; i < dados.length; i++) {
            if (Byte.toUnsignedInt(dados[i]) != padding) {
                throw new IllegalArgumentException("Padding invalido.");
            }
        }

        return Arrays.copyOf(dados, inicioPadding);
    }

    private byte[] decifrarBloco(byte[] bloco, BlocoAes[] roundKeys) {
        ConversorBytes conversor = new ConversorBytes();
        byte[][] estado = conversor.bytesParaMatrizEstado(bloco);

        estado = aes.addRoundKey(estado, roundKeys[roundKeys.length - 1].getMatrizEstado());

        for (int i = roundKeys.length - 2; i >= 0; i--) {
            estado = aes.shiftRows(true, estado);
            estado = aes.subBytes(true, estado);
            estado = aes.addRoundKey(estado, roundKeys[i].getMatrizEstado());
            if (i != 0) {
                estado = aes.mixColumns(true, estado);
            }
        }

        return conversor.matrizEstadoParaBytes(estado);
    }

    private byte[] realizarXor(byte[] blocoA, byte[] blocoB) {
        byte[] resultado = new byte[aes.TAM_BLOCO];
        for (int i = 0; i < aes.TAM_BLOCO; i++) {
            resultado[i] = (byte) (blocoA[i] ^ blocoB[i]);
        }
        return resultado;
    }

    private void salvarArquivo(byte[] dados, String nomeArquivoSaida) {
        try {
            Files.write(Path.of(nomeArquivoSaida), dados);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo.", e);
        }
    }
}
