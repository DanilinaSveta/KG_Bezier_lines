import java.awt.*;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import static java.awt.Font.BOLD;
//система координат

class CartesianPanel extends JPanel {


    // x-axis coord constants
    public static final int X_AXIS_FIRST_X_COORD = 50 + 250; //
    public static final int X_AXIS_SECOND_X_COORD = 600;
    public static final int X_AXIS_Y_COORD = 600 - 250;

    // y-axis coord constants
    public static final int Y_AXIS_FIRST_Y_COORD = 50;
    public static final int Y_AXIS_SECOND_Y_COORD = 600 - 250; //
    public static final int Y_AXIS_X_COORD = 50 + 250; //

    //arrows of axis are represented with "hipotenuse" of
    //triangle
    // now we are define length of cathetas of that triangle
    public static final int FIRST_LENGHT = 10;
    public static final int SECOND_LENGHT = 5;

    // size of start coordinate lenght
    public static final int ORIGIN_COORDINATE_LENGHT = 6;

    // distance of coordinate strings from axis
    public static final int AXIS_STRING_DISTANCE = 20;


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1f));
        Font font = new Font ("Arial",Font.TRUETYPE_FONT,10);
        g2.setFont(font);
        g2.setColor(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // x-axis
        g2.drawLine(X_AXIS_FIRST_X_COORD - 300, X_AXIS_Y_COORD ,
                X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        // y-axis
        g2.drawLine(Y_AXIS_X_COORD , Y_AXIS_FIRST_Y_COORD,
                Y_AXIS_X_COORD , Y_AXIS_SECOND_Y_COORD + 300);
        g2.setColor(Color.gray);
        Stroke dashed = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2.setStroke(dashed);
        for (int i = 53; i < 640; i +=27){   // сетка по x
            g2.drawLine(X_AXIS_FIRST_X_COORD - 260, i,
                    X_AXIS_SECOND_X_COORD, i);
        }
        for (int i = 30; i < 640; i +=27){   // сетка по y
            g2.drawLine(i, Y_AXIS_FIRST_Y_COORD,
                    i, Y_AXIS_SECOND_Y_COORD + 260);
        }
        g2.setColor(Color.black);
        // x-axis arrow
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
                X_AXIS_Y_COORD - SECOND_LENGHT,
                X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
                X_AXIS_Y_COORD + SECOND_LENGHT,
                X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);

        // y-axis arrow
        g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
                Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
                Y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
        g2.drawLine(Y_AXIS_X_COORD  + SECOND_LENGHT,
                Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
                Y_AXIS_X_COORD , Y_AXIS_FIRST_Y_COORD);

        // draw origin Point
        g2.fillOval(
                X_AXIS_FIRST_X_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
                Y_AXIS_SECOND_Y_COORD - (ORIGIN_COORDINATE_LENGHT / 2),
                ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);

        // draw text "X" and draw text "Y"
        g2.drawString("X", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
                X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        g2.drawString("Y", Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
                Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);

        // numerate axis
        int xCoordNumbers = 11;
        int yCoordNumbers = 11;
        int xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD)
                / xCoordNumbers;
        int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)
                / yCoordNumbers;

        // draw x-axis numbers
        for(int i = -10; i < xCoordNumbers; i++) {
            g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
                    X_AXIS_Y_COORD - SECOND_LENGHT,
                    X_AXIS_FIRST_X_COORD + (i * xLength),
                    X_AXIS_Y_COORD + SECOND_LENGHT);
            g2.drawString(Integer.toString(i),
                    X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
                    X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        }

        //draw y-axis numbers
        for(int i = -10; i < yCoordNumbers; i++) {
            g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength),
                    Y_AXIS_X_COORD + SECOND_LENGHT,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength));
            g2.drawString(Integer.toString(i),
                    Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength));
        }
    }
}