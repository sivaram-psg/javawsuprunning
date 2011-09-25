package tsch2client;
public class TimeClientWSDL
{
	public static void main(String args[])
	{
	TimeServerImplService service= new TimeServerImplService();
	TimeServer eif=service.getTimeServerImplPort();
	
	System.out.println("elapsed time"+eif.getTimeAsElapsed());
	System.out.println("elapsed time string"+eif.getTimeAsString());
	}
}