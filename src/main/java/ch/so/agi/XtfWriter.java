package ch.so.agi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.interlis.interlis2.DataSection;
import ch.interlis.interlis2.HeaderSection;
import ch.interlis.interlis2.Model;
import ch.interlis.interlis2.Models;
import ch.interlis.interlis2.ObjectFactory;
import ch.interlis.interlis2.SOAFUAbbaustellen20200918Abbaustellen;
import ch.interlis.interlis2.SOAFUAbbaustellen20200918AbbaustellenAbbaustelle;
import ch.interlis.interlis2.TRANSFER;

public class XtfWriter {
    private Marshaller marshaller;
    List<Abbaustelle> abbaustellenList;
    
    public XtfWriter() throws JAXBException {
        System.out.println("XtfWriter");
        
        JAXBContext context = JAXBContext.newInstance("ch.interlis.interlis2");
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        abbaustellenList = new ArrayList<Abbaustelle>();
        Abbaustelle abbaustelle = new Abbaustelle("5c6be6dd-7111-42fd-9eae-bd46fefa3c93", "5432", "Hellstätt", "Fubar", "5e5bb99e-2f68-499e-aebe-d01f05b9ea88");
        abbaustellenList.add(abbaustelle);        
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
        {
            Model model = new Model();
            model.setNAME("GeometryCHLV03_V1");
            model.setVERSION("2017-12-04");
            model.setURI("http://www.geo.admin.ch");
            models.getMODELS().add(model);  
        }
        {
            Model model = new Model();
            model.setNAME("GeometryCHLV95_V1");
            model.setVERSION("2017-12-04");
            model.setURI("http://www.geo.admin.ch");
            models.getMODELS().add(model);  
        }
        {
            Model model = new Model();
            model.setNAME("SO_AFU_Abbaustellen_20200918");
            model.setVERSION("2020-09-18");
            model.setURI("http://afu.so.ch");
            models.getMODELS().add(model);  
        }

        HeaderSection headerSection = new HeaderSection();
        headerSection.setSENDER("some-jaxb-fairy-dust");
        headerSection.setVERSION(BigDecimal.valueOf(2.3));
        headerSection.setMODELS(models);
        
        DataSection dataSection = new DataSection();
        SOAFUAbbaustellen20200918Abbaustellen abbaustellen = new SOAFUAbbaustellen20200918Abbaustellen();
        abbaustellen.setBID("bX");
        
        for (Abbaustelle abbauObj : abbaustellenList) {
            SOAFUAbbaustellen20200918AbbaustellenAbbaustelle abbaustelle = new SOAFUAbbaustellen20200918AbbaustellenAbbaustelle();
            abbaustelle.setTID(abbauObj.getTid());
            abbaustelle.setNummer(abbauObj.getNummer());
            abbaustelle.setName(abbauObj.getName());
            abbaustelle.setBemerkungen(abbauObj.getBemerkungen());
            
            ObjectFactory factory = new ObjectFactory();
            ch.interlis.interlis2.SOAFUAbbaustellen20200918AbbaustellenAbbaustelle.Geometrie geometrie = factory.createSOAFUAbbaustellen20200918AbbaustellenAbbaustelleGeometrie();
            geometrie.setREF(abbauObj.getGeomRef());
            abbaustelle.setGeometrie(geometrie);
            
            abbaustellen.getSOAFUAbbaustellen20200918AbbaustellenAbbaustellesAndSOAFUAbbaustellen20200918AbbaustellenGeometries().add(abbaustelle);
        } 
        dataSection.getCoordSysCoordsysTopicsAndSOAFUAbbaustellen20200918Abbaustellens().add(abbaustellen);
        
        TRANSFER transfer = new TRANSFER();
        transfer.setHEADERSECTION(headerSection);
        transfer.setDATASECTION(dataSection);
        
        OutputStream os = new FileOutputStream("./abbaustellen_fachanwendung.xml");
        marshaller.marshal( transfer, os );
    }
}
