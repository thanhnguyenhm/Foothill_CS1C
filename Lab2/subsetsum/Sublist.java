package subsetsum;

import java.util.*;

class Sublist implements Cloneable
{
   private double sum = 0;
   private ArrayList<Double> originalObjects;
   private ArrayList<Integer> indices;
   
   // constructor creates an empty Sublist (no indices)
   public Sublist(ArrayList<Double> shoppingList) 
   {
      sum = 0;
      originalObjects = shoppingList;
      indices = new ArrayList<Integer>();
   }
   
   double getSum() 
   { return sum; }
   
   // I have done the clone() for you, since you will need clone() inside addItem().
   public Object clone() throws CloneNotSupportedException
   {
      // shallow copy
      Sublist newObject = (Sublist)super.clone();
      // deep copy
      newObject.indices = (ArrayList<Integer>)indices.clone();
      
      return newObject;
   }
   
   Sublist addItem( int indexOfItemToAdd) throws CloneNotSupportedException
   {
      Sublist newSublist = (Sublist)clone();

      newSublist.indices.add(indexOfItemToAdd);
      newSublist.sum += originalObjects.get(indexOfItemToAdd);

      return newSublist;
   }
   
   void showSublist()
   { 
      for (int k = 0 ; k < indices.size() ; k++)
         System.out.println("  array[" + indices.get(k) + "] = " 
               + originalObjects.get(indices.get(k)));
   }
   
   public ArrayList<Double> getSubSet()
   {
      ArrayList<Double> returnSubSet = new ArrayList<Double>();
      for (int k = 0 ; k < indices.size() ; k++)
      {
         returnSubSet.add(originalObjects.get(indices.get(k)));
      }
      
      return returnSubSet;
   }
};
