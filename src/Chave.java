
import java.util.HexFormat;

public class Chave {

    private String chaveString;
    private byte[] chaveBytes;

    // validar entradas
    // qt valores == 16
    // valores > 0 && < 256
    public Chave(String chaveString) {
        throw new UnsupportedOperationException();
    }

    public Chave(byte[] chaveBytes) {
        throw new UnsupportedOperationException();
    }

    public String getChaveString() {
        return chaveString;
    }

    public String getChaveBytes() {
        return chaveString;
    }

    public byte[] getPalavra(int index) {
        throw new UnsupportedOperationException();
    }

    public String getMatrizEstado() {

        String textoFinal = "";

        int contador = 0;
        for (byte b : chaveBytes) {
            if (contador == 8) {
                contador = 0;
                textoFinal += "\n";
            }
            textoFinal += HexFormat.of().toHexDigits(b).toUpperCase() + " ";
            contador++;
        }

        return textoFinal;
    }
}
