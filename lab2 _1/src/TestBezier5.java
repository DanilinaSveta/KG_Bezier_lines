import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.*;

import static java.awt.Font.BOLD;

public class TestBezier5 extends JPanel {

    private static Point2D[] keyPointP;

    private static Ellipse2D.Double[] keyPointE;

    private static int keyPointNum;

    private static double t = 0.001;
    private static boolean flagShow = true;

    static class BezierPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private int keyPointID = -1;

        BezierPanel() {
            this.addMouseListener(new MouseAction());
            this.addMouseMotionListener(new MouseMotion());
        }

        protected void paintComponent(Graphics g) {
            // переписать перекраску
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(3f));
            g2.setColor(Color.BLUE);

            if (flagShow) {
                for (int i = 0; i < keyPointNum; i ++) {// Нарисуйте точку

                    g2.fill(keyPointE[i]); // Ключевые точки в середине

                    if (keyPointNum > 1 && i < (keyPointNum - 1)) {
                        g2.drawLine((int) keyPointP[i].getX(), (int) keyPointP[i].getY(), (int) keyPointP[i + 1].getX(),
                                (int) keyPointP [i + 1].getY ()); // красить прямую линию между ключевыми точками
                    }
                    if (i == 0) {
                        g2.drawString("B", (int) keyPointE[i].x, (int) keyPointE[i].y);
                    } else if (i == 1) {
                        g2.drawString("C", (int) keyPointE[i].x, (int) keyPointE[i].y);
                    } else if (i == 2) {;
                        g2.drawString("D", (int) keyPointE[i].x, (int) keyPointE[i].y);
                    } else if (i == 3) {;
                        g2.drawString("E", (int) keyPointE[i].x, (int) keyPointE[i].y);
                    }
                }
            }
            // 4 кривых Бесселя
            if (keyPointNum == 4) {
                double KPP_0X = ((keyPointP[0].getX() + keyPointP[3].getX())/2);
                double KPP_0Y = ((keyPointP[0].getY() + keyPointP[3].getY())/2);
                g2.draw(keyPointE[0]);
                double kPx = (keyPointE[0].x + keyPointE[3].x)/2;
                double kPy = (keyPointE[0].y + keyPointE[3].y)/2;
                g2.drawString("A  F", (int) kPx, (int) kPy);
                g2.drawLine((int) KPP_0X, (int) KPP_0Y, (int) keyPointP[0].getX(),
                        (int) keyPointP [0].getY ()); // красить прямую линию между ключевыми точками
                g2.drawLine((int) KPP_0X, (int) KPP_0Y, (int) keyPointP[3].getX(),
                        (int) keyPointP [3].getY ()); // красить прямую линию между ключевыми точками
                double x, y;
                g2.setColor(Color.RED);
                for (double k = t; k <= 1 + t; k += t) {
                    double r = 1 - k;
                    x = Math.pow(r, 5) * KPP_0X + 5 * k * Math.pow(r, 4) * keyPointP[0].getX()
                            + 10 * Math.pow(k,2) * Math.pow(r,3) *keyPointP[1].getX()
                            + 10 * Math.pow(k, 3) * Math.pow(r,2) * keyPointP[2].getX()
                            + 5 * Math.pow(k, 4) * (1 - k) * keyPointP[3].getX()+ Math.pow(k, 5) * KPP_0X;
                    y = Math.pow(r, 5) * KPP_0Y + 5 * k * Math.pow(r, 4) * keyPointP[0].getY()
                            + 10 * Math.pow(k,2) * Math.pow(r,3) *keyPointP[1].getY()
                            + 10 * Math.pow(k, 3) * Math.pow(r,2) * keyPointP[2].getY()
                            + 5 * Math.pow(k, 4) * (1 - k) * keyPointP[3].getY()+ Math.pow(k, 5) * KPP_0Y;
                    g2.drawOval((int) x, (int) y, 1, 1);
                }
            }
            g2.setColor(Color.black);
            Font font = new Font ("Arial", BOLD,12);
            g2.setFont(font);
            g2.drawString("Линии Безье 5-го порядка",20, 20);
            g2.drawString("Для аппроксимации окружности расположите четыре точки на плоскости",20, 35);
            //система координат
            CartesianPanel cartesianPanel =  new CartesianPanel();
            cartesianPanel.paintComponent(g);
        }

        class MouseAction extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                flagShow = true;
                // Щелкните левую кнопку мыши
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (keyPointNum  < 4) {
                        double x = e.getX();
                        double y = e.getY();
                        keyPointP[keyPointNum] = new Point2D.Double(x, y);
                        keyPointE[keyPointNum] = new Ellipse2D.Double(x - 4, y - 4, 8, 8);
                        keyPointNum++;
                        repaint();
                    }
                } // Щелкните правой кнопкой мыши
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    flagShow = false; // скрыть силу синей помощи, но на самом деле не удаляет
                    repaint();
                    keyPointNum = 0;
                    keyPointP = new Point2D[5];
                    keyPointE = new Ellipse2D.Double[5];
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Нажмите мышь, чтобы определить, является ли она ключевой точкой.
                for (int i = 0; i < keyPointNum; i++) {
                    if (keyPointE[i].contains((Point2D) e.getPoint())) {
                        keyPointID = i;
                        break;
                    } else {
                        keyPointID = -1;
                    }
                }
            }
        }

        class MouseMotion extends MouseMotionAdapter {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Клавиша перетаскивания мыши
                if (keyPointID != -1) {
                    double x = e.getX();
                    double y = e.getY();
                    keyPointP[keyPointID] = new Point2D.Double(x, y);
                    keyPointE[keyPointID] = new Ellipse2D.Double(x - 4, y - 4, 8, 8);
                    repaint();
                }
            }
        }
    }

    public TestBezier5(JTabbedPane pnlMain) {
        ;
        keyPointNum = 0;
        keyPointP = new Point2D[5];
        keyPointE = new Ellipse2D.Double[5];
        BezierPanel bezierPanel = new BezierPanel();
        bezierPanel.setPreferredSize(new Dimension(600, 600));
        bezierPanel.setBackground(Color.WHITE);

        pnlMain.add("Безье 5-го порядка",bezierPanel);


    }
}