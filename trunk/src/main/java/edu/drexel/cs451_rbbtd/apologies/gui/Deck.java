package edu.drexel.cs451_rbbtd.apologies.gui;

/**
 * Created by Ben on 2/24/14.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Deck {

    String deck = Apologies.getResourcePath("Deck.png");
    String one = Apologies.getResourcePath("One.png");
    String two = Apologies.getResourcePath("Two.png");
    String three = Apologies.getResourcePath("Three.png");
    String four = Apologies.getResourcePath("Four.png");
    String five = Apologies.getResourcePath("Five.png");
    String seven = Apologies.getResourcePath("Seven.png");
    String eight = Apologies.getResourcePath("Eight.png");
    String ten = Apologies.getResourcePath("Ten.png");
    String eleven = Apologies.getResourcePath("Eleven.png");
    String twelve = Apologies.getResourcePath("Twelve.png");
    String apologies = Apologies.getResourcePath("Apologies!.png");
    String cards[] = {one,two,three,four,five,seven,eight,ten,eleven,twelve,apologies};

    private int x;
    private int y;
    private Image image;

    public Deck(int x, int y) {
        ImageIcon ii = new ImageIcon(deck);
        image = ii.getImage();
        this.x = x;
        this.y = y;

    }

    public Card drawCard() {
        Random generator = new Random();
        int i = generator.nextInt(11);
        String rand = cards[i];
        Card card = new Card(295, 210, rand);
        return card;

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
