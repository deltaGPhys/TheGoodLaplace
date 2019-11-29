package com.forestfire;

import javax.swing.*;
import java.awt.*;

public class SimpleWindow {
    static JPanel panel;
    static JFrame frame;
    private Integer dim = 0;
    private Integer boxDim = 5;
    private static final Color BLACK = new Color(0, 0, 0);
    private static final Color DARKGREEN = new Color(0, 125, 0);
    private static final Color ORANGE = new Color(200, 150, 0);

    public SimpleWindow(Integer dimension) {
        this.dim = dimension * this.boxDim;
        panel = new JPanel();
        Dimension dim = new Dimension(this.dim, this.dim);
        panel.setPreferredSize(dim);
        frame = new JFrame();
        Integer framesize = (this.dim < 100) ? 100 : this.dim;
        frame.setSize(framesize, framesize);
        Container contentPane = frame.getContentPane();
        contentPane.add(panel);
        frame.setVisible(true);
    }

    public void sleep(Integer millisecs) {
        try {
            Thread.sleep(millisecs);
            Graphics g = panel.getGraphics();
            g.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void display(int[][] array, Integer n) {
        frame.setTitle(String.format("Generation: %6d", n));
        Graphics g = panel.getGraphics();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                g.drawRect(i * this.boxDim, j * this.boxDim, this.boxDim, this.boxDim);
                if (array[i][j] == 0) {
                    g.setColor(BLACK);
                } else if (array[i][j] == 1) {
                    g.setColor(DARKGREEN);
                } else if (array[i][j] == 2) {
                    g.setColor(ORANGE);
                }
                g.fillRect(i * this.boxDim-1, j * this.boxDim, this.boxDim+1, this.boxDim);
            }
        }

    }
}
