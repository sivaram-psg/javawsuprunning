package ch01.soap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

public class DemoSoap
{
	private static final String LocalName = "TimeRequest";
	private static final String NameSpace = "http://ch01/mysoap";
	private static final String NameSpacePrefix = "ms";
	
	private ByteArrayOutputStream out;
	private ByteArrayInputStream in;
	
	public static void main(String[] args) 
	{
		new DemoSoap().request();
	}

	private void request()  {

		//Build soap message to an output stream
    SOAPMessage msg=create_soap_message();
    
    /*
     * Inject the appropriate information into the message.In this case only (Optional)
     * message hdaer is usedand the body is empty
     */
    try {
		SOAPEnvelope env=msg.getSOAPPart().getEnvelope();
		SOAPHeader hdr=env.getHeader();
		
		//Add an element to the SOAP header
		Name lookup_name= create_qname(msg);
		hdr.addHeaderElement(lookup_name).addTextNode("time_request");
		
		//simulate sending SOAP message to remote system by 
		//writing it to the output stream
		out = new ByteArrayOutputStream();
		try {
			msg.writeTo(out);
			trace("The SOAP message sent",msg);
			
			SOAPMessage response=process_request();
			extract_contents_and_print(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} catch (SOAPException e) {
		e.printStackTrace();
	}
		
	}

	private void extract_contents_and_print(SOAPMessage msg) {
		
		try {
			SOAPBody body=msg.getSOAPBody();
			Name lookup_name= create_qname(msg);
			Iterator it=body.getChildElements(lookup_name);
			Node next=(Node)it.next();
			
			String value=(next==null)?"Error!":next.getValue();
			System.out.println("\n\n Returned from server"+value);
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		
		
	}

	private SOAPMessage process_request() {
		process_incoming_soap();
		coordinate_streams();
		return create_soap_message(in);
	}
	
	private SOAPMessage create_soap_message(InputStream in) {
		SOAPMessage msg = null;
		try {
			MessageFactory mf= MessageFactory.newInstance();
			try {
				msg=mf.createMessage(null,in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return msg;
	}

	private void coordinate_streams() {
		in = new ByteArrayInputStream(out.toByteArray());
		out.reset();
		
	}

	private void process_incoming_soap() {
		//copy output stream to input stream to simulate coordinated streams
		
		coordinate_streams();
		
		//create the recieved SOAP message from the input stream
		SOAPMessage message=create_soap_message(in);
		
		//inspect the SOAP header for the keyword 'time_request'
		//process the request if the keyword appears
		Name lookup_name = create_qname(message);
		
		try {
			SOAPHeader header = message.getSOAPHeader();
			Iterator it=header.getChildElements(lookup_name);
			Node next=(Node)it.next();
			
			String value = (next == null) ? "Error!" : next.getValue();
			
			//if SOAP message contains request for the time create
			// new SOAP message with the current time in the body
			
			if(value.toLowerCase().contains("time_request"))
			{
				//Extract the body and add current time as an element
				String now = new Date().toString();
				SOAPBody body= message.getSOAPBody();
				body.addBodyElement(lookup_name).addTextNode(now);
				message.saveChanges();
				
				//write to the output stream
				try {
					message.writeTo(out);
				} catch (IOException e) {
					e.printStackTrace();
				}
				trace("The received/processed soap message is",message);
				
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		
	}

	private void trace(String string, SOAPMessage msg) {
		System.out.println("\n");
		System.out.println(string);
		try {
			msg.writeTo(System.out);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private Name create_qname(SOAPMessage msg) {
		Name name = null;
		try {
			SOAPEnvelope env=msg.getSOAPPart().getEnvelope();
			name = env.createName(LocalName, NameSpacePrefix, NameSpace);
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return name;
	}

	private SOAPMessage create_soap_message() {
		SOAPMessage msg = null;
		try {
			MessageFactory mf= MessageFactory.newInstance();
			msg=mf.createMessage();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return msg;
	}
}