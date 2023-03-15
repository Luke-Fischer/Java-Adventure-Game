package adventure;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class that runs the game
 */

public class Game implements Serializable{
    /**Serialization ID number*/
    private static final long serialVersionUID = 440660157529395718L;
    /** Declares constant for the 6 available directions */
    public static final int DIRECTIONS = 6;
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private Parser parser = new Parser();
    private Adventure myAdventure;
    private Player player;
    String playerName;

    /**
     * Default constructer initializing private instance methods
     */
    public Game(){
        myAdventure = null;
        player = null;
        playerName = null;
    }

    /**
     * This is the method that runs the game
     * 
     * @param args
     * @throws InvalidCommandException
     * @throws IOException
     */
    public static void main(String args[]) throws InvalidCommandException {
        boolean defaultAdventure = false;
        boolean validCommand = true;
        boolean loadFile = false;
        Game theGame = new Game();
        Command nextCommand = null;
        // 1. Print a welcome message to the user
        System.out.println("Welcome to Colossal Cave");

        String fileName = "";
        //File path for json file
        fileName = "src/main/java/adventure/default_adventure.json";
        defaultAdventure = true;

        // 2. Ask the user if they want to load a json file.
        Scanner scnr = new Scanner(System.in);
        String line;
        System.out.println("You are currently playing the deafult adventure - to continue press any button followed by <enter>");
        System.out.println("To load a new game type \"-a <JSON file relative path>\"");
        System.out.println("To load a saved game type \"-l <Saved game name>\"");
    
        line = scnr.next();
        /* 3. Parse the file the user specified to create the
        adventure, or load your default adventure*/

        if(line.equals("-a")){
            fileName = scnr.next();
            defaultAdventure = false;
        }

        if(line.equals("-l")){
            fileName = scnr.next();
            DeSerializationTest deSerialize = new DeSerializationTest();
            deSerialize.Deserialize(fileName);
            theGame.myAdventure = deSerialize.getAdventure();
            theGame.player = deSerialize.getPlayer();
            Command initCommand = new Command(theGame.myAdventure.listAllItems());
            loadFile = true;
        }
        
        if(!loadFile){
            //Load adventure, return Json file object, set to adventureJson
            JSONObject adventureJson = theGame.loadAdventureJson(fileName);

            //Generate adventure
            theGame.myAdventure = theGame.generateAdventure(adventureJson);

            //Ask for player name
            System.out.println("Enter your heros name:");
            theGame.playerName = scnr.next();
            theGame.player.setPlayerName(theGame.playerName);
            scnr.nextLine();

            // 4. Print the beginning of the adventure
            if(defaultAdventure == true){
                System.out.println("You have woken up on a city bus in a campus bus ring. You look outside to see a blizzard starting to form.");
                System.out.println("To secure a more reliable place of shelter you walk into the nearest building. A sign reads University Center.");
            }
        }
        // 5. Begin game loop here
        Boolean done = false;
        String text = "";
        System.out.println();
        System.out.println(theGame.parser.allCommands());

        while(!done){
            text = "";
            try{ 
                // 6. Get the user input. 
                while (text.equals("")){
                    text = scnr.nextLine();
                }
                //Send user input to be parsed
                nextCommand = theGame.parser.parseUserInput(text);
            } 
            catch(InvalidCommandException excp){
                System.out.println("Cannot use that command");
                validCommand = false;
            }
            if(validCommand){
                theGame.followCommands(nextCommand);
            }
            validCommand = true;
            System.out.println();
        }
        scnr.close();
    }

    private void followCommands(Command nextCommand){
        if(nextCommand.getActionWord().equals("quit")){
            quitGame();
        }
        else if(nextCommand.getActionWord().equals("inventory")){
            player.printInventory();
        }
        else if(nextCommand.getActionWord().equals("go")){
            myAdventure.moveHero(nextCommand);
            player.setCurrentRoom(myAdventure.getCurrentRoom());
        }
        else if(nextCommand.getActionWord().equals("look")){
            if(nextCommand.hasSecondWord()){
                myAdventure.lookAtSomething(nextCommand);
            }
            else{
                myAdventure.lookAround();
            }
        }
        else if(nextCommand.getActionWord().equals("take")){
            if(nextCommand.hasSecondWord()){
                boolean itemInRoom = false;
                for(int k = 0; k < myAdventure.getCurrentRoom().listItems().size(); k++){
                    if(myAdventure.getCurrentRoom().listItems().get(k).getName().equals(nextCommand.getNoun())){
                        player.addInventory(myAdventure.getCurrentRoom().listItems().get(k));
                        itemInRoom = true;
                        System.out.println(myAdventure.getCurrentRoom().listItems().get(k).getName()+ " has been added to your inventory");
                    }
                    if(!itemInRoom){
                        System.out.println("Cannot pick up item as it not in your current room");
                    }
                }
            }
        }
    }

