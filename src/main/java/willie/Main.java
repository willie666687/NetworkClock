package willie;

import willie.handler.DataHandler;
import willie.thread.TimeThread;
import willie.windowManager.JFrame;
import willie.windowManager.JPanel;
import willie.impl.ConfigOptions;

public class Main {
    public static ConfigOptions configOptions;
    public static JPanel panel;
    public static JFrame frame;
    public static void main(String[] args){
        DataHandler.createFile();
        DataHandler.loadConfig();
        panel = new JPanel();
        frame = new JFrame();
        TimeThread timeThread = new TimeThread();
        timeThread.start();
    }
}