package ch02.jaxbexamples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

class Marshal
{
	private static final String file_name="bd.mar";
	
	public static void main(String args[])
	{
		new Marshal().run_example();
	}

	private void run_example() {
		try {
			JAXBContext contxt=JAXBContext.newInstance(Skier.class);
			Marshaller m=contxt.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
			
			Skier skier=createSkier();
			m.marshal(skier, System.out);
			
			
				FileOutputStream out=new FileOutputStream(file_name);
				m.marshal(skier, out);
				
					out.close();
					Unmarshaller u=contxt.createUnmarshaller();
					Skier bd_clone=(Skier)u.unmarshal(new File(file_name));
					System.out.println();
					m.marshal(bd_clone, System.out);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	private Skier createSkier() {
		// TODO Auto-generated method stub
		Person bd=new Person("bjorn dhaille",41,"Male");
		List<String> list=new ArrayList<String>();
		list.add("12 olympic medals");
		list.add("9 world championships");
		list.add("12 olympic medals");
		list.add("Winningest winter olympian");
		list.add("Greatest Nordic Skier");
		return new Skier(bd, "norway", list);
		
		
	}
}