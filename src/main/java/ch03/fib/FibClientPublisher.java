package ch03.fib;

import javax.xml.ws.Endpoint;
public class FibClientPublisher {
    public static void main(String[ ] args) {
        Endpoint.publish("http://127.0.0.1:8888/fib",
                          new RabbitCounter());
    }
}