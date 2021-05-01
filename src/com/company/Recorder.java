package com.company;
import javax.sound.sampled.*;
import java.io.*;

public class Recorder {
    private AudioFormat AF;
    private String fileName;
    private TargetDataLine line;
    private File wavFile;
    private AudioFileFormat.Type fileType;
    private DataLine.Info info;

    public Recorder(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian){
        AF = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        wavFile = new File("C:/Users/Bob/IdeaProjects/VoiceRecordingApp/test.wav");
        fileType = AudioFileFormat.Type.WAVE;

        info = new DataLine.Info(TargetDataLine.class, AF);
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setFileName(String fn){
        fileName = fn;
    }

    public void start(){
        try{

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }

            line.open(AF);
            line.start();   // start capturing

            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, fileType, wavFile);


        } catch (IOException e) {
            e.printStackTrace();
        }catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void finish(){
        line.stop();
        line.close();
        System.out.println("Recording has ended");
    }

}
