package br.com.jdorigao.impressao.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Classe responsavel por armazenar os metodos uteis
 */
public class ImpressaoUtil {

    /**
     * Verifica se um objeto é vazio.
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Optional<T> verifica(T obj) {
        if (obj == null)
            return Optional.empty();
        if (obj instanceof Collection)
            return ((Collection<?>) obj).size() == 0 ? Optional.empty() : Optional.of(obj);

        final String s = String.valueOf(obj).trim();

        return s.length() == 0 || s.equalsIgnoreCase("null") ? Optional.empty() : Optional.of(obj);
    }

    /**
     * Le o Arquivo e retona String do conteudo
     *
     * @return String
     * @throws IOException
     */
    public static String leArquivo(String caminhoArquivo) throws IOException {

        verifica(caminhoArquivo).orElseThrow(() -> new IllegalArgumentException("Arquivo não pode ser nulo/vazio."));
        if (!Files.exists(Paths.get(caminhoArquivo))) {
            throw new FileNotFoundException("Arquivo " + caminhoArquivo + " não encontrado.");
        }
        List<String> list = Files.readAllLines(Paths.get(caminhoArquivo));
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        list.forEach(joiner::add);

        return joiner.toString();
    }
}
