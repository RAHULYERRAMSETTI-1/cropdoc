package andy.nasa.models;

import java.util.ArrayList;
import java.util.List;

import andy.nasa.main.widget.ContactItemInterface;

public class ExampleDataSource {

	public static List<ContactItemInterface> getSampleContactList(){
		 List<ContactItemInterface>  list = new  ArrayList<ContactItemInterface> ();

		 list.add(new ExampleContactItem("Apple" , " " , "https://en.wikipedia.org/wiki/Apple") );
		 list.add(new ExampleContactItem("orange" , " " , "https://en.wikipedia.org/wiki/Orange" ) );
		 list.add(new ExampleContactItem("potato" , " " , "https://en.wikipedia.org/wiki/Potato" ) );
		 list.add(new ExampleContactItem("tomato" , " " , "https://en.wikipedia.org/wiki/Tomato" ) );
		 list.add(new ExampleContactItem("almond" , " " , "https://en.wikipedia.org/wiki/Almond" ) );
		 list.add(new ExampleContactItem("barley" , " " , "httpps://en.wikipedia.org/wiki/Barley" ) );
		 list.add(new ExampleContactItem("banana" , " " , "https://en.wikipedia.org/wiki/Banana" ) );
		 list.add(new ExampleContactItem("bean" , " " , "https://en.wikipedia.org/wiki/Bean" ) );
		 list.add(new ExampleContactItem("carrot" , " " , "https://en.wikipedia.org/wiki/Carrot" ) );
		 
		 list.add(new ExampleContactItem("cashew" , " " , "https://en.wikipedia.org/wiki/Cashew" ) );
		 list.add(new ExampleContactItem("Cherry" , " " , "https://en.wikipedia.org/wiki/Cherry" ) );
		 list.add(new ExampleContactItem("lemon" , " " , "https://en.wikipedia.org/wiki/Lemon" ) );
		 list.add(new ExampleContactItem("corn	" , " " , "https://en.wikipedia.org/wiki/Maize" ) );
		 list.add(new ExampleContactItem("cotton" , " " , "https://en.wikipedia.org/wiki/Cotton" ) );
		 list.add(new ExampleContactItem("date palm" , " " , "https://en.wikipedia.org/wiki/Date_palm" ) );
		 list.add(new ExampleContactItem("ginger" , " " , "https://en.wikipedia.org/wiki/Ginger" ) );
		 list.add(new ExampleContactItem("grape" , " " , "https://en.wikipedia.org/wiki/Grape" ) );
		 list.add(new ExampleContactItem("guava" , " " , "https://en.wikipedia.org/wiki/Guava" ) );
		 
		 
		 list.add(new ExampleContactItem("oak" , " " , "https://en.wikipedia.org/wiki/Oak" ) );
		 list.add(new ExampleContactItem("oat" , " " , "https://en.wikipedia.org/wiki/Oat" ) );
		 list.add(new ExampleContactItem("onion" , " " , "https://en.wikipedia.org/wiki/Onion" ) );
		 list.add(new ExampleContactItem("papaya" , " " , "https://en.wikipedia.org/wiki/Papaya" ) );
		 list.add(new ExampleContactItem("peanut" , " " , "https://en.wikipedia.org/wiki/Peanut" ) );
		
		 
		 list.add(new ExampleContactItem("pea" , " " , "https://en.wikipedia.org/wiki/Pea" ) );
		 list.add(new ExampleContactItem("pineapple" , " " , "https://en.wikipedia.org/wiki/Pineapple" ) );
		 list.add(new ExampleContactItem("rice" , " " , "https://en.wikipedia.org/wiki/Rice" ) );

list.add(new ExampleContactItem("strawberry" , " " , "https://en.wikipedia.org/wiki/Strawberry" ) );

list.add(new ExampleContactItem("sugarcane" , " " , "https://en.wikipedia.org/wiki/Sugarcane" ) );

list.add(new ExampleContactItem("sunflower" , " " , "https://en.wikipedia.org/wiki/Helianthus" ) );

list.add(new ExampleContactItem("tea" , " " , "https://en.wikipedia.org/wiki/Tea" ) );

list.add(new ExampleContactItem("tobacco" , " " , "https://en.wikipedia.org/wiki/Tobacco" ) );

list.add(new ExampleContactItem("wallnut" , " " , "https://en.wikipedia.org/wiki/Walnut" ) );

		 
		 return list;
	}
	
	
}
