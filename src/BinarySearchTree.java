/**
 * Binary search tree implementation.
 * 
 * We do not allow duplicates.
 * 
 * @author Greg Gagne
 */
import java.util.Iterator;

import bridges.base.BSTElement;
import bridges.base.BinTreeElement;

public class BinarySearchTree <K extends Comparable<? super K>> implements SearchTreeInterface<K> 
{
	// the root of the binary search tree
	private BSTElement<K, String> root;
	private boolean contains;


	/**
	 * Create an empty binary search tree
	 */
	public BinarySearchTree() 
	{
		root = null;
	}

	/**
	 * This method has nothing to do with a binary search tree,
	 * but is necessary to provide the bridges visualization.
	 */
	public BSTElement<K, String> getRoot() 
	{
		return root;
	}

	public boolean isEmpty() 
	{
		return root == null;
	}

	/**
	 * Solution that uses recursive helper method.
	 * We disallow duplicate elements in the tree. 
	 */
	public K add(K key) 
	{
		if (contains(key))
			return null;
		else {
			root = add(key, root);

			return key;
		}
	}


	/**
	 * private helper method for adding a node to the binary search tree
	 * @param key
	 * @param subtree
	 * @return the root of the tree
	 */
	private BSTElement<K, String> add(K key, BSTElement<K,String> subtree) 
	{
		if (subtree == null) 
		{
			// we have found the spot for the addition

			// create the new node
			// parameters are (1) label (2) key (3) empty string [not used]
			BSTElement<K, String> newNode = new BSTElement<K, String>(key.toString(), key, "");

			// we also set the color of a new node to red
			newNode.getVisualizer().setColor("red");

			return newNode;
		}

		int direction = key.compareTo(subtree.getKey());

		if (direction < 0) 
		{
			subtree.setLeft( add(key, subtree.getLeft()) );
		}
		else if (direction > 0) 
		{
			subtree.setRight( add(key, subtree.getRight()) );
		}

		return subtree;
	}


	/**
	 * Non-recursive algorithm for addition
	 * This only serves the purpose of demonstrating how the
	 * recursive approach provides a much cleaner algorithm!
	 */
	/*
	public K add(K key) {
		// we disallow duplicates
		if (contains(key))
			return null;

		// create the new node
		// parameters are (1) label (2) key (3) null [not used]
		BSTElement<K, String> newNode = new BSTElement<K, String>(key.toString(), key, "");
		newNode.getVisualizer().setColor("red");

		// if the tree is empty, set the root to the new node
		if (isEmpty()) {
			root = newNode;
		}
		else {
			// else treat it like an unsuccessful search
			BSTElement<K, String> node = root;
			boolean keepLooking = true;

			while (keepLooking) {
				int direction = key.compareTo(node.getKey());

				if (direction < 0) {
					// go left
					if (node.getLeft() == null) {
						// we found the place for the insert
						node.setLeft(newNode);
						keepLooking = false;
					}
					else
						node = node.getLeft();
				}
				else if (direction > 0) {
					// go right
					if (node.getRight() == null) {
						// we found the place for the insert
						node.setRight(newNode);
						keepLooking = false;
					}
					else
						node = node.getRight();
				}
			}
		}

		return key;
	}
	 */

	public K getLargest() 
	{
		K returnValue = null;
		if(!isEmpty())
		{
			returnValue = findLargest(root);
		}
		return returnValue;
	}

	private K findLargest(BSTElement<K, String> item)
	{
		K returnValue = null;
		boolean foundLargest = false;
		if(item.getRight() != null)
		{
			returnValue = findLargest(item.getRight());
		}
		else
		{
			returnValue = item.getKey();
			foundLargest = true;
		}
		if(foundLargest)
		{
			item.getVisualizer().setColor("green");
		}
		return returnValue;
	}

	public K getSmallest() 
	{
		K returnValue = null;
		if(!isEmpty())
		{
			returnValue = findSmallest(root);
		}
		return returnValue;
	}

