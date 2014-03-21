package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Deck {

    String deckImagePath = ApologiesGameWindow.getResourcePath("Deck.png");

    String oneImagePath = ApologiesGameWindow.getResourcePath("One.png");
    String twoImagePath = ApologiesGameWindow.getResourcePath("Two.png");
    String threeImagePath = ApologiesGameWindow.getResourcePath("Three.png");
    String fourImagePath = ApologiesGameWindow.getResourcePath("Four.png");
    String fiveImagePath = ApologiesGameWindow.getResourcePath("Five.png");
    String sevenImagePath = ApologiesGameWindow.getResourcePath("Seven.png");
    String eightImagePath = ApologiesGameWindow.getResourcePath("Eight.png");
    String tenImagePath = ApologiesGameWindow.getResourcePath("Ten.png");
    String elevenImagePath = ApologiesGameWindow.getResourcePath("Eleven.png");
    String twelveImagePath = ApologiesGameWindow.getResourcePath("Twelve.png");
    String apologiesImagePath = ApologiesGameWindow.getResourcePath("Apologies!.png");

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
