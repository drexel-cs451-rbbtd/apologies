package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;

public class Pawn {

    private int x;
    private int y;
    private Image image;
    private int space = -1; // Records how many spaces forward from start location the pawn has traveled
    private int positions[][];
    private PlayerColor color;
    private String errorMessage;


    public Pawn(int x, int y, int[][] positions, String path, PlayerColor color) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        this.x = x;
        this.y = y;
        this.positions = positions;
        this.color = color;
    }

    public PlayerColor getColor() {
        return this.color;
    }

    public void moveForward(int number) {
        space +=number;
        x = positions[space][0];
        y = positions[space][1];
    }

    public void moveBack(int number) {
        int temp = space; // save initial value in case cannot move back farther
        space -=number;
        try{
            x = positions[space][0];
            y = positions[space][1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            this.errorMessage = "Cannot move back any further.";
            space = temp;
        }
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void resetErrorMessage() {
         this.errorMessage = null;
    }

    //////////////////////////////////// CARD FUNCTIONS//////////////////////////////////////

    // All card functions only one function for now
    public void One(){
        int choice = 2;

        // choice 1
        if (choice == 1)
        this.moveForward(1);
        this.errorMessage = null;

        // choice 2 start a pawn
        if (choice == 2)
            if (this.space == -1) // if pawn is in home
                this.moveForward(1);
            else
                this.errorMessage = "Must select a pawn in base";
    }

    public void Two(){
        int choice = 2;
        // choice 1
        if (choice == 1){
            this.moveForward(2);
            this.errorMessage = null;
        }

        // choice 2 start a pawn
        if (choice == 2){
            if (this.space == -1) // if pawn is in home
                this.moveForward(1);
            else
                this.errorMessage = "Must select a pawn in base";

        }
    }

    public void Three(){
         this.moveForward(3);
    }

    public void Four(){
            this.moveBack(4);
    }

    public void Five(){
            this.moveForward(5);
    }

    public void Seven(){ // Unimplemented for now
            this.moveForward(7);
    }

    public void Eight(){
            this.moveForward(8);
    }

    public void Ten(){
        int choice = 2;

        // choice 1
        if (choice == 1)
            this.moveForward(10);

        // choice 2
        if (choice == 2);
            this.moveBack(1);
    }

    public void Eleven(){
        int choice = 2;  // unimplemented second function swap

        // choice 1
        if (choice == 1){
              this.moveForward(11);
        }
        // choice 2
        if (choice == 2);
        this.moveForward(1);
    }

    public void Twelve(){
        int choice = 2;

        // choice 1
        if (choice == 1){
                this.moveForward(12);
        }

        // choice 2
        if (choice == 2);
        this.moveForward(1);
    }

    public void Apologies(){ // unimplemented for now
        int choice = 2;
        if (choice == 1)
           this.moveForward(1);

        // choice 2
        if (choice == 2);
           this.moveForward(1);
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