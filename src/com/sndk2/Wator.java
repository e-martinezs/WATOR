package com.sndk2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Wator extends JPanel {

    private BufferedImage buffer;
    private Graphics2D g2d;

    public Wator(){
        createBuffer();
    }

    private void createBuffer(){
        buffer = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = buffer.createGraphics();
    }

    public void update(double delta){

    }

    @Override
    public void paintComponent(Graphics g) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        g.drawImage(buffer, 0 ,0, this);
    }
}
