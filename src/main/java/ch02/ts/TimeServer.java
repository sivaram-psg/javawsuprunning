package ch02.ts;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface TimeServer {

	@WebMethod 
	@WebResult(partName="time_response") 
	String getTimeAsString();
	
	@WebMethod 
	@WebResult(partName="time_response") 
	long getTimeAsElapsed();
}
