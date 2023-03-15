package adventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


/**
 * This class validates user input
 */
public class Command implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 8461394084050192693L;
    private String action;
    private String noun;
    static HashSet<String> validActions = new HashSet<String>();
    static HashSet<String> validNouns = new HashSet<String>();
  /**
     * Create a command object with default values.  
     * both instance variables are set to null
     * 
     */
    public Command() throws InvalidCommandException {
        this(null, null);
    }

    /**
     * one parameter constructer that initializes valid action and valid nouns HashSet
     * @param itemList
     */
    public Command(ArrayList<Item> itemList){
        Collections.addAll(validActions, "go", "look", "quit", "take", "inventory");
        Collections.addAll(validNouns, "N", "S", "E", "W", "up", "down");
        for(int i = 0; i < itemList.size(); i++){
            validNouns.add(itemList.get(i).getName());
        }
    }

  /**
   * Create a command object given only an action. this.noun is set to null
   *
   * @param command The first word of the command.
   * @throws Exception
   * 
   */
  public Command(String command) throws InvalidCommandException {
        if(!(validActions.contains(command))){
            throw new InvalidCommandException();
        }
        this.action = command; 
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command. 
     * @param what      The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException{
        if(!(validActions.contains(command))){
            throw new InvalidCommandException();
        } 
        if(!(validNouns.contains(what))){
            throw new InvalidCommandException();
        } 
        this.action = command;
        this.noun = what;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     */
    public String getActionWord() {
        return this.action;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getNoun() {
        return this.noun;
    }



    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }
}
