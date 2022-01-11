import cn.GiveQuestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainThread extends JFrame implements ActionListener, Runnable
{
    JTextArea showText;                                 // 题目展示框
    JButton startButton, confirmButton;                 // 开始按钮、确认按钮
    JRadioButton radioA, radioB, radioC, radioD;        // 单选按钮
    JCheckBox checkA, checkB, checkC, checkD;           // 多选按钮
    JRadioButton radioTrue, radioFalse;                 // 判断按钮
    JButton empty1, empty2, empty3, empty4;             // 排版用的空按钮   
    Box baseBox;                                        // 盒式布局
    ButtonGroup bg1, bg2, bg3;                          // 按钮
    JPanel jp1, jp2, jp3, jp4, jp5, jp6;                // 面板控件             
    JTextField showAnswer, showScore, time;             // 答案展示框、分数展示框，时间框
    GiveQuestion giveQuestion;                          // GiveQuestion线程
    
    int score = 0;                                      // 分数
    int idx = 0;                                        // 题号
    Timer timer;                                        // 计时器
    int second = 0;                                     // 时间
    String choice = new String();                       // 选项
    

    public MainThread()
    {
        baseBox = Box.createVerticalBox();
		// 题目区域样式
        showText = new JTextArea();
        showText.setFont(new Font("", Font.BOLD,15));
        // 自动换行
        showText.setLineWrap(true);
        showText.setWrapStyleWord(true);

		// 实例化GiveQuesiton线程
        giveQuestion = new GiveQuestion();
        giveQuestion.showText = showText;

		// 设置其他组件
        empty3 = new JButton();
        empty4 = new JButton();
        startButton = new JButton("开始答题");

		// 设置开始按钮及其监视器
        startButton.addActionListener(this);        
        startButton.setPreferredSize(new Dimension(575,50));
        add(startButton);
        baseBox.add(Box.createVerticalStrut(5));
        baseBox.add(showText);
        showText.setPreferredSize(new Dimension(575, 150));
        baseBox.add(Box.createVerticalStrut(5));

        // 按钮排列
        // 单选
        jp1 = new JPanel();
        jp1.setLayout(new GridLayout(1, 4));
        radioA = new JRadioButton("A");
        radioB = new JRadioButton("B");
        radioC = new JRadioButton("C");
        radioD = new JRadioButton("D");
        jp1.add(radioA);
        jp1.add(radioB);
        jp1.add(radioC);
        jp1.add(radioD);
        // 多选
        jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1, 4));
        checkA = new JCheckBox("A");
        checkB = new JCheckBox("B");
        checkC = new JCheckBox("C");
        checkD = new JCheckBox("D");
        jp2.add(checkA);
        jp2.add(checkB);
        jp2.add(checkC);
        jp2.add(checkD);
        // 判断
        jp3 = new JPanel(new GridLayout(1, 2));
        radioTrue = new JRadioButton("A");
        radioFalse = new JRadioButton("B");
        jp3.add(radioTrue);
        jp3.add(radioFalse);


        // 按钮功能实现
        // 单选
        bg1 = new ButtonGroup();
        bg1.add(radioA);
        bg1.add(radioB);
        bg1.add(radioC);
        bg1.add(radioD);
        baseBox.add(jp1);
        jp1.setVisible(false);
        // 多选
        baseBox.add(jp2);
        jp2.setVisible(false);
        // 判断
        bg3 = new ButtonGroup();
        bg3.add(radioTrue);
        bg3.add(radioFalse);
        baseBox.add(jp3);
        jp3.setVisible(false);
        baseBox.add(Box.createVerticalStrut(5));
        // 确认按钮
        jp4 = new JPanel();
        jp4.setLayout(new GridLayout(1, 3));
        confirmButton = new JButton("提交");
        confirmButton.addActionListener(this);
        jp4.add(empty3);
        jp4.add(confirmButton);
        jp4.add(empty4);
        empty3.setVisible(false);
        empty4.setVisible(false);
        baseBox.add(jp4);
        jp4.setVisible(false);
        baseBox.add(Box.createVerticalStrut(8));
        
        // 显示正确答案和分数
        jp5 = new JPanel(new GridLayout(3, 4));
        showAnswer = new JTextField(5);
        showScore = new JTextField(5);
        showAnswer.setEditable(false);
        showScore.setEditable(false);
        jp5.add(new JLabel("正确答案: "));
        jp5.add(showAnswer);
        jp5.add(new JLabel(""));
        jp5.add(new JLabel(""));
        jp5.add(new JLabel(""));
        jp5.add(new JLabel(""));
        jp5.add(new JLabel(""));
        jp5.add(new JLabel(""));
        jp5.add(new JLabel("成绩: "));
        jp5.add(showScore);
        jp5.add(new JLabel(""));
        jp5.add(new JLabel(""));
        baseBox.add(jp5);
        jp5.setVisible(false);
        baseBox.add(Box.createVerticalStrut(20));

        // 显示时间
        jp6 = new JPanel(new GridLayout(1, 4));
        time = new JTextField();
        JTextField empty5 = new JTextField();
        JTextField empty6 = new JTextField();
        empty5.setVisible(false);
        empty6.setVisible(false);
        time.setEditable(false);
        jp6.add(new JLabel("时间: "));
        jp6.add(time);
        jp6.add(empty5);
        jp6.add(empty6);
        baseBox.add(jp6);
        jp6.setVisible(false);

        // 计时器更新
        timer = new Timer(1000, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                second++;
                time.setText(String.format(second + " s"));
            }
        });

		// 主窗口样式
        add(baseBox);
        setBounds(100, 100, 600, 400);
        FlowLayout flow = new FlowLayout();
        setVisible(true);
        validate();
        flow.setAlignment(FlowLayout.LEFT);
        setLayout(flow);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showChoice()
    {
        // 初始不显示
        bg1.clearSelection();
        checkA.setSelected(false);
        checkB.setSelected(false);
        checkC.setSelected(false);
        checkD.setSelected(false);
        bg3.clearSelection();
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp3.setVisible(false);
        // 根据题目显示对应选项
        if (idx < 10) jp1.setVisible(true);
        else if (idx < 20) jp2.setVisible(true);
        else jp3.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
		// 开始答题
        if (e.getSource() == startButton)
        {
            if(!(giveQuestion.isAlive()))
            {
                giveQuestion = new GiveQuestion();
                giveQuestion.showText = showText;
                giveQuestion.sleepTime = 20000;
            }
            try
            {
                giveQuestion.start();
                startButton.setVisible(false); // 开始按钮隐藏
            }
            catch (Exception ex) {}
        }

        // 确认按钮
        else if (e.getSource() == confirmButton)
        {
            choice = "";    
            // 单选
            if (idx < 10)
            {
                if (radioA.isSelected()) choice = "A";
                else if (radioB.isSelected()) choice = "B";
                else if (radioC.isSelected()) choice = "C";
                else if (radioD.isSelected()) choice = "D";
            }
            // 多选
            else if (idx < 20)
            {
                if (checkA.isSelected()) choice = "A";
                if (checkB.isSelected()) choice = "B";
                if (checkC.isSelected()) choice = "C";
                if (checkD.isSelected()) choice = "D";
            }
            // 判断
            else
            {
                if (radioTrue.isSelected()) choice = "A";
                else if (radioFalse.isSelected()) choice = "B";
            }

            if (choice.equals("")) return ; // 空点确认

            showAnswer.setText(giveQuestion.answers[idx]);
            // 判断得分
            if (choice.equals(giveQuestion.answers[idx]))
            {
                if (idx >= 10 && idx < 20) score += 2;
                else score++;
            }
            showScore.setText(Integer.toString(score));
            giveQuestion.interrupt();
        }
    }

    @Override
    public void run()
    {
        // 等待答题线程
        while (!giveQuestion.isAlive()) {}

        jp4.setVisible(true);   // 显示确认按钮
        jp5.setVisible(true);   // 显示计分榜
        jp6.setVisible(true);   // 显示时间
        idx = -1;               // 初始化idx
        timer.start();          // 启动计时器
        showScore.setText("0"); // 初始化score

        // 开始给题
        while (giveQuestion.num <= 15)
        {
            try
            {
                Thread.sleep(10); // 展示正确选项时间
            }
            catch (InterruptedException ee) {}
            if (idx == giveQuestion.idx) continue;
            idx = giveQuestion.idx;
            showChoice();
        }
        timer.stop();
    }
}

public class Program
{
    public static void main(String [] args)
    {
        MainThread mainThread = new MainThread();
        Thread thread = new Thread(mainThread);
        mainThread.setTitle("单机版Java简易机考程序");
        thread.start();
    }
}
