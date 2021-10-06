package org.example.userdefinedexception;

public class NoPodcastAvailableException extends Exception
{
    public NoPodcastAvailableException(String message) {
        super(message);
    }
}
