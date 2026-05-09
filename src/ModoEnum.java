public enum ModoEnum {
    ECB("ECB"),
    CBC("CBC");

    private final String valor;

    private ModoEnum(String modo) {
        this.valor = modo;
    }

    public String getValor() {
        return valor;
    }
}
