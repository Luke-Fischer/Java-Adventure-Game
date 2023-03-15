package adventure;

import java.util.ArrayList;

/**
 * This class gives the user access to methods that will aid them in locating information 
 * such as an arry of all rooms and items
 * @auhtor Luke Fischer 1061800
 * 
 */

public class Adventure implements java.io.Serializable {

    private static final long serialVersionUID = 1468883910047769572L;
    private ArrayList<Room> allRooms = new ArrayList<Room>();
    private ArrayList<Item> allItems = new ArrayList<Item>();
    private Room currentRoom;

    /**
     * Default constructor setting private instance to default values
     */
    public Adventure(){
        currentRoom = null;
    }

    /**
     * Initializes instance variables with proper values from the adventure
     * @param allRooms
     * @param allItems
     */
    public Adventure(ArrayList<Room> allRooms, ArrayList<Item> allItems){
        this.allRooms = allRooms;
        this.allItems = allItems;

        //Find out where start room is
        for(int i = 0; i < allRooms.size(); i++){
            if(allRooms.get(i).getStart() != null){
                currentRoom = allRooms.get(i);
            }
        }

        
    }
    /**
     * Gets an ArrayList of all the room objects
     * @return ArrayList of rooms
     */
    public ArrayList<Room> listAllRooms(){
        return allRooms;
    }

    /**
     * Gets an ArrayList of all the item objects
     * @return ArrayList of all items
     */
    public ArrayList<Item> listAllItems(){
        return allItems;
    }

    /**
     * Gets the long description of the current room
     * @return String of text describing the room
     */
    public String getCurrentRoomDescription(){
        return currentRoom.longDescr;
    }

    /**
     * Gets the location of the current room
     * @return Room object 
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * Sets the new current room 
     * @param currentRoomParam 
     */
    public void setCurrentRoom(Room currentRoomParam){
        currentRoom = currentRoomParam;
    }

    /**
     * This method takes in a directional command in moves the hero to the room if available
     * @param toGo Takes in a direction
     */
    public void moveHero(Command toGo){
        if(getCurrentRoom().getConnectedRoomHash(toGo.getNoun()) != null){
            //Changes currrent location based on user input
            setCurrentRoom(getCurrentRoom().getConnectedRoomHash(toGo.getNoun()));
            
            //Print current room
            System.out.println("Current room: " + getCurrentRoom().getName());

            //Print items in room
            for(int i = 0; i < getCurrentRoom().listItems().size(); i++){
                System.out.println("Items in room: " + getCurrentRoom().listItems().get(i).getName());
            }
        } else{
            System.out.println("Error - invalid direction");
        }
    }

    /**
     * This method prints out a description of an item in the room
     * @param toSee Take in a string related to an item name
     */
    public void lookAtSomething(Command toSee){
        boolean itemIsInRoom = false;
        for(int k = 0; k < getCurrentRoom().listItems().size(); k++){
            if(getCurrentRoom().listItems().get(k).getName().equals(toSee.getNoun())){
                System.out.println(getCurrentRoom().listItems().get(k).getLongDescription());
                itemIsInRoom = true;
            }
        }
        if(!itemIsInRoom){
            System.out.println("The item you're looking for is not in the current room");
        }
    }

    /**
     * This method prints out the decription of the current hero's room
     */
    public void lookAround(){
        System.out.println(getCurrentRoomDescription());
    }

    /**
     * This function overrides the super classes toString() function and 
     * displays the number of rooms and items contained in the adventure
     */
    @Override
    public String toString(){
        return "This adventure has " + allRooms.size() + " Rooms and " + allItems.size() + " Items";
    }
}
