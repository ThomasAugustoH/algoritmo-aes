
import java.io.File;

public class Aes {

    public void cifrar(File arquivoSimples, String nomeArquivoSaida, Modo modoOperacao, Chave chave) {
        throw new UnsupportedOperationException();
    }

    public void decifrar(File arquivoCifrado, String nomeArquivoSaida, Modo modoOperacao, Chave chave) {
        throw new UnsupportedOperationException();
    }

    public void cifrar(String nomeArquivoSimples, String nomeArquivoSaida, Modo modoOperacao, Chave chave) {
        throw new UnsupportedOperationException();
    }

    public void decifrar(String arquivoCifrado, String nomeArquivoSaida, Modo modoOperacao, Chave chave) {
        throw new UnsupportedOperationException();
    }

    private void addRoundKey() {
        throw new UnsupportedOperationException();
    }

    private void subBytes(boolean inverter) {
        throw new UnsupportedOperationException();
    }

    private void shiftRows(boolean inverter) {
        throw new UnsupportedOperationException();
    }

    private void mixColumns(boolean inverter) {
        throw new UnsupportedOperationException();
    }

    private void multiplicacaoGalois() {
        throw new UnsupportedOperationException();
    }

    private void subBytes() {
        throw new UnsupportedOperationException();
        //return subBytes(false);
    }

    private void shiftRows() {
        throw new UnsupportedOperationException();
        //return mixColumns(false);
    }

    private void mixColumns() {
        throw new UnsupportedOperationException();
        //return mixColumns(false);
    }

}
