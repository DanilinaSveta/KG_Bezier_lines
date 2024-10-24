import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bezier5 extends JPanel {
    private static double t = 0.001;
    double u1_x, u1_y, u2_x, u2_y, u3_x, u3_y, u4_x, u4_y, u0_x, u0_y;
    double x1 = -2.2, y1 = 1.64, y2 = 2.08;
    private int R, s_i;
    private double s_x, s_y, s_y2;
    int x = 543, y = 350;
    public void setR(int R){
        this.R = R;
    }
    public void setS_x(double s_x){this.s_x = s_x;}
    public void setS_y(double s_y){this.s_y = s_y;}
    public void setS_i(int s_i){this.s_i = s_i;}
    public void setS_y2(double s_y2){this.s_y2 = s_y2;}

    public void paintComponent(Graphics g) {
        R = R*50;
        if (s_i == 0){
            u1_y = (y1*R) + y;
            u2_x = (x1 * R) + x;
            u2_y = (y2*R) + y;
            u3_x = (x1 * R ) + x;
            u3_y = (y2*R *(-1)) + y;
            u4_y = (y1*R *(-1)) + y;
        }
        if (s_i == 1){
            u1_y = (s_y*R) + y;
            u2_x = (s_x * R) + x;
            u2_y = (s_y2*R) + y;
            u3_x = (s_x * R ) + x;
            u3_y = (s_y2*R *(-1)) + y;
            u4_y = (s_y*R *(-1)) + y;
        }
        u0_x = R + x;
        u0_y = y;
        u1_x = u0_x;
        u4_x = u0_x;


        if (R > 0){
            Graphics2D g2d = (Graphics2D) g;

            float[] dashingPattern2 = {10f, 4f};
            Stroke stroke2 = new BasicStroke(4f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 1.0f, dashingPattern2, 0.0f);

            g2d.setColor(Color.green);
            Font font  = new Font("Tahoma",Font.BOLD|Font.ITALIC, 30);
            g2d.setFont(font);
            g2d.setStroke(stroke2);
            g2d.drawOval(x -R, y-R, R*2,R*2);
            Color c = new Color(0,255,0,50);
            g2d.setColor(c);
            g2d.fill(new Ellipse2D.Double(x -R, y-R, R*2,R*2));

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(new BasicStroke(3f));

            g2d.setColor(Color.BLUE);

            g2d.drawLine((int) u0_x, (int) u0_y, (int) u1_x, (int) u1_y);
            g2d.drawLine((int) u1_x, (int) u1_y, (int) u2_x, (int) u2_y);
            g2d.drawLine((int) u2_x, (int) u2_y, (int) u3_x, (int) u3_y);
            g2d.drawLine((int) u3_x, (int) u3_y, (int) u4_x, (int) u4_y);
            g2d.drawLine((int) u0_x, (int) u0_y, (int) u4_x, (int) u4_y); // красить прямую линию между ключевыми точками

            double x, y;
            for (double k = t; k <= 1 + t; k += t) {
                g2d.setStroke(new BasicStroke(1f));
                g2d.setColor(Color.RED);
                double r = 1 - k;
                x = Math.pow(r, 5) * u0_x + 5 * k * Math.pow(r, 4) * u1_x
                        + 10 * Math.pow(k,2) * Math.pow(r,3) *u2_x
                        + 10 * Math.pow(k, 3) * Math.pow(r,2) * u3_x
                        + 5 * Math.pow(k, 4) * (1 - k) * u4_x+ Math.pow(k, 5) *  u0_x;
                y = Math.pow(r, 5) * u0_y + 5 * k * Math.pow(r, 4) * u1_y
                        + 10 * Math.pow(k,2) * Math.pow(r,3) *u2_y
                        + 10 * Math.pow(k, 3) * Math.pow(r,2) * u3_y
                        + 5 * Math.pow(k, 4) * (1 - k) * u4_y+ Math.pow(k, 5) * u0_y;
                g2d.drawOval((int) x, (int) y, 1, 1);

                g2d.setColor(Color.BLUE);
                font  = new Font("Tahoma",Font.ITALIC, 20);
                g2d.setFont(font);

                g2d.setStroke(new BasicStroke(3f));
                g2d.setColor(Color.BLACK);
                g2d.drawString("U0", (int)u0_x + 10 , (int) u0_y);
                g2d.drawOval((int)u0_x -3 , (int) u0_y-3,5,5);
                g2d.drawString("U5", (int)u0_x +10, (int) u0_y+30);
                g2d.drawString("U1", (int)u1_x , (int)u1_y );
                g2d.drawOval((int)u1_x-3 , (int)u1_y-3 ,5,5);
                g2d.drawString("U2", (int)u2_x -30 , (int)u2_y);
                g2d.drawOval((int)u2_x-3 , (int)u2_y-3,5,5);
                g2d.drawString("U3", (int)u3_x  , (int)u3_y);
                g2d.drawOval((int)u3_x  -3, (int)u3_y-3,5,5);
                g2d.drawString("U4", (int)u4_x  , (int)u4_y);
                g2d.drawOval((int)u4_x  -3, (int)u4_y-3,5,5);

            }

        }

    }

}
