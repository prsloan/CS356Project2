import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class AdminControlButtons extends JPanel implements ActionListener {
	
	private JTextField userIdTextField = new JTextField(10);
	private JButton addUserButton = new JButton("Add User");
	private JTextField groupIdTextField = new JTextField(10);
	private JButton addGroupButton = new JButton("Add Group");
	private JButton openUserViewButton = new JButton (" Open User View ");
	private JButton showUserTotalButton = new JButton ("Show User Total ");
	private JButton showGroupTotalButton = new JButton ("Show Group Total ");
	private JButton showMessagesTotalButton = new JButton (" Show Messages Total ");
	private JButton showPositivePercentage = new JButton (" Show Positive Percentage ");
	GridBagConstraints c = new GridBagConstraints();
	
	//statistics
	private int numberOfUsers;
	private int numberOfGroups;
	private int numberOfMessages;
	private int positiveMessages;
	private float percentage;
	
	
	private static final AdminControlButtons BUTTONS = new AdminControlButtons();
	
	public static AdminControlButtons getInstance(){
		return BUTTONS;
	}
	
	private AdminControlButtons(){
		super(new GridBagLayout());
		//initialze counters
		numberOfUsers = 0;
		numberOfGroups = 1;
		numberOfMessages = 0;
		positiveMessages = 0;
		percentage = 0;
		
		//set up layout
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		add(userIdTextField, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.0;
		add(addUserButton, c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		add(groupIdTextField, c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.0;
		add(addGroupButton, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		add(openUserViewButton, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1 ;
		add(showUserTotalButton, c);
		c.gridx = 1;
		c.gridy = 4;
		add(showGroupTotalButton, c);
		c.gridx = 0;
		c.gridy = 5;
		add(showMessagesTotalButton, c);
		c.gridx = 1;
		c.gridy = 5;
		add(showPositivePercentage, c);
		
		
		initializeButtons();
		
	}
	
	private void initializeButtons(){
		addUserButton.addActionListener(this);
		addGroupButton.addActionListener(this);
		openUserViewButton.addActionListener(this);
		showUserTotalButton.addActionListener(this);
		showGroupTotalButton.addActionListener(this);
		showMessagesTotalButton.addActionListener(this);
		showPositivePercentage.addActionListener(this);
		
	}
	
	/**
	 * a method to call whenever a message gets added to a news feed
	 */
	public void incrementMessages(){
		numberOfMessages++;
	}
	
	public void incrementPositiveMessages(){
		positiveMessages++;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource().equals(addUserButton)){
			UserList.getInstance().addUser(userIdTextField.getText());
			numberOfUsers++;
		}
		else if (event.getSource().equals(addGroupButton)){
			UserList.getInstance().addGroup(groupIdTextField.getText());
			numberOfGroups++;
		}
		else if (event.getSource().equals(openUserViewButton)){
			@SuppressWarnings("unused")
			UserView newView = new UserView(UserList.getInstance().getCurrentSelection()); 
		}
		else if (event.getSource().equals(showUserTotalButton)){
			JOptionPane.showMessageDialog(this, " The total number of Users is : " + numberOfUsers , "Total Users", JOptionPane.PLAIN_MESSAGE);
		}
		else if (event.getSource().equals(showGroupTotalButton)){
			JOptionPane.showMessageDialog(this, " The total number of Groups is : " + numberOfGroups , "Total Groups", JOptionPane.PLAIN_MESSAGE);
		}
		else if (event.getSource().equals(showMessagesTotalButton)){
			JOptionPane.showMessageDialog(this, " The total number of Messages is :" + numberOfMessages , "Total Messages", JOptionPane.PLAIN_MESSAGE);
		}
		else if (event.getSource().equals(showPositivePercentage)){
			if(numberOfMessages > 0){
				 percentage = ((float)positiveMessages/(float)numberOfMessages)*100;
				}
			JOptionPane.showMessageDialog(this, " The percentage of Positive Messages is : " + percentage + " % "  , "Positive Percentage", JOptionPane.PLAIN_MESSAGE);
		}
		UserList.getInstance().repaint();
		AdminPanel.getInstance().repaint();
	}

}
