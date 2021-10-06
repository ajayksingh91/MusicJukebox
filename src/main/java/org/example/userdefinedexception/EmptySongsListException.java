package org.example.userdefinedexception;

public class EmptySongsListException extends  Exception
{
    public EmptySongsListException(String message) {
        super(message);
    }
}
