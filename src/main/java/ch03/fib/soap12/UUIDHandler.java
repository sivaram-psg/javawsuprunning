package ch03.fib.soap12;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class UUIDHandler implements SOAPHandler<SOAPMessageContext>{

	private static final String LoggerName="ClientSideLogger";
	private Logger logger;
	private final boolean log_p=true;
	
	public UUIDHandler()
	{
		logger=Logger.getLogger(LoggerName);
	}
	
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		if(log_p)
		{
			logger.info("handleMessage");
		}
		
		Boolean request_p=(Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(request_p)
		{
			UUID uuid=UUID.randomUUID();
			try
			{
			SOAPMessage msg=context.getMessage();
			SOAPEnvelope env=msg.getSOAPPart().getEnvelope();
			SOAPHeader hdr=env.getHeader();
			if(hdr==null)
			{
				hdr = env.addHeader();
			}
			
			QName qname=new QName("http://ch03.fib", "uuid");
			SOAPHeaderElement helem=hdr.addHeaderElement(qname);
			
			helem.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
			helem.addTextNode(uuid.toString());
			msg.saveChanges();
			
			msg.writeTo(System.out);
			
			}
			catch(SOAPException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		if(log_p)
		{
			try {
				context.getMessage().writeTo(System.out);
			} catch (SOAPException e) {
			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void close(MessageContext context) {
		if(log_p)
		{
			logger.info("close");
		}
		
	}

	@Override
	public Set<QName> getHeaders() {
		if(log_p)
		{
			logger.info("getHeaders");
		}
		return null;
	}
	
}