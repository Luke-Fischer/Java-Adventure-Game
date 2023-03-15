package adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;


public class RoomTest{
    private Room testRoom;

@Before
public void setup(){
    testRoom = new Room();

}

@Test
//Tests the set/getter function for room Name function
public void testSetNameWithValidInput(){
    System.out.println("Testing setName with valid name");
    String roomName = "one";
    testRoom.setName(roomName);
    assertTrue(testRoom.getName().equals(roomName));

}

@Test
//Tests the set/getter functions for room ID
public void testSetIdWithValidInput(){
    System.out.println("Testing setID with valid room ID");
    int roomID = 100;
    testRoom.setId(roomID);
    assertTrue(testRoom.getId() == roomID);
}

@Test
//Tests for start room 
public void setBeginningRoomWithValidInput(){
    System.out.println("Testing setStart with valid start String");
    String start = "true";
    testRoom.setStart(start);
    assertTrue(testRoom.getStart().equals(start));
}

@Test
//tests when testRoom is not the starting room
public void setBeginningRoomWithNullInput(){
    System.out.println("Testing setStart with null start String");
    String start = "";
    testRoom.setStart(start);
    assertTrue(testRoom.getStart().equals(start));
}

@Test 
//Tests the set/getter for short description
public void setShortDescriptionWithValidInput(){
    System.out.println("Testing short description with valid input");
    String shortDescription = "An object";
    testRoom.setStart(shortDescription);
    assertTrue(testRoom.getStart().equals(shortDescription));
}


@Test 
//Tests the set/getter for long description
public void setLongDescriptionWithValidInput(){
    System.out.println("Testing long description with valid input");
    String longDescription = "An object with a long description";
    testRoom.setStart(longDescription);
    assertTrue(testRoom.getStart().equals(longDescription));
}

@Test
public void setConnectedRoomMethod() {
    System.out.println("Testing the get connected room HashMap");
    Room testRoom100 = new Room();
    testRoom100.setId(100);

    Room testRoom200 = new Room();
    testRoom200.setId(200);

    Room testRoom300 = new Room();
    testRoom300.setId(300);

    testRoom.setConnectedRooms("S", testRoom100);
    testRoom.setConnectedRooms("N", testRoom200);
    testRoom.setConnectedRooms("E", testRoom300);
    assertTrue(testRoom.getConnectedRoomHash("S").getId() == testRoom100.getId());
    assertTrue(testRoom.getConnectedRoomHash("N").getId() == testRoom200.getId());
    assertTrue(testRoom.getConnectedRoomHash("E").getId() == testRoom300.getId());
}

}
