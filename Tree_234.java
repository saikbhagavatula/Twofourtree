package tree_234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


class Node {
	private static final int ORDER = 4;
	private int numItems;
	private Node parent;
	private Node childArray[] = new Node[ORDER];
	private NodeData itemArray[] = new NodeData[ORDER - 1];

	// add child to this node
	public void addChild(int childNum, Node child) {
		childArray[childNum] = child;
		if (child != null)
			child.parent = this;
	}

	// remove child from this node, return it
	public Node removeChild(int childNum) {
		Node tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	public Node getChild(int childNum) {
		return childArray[childNum];
	}

	public Node getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return (childArray[0] == null) ? true : false;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int theValue) {
		numItems = theValue;
		return;
	}

	public NodeData getItem(int index) // get index data
	{
		return itemArray[index];
	}

	public NodeData setItem(int index, NodeData theValue) 
	{
		itemArray[index] = theValue;
		return itemArray[index];
	}

	public boolean isFull() {
		return (numItems == ORDER - 1) ? true : false;
	}

	public int insertItem(NodeData newItem) {
		// node is not full
		numItems++; // add new item
		int newKey = newItem.dData; // new item key

		

		for (int j = ORDER - 2; j >= 0; j--) // start on right,
		{ // check items
			if (itemArray[j] == null) // if null,
				continue; // go left 
			else // not null,
			{ // get its key
				int itsKey = itemArray[j].dData;
				if (newKey < itsKey) // if bigger
					itemArray[j + 1] = itemArray[j]; // shift right
				else {
					itemArray[j + 1] = newItem; // insert new item
					return j + 1; // return index to
				} // new item
			} // end else (not null)
		} // end for // shifted all items,
		itemArray[0] = newItem; // insert new item
		return 0;
	} // end insertItem()

	public void insertatfront(NodeData newItem) {

		int newKey = newItem.dData; // key of new item
		numItems++;
		for (int j = numItems - 1; j > 0; j--) {
			itemArray[j] = itemArray[j - 1];
			addChild(j + 1, removeChild(j));
		}
		addChild(1, removeChild(0));
		itemArray[0] = newItem;
		addChild(0, null);

		return;
	}

	public NodeData removeItem() 
	{
		// assumes node not empty
		NodeData temp = itemArray[numItems - 1]; // store item
		itemArray[numItems - 1] = null; // remove it
		numItems--;
		return temp; // return item
	}

	public void displayNode() 
	{
		for (int j = 0; j < numItems; j++)
			itemArray[j].showValue(); 
		// System.out.println();
	}

	public void displayvalue(int j) 
	{
		itemArray[j].showValue(); 
			}

	public void deletenodevalue(int theValue) {
		// Only valid for leafs
		int flag = -1;
		// delete the value and decrease node size
		for (int i = 0; i < numItems; i++) {
			if (theValue == itemArray[i].dData) {
				flag = i;
			}
			if (flag != -1 && i + 1 < numItems) {
				itemArray[i].dData = itemArray[i + 1].dData;
			}
		}
		itemArray[numItems - 1] = null; // disconnect it
		numItems--; // one less item

	}

	public void deletevalue(int theValue, String side) {
		// Only valid for leafs
		int flag = -1;
		// delete the value and decrease node size
		for (int i = 0; i < numItems; i++) {
			if (theValue == itemArray[i].dData) {
				flag = i;
			}
			if (flag != -1 && i + 1 < numItems) {
				itemArray[i].dData = itemArray[i + 1].dData;
			}
		}
		itemArray[numItems - 1] = null; // disconnect it
		numItems--; // one less item

	}

	public Node getsibiling(int theValue) {
		// get the sibling
		Node x = null;
		Node p = getParent();
		if (numItems != 0) {
			for (int i = 0; i <= p.numItems; i++) {
				if (p.childArray[i].itemArray[0].dData < theValue) {
					
					x = p.childArray[i];
				}
			}
		} else if (numItems == 0) {
			for (int i = 0; i <= p.numItems; i++) {
				if (p.childArray[i].itemArray[0] == null) {
					
					if (i != 0) {
						x = p.childArray[i - 1];
					}
				}
			}
		}
		return x;
	}

} // Node Class Ends

class NodeData {
	public int dData; // one data item

	public NodeData(int dd) // constructor
	{
		dData = dd;
	}

