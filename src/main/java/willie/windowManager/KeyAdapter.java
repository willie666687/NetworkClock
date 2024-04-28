package willie.windowManager;

import willie.util.DebugOutput;

import java.awt.event.KeyEvent;

import static willie.Main.frame;

public class KeyAdapter extends java.awt.event.KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        DebugOutput.print(e.paramString());
        if(e.getKeyCode() == KeyEvent.VK_F11){
            if(frame.isUndecorated()){
                frame.dispose();
                frame.setUndecorated(false);
                frame.setExtendedState(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }else {
                frame.dispose();
                frame.setUndecorated(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        }
    }
}
