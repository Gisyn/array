package cn;

import java.util.Random;
import javax.swing.*;

public class GiveQuestion extends Thread 
{
    public JTextArea showText;              // 题目
    public int idx;                         // 题号
    public int num = 1;
    // 各题型
    int singleChoice = 0;
    int multiChoice = 0; 
    int judgeChoice = 0;
    // 标记位
    int [] flag = new int [30];
    // 答题时间
    public int sleepTime = 20000;           
    // question data
    String [] questions =
    {
        // 单选
        "【单选】下面哪个单词是Java语言的关键字？\nA. Float\nB. this\nC. string\nD. unsigned",
        "【单选】Java程序的执行过程中用到一套JDK工具，其中java.exe是指: \nA. Java文档生成器\nB. Java解释器\nC. Java编译器\nD. Java类分解器",
        "【单选】提供Java存取数据库能力的包是: \nA. java.sql\nB. java.awt\nC. java.lang\nD. java.swing",
        "【单选】JAVA语言使用的字符集是（  ）。\nA. ASCII\nB. Unicode\nC. EBCDIC\nD. GBK",
        "【单选】Java I/O程序设计中，下列描述正确的是：\nA. OutputStream用于写操作\nB. InputStream用于写操作\nC. I/O库不支持对文件可读可写API\nD. 以上均正确",
        "【单选】下列javaDoc注释正确的是：\nA. /*我爱北京天安门*/\nB. //我爱北京天安门*/\nC. /**我爱北京天安门*/\nD. /*我爱北京天安门**/",
        "【单选】下列说法正确的是：\nA. JAVA程序的main方法必须写在类里面\nB. JAVA程序中可以有多个main方法\nC. JAVA程序中类名必须与文件名一样\nD. JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来",
        "【单选】下面哪个可以改变容器的布局？\nA. setLayout(aLayoutManager)\nB. addLayout(aLayoutManager)\nC. layout(aLayoutManager)\nD. setLayoutManager(aLayoutManager)",
        "【单选】表达式不可以作为循环条件: \nA. i++;\nB. i>5;\nC. bEqual = str.equals(\"q\");\nD. count = = i;",
        "【单选】关于sleep()和wait()，以下描述错误的一项是（ ）\nA. sleep是线程类（Thread）的方法，wait是Object类的方法\nB. sleep不释放对象锁，wait放弃对象锁\nC. sleep暂停线程、但监控状态仍然保持，结束后会自动恢复\nD. wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态",

        // 多选
        "【多选】下面能让线程停止执行的有（   ）。\nA. sleep()\nB. stop()\nC. notify()\nD. synchronized()",
        "【多选】下列说法错误的有: \nA. 在类方法中可用this来调用本类的类方法\nB. 在类方法中调用本类的类方法时可直接调用\nC. 在类方法中只能调用本类中的类方法\nD. 在类方法中绝对不能调用实例方法",
        "【多选】下面的哪些声明是合法的？ \nA. long l = 499\nB. int i = 4L\nC. float f =1.1\nD. double d = 34.4",
        "【多选】在Java中，下面对于构造函数的描述错误的是：\nA. 类不一定要显式定义构造函数\nB. 构造函数的返回类型是void\nC. 如果构造函数不带任何参数，那么构造函数的名称和类名可以不同\nD. 一个类可以定义多个构造函数",
        "【多选】下面关于构造函数的说法正确的是: \nA. 构造函数也属于类的方法，用于创建对象的时候给成员变量赋值。\nB. 构造函数不可以重载。\nC. 构造函数有返回值。\nD.构造函数一定要和类名相同",
        "【多选】在Java接口中，下列选项中有效的方法声明是: \nA. public void aMethod();\nB. void aMethod();\nC. protected void aMethod();\nD. private void aMethod();",
        "【多选】允许作为类及类成员的访问控制符的是: \nA. public\nB. private\nC. static\nD. protected",
        "【多选】下列关于哪些叙述是不正确的？\nA. 一个类可以定义多个构造函数\nB. 一个类只能实现一个接口\nC. 一个类不能同时继承一个类和实现一个接口\nD. 一个类可以具有多个子类",
        "【多选】关于关键字static以下哪些选项是错误的？\nA. 被static修饰符修饰的成员变量可以被修改。\n B. 在方法中创建的static变量，每次方法调用中对它的值做的修改都不能保留下来。\nC. 一个类的所有类对象共享这个类的static变量。\nD. static修饰符只能用于修饰基本类型变量。",
        "【多选】下面哪些是对字符串String的不正确定义？\nA. String s1=null;\nB. String s2='null';\nC. String s3=(String)'abc';\nD. String s4=(String)'\\uface';",

        // 判断
        "【判断】在Java的方法中定义一个常量要用const关键字。\nA. 正确\nB. 错误",
        "【判断】描述对象的两个要素是属性和方法。\nA. 正确\nB. 错误",
        "【判断】Java支持多重继承。\nA. 正确\nB. 错误",
        "【判断】一个类可以实现多接口。\nA. 正确\nB. 错误",
        "【判断】Java语言是编译解释型语言。\nA. 正确\nB. 错误",
        "【判断】抽象方法没有方法体。\nA. 正确\nB. 错误",
        "【判断】package语句必须放在程序的第一句。\nA. 正确\nB. 错误",
        "【判断】接口中可以包含非静态成员变量。\nA. 正确\nB. 错误",
        "【判断】final 类可以有子类。\nA. 正确\nB. 错误",
        "【判断】接口中可以包含非静态成员。\nA. 正确\nB. 错误"
    };
    //答案列表
    public String[] answers =
    {
        "B", "B", "A", "B", "A", "A", "A", "D", "A", "D",                   // 单选题答案
        "ABD", "ACD", "AD", "BC", "AD", "AB", "ABD", "BC", "BD", "BCD",     // 多选题答案
        "B", "A", "B", "A", "A", "B", "A", "B", "B", "B"                    // 判断题答案
    };

    @Override
    public void run()
    {
        showText.setEditable(false);
        // 初始化标记
        for (int i = 0; i < 30; i++) flag[i] = 0;
        idx = 0;
        Random random = new Random();
        while (num <= 15)
        {
            // 题型数量最多为5
            if (singleChoice >= 5) 
            {
                for (int i = 0; i < 10; i++) flag[i] = 1;
            }
            if (multiChoice >= 5)
            {
                for (int i = 10; i < 20; i++) flag[i] = 1;
            }
            if (judgeChoice >= 5)
            {
                for (int i = 20; i < 30; i++) flag[i] = 1;
            }
            // 找到合法的题目
            while (flag[idx] == 1) idx = random.nextInt(30); 
            flag[idx] = 1;
            // 标记
            if (idx < 10) singleChoice++;
            else if (idx < 20) multiChoice++;
            else judgeChoice++;
            // 显示
            showText.setText(num + ". " + questions[idx]);
            // 间隔
            try 
            {
                Thread.sleep(sleepTime);
            }
            // 正确答题
            catch (InterruptedException e1)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (Exception e2) {}
            }
            num++;
        }
    }
}