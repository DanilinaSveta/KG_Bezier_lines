import java.awt.*;
import javax.swing.*;
//система координат

class CartesianPanel extends JPanel {
    private int R, i_TP;
    private int s_i;
    private double s_x, s_y, s_y2;

    public void setR(int R){
        this.R = R;
    }
    public int getR(){
        return R;
    }

    public void setI_TP(int i_TP) {
        this.i_TP = i_TP;
    }
    public void setS_x(double s_x){this.s_x = s_x;}
    public double getS_x(){return s_x;}
    public void setS_y(double s_y){this.s_y = s_y;}
    public double getS_y(){return s_y;}
    public void setS_i(int s_i){this.s_i = s_i;}
    public int getS_i(){
        return s_i;
    }
    public void setS_y2(double s_y2){this.s_y2 = s_y2;}
    public double getS_y2(){return s_y2;}

    // x-axis coord constants
    public static final int X_AXIS_FIRST_X_COORD = 543; //
    public static final int X_AXIS_SECOND_X_COORD = 710;
    public static final int X_AXIS_Y_COORD = 600 - 250;

    // y-axis coord constants
    public static final int Y_AXIS_FIRST_Y_COORD = 50;
    public static final int Y_AXIS_SECOND_Y_COORD = 600 - 250; //
    public static final int Y_AXIS_X_COORD = 543; //

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

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // x-axis
        g2.drawLine(X_AXIS_FIRST_X_COORD - 500, X_AXIS_Y_COORD ,
                X_AXIS_SECOND_X_COORD, X_AXIS_Y_COORD);
        // y-axis
        g2.drawLine(Y_AXIS_X_COORD , Y_AXIS_FIRST_Y_COORD,
                Y_AXIS_X_COORD , Y_AXIS_SECOND_Y_COORD + 300);
        g2.setColor(Color.gray);
        Stroke dashed = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2.setStroke(dashed);
        for (int i = 50; i < 640; i +=50){   // сетка параллельно x
            g2.drawLine(X_AXIS_FIRST_X_COORD - 500, i,
                    X_AXIS_SECOND_X_COORD, i);
        }
        for (int i = 43; i < 700; i +=50){   // сетка параллельно y
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
        int xCoordNumbers = 4;
        int yCoordNumbers = 6;
        int xLength = (X_AXIS_SECOND_X_COORD +35- X_AXIS_FIRST_X_COORD)
                / xCoordNumbers;
        int yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD)
                / yCoordNumbers;

        // draw x-axis numbers
        for(int i = -9; i < xCoordNumbers; i++) {
            g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
                    X_AXIS_Y_COORD - SECOND_LENGHT,
                    X_AXIS_FIRST_X_COORD + (i * xLength),
                    X_AXIS_Y_COORD + SECOND_LENGHT);
            g2.drawString(Integer.toString(i),
                    X_AXIS_FIRST_X_COORD + (i * xLength) - 3,
                    X_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        }

        //draw y-axis numbers
        for(int i = -5; i < yCoordNumbers; i++) {
            g2.drawLine(Y_AXIS_X_COORD - SECOND_LENGHT,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength),
                    Y_AXIS_X_COORD + SECOND_LENGHT,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength));
            g2.drawString(Integer.toString(i),
                    Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength));
        }
        if (i_TP == 1){
            Bezier4 bezier4 = new Bezier4();
            bezier4.setR(getR());
            bezier4.setS_i(getS_i());
            bezier4.setS_y(getS_y());
            bezier4.setS_x(getS_x());
            bezier4.paintComponent(g);
        }
        if (i_TP == 2){
            Bezier5 bezier5 = new Bezier5();
            bezier5.setR(getR());
            bezier5.setS_i(getS_i());
            bezier5.setS_y(getS_y());
            bezier5.setS_x(getS_x());
            bezier5.setS_y2(getS_y2());

            bezier5.paintComponent(g);
        }
    }
}