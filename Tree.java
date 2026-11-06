// Name: Ethan Lynch
// Class: CS 3305/Section#3
// Term: Fall 2026
// Instructor: Dr. Haddad
// Assignment: 6
// IDE Name: Visual Studio Code

/*
  Interface for a generic tree (textbook Listing 25.3).
  Lists the operations every tree class has to provide.
*/

public interface Tree<E> extends Iterable<E>
{
   /*
     Returns true if e is in the tree.
   */
   public boolean search(E e);

   /*
     Adds e to the tree. Returns true if it was added.
   */
   public boolean insert(E e);

   /*
     Removes e from the tree. Returns true if it was removed.
   */
   public boolean delete(E e);

   /*
     Prints the tree inorder (left, root, right).
   */
   public void inorder();

   /*
     Prints the tree postorder (left, right, root).
   */
   public void postorder();

   /*
     Prints the tree preorder (root, left, right).
   */
   public void preorder();

   /*
     Returns the number of nodes in the tree.
   */
   public int getSize();

   /*
     Returns true if the tree has no nodes.
   */
   public boolean isEmpty();
}
