// Name: Ethan Lynch
// Class: CS 3305/Section#3
// Term: Fall 2026
// Instructor: Dr. Haddad
// Assignment: 6
// IDE Name: Visual Studio Code

import java.util.Scanner;

/*
  Reads text from the keyboard, splits it into words on spaces, and adds
  each word to a BST of strings. The BST skips duplicates, so an inorder
  traversal prints each word once in sorted order.
*/

public class RemoveDuplicates
{
   //Scanner used for all user input
   private static Scanner scanner = new Scanner(System.in);

   //text entered with option 1, null until the user enters some
   private static String inputText = null;

   /*
     Shows the menu and runs the chosen option until the user enters 3.
   */
   public static void main(String[] args)
   {
      int option = 0;

      //loop: 3 ends the program
      while (option != 3)
      {
         printMenu();
         option = readInteger("Enter option number: ");
         System.out.println();

         //run the method for the selected option
         switch (option)
         {
            case 1:
               readInputText();
               break;
            case 2:
               removeDuplicates();
               break;
            case 3:
               System.out.println("Exiting program. Goodbye!");
               break;
            default:
               System.out.println("Invalid option. Please enter a number from 1 to 3.");
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
      System.out.println("-----------------MAIN MENU---------------");
      System.out.println("1. Read input string");
      System.out.println("2. Remove duplicates and display outputs");
      System.out.println("3. Exit program");
      System.out.println();
   }

   /*
     Shows a prompt and reads an integer. Keeps asking until the
     user types a valid whole number, then clears the rest of the line.
   */
   public static int readInteger(String prompt)
   {
      System.out.print(prompt);

      //skip bad input and ask again
      while (!scanner.hasNextInt())
      {
         scanner.next();
         System.out.print("Invalid input. " + prompt);
      }
      int value = scanner.nextInt();
      scanner.nextLine(); //drop the leftover newline so text input reads cleanly
      return value;
   }

   /*
     Option 1: reads the input text. The user can type or paste one or
     more lines and presses Enter on a blank line to finish. Empty input
     is rejected.
   */
   public static void readInputText()
   {
      System.out.println("Enter input text (press Enter on a blank line to finish):");

      String text = "";
      String line = scanner.nextLine();

      //keep reading lines until a blank one
      while (!line.trim().isEmpty())
      {
         if (!text.isEmpty())
         {
            text += "\n";
         }
         text += line;
         line = scanner.nextLine();
      }

      //need at least one word
      if (text.trim().isEmpty())
      {
         System.out.println("No text entered. Please try option 1 again.");
         return;
      }

      inputText = text;
      System.out.println("Input text saved.");
   }

   /*
     Option 2: adds every word of the input text to a BST, then prints
     the original text and the words inorder without duplicates.
   */
   public static void removeDuplicates()
   {
      //need text before we can process it
      if (inputText == null)
      {
         System.out.println("Please read the input string first (option 1).");
         return;
      }

      //split on spaces (and line breaks) to get the words
      String[] words = inputText.trim().split("\\s+");

      //the BST ignores repeats, so each word is stored once
      BST<String> tree = new BST<String>();
      for (int i = 0; i < words.length; i++)
      {
         tree.insert(words[i]);
      }

      System.out.println("Original Text:");
      System.out.println(inputText);
      System.out.println();
      System.out.println("Processed Text:");
      tree.inorder();
      System.out.println();
   }
}
