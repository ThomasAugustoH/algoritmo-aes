package com.aes;

public class Aes {

    public final int TAM_BLOCO = 16;
    private BlocoAes[] roundKeys;
    private final GerenciadorTabelas gerenciadorTabelas = new GerenciadorTabelas();
    private final ConversorBytes conversorBytes = new ConversorBytes();

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

        return conversorBytes.matrizEstadoParaBytes(matrizEstadoAtual);
    }

    public void setChave(BlocoAes chave) {
        ExpansorChave expansorChave = new ExpansorChave();
        this.roundKeys = expansorChave.expandirChave(chave);
    }

    private byte[][] addRoundKey(byte[][] matrizEstado, int indiceChaveAtual) {
        return addRoundKey(matrizEstado, this.roundKeys[indiceChaveAtual].getMatrizEstado());
    }

    protected byte[][] addRoundKey(byte[][] matrizEstado, byte[][] matrizChave) {
        byte[][] novaMatriz = conversorBytes.realizarXor(matrizEstado, matrizChave);

        return novaMatriz;
    }

    protected byte[][] subBytes(boolean inverter, byte[][] matrizEstado) {
        TabelasEnum tabela;

        if (!inverter) {
            tabela = TabelasEnum.S_BOX;
        } else {
            tabela = TabelasEnum.INV_S_BOX;
        }

        byte[][] novaMatriz = gerenciadorTabelas.substituir(matrizEstado, tabela);

        return novaMatriz;
    }

    protected byte[][] shiftRows(boolean inverter, byte[][] matrizEstado) {
        byte[][] novaMatriz = new byte[matrizEstado.length][];

        for (int i = 0; i < matrizEstado.length; i++) {
            novaMatriz[i] = matrizEstado[i].clone();
        }

        int sinal;

        if (!inverter) {
            sinal = -1;
        } else {
            sinal = 1;
        }

        for (int i = 1; i < matrizEstado.length; i++) {
            int desvio = sinal * i;

            for (int j = 0; j < matrizEstado.length; j++) {
                int c = (desvio + j) % matrizEstado.length;
                if (c < 0) {
                    c += matrizEstado.length;
                }

                novaMatriz[i][c] = matrizEstado[i][j];
            }
        }

        return novaMatriz;
    }

    protected byte[][] mixColumns(boolean inverter, byte[][] matrizEstado) {
        TabelasEnum tabela;

        if (!inverter) {
            tabela = TabelasEnum.MATRIZ_MULTIPLICACAO;
        } else {
            tabela = TabelasEnum.INV_MATRIZ_MULTIPLICACAO;
        }

        int[][] matrizMultiplicacao = gerenciadorTabelas.getTabela(tabela);
        byte[][] novaMatriz = new byte[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                byte[] bytesParaMultiplicar = new byte[4];
                for (int k = 0; k < bytesParaMultiplicar.length; k++) {
                    bytesParaMultiplicar[k] = multiplicacaoGalois(matrizEstado[k][j], matrizMultiplicacao[i][k]);
                }

                byte valorFinal = conversorBytes.realizarXor(bytesParaMultiplicar[0], bytesParaMultiplicar[1]);
                valorFinal = conversorBytes.realizarXor(valorFinal, bytesParaMultiplicar[2]);
                valorFinal = conversorBytes.realizarXor(valorFinal, bytesParaMultiplicar[3]);

                novaMatriz[i][j] = valorFinal;
            }
        }

        return novaMatriz;
    }

    protected byte multiplicacaoGalois(byte termo1, int termo2) {
        if (termo1 == 0 || termo2 == 0) {
            return 0;
        }
        if (termo1 == 1) {
            return (byte) termo2;
        }
        if (termo2 == 1) {
            return termo1;
        }

        byte termo2Byte = (byte) termo2;

        termo1 = gerenciadorTabelas.substituir(termo1, TabelasEnum.TABELA_L);
        termo2Byte = gerenciadorTabelas.substituir(termo2Byte, TabelasEnum.TABELA_L);

        byte resultadoL = (byte) ((Byte.toUnsignedInt(termo1) + Byte.toUnsignedInt(termo2Byte)) % 255);
        byte resultadoE = gerenciadorTabelas.substituir(resultadoL, TabelasEnum.TABELA_E);

        return resultadoE;
    }

    protected byte[][] subBytes(byte[][] matrizEstado) {
        return subBytes(false, matrizEstado);
    }

    protected byte[][] shiftRows(byte[][] matrizEstado) {
        return shiftRows(false, matrizEstado);
    }

    protected byte[][] mixColumns(byte[][] matrizEstado) {
        return mixColumns(false, matrizEstado);
    }

}
