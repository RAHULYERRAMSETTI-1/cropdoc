package andy.nasa.models;


	import java.util.ArrayList;
	import java.util.List;

	import andy.nasa.main.widget.ContactItemInterface;

	public class Diseases {

		public static List<ContactItemInterface> getSampleContactList(){
			 List<ContactItemInterface>  list = new  ArrayList<ContactItemInterface> ();

			
			 list.add(new ExampleContactItem("Bacterial canker-Tea" , " " , "https://en.wikipedia.org/wiki/Xanthomonas_campestris") );
			 list.add(new ExampleContactItem("Capillovirus-APPLE" , " " , "https://en.wikipedia.org/wiki/Capillovirus" ) );
			 list.add(new ExampleContactItem("Collar rot-tobacco" , " " , "https://en.wikipedia.org/wiki/Sclerotinia_sclerotiorum" ) );
			 list.add(new ExampleContactItem("halus spot-maise" , " " , "https://en.wikipedia.org/wiki/Pseudomonas_syringae" ) );
			 list.add(new ExampleContactItem("leaf rust-strawberry" , " " , "https://en.wikipedia.org/wiki/Frommeella_tormentillae" ) );
			 list.add(new ExampleContactItem("canker-carrot" , " " , "https://en.wikipedia.org/wiki/Rhizoctonia_solani" ) );
			 list.add(new ExampleContactItem("rust-soybean" , " " , "https://en.wikipedia.org/wiki/List_of_soybean_diseases" ) );
			 
			 return list;
		}
		
		
	}



