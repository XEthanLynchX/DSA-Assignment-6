// Name: Ethan Lynch
// Class: CS 3305/Section#3
// Term: Fall 2026
// Instructor: Dr. Haddad
// Assignment: 6
// IDE Name: Visual Studio Code

/*
  Abstract base class for trees (textbook Listing 25.4).
  Gives empty traversal methods and an isEmpty() built on getSize().
  Subclasses fill in the rest.
*/

public abstract class AbstractTree<E> implements Tree<E>
{
   /*
     Inorder traversal from the root. Does nothing here,
     subclasses override it.
   */
   @Override
   public void inorder()
   {
   }

   /*
     Postorder traversal from the root. Does nothing here,
     subclasses override it.
   */
   @Override
   public void postorder()
   {
   }

   /*
     Preorder traversal from the root. Does nothing here,
     subclasses override it.
   */
   @Override
   public void preorder()
   {
   }

   /*
     Returns true if the tree has no nodes.
   */
   @Override
   public boolean isEmpty()
   {
      return getSize() == 0;
   }
}
