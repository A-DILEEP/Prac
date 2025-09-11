package com.example.phase2ex4.dto;


public class ArtistRequestDto {
    private String firstName;
    private String lastName;
 
    public ArtistRequestDto() {}
 
    public ArtistRequestDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
 
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
 
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
