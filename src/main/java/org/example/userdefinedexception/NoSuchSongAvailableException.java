package org.example.userdefinedexception;

public class NoSuchSongAvailableException extends Exception
{
    public NoSuchSongAvailableException(String message) {
        super(message);
    }
}
