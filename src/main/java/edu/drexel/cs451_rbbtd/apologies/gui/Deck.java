package edu.drexel.cs451_rbbtd.apologies.gui;

/**
 * Created by Ben on 2/24/14.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Deck {

    String deck = "src\\main\\resources\\Deck.png";
    String one = "src\\main\\resources\\One.png";
    String two = "src\\main\\resources\\Two.png";
    String three = "src\\main\\resources\\Three.png";
    String four = "src\\main\\resources\\Four.png";
    String five = "src\\main\\resources\\Five.png";
    String seven = "src\\main\\resources\\Seven.png";
    String eight = "src\\main\\resources\\Eight.png";
    String ten = "src\\main\\resources\\Ten.png";
    String eleven = "src\\main\\resources\\Eleven.png";
    String twelve = "src\\main\\resources\\Twelve.png";
    String apologies = "src\\main\\resources\\Apologies!.png";
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
