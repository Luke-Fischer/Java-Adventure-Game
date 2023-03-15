package adventure;

import java.util.ArrayList;

/**
 * This class contains information on a player
 */

public class Player implements java.io.Serializable  {
    private static final long serialVersionUID = -8484731477214611735L;
    private String playerName;
    private ArrayList<Item> playerInventory = new ArrayList<Item>();
    private Room currentRoom;
    private String savedNameGame;

    /**
     * Initializes private instance method to defalult values
     */
    public Player(){
        playerName = "n/a";
        currentRoom = null;
        savedNameGame = "n/a";
    }

    public Player(Adventure playerAdvenure){
        currentRoom = playerAdvenure.getCurrentRoom();
    }

    /**
     * Sets the name of a player
     * @param playerName
     */
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    /**
     * Gets the name of the player 
     * @return the name of the player
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Adds an item to the players inventory
     * @param playerItem
     */
    public void addInventory(Item playerItem){
        this.playerInventory.add(playerItem);
    }

    /**
     * @return The players inventory
     */
    public ArrayList<Item> getInventory(){
        return playerInventory;
    }

    /**
     * Prints out the players inventory
     */
    public void printInventory(){
        System.out.print("\nYour inventory:\n");
        for(int i = 0; i < playerInventory.size(); i++){
            System.out.println(playerInventory.get(i).getName());
        }
    }

    /**
     * Sets the current room of the hero
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }

    /**
     * gets the current room of the hero
     * @return
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }

    /**
     * Sets the name of what the user whishes their game to be saved by
     * @param savedGame
     */
    public void setSavedNameGame(String savedGame){
        this.savedNameGame = savedGame;
    }

    /**
     * Gets the name of the saved game
     * @return the name of the saved game
     */
    public String getSavedNameGame(){
        return savedNameGame;
    }

    /**
     * This function overrides the super classes toString() function and 
     * displays the name of the player and their current location
     */
    @Override
    public String toString(){
        return playerName + " is currently in the room: " + getCurrentRoom().getName();
    }
}
