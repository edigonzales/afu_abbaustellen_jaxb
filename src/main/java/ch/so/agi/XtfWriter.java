package ch.so.agi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.interlis.interlis2.HeaderSection;
import ch.interlis.interlis2.Model;
import ch.interlis.interlis2.Models;
import ch.interlis.interlis2.TRANSFER;

public class XtfWriter {
    private Marshaller marshaller;
    
    public XtfWriter() throws JAXBException {
        System.out.println("XtfWriter");
        
        JAXBContext context = JAXBContext.newInstance("ch.interlis.interlis2");
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }
    
    public void write() throws JAXBException, FileNotFoundException {
        Models models = new Models();
        {
            Model model = new Model();
            model.setNAME("CoordSys");
            model.setVERSION("2015-11-24");
            model.setURI("http://www.interlis.ch/models");
            models.getMODELS().add(model);
        }
        HeaderSection headerSection = new HeaderSection();
        headerSection.setSENDER("some-jaxb-fairy-dust");
        headerSection.setVERSION(BigDecimal.valueOf(2.3));
        headerSection.setMODELS(models);
        
        TRANSFER transfer = new TRANSFER();
        transfer.setHEADERSECTION(headerSection);
        
        
        OutputStream os = new FileOutputStream("/Users/stefan/tmp/abbaustellen_fachanwendung.xtf");
        marshaller.marshal( transfer, os );
    }
    
    
}