	public void showValue() 
	{
		
		System.out.print(dData+",");
	}

}  // Node Data Class Ends

class project_1 {

	public static void main(String[] args) {
		_234 theTree = new _234();
		int n,value;
		Random random = new Random();
		System.out.println("\t Algos Project 2 !!");
		while (true) {
			try {
				System.out.println("1. Insert n Integers\n"
						+ "2. Operations with defined probability (Would Perform 2n Operations)\n3. Print Tree In Order"
						+ "\n4. View Tree level by level"
						+ "\n5. Insert a new Value"
						+ "\n6. Delete a value"
						+ "\n7. Exit Program");
				char choice = getChar();
				switch (choice) {
				case '1':
					System.out.println("Value of n");
					n = getInt();
					for (int a = 1; a < n+1; a++) {
						Node present = theTree.find(a);
						if (present != null){
							
						}else{
							theTree.insert(a);
						}
					}
					break;
				case '2':
					System.out.println("Value of n :");
					n = getInt();
					long startTime, endTime;
					int operation = 0;
					int searchCount = 0;
					int insertCount = 0;
					int deleteCount = 0;
					startTime = System.nanoTime();
					while (operation < (2 * n)) {
						double rand = random.nextDouble();
						if (rand < 0.4) {
							// insert operation runs at 0.4 probability
							int x = random.nextInt(n);
							Node present = theTree.find(x);
							if (present != null) {
								
							} else {
								theTree.insert(x);
							}
							operation++;
							insertCount++;
							
						}
						rand = random.nextDouble();
						if (rand < 0.25) { // delete operation runs at 0.25 probability
							int x = random.nextInt(n);
							Node del = theTree.find(x);
							if (del != null) {
								if (theTree.delete(del, x) != null)
								{
								}else {
									
								}
							} else
								
							operation++;
							deleteCount++;
							
						}
						rand = random.nextDouble();
						if (rand < 0.35) { 
							Node found = theTree.find(random.nextInt(n));
							
							operation++;
							searchCount++;
							
						}
					}
					endTime = System.nanoTime() - startTime;
					System.out.println("Total Operations :");
					System.out.println("Search operations performed :"
							+ searchCount);
					System.out.println("Delete operations performed :"
							+ deleteCount);
					System.out.println("Insert operations performed :"
							+ insertCount);
					System.out.println("Total Time Taken:" + endTime + " nanoseconds");
					break;
				case '3':
					System.out.println("In Order: ");
					theTree.displayTree(1);
					break;
				case '4':
					System.out.println("Tree:");
					theTree.displayTree(0);
					break;
				case '5':
					System.out.println(" Value to be inserted: ");
					value = getInt();
					Node present = theTree.find(value);
					if (present != null){
						System.out.println("Duplicate Value not Allowed!!");
					}else{
						theTree.insert(value);
					}
					break;
				case '6' :
					System.out.println("Value to be deleted: ");
					value = getInt();
					Node del = theTree.find(value);
					if (del != null)
						{
							if(theTree.delete(del,value)!=null)
								{
								 System.out.println("Value Deleted:" + value);
								
							    System.out.println("\n Tree after deleting" + value);
							    theTree.displayTree(0); // Prints the new balanced tree
								}
							else{
								System.err.println("Value not Deleted!!!");
							}
						}
					else
						System.out.println("Could not find " + value);
					
					break;
				case '7' :
					System.out.println("Completed..");
					System.exit(0);
				default:
					System.out.println("Invalid input\n");
				}
			} catch (Exception e) {
				System.err.println("Please enter valid input");
			}
		}

	}
	
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}  // Project_1 class ends

class _234 {
	private Node root = new Node(); // make root node

	// insert a NodeData
	public void insert(int dValue)

	// Performs the splits in root -> leaf fashion.

	{
		Node curNode = root;
		NodeData tempItem = new NodeData(dValue);

		while (true) {
			if (curNode.isFull()) // if node full,
			{
				split(curNode); // split it
				curNode = curNode.getParent(); 
				curNode = getNextChild(curNode, dValue);
			} // node is full

			else if (curNode.isLeaf()) // if node is leaf,
				break; // go insert
			// node is not full, not a leaf; so go to lower level
			else
				curNode = getNextChild(curNode, dValue);
		} // end while

		curNode.insertItem(tempItem); // insert new NodeData
	} // end insert()

