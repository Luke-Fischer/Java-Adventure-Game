package adventure;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream; 
import java.io.IOException;

public class SerializationTest 
{ 
    private Player player;
	private Adventure myAdventure;

	public SerializationTest(Player player, Adventure myAdvenuture) {
		this.player = player;
		this.myAdventure = myAdvenuture;
	}

	public void Serialize(){
		SerializationDemo object = new SerializationDemo(player, myAdventure); 
		object.printMe();
		
		// Serialization 
		try
		{ 
			//Saving of object in a file 
			FileOutputStream outPutStream = new FileOutputStream(player.getSavedNameGame() + ".save");
			ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream); 
			
			// Method for serialization of object 
			outPutDest.writeObject(object); 
			
			outPutDest.close(); 
			outPutStream.close(); 
			
			System.out.println("Object has been serialized"); 

		} 
		
		catch(IOException ex) 
		{ 
			System.out.println(ex); 
		} 
	}	
}
	