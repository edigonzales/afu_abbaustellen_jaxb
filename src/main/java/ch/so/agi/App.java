package ch.so.agi;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

public class App {
    
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        XtfWriter writer = new XtfWriter();
        writer.write();
        System.out.println("Done.");
    }
}
