import br.com.jdorigao.impressao.model.Impressao;
import br.com.jdorigao.impressao.service.ImpressaoService;
import br.com.jdorigao.impressao.util.ImpressaoUtil;

public class TesteImpressaoHtml {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = ImpressaoUtil.leArquivo("/home/juliano/Downloads/nfe.xml");

            //Aqui está pegando o Layout Padrão
            Impressao impressao = ImpressaoUtil.impressaoPadraoNFe(xml);

            //Faz a impressão em pdf File passando o caminho do Arquivo
            ImpressaoService.impressaoHtml(impressao, "/home/juliano/Downloads/teste-nfe.html");

        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}