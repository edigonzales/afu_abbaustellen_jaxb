package ch.so.agi;

public class Abbaustelle {
    String tid;
    String nummer;
    String name;    
    String Bemerkungen;
    String geomRef;
    
    public Abbaustelle(String tid, String nummer, String name, String bemerkungen, String geomRef) {
        super();
        this.tid = tid;
        this.nummer = nummer;
        this.name = name;
        Bemerkungen = bemerkungen;
        this.geomRef = geomRef;
    }
    
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    public String getNummer() {
        return nummer;
    }
    public void setNummer(String nummer) {
        this.nummer = nummer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBemerkungen() {
        return Bemerkungen;
    }
    public void setBemerkungen(String bemerkungen) {
        Bemerkungen = bemerkungen;
    }
    public String getGeomRef() {
        return geomRef;
    }
    public void setGeomRef(String geomRef) {
        this.geomRef = geomRef;
    }
}
