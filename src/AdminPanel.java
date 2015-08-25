import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class AdminPanel extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static final AdminPanel INSTANCE = new AdminPanel();
	private JPanel content = new JPanel();
	
	
	
	public static AdminPanel getInstance(){
		return INSTANCE;
	}
	
	private AdminPanel(){
		super("Admin User Panel");
		content.setLayout(new GridLayout(0,2));
		content.add(UserList.getInstance());
		content.add(AdminControlButtons.getInstance());
		
		this.setContentPane(content);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	

}
