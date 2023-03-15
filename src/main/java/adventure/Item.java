package adventure;

import java.util.ArrayList;

/**
 * This class gives the user the ability to get and set private variables of items
 * @auhtor Luke Fischer 1061800
 * 
 */

public class Item implements java.io.Serializable {
 
    private int itemId;
    private String itemName;
    private String itemDescription;
    private Room containingRoom;

    /**
     * Constructor sets private instance variables to deafult values
     */
    public Item(){
        itemId = 0;
        itemName = "No Name";
        itemDescription = "No Description";
        containingRoom = null;
    }

    /**
     * Initializes instance variables
     * @param itemId
     * @param itemName
     * @param itemDescription
     */
    public Item(int itemId, String itemName, String itemDescription){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    /**
     * Gets the id of an item
     * @return itemId
     */
    public int getId(){
        return itemId;
    }

    /**
     * Sets the id of an item
     * @param itemId
     */
    public void setId(int itemId){
        this.itemId = itemId;
    }

    /**
     * Gets the name of the item
     * @return itemName
     */
    public String getName(){
        return itemName;
    }

    /**
     * Sets the name of an item
     * @param itemName
     */
    public void setName(String itemName){
        this.itemName = itemName;
    }

    /**
     * Gets the long description of the item
     * @return itemDescription
     */
    public String getLongDescription(){
        return itemDescription;
    }

    /**
     * Sets the long description of a room
     * @param itemDescription
     */
    public void setLongDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }

    /**
     * gets the address of the room that contains an item
     * @return containingRoom
     */
    public Room getContainingRoom(){
        return containingRoom;
    }

    public void setContainingRoom(Room containingRoom){
        this.containingRoom = containingRoom;
    }

    /**
     * Updates the locating rooms of the item
     * @param allRooms
     */
    public void updateItems(ArrayList<Room> allRooms){
        for(int i = 0; i < allRooms.size(); i++){
            for(int k = 0; k < allRooms.get(i).listItems().size(); k++){
                if(itemId == allRooms.get(i).listItems().get(k).getId() ){
                    containingRoom = allRooms.get(i);
                }
            }            
        }
    }

    /**
     * This function overrides the super classes toString() function and 
     * displays the item name along with its description and ID number
     */
    @Override
    public String toString(){
        return itemName + ": " + getLongDescription() + "\nItem ID: " + getId();
    }
}
