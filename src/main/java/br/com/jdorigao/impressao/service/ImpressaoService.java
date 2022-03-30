package br.com.jdorigao.impressao.service;

import br.com.jdorigao.impressao.util.ImpressaoUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class ImpressaoService {

    public static void main(String[] args) {
        try {
            impressao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void impressao() throws JRException, ParserConfigurationException, IOException, SAXException {
        String destinoPdf = "/home/juliano/Downloads/nfe.pdf";
        String arquivoXML = "/home/juliano/Downloads/nfe.xml";
        InputStream jasper = ImpressaoService.class.getResourceAsStream("/jasper/nfe/danfe.jasper");
        String xml = ImpressaoUtil.leArquivo(arquivoXML);
        String pathExpression = "/nfeProc/NFe/infNFe/det";

        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(xml)));

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Logo", ImpressaoService.class.getResourceAsStream("/img/nfe.jpg"));
        parametros.put("SUBREPORT", ImpressaoService.class.getResourceAsStream("/jasper/nfe/danfe_fatura.jasper"));
        parametros.put("XML_DATA_DOCUMENT", document);

        JRDataSource xmlDataSource = new JRXmlDataSource(document, pathExpression);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, xmlDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, destinoPdf);
    }
}
