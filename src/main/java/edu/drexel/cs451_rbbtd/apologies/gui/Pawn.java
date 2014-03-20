package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;

public class Pawn {

    private int baseX;
    private int baseY;
    private int x;
    private int y;
    private Image image;
    private int space = -1; // Records how many spaces forward from start location the pawn has traveled
    private int positions[][];
    private PlayerColor color;
    public String errorMessage;


    public Pawn(int x, int y, int[][] positions, String path, PlayerColor color) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        this.x = x;
        this.baseX = x;
        this.y = y;
        this.baseY = y;
        this.positions = positions;
        this.color = color;
    }

    public PlayerColor getColor() {
        return this.color;
    }

    public void moveForward(int number) {
        int temp = space; // save initial value in case cannot move back farther
        space +=number;
        try{
            x = positions[space][0];
            y = positions[space][1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            this.x = baseX;
            this.y = baseY;
            space = temp;
        }
    }

    public void moveBack(int number) {
        int temp = space; // save initial value in case cannot move back farther
        space -=number;
        try{
            x = positions[space][0];
            y = positions[space][1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            this.x = baseX;
            this.y = baseY;
            space = temp;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpace(){
        return space;
    }

    public void setSpace(int number){
        this.space = number;
    }

    public int getIndex(int x, int y){ // gets the index of an x and y pair
        int index = 0;
        for (int i = 0; i < positions.length; i++){
            if (positions[i][0] == x && positions[i][1] == y)
            {
                index = i;
            }

        }

        return index;
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

    // Move to space function for card swapping
    public void moveTo(int space){
        this.space = space;
        x = positions[space][0];
        y = positions[space][1];
    }

    // Specific card functions
    public void One(int choice){

        // choice 1 start a pawn
        if (choice == 1)
            if (this.space == -1) // if pawn is in home
                this.moveForward(1);
            else
                this.errorMessage = "Must select a pawn in base";

        // choice 2
        if (choice == 2)
            this.moveForward(1);
    }

    public void Two(int choice){

        // choice 1 start a pawn
        if (choice == 1){
            if (this.space == -1) // if pawn is in home
                this.moveForward(1);
            else
                this.errorMessage = "Must select a pawn in base";
        // choice 2

        if (choice == 2){
            this.moveForward(2);
            this.errorMessage = null;
        }

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

    public void Eleven(int choice){

        // choice 1
        if (choice == 1){
              this.moveForward(11);
        }
        // choice 2
        if (choice == 2){
        }
    }

    public void Twelve(){
        this.moveForward(12);
    }

    public void Apologies(){ // unimplemented for now
    }

    // Calls appropriate card function based on Card Number passed to it
    public void Move(int cardNum, int optSelected){
        if (optSelected == 3) cardNum = 99;
        switch (cardNum){
            case 0:
                this.One(optSelected);
                break;
            case 1:
                this.Two(optSelected);
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
                this.Eleven(optSelected);
                break;
            case 9:
                this.Twelve();
                break;
            case 10:
                this.Apologies();
                break;
            case 99:
                break;
        }
    }
} // end class