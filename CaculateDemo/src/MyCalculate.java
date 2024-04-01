import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyCalculate {
    private JFrame jf;
    private JPanel jp1, jp2;
    private JTextField jtf;
    private JButton[] jbs;
    private JButton c_jbs;
    private String str0 = "0";
    private String flag=null;
    private double s1 = 0.0;
    private double s2 = 0.0;

    public MyCalculate() {
        jf = new JFrame("计算器");
        jp1 = new JPanel();
        jtf = new JTextField(14);
        jbs = new JButton[16];
        c_jbs = new JButton("C");
        String s = "123+456-789*0.=/";
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton(s.substring(i, i + 1));
        }
        init();
        doit();
    }

    private void init() {
        jp1.setLayout(new FlowLayout());
        jp1.add(jtf);
        jp1.add(c_jbs);
        jtf.setEditable(true);

        jtf.setText("0");
        jp2 = new JPanel();
        jp2.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < jbs.length; i++) {
            jp2.add(jbs[i]);
        }
        jf.add(jp1, BorderLayout.NORTH);
        jf.add(jp2, BorderLayout.CENTER);
    }

    public void showMe() {
        jf.setVisible(true);
        jf.setSize(276, 210);
        // jf.pack(); //�Զ�ƥ���С
        jf.setLocation(200, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFontAndColor();
    }

    private void setFontAndColor() {
        Font f = new Font("����", Font.BOLD, 14);
        jtf.setFont(f);
        Color c = new Color(63, 178, 198);
        c_jbs.setBackground(c);
        jp1.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < jbs.length; i++) {
            jbs[i].setBackground(Color.gray);
        }
    }

    public void doit() {
        c_jbs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if (str.equals("C")) {
                    str0 = "0";
                    s1 = 0.0;
                    s2 = 0.0;
                    flag=null;
                    jtf.setText(str0);
                }
            }
        });
        for (int i = 0; i < jbs.length; i++) {
            jbs[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // �õ���ť�ϵ�����
                    String str = e.getActionCommand();
                    if (str.equals("+")) {
                        s2=s1;
                        s1=0.0;
                        str0 = "0";
                        flag="+";
                        s1=s2+s1;
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
                        s1=0.0;
                        str0 = "0";
                        flag="/";
                        s1=s2/s1;
                    } else if (str.equals("=")) {
                        if(flag.equals("+")){
                            s1=s2+s1;
                        }else if(flag.equals("-")){
                            s1=s2-s1;
                        }else if(flag.equals("*")){
                            s1=s2*s1;
                        }else if(flag.equals("/")){
                            s1=s2/s1;
                        }else{
                        }
                        flag=null;
                        str0 ="0";
                    } else {
                        str0 = str0 + str;
                        s1=Double.parseDouble(str0);
                    }
                    jtf.setText(s1+"");
                }
            });
        }

    }
}



