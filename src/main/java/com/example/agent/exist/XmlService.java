package com.example.agent.exist;

import com.example.agent.exceptions.ReportNotFoundException;
import com.example.agent.model.CampaignReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class XmlService {
    @Value("${REPORTS_PATH}")
    private String documentPath;

    public void save(CampaignReport report) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        List<CampaignReport> reports = readAll();
        reports.add(report);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootElement = document.createElement("reports");
        for (CampaignReport r : reports) {
            Element e = toElement(r, document);
            rootElement.appendChild(e);
        }
        document.appendChild(rootElement);

        DOMSource source = new DOMSource(document);
        File myFile = new File(documentPath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        StreamResult file = new StreamResult(myFile);
        transf.transform(source, file);
    }

    public CampaignReport read(int id) throws IOException, SAXException, ParserConfigurationException {
        Optional optional = readAll().stream().filter(report -> report.getId() == id).findFirst();
        if (optional.isEmpty()) throw new ReportNotFoundException();
        return (CampaignReport) optional.get();
    }

    public List<CampaignReport> readAll() throws ParserConfigurationException, IOException, SAXException {
        List<CampaignReport> reports = new ArrayList();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(documentPath));
        document.getDocumentElement().normalize();
        NodeList nodes = document.getElementsByTagName("report");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                reports.add(fromElement(element));
            }
        }
        return reports;
    }

    private Element toElement(CampaignReport report, Document document) {
        Element element = document.createElement("report");
        element.setAttribute("id", Integer.toString(report.getId()));
        element.appendChild(createAttributeElement("likes", Integer.toString(report.getLikes()), document));
        element.appendChild(createAttributeElement("dislikes", Integer.toString(report.getDislikes()), document));
        element.appendChild(createAttributeElement("comments", Integer.toString(report.getComments()), document));
        element.appendChild(createAttributeElement("revenue", Double.toString(report.getRevenue()), document));
        return element;
    }

    private CampaignReport fromElement(Element element) {
        CampaignReport campaignReport = new CampaignReport();
        campaignReport.setId(Integer.parseInt(element.getAttribute("id")));
        campaignReport.setLikes(Integer.parseInt(element.getElementsByTagName("likes").item(0).getTextContent()));
        campaignReport.setDislikes(Integer.parseInt(element.getElementsByTagName("dislikes").item(0).getTextContent()));
        campaignReport.setComments(Integer.parseInt(element.getElementsByTagName("comments").item(0).getTextContent()));
        campaignReport.setRevenue(Double.parseDouble(element.getElementsByTagName("revenue").item(0).getTextContent()));
        return campaignReport;
    }

    private Element createAttributeElement(String name, String value, Document document) {
        Element element = document.createElement(name);
        element.appendChild(document.createTextNode(value));
        return element;
    }
}
