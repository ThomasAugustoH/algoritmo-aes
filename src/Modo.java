public enum Modo {
    ECB("ECB"),
    CBC("CBC");

    private final String valor;

    private Modo(String modo) {
        this.valor = modo;
    }

    public String getValor() {
        return valor;
    }
}
