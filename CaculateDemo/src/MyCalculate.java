import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

import javax.swing.*;

public class MyCalculate {
    private JFrame j_frame;
    private JPanel j_panel1, j_panel2;
    private JLabel j_memory;
    private JMenuBar j_menuBar;
    private JMenuItem pasteItem;
    private JMenuItem copyItem;
    private JMenuItem viewItem;
    private JMenuItem aboutItem;
    private JMenu j_viewMenu;
    private JMenu j_editMenu;
    private JMenu j_helpMenu;
    private JTextField j_textfield;
    private JButton[] j_buttons;
    private String str_basic = "0";
    private String flag = null;
    private double num1 = 0.0;
    private double num2 = 0.0;
    private double memory = 0.0;

    // 创建 MyCalculate 构造函数
    public MyCalculate() {
        newFrame();
        newBar();
        newButtons();
        addButtons();
        action();
    }

    private void newFrame() {
        // 初始化 JFrame、JPanel 和 JTextField
        j_frame = new JFrame("JAVA计算器");
        j_panel1 = new JPanel();
        j_panel2 = new JPanel();
        j_memory = new JLabel();
        j_memory.setPreferredSize(new Dimension(30, 15)); // 设置固定大小
        j_textfield = new JTextField(13);
    }

    private void newBar() {
        j_menuBar = new JMenuBar();
        j_viewMenu = new JMenu("查看(V)");
        j_editMenu = new JMenu("编辑(E)");
        j_helpMenu = new JMenu("帮助(H)");

        copyItem = new JMenuItem("复制");
        pasteItem = new JMenuItem("粘贴");

        viewItem = new JMenuItem("查看帮助");
        aboutItem = new JMenuItem("关于计算器");

        j_textfield.setEditable(true);
        j_textfield.setText("0");
        j_panel1.setLayout(new FlowLayout());
        j_panel1.add(j_memory);
        j_panel1.add(j_textfield);

        j_editMenu.add(copyItem);
        j_editMenu.add(pasteItem);

        j_helpMenu.add(viewItem);
        j_helpMenu.add(aboutItem);

        j_menuBar.add(j_viewMenu);
        j_menuBar.add(j_editMenu);
        j_menuBar.add(j_helpMenu);

        j_frame.setJMenuBar(j_menuBar);
    }

    private void newButtons() {
        // 为按钮数组赋值
        j_buttons = new JButton[30];
        // 逐个创建 JButton 对象，文本内容为对应位置的字符
        j_buttons[0] = new JButton("MC");
        j_buttons[1] = new JButton("MR");
        j_buttons[2] = new JButton("MS");
        j_buttons[3] = new JButton("M+");
        j_buttons[4] = new JButton("M-");
        j_buttons[5] = new JButton("<-");
        j_buttons[6] = new JButton("CE");
        j_buttons[7] = new JButton("C");
        j_buttons[8] = new JButton("±");
        j_buttons[9] = new JButton("√");
        j_buttons[10] = new JButton("7");
        j_buttons[11] = new JButton("8");
        j_buttons[12] = new JButton("9");
        j_buttons[13] = new JButton("/");
        j_buttons[14] = new JButton("%");
        j_buttons[15] = new JButton("4");
        j_buttons[16] = new JButton("5");
        j_buttons[17] = new JButton("6");
        j_buttons[18] = new JButton("*");
        j_buttons[19] = new JButton("1/x");
        j_buttons[20] = new JButton("1");
        j_buttons[21] = new JButton("2");
        j_buttons[22] = new JButton("3");
        j_buttons[23] = new JButton("-");
        j_buttons[24] = new JButton("=");
        j_buttons[25] = new JButton("0");
        j_buttons[26] = new JButton(".");
        j_buttons[27] = new JButton("+");

        j_buttons[28] = new JButton("(");
        j_buttons[29] = new JButton(")");

        // 设置按钮的字体大小
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        for (int i = 0; i < j_buttons.length; i++) {
            j_buttons[i].setFont(buttonFont); // 为每个按钮设置字体
        }
    }

