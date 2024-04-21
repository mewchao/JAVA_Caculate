import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.Stack;

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
    private Double memory = 0.0;
    String expression = "";
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
        j_panel1.setBackground(Color.LIGHT_GRAY);
        j_panel2.setBackground(Color.white);

        Font f = new Font("Dialog", Font.BOLD, 16);
        j_textfield.setFont(f);
        for (int i = 0; i < j_buttons.length; i++) {
            j_buttons[i].setBackground(Color.WHITE);
        }
    }

    private static final String OPERATORS = "+-*/%";

    public static double evaluateExpression(String expression) {
        Stack<String> operatorStack = new Stack<>();
        Stack<Double> operandStack = new Stack<>();

        expression = expression.replaceAll("\\s", "");
        String[] tokens = expression.split("(?=[-+*/()%])|(?<=[-+*/()%])");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (OPERATORS.contains(token)) {
                if (token.equals("-") && (i == 0 || tokens[i - 1].equals("("))) {
                    // 当"-"作为负号使用时
                    operandStack.push(0.0);
                }
                while (!operatorStack.isEmpty() && getPrecedence(token) <= getPrecedence(operatorStack.peek())) {
                    String operator = operatorStack.pop();
                    double operand2 = operandStack.pop();
                    double operand1 = operandStack.pop();
                    operandStack.push(calculate(operand1, operand2, operator));
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    String operator = operatorStack.pop();
                    double operand2 = operandStack.pop();
                    double operand1 = operandStack.pop();
                    operandStack.push(calculate(operand1, operand2, operator));
                }
                operatorStack.pop(); // Discard "("
            } else {
                operandStack.push(Double.parseDouble(token));
            }
        }

        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            double operand2 = operandStack.pop();
            double operand1 = operandStack.pop();
            operandStack.push(calculate(operand1, operand2, operator));
        }

        return operandStack.pop();
    }

    private static int getPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/") || operator.equals("%")) {  // 添加了 "%"
            return 2;
        } else {
            return 0;
        }
    }

    private static double calculate(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
            case "%":  // 添加了 "%"
                if (operand2 != 0) {
                    return operand1 % operand2;
                } else {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
            default:
                return 0;
        }
    }

    public void action() {
        ActionListener actionListener_buttons = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isError = false;
                String str = e.getActionCommand();
                if (expression == "Error") {
                    expression = "";
                }
                if (str.equals("+")) {
                    expression += str;
                } else if (str.equals("-")) {
                    expression += str;
                } else if (str.equals("*")) {
                    expression += str;
                } else if (str.equals("/")) {
                    expression += str;
                } else if (str.equals("(")) {
                    expression += str;
                } else if (str.equals(")")) {
                    expression += str;
                } else if (str.equals("%")) {
                    expression += str;
                } else if (str.equals(".")) {
                    expression += str;
                } else if (str.equals("MC")) {
                    memory = 0.0;
                    j_memory.setText(""); // 修改标签的文本内容
                } else if (str.equals("MR")) {
                    expression = Double.toString(memory);
                } else if (str.equals("MS")) {
                    double temp = 0.0;
                    try {
                        memory = evaluateExpression(expression);
                    } catch (IllegalArgumentException ex) {
                        isError = true;
                    }
                    j_memory.setText("M");
                } else if (str.equals("M+")) {
                    double temp = 0.0;
                    try {
                        temp = evaluateExpression(expression);
                    } catch (IllegalArgumentException ex) {
                        isError = true;
                    }
                    memory += temp;
                } else if (str.equals("M-")) {
                    double temp = 0.0;
                    try {
                        temp = evaluateExpression(expression);
                    } catch (IllegalArgumentException ex) {
                        isError = true;
                    }
                    memory -= temp;
                } else if (str.equals("C")) {
                    expression = "";
                    j_textfield.setText("0");
                    return;
                } else if (str.equals("CE")) {
                    expression = "";
                    j_textfield.setText("0");
                    return;
                } else if (str.equals("√")) {
                    double temp = 0.0;
                    try {
                        temp = evaluateExpression(expression);
                    } catch (IllegalArgumentException ex) {
                        isError = true;
                    }
                    temp = Math.sqrt(temp);
                    expression = Double.toString(temp);
                } else if (str.equals("<-")) {
                    expression = expression.substring(0, expression.length() - 1);
                } else if (str.equals("±")) {
                    double temp = 0.0;
                    try {
                        temp = evaluateExpression(expression);
                    } catch (IllegalArgumentException ex) {
                        isError = true;
                    }
                    temp = -temp;
                    expression = Double.toString(temp);
                } else if (str.equals("1/x")) {
                    double temp = 0.0;
                    try {
                        temp = evaluateExpression(expression);
                    } catch (IllegalArgumentException ex) {
                        j_textfield.setText("Error");
                    }
                    if (temp != 0) {
                        temp = 1.0 / temp;
                        expression = Double.toString(temp);
                    } else {
                        j_textfield.setText("Error");
                    }
                } else if (str.equals("=")) {
                    try {
                        expression = Double.toString(evaluateExpression(expression));
                    } catch (IllegalArgumentException ex) {
                        isError = true;
                    }
                } else {
                    expression += str;
                }
                //无论是哪个按钮，最终始终设置为expression
                if (isError) {
                    j_textfield.setText("Error");
                } else {
                    j_textfield.setText(expression);
                }
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
                        expression = text;
                        j_textfield.setText(text);
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
                String textToCopy = expression;

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