    /**
     * Asks user for a name to save the game and serializes the game
     */
    public void quitGame(){
        Scanner newScnr = new Scanner(System.in);
        String saveName = "";
        System.out.println("Name your saved game:");
        saveName = newScnr.next();
        player.setSavedNameGame(saveName);
        SerializationTest savedGame = new SerializationTest(player, myAdventure);
        savedGame.Serialize();
        newScnr.close();
        System.exit(1);
    }

    /**
     * This class loads the adventure
     * @param filename
     * @return JSON object
     */
    public JSONObject loadAdventureJson(String filename){
        //Initialize JSON object that will hold contents of the file
        JSONObject adventureJson;

        //Initialize an new JSON parser
        JSONParser parser = new JSONParser(); 
        
        try(Reader reader = new FileReader(filename)){
            //parse file into JSON object
            adventureJson = (JSONObject)parser.parse(reader);
            } catch (Exception e){
            System.out.println("File not found.");
            adventureJson = null;
            System.exit(1);
        }
        return adventureJson;
    }
    
    /**
     * This method reads in a Json file and creates an adventure object (i.e rooms, items etc..)
     * @param adventureJson
     * @return Adventure 
     */
    public Adventure generateAdventure(JSONObject adventureJson) {
        JSONObject one = (JSONObject) adventureJson.get("adventure");
        if(one != null){

            // Load item_List
            JSONArray items = (JSONArray) one.get("item");
            if(items != null){
                for(Object currentItem : items){
                    JSONObject item = (JSONObject) currentItem;
                    int itemId = (int)(long) item.get("id");
                    String itemName = (String) item.get("name");
                    String itemDescr = (String) item.get("desc");
                    Item nextItem = new Item(itemId, itemName, itemDescr);
                    itemList.add(nextItem);
                }
            }                

            // Load room_List
            JSONArray roomListJson = (JSONArray) one.get("room");
            for(Object currentRoom : roomListJson){
                JSONObject room = (JSONObject) currentRoom;
                    
                int roomId = (int) (long) room.get("id");
                String start = (String) room.get("start");
                String currentName = (String) room.get("name");
                String shortDescription = (String) room.get("short_description");
                String longDescription = (String) room.get("long_description");
                    
                int[] entranceArray = new int[DIRECTIONS];
                JSONArray entranceList = (JSONArray)room.get("entrance");
                for(Object currentEntrance : entranceList){
                    JSONObject entrance = (JSONObject) currentEntrance;

                    int entranceId = (int)(long) entrance.get("id");
                    String direction = (String) entrance.get("dir");
                    if(direction.equals("N")){
                        entranceArray[0] = entranceId;
                    } else if(direction.equals("E")){
                        entranceArray[1] = entranceId;
                    } else if(direction.equals("S")){
                        entranceArray[2] = entranceId;
                    } else if(direction.equals("W")){
                        entranceArray[3] = entranceId;
                    } else if(direction.equals("up")){
                        entranceArray[4] = entranceId;
                    } else if(direction.equals("down")){
                        entranceArray[5] = entranceId;
                    } 
                }

                //Get loot
                ArrayList<Item> lootIdList = new ArrayList<Item>();
                JSONArray lootList = (JSONArray)room.get("loot");
                if(lootList != null){
                    for(Object lootInRoom : lootList){
                        JSONObject loot = (JSONObject) lootInRoom;
                        int currentLoot = (int)(long) loot.get("id");
                        for (int i = 0 ; i < itemList.size(); i++ ){
                            if (itemList.get(i).getId() == currentLoot){
                                Item itemObj = itemList.get(i);
                                lootIdList.add( itemObj );
                                break;
                            }
                        }
                    }
                }
                //Create room object and add it to an array
                Room nextRoom = new Room(roomId, start, currentName, shortDescription, 
                                          longDescription, lootIdList, entranceArray);
                roomList.add(nextRoom);
            }

            //update all rooms
            for (int i = 0; i < roomList.size(); i++){
                roomList.get(i).updateExits(roomList);
            }

            // update all items
            for (int i = 0; i < itemList.size(); i++){
                itemList.get(i).updateItems(roomList);
            }
        }  
        Command initCommand = new Command(itemList);
        Adventure myAdventure = new Adventure(roomList, itemList);
        player = new Player(myAdventure);
        player.setCurrentRoom(myAdventure.getCurrentRoom());
        return myAdventure;
    }

    @Override
    public String toString(){
        return "Game saved name: " + player.getSavedNameGame() + "\nPlayer name: " + player.getPlayerName();
    }
}
