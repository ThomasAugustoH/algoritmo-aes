

public class Aes {

    public final int TAM_BLOCO = 16;
    private Chave[] roundKeys;

    public byte[] cifrar(byte[] bloco) {
        if (this.roundKeys == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }

        bloco = addRoundKey(bloco);

        throw new UnsupportedOperationException();
    }

    public void setChave(Chave chave) {
        ExpansorChave expansorChave = new ExpansorChave();
        this.roundKeys = expansorChave.expandirChave(chave);
    }

    private byte[] addRoundKey(byte[] bloco) {
        throw new UnsupportedOperationException();
    }

    private byte[] subBytes(boolean inverter) {
        throw new UnsupportedOperationException();
    }

    private byte[] shiftRows(boolean inverter) {
        throw new UnsupportedOperationException();
    }

    private byte[] mixColumns(boolean inverter) {
        throw new UnsupportedOperationException();
    }

    private byte[] multiplicacaoGalois() {
        throw new UnsupportedOperationException();
    }

    private byte[] subBytes() {
        throw new UnsupportedOperationException();
        //return subBytes(false);
    }

    private byte[] shiftRows() {
        throw new UnsupportedOperationException();
        //return mixColumns(false);
    }

    private byte[] mixColumns() {
        throw new UnsupportedOperationException();
        //return mixColumns(false);
    }

}
