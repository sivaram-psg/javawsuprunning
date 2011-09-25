
package ch01.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getTimeAsElapsedResponse", namespace = "http://ts.ch01/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTimeAsElapsedResponse", namespace = "http://ts.ch01/")
public class GetTimeAsElapsedResponse {

    @XmlElement(name = "return", namespace = "")
    private long _return;

    /**
     * 
     * @return
     *     returns long
     */
    public long getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(long _return) {
        this._return = _return;
    }

}
