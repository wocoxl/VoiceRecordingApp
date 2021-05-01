package com.company;


public class Main {

    public static void main(String[] args) {
	// write your code here
        Recorder rc = new Recorder(16000,8,2,true,true);
        long record_time = 60000;
        Thread stopper = new Thread( new Runnable(){
            public void run(){
                try{
                    Thread.sleep(record_time);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                rc.finish();
            }
        }

        );
        stopper.start();

        rc.start();

    }
}
