/*
   Steven Law
   stlaw9@gmail.com
   Project #3: A curious or hungry robot
   
   The ClosestFirstStructure class retrieves its energy sources based on the closest distance to
   the robot's position.
*/

import java.awt.Point;
import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;

public class ClosestFirstStructure<E extends Memory> implements RobotMemory<E> 
{	
   protected LinkedList<Memory> closest = new LinkedList<Memory>();

   /*
      The forget method forgets the first memory.
   */
   public void forget()
   {
      closest.removeFirst();
   }
   
   /*
      The learn method adds a memory object at the end of the list.
      @param episode The object to be added.
      @return The size of the list.
   */
   public int learn(E episode)
   {
      closest.addLast(episode);
      return closest.size();
   }

   /*
      The emptyCheck method checks to see if the list is empty.
      @return Returns true if it is empty.
   */
   public boolean emptyCheck()
   {
      boolean emptyStatus = false;
      if (closest.isEmpty()) {
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
      if (closest.peekFirst().getEnergy() == 0) {
         deleteStatus = true;
      }
      return deleteStatus;
   }
   
   /*
      The retrieve method makes a probability check. If it passes, it finds 
      the closest energy source to the robot's position and moves that memory
      to the front of the list.
      @param probability The retrieval probability.
      @param robotPosition The current location of the robot.
      @return The location of the next energy source of probability is passed. Otherwise
              it returns the robot's current position.
   */
   public Point retrieve(double probability, Point robotPosition) 
   {  
      Random chance = new Random();   
      if (chance.nextDouble() <= probability) {
         double shortestDis = robotPosition.distance(closest.get(0).getLocation());
         int closestIndex = 0;
         
         for (int i = 1; i < closest.size(); i++) {
            double tempDis = robotPosition.distance(closest.get(i).getLocation());
            if (tempDis < shortestDis) {
               shortestDis = tempDis;
               closestIndex = i;
            }
         }
         
         if (closestIndex != 0) {
            int toRemove = closestIndex + 1;
            closest.offerFirst(closest.get(closestIndex));
            closest.removeLastOccurrence(closest.get(toRemove));
         }
         
         return closest.peekFirst().getLocation();
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
      double energyLeft = closest.peekFirst().takeEnergy(need);
      return energyLeft;
   }
   
   /*
      The getNext method checks the next memory in line.
      @return The next memory object in the list.
   */
   public Memory getNext()
   {
      return closest.peekFirst();
   }
   
   /*
      The dupCheck method checks to see if there is a duplicate memory object in the list.
      @param tempE The energy source to check duplicates for.
   */
   public void dupCheck(EnergySource tempE)
   {
      Iterator<Memory> it = closest.iterator();
      Memory foundMem = null;
      while (it.hasNext()) {
         Memory obj = it.next();
         if (obj.getLocation() == tempE.getLocation()) {
            foundMem = obj;
            break;
         }
      }
      if (foundMem != null) {
         closest.remove(foundMem);
      }
   } 
}

