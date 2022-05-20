package osgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Window.Type;

public class Gui extends JFrame {
	// 面板
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextField textField_3;

	/**
	 * Launch the application.构造方法初始化界面
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setType(Type.POPUP);
		setTitle("严良泽de磁盘调度算法OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("磁盘管理算法模拟系统");
		lblNewLabel.setForeground(new Color(0, 255, 255));
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 24));
		lblNewLabel.setBounds(220, 10, 258, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("磁道数量");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(258, 61, 86, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("选择算法");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(73, 10, 94, 44);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("磁头初始位置");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(429, 63, 131, 20);
		contentPane.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setFont(new Font("黑体", Font.PLAIN, 20));
		textField.setBounds(354, 58, 66, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("黑体", Font.PLAIN, 20));
		textField_1.setBounds(570, 58, 66, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("先来先服务");
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FCFS(e);
			}
		});
		btnNewButton.setBounds(53, 58, 131, 31);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("最短寻道优先");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SSTF(e);
			}
		});
		btnNewButton_1.setBounds(53, 99, 131, 31);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("电梯算法");
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SCAN(e);
			}
		});
		btnNewButton_2.setBounds(53, 140, 131, 31);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("循环扫描算法");
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CSCAN(e);
			}
		});
		btnNewButton_3.setBounds(53, 181, 131, 31);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel_4 = new JLabel("输入N个需要遍历的磁盘号");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(314, 119, 258, 33);
		contentPane.add(lblNewLabel_4);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("黑体", Font.PLAIN, 20));
		textField_2.setBounds(220, 162, 461, 43);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("该算法遍历的磁道序列为：");
		lblNewLabel_5.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(40, 251, 281, 43);
		contentPane.add(lblNewLabel_5);

		textArea = new JTextArea();
		textArea.setFont(new Font("黑体", Font.PLAIN, 20));
		textArea.setBounds(28, 290, 638, 31);
		contentPane.add(textArea);
		textArea.setEditable(false);

		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("黑体", Font.PLAIN, 20));
		textArea_1.setBounds(287, 331, 57, 31);
		contentPane.add(textArea_1);
		textArea_1.setEditable(false);
		
		JLabel lblNewLabel_6 = new JLabel("该算法的磁头移动距离为：");
		lblNewLabel_6.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(40, 321, 281, 43);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("选择方向：");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(229, 211, 100, 43);
		contentPane.add(lblNewLabel_7);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("黑体", Font.PLAIN, 20));
		textField_3.setBounds(327, 217, 44, 30);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("默认0为磁头向外，1向内");
		lblNewLabel_8.setForeground(new Color(255, 69, 0));
		lblNewLabel_8.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(401, 215, 258, 33);
		contentPane.add(lblNewLabel_8);
		
//		JLabel lblNewLabel_9 = new JLabel("计科20-3 严良泽 ");
//		lblNewLabel_9.setForeground(new Color(255, 69, 0));
//		lblNewLabel_9.setFont(new Font("方正姚体", Font.ITALIC, 20));
//		lblNewLabel_9.setBounds(270, 410, 160, 31);
//		contentPane.add(lblNewLabel_9);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("image/11.jpg"));
		background.setBounds(1, 0, 691, 488);
		contentPane.add(background);	
		
		this.setIconImage(new ImageIcon("image/java.png").getImage());
		//this.getContentPane().setBackground(Color.orange);
	}
	

	
	//1.FCFS
	public void FCFS(ActionEvent evt) {
		textArea.setText("");
		Cipan cipan = new Cipan();
		String M = textField.getText();
		if(M.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int M1 = Integer.parseInt(M);
		cipan.setM(M1);
		System.out.println(M1);
		
		String Now = textField_1.getText();
		if(Now.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int Now1 = Integer.parseInt(Now);
		cipan.setNow(Now1);
		System.out.println(cipan.getNow());

		String S = String.valueOf(textField_2.getText());	//拿到N磁道号
		System.out.println(S);
		int[] a = gain(S, M1);		    //构造函数来接收N个磁道号
		
		for (int i = 0; i < M1; i++) {	//测试接收到的数据
			System.out.print(a[i]+" ");
		}
		System.out.println();
		
		cipan.setCidao(a);
		String s = cipan.FCFS();		//把数据送入函数再复制给s
		System.out.println(s);
		System.out.println(cipan.getNow());
		textArea.setText(Now+s); //结果在textArea位置显示
		textArea_1.setText(Integer.toString(cipan.Sum[0]));
		//JOptionPane.showConfirmDialog(this, "hhhh");
	}
	
	//2.SSTF
	protected void SSTF(ActionEvent evt) {
		textArea.setText("");
		Cipan cipan = new Cipan();
		String M = textField.getText();
		if(M.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int M1 = Integer.parseInt(M);
		cipan.setM(M1);
		System.out.println(M1);

		String Now = textField_1.getText();
		if(Now.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int Now1 = Integer.parseInt(Now);
		cipan.setNow(Now1);
		System.out.println(Now1);
		
		String S = String.valueOf(textField_2.getText());	//拿到N磁道号
		System.out.println(S);
		int[] a = gain(S, M1);		    //构造函数来接收N个磁道号
		
		for (int i = 0; i < M1; i++) {	//测试接收到的数据
			System.out.print(a[i]+" ");
		}
		System.out.println();
		
		cipan.setCidao(a);
		String s = cipan.SSTF();		//把数据送入函数再复制给s
		System.out.println(s);
		System.out.println(cipan.getNow());
		textArea.setText(Now+s); //结果在textArea位置显示
		textArea_1.setText(Integer.toString(cipan.Sum[1]));
		
	}

	//3.SCAN
	protected void SCAN(ActionEvent evt) {	
		Cipan cipan = new Cipan();
		String M = textField.getText();
		if(M.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int M1 = Integer.parseInt(M);
		cipan.setM(M1);
		System.out.println(M1);
		
		String Now = textField_1.getText();
		if(Now.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int Now1 = Integer.parseInt(Now);
		cipan.setNow(Now1);
		System.out.println(Now1);
		
		String Choice = textField_3.getText();
		if(!Choice.equals("")) {
			int Choice1 = Integer.parseInt(Choice);
			cipan.setChoice(Choice1);
		}
		
		String S = String.valueOf(textField_2.getText());	//拿到N磁道号
		System.out.println(S);
		int[] a = gain(S, M1);		    //构造函数来接收N个磁道号
		
		for (int i = 0; i < M1; i++) {	//测试接收到的数据
			System.out.print(a[i]+" ");
		}
		System.out.println();
		
		cipan.setCidao(a);
		String s = cipan.SCAN();		//把数据送入函数再复制给s
		System.out.println(s);
		System.out.println(cipan.getNow());
		textArea.setText(Now+s); //结果在textArea位置显示
		textArea_1.setText(Integer.toString(cipan.Sum[2]));
	}
	
	//4.CSCAN
	protected void CSCAN(ActionEvent evt) {
		Cipan cipan = new Cipan();
		String M = textField.getText();
		if(M.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int M1 = Integer.parseInt(M);
		cipan.setM(M1);
		System.out.println(M1);

		String Now = textField_1.getText();
		if(Now.equals("")) {
			JOptionPane.showConfirmDialog(this, "你还没有输入完成哦");
		}
		int Now1 = Integer.parseInt(Now);
		cipan.setNow(Now1);
		System.out.println(Now1);
		
		String Choice = textField_3.getText();
		if(!Choice.equals("")) {
			int Choice1 = Integer.parseInt(Choice);
			cipan.setChoice(Choice1);
		}
		
		String S = String.valueOf(textField_2.getText());	//拿到N磁道号
		System.out.println(S);
		int[] a = gain(S, M1);		    //构造函数来接收N个磁道号
		
		for (int i = 0; i < M1; i++) {	//测试接收到的数据
			System.out.print(a[i]+" ");
		}
		System.out.println();
		
		cipan.setCidao(a);
		String s = cipan.CSCAN();		//把数据送入函数再复制给s
		System.out.println(s);
		System.out.println(cipan.getNow());
		textArea.setText(Now+s); //结果在textArea位置显示
		textArea_1.setText(Integer.toString(cipan.Sum[3]));

	}

	// 获取输入的N个数，存放再int型数组中
	public int[] gain(String s, int n) {
		int index1 = 0;
		int index2 = 0;
		int[] a = new int[n];
		int i = 0;
		index2 = s.indexOf(" ", index1);
		while (index2 != -1 && i < n) {
			//substring(int begin,int end) 截取从begin到end-1位置的元素
			String temps = s.substring(index1, index2);	
			a[i] = Integer.valueOf(temps);
			i++;
			index1 = index2 + 1;
			index2 = s.indexOf(" ", index1);
		}
		return a;
	}
}
