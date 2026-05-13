package com.aes;

public class ExpansorChave {

    private BlocoAes[] keySchedule = new BlocoAes[11];

    public BlocoAes[] expandirChave(BlocoAes chave) {
        keySchedule[0] = chave;
        throw new UnsupportedOperationException();

        // return keySchedule;
    }

    private byte[] rotacionarBytes(int indiceChaveAtual) {
        throw new UnsupportedOperationException();
    }

    private byte[] substituirBytes(int indiceChaveAtual) {
        throw new UnsupportedOperationException();
    }

    private byte[] gerarRoundConstant(int indiceChaveAtual) {
        throw new UnsupportedOperationException();
    }

    private byte[] realizarXor(byte[] palavraA, byte[] palavraB) {
        throw new UnsupportedOperationException();
    }
}
