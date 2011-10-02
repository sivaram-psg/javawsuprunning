package ch03.mctx;

import javax.xml.ws.Endpoint;
public class EchoPublisher {
    private static final String port = "9797";
    public static void main(String[ ] args) {
	System.out.println("Echo published on port " + port);
        Endpoint.publish("http://127.0.0.1:" + port + "/echo",
                          new Echo());
    }
}