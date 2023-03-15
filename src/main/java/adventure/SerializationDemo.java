package adventure;

class SerializationDemo implements java.io.Serializable 
{ 
	//your IDE will generate this if you want, or use serialver on the command line
	private static final long serialVersionUID = -3788086098781612036L;
	private Player player;
	private Adventure myAdventure;

	// Default constructor 
	public SerializationDemo(Player player, Adventure myAdventure) 
	{ 
		this.player = player; 
		this.myAdventure = myAdventure; 
	} 

	public void printMe(){
		System.out.println("\nPlayer's current room: " + player.getCurrentRoom().getName());
		player.printInventory();
		System.out.println();
	}

	public Adventure getAdventure(){
		return myAdventure;
	}

	public Player getPlayer(){
		return player;
	}
} 


