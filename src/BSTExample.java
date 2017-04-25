/**
 * Demo program of the binary search tree
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bridges.connect.Bridges;

public class BSTExample 
{
	public static void main(String[] args) 
	{
		//create the Bridges object
		// Use YOUR API Sha1 key and username
		Bridges<String, String> bridges = new Bridges<>(4, "268629325599", "Aritemis");
		
		//Create elements and insert them into the tree
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		
		
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(50);
		list.add(75);
		list.add(20);
		list.add(10);
		list.add(30);
		list.add(5);
		list.add(15);
		list.add(25);
		list.add(35);
		list.add(3);
		list.add(8);
		list.add(13);
		list.add(28);
		list.add(33);
		list.add(38);
		list.add(85);
		list.add(95);
		
		// iterate through the list and insert the
		// elements into the tree
		Iterator<Integer> itr = list.iterator();
		
		while (itr.hasNext())
		{
			tree.add(itr.next());
		}
		
		// Now perform some operations on the tree
		// i.e. isEmpty(), size(), iterator(), contains(), remove(), 
		
		tree.contains(10);
		tree.remove(20);
		
		System.out.println(tree.getLargest());
		System.out.println(tree.getSmallest());
		System.out.println("");

		System.out.println(tree.contains(30));
		System.out.println(tree.contains(2));
		
		tree.remove(95);
		
		Iterator inOrder = tree.iterator();
		while(inOrder.hasNext())
		{
			System.out.print(inOrder.next() + " ");
		}
		
		
		
		// visualize the tree
		bridges.setDataStructure(tree.getRoot());
		
		bridges.visualize();
		
	}

}
