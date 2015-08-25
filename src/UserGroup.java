
import javax.swing.tree.DefaultMutableTreeNode;



@SuppressWarnings("serial")
public class UserGroup extends DefaultMutableTreeNode implements User{

private String groupId;
	
	public static UserGroup newUserGroup(String id){
		UserGroup tempGroup = new UserGroup();
		tempGroup.setId(id);
		tempGroup.setAllowsChildren(true);
		
		return tempGroup;
	}
	
	private UserGroup(){
		super();
	}


	public void setId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public String getId() {
		return groupId;
	}
	
	@Override 
	public String toString(){
		StringBuilder temp = new StringBuilder("<html><b>");
		temp.append(groupId + "</b></html>");
		return temp.toString();
	}

	
}
