package ch03.mctx;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;


@WebService
@HandlerChain(file="handler-chain.xml")
public class Echo{
	
	@Resource
	WebServiceContext ws_ctx;
	
	@WebMethod
	public String echo(String from_client)
	{
		MessageContext ctx=ws_ctx.getMessageContext();
		Map req_headers=(Map)ctx.get(MessageContext.HTTP_REQUEST_HEADERS);
		String response="Echoing your message"+from_client;
		MapDump.dump_map((Map)ctx,"");
		return response;
	}
}