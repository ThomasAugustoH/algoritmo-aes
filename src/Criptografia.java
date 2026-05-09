import java.nio.file.Files;
import java.nio.file.Path;

public class Criptografia {

    private ModoOperacao operador;

    public Criptografia(ModoEnum modoOperacao) {
        switch (modoOperacao) {
            case ModoEnum.ECB -> operador = new ECB();
            case ModoEnum.CBC -> operador = new CBC();
            default -> throw new IllegalArgumentException("Modo de operação não encontrado.");
        }
    }

    public void setChave(Chave chave) {
        operador.setChave(chave);
    }

    public void setVetorInicializacao(Chave vetorInicializacao) {
        operador.setVetorInicializacao(vetorInicializacao);
    }

    public void cifrar(String nomeArquivoSimples, String nomeArquivoSaida) throws Exception {
        byte[] bytesArquivo = Files.readAllBytes((Path.of(nomeArquivoSimples)));
        operador.cifrar(bytesArquivo, nomeArquivoSaida);
    }

    public void decifrar(String arquivoCifrado, String nomeArquivoSaida) throws Exception {
        byte[] bytesArquivo = Files.readAllBytes((Path.of(arquivoCifrado)));
        operador.decifrar(bytesArquivo, nomeArquivoSaida);
    }
}
