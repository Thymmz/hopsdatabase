package com.walmart.transformationservice.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

@Component
public class XsltTransProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        Document doc = convertStringToXmlDocument(body);
        String xsltPath = (String) exchange.getIn().getHeader("hopmeta");
        StreamSource xslcode = new StreamSource(new File(xsltPath));

        //Configure input and output source
        Source xmlSource = new DOMSource(doc);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StreamResult outputTarget = new StreamResult(outputStream);

        //Apply the xsl transformation
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(xslcode);
        transformer.transform(xmlSource, outputTarget);

        //Get json data transformed from xml
        String result = outputTarget.getOutputStream().toString();
        exchange.getIn().setBody(result);
    }

    private Document convertStringToXmlDocument(String body) throws IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = (org.w3c.dom.Document) documentBuilder.parse(new InputSource(new StringReader(body)));
            return document;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