//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//
//public class MyCalculate {
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        System.out.println(exec("-8*(((-2+4)+3)/((-1-5)*-2)-5)"));
//    }
//
//    /**
//     *
//     * @param exp
//     *            带括号的四则表达式
//     * @return 运算结果
//     */
//    public static double exec(String exp) {
//
//        // 有括号
//        // -8*(((-2+4)+3)/((-1-5)*-2)-5)
//
//
//        int leftIndex = exp.lastIndexOf('('); // 16
//
//        if (leftIndex == -1) {
//            // 没有括号
//            // System.out.println("calc" + exp);
//            return calc(exp);
//        } else {
//            // 如果有括弧，调用exec
//            // System.out.println("exec" + exp);
//            // 先找最里面的(位置 再找对应的)位置
//
//            // (-1-5)*-2)-5) 21
//            int rightIndex = exp.substring(leftIndex).indexOf(')') + leftIndex;
//
//            // 去独立的表达式，运算 calc（-1-5）
//            double res = calc(exp.substring(leftIndex + 1, rightIndex));
//            // 重新组织表达式
//            exp = exp.substring(0, leftIndex) + res
//                    + exp.substring(rightIndex + 1);
//            // -8*(((-2+4)+3)/( -6 *-2)-5)
//            return exec(exp);
//        }
//        // 如果没有括弧 直接调用calc
//
//    }
//
//    /**
//     *
//     * @param exp
//     *            不带括号的四则表达式
//     * @return 运算结果
//     */
//    public static double calc(String exp) {
//        // 1 . 获取所有四则运算的数字
//        List<Double> numbers = sliptNumbers(exp);
//        // 2. 获取所有四则运算的操作符号
//        List<Character> ops = sliptOps(exp);
//        // 3. 先乘车运算
//        // 遍历运算符中的*和/
//        for (int i = 0; i < ops.size(); i++) {
//            // * /
//            // 获取运算符（不移除）
//            char op = ops.get(i);
//
//            // 如果是 * 或者 /， 从运算符的容器中移除，同是从数字容器中到对应该运算符位置的两个数字（移除数据，后面所有数据往前顺序移动）
//            if (op == '*' || op == '/') {
//                // 从运算符的容器中移除
//                ops.remove(i);// 移除当前位置
//
//                // 从数字容器中获取对应该运算符位置的两个数字（移除）
//                double d1 = numbers.remove(i);
//                double d2 = numbers.remove(i);
//
//                // 运算
//                d1 = op == '*' ? d1 * d2 : d1 / d2;
//
//                // 把运算结果插入到数字容器中的i位置
//                numbers.add(i, d1);// 插入到i的位置 原来从i位置一直到最后的数据，都要往后瞬移一位
//                // numbers.set(i, d1);//设置i位置上的数据为d1,其余不变
//                i--;// 移除后，后面所有运算符往前移动，为了保证下一个运算符不被遗漏，所以i--
//            }// end if (op == '*' || op == '/') {
//
//        }// end for (int i = 0 ; i < ops.size(); i++) {
//
//        // 4. 再加减运算
//        while (!ops.isEmpty()) {
//            // 每次去运算容器中第一个运算符
//            char op = ops.remove(0);
//            // 每次从数字容器中两次取第一个数字
//            double d1 = numbers.remove(0);
//            double d2 = numbers.remove(0);
//
//            // 计算
//            d1 = op == '+' ? d1 + d2 : d1 - d2;
//
//            // 把结果插入到数字容器中的第一个位置
//            numbers.add(0, d1);
//        }
//
//        // 5. 返回结果
//
//        return numbers.get(0);
//    }
//
//    /**
//     * 从表达式中分离所有的运算符
//     *
//     * @param exp
//     */
//    private static List<Character> sliptOps(String exp) {
//        List<Character> ops = new ArrayList<Character>();
//        // -8*-2+3/-1-5*-2-5
//        // 把真实表达式变成下面的表达式
//        String formaterExp = formaterExp(exp);
//        // @8*@2+3/@1-5*@2-5
//
//        StringTokenizer st = new StringTokenizer(formaterExp, "@0123456789.");
//        while (st.hasMoreTokens()) {
//            String opStr = st.nextElement() + "";// 取出分割符号之间的数据
//            // System.out.println(numStr);
//            // 如果前面是@ 变为负数
//            ops.add(opStr.charAt(0));
//        }
//        return ops;
//    }
//
//    /**
//     * 从表达式中分离所有的数字
//     *
//     * @param exp
//     *            -8*-2+3/-1-5*-2-5 表达式
//     */
//    private static List<Double> sliptNumbers(String exp) {
//        List<Double> numbers = new ArrayList<Double>();
//        // -8*-2+3/-1-5*-2-5
//        // 把真实表达式变成下面的表达式
//        String formaterExp = formaterExp(exp);
//        // @8*@2+3/@1-5*@2-5
//
//        StringTokenizer st = new StringTokenizer(formaterExp, "+-*/");
//        while (st.hasMoreTokens()) {
//            String numStr = st.nextElement() + "";// 取出分割符号之间的数据
//            // System.out.println(numStr);
//            // 如果前面是@ 变为负数
//            if (numStr.charAt(0) == '@') {
//                numStr = "-" + numStr.substring(1);
//            }
//
//            // 把数字型的字符串变成数字
//            numbers.add(Double.parseDouble(numStr));
//        }
//        return numbers;
//    }
//
//    private static String formaterExp(String exp) {
//        int index = 0;
//        while (index < exp.length()) {
//            char c = exp.charAt(index);
//            // 判断是否是-符号
//            // -代表的是负数 第一个，前一字符*/
//            if (c == '-') {
//                // 第一个，
//                if (index == 0) {
//                    exp = "@" + exp.substring(1);
//                } else {
//                    // 前一字符* /
//                    if (exp.charAt(index - 1) == '*'
//                            || exp.charAt(index - 1) == '/') {
//                        exp = exp.substring(0, index) + "@"
//                                + exp.substring(index + 1);
//                    }
//                }
//            }
//
//            index++;
//            //
//        }
//        return exp;
//    }
//
//}