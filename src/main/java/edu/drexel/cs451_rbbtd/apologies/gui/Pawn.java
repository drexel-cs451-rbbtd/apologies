package edu.drexel.cs451_rbbtd.apologies.gui; /**
 * Created by Ben on 2/8/14.
 */

import javax.swing.*;
import java.awt.*;

public class Pawn{

    String pawn = "src\\main\\resources\\YellowPawn.png";

    private int x;
    private int y;
    private int t = 40; // tile size
    private Image image;
    private int space = -1; // Records how many spaces forward from start location the pawn has traveled


    // The coordinates for travelling around the board as yellow player
    public int positions[][] = {
            {t*4-15, t*0-10}, // first square out of home
            {t*5-15, t*0-10},
            {t*6-15, t*0-10},
            {t*7-15, t*0-10},
            {t*8-15, t*0-10},
            {t*9-15, t*0-10},
            {t*10-15, t*0-10},
            {t*11-15, t*0-10},
            {t*12-15, t*0-10},
            {t*13-15, t*0-10},
            {t*14-15, t*0-10},
            {t*14-15, t*1-10},
            {t*14-15, t*2-10},
            {t*14-15, t*3-10},
            {t*14-15, t*4-10},
            {t*14-15, t*5-10},
            {t*14-15, t*6-10},
            {t*14-15, t*7-10},
            {t*14-15, t*8-10},
            {t*14-15, t*9-10},
            {t*14-15, t*10-10},
            {t*14-15, t*11-10},
            {t*14-15, t*12-10},
            {t*14-15, t*13-10},
            {t*14-15, t*14-10},
            {t*13-15, t*14-10},
            {t*12-15, t*14-10},
            {t*11-15, t*14-10},
            {t*10-15, t*14-10},
            {t*9-15, t*14-10},
            {t*8-15, t*14-10},
            {t*7-15, t*14-10},
            {t*6-15, t*14-10},
            {t*5-15, t*14-10},
            {t*4-15, t*14-10},
            {t*3-15, t*14-10},
            {t*2-15, t*14-10},
            {t*1-15, t*14-10},
            {t*0-15, t*14-10},
            {t*0-15, t*13-10},
            {t*0-15, t*12-10},
            {t*0-15, t*11-10},
            {t*0-15, t*10-10},
            {t*0-15, t*9-10},
            {t*0-15, t*8-10},
            {t*0-15, t*7-10},
            {t*0-15, t*6-10},
            {t*0-15, t*5-10},
            {t*0-15, t*4-10},
            {t*0-15, t*3-10},
            {t*0-15, t*2-10},
            {t*0-15, t*1-10},
            {t*0-15, t*0-10},
            {t*1-15, t*0-10},
            {t*2-15, t*0-10},
            {t*2-15, t*1-10},
            {t*2-15, t*2-10},
            {t*2-15, t*3-10},
            {t*2-15, t*4-10},
            {t*2-15, t*6-10},


    };

    public Pawn(int x, int y) {
        ImageIcon ii = new ImageIcon(pawn);
        image = ii.getImage();
        this.x = x;
        this.y = y;

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

} // end class