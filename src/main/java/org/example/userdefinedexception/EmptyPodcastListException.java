package org.example.userdefinedexception;

public class EmptyPodcastListException extends Exception
{
    public EmptyPodcastListException(String message) {
        super(message);
    }
}
