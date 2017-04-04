package queues;

import java.util.*;

/**  
 * @author Foothill College, Thanh Nguyen
 */
public class Queue<E> implements Iterable<E>
{
   //attributes
   private String name;
   private Node head, tail;
   private int size;
   
   //A constructor that takes in a user assigned name and initializes the class attributes. 
   Queue(String name)
   { 
      this.name = name; 
      head = null;
      tail = null;
      size = 0;
   }
   Queue()
   { 
      head = null;
      tail = null;
      size = 0;
   }
   
     //A method called enqueue() which takes a generic item as the argument
     //and adds the item to the end of the queue.

   public void enqueue(E data) 
   {
      if (data == null) 
         return;
      
      Node newNode = tail;
      tail = new Node(data);
      tail.next = null;
      if (isEmpty())
         head = tail;
      else
         newNode.next = tail;
      size++;
   } 
   
   //A method called dequeue() which receives no arguments 
   //and removes the item from the front of the queue. 
   //This method should return the generic item dequeue-ed.
   public E dequeue()
   {
      Node temp;
      
      temp = head;
      if (isEmpty())
         return null;

      head = head.next;
      temp.next = null;
      size--;
      
      return temp.data;     
   }
 
   //A method called peek which looks at the least recently added item of the queue 
   //and returns a generic type for the data seen at the front of the queue. 
   //The item should not be removed from the front of the queue.
   public E peek()
   {
      if (isEmpty())
         return null;

      return head.data;     
   }
   
   //The methods isEmpty(), size() and toString() method.
   public boolean isEmpty()
   {
      return (head == null);
   }  
   public int size()
   {
      return size;
   }
   public String toString()
   {
      Node p;
      
      String myStr = this.name + ":\n";
      // Display all the nodes in the stack
      for( p = head; p != null; p = p.next )
      {
         myStr += p.toString() + ",\n";
      }        
      return myStr;  
   }
   public void clear()
   {
      head = null;
      size = 0;
   }
   
   public String getName()
   {
      return name;
   }
   
   //inner classes Node
   private class Node
   {
      Node next;
      E data;
      
      Node(E x)
      {
         next = null;
         data = x;
      }
      
      public String toString()
      {
         String myStr = data.toString();
         return myStr;
      }
   }
   //end class Node

   private class QueueIterator implements Iterator<E> 
   {
      private Node current = tail;
      
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
      return new QueueIterator();
   }

}//end StackList