	private K findSmallest(BSTElement<K, String> item)
	{
		K returnValue = null;
		boolean foundSmallest = false;
		if(item.getLeft() != null)
		{
			returnValue = findSmallest(item.getLeft());
		}
		else
		{
			returnValue = item.getKey();
			foundSmallest = true;
		}
		if(foundSmallest)
		{
			item.getVisualizer().setColor("yellow");
		}
		return returnValue;
	}

	public boolean contains(K key) 
	{		
		contains = false;
		return findContains(null, root, key);
	}

	private boolean findContains(BSTElement<K, String> previous, BSTElement<K, String> root, K key)
	{
		if(root != null)
		{
			K rootValue = root.getKey();
			if(rootValue.compareTo(key) == 0)
			{
				contains = true;
			}
			else
			{
				if(key.compareTo(rootValue) > 0)
				{
					if(root.getRight() != null)
					{
						findContains(root, root.getRight(), key);
					}
				}
				else if(key.compareTo(rootValue) < 0)
				{
					if(root.getLeft() != null)
					{
						findContains(root, root.getLeft(), key);
					}
				}
			}
		}
		return contains;
	}

	public K remove(K key)
	{
		K result = null;
		if(!isEmpty() && contains(key))
		{
			remove(key, root);
		}
		return result;
	}

	private BSTElement<K, String> remove(K key, BSTElement<K, String> subTree)
	{
		BSTElement<K, String> result = null;
		int compare = key.compareTo(subTree.getKey());
		BSTElement<K, String> left = null;
		BSTElement<K, String> right = null;
		if(compare < 0)
		{
			left = remove(key, subTree.getLeft());
			subTree.setLeft(left);
		}
		else if(compare > 0)
		{
			right = remove(key, subTree.getRight());
			subTree.setRight(right);
		}
		else 
		{
			if(subTree.getLeft() != null && subTree.getRight() != null)
			{
				result = getLeftMost(subTree.getRight());
				subTree.setKey(result.getKey());
				subTree.setLabel(result.getLabel());
				right = remove(result.getKey(), subTree.getRight());
				subTree.setRight(right);
			}
			else
			{
				if(subTree.getLeft() != null)
				{
					subTree.setKey(subTree.getLeft().getKey());
					subTree.setLabel(subTree.getLeft().getLabel());
					subTree.setLeft(subTree.getLeft().getLeft());
				}
				else if(subTree.getRight() != null)
				{
					subTree.setKey(subTree.getRight().getKey());
					subTree.setLabel(subTree.getRight().getLabel());
					subTree.setRight(subTree.getRight().getRight());
				}
				else
				{
					subTree = null;
				}
			}
		}
		return subTree;
	}


	private BSTElement<K, String> getRightMost(BSTElement<K, String> node) 
	{
		BSTElement<K, String> result = null;
		if(node.getRight() == null)
		{
			//node.getVisualizer().setColor("purple");
			result = node;
		}
		else
		{
			result = getRightMost(node.getRight());
		}
		return result;
	}

	private BSTElement<K, String> getLeftMost(BSTElement<K, String>  node)
	{
		BSTElement<K, String> result = null;
		if(node.getLeft() == null)
		{
			node.getVisualizer().setColor("green");
			result = node;
		}
		else
		{
			result = getLeftMost(node.getLeft());
		}
		return result;
	}

	public int size() 
	{
		return sizeHelper(root);
	}

	private int sizeHelper(BSTElement<K, String> root)
	{
		int numElements = 0;
		if(root != null)
		{
			int numLeft = sizeHelper(root.getLeft());
			int numRight = sizeHelper(root.getRight());
			numElements = numLeft + numRight + 1;
		}
		return numElements;
	}

	public Iterator<K> iterator()
	{
		return new Iteration();
	}

	private class Iteration implements Iterator<K>
	{

		private K[] elements;
		private int next;

		public Iteration()
		{
			elements = (K[]) new Comparable[size()];
			next = 0;
			inOrder(root);
			next = 0;
		}

		private void inOrder(BSTElement item) 
		{
			if (item != null) 
			{
				inOrder(item.getLeft());
				elements[next++] = (K) item.getKey();
				inOrder(item.getRight());

			}
		}

		public boolean hasNext() 
		{
			return (next < size());
		}

		public K next() 
		{
			return elements[next++];
		}

	}
}
