package EchoServiceGenerated;

import java.util.Map;
import java.util.Set;

public class MapDump {

	public static void dump_map(Map ctx, String string) {
		Set keys=ctx.keySet();
		for(Object key:keys)
		{
			System.out.println(key+" ==> " +ctx.get(key));
			if(ctx.get(key) instanceof Map)
			{
				dump_map((Map)ctx.get(key)," indent");
			}
		}
		
	}

}
