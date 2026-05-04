
public class ExpansorChave {

    private Chave[] keySchedule = new Chave[11];

    public ExpansorChave(Chave chave) {
        keySchedule[0] = chave;
    }

    public void expandirChave() {
        throw new UnsupportedOperationException();
    }

    private void rotacionarBytes() {
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
