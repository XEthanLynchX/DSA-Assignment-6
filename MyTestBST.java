// Name: Ethan Lynch
// Class: CS 3305/Section#3
// Term: Fall 2026
// Instructor: Dr. Haddad
// Assignment: 6
// IDE Name: Visual Studio Code

import java.util.ArrayList;
import java.util.Scanner;

/*
  Menu test program for the BST class. Combines TestBST and TestBSTDelete.
  The user must pick the tree data type (integer or string) with option 0
  first. Every value after that is read and checked against that type.
  The menu shows again after each option until the user picks option 10.
*/

public class MyTestBST
{
   //Scanner used for all user input
   private static Scanner scanner = new Scanner(System.in);

   //only one of these is used, based on the type picked in option 0
   private static BST<Integer> intTree = null;
   private static BST<String> stringTree = null;

   //"integer" or "string", null until option 0 is done
   private static String treeType = null;

   /*
     Shows the menu and runs the chosen option until the user enters 10.
     Options 1 to 9 are blocked until a data type is picked.
   */
   public static void main(String[] args)
   {
      int option = -1;

      //loop: 10 ends the program
      while (option != 10)
      {
         printMenu();
         option = readInteger("Enter option number: ");
         System.out.println();

         //force option 0 before any tree option
         if (treeType == null && option >= 1 && option <= 9)
         {
            System.out.println("Please select the tree data type first (option 0).");
            continue;
         }

         //run the method for the selected option, using the tree for the picked type
         switch (option)
         {
            case 0:
               selectTreeType();
               break;
            case 1:
               if (isIntegerTree())
               {
                  insertElement(intTree, readInteger("Enter integer to insert: "));
               }
               else
               {
                  insertElement(stringTree, readString("Enter string to insert: "));
               }
               break;
            case 2:
               if (isIntegerTree())
               {
                  deleteElement(intTree);
               }
               else
               {
                  deleteElement(stringTree);
               }
               break;
            case 3:
               if (isIntegerTree())
               {
                  searchElement(intTree);
               }
               else
               {
                  searchElement(stringTree);
               }
               break;
            case 4:
               printSize(currentTree());
               break;
            case 5:
               if (isIntegerTree())
               {
                  printPath(intTree);
               }
               else
               {
                  printPath(stringTree);
               }
               break;
            case 6:
               checkEmpty(currentTree());
               break;
            case 7:
               printPreorder(currentTree());
               break;
            case 8:
               printInorder(currentTree());
               break;
            case 9:
               printPostorder(currentTree());
               break;
            case 10:
               System.out.println("Exiting program. Goodbye!");
               break;
            default:
               System.out.println("Invalid option. Please enter a number from 0 to 10.");
         }
      }

      scanner.close();
   }

   /*
     Prints the main menu with blank lines before and after it.
   */
   public static void printMenu()
   {
      System.out.println();
      System.out.println();
      System.out.println("----------------MAIN MENU---------------");
      System.out.println("0. Enter Tree Data Type (integer or string)");
      System.out.println("1. Insert Data Element");
      System.out.println("2. Delete Data Element");
      System.out.println("3. Search for Data Element");
      System.out.println("4. Print Tree Size");
      System.out.println("5. Path from Root to Data Element");
      System.out.println("6. Check if Empty Tree");
      System.out.println("7. Print Preorder Traversal");
      System.out.println("8. Print Inorder Traversal");
      System.out.println("9. Print Postorder Traversal");
      System.out.println("10. Exit program");
      System.out.println();
   }

   /*
     Shows a prompt and reads an integer. Keeps asking until the
     user types a valid whole number.
   */
   public static int readInteger(String prompt)
   {
      System.out.print(prompt);

      //skip bad input and ask again
      while (!scanner.hasNextInt())
      {
         scanner.next();
         System.out.print("Invalid input. Please enter an integer. " + prompt);
      }
      return scanner.nextInt();
   }

   /*
     Shows a prompt and reads one word as a string.
   */
   public static String readString(String prompt)
   {
      System.out.print(prompt);
      return scanner.next();
   }

   /*
     Returns true if the picked tree type is integer.
   */
   public static boolean isIntegerTree()
   {
      return treeType.equals("integer");
   }

   /*
     Returns the tree for the picked type.
   */
   public static BST<?> currentTree()
   {
      if (isIntegerTree())
      {
         return intTree;
      }
      return stringTree;
   }

   /*
     Option 0: reads the tree data type and creates a new, empty
     tree of that type. Keeps asking until the user types
     "integer" or "string".
   */
   public static void selectTreeType()
   {
      String type = readString("Enter tree data type (integer or string): ").toLowerCase();

      //only two types are allowed
      while (!type.equals("integer") && !type.equals("string"))
      {
         System.out.println("Invalid data type. Please enter integer or string.");
         type = readString("Enter tree data type (integer or string): ").toLowerCase();
      }

      treeType = type;

      //start a fresh tree for the new type
      if (isIntegerTree())
      {
         intTree = new BST<Integer>();
         stringTree = null;
      }
      else
      {
         stringTree = new BST<String>();
         intTree = null;
      }

      System.out.println("Tree data type set to " + treeType + ". A new empty tree was created.");
   }

