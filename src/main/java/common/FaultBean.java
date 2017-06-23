package common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by echo on 17/6/10.
 */
public class FaultBean {
    private String message;

    public FaultBean(){

    }

    public FaultBean(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
}
