package jframe.date;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DateConvert {
	public static void main(String[] args) throws Exception {

		MyButton button = new MyButton();
		button.mytest01();
	}
}

class MyButton extends Frame implements ActionListener {
	// Frame f = new Frame("my awt");
	Panel panel1 = new Panel();
	Panel panel2 = new Panel();
	Panel panel3 = new Panel();

	TextField tf = new TextField(30); // 创建一个文本框
	TextField tfResult = new TextField(30); // 创建一个文本框
	Button b = new Button("Convert");// 创建一个Button
	Label l = new Label();
	Label l1 = new Label();// 记录上一次转的数
	Label lnew = new Label();//记录新值
	

	String lastvalue = "last value:";
	ArrayList<String> list = new ArrayList<String>();

	public void mytest01() throws HeadlessException {
		this.setSize(350, 180);
		this.setLocation(300, 200);
		// FlowLayout layout = new FlowLayout();
		// layout.setAlignment(FlowLayout.LEFT);
		// layout.setHgap(FlowLayout.LEFT);
		this.setLayout(new GridLayout(3, 2));
		l.setBackground(Color.red);

		panel1.add(tf);
		panel1.add(b);
		
		//panel2.add(l);
		panel2.add(tfResult);
		panel3.add(l1);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);

		//默认text中为当前日期
		Date now=new Date();
		SimpleDateFormat sf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String nowTime=sf.format(now);
		tf.setText(nowTime);
		
		//结果区为
		tfResult.setBackground(Color.red);
		tfResult.setText("result will show this");
		// 默认加载显示lable为
		l.setText("result show this area");
		l1.setText(lastvalue + "                                            ");
		this.addWindowListener(new MyWin());
		b.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == b) {
				String str = tf.getText();
				l1.setText("last value:"+lnew.getText());//得到文本框中的旧值
				lnew.setText(str);//将文本框中的值赋给新label
				int index = str.indexOf("/");
				int index1 = str.indexOf("-");
				long slong = 0;
				if (index <= 0 && index1 <= 0) {
					slong = Long.parseLong(str);
				} else {
					slong = -1;
				}
				if (str == "") {
					l.setText("请输入数字!");
				} else if (slong > 0) {
					// 这个是将数字转换为日期
					SimpleDateFormat sf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date dateS = new Date(slong);
					//l.setText(sf.format(dateS));
					tfResult.setText(sf.format(dateS));
					System.out.println(sf.format(dateS));

				} else if (slong == -1) {
					// 将数字转化为时间
					SimpleDateFormat sf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date TodaySDate = sf.parse(str);
					long timeSToday = TodaySDate.getTime();
					//l.setText(Long.toString(timeSToday));
					tfResult.setText(Long.toString(timeSToday));
					System.out.println(Long.toString(timeSToday));
				}
			} else {
				System.out.println("退出");
			}
		} catch (Exception e2) {
			l.setText("throw exception");
		}
	}
}

class MyWin extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("Window closing"+e.toString());
		System.out.println("我关了");
		System.exit(0);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// 每次获得焦点 就会触发
		System.out.println("我活了");
		// super.windowActivated(e);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("我开了");
		// super.windowOpened(e);
	}

}
