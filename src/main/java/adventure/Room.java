package adventure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class gives the user the ability to get and set private variables of rooms
 * @auhtor Luke Fischer 1061800
 * 
 */

public class Room implements java.io.Serializable  {
    private static final long serialVersionUID = 5516064512980044942L;

    /**Declare  constant of available directions*/
    public static final int DIRECTION = 6;

    private int id;
    private String start;
    private String name;
    private String shortDescr;
    public String longDescr;
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private HashMap<String, Room> availableExits = new HashMap<String, Room>();
    private String[] allDirections = new String[] {"N", "E", "S", "W", "up", "down"};

    /**
     * Initializes instance varaibles with default variables
     */
    public Room(){
        id = 0;
        start = "No Start";
        name = "No Name";
        shortDescr = "No Short Description";
        longDescr = "No long Description";
    }
    
    // 0 is north
    // 1 is east
    // 2 is south
    // 3 is west
    // 4 is up
    // 5 is down
    private int[] entranceArray = new int[DIRECTION];    

    /**
     * Initializes Room variables 
     * @param id
     * @param start
     * @param name
     * @param shortDescr
     * @param longDescr
     * @param itemList
     * @param entranceArray
     */
    public Room(int id, String start, String name, String shortDescr, String longDescr, ArrayList<Item> itemList, int[] entranceArray){

        this.id = id;
        this.start = start;
        this.name = name; 
        this.shortDescr = shortDescr;
        this.longDescr = longDescr;
        this.itemList = itemList;
        this.entranceArray = entranceArray;
    }


    /* required public methods */

    /**
     * Gets a list of items in a certain room
     * @return itemList
     */
    public ArrayList<Item> listItems(){
        //lists all the items in the room
        return itemList;
    }

    /**
     * Gets the id of a room
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the id of the room
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * gets start value of a room
     * @return start
     */
    public String getStart(){
        return start;
    }

    /**
     * Sets the string value of start
     * @param start
     */
    public void setStart(String start){
        this.start = start;
    }

    /**
     * gets the name of a room
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the room
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the short description of a room
     * @return shortDescr
     */
    public String getShortDescription(){
        return shortDescr;
    }

    /**
     * Sets the short description of the room
     * @param shortDescr
     */
    public void setShortDescription(String shortDescr){
        this.shortDescr = shortDescr;
    }

    /**
     * Gets the long description of the room
     * @return longDescr
     */
    public String getLongDescription(){
        return longDescr;
    }

    /**
     * Sets the long description of the room
     * @param longDescr
     */
    public void setLongDescription(String longDescr){
        this.longDescr = longDescr;
    }

    /**
     * Gets wether or not a room is connected
     * @param direction
     * @return Room object
     */

    public Room getConnectedRoomHash(String direction) {
        if(availableExits.containsKey(direction)){
            return availableExits.get(direction);
        }
        else{
            return null;
        }
    }

    //for testing only
    public void setConnectedRooms(String direction, Room room){
        availableExits.put(direction, room);
    }

    /**
     * Determines connected rooms
     * @param allRooms
     */
    //Hash Map implemented
    public void updateExits(ArrayList<Room> allRooms){
        for (int i = 0 ; i < entranceArray.length; i++ ){
            if (entranceArray[i] != 0){
                for (int k = 0 ; k < allRooms.size(); k++ ){
                    if (entranceArray[i] == allRooms.get(k).getId()){
                        availableExits.put(allDirections[i], allRooms.get(k));
                        break;
                    }
                }      
            } else{
                availableExits.put(null, null);
            }
        }
    }

    /**
     * This function overrides the super classes toString() function and 
     * displays the name of the room along with its description and ID number
     */
    @Override
    public String toString(){
        return "You are in the " + getName() + " room\n" + getShortDescription() + "\nRoom ID: " + getId();
    }
}
