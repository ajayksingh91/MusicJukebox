package org.example.userdefinedexception;

public class EmptyPlaylistException extends Exception
{
    public EmptyPlaylistException(String message) {
        super(message);
    }
}
