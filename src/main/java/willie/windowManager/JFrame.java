package willie.windowManager;

import java.util.Objects;

import static willie.Main.*;

public class JFrame extends javax.swing.JFrame {
    public JFrame() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setBounds(740, 350, 450, 300);
        setTitle("clock");
        add(panel);
        addKeyListener(new KeyAdapter());
        if(Objects.equals(configOptions.DefaultFullScreen, "true")){
            setUndecorated(true);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        setVisible(true);
    }
}
