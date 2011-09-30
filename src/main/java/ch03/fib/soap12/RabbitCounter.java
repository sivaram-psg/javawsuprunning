package ch03.fib.soap12;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;


@WebService(targetNamespace="http://ch03.fib")
@BindingType(value="http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT,
             use=SOAPBinding.Use.LITERAL,
             parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class RabbitCounter
{
	private Map<Integer,Integer> cache=Collections.synchronizedMap(new HashMap<Integer,Integer>());
	
	@WebMethod
	public int countRabbits(int n) throws FibException
	{
		if(n<0) throw new FibException("Negative arguement not allowed",n+"<0");
		if(n<2) return n;
		
		if(cache.containsKey(n))
			return cache.get(n);
		
		if(cache.containsKey(n-1) && cache.containsKey(n-2))
		{
			cache.put(n,cache.get(n-1)+cache.get(n-2));
			return cache.get(n);
			
		}
		
		//otherwise compute from scratch,cache and return
		int fib=1,prev=0;
		for(int i=2;i<=n;i++)
		{
			int temp=fib;
			fib+=prev;
			prev=temp;
		}
		cache.put(n, fib);
		return fib;
	}
}