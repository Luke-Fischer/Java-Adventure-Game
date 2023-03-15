# Game Description

## How to operate your program
clean: mvn clean
compile: mvn compile
execute: java:exec

### Instructions for using the program
-Run the excecutable
-You will be prompted wether or not you wish to run your own json file or use the default
-if you chose your own: You must type the relative path of that file (i.e src\main\java\adventure\"Your json file")
-A beggining output will appear
-You can move south with go S, north with go N, etc..
-Get a description of the current room by typing "look"
-Get a description of the current room and a certain item by typing "look" "item" - i.e ("wand", "potion").
-Exit the program by typing "exit"

The default map: 
                                N
    Room: War memorial                   Room: Johnson Green
    Item: Wand                           item: wand

    Room: Reynolds building
 W  item: Laptop                                                        E

    Room: Summerlee Science Complex       Room: University Centre
    item: potion                          item: no item
                                S


