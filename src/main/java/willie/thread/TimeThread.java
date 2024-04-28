package willie.thread;

import willie.util.DebugOutput;
import willie.util.TimeUtils;

import java.util.Calendar;
import java.util.Date;

import static willie.Main.frame;
import static willie.util.TimeUtils.getTime;

public class TimeThread extends Thread {
    @Override
    public void run(){
        while(true){
            frame.repaint();
            Date date = getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            double second = calendar.get(Calendar.SECOND);
            DebugOutput.print(String.valueOf(second));
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
