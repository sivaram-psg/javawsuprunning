package ch03.mctx;

import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Locale;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPConstants;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;

import java.io.IOException;

public class EchoHandler implements SOAPHandler<SOAPMessageContext> {
    public boolean handleMessage(SOAPMessageContext ctx) {
	// Is this an inbound message, i.e., a request?
	Boolean response_p = (Boolean)
            ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

	// Manipulate the SOAP only if it's incoming.
        if (!response_p) {
	    //	    try {
	    SOAPMessage msg = ctx.getMessage();
	    Map m = (Map) ctx;
	    dump_map(m, "");
	    }
	//catch(SOAPException e) { System.err.println(e); }
	//catch(IOException e) { System.err.println(e); }
        //}
	return true; // continue down the chain
    }
    
    public boolean handleFault(SOAPMessageContext ctx) {
        return true; // do not continue down the chain
    }

    // For now, no-ops. 
    public Set<QName> getHeaders() { return null; }     
    public void close(MessageContext messageContext) { }

    private static void dump_map(Map map, String indent) {
	Set keys = map.keySet();
	for (Object key : keys) {
	    System.out.println(indent + key + " ==> " + map.get(key));
	    if (map.get(key) instanceof Map)
		dump_map((Map) map.get(key), indent += "   ");
	}
    }
}
