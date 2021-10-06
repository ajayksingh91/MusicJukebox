package org.example.impl;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PlayMusicImpl {

    AudioInputStream audioInputStream;
    Long currentFrame;
    Clip clip;
    String status;
    String filePath;
    Scanner sc = new Scanner(System.in);

    public PlayMusicImpl(String  location) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.filePath=location;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void MusicPlay()
    {
        try
        {
            int choice;
            clip.start();
            status = "play";

            do
            {
                System.out.println("1. Pause\n2. Resume\n3. Restart\n4. Stop\n 5. Exit");
                System.out.println("Enter your choice : ");
                choice = sc.nextInt();
                switch (choice)
                {
                    case 1:
                        if (status.equals("paused"))
                        {
                            System.out.println("audio is already paused");

                            return;
                        }
                        this.currentFrame = this.clip.getMicrosecondPosition();
                        clip.stop();
                        status = "paused";
                        System.out.println("Music is Pause at : "+currentFrame+ " time");
                        break;
                    case 2:
                        if (status.equals("play"))
                        {
                            System.out.println("Audio is already being played");
                            return;
                        }
                        clip.close();
                        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                        clip.open(audioInputStream);
                        clip.setMicrosecondPosition(currentFrame);
                        clip.start();
                        status = "play";
                        System.out.println("Music is Resume at : "+currentFrame+ " time");
                        break;
                    case 3:
                        clip.stop();
                        clip.close();
                        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                        clip.open(audioInputStream);
                        currentFrame = 0L;
                        clip.setMicrosecondPosition(0);
                        clip.start();
                        status = "play";
                        break;
                    case 4:
                        currentFrame = 0L;
                        clip.stop();
                        clip.close();
                        break;
                    default:
                        break;
                }

            }while (choice<=4);

        }
        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
