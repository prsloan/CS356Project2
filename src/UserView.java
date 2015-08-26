import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UserView extends JFrame implements Observer, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dimension preferredSize = new Dimension(300,550);
	
	private individualUser currentUser;
	
	//The components of the frame
	private JTextField userId = new JTextField(12);
	private JButton followUser = new JButton("Follow User");
	private JTextField tweetMessage = new JTextField(12);
	private JButton postTweet =new JButton(" Post Tweet ");
	@SuppressWarnings("rawtypes")
	private JList following ;
	private JTextArea newsFeed ;
	private JScrollPane newsFeedScroll;
	private JPanel panel = new JPanel();
	private JLabel currentlyFollowing = new JLabel("Currently Following :");
	private JLabel newsLabel = new JLabel(" News Feed :");
	
	/**
	 *  The constructor of the User view will construct a frame that is tied to the input user
	 *  based.
	 * @param currentUser
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserView(individualUser currentUser){
		super("User View of " + currentUser.getId());
		this.currentUser = currentUser;
		this.setPreferredSize(preferredSize);
		
		
		following = new JList(currentUser.getFollowing());
		newsFeed = new JTextArea();
		newsFeedScroll = new JScrollPane(newsFeed);
		
		this.updateNews();
		this.initializeFollowing();
		this.populateFrame();
		this.addListeners();
		
		this.setContentPane(panel);
		this.pack();
		this.setResizable(false); //keep it this size
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	/**
	 * When the window opens, it must set itself to observe the people the user associated with the window are observing.
	 */
	private void initializeFollowing() {
		Object[]  toFollow =  currentUser.getFollowing();
		
		for (Object o : toFollow){
			UserList.getInstance().getUserById(o.toString()).addObserver(this);
		}
		
	}

	
	
	/**
	 * This method pulls the news feed from the user the window is associated with
	 */
	private void updateNews() {
		
		Object[] news = currentUser.getNews();
		
		for(Object s : news)
			newsFeed.append(s.toString());
		
	}

	private void addListeners() {
		followUser.addActionListener(this);
		postTweet.addActionListener(this);
	}

	/**
	 * This method fills the panel with the various composite components of the window
	 */
	private void populateFrame() {
		newsFeedScroll.setPreferredSize(new Dimension(280, 280));
		following.setPreferredSize(new Dimension(200, 100));
		
		panel.add(userId);
		panel.add(followUser);
		panel.add(currentlyFollowing);
		panel.add(new JScrollPane(following));
		panel.add(tweetMessage);
		panel.add(postTweet);
		panel.add(newsLabel);
		panel.add(newsFeedScroll);
		
	}

	@Override
	/**
	 * Updates the text area while the window is in existence.
	 */
	public void update(Observable source, Object arg) {
		individualUser updatedUser = (individualUser) source ;
		StringBuilder newTweet = new StringBuilder();
		
		newTweet.append(updatedUser.getId() + " : ");
		newTweet.append(arg.toString() + "\n");
		
		newsFeed.append(newTweet.toString());
		
		
		newsFeed.repaint();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	
	/**
	 * This Method handles the operations to be performed when buttons are pushed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(followUser)){
			try{
			currentUser.followUser(UserList.getInstance().getUserById(userId.getText())); //add the user this window is associated with to the observers
			UserList.getInstance().getUserById(userId.getText()).addObserver(this);  //adds this window to the list of observers of the user
			}
			catch (NullPointerException exception){
				JOptionPane.showMessageDialog(this, exception.getMessage());
			}
			
			following.setListData( currentUser.getFollowing());
			following.repaint();
		}
		else if (e.getSource().equals(postTweet)){
			currentUser.postTweet(tweetMessage.getText());
		}
		
	}
	
	
}
