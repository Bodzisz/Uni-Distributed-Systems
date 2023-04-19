
package io.github.bodzisz;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.1
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PersonNotFoundException", targetNamespace = "http://service.bodzisz.github.io/")
public class PersonNotFoundException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PersonNotFoundException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PersonNotFoundException_Exception(String message, PersonNotFoundException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public PersonNotFoundException_Exception(String message, PersonNotFoundException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: io.github.bodzisz.PersonNotFoundException
     */
    public PersonNotFoundException getFaultInfo() {
        return faultInfo;
    }

}
