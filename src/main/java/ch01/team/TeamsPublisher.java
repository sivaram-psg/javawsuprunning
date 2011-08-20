package ch01.team;

import javax.xml.ws.Endpoint;

public class TeamsPublisher
{
	public static void main(String args[])
	{
		int port= 8888;
		String url="http://localhost:"+port+"/teams";
		System.out.println("publishing teams on port"+port);
		Endpoint.publish(url, new Teams());
	}
}