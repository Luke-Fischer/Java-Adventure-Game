package adventure;

import java.io.FileInputStream;
import java.io.ObjectInputStream; 
import java.io.IOException;
import java.lang.ClassNotFoundException;


public class DeSerializationTest 
{ 	
	SerializationDemo gotItBack = null; 

	/**
	 * Deserializes a Serialized file
	 * @param fileName
	 */
	public void Deserialize(String fileName){

		// Deserialization 
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName)); ){ 
	
			// Method for deserialization of object 
			gotItBack = (SerializationDemo)in.readObject(); 
			
			System.out.println("Object has been deserialized "); 
			gotItBack.printMe(); 
		} 
		
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught " + ex); 
			System.exit(1);
		} 
		
		catch(ClassNotFoundException ex) 
		{ 
			System.out.println("ClassNotFoundException is caught " + ex); 
			System.exit(1);
		} 

	} 

	/**
	 * Returns the serialized object to the game
	 * @return Adventure object
	 */
	public Adventure getAdventure(){
		return gotItBack.getAdventure();
	}

	/**
	 * Returns the serialized player to the game
	 * @return Player object
	 */
	public Player getPlayer(){
		return gotItBack.getPlayer();
	}
}