package fibC;

public class FibClient
{
	public static void main(String args[])
	{
		RabbitCounterService service=new RabbitCounterService();
		RabbitCounter port=service.getRabbitCounterPort();
		
		int n=45;
				try {
					System.out.println("fib("+n+")="+port.countRabbits(n));
				} catch (FibException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}