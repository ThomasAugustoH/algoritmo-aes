package com.aes;

public class Aes {

    public final int TAM_BLOCO = 16;
    private BlocoAes[] roundKeys;
    private GerenciadorTabelas gerenciadorTabelas = new GerenciadorTabelas();
    private ConversorBytes conversorBytes = new ConversorBytes();

    public byte[] cifrar(byte[] blocoEmBytes) {
        if (this.roundKeys == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }

        BlocoAes bloco = new BlocoAes(blocoEmBytes);
        byte[][] matrizEstadoAtual = bloco.getMatrizEstado();
        matrizEstadoAtual = addRoundKey(matrizEstadoAtual, 0);

        for (int i = 1; i < roundKeys.length; i++) {
            matrizEstadoAtual = subBytes(matrizEstadoAtual);
            matrizEstadoAtual = shiftRows(matrizEstadoAtual);
            if (i != roundKeys.length) {
                matrizEstadoAtual = mixColumns(matrizEstadoAtual);
            }
            matrizEstadoAtual = addRoundKey(matrizEstadoAtual, i);
        }

        throw new UnsupportedOperationException();
    }

    public void setChave(BlocoAes chave) {
        ExpansorChave expansorChave = new ExpansorChave();
        this.roundKeys = expansorChave.expandirChave(chave);
    }

    private byte[][] addRoundKey(byte[][] matrizEstado, int indiceChaveAtual) {
        byte[][] matrizChave = this.roundKeys[indiceChaveAtual].getMatrizEstado();

        byte[][] novaMatriz = conversorBytes.realizarXor(matrizEstado, matrizChave);

        return novaMatriz;
    }

    private byte[][] subBytes(boolean inverter, byte[][] matrizEstado) {
        TabelasEnum tabela;

        if (!inverter) {
            tabela = TabelasEnum.S_BOX;
        } else {
            tabela = TabelasEnum.INV_S_BOX;
        }

        byte[][] novaMatriz = gerenciadorTabelas.substituir(matrizEstado, tabela);

        return novaMatriz;
    }

    private byte[][] shiftRows(boolean inverter, byte[][] matrizEstado) {
        byte[][] novaMatriz = matrizEstado;
        int sinal;

        if (!inverter) {
            sinal = -1;
        } else {
            sinal = 1;
        }

        for (int i = 1; i < matrizEstado.length; i++) {
            int desvio = sinal * i;
            int c = 4;

            for (int j = 0; j < matrizEstado.length; j++) {
                novaMatriz[i][j] = matrizEstado[i][c];
                c += desvio % 4;
            }
        }

        return novaMatriz;
    }

    private byte[][] mixColumns(boolean inverter, byte[][] matrizEstado) {
        throw new UnsupportedOperationException();
    }

    private void multiplicacaoGalois() {
        throw new UnsupportedOperationException();
    }

    private byte[][] subBytes(byte[][] matrizEstado) {
        return subBytes(false, matrizEstado);
    }

    private byte[][] shiftRows(byte[][] matrizEstado) {
        return shiftRows(false, matrizEstado);
    }

    private byte[][] mixColumns(byte[][] matrizEstado) {
        return mixColumns(false, matrizEstado);
    }

}