    private void addButtons() {

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbs = new GridBagConstraints();
        j_panel2.setLayout(gbl);

        for (int i = 0; i < 30; i++) {
                j_panel2.add(j_buttons[i]);
        }

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 1;
        gbs.gridheight = 1;
        for (int i = 0; i < 4; i++) {
            gbs.gridx = i;
            gbs.gridy = 0;
            gbl.setConstraints(j_buttons[i], gbs);
        }


        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gbs.gridheight = 1;
        gbs.gridx = 4;
        gbs.gridy = 0;
        gbl.setConstraints(j_buttons[4], gbs);


        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 1;
        gbs.gridheight = 1;
        for (int i = 5; i < 9; i++) {
            gbs.gridx = i - 5;
            gbs.gridy = 1;
            gbl.setConstraints(j_buttons[i], gbs);
        }

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gbs.gridheight = 1;
        gbs.gridx = 4;
        gbs.gridy = 1;
        gbl.setConstraints(j_buttons[9], gbs);


        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 1;
        gbs.gridheight = 1;
        for (int i = 10; i < 14; i++) {
            gbs.gridx = i - 10;
            gbs.gridy = 2;
            gbl.setConstraints(j_buttons[i], gbs);
        }

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gbs.gridheight = 1;
        gbs.gridx = 4;
        gbs.gridy = 2;
        gbl.setConstraints(j_buttons[14], gbs);


        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 1;
        gbs.gridheight = 1;
        for (int i = 15; i < 19; i++) {
            gbs.gridx = i - 15;
            gbs.gridy = 3;
            gbl.setConstraints(j_buttons[i], gbs);
        }

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gbs.gridheight = 1;
        gbs.gridx = 4;
        gbs.gridy = 3;
        gbl.setConstraints(j_buttons[19], gbs);

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 1;
        gbs.gridheight = 1;
        for (int i = 20; i < 24; i++) {
            gbs.gridx = i - 20;
            gbs.gridy = 4;
            gbl.setConstraints(j_buttons[i], gbs);
        }

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = GridBagConstraints.REMAINDER;
        gbs.gridheight = 2;
        gbs.gridx = 4;
        gbs.gridy = 4;
        gbl.setConstraints(j_buttons[24], gbs);

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 2;
        gbs.gridheight = 1;
        gbs.gridx = 0;
        gbs.gridy = 5;
        gbl.setConstraints(j_buttons[25], gbs);

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 1;
        gbs.gridheight = 1;
        for (int i = 26; i < 27; i++) {
            gbs.gridx = i - 24;
            gbs.gridy = 5;
            gbl.setConstraints(j_buttons[i], gbs);
        }

        gbs.gridx = 3;
        gbs.gridy = 5;
        gbl.setConstraints(j_buttons[27], gbs);

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 3;
        gbs.gridheight = 1;
        gbs.gridx = 0;
        gbs.gridy = 6;
        gbl.setConstraints(j_buttons[28], gbs);

        gbs.weightx = 1.0;
        gbs.weighty = 1.0;
        gbs.fill = GridBagConstraints.BOTH;
        gbs.gridwidth = 3;
        gbs.gridheight = 1;
        gbs.gridx = 3;
        gbs.gridy = 6;
        gbl.setConstraints(j_buttons[29], gbs);

        j_frame.add(j_panel1, BorderLayout.NORTH);
        j_frame.add(j_panel2, BorderLayout.CENTER);
    }

    public void showMe() {
        j_frame.setVisible(true);
        j_frame.setSize(300, 350);
        j_frame.setLocation(200, 200);
        j_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFontAndColor();
    }

    private void setFontAndColor() {
        Color c1 = new Color(63, 178, 198);
        Color c2 = new Color(63, 178, 198);
        j_panel1.setBackground(Color.LIGHT_GRAY);
        j_panel2.setBackground(Color.white);

        Font f = new Font("Dialog", Font.BOLD, 16);
        j_textfield.setFont(f);
        for (int i = 0; i < j_buttons.length; i++) {
            j_buttons[i].setBackground(Color.WHITE);
        }
    }

