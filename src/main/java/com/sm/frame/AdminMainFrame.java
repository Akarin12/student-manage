package com.sm.frame;

import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainFrame extends JFrame {
    private JPanel rootPanel;
    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPunishPanel;
    private JButton 新增院系Button;
    private JButton 刷新数据Button;
    private JPanel leftPanel;
    private JTextField depNameField;
    private JButton 选择logo图Button;
    private JButton 新增Button;
    private JScrollPane scrollPane;
    private JPanel contentPanel;

    public AdminMainFrame() {
        //窗体基本属性
        setTitle("管理员主界面");
        setContentPane(rootPanel);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //获取centerPanel的布局 获得的LayoutManger 向下转型为cardlayout
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("card1", departmentPanel);
        centerPanel.add("card2", classPanel);
        centerPanel.add("card3", studentPanel);
        centerPanel.add("card4", rewardPunishPanel);

        院系管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "card1");
            }
        });
        班级管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "card2");
            }
        });

        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "card3");
            }
        });
        奖惩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "card4");
            }
        });


        新增院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = leftPanel.isVisible();
                if (flag == false) {
                    leftPanel.setVisible(true);
                } else {
                    leftPanel.setVisible(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        new AdminMainFrame();
    }
}