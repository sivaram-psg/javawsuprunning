package ch02.ts;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
//@SOAPBinding(style=Style.RPC)
public interface TimeServer {

	@WebMethod String getTimeAsString();
	@WebMethod long getTimeAsElapsed();
}
