import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Client {
    JFrame main;
    JTextField txtUser;
    JButton btnDangNhap, btnThoat;
    mychat c = null;
    public Client(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        main = new JFrame("Đăng Nhập");
        main.setBounds((int) width/2-250,(int)height/2-200,500,400);
        main.setResizable(false);
        main.setLayout(null);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Chat nhóm");
        label.setFont(new Font("Courier New",Font.BOLD,25));
        label.setBounds(170,20,400,50);
        main.add(label);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null,"Viết tên đăng nhập"));
        panel.setBounds(50,100,400,150);
        panel.setLayout(null);
        JLabel label1 = new JLabel("Tên người dùng:");
        label1.setBounds(10,40,150,20);
        panel.add(label1);
        txtUser = new JTextField();
        txtUser.setBounds(170,40,200,20);
        panel.add(txtUser);
        main.add(panel);

        btnDangNhap = new JButton("Đăng Nhập");
        btnDangNhap.setBounds(80,260,120,60);
        btnThoat = new JButton("Thoát");
        btnThoat.setBounds(280,260,120,60);
        main.add(btnDangNhap);
        main.add(btnThoat);

        txtUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    DangNhap();
                }
            }
        });
        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DangNhap();
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    void DangNhap(){
    	try{       	        
    		c = new mychat(txtUser.getText());   
            Thread t1=new Thread(c);
            t1.start();
            main.dispose();
	    }catch(Exception e){e.printStackTrace();}
    }
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Client form = new Client();
            }
        });
    }
}