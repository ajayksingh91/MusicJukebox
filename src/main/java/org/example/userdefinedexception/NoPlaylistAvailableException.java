package org.example.userdefinedexception;

public class NoPlaylistAvailableException extends Exception
{
    public NoPlaylistAvailableException(String message) {
        super(message);
    }
}
