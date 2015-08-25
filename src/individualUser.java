
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;



public class individualUser extends DefaultMutableTreeNode implements User, Observer, Observable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private ArrayList<Observer> followers;
	private ArrayList<individualUser> following;
	private ArrayList<String> newsFeed;
	private ArrayList<String> postedTweets;
 	
	public static individualUser newUser(String id){
		individualUser tempUser = new individualUser();
		tempUser.setId(id);
		tempUser.setAllowsChildren(false);
		
		return tempUser;
	}
	
	private individualUser(){
		super();
		followers = new ArrayList<Observer>();
		following = new ArrayList<individualUser>();
		newsFeed = new ArrayList<String>();
		postedTweets = new ArrayList<String>();
	}
	
	@Override
	public String getId() {
		return userId;
	}

	@Override
	public void setId(String userId) {
		
		this.userId = userId; 
	}
	
	
	

	
	public void followUser(individualUser userToFollow){
		userToFollow.addObserver(this);
		following.add(userToFollow);
		
		
	}
	
	
	public void postTweet(String tweet){
		postedTweets.add(tweet);
		this.notifyObservers(tweet);
		
	}
	
	
	public Object[] getFollowing(){
		return  following.toArray();
	}
	
	public Object[] getNews(){
		return  newsFeed.toArray();
	}
	
	
	
	
	@Override
	public void notifyObservers(Object arg) {
		for(Observer i: followers){
			i.update(this, arg);
		}
		
	}

	@Override
	public void addObserver(Observer o) {
		followers.add( o);
	}

	@Override
	public void removeObserver(Observer o) {
		followers.remove(o);
	}

	@Override
	public void update(Observable source, Object arg) {
		AdminControlButtons.getInstance().incrementMessages();
		if ( PositiveWords.INSTANCE.isPositive(arg.toString())){
			AdminControlButtons.getInstance().incrementPositiveMessages();
		}
		StringBuilder news = new StringBuilder();
		
		news.append(source.toString() + " : ");
		news.append(arg.toString() + "\n");
		
		newsFeed.add(news.toString());
		
	}

	@Override 
	public String toString(){
		return userId;
	}	
	

	
}
