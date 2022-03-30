package br.com.jdorigao.impressao.service;

import br.com.jdorigao.impressao.model.Impressao;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class ImpressaoService {

    public static void impressaoPdfArquivo(Impressao impressao, String destinoPdf) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        JasperExportManager.exportReportToPdfFile(jasperPrint, destinoPdf);
    }

    public static byte[] impressaoPdfByte(Impressao impressao) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public static void impressaoHtml(Impressao impressao, String destinoHtml) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        JasperExportManager.exportReportToHtmlFile(jasperPrint, destinoHtml);

    }

    public static JasperViewer impressaoPreview(Impressao impressao) throws JRException, ParserConfigurationException, IOException, SAXException {
        JasperPrint jasperPrint = geraImpressao(impressao);
        return new JasperViewer(jasperPrint, true);
    }

    private static JasperPrint geraImpressao(Impressao impressao) throws IOException, ParserConfigurationException, JRException, SAXException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(impressao.getXml())));

        JRDataSource xmlDataSource = new JRXmlDataSource(document, impressao.getPathExpression());
        return JasperFillManager.fillReport(impressao.getJasper(), impressao.getParametros(), xmlDataSource);
    }
}
