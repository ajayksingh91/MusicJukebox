package org.example.userdefinedexception;

public class ArtistNotFoundException extends Exception
{
    public ArtistNotFoundException(String message) {
        super(message);
    }
}
