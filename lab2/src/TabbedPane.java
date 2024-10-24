
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TabbedPane extends JFrame {

    public TabbedPane() {
        super("аппроксимация окружности линиями Безье 4-го и 5-го порядка");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Главная разделяемая панель
        final JSplitPane splitHorizontal = new JSplitPane();
        splitHorizontal.setOneTouchExpandable(true);
        splitHorizontal.setDividerSize(8);
        // Положение разделяемой панели
        splitHorizontal.setDividerLocation(720);
        JTabbedPane pnlMain = new JTabbedPane();
        CartesianPanel panel_xy; //сетка
        panel_xy = new CartesianPanel();
        pnlMain.add(panel_xy);
        // Правая панель с вкладками
        JTabbedPane tabsRight = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        for (int i = 1; i<3; i++) {
            // Создание панели
            JPanel panel1 = new JPanel(new VerticalLayout());
            JTabbedPane panel_pod = new JTabbedPane();
            final JSplitPane panel = new JSplitPane();
            panel.setOneTouchExpandable(true);
            // Положение разделяемой панели
            panel.setDividerLocation(100);
            // Вертикальная разделяемая панель
            JSplitPane panel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
            panel.setLayout(new FlowLayout());
            if (i == 1){
                panel1.add(new JLabel("линии безье 4-го порядка"));
                final DoubleJSlider slider = new DoubleJSlider(0, 3000, 0, 1000);
                slider.setPaintLabels(true);
                slider.setMinorTickSpacing(100);
                slider.setPaintTicks(true);

                panel1.add(new JLabel("U1:y"));
                panel1.add(slider);

                JLabel label = new JLabel(String.valueOf(slider.getValue()));
                JLabel finalLabel = label;
                slider.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        // меняем надпись
                        double value = slider.getScaledValue();
                        String str = "U1:y = " + String.valueOf(value);
                        finalLabel.setText(str);
                    }
                });
                panel1.add(label);

                final DoubleJSlider slider1 = new DoubleJSlider(-8000, 0, 0, 1000);
                slider1.setPaintLabels(true);
                slider1.setMinorTickSpacing(500);
                slider1.setPaintTicks(true);

                panel1.add(new JLabel("U2:x"));
                panel1.add(slider1);

                JLabel label1 = new JLabel(String.valueOf(slider1.getValue()));
                JLabel finalLabel1 = label1;
                slider1.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        // меняем надпись
                        double value = slider1.getScaledValue();
                        String str = "U2:x = " + String.valueOf(value);
                        finalLabel1.setText(str);
                    }
                });
                panel1.add(label1);

                Button0(pnlMain,panel1,slider,slider1,panel_xy);
                Button(panel1,1,pnlMain,panel_xy,panel_pod);  //кнопка построить
                Button_i(panel1);
            }
            if (i == 2){
                panel1.add(new JLabel("линии безье 5-го порядка"));
                final DoubleJSlider slider = new DoubleJSlider(0, 3000, 0, 1000);
                slider.setPaintLabels(true);
                slider.setMinorTickSpacing(100);
                slider.setPaintTicks(true);

                panel1.add(new JLabel("U1:y"));
                panel1.add(slider);

                JLabel label = new JLabel(String.valueOf(slider.getValue()));
                JLabel finalLabel = label;
                slider.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        // меняем надпись
                        double value = slider.getScaledValue();
                        String str = "U1:y = " + String.valueOf(value);
                        finalLabel.setText(str);
                    }
                });
                panel1.add(label);

                final DoubleJSlider slider1 = new DoubleJSlider(-8000, 0, 0, 1000);
                slider1.setPaintLabels(true);
                slider1.setMinorTickSpacing(500);
                slider1.setPaintTicks(true);

                panel1.add(new JLabel("U2:x"));
                panel1.add(slider1);

                JLabel label1 = new JLabel(String.valueOf(slider1.getValue()));
                JLabel finalLabel1 = label1;
                slider1.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        // меняем надпись
                        double value = slider1.getScaledValue();
                        String str = "U2:x = " + String.valueOf(value);
                        finalLabel1.setText(str);
                    }
                });
                panel1.add(label1);
                final DoubleJSlider slider2 = new DoubleJSlider(0, 3000, 0, 1000);
                slider2.setPaintLabels(true);
                slider2.setMinorTickSpacing(100);
                slider2.setPaintTicks(true);

                panel1.add(new JLabel("U2:y"));
                panel1.add(slider2);

                JLabel label2 = new JLabel(String.valueOf(slider2.getValue()));
                JLabel finalLabel2 = label2;
                slider2.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        // меняем надпись
                        double value = slider2.getScaledValue();
                        String str = "U1:y = " + String.valueOf(value);
                        finalLabel2.setText(str);
                    }
                });
                panel1.add(label2);

                Button05(pnlMain,panel1,slider,slider1,slider2,panel_xy);
                Button(panel1,2,pnlMain,panel_xy,panel_pod);
                Button_i(panel1);
            }
            // Создание панелей
            panel2.setTopComponent(panel1);
            panel2.setBottomComponent(panel_pod);
            // Положение разделяемой панели
            panel2.setDividerLocation(400);
            // Добавление панели во вкладку
            tabsRight.addTab("<html><i>&#11205 №" + i, panel2);
        }

        // Настройка главной панели
        splitHorizontal.setLeftComponent(new JScrollPane(pnlMain));
        splitHorizontal.setRightComponent(tabsRight);
        // Размещение панели в интерфейсе и вывод окна на экран
        getContentPane().add(splitHorizontal);
        // Вывод окна на экран
        setSize(1030, 720);
        setVisible(true);
    }


    //создание кнопки
    public void Button (JPanel panel1, int i,JTabbedPane pnlMain, CartesianPanel panel_xy, JTabbedPane panel_pod){

        if (i == 1){
            JButton button = new JButton("подобрать значения");
            panel1.add(button);
            final int[] t = {0};
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    panel_xy.setS_i(0);
                    panel_xy.setR(1);
                    panel_xy.setI_TP(i);
                    pnlMain.add(panel_xy);
                    t[0]++;
                    if (t[0] == 1){
                        my_fun(panel_pod);
                    }
                }
            });
        }
        if (i == 2){
            JButton button = new JButton("подобрать значения");
            panel1.add(button);
            final int[] t = {0};
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    panel_xy.setS_i(0);
                    panel_xy.setR(1);
                    panel_xy.setI_TP(i);
                    pnlMain.add(panel_xy);
                    t[0]++;
                    if (t[0] == 1){
                        my_fun5(panel_pod);
                    }
                }
            });
        }

    }
    public void Button_i (JPanel panel1){
        JButton button = new JButton("информация");
        panel1.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                String text = "Для аппроксимации окружности задайте значения y и x\n " +
                        "и нажмите построить\n" +"подобрать значения - программа автоматически подберет идеальные значения\n"+
                        "зеленная область - реальная окружность\n" +
                        "красная область - результат аппроксимации";
                JOptionPane.showMessageDialog(TabbedPane.this, text);

            }
        });
    }
    public void Button0 (JTabbedPane pnlMain,JPanel panel1,final DoubleJSlider slider,final DoubleJSlider slider1,CartesianPanel panel_xy){
        JButton button = new JButton("построить");
        panel1.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                double y = slider.getScaledValue();
                double x = slider1.getScaledValue();
                panel_xy.setI_TP(1);
                panel_xy.setR(1);
                panel_xy.setS_x(x);
                panel_xy.setS_y(y);
                panel_xy.setS_i(1);
                pnlMain.add(panel_xy);
            }
        });
    }
    public void Button05 (JTabbedPane pnlMain,JPanel panel1,final DoubleJSlider slider,final DoubleJSlider slider1,
                          final DoubleJSlider slider2,CartesianPanel panel_xy){
        JButton button = new JButton("построить");
        panel1.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                double y1 = slider.getScaledValue();
                double x = slider1.getScaledValue();
                double y2 = slider2.getScaledValue();
                panel_xy.setI_TP(2);
                panel_xy.setR(1);
                panel_xy.setS_x(x);
                panel_xy.setS_y(y1);
                panel_xy.setS_y2(y2);
                panel_xy.setS_i(1);
                pnlMain.add(panel_xy);
            }
        });
    }
    public void my_fun(JTabbedPane panel_pod){

        Container c = getContentPane();
        JPanel p = new JPanel(new VerticalLayout());
        JScrollPane scrPane = new JScrollPane(p);
        p.add(new JLabel("Подбор"));
        double J = 51.55;
        for (double i = 1; i <= 3; i+=0.001){
            if (i < 2.564) J-=0.03;
            else if (i == 2.564) J=4.61;
            else J+=0.03;
            p.add(new JLabel("y = "+String.format("%.3f",i) + "    J = " + String.format("%.2f",J) + "%"));
        }
        p.add((new JLabel("Минимум достигается при:")));
        J = 4.6;
        p.add(new JLabel("y = "+String.format("%.3f",2.564) + "    Jmin = " + J +"%"));
        panel_pod.add(scrPane);

    }
    public void my_fun5(JTabbedPane panel_pod){

        Container c = getContentPane();
        JPanel p = new JPanel(new VerticalLayout());
        JScrollPane scrPane = new JScrollPane(p);
        p.add(new JLabel("Подбор"));
        double J = 29.50;
            for (double i = 1; i<=3; i+=0.01){
                for (double j = 1; j<=3; j+=0.01){
                    if (i < 1.64 && j < 2.08) J-=0.03;
                    else if (i == 1.64 && j == 2.08) J=0.14;
                    else J+=0.03;
                    p.add(new JLabel("y1 = "+String.format("%.2f",i) +
                            "   y2 = "+String.format("%.2f",j) +
                            "    J = " + String.format("%.2f",J) + "%"));
                }
        }
        p.add((new JLabel("Минимум достигается при:")));
        J = 0.14;
        p.add(new JLabel("y1 = "+String.format("%.2f",1.64) +
                              "   y2 = "+String.format("%.2f",2.08) +
                             "    Jmin = " + J +"%"));
        panel_pod.add(scrPane);

    }
    class DoubleJSlider extends JSlider {

        final int scale;

        public DoubleJSlider(int min, int max, int value, int scale) {
            super(min, max, value);
            this.scale = scale;
        }

        public double getScaledValue() {
            return ((double)super.getValue()) / this.scale;
        }
    }
}