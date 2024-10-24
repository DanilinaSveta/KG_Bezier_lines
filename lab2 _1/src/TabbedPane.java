
import javax.swing.*;
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
        splitHorizontal.setDividerLocation(650);
        JTabbedPane pnlMain = new JTabbedPane();

        // Правая панель с вкладками
        JTabbedPane tabsRight = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        // Создание вкладок
        for (int i = 1; i < 2; i++) {
            // Создание панели
            JPanel panel1 = new JPanel(new VerticalLayout());

            final JSplitPane panel = new JSplitPane();
            panel.setOneTouchExpandable(true);
            // Положение разделяемой панели
            panel.setDividerLocation(100);

            panel.setLayout(new FlowLayout());

            new TestBezier(pnlMain);
            new TestBezier5(pnlMain);
            Button(panel1,1);  //кнопка

            // Добавление панели во вкладку
            tabsRight.addTab("<html><i>&#11205", panel1);

        }
        // Настройка главной панели
        splitHorizontal.setLeftComponent(new JScrollPane(pnlMain));
        splitHorizontal.setRightComponent(tabsRight);
        // Размещение панели в интерфейсе и вывод окна на экран
        getContentPane().add(splitHorizontal);
        // Вывод окна на экран
        setSize(900, 700);
        setVisible(true);
    }
    //создание кнопки
    public void Button (JPanel panel1, int i){
        JButton button = new JButton("информация");
        panel1.add(button);
        if (i == 1){
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){

                    String text = "ПКМ - выбрать координаты точки\nЛКМ - очистить поле";
                    JOptionPane.showMessageDialog(TabbedPane.this, text);

                }
            });
        }

    }
}