package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;

public class Pawn {

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


    //////////////////////////////////// CARD FUNCTIONS//////////////////////////////////////

    // All card functions only one function for now
    public void One(){
        this.moveForward();
    }

    public void Two(){
        this.moveForward();
        this.moveForward();
    }

    public void Three(){
        this.moveForward();
        this.moveForward();
        this.moveForward();
    }

    public void Four(){
        this.moveBack();
        this.moveBack();
        this.moveBack();
        this.moveBack();
    }

    public void Five(){
        for (int i = 0; i < 5; i++) {
            this.moveForward();
        }
    }

    public void Seven(){ // Unimplemented for now
        this.moveForward();
    }

    public void Eight(){
        for (int i = 0; i < 8; i++) {
            this.moveForward();
        }
    }

    public void Ten(){
        for (int i = 0; i < 10; i++) {
            this.moveForward();
        }
    }

    public void Eleven(){ // unimplemented second function swap
        for (int i = 0; i < 11; i++) {
            this.moveForward();
        }
    }

    public void Twelve(){
        for (int i = 0; i < 12; i++) {
            this.moveForward();
        }
    }

    public void Apologies(){ // unimplemented for now
        this.moveForward();
    }

    // Calls appropriate card function based on Card Number passed to it
    public void Move(int cardNum){
        switch (cardNum){
            case 0:
                this.One();
                break;
            case 1:
                this.Two();
                break;
            case 2:
                this.Three();
                break;
            case 3:
                this.Four();
                break;
            case 4:
                this.Five();
                break;
            case 5:
                this.Seven();
                break;
            case 6:
                this.Eight();
                break;
            case 7:
                this.Ten();
                break;
            case 8:
                this.Eleven();
                break;
            case 9:
                this.Twelve();
                break;
            case 10:
                this.Apologies();
                break;
        }
    }
} // end class