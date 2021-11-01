package distance_calculator_web_app.exceptions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Error {

    private String info;

    public Error() {
    }

    @XmlAttribute
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
