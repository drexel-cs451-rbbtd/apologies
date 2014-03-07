package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;

public class Card {

    private int x;
    private int y;
    private int cardNum;
    private Image image;

    public Card(int x, int y, String path, int number) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        this.x = x;
        this.y = y;
        this.cardNum = number;
    }

    public Card(Card card) {
        image = card.getImage();
        this.x = card.getX();
        this.y = card.getY();
        this.cardNum = card.getNumber();

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber() {
        return cardNum;
    }

    public Image getImage() {
        return image;
    }
}
