package com.dialexa.hrsp;


public class Room {

    private String id;
    private String roomNumber;
   // private String nightOfAvailability;
   // private boolean availability;
    private boolean petFriendly;
   // private List<Bed> beds;
   // private String localeId;

    public Room(String id, String roomNumber, boolean petFriendly){
        this.id=id;
        this.roomNumber=roomNumber;
        this.petFriendly=petFriendly;
    }

    public Room() {
    }


    public String getRoomNumber() {
        return this.roomNumber;
    }

    public String getId() {
        return this.id;
    }

    public boolean getPetFriendly() {
        return this.petFriendly;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPetFriendly(Boolean petFriendly) {
        this.petFriendly = petFriendly;
    }
}