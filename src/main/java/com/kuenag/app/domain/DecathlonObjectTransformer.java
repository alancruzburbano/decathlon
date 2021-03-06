package com.kuenag.app.domain;

import com.kuenag.app.model.Results;
import com.kuenag.app.utils.PropertiesUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class that will transform the Results object in a xml file using JAXB
 *
 * @author Alvaro Andres Cruz Burbano
 */
public class DecathlonObjectTransformer {

    static Logger logger = Logger.getLogger(DecathlonObjectTransformer.class.getName());

    public boolean jaxbObjectToXML(Results resultList) {
        boolean isCreated = true;
            try {
                JAXBContext context = JAXBContext.newInstance(Results.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
                Path outputPath = Paths.get(PropertiesUtil.getProperty("app.decathlon.file.output.path"), PropertiesUtil.getProperty("app.decathlon.file.output.name"));
                try(final OutputStream os = Files.newOutputStream(outputPath)) {
                    m.marshal(resultList, os);
                }
            } catch (JAXBException | IOException e) {
                logger.log(Level.SEVERE ,String.format("Error creating xml file report: %s",e.getLocalizedMessage()));
                isCreated = false;
            }
            return isCreated;
    }

}
