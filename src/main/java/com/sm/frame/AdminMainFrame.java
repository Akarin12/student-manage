package com.sm.frame;


import com.sm.entity.*;
import com.sm.factory.ServiceFactory;
import com.sm.service.DepartmentService;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Map;

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
    private JLabel adminLabel;
    private JLabel timeLabel;
    private JLabel logoLabel;
    private JButton reButton;
    private JTextField classTextField;
    private JButton 新增班级Button;
    private JPanel topPanel;
    private JScrollPane centerJScrollPanel;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private String uploadFileUrl;
    private File file;

    private Department department;
    private DepartmentService departmentService;

    private int departmentId = 0;
    private JComboBox<Department> depcomboBox;
    private JPanel stuTopPanel;
    private JComboBox<Department> comboBox1;
    private JComboBox<CClass> comboBox2;
    private JTextField findStuField;
    private JButton 搜索Button;
    private JButton 批量导入Button;
    private JButton 新增学生Button;
    private ImgPanel infoPanel;
    private JPanel tablePanel;
    private JLabel stuAvatarLabel;
    private JLabel stuIdLabel;
    private JLabel stuClassLabel;
    private JLabel stuNameLabel;
    private JLabel stuGenderLabel;
    private JTextField stuAddressField;
    private JTextField stuPhoneField;
    private JLabel stuBirthdayLabel;
    private JButton 编辑Button;
    private JLabel stuDepLabel;
    private JButton 初始化数据Button;
    private JComboBox comboBox3;
    private JTextField textField3;
    private JButton 查询Button;
    private JButton 新增奖惩Button;
    private JButton 返回Button;
    private JPanel listPanel;

    private int row;

    public AdminMainFrame(Admin admin) {
        departmentService = ServiceFactory.getDepartmentServiceInstance();

        //窗体基本属性
        setTitle("管理员主界面");
        setContentPane(rootPanel);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Font font = new Font("宋体", Font.BOLD, 24);
        timeLabel.setFont(font);
        adminLabel.setFont(font);

        //获取centerPanel的布局 获得的LayoutManger 向下转型为cardlayout
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("card1", departmentPanel);
        centerPanel.add("card2", classPanel);
        centerPanel.add("card3", studentPanel);
        centerPanel.add("card4", rewardPunishPanel);

        adminLabel.setText("管理员:" + admin.getAdminName());

        TimeThread timeThread = new TimeThread();
        timeThread.setTimeLabel(timeLabel);
        timeThread.start();

        showDepartments();

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
                showClassPanel();
            }
        });

        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "card3");
                infoPanel.setFileName("info.jpg");
                infoPanel.repaint();
                List<StudentVO> students = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudentTable(students);

                Department tip1 = new Department();
                tip1.setDepartmentName("请选择院系");
                comboBox1.addItem(tip1);
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox2.addItem(tip2);

                List<Department> departments = ServiceFactory.getDepartmentServiceInstance().selectAll();
                for (Department department : departments) {
                    comboBox1.addItem(department);
                }

                List<CClass> cClasses = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : cClasses) {
                    comboBox2.addItem(cClass);
                }

                comboBox1.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            int index = comboBox1.getSelectedIndex();
                            if (index != 0) {
                                departmentId = comboBox1.getItemAt(index).getId();
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByDepartmentId(departmentId);
                                showStudentTable(studentList);
                                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(departmentId);
                                comboBox2.removeAllItems();
                                CClass tip = new CClass();
                                tip.setClassName("请选择班级");
                                comboBox2.addItem(tip);
                                for (CClass cClass : cClassList) {
                                    comboBox2.addItem(cClass);
                                }
                            }
                        }
                    }
                });

                comboBox2.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            int index = comboBox2.getSelectedIndex();
                            if (index != 0) {
                                int classId = comboBox2.getItemAt(index).getId();
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByClassId(classId);
                                showStudentTable(studentList);
                            }
                        }
                    }
                });

            }
        });

        奖惩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "card4");
                List<Rewards> rewardsList = ServiceFactory.getRewardsServiceInstance().getAll();
                comboBox3.removeAllItems();
                comboBox3.addItem("学号查询");
                comboBox3.addItem("姓名查询");
                showRewardsTable(rewardsList);
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

        刷新数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepartments();
            }
        });

        depNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                depNameField.setText("");
            }
        });

        选择logo图Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:\\resources\\images\\avatar\\new"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建icon对象
