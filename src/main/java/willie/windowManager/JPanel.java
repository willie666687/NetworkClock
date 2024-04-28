package willie.windowManager;

import willie.util.DebugOutput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static java.lang.Math.abs;
import static willie.Main.configOptions;
import static willie.util.TimeUtils.getTime;

public class JPanel extends javax.swing.JPanel {
    double imgWidthScale;
    double imgHeightScale;
    double windowWidth;
    double windowHeight;
    double finalScale = 1;
    String pictureShowMethod;
    public void paint(Graphics g) {
        windowWidth = getWidth();
        windowHeight = getHeight();
        pictureShowMethod = configOptions.pictureShowMethod;
        setupBackgroundImage(g);
        setupClockPing(g);
    }
    void setupClockPing(Graphics g){
        BufferedImage clockPingImg = null;
        try {
            clockPingImg = ImageIO.read(new File("config", configOptions.ClockPingPicture));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int posX = (int) (windowWidth / 2);
        int posY = (int) (windowHeight / 2 - clockPingImg.getHeight()/2);
        double pingWidthScale = imgWidthScale * configOptions.pingScale * finalScale;
        double pingHeightScale = imgHeightScale * configOptions.pingScale * finalScale;
        AffineTransform at = new AffineTransform();
        at.translate(posX, posY);
        at.scale(pingWidthScale, pingHeightScale);
        Date date = getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        double second = calendar.get(Calendar.SECOND);
        double secondRotation = second * 6 - 90;
        at.rotate(Math.toRadians(secondRotation));
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(clockPingImg, at, null);
    }
    void setupBackgroundImage(Graphics g){
        BufferedImage backGroundImg = null;
        try {
            backGroundImg = ImageIO.read(new File("config", configOptions.BackGroundPicture));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        double imgWidth = backGroundImg.getWidth();
        double imgHeight = backGroundImg.getHeight();
        imgWidthScale = windowWidth / imgWidth;
        imgHeightScale = windowHeight / imgHeight;
        if(Objects.equals(pictureShowMethod, "center")){
            if(imgWidthScale < imgHeightScale) {
                finalScale = imgWidthScale;
                imgWidth *= finalScale;
                imgHeight *= finalScale;
                imgWidthScale = 1;
                imgHeightScale = 1;
            }else{
                finalScale = imgHeightScale;
                imgHeight *= finalScale;
                imgWidth *= finalScale;
                imgWidthScale = 1;
                imgHeightScale = 1;
            }
            int imgPosX = (int) (abs(windowWidth - imgWidth) / 2);
            int imgPosY = (int) (abs(windowHeight - imgHeight) / 2);
            System.out.println("imgPosX: " + imgPosX + " imgPosY: " + imgPosY);
            System.out.println("imgWidth: " + imgWidth + " imgHeight: " + imgHeight);
            System.out.println("windowWidth: " + windowWidth + " windowHeight: " + windowHeight);
            System.out.println("imgWidthScale: " + imgWidthScale + " imgHeightScale: " + imgHeightScale);
            g.drawImage(backGroundImg, imgPosX, imgPosY, (int)imgWidth, (int)imgHeight, null);
        }else if (Objects.equals(pictureShowMethod, "scale")){
            g.drawImage(backGroundImg, 0, 0, (int)windowWidth, (int)windowHeight, null);
        }
    }
}