   /*
     Option 1: inserts value and prints the tree inorder before
     and after. Duplicates are not added.
   */
   public static <E extends Comparable<E>> void insertElement(BST<E> tree, E value)
   {
      System.out.print("BST before inserting " + value + " (Inorder):\t");
      tree.inorder();
      System.out.println();

      //insert returns false for a duplicate
      if (!tree.insert(value))
      {
         System.out.println(value + " is already in the tree. Duplicates are not inserted.");
         return;
      }

      System.out.print("BST after inserting " + value + " (Inorder):\t");
      tree.inorder();
      System.out.println();
   }

   /*
     Option 2: reads a value, deletes it, and prints the tree
     inorder before and after. Prints "Empty Tree" if there is
     nothing to delete.
   */
   public static <E extends Comparable<E>> void deleteElement(BST<E> tree)
   {
      if (tree.isEmpty())
      {
         System.out.println("Empty Tree");
         return;
      }

      E value = readValue("Enter " + treeType + " to delete: ");

      System.out.print("BST before deleting " + value + " (Inorder):\t");
      tree.inorder();
      System.out.println();

      //delete returns false if the value isn't there
      if (!tree.delete(value))
      {
         System.out.println(value + " is not in the tree.");
         return;
      }

      System.out.print("BST after deleting " + value + " (Inorder):\t");
      tree.inorder();
      System.out.println();
   }

   /*
     Option 3: reads a value and prints whether it is in the tree.
     Prints "Empty Tree" if the tree is empty.
   */
   public static <E extends Comparable<E>> void searchElement(BST<E> tree)
   {
      if (tree.isEmpty())
      {
         System.out.println("Empty Tree");
         return;
      }

      E value = readValue("Enter " + treeType + " to search for: ");
      System.out.println("Is " + value + " in the tree? " + tree.search(value));
   }

   /*
     Option 4: prints the number of nodes in the tree.
     Prints "Empty Tree" if the tree is empty.
   */
   public static void printSize(BST<?> tree)
   {
      if (tree.isEmpty())
      {
         System.out.println("Empty Tree");
      }
      System.out.println("Tree size: " + tree.getSize());
   }

   /*
     Option 5: reads a value and prints the path from the root
     down to it. Prints a message if the value is not in the tree.
   */
   public static <E extends Comparable<E>> void printPath(BST<E> tree)
   {
      if (tree.isEmpty())
      {
         System.out.println("Empty Tree");
         return;
      }

      E value = readValue("Enter " + treeType + " to find the path to: ");

      //no path to print if the value isn't in the tree
      if (!tree.search(value))
      {
         System.out.println(value + " is not in the tree.");
         return;
      }

      //print each node from the root down to the value
      ArrayList<BST.TreeNode<E>> path = tree.path(value);
      System.out.print("Path from root to " + value + ": ");
      for (int i = 0; i < path.size(); i++)
      {
         System.out.print(path.get(i).element + " ");
      }
      System.out.println();
   }

   /*
     Option 6: prints whether the tree is empty.
   */
   public static void checkEmpty(BST<?> tree)
   {
      System.out.println("Is empty tree? " + tree.isEmpty());
   }

   /*
     Option 7: prints the preorder traversal.
     Prints "Empty Tree" if the tree is empty.
   */
   public static void printPreorder(BST<?> tree)
   {
      if (tree.isEmpty())
      {
         System.out.println("Empty Tree");
         return;
      }

      System.out.print("Preorder: ");
      tree.preorder();
      System.out.println();
   }

   /*
     Option 8: prints the inorder traversal.
     Prints "Empty Tree" if the tree is empty.
   */
   public static void printInorder(BST<?> tree)
   {
      if (tree.isEmpty())
      {
         System.out.println("Empty Tree");
         return;
      }

      System.out.print("Inorder: ");
      tree.inorder();
      System.out.println();
   }

   /*
     Option 9: prints the postorder traversal.
     Prints "Empty Tree" if the tree is empty.
   */
   public static void printPostorder(BST<?> tree)
   {
      if (tree.isEmpty())
      {
         System.out.println("Empty Tree");
         return;
      }

      System.out.print("Postorder: ");
      tree.postorder();
      System.out.println();
   }

   /*
     Reads a value that matches the picked tree type: an Integer
     for an integer tree, a String for a string tree.
   */
   @SuppressWarnings("unchecked")
   public static <E extends Comparable<E>> E readValue(String prompt)
   {
      //the cast is safe because the tree passed in always matches treeType
      if (isIntegerTree())
      {
         return (E) Integer.valueOf(readInteger(prompt));
      }
      return (E) readString(prompt);
   }
}
