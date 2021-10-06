package org.example.userdefinedexception;

public class NoSuchAlbumAvaiableException extends Exception
{
    public NoSuchAlbumAvaiableException(String message) {
        super(message);
    }
}
