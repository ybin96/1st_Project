package first_project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Page_1_LogIn01 extends JFrame {
	JLabel jlb_logo;
	JLabel jlb_id;
	JTextField jtf_id;
	JLabel jlb_pwd;
	JPasswordField jpf_pwd;
	static int login_eno; //로그인한 회원 eno
	static String ip = "";	//아이피주소
	
	public static void ip() {
		
		try {
			InetAddress local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();
			System.out.println(ip);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int LogIn() {	//로그인 결과 정보 숫자로 반환하는 메소드
		
		String jtf_id_in = jtf_id.getText();
		String jpf_pwd_in = String.valueOf(jpf_pwd.getPassword());
		
		CompanyDAO dao = new CompanyDAO();
		
		ArrayList<CompanyVO> list = dao.loginEmp(Integer.parseInt(jtf_id_in));
		
		if (list.size() == 0) {
			System.out.println("없는사람임");
			return 2;
		}
		for(CompanyVO test:list) {
			if( Integer.toString(test.getPwd()).equals(jpf_pwd_in)) {
				login_eno = Integer.parseInt(jtf_id_in);
				return 0;	//로그인 성공
			}else{
				return 1;//비밀번호 틀림
			}
		}
		return 3;
				
	
	}
	
	public Page_1_LogIn01() {
		getContentPane().setLayout(new BorderLayout());
		ip();
		JPanel p_logo = new JPanel();
		ImageIcon icon_logo = new ImageIcon("favicon.png");	//로고 이미지 삽입
		jlb_logo = new JLabel(icon_logo);
		p_logo.add(jlb_logo,BorderLayout.CENTER);
		
		getContentPane().add(p_logo,BorderLayout.NORTH);
		
		JPanel p_login = new JPanel();
		
		jlb_id = new JLabel("    ID     :   ");
		p_login.setLayout(null);
		
		JPanel p_login_west = new JPanel();
		p_login_west.setBounds(82, 110, 63, 30);
		p_login_west.setLayout(new GridLayout(2,1));
		p_login_west.add(jlb_id);
		
		
		p_login.add(p_login_west);
		
		JPanel p_login_east = new JPanel();
		p_login_east.setBounds(123, 51, 226, 42);
		p_login_east.setLayout(new GridLayout(2,1));
		
		p_login.add(p_login_west,BorderLayout.WEST);
		p_login.add(p_login_east);
		
		getContentPane().add(p_login,BorderLayout.CENTER);
		jlb_pwd = new JLabel("  PWD  :   ");
		jlb_pwd.setBounds(95, 150, 63, 15);
		p_login.add(jlb_pwd);
		jpf_pwd = new JPasswordField(20);
		jpf_pwd.setBounds(163, 147, 226, 21);
		p_login.add(jpf_pwd);
		jpf_pwd.setEchoChar('*');
		jtf_id = new JTextField(20);
		jtf_id.setBounds(163, 110, 226, 21);
		p_login.add(jtf_id);
		
		JPanel p_btn = new JPanel();
		JButton btn_login = new JButton("로그인");
		p_btn.setLayout(new BorderLayout());
		p_btn.add(btn_login,BorderLayout.CENTER);
		getContentPane().add(p_btn,BorderLayout.SOUTH);
		
		setTitle("로그인");
		setLocation(600, 200);	//실행시 나타날 위치
		setResizable(false);	//크기 조정 불가능
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				LogIn();
				if (LogIn() == 0) {
					System.out.println("로그인 성공");
					setVisible(false);
					new Page_2_Menu();
					if((login_eno+"").charAt(0)=='1')
					{
						JOptionPane.showMessageDialog(null, "관리자 계정으로 접속하셨습니다.");
						
					}
				}else if (LogIn() == 1) {
					System.out.println("비밀번호 틀림");
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
				}else if (LogIn() == 2) {
					System.out.println("아이디 없음");
					JOptionPane.showMessageDialog(null, "올바르지 않은 아이디 입니다.");
				}else {
					System.out.println("오류발생");
				}
				
				
			}
		});
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Page_1_LogIn01();
	}

}