//                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                    //将按钮隐藏
                    选择logo图Button.setVisible(false);
                    reButton.setVisible(true);
                }
            }
        });

        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上传文件到OSS并返回外链
                uploadFileUrl = AliOSSUtil.ossUpload(file);
                //创建Department对象，并设置相应属性
                Department department = new Department();
                department.setDepartmentName(depNameField.getText().trim());
                department.setLogo(uploadFileUrl);
//                department.setDescription(descriptionArea.getText().trim());
                //调用service实现新增功能
                int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    //新增成功后，将侧边栏隐藏
                    leftPanel.setVisible(false);
                    //刷新界面数据
                    showDepartments();
                    //将图片预览标签隐藏
                    logoLabel.setVisible(false);
                    //将选择图片的按钮可见
                    选择logo图Button.setVisible(true);
                    //清空文本框
                    depNameField.setText("");
//                    descriptionArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系失败");
                }
            }
        });

        reButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoLabel.setVisible(false);
                选择logo图Button.setVisible(true);
                reButton.setVisible(false);
            }
        });

        新增班级Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        新增班级Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = classTextField.getText();
                String[] strings = s.split("；");
                for (String ss : strings) {
                    CClass cClass = new CClass();
                    cClass.setClassName(ss);
                    cClass.setDepartmentId(departmentId);
                    ServiceFactory.getCClassServiceInstance().addClass(cClass);
                }
                showClassPanel();
                classTextField.setText("");
            }
        });

        初始化数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<StudentVO> students = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudentTable(students);
                comboBox1.setSelectedIndex(0);
                comboBox2.removeAllItems();
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox2.addItem(tip2);
                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : cClassList) {
                    comboBox2.addItem(cClass);
                }

                stuAvatarLabel.setText("");
                stuIdLabel.setText("");
                stuDepLabel.setText("");
                stuClassLabel.setText("");
                stuNameLabel.setText("");
                stuGenderLabel.setText("");
                stuAddressField.setText("");
                stuBirthdayLabel.setText("");
                stuPhoneField.setText("");
            }
        });

        搜索Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = findStuField.getText().trim();
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByKeywords(keywords);
                if (studentList != null) {
                    showStudentTable(studentList);
                }
            }
        });

        新增学生Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFrame(AdminMainFrame.this);


            }
        });
        查询Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = textField3.getText().trim();
                List<Rewards> rewardsList = ServiceFactory.getRewardsServiceInstance().selectByKeywords(keywords);
                if (rewardsList !=null){
                    showRewardsTable(rewardsList);
                }
            }
        });
        新增奖惩Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RewardsAddFrame(AdminMainFrame.this);
                AdminMainFrame.this.setEnabled(false);
            }
        });
        返回Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Rewards> rewardsList = ServiceFactory.getRewardsServiceInstance().getAll();
                showRewardsTable(rewardsList);
                textField3.setText("");
            }
        });
    }


    public static void main(String[] args) throws Exception {
//        new AdminMainFrame(new Admin("我"));
//        new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("tianzhen"));

    }

    private void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List<Map> departmentList = ServiceFactory.getDepartmentServiceInstance().selectDepartmentInfo();        int len = departmentList.size();
        //获取院系统计数据
        for (Map map : departmentList) {
            //给每个院系对象创建一个面板
            final JPanel depPanel = new JPanel(new BorderLayout());
            Department department = (Department) map.get("department");
            int classCount = (int) map.get("classCount");
            int studentCount = (int) map.get("studentCount");
            depPanel.setLayout(new BorderLayout());
            depPanel.setPreferredSize(new Dimension(300, 300));
            //将院系名称设置给面板标题
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            //新建一个Label用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "' width=200 height=200/></html>");
            //图标标签加入院系面板
            depPanel.add(logoLabel, BorderLayout.CENTER);
            //将院系统计数据加入面板
            JLabel infoLabel = new JLabel("班级" + classCount + "个,学生" + studentCount + "人");
            depPanel.add(infoLabel, BorderLayout.SOUTH);
            //院系面板加入主体内容面板
            contentPanel.add(depPanel);
            //刷新主体内容面板
            contentPanel.revalidate();
            JButton delBtn = new JButton("删除");
            delBtn.setSize(new Dimension(50, 30));
            depPanel.add(delBtn, BorderLayout.EAST);
            delBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int n = JOptionPane.showConfirmDialog(null, "确认删除吗", "warring!", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        contentPanel.remove(depPanel);
                        contentPanel.repaint();
                        ServiceFactory.getDepartmentServiceInstance().deleteDepartment(department.getId());
                    }
                    showDepartments();
                }
            });
        }
    }

    /**
     * 展示班级的数据
     */
    private void showClassPanel() {
        List<Department> departmentlist = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showCombobox(departmentlist);
        showTree(departmentlist);
        showClasses(departmentlist);
    }

    private void showCombobox(List<Department> departmentList) {
        for (Department department : departmentList
        ) {
            depcomboBox.addItem(department);
        }
    }

    private void showTree(List<Department> departmentList) {
        treePanel.removeAll();
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("南工院");
        for (Department department :
                departmentList) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            top.add(group);
            List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(department.getId());
            for (CClass cClass :
                    cClassList) {
                int num = ServiceFactory.getStudentServiceInstance().countStudentByClassId(cClass.getId());
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(cClass.getClassName() + "(" + num + "人)");
                group.add(node);
            }
        }
        final JTree tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        treePanel.add(tree);
        treePanel.revalidate();
    }

    private void showClasses(List<Department> departmentList) {
        classContentPanel.removeAll();
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 20);
        for (Department department :
                departmentList) {
            ImgPanel depPanel = new ImgPanel();
//            depPanel.setFileName("66.jpg");
//            depPanel.repaint();
            depPanel.setPreferredSize(new Dimension(350, 500));
            depPanel.setLayout(null);
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setBounds(130, 15, 200, 30);
            List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(department.getId());
            DefaultListModel listModel = new DefaultListModel();
            for (CClass cClass :
                    cClassList) {
                listModel.addElement(cClass);
            }
            JList<CClass> jList = new JList<>(listModel);
            JScrollPane listScrollPanel = new JScrollPane(jList);
            listScrollPanel.setBounds(50, 120, 250, 300);
            depPanel.add(depNameLabel);
            depPanel.setFont(titleFont);
            depNameLabel.setFont(titleFont);
            depPanel.add(listScrollPanel);
            classContentPanel.add(depPanel);
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem item1 = new JMenuItem("修改");
            JMenuItem item2 = new JMenuItem("删除");
            jPopupMenu.add(item1);
            jPopupMenu.add(item2);
            jList.add(jPopupMenu);
            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int index = jList.getSelectedIndex();
                    if (e.getButton() == 3) {
                        jPopupMenu.show(jList, e.getX(), e.getY());
                    }
                    CClass cClass = jList.getModel().getElementAt(index);
                    item2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int choice = JOptionPane.showConfirmDialog(depPanel, "确定删除吗？");
                            if (choice == 0) {
                                ServiceFactory.getCClassServiceInstance().delectClass(cClass.getId());
                                listModel.remove(index);
                                showClassPanel();
                            }
                        }
                    });
                }
            });
        }


        //新增班级数据
        depcomboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //得到选中项的索引
                int index = depcomboBox.getSelectedIndex();
                //按照索引取出项，就是一个Department对象，然后取出其id备用
                departmentId = depcomboBox.getItemAt(index).getId();
            }
        });


    }

    //展示学生信息
    public void showStudentTable(List<StudentVO> studentList) {
        tablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(new String[]{"学号", "院系", "班级", "姓名", "性别", "地址", "手机号", "出生日期", "头像"});
        for (StudentVO student : studentList) {
            Object[] objects = new Object[]{
                    student.getId(), student.getDepartmentName(), student.getClassName(),
                    student.getStudentName(), student.getGender(), student.getAddress(),
                    student.getPhone(), student.getBirthday(), student.getAvatar()
            };
            model.addRow(objects);
            TableColumn tc = table.getColumnModel().getColumn(8);
            tc.setMinWidth(0);
            tc.setMaxWidth(0);
            JTableHeader header = table.getTableHeader();
            DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
            hr.setHorizontalAlignment(JLabel.CENTER);
            header.setDefaultRenderer(hr);
            header.setPreferredSize(new Dimension(header.getWidth(), 40));
            header.setFont(new Font("微软雅黑", 0, 22));
            table.setRowHeight(35);
            table.setBackground(new Color(60, 60, 60));
            table.setGridColor(new Color(240, 240, 240));
            DefaultTableCellHeaderRenderer r = new DefaultTableCellHeaderRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
            JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            tablePanel.add(scrollPane, BorderLayout.CENTER);
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem item = new JMenuItem("删除");
            jPopupMenu.add(item);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    row = table.getSelectedRow();
                    stuIdLabel.setText(table.getValueAt(row, 0).toString());
                    stuDepLabel.setText(table.getValueAt(row, 1).toString());
                    stuClassLabel.setText(table.getValueAt(row, 2).toString());
                    stuNameLabel.setText(table.getValueAt(row, 3).toString());
                    stuGenderLabel.setText(table.getValueAt(row, 4).toString());
                    stuAddressField.setText(table.getValueAt(row, 5).toString());
                    stuPhoneField.setText(table.getValueAt(row, 6).toString());
                    stuBirthdayLabel.setText(table.getValueAt(row, 7).toString());
                    stuAvatarLabel.setText("<html><img src='" + table.getValueAt(row, 8).toString() + "' width=200 height=200/></html>");
                    编辑Button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getActionCommand().equals("编辑")) {
                                stuAddressField.setEditable(true);
                                stuAddressField.setEnabled(true);
                                stuPhoneField.setEditable(true);
                                stuPhoneField.setEnabled(true);
                                编辑Button.setText("保存");
                            }
                            if (e.getActionCommand().equals("保存")) {
                                Student student = new Student();
                                student.setId(stuIdLabel.getText());
                                student.setPhone(stuPhoneField.getText());
                                student.setAddress(stuAddressField.getText());
                                int n = ServiceFactory.getStudentServiceInstance().updateById(student);
                                if (n == 1) {
                                    model.setValueAt(stuAddressField.getText(), row, 5);
                                    model.setValueAt(stuPhoneField.getText(), row, 6);
                                    stuAddressField.setEditable(false);
                                    stuAddressField.setEnabled(false);
                                    stuPhoneField.setEditable(false);
                                    stuPhoneField.setEnabled(false);
                                    编辑Button.setText("编辑");

                                }
                            }
                        }
                    });
                    if (e.getButton() == 3) {
                        jPopupMenu.show(table, e.getX(), e.getY());
                    }
                }
            });
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = String.valueOf(table.getValueAt(row, 0));
                    int choice = JOptionPane.showConfirmDialog(tablePanel, "确认删除吗");
                    if (choice == 0) {
                        if (row != -1) {
                            model.removeRow(row);
                        }
                        ServiceFactory.getStudentServiceInstance().deleteById(id);
                    }

                }
            });
        }
    }

    public void showRewardsTable(List<Rewards> rewardsList){
        listPanel.removeAll();
        //创建表格
        JTable table = new JTable();
        //表格数据模型
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //表头内容
        model.setColumnIdentifiers(new String[]{"编号", "奖惩类型", "日期", "学号", "姓名", "原因"});
        for (Rewards rewards:rewardsList) {
            Object[] objects = new Object[]{rewards.getId(),rewards.getType(),rewards.getRewardsDate(),rewards.getNumber(),rewards.getName()
                    ,rewards.getReason()};
            model.addRow(objects);
        }
        //获得表头
        JTableHeader head = table.getTableHeader();
        //表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        head.setDefaultRenderer(hr);
        //设置表头大小
        head.setPreferredSize(new Dimension(head.getWidth(), 40));
        //设置表头字体
        head.setFont(new Font("楷体", Font.PLAIN, 22));
        //设置表格行高
        table.setRowHeight(35);
        //表格背景色
        table.setBackground(new Color(212, 212, 212));
        //表格内容居中
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        //表格加入滚动面板，水平垂直方向带滚动条
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        listPanel.add(scrollPane);
        listPanel.revalidate();
        //弹出菜单
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("删除");
        jPopupMenu.add(item);
        table.add(jPopupMenu);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                if (e.getButton() == 3) {
                    jPopupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) table.getValueAt(row, 0);
                int choice = JOptionPane.showConfirmDialog(listPanel, "确定删除吗");
                if (choice == 0) {
                    if (row != -1) {
                        model.removeRow(row);
                    }
                    //刷新表格数据
                    ServiceFactory.getRewardsServiceInstance().deleteById(id);
                }
            }
        });
    }

}