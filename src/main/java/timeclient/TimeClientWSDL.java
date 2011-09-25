package timeclient;
public class TimeClientWSDL
{
	public static void main(String args[])
	{
	TimeServerImplService service= new TimeServerImplService();
	TimeServer eif=service.getTimeServerImplPort();
	
	System.out.println("elapsed time test branch"+eif.getTimeAsElapsed());
	System.out.println("elapsed time string"+eif.getTimeAsString());
	}
}