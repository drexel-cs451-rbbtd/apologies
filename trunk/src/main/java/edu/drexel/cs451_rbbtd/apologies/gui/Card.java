package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ben on 2/24/14.
 */
public class Card {

    private int x;
    private int y;
    private Image image;

    public Card(int x, int y, String path) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        this.x = x;
        this.y = y;

    }

    public Card(Card card) {
        image = card.getImage();
        this.x = card.getX();
        this.y = card.getY();

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
}
