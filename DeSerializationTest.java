import java.io.FileInputStream;
import java.io.ObjectInputStream; 
import java.io.IOException;
import java.lang.ClassNotFoundException;


public class DeSerializationTest 
{ 	

	public static void main(String[] args) 
	{ 

      String filename = "aNameIMadeUp.someEnding";
	  SerializationDemo gotItBack = null; 

		// Deserialization 
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)); ){ 
	
			// Method for deserialization of object 
			gotItBack = (SerializationDemo)in.readObject(); 
			
			System.out.println("Object has been deserialized "); 
			gotItBack.printMe(); 
		} 
		
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught " + ex); 
		} 
		
		catch(ClassNotFoundException ex) 
		{ 
			System.out.println("ClassNotFoundException is caught " + ex); 
		} 

	} 
}