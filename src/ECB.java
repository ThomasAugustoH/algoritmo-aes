
public class ECB extends ModoOperacao {

    @Override
    public void cifrar(byte[] textoSimples, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }
    }

    @Override
    public void decifrar(byte[] textoCifrado, String nomeArquivoSaida) {
        if (this.chave == null) {
            throw new IllegalArgumentException("Chave não informada!");
        }

    }

}
