package com.aes;

public class ExpansorChave {

    private Chave[] keySchedule = new Chave[11];

    public Chave[] expandirChave(Chave chave) {
        keySchedule[0] = chave;
        throw new UnsupportedOperationException();
    }

    private byte[] rotacionarBytes(int indiceChaveAtual) {
        throw new UnsupportedOperationException();
    }

    private void substituirBytes() {
        throw new UnsupportedOperationException();
    }

    private void gerarRoundConstant() {
        throw new UnsupportedOperationException();
    }

    private void realizarXor() {
        throw new UnsupportedOperationException();
    }
}
