import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class mychat implements Runnable{
    public JTextField tx;
    public JTextArea ta;
    public String login="Imed";
    BufferedWriter writer;
    BufferedReader reader;
    public mychat(String l){
        login=l;        
        
        JFrame f=new JFrame(l);
        f.setSize(400,400);        
        
        JPanel p1=new JPanel();
        p1.setLayout(new BorderLayout());
            
        JPanel p2=new JPanel();
        p2.setLayout(new BorderLayout());        
        
        tx=new JTextField();
        p1.add(tx, BorderLayout.CENTER);
        
        JButton b1=new JButton("Send");
        p1.add(b1, BorderLayout.EAST); 
        
        ta=new JTextArea();
        p2.add(ta, BorderLayout.CENTER);
        p2.add(p1, BorderLayout.SOUTH);
        
        f.setContentPane(p2);
           
 
           
        try{
                 Socket socketClient= new Socket("localhost",5555);
                 writer= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                 
                 reader =new BufferedReader(new InputStreamReader(socketClient.getInputStream(), "UTF8"));
                 
 
			
		    		
        }catch(Exception e){e.printStackTrace();}
    
        tx.addKeyListener(new KeyAdapter() {
        	 @Override
             public void keyPressed(KeyEvent e) {
        		 if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			 send();
        		 }
        	 }
        });
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                send();
            }
          }
        );
        
        f.setVisible(true);    
        
 
    }
    public void send() {
    	String s=login+" : "+tx.getText();  
        tx.setText("");
        try{
            writer.write(s);
            writer.write("\r\n");
            writer.flush(); 
            }catch(Exception e){e.printStackTrace();}
    }
    public void run(){
             try{
                String serverMsg=""; 
                while((serverMsg = reader.readLine()) != null){
                    System.out.println("from server: " + serverMsg);
                    ta.append(serverMsg+"\n");
                }
        }catch(Exception e){e.printStackTrace();}   
    }
}