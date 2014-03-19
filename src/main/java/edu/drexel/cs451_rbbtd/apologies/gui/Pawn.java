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

    public String getErrorMessage() {
        return errorMessage;
    }


    //////////////////////////////////// CARD FUNCTIONS//////////////////////////////////////

    // All card functions only one function for now
    public void One(){
        int choice = 2;

        // choice 1
        if (choice == 1)
        this.moveForward();

        // choice 2 start a pawn
        if (choice == 2)
            if (this.space == -1) // if pawn is in home
                this.moveForward();
            else
                this.errorMessage = "Must select a pawn in base";
    }

    public void Two(){
        int choice = 2;
        // choice 1
        if (choice == 1){
            this.moveForward();
            this.moveForward();
        }

        // choice 2 start a pawn
        if (choice == 2){
            if (this.space == -1) // if pawn is in home
                this.moveForward();
            else
                this.errorMessage = "Must select a pawn in base";

        }
    }

    public void Three(){
        int choice = 2;

        // choice 1
        if (choice == 1){
            this.moveForward();
            this.moveForward();
            this.moveForward();
        }
        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Four(){
        int choice = 2;

        // choice 1
        if (choice == 1){
            this.moveBack();
            this.moveBack();
            this.moveBack();
            this.moveBack();
        }
        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Five(){
        int choice = 2;

        // choice 1
        if (choice == 1){
            for (int i = 0; i < 5; i++) {
                this.moveForward();
            }
        }

        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Seven(){ // Unimplemented for now
        int choice = 2;

        // choice 1
        if (choice == 1){
            for (int i = 0; i < 7; i++) {
                this.moveForward();
            }
        }

        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Eight(){
        int choice = 2;

        // choice 1
        if (choice == 1){
            for (int i = 0; i < 8; i++) {
                this.moveForward();
            }
        }

        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Ten(){
        int choice = 2;

        // choice 1
        if (choice == 1){
            for (int i = 0; i < 10; i++) {
                this.moveForward();
            }
        }

        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Eleven(){
        int choice = 2;  // unimplemented second function swap

        // choice 1
        if (choice == 1){
            for (int i = 0; i < 11; i++) {
              this.moveForward();
            }
        }
        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Twelve(){
        int choice = 2;

        // choice 1
        if (choice == 1){
            for (int i = 0; i < 12; i++) {
                this.moveForward();
            }
        }

        // choice 2
        if (choice == 2);
        this.moveForward();
    }

    public void Apologies(){ // unimplemented for now
        int choice = 2;
        if (choice == 1)
           this.moveForward();

        // choice 2
        if (choice == 2);
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