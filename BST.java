// Name: Ethan Lynch
// Class: CS 3305/Section#3
// Term: Fall 2026
// Instructor: Dr. Haddad
// Assignment: 6
// IDE Name: Visual Studio Code

/*
  Generic binary search tree (textbook Listing 25.5).
  Smaller values go in the left subtree, larger values go in the right.
  Duplicate values are not inserted.
*/

public class BST<E extends Comparable<E>> extends AbstractTree<E>
{
   protected TreeNode<E> root; //top node of the tree
   protected int size = 0;     //number of nodes in the tree

   /*
     Creates an empty tree.
   */
   public BST()
   {
   }

   /*
     Creates a tree and inserts each value from the array.
   */
   public BST(E[] objects)
   {
      for (int i = 0; i < objects.length; i++)
      {
         insert(objects[i]);
      }
   }

   /*
     Returns true if e is in the tree.
   */
   @Override
   public boolean search(E e)
   {
      TreeNode<E> current = root; //start at the root

      //go left or right until e is found or we run off the tree
      while (current != null)
      {
         if (e.compareTo(current.element) < 0)
         {
            current = current.left;
         }
         else if (e.compareTo(current.element) > 0)
         {
            current = current.right;
         }
         else
         {
            return true; //match found
         }
      }

      return false;
   }

   /*
     Inserts e into the tree. Returns false if e is
     already in the tree, true if it was added.
   */
   @Override
   public boolean insert(E e)
   {
      if (root == null)
      {
         //empty tree, new node becomes the root
         root = createNewNode(e);
      }
      else
      {
         //find the parent the new node will hang from
         TreeNode<E> parent = null;
         TreeNode<E> current = root;
         while (current != null)
         {
            if (e.compareTo(current.element) < 0)
            {
               parent = current;
               current = current.left;
            }
            else if (e.compareTo(current.element) > 0)
            {
               parent = current;
               current = current.right;
            }
            else
            {
               return false; //duplicate, don't insert
            }
         }

         //attach the new node on the correct side of the parent
         if (e.compareTo(parent.element) < 0)
         {
            parent.left = createNewNode(e);
         }
         else
         {
            parent.right = createNewNode(e);
         }
      }

      size++;
      return true;
   }

   /*
     Creates and returns a new node holding e.
   */
   protected TreeNode<E> createNewNode(E e)
   {
      return new TreeNode<E>(e);
   }

   /*
     Prints the tree inorder, starting at the root.
   */
   @Override
   public void inorder()
   {
      inorder(root);
   }

   /*
     Prints a subtree inorder: left, node, right.
   */
   protected void inorder(TreeNode<E> root)
   {
      if (root == null)
      {
         return;
      }
      inorder(root.left);
      System.out.print(root.element + " ");
      inorder(root.right);
   }

   /*
     Prints the tree postorder, starting at the root.
   */
   @Override
   public void postorder()
   {
      postorder(root);
   }

   /*
     Prints a subtree postorder: left, right, node.
   */
   protected void postorder(TreeNode<E> root)
   {
      if (root == null)
      {
         return;
      }
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.element + " ");
   }

   /*
     Prints the tree preorder, starting at the root.
   */
   @Override
   public void preorder()
   {
      preorder(root);
   }

   /*
     Prints a subtree preorder: node, left, right.
   */
   protected void preorder(TreeNode<E> root)
   {
      if (root == null)
      {
         return;
      }
      System.out.print(root.element + " ");
      preorder(root.left);
      preorder(root.right);
   }

   //node class that holds one tree element and links to its children
   public static class TreeNode<E extends Comparable<E>>
   {
      protected E element;         //data field
      protected TreeNode<E> left;  //left child
      protected TreeNode<E> right; //right child

      public TreeNode(E e)         //constructor method
      {
         element = e;
      }
   }

   /*
     Returns the number of nodes in the tree.
   */
   @Override
   public int getSize()
   {
      return size;
   }

   /*
     Returns the root node.
   */
   public TreeNode<E> getRoot()
   {
      return root;
   }

   /*
     Returns the list of nodes visited going from the root
     toward e. If e is not in the tree, the list stops at
     the last node checked.
   */
   public java.util.ArrayList<TreeNode<E>> path(E e)
   {
      java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
      TreeNode<E> current = root; //start at the root

      //add each node we pass, then step left or right
      while (current != null)
      {
         list.add(current);
         if (e.compareTo(current.element) < 0)
         {
            current = current.left;
         }
         else if (e.compareTo(current.element) > 0)
         {
            current = current.right;
         }
         else
         {
            break; //reached e
         }
      }

      return list;
   }

   /*
     Deletes e from the tree. Returns true if it was
     deleted, false if e was not in the tree.
   */
   @Override
   public boolean delete(E e)
   {
      //find the node to delete and its parent
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
      {
         if (e.compareTo(current.element) < 0)
         {
            parent = current;
            current = current.left;
         }
         else if (e.compareTo(current.element) > 0)
         {
            parent = current;
            current = current.right;
         }
         else
         {
            break; //current is the node to delete
         }
      }

      if (current == null)
      {
         return false; //e is not in the tree
      }

      if (current.left == null)
      {
         //case 1: no left child, link the parent to the right child
         if (parent == null)
         {
            root = current.right;
         }
         else
         {
            if (e.compareTo(parent.element) < 0)
            {
               parent.left = current.right;
            }
            else
            {
               parent.right = current.right;
            }
         }
      }
      else
      {
         //case 2: has a left child, find the rightmost node
         //in the left subtree (largest value smaller than e)
         TreeNode<E> parentOfRightMost = current;
         TreeNode<E> rightMost = current.left;

         while (rightMost.right != null)
         {
            parentOfRightMost = rightMost;
            rightMost = rightMost.right;
         }

         //copy the rightmost value into current
         current.element = rightMost.element;

         //unlink the rightmost node
         if (parentOfRightMost.right == rightMost)
         {
            parentOfRightMost.right = rightMost.left;
         }
         else
         {
            //rightmost was current's direct left child
            parentOfRightMost.left = rightMost.left;
         }
      }

      size--;
      return true;
   }

   /*
     Returns an iterator that walks the tree inorder.
   */
   @Override
   public java.util.Iterator<E> iterator()
   {
      return new InorderIterator();
   }

   //iterator that copies the tree into a list inorder, then walks the list
   private class InorderIterator implements java.util.Iterator<E>
   {
      private java.util.ArrayList<E> list = new java.util.ArrayList<E>(); //tree values in order
      private int current = 0; //index of the next element

      /*
        Fills the list from the tree.
      */
      public InorderIterator()
      {
         inorder();
      }

      /*
        Adds every tree value to the list, starting at the root.
      */
      private void inorder()
      {
         inorder(root);
      }

      /*
        Adds a subtree's values to the list inorder.
      */
      private void inorder(TreeNode<E> root)
      {
         if (root == null)
         {
            return;
         }
         inorder(root.left);
         list.add(root.element);
         inorder(root.right);
      }

      /*
        Returns true if there are more elements to visit.
      */
      @Override
      public boolean hasNext()
      {
         return current < list.size();
      }

      /*
        Returns the current element and moves to the next one.
      */
      @Override
      public E next()
      {
         return list.get(current++);
      }

      /*
        Deletes the current element from the tree and rebuilds the list.
      */
      @Override
      public void remove()
      {
         delete(list.get(current));
         list.clear();
         inorder();
      }
   }

   /*
     Removes every element from the tree.
   */
   public void clear()
   {
      root = null;
      size = 0;
   }
}
