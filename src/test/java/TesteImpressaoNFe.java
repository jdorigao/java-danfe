import br.com.jdorigao.impressao.model.Impressao;
import br.com.jdorigao.impressao.service.ImpressaoService;
import br.com.jdorigao.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.engine.JRException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TesteImpressaoNFe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = ImpressaoUtil.leArquivo("/home/juliano/Downloads/nfe.xml");

            //Aqui está pegando o Layout Padrão
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xml);

            //Faz a impressão em pdf File
            impressaoPdfArquivo(impressao);
            System.out.println("Impressão Pdf Arquivo OK");
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }

    private static void impressaoPdfArquivo(Impressao impressao) throws IOException, JRException, ParserConfigurationException, SAXException {
        ImpressaoService.impressaoPdfArquivo(impressao, "/home/juliano/Downloads/teste-nfe.pdf");
    }
}