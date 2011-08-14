package ch01.ts;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TimeClient
{
	public static void main(String args[]) throws MalformedURLException
	{
		URL url=new URL("http://127.0.0.1:9876/ts?wsdl");
		QName qname=new QName("http://ch01.ts/", "TimeServerImplService");
		Service service = Service.create(url, qname);
		TimeServer eif=service.getPort(TimeServer.class);
		System.out.println("time elapsed"+eif.getTimeAsElapsed());
		System.out.println("time string"+eif.getTimeAsString());
	}
}