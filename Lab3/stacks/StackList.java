package stacks;

import java.util.*;

public class StackList<E> implements Iterable<E>
{
   //attribute
   private String name;
   private Node top;
   private int size;
   
   //A constructor that takes initializes the class attributes.
   StackList()
   { 
      name = ""; 
      top = null;
      size = 0;
   }
   
   //A method called push() which takes a generic item as the argument 
   //and adds the item to the top of the stack.
   public void push(E data)
   {
      if (data == null) 
         return;
      // build a node and place it on the stack
      Node newNode = new Node(data);
      newNode.next = top;
      top = newNode;
      size++;
   }
     
   //A method called pop() which receives no arguments 
   //and removes the item from the top of the stack. 
   //This method should return the generic item popped.
   public E pop()
   {
      Node temp;
      
      temp = top;
      if (isEmpty())
         return null;

      top = top.next; 
      size--;
      return temp.data;     
   }
 
   //A method called peek which looks at the top of the type 
   //and returns a generic type for the data seen at the top of the stack. 
   //The item should not be removed from the top of the stack.
   public E peek()
   {
      if (top == null)
         return null;

      return top.data;     
   }
   
   //The methods isEmpty(), size() and toString() method.
   public boolean isEmpty()
   {
      return (top == null);
   }  
   public int size()
   {
      return size;
   }
   public String toString()
   {
      Node p;
      
      String myStr = "";
      // Display all the nodes in the stack
      for( p = top; p != null; p = p.next )
      {
         myStr += p.toString() + ",\n";
      }        
      return myStr;  
   }
   public void clear()
   {
      top = null;
      size = 0;
   }
   //inner classes 
   private class Node
   {
      Node next;
      E data;
      
      Node(E x)
      {
         data = x;
      }
      
      public String toString()
      {
         String myStr = data.toString();
         return myStr;
      }
   }
   //end class Node

   private class StackIterator implements Iterator<E> 
   {
      private Node current = top;
      
      public boolean hasNext() 
      {
         return current != null;
      }

      public void remove() 
      {
         throw new UnsupportedOperationException();
      }

      public E next() 
      {
         if (!hasNext())
            throw new NoSuchElementException();
         E temp = current.data;
         current = current.next;
         return temp;
      }
   }
   
   public Iterator<E> iterator()
   {
      return new StackIterator();
   }

}//end StackList