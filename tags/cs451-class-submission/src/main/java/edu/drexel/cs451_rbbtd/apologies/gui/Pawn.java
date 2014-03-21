package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Pawn {

    private int baseX;
    private int baseY;
    private int homeX;
    private int homeY;
    public int isHome = 0;
    private int x;
    private int y;
    private Image image;
    protected int space = -1; // Records how many spaces forward from start location the pawn has traveled
    private int positions[][];
    private PlayerColor color;
    public String errorMessage;
    private int cardNumber;


    public Pawn(int x, int y, int[][] positions, String path, PlayerColor color) {
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        this.x = x;
        this.baseX = x;
        this.y = y;
        this.baseY = y;
        this.positions = positions;
        this.color = color;

        switch (color) {
            case RED:
                this.homeX = this.x + 80;
                this.homeY = this.y - 140;
                break;
            case GREEN:
                this.homeX = this.x - 140;
                this.homeY = this.y - 80;
                break;
            case BLUE:
                this.homeX = this.x + 140;
                this.homeY = this.y + 80;
                break;
            case YELLOW:
                this.homeX = this.x - 80;
                this.homeY = this.y + 140;
                break;
        }
    }

    public PlayerColor getColor() {
        return this.color;
    }

    public void moveForward(int number) {
        int temp = space; // save initial value in case cannot move back farther

        space +=number;
        try{
            int x_pos = positions[space][0];
            int y_pos = positions[space][1];
            handleBumps(x_pos, y_pos);
            boolean slid = handleSlides(x_pos, y_pos);
            if (slid) { x_pos += 3; y_pos += 3; }
            x = x_pos;
            y = y_pos;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            this.x = homeX;
            this.y = homeY;
            space = temp;
            this.isHome = 1;
        }
    }

    public void moveBack(int number) {
        int temp = space; // save initial value in case cannot move back farther
        space -=number;
        try{
            int x_pos = positions[space][0];
            int y_pos = positions[space][1];
            handleBumps(x_pos, y_pos);
            boolean slid = handleSlides(x_pos, y_pos);
            if (slid) { x_pos += 3; y_pos += 3; }
            x = x_pos;
            y = y_pos;
        } catch (ArrayIndexOutOfBoundsException e) {
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

    public int getSpace() {
        return space;
    }

    public void setSpace(int number) {
        this.space = number;
    }

    public int getIndex(int x, int y) { // gets the index of an x and y pair
        int index = 0;
        for (int i = 0; i < positions.length; i++) {
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
    public void moveTo(int space) {
        this.space = space;
        x = positions[space][0];
        y = positions[space][1];
    }

    // Specific card functions
    public void One(int choice) {

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

    public void Two(int choice) {

        // choice 1 start a pawn
        if (choice == 1) {
            if (this.space == -1) // if pawn is in home
                this.moveForward(1);
            else
                this.errorMessage = "Must select a pawn in base";
        // choice 2

        if (choice == 2) {
            this.moveForward(2);
            this.errorMessage = null;
        }

        }
    }

    public void Three() {
         this.moveForward(3);
    }

    public void Four() {
            this.moveBack(4);
    }

    public void Five() {
            this.moveForward(5);
    }

    public void Seven() { // Unimplemented for now
            this.moveForward(7);
    }

    public void Eight() {
            this.moveForward(8);
    }

    public void Ten(int choice) {

        // choice 1
        if (choice == 1)
            this.moveForward(10);

        // choice 2
        if (choice == 2);
            this.moveBack(1);
    }

    public void Eleven(int choice) {

        // choice 1
        if (choice == 1) {
              this.moveForward(11);
        }
        // choice 2
        if (choice == 2) {
        }
    }

    public void Twelve() {
        this.moveForward(12);
    }

    public void Apologies() { // unimplemented for now
    }

    // Calls appropriate card function based on Card Number passed to it
    public void Move(int cardNum, int optSelected) {
        cardNumber = cardNum;
        // If skipping a turn or stuck at start, do not move.
        if (optSelected == 3) cardNum = 99;
        if (pawnInBase(optSelected)) cardNum = 99;
        switch (cardNum) {
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
                this.Ten(optSelected);
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

    public boolean pawnInBase(int optSelected)
    {
        if (space != -1 || ((cardNumber == 0 || cardNumber == 1 || cardNumber == 10) && optSelected == 1)) return false;
        else return true;
    }

    public void handleBumps(int x_pos, int y_pos)
    {
        List<Pawn> pawns = Board.getPawns();
        for (Pawn pawn: pawns) {
            if (pawn.getX() == x_pos && pawn.getY() == y_pos) {
                pawn.x = pawn.baseX;
                pawn.y = pawn.baseY;
                pawn.space = -1;
            }
        }
    }

    public boolean handleSlides(int x_pos, int y_pos)
    {
        int space_temp = space + 1;
        int x_temp;
        int y_temp;
        if ((x_pos == positions[53][0] && y_pos == positions[53][1])
            || (x_pos == positions[11][0] && y_pos == positions[11][1])
            || (x_pos == positions[19][0] && y_pos == positions[19][1])
            || (x_pos == positions[25][0] && y_pos == positions[25][1])
            || (x_pos == positions[33][0] && y_pos == positions[33][1])
            || (x_pos == positions[39][0] && y_pos == positions[39][1])
            || (x_pos == positions[47][0] && y_pos == positions[47][1])) {
            for (int i = 0; i < 3; i++) {
                x_temp = positions[space_temp][0];
                y_temp = positions[space_temp][1];
                for (Pawn pawn: Board.getPawns()) {
                    if (pawn.getX() == x_temp && pawn.getY() == y_temp) {
                        pawn.x = pawn.baseX;
                        pawn.y = pawn.baseY;
                        pawn.space = -1; } }
                space_temp++; } return true; }
        return false;
    }
} // end class