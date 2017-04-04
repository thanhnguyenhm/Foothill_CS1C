package stacks;

import java.util.*;

public class Navigator
{
   String currentLink;
   StackList<String> backLinks;
   StackList<String> forwardLinks;
   boolean beginStr = true;
   
   //A constructor that initializes the class attributes.
   Navigator()
   {
      currentLink = "";
      backLinks = new StackList<String>();
      forwardLinks = new StackList<String>();
   }
   
   /* TODO: Sets currentLink to the supplied link address. Places the 
   old currentLink on the backLinks stack. Then, clears the 
   forwardLinks stack.sets current link, */
   public void setCurrentLink(String currentLink)
   {
      if(beginStr)
      {
         this.currentLink = currentLink;
         beginStr = false;
      }       
      else
      {
         backLinks.push(this.currentLink);
         this.currentLink = currentLink;
         forwardLinks.clear();
      }     
   }
   
   //Accessor methods for the class attributes.
   public String getCurrentLink() { return currentLink; }
   public StackList<String> getBackLinks() { return backLinks; }
   public StackList<String> getForwardLinks() { return forwardLinks; }
   
   /* TODO: If there is a link on the backLinks stack, goes to the top link on the
    *       backLinks stack. Pushes the old currentLink onto the forwardLinks stack.
    *       Remember to check for boundary conditions.
    */
   public void goBack()
   {
      if(!backLinks.isEmpty())
      {
         forwardLinks.push(currentLink);
         this.currentLink = backLinks.pop();       
      }
      else
         System.out.println("Empty list");
   }
   
   /* TODO: If there is a link on the forwardLinks stack, goes to the top link on the
    *       forwardLinks stack. Pushes the old currentLink onto the backLinks stack.
    *       Remember to check for boundary conditions.
    */
   public void goForward()
   {
      if(!forwardLinks.isEmpty())
      {
         backLinks.push(currentLink);
         this.currentLink = forwardLinks.pop();       
      }
      else
         System.out.println("Empty list");
   }
}
