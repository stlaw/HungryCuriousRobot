/*
   Steven Law
   stlaw9@gmail.com
   Project #3: A curious or hungry robot
   
   The RandomStructure class retrieves its energy sources randomly.
*/

import java.awt.Point;
import java.util.Deque;
import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;

public class RandomStructure<E extends Memory> implements RobotMemory<E> 
{	
   protected LinkedList<Memory> randomList = new LinkedList<Memory>();

   /*
      The forget method forgets the first memory.
   */
   public void forget()
   {
      randomList.removeFirst();
   }
   
   /*
      The learn method adds a memory object at the end of the list.
      @param episode The object to be added.
      @return The size of the list.
   */
   public int learn(E episode)
   {
      randomList.addLast(episode);
      return randomList.size();
   }
   
   /*
      The emptyCheck method checks to see if the list is empty.
      @return Returns true if it is empty.
   */
   public boolean emptyCheck()
   {
      boolean emptyStatus = false;
      if (randomList.isEmpty()) {
         emptyStatus = true;
      } 
      return emptyStatus;
   }

   /*
      The deleteCheck method deletes the first memory if there is no 
      energy left in it.
      @return Returns true if the energy source was deleted.
   */
   public boolean deleteCheck()
   {
      boolean deleteStatus = false;
      if (randomList.peekFirst().getEnergy() == 0) {
         deleteStatus = true;
      }
      return deleteStatus;
   }
   
   /*
      The retrieve method makes a probability check. If it passes, it randomly
      selects a memory to move to the front of the list.
      @param probability The retrieval probability.
      @param robotPosition The current location of the robot.
      @return The location of the next energy source of probability is passed. Otherwise
              it returns the robot's current position.
   */
   public Point retrieve(double probability, Point robotPosition) 
   {  
      Random chance = new Random();   
      if (chance.nextDouble() <= probability) {
         int newIndex = chance.nextInt(randomList.size());
         int toRemove = newIndex + 1;
         randomList.offerFirst(randomList.get(newIndex));
         randomList.removeLastOccurrence(randomList.get(toRemove));         
         return randomList.peekFirst().getLocation();
      } 
      else {
         return robotPosition;
      }
   }
   
   /*
      The getAmount method lets the robot consume energy to fill its energy level to max.
      @param need The amount of energy needed to hit max.
      @return The amount of energy left in the source.
   */
   public double getAmount(double need)
   {
      double energyLeft = randomList.peekFirst().takeEnergy(need);
      return energyLeft;
   }
   
   
   /*
      The getNext method checks the next memory in line.
      @return The next memory object in the list.
   */
   public Memory getNext()
   {
      return randomList.peekFirst();
   }
   
   /*
      The dupCheck method checks to see if there is a duplicate memory object in the list.
      @param tempE The energy source to check duplicates for.
   */
   public void dupCheck(EnergySource tempE)
   {
      Iterator<Memory> it = randomList.iterator();
      Memory foundMem = null;
      while (it.hasNext()) {
         Memory obj = it.next();
         if (obj.getLocation() == tempE.getLocation()) {
            foundMem = obj;
            break;
         }
      }
      if (foundMem != null) {
         randomList.remove(foundMem);
      }
   }
}

