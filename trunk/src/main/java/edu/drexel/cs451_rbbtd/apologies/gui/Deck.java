package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Deck {

    String deckImagePath = Apologies.getResourcePath("Deck.png");

    String oneImagePath = Apologies.getResourcePath("One.png");
    String twoImagePath = Apologies.getResourcePath("Two.png");
    String threeImagePath = Apologies.getResourcePath("Three.png");
    String fourImagePath = Apologies.getResourcePath("Four.png");
    String fiveImagePath = Apologies.getResourcePath("Five.png");
    String sevenImagePath = Apologies.getResourcePath("Seven.png");
    String eightImagePath = Apologies.getResourcePath("Eight.png");
    String tenImagePath = Apologies.getResourcePath("Ten.png");
    String elevenImagePath = Apologies.getResourcePath("Eleven.png");
    String twelveImagePath = Apologies.getResourcePath("Twelve.png");
    String apologiesImagePath = Apologies.getResourcePath("Apologies!.png");
    String cardImagePaths[] = {oneImagePath, twoImagePath, threeImagePath, fourImagePath, fiveImagePath, sevenImagePath, eightImagePath, tenImagePath, elevenImagePath, twelveImagePath, apologiesImagePath};

    private int x;
    private int y;
    private Image image;

    public Deck(int x, int y) {
        ImageIcon ii = new ImageIcon(deckImagePath);
        image = ii.getImage();
        this.x = x;
        this.y = y;
    }

    public Card drawCard() {
        Random generator = new Random();
        int i = generator.nextInt(11);
        String cardImagePath = cardImagePaths[i];
        Card card = new Card(295, 210, cardImagePath, i);
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
