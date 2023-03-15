To add serialization to your project for game saves.


1. Decide which objects you are saving?  Game? Adventure? All of them?
2. Add implements java.io.Serializable to the class declaration for every class that will be serialized
3. Add a private static final long serialVersionUID  member variable to each class and give it a value.  Use your IDE or serialver on the command line
4. Write a method in Game to save the serialized objects and one to load them.  The methods will look a lot like my examples.

