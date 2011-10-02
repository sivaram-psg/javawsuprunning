package EchoServiceGenerated;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

class EchoClient
{
 public static void main(String args[])
 {
	 EchoService service=new EchoService();
	 Echo port=service.getEchoPort();
	 
	 Map<String,Object> req_ctx=((BindingProvider)port).getRequestContext();
	 if(args.length>=2)
	 {
		 req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, args[0]+"/"+args[1]);
		 req_ctx.put(BindingProvider.SOAPACTION_URI_PROPERTY, args[1]);
	 }
	 Map<String,List<String>> my_header=new HashMap<String, List<String>>();
	 my_header.put("Accept-encoding", Collections.singletonList("gzip"));
	 my_header.put("Greeting", Collections.singletonList("Hello World"));
	 
	 req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, my_header);
	 
	 MapDump.dump_map(req_ctx,"");
	 System.out.println("\n\nrequest above response below");
	 
	 String response=port.echo("Have a nice day:");
	 Map<String,Object> res_ctx=((BindingProvider)port).getResponseContext();
	 MapDump.dump_map(res_ctx, "");
	 
	 Object response_code=res_ctx.get(MessageContext.HTTP_RESPONSE_CODE);
 }
}