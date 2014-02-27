package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;

public class Pawn{

    private int x;
    private int y;
    private Image image;
    private int space = -1; // Records how many spaces forward from start location the pawn has traveled
    private int positions[][];


    public Pawn(int x, int y, int[][] positions, String path) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        this.x = x;
        this.y = y;
        this.positions = positions;

    }


    public void moveForward() {
        space +=1;
        x = positions[space][0];
        y = positions[space][1];
    }

    public void moveBack() {
        space -=1;
        x = positions[space][0];
        y = positions[space][1];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

} // end class