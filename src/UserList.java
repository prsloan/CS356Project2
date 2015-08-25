
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;


public class UserList extends JPanel implements TreeSelectionListener{

	
/**
	 * 
	 */
	private static final long serialVersionUID = -5460531975773475433L;
	private static final UserList USERLISTINSTANCE = new UserList();
	private JTree userTree;
	private DefaultTreeModel treeModel;
	private UserGroup root = UserGroup.newUserGroup("Root");
	private User currentSelection;
	
	
public static UserList getInstance(){
	return USERLISTINSTANCE;
	
}

private UserList(){
	super(new GridLayout(1,0));
	

	//create tree
	treeModel = new DefaultTreeModel(root);
	userTree = new JTree(treeModel);
	userTree.setEditable(true);
	userTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	
	
	//add the listener
	userTree.addTreeSelectionListener(this);
	userTree.setRootVisible(true);
	
	 //Create the scroll pane and add the tree to it. 
    JScrollPane treeView = new JScrollPane(userTree);
	
	add(treeView);
}


public UserGroup getRoot(){
	return root;
}


public individualUser getCurrentSelection(){
	return (individualUser) currentSelection;
}


public void addUser(String input){
	if (currentSelection == null){
		JOptionPane.showMessageDialog(this, "NO GROUP SELECTED! TRY AGAIN!", "ERROR!", JOptionPane.ERROR_MESSAGE);
	}
	else if (currentSelection instanceof individualUser){
		JOptionPane.showMessageDialog(this, "NO GROUP SELECTED! TRY AGAIN!", "ERROR!", JOptionPane.ERROR_MESSAGE);
	}
	else{
	User newUser = individualUser.newUser(input);
	treeModel.insertNodeInto((DefaultMutableTreeNode)newUser, (DefaultMutableTreeNode)currentSelection, 0);
	}
}

public void addGroup(String input){
	User newGroup = UserGroup.newUserGroup(input);
	treeModel.insertNodeInto((DefaultMutableTreeNode)newGroup, (DefaultMutableTreeNode)currentSelection, 0);
}


public individualUser getUserById(String id) throws NullPointerException{
	Enumeration<?> list = root.preorderEnumeration();
	Object cursor;
	individualUser temp;
	
	while (list.hasMoreElements()){
		cursor = list.nextElement();
		if (cursor instanceof individualUser){
			temp = (individualUser) cursor;
			if (temp.toString().equals(id)){
				return  (individualUser) cursor;
				}
		}
		
	}
	
	throw new NullPointerException(" No User With that ID Found");
}


@Override
public void valueChanged(TreeSelectionEvent e) {
	DefaultMutableTreeNode node =  (DefaultMutableTreeNode)
            userTree.getLastSelectedPathComponent();
	
	currentSelection = (User) node;
	
}
	
	
	
}
