import javax.swing.JOptionPane;
import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {
        int inp=JOptionPane.showConfirmDialog(null,"Do you want to host the chat?\nYes - Act as server\nNo - Act as client","Want to host a chat?",JOptionPane.YES_NO_OPTION);
        if(inp==0) {
            new TwoChat(true,null).setVisible(true);
        }
        else {
            String ipstring=JOptionPane.showInputDialog("Please enter the ip address");
            try{
                InetAddress.getByName(ipstring);
                new TwoChat(false,ipstring).setVisible(true);
            }catch(Exception e){JOptionPane.showMessageDialog(null,"Invalid or Unreachable IP");}
        }
    }
}
