package subsetsum;

import java.util.*;

/**
 * @author Foothill College, Thanh Nguyen
 */

public class SubsetSum
{
   private static ArrayList<Sublist> col = new ArrayList<Sublist>();
   private static ArrayList<Double> subset;
     
   public static ArrayList<Double> 
   findSubset(ArrayList<Double> shoppingList, double budget)
   {      
      //Initialize the collection Col with one sub-list: the empty sub-list.
      Sublist newSublist = new Sublist(shoppingList);          
      col.add(newSublist);      
            
      //System.out.println(shoppingList.size());
      
      //Loop over all elements x in S
      for(int i = 0; i < shoppingList.size(); i++)
      {
         //Loop over all subsets, L, that are already members of Col.
         for (int j = 0; j < col.size() - j; j++)
         {           
            newSublist = col.get(j);
            double k = newSublist.getSum() + shoppingList.get(i);
            if(k <= budget)
            {
               //newSublist.showSublist();
               try
               {
                  col.add(newSublist.addItem(i));                 
                
               } catch (CloneNotSupportedException e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }          
            else if(newSublist.getSum() + shoppingList.get(i) == budget)
               break;      
         }        
      }

      //Of all the subsets that end up in Col, find the sub-list L' with the largest sum()
      double highest = col.get(0).getSum();           
      int highestIndex = 0;
      
      for (int i = 0; i < col.size(); i++ )
      {       
         double curValue = col.get(i).getSum();
         if (curValue > highest) 
         {
             highest = curValue;
             highestIndex = i;
         }
      }
      
      subset = col.get(highestIndex).getSubSet();
      
      return subset;
   }

}