    public void action() {
        ActionListener actionListener_buttons = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if (str.equals("+")) {
                    num2 = num1;
                    num1 = 0.0;
                    str_basic = "0";
                    flag = "+";
                    num1 = num2 + num1;
                } else if (str.equals("-")) {
                    num2 = num1;
                    num1 = 0.0;
                    str_basic = "0";
                    flag = "-";
                    num1 = num2 - num1;
                } else if (str.equals("*")) {
                    num2 = num1;
                    num1 = 0.0;
                    str_basic = "0";
                    flag = "*";
                    num1 = num2 * num1;
                } else if (str.equals("/")) {
                    num2 = num1;
                    str_basic = "0";
                    flag = "/";
                } else if (str.equals("%")) {
                    num2 = num1;
                    str_basic = "0";
                    flag = "%";
                } else if (str.equals(".")) {
                    str_basic = str_basic + str;
                } else if (str.equals("MC")) {
                    //  memory clear
                    memory = 0;
                    j_memory.setText(""); // 修改标签的文本内容
                } else if (str.equals("MR")) {
                    // memory read
                    num1 = memory;
                } else if (str.equals("MS")) {
                    // memory save
                    memory = num1;
                    // 修改标签的文本内容
                    j_memory.setText("M");
                } else if (str.equals("M+")) {
                    memory += num1;
                } else if (str.equals("M-")) {
                    memory -= num1;
                } else if (str.equals("C")) {
                    j_textfield.setText("0");
                    num1 = 0.0;
                    num2 = 0.0;
                    str_basic = "0";
                } else if (str.equals("CE")) {
                    num1 = 0.0;
                    str_basic = "0";
                } else if (str.equals("√")) {
                    num1 = Math.sqrt(num1);
                } else if (str.equals("<-")) {
                    //退格
                    str_basic = str_basic.substring(0, str_basic.length() - 1);
                    num1 = Double.parseDouble(str_basic);
                } else if (str.equals("±")) {
                    num1 = -(num1);

                } else if (str.equals("1/x")) {
                    num1 = 1.0 / num1;
                } else if (str.equals("=")) {

                    if (flag == null) {

                    } else if (flag.equals("+")) {
                        num1 = num2 + num1;
                    } else if (flag.equals("-")) {
                        num1 = num2 - num1;
                    } else if (flag.equals("*")) {
                        num1 = num2 * num1;
                    } else if (flag.equals("/")) {
                        num1 = num2 / num1;
                    } else if (flag.equals("%")) {
                        num1 = num2 % num1;
                    }
                    flag = null;
                    str_basic = "0";
                } else {
                    //处理输入数字时
                    str_basic = str_basic + str;
                    num1 = Double.parseDouble(str_basic);
                }
                //无论是哪个按钮，最终始终设置为s1
                j_textfield.setText(num1 + "");
            }
        };

        for (int i = 0; i < j_buttons.length; i++) {
            j_buttons[i].addActionListener(actionListener_buttons);
        }

        ActionListener actionListener_paste = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取系统剪贴板
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                // 从剪贴板中获取Transferable对象
                Transferable transferable = clipboard.getContents(null);

                // 检查Transferable对象中是否包含文本
                if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        // 从Transferable对象中获取文本并将其粘贴到应用程序中
                        String text = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                        num1 = Double.parseDouble(text);
                        j_textfield.setText(num1 + "");
                    } catch (UnsupportedFlavorException | IOException ee) {
                        ee.printStackTrace();
                    }
                }
            }
        };

        pasteItem.addActionListener(actionListener_paste);

        ActionListener actionListener_copy = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textToCopy = Double.toString(num1);

                // 创建StringSelection对象，用于保存要复制的文本
                StringSelection stringSelection = new StringSelection(textToCopy);

                // 获取系统剪贴板
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                // 将StringSelection对象放入剪贴板
                clipboard.setContents(stringSelection, null);
            }
        };

        copyItem.addActionListener(actionListener_copy);


        ActionListener actionListener_about = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示普通消息框
                String message = "Author:  Youjunchao\nSno:       222200231\nversion: 1.0";
                JOptionPane.showMessageDialog(j_frame, message, "关于计算器", JOptionPane.PLAIN_MESSAGE);
            }
        };

        aboutItem.addActionListener(actionListener_about);


        JLabel hyperlink = new JLabel("<html><u>点击这里查看官方教程</u></html>");
        hyperlink.setForeground(Color.BLUE);
        hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://docs.oracle.com/javase/tutorial/uiswing/"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ActionListener actionListener_view = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(j_frame, hyperlink, "查看帮助", JOptionPane.PLAIN_MESSAGE);
            }
        };
        viewItem.addActionListener(actionListener_view);
    }
}