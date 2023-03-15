package adventure;

public class Parser{

    private String userInput;

    /**
     * Default Constructor setting the string to be parsed to null;
     */
    public Parser(){
        userInput = "";
    }
    
    /**
     * Send user input to Command.java to test for valid inputs
     * @param userInput
     * @return The command
     * @throws InvalidCommandException
     */
    public Command parseUserInput(String userInput) throws InvalidCommandException{
        this.userInput = userInput;
       //Split user input into verb and noun
        String[] splitText = userInput.split(" ");

        if((splitText.length == 0) || (splitText.length > 2)){
            throw new InvalidCommandException();
        }
        else{  
            if(splitText.length == 1){       
                //Create a command object for command validation
                if(splitText[0].equals("go")){
                    System.out.println("Specify a direction you'd like to go!");
                    throw new InvalidCommandException();
                }
                Command oneCommand = new Command(splitText[0]);
                return oneCommand;
            }
            else{
                //Create a command object for noun validation
                Command twoCommand = new Command(splitText[0], splitText[1]);
                return twoCommand;
            } 
        }
    }

    /**
     * Creates and returns a String that provides detailed instructions on what actions players can do
     * @return String of instructions
     */
    public String allCommands(){
        String commandsString = ("Type \"go\" and a direction \"N, S, E or W\" to move\n");
        commandsString = commandsString + "Type \"look\" followed with an item name to see a description of the item\n";
        commandsString = commandsString + "Type only \"look\" to see the description of the room\n";
        commandsString = commandsString + "Type \"inventory\" to view the items in your inventory\n";
        commandsString = commandsString + "Type \"quit\" to quit the program\n";
        return commandsString;
    }

    /**
     * This function overrides the super classes toString() function and 
     * displays the current user command
     */
    @Override
    public String toString(){
        return "Current command: " + userInput;
    }
}
