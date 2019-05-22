package com.sm.frame;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeThread extends  Thread {
    private JLabel timeLabel;
    public  void setTimeLabel(JLabel timeLabel){
        this.timeLabel = timeLabel;
    }

    @Override
    public void run() {
        while (true){
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
            timeLabel.setText(String.valueOf(sf.format(date)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