	public void split(Node thisNode) // split the node
	{
		// assumes node is full
		NodeData itemB, itemC;
		Node parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem(); // remove items from
		itemB = thisNode.removeItem(); // this node
		child2 = thisNode.removeChild(2); // remove children
		child3 = thisNode.removeChild(3); // from this node

		Node newRight = new Node(); // make new node

		if (thisNode == root) // if this is the root,
		{
			root = new Node(); // make new root
			parent = root; // root is our parent
			root.addChild(0, thisNode); // connect to parent
		} else
			// node is not the root
			parent = thisNode.getParent(); // get parent

		// handle parent
		itemIndex = parent.insertItem(itemB); 
		int n = parent.getNumItems(); // total values

		for (int j = n - 1; j > itemIndex; j--) // move parent and its connections
		{ 
			Node temp = parent.removeChild(j); // one child
			parent.addChild(j + 1, temp); // to the right
		}
		// connect newRight to parent
		parent.addChild(itemIndex + 1, newRight);

		// handle newRight
		newRight.insertItem(itemC); // item C to newRight
		newRight.addChild(0, child2); // connect to 0 and 1
		newRight.addChild(1, child3); // on newRight
	} // end split()

	// gets appropriate child of node during search for value
	public Node getNextChild(Node theNode, int theValue) {

		

		int j;
		
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) // for each value in node
		{ 
			if (theValue < theNode.getItem(j).dData)
				return theNode.getChild(j); // return left child
		} 
		return theNode.getChild(j); // return right child
	}

	public void displayTree(int i) {
		if (i == 0) {
			recDisplayTree(root, 0, 0);
		} else
			inorderdisplay(root, 0, 0);
		System.out.println();
	}

	private void recDisplayTree(Node thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + " ");
		thisNode.displayNode(); 
		System.out.println();
		
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node nextNode = thisNode.getChild(j);
			if (nextNode != null)
				recDisplayTree(nextNode, level + 1, j);
			else
				return;
		}
	} // end recDisplayTree()

	public void inorderdisplay(Node thisNode, int level, int childNumber) {
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node nextNode = thisNode.getChild(j);
			if (nextNode != null)
				inorderdisplay(nextNode, level + 1, j);
			else {
				thisNode.displayNode();
				return;
			}
			if (j < thisNode.getNumItems())
				thisNode.displayvalue(j);
		}
	}

	public Node find(int theValue) {
		return findvalue(root, theValue);
	}

	public Node findvalue(Node theNode, int theValue) {

		Node l = null;
		
		int numItems = theNode.getNumItems();
		
		for (int j = 0; j < numItems; j++) 
		{ 
			if (theValue == theNode.getItem(j).dData) {
				l = theNode;
				break;
			} else if (theValue < theNode.getItem(j).dData && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j), theValue); // left child
																
				break;
			} else if (theValue > theNode.getItem(j).dData && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j + 1), theValue); //right child

			}
		} // end for 
		return l;
	}

	public Node delete(Node currnode, int theValue) {
		Node y = null;
		
		if (currnode.isLeaf()) {
			if (currnode.getNumItems() > 1) {
				currnode.deletenodevalue(theValue);
				return currnode;
			} else {
				y = deleteleaf_cases(currnode, theValue);
				return y;
			}
		} else {
			// delete interior nodes
		
			Node n = getNextChild(currnode, theValue);
			Node c = getinordernode(n);
			NodeData d = c.getItem(0);
			int k = d.dData;
			delete(c, d.dData);
					
			Node found = find(theValue);
			for(int i = 0; i < found.getNumItems();i++){
				if(found.getItem(i).dData==theValue){
					found.getItem(i).dData=k;
				}
			}
			return found;
			
		}
	}

	public Node deleteleaf_cases(Node thisNode, int theValue) {
		String sibling_side = "l";
		Node p = thisNode.getParent();
		Node sibling = thisNode.getsibiling(theValue);
		if (sibling == null) {
			sibling_side = "r";
			sibling = p.getChild(1);
		}

		if (sibling.getNumItems() == 1) {
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {


					thisNode.setItem(thisNode.getNumItems() - 1, null);
					thisNode.setNumItems(thisNode.getNumItems() - 1);
					NodeData d = p.getItem(i);
					sibling.insertItem(d);
					p.removeChild(i + 1);
					for (int j = i; j < p.getNumItems(); j++) {
						if (j + 1 < p.getNumItems()) {
							p.setItem(j, p.getItem(j + 1));
							if (j + 2 <= p.getNumItems()) {
								p.addChild(j + 1, p.removeChild(j + 2));
							}
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}

					return p;

				} else if (p.getChild(i) == sibling && sibling_side == "r") {


					thisNode.setItem(thisNode.getNumItems() - 1, null);
					thisNode.setNumItems(thisNode.getNumItems() - 1);
					NodeData d = p.getItem(i - 1);
					sibling.insertItem(d);
					p.removeChild(0);
					p.addChild(0, p.removeChild(1));

					for (int j = i; j < p.getNumItems(); j++) {
						p.setItem(j - 1, p.getItem(j));
						if (j + 1 <= p.getNumItems()) {
							p.addChild(j, p.removeChild(j + 1));
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;

				}
			}
		} else if (sibling.getNumItems() > 1) {
			int f = 0;
			if (sibling_side == "r") {
				f = 0;
			} else {
				f = sibling.getNumItems() - 1;
			}

			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {
					thisNode.getItem(0).dData = p.getItem(i).dData;
					p.getItem(i).dData = sibling.getItem(f).dData;
					sibling.deletenodevalue(sibling.getItem(f).dData);
					return p;
				}

				if (p.getChild(i) == sibling && sibling_side == "r") {
					thisNode.getItem(0).dData = p.getItem(i - 1).dData;
					p.getItem(i - 1).dData = sibling.getItem(f).dData;
					sibling.deletenodevalue(sibling.getItem(f).dData);
					return p;
				}
			}
		}

		return null;
	}

	public Node balancetree(Node currnode) { // Argument is empty node.
		String sibling_side = "l";
		Node p = currnode.getParent();
		Node sibling = currnode.getsibiling(-1);
		if (sibling == null) {
			sibling_side = "r";
			sibling = p.getChild(1);
		}

		if (sibling.getNumItems() == 1) {
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {

					NodeData d = p.getItem(i);
					sibling.insertItem(d);
					
					sibling.addChild(sibling.getNumItems(),
							currnode.removeChild(0));
					p.removeChild(i + 1);
					for (int j = i; j < p.getNumItems(); j++) {
						if (j + 1 < p.getNumItems()) {
							p.setItem(j, p.getItem(j + 1));
							if (j + 2 <= p.getNumItems()) {
								p.addChild(j + 1, p.removeChild(j + 2));
							}
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					
					if (p.getNumItems() == 0) {

						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

				else if (p.getChild(i) == sibling && sibling_side == "r") {

					NodeData d = p.getItem(i - 1);
					sibling.insertatfront(d);
					sibling.addChild(0, currnode.removeChild(0));
					p.removeChild(0);
					p.addChild(0, p.removeChild(1));

					for (int j = i; j < p.getNumItems(); j++) {
						p.setItem(j - 1, p.getItem(j));
						if (j + 1 <= p.getNumItems()) {
							p.addChild(j, p.removeChild(j + 1));
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					
					if (p.getNumItems() == 0) {

						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

			}

		} else if (sibling.getNumItems() > 1) {
			int f = 0;
			if (sibling_side == "r") {
				f = 0;
			} else {
				f = sibling.getNumItems() - 1;
			}
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {

					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.addChild(1, currnode.removeChild(0));
					currnode.addChild(0,
							sibling.removeChild(sibling.getNumItems()));
					currnode.setItem(0, p.getItem(i));
					p.setItem(i, sibling.getItem(f));
					sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
					return p;
				}

				if (p.getChild(i) == sibling && sibling_side == "r") {

					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.setItem(0, p.getItem(i - 1));

					p.setItem(i - 1, sibling.getItem(f));

					currnode.addChild(1, sibling.removeChild(f));


					for (int j = 0; j < sibling.getNumItems(); j++) {
						if (j + 1 < sibling.getNumItems()) {
							sibling.setItem(j, sibling.getItem(j + 1));
						}
						sibling.addChild(j, sibling.removeChild(j + 1));
					}
			sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
					
					return p;
				}
			}
		}
		return null;
	}
	
	
	
	public Node getinordernode(Node thisNode){
		Node c = null;
		if(thisNode.isLeaf()){
			c = thisNode;
		}
		else{
			c = getinordernode(thisNode.getChild(0));
		}
		return c;
	}

}// _234 Class ends


