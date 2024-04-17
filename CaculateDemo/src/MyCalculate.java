import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

import javax.swing.*;

public class MyCalculate {
    private JFrame jf;
    private JPanel  jp1, jp2;

    private JLabel j_memory;
    private JMenuBar menuBar;
    private JMenu viewMenu;
    private JMenu editMenu;
    private JMenu helpMenu;

    private JTextField jtf;
    private JButton[] jbs;
    private String str0 = "0";
    private String flag = null;
    private double s1 = 0.0;
    private double s2 = 0.0;

    private double memory = 0.0;

    // 创建 MyCalculate 构造函数
    public MyCalculate() {
        // 初始化 JFrame、JPanel 和 JTextField
        jf = new JFrame("JAVA计算器");

        jp1 = new JPanel();
        jp2 = new JPanel();
        j_memory = new JLabel();

        j_memory.setPreferredSize(new Dimension(50, 20)); // 设置固定大小

         menuBar = new JMenuBar();
         viewMenu = new JMenu("查看(V)");
         editMenu = new JMenu("编辑(E)");
         helpMenu = new JMenu("帮助(H)");

        JMenuItem copyItem = new JMenuItem("复制");
        JMenuItem pasteItem = new JMenuItem("粘贴");

        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        menuBar.add(viewMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        jf.setJMenuBar(menuBar);

        jtf = new JTextField(25);

        // 为按钮数组赋值
        jbs = new JButton[30];

        // 逐个创建 JButton 对象，文本内容为对应位置的字符
        jbs[0] = new JButton("MC");
        jbs[1] = new JButton("MR");
        jbs[2] = new JButton("MS");
        jbs[3] = new JButton("M+");
        jbs[4] = new JButton("M-");
        jbs[5] = new JButton("⬅");
        jbs[6] = new JButton("CE");
        jbs[7] = new JButton("C");
        jbs[8] = new JButton("±");
        jbs[9] = new JButton("√");
        jbs[10] = new JButton("7");
        jbs[11] = new JButton("8");
        jbs[12] = new JButton("9");
        jbs[13] = new JButton("/");
        jbs[14] = new JButton("%");
        jbs[15] = new JButton("4");
        jbs[16] = new JButton("5");
        jbs[17] = new JButton("6");
        jbs[18] = new JButton("*");
        jbs[19] = new JButton("1/x");
        jbs[20] = new JButton("1");
        jbs[21] = new JButton("2");
        jbs[22] = new JButton("3");
        jbs[23] = new JButton("-");
        jbs[24] = new JButton("=");
        jbs[25] = new JButton("0");
        jbs[26] = new JButton("0");
        jbs[27] = new JButton(".");
        jbs[28] = new JButton("+");
        jbs[29] = new JButton("=");

        init();
        doit();
    }

    private void init() {
        jtf.setEditable(true);
        jtf.setText("0");
        jp1.setLayout(new FlowLayout());
        jp1.add(j_memory);
        jp1.add(jtf);

        jp2.setLayout(new GridLayout(6, 5));
        for (int i = 0; i < jbs.length; i++) {
            // 添加按钮
            jp2.add(jbs[i]);
        }
        Color c1 = new Color(63, 178, 198);
        Color c2 = new Color(63, 178, 198);
        jp1.setBackground(Color.LIGHT_GRAY);
        jp2.setBackground(Color.pink); // 设置背景颜色以区分

        jf.add(jp1, BorderLayout.NORTH);
        jf.add(jp2, BorderLayout.CENTER);
    }

    public void showMe() {
        jf.setVisible(true);
        jf.setSize(500, 500);
        jf.setLocation(200, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFontAndColor();
    }

    private void setFontAndColor() {
        Font f = new Font("Dialog", Font.BOLD, 16);
        jtf.setFont(f);
        for (int i = 0; i < jbs.length; i++) {
            jbs[i].setBackground(Color.WHITE);
        }
    }

    public void doit() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
//                System.out.println(str);
                if (str.equals("+")) {
                    s2=s1;
                    s1=0.0;
                    str0 = "0";
                    flag="+";
                    s1=s2+s1;
//                    System.out.print("s1:");
//                    System.out.println(s1);
                } else if (str.equals("-")) {
                    s2=s1;
                    s1=0.0;
                    str0 = "0";
                    flag="-";
                    s1=s2-s1;
                } else if (str.equals("*")) {
                    s2=s1;
                    s1=0.0;
                    str0 = "0";
                    flag="*";
                    s1=s2*s1;
                } else if (str.equals("/")) {
                    s2=s1;
//                    s1=0.0;
                    str0 = "0";
                    flag="/";
//                    s1=s2/s1;
                }else if (str.equals("%")) {
                    s2=s1;
                    str0 = "0";
                    flag="%";
                }else if (str.equals(".")) {
                    str0 = str0 + str;
                }else if (str.equals("MC")) {
//                    memory clear
                    memory=0;
                    j_memory.setText(""); // 修改标签的文本内容
                }else if (str.equals("MR")) {
//                    memory read
                    s1=memory;
                }else if (str.equals("MS")) {
//                    memory save
                    memory=s1;
                    j_memory.setText("M"); // 修改标签的文本内容
                }else if (str.equals("M+")) {
                    memory+=s1;
                }else if (str.equals("M-")) {
                    memory-=s1;
                }else if (str.equals("C")) {
                    jtf.setText("0");
                    s1=0.0;
                    s2=0.0;
                    str0="0";
                }else if (str.equals("CE")) {

                }else if (str.equals("√")) {

                }else if (str.equals("⬅")) {
                    //退格
                    str0 =  str0.substring(0, str0.length() - 1);
                    s1=Double.parseDouble(str0);
                }else if (str.equals("±")) {
                    s1=-(s1);

                }else if (str.equals("1/x")) {
                    s1=1.0/s1;
                }else if (str.equals("=")) {

                    if(flag.equals("+")){
                        s1=s2+s1;
                    }else if(flag.equals("-")){
                        s1=s2-s1;
                    }else if(flag.equals("*")){
                        s1=s2*s1;
                    }else if(flag.equals("/")){
                        s1=s2/s1;
                    }else if(flag.equals("%")){
                        s1=s2%s1;
                    }else{

                    }
                    flag=null;
                    str0 ="0";
                }else {
                    //处理输入数字时
                    str0 = str0 + str;
//                    System.out.println(str0);
                    s1=Double.parseDouble(str0);
                    System.out.print("s1:");
                    System.out.println(s1);
                }
                //无论是哪个按钮，最终始终设置为s1
                jtf.setText(s1+"");
            }
        };

        for (int i = 0; i < jbs.length; i++) {
            jbs[i].addActionListener(actionListener);
        }
    }
}