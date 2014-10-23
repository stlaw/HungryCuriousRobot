/*
   Steven Law
   stlaw9@gmail.com
   Project #3: A curious or hungry robot
   
   The RobotMemorySimulation class will run 20 experiments of the robot simulation in each of 3 memory structures
   and 3 different retrieval probabilities. The simulation will first add a specified amount of energy sources
   and then initiate a robot with a particular memory structure and retrieval probability. The robot will then move 
   around the "field" detecting and retrieving energy sources based on its own energy level. It is in the curious
   state when it is above 50 energy and in the hungry state at or below 50. When the robot reaches negative energy
   it becomes inactive and the simulation is over. The average distanced traveled is then recorded.
**/   

import java.awt.Point;
import java.util.*;

public class RobotMemorySimulation
{  
   Random random = new Random();
   ArrayList<EnergySource> energy = new ArrayList<EnergySource>();
   
   /*
   The constructor for RobotMemorySimulation takes the robot starting energy level, number
   of energy sources to be added, the memory structure, and retrieval probability. It then
   creates a robot and begins to run the simulation.
   @param numEnergy The number of energy sources to be added.
   @param roboEnergy The starting energy level of the robot.
   @probability The retrieval probability.
   @memType The memory structure to be used.
   */  
   public RobotMemorySimulation(int numEnergy, double roboEnergy, double probability, int memType)
   {   
      Point startPos = new Point(random.nextInt(200), random.nextInt(200));
      Robot<Memory> robot = new Robot<Memory>(startPos, roboEnergy, memType); 
              
      while (energy.size() < numEnergy) { 
         addEnergySource(numEnergy, 125);
      }
      
      while (robot.getLevel() > 0) {                  //Active state
         while (robot.getLevel() > 50) {              //Curious state
            robot.move(robot.getPosition());
            for (int j = 0; j < energy.size(); j++) {
               if (robot.getPosition().distance(energy.get(j).getLocation()) <= 15) {
                  EnergySource temp1 = new EnergySource(energy.get(j).getLocation(), energy.get(j).energyAmount);
                  
                  robot.getStructure().dupCheck(temp1);
                  robot.getStructure().learn(new Memory(temp1));
               }
            }
         }
         while (robot.getLevel() <= 50 && robot.getLevel() > 0) {             //Hungry state  
            if (!robot.getStructure().emptyCheck()) {
               robot.hungry(robot.getPosition(), probability);
               
               int tempIndex = -1;
               
               for (int k = 0; k < energy.size(); k++) {
                  if (energy.get(k).getLocation() == robot.getStructure().getNext().energy.getLocation()) {
                     tempIndex = k;
                  }
               }
               energy.get(tempIndex).setAmount(robot.getStructure().getNext().getEnergy());
               
               if (energy.get(tempIndex).getAmount() == 0) {
                  robot.getStructure().forget();
                  energy.remove(tempIndex);
               }
               
            } 
            else {
               robot.move(robot.getPosition());
            }  
         }
      }
      System.out.println("Total distance traveled: " + robot.getDistance());
   }
   
   /*
      The addEnergySource method is used to add energy sources onto the "field".
      @param numE The number of energy sources to add.
      @param energyUnits The amount of energy units each source has.
   */
   public void addEnergySource(int numE, double energyUnits) 
   {
      EnergySource newEnergy = new EnergySource(random.nextInt(200), random.nextInt(200), energyUnits);
      
      boolean distanceCheck = false;
      
      if (energy.size() == 0) { 
         energy.add(newEnergy);
      } else {
         for (int i = 0; i < energy.size(); i++) {
            if (newEnergy.getLocation().distance(energy.get(i).getLocation()) >= 20) {
               distanceCheck = true;
            }
         }
      }
      if (distanceCheck == true) {
         energy.add(newEnergy);
      }
   }
   
   /*
      The getList method is used to get the array list of energy sources currently active.
      @return The ArrayList object of currently active energy sources.
   */
   public ArrayList<EnergySource> getList()
   {
      return energy;
   }
   
   /*
      The main method runs all of the experiments through the constructor."
   */
   public static void main(String[] args)
   {
      System.out.println("Closest First Memory 40%");
      System.out.println("------------------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.4, 0);
      }
      System.out.println();
      
      System.out.println("Closest First Memory 60%");
      System.out.println("------------------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.6, 0);
      }
      System.out.println();
   
      
      System.out.println("Closest First Memory 80%");
      System.out.println("------------------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.8, 0);
      }
      System.out.println();
   
      
      System.out.println("Random Memory 40%");
      System.out.println("-------------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.4, 1);
      }
      System.out.println();
   
      
      System.out.println("Random Memory 60%");
      System.out.println("------------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.6, 1);
      }
      System.out.println();
   
      
      System.out.println("Random Memory 80%");
      System.out.println("------------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.8, 1);
      }
      System.out.println();
   
      
      System.out.println("Fifo Memory 40%");
      System.out.println("-----------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.4, 2);
      }
      System.out.println();
   
      
      System.out.println("Fifo Memory 60%");
      System.out.println("----------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.6, 2);
      }
      System.out.println();
   
      
      System.out.println("Fifo Memory 80%");
      System.out.println("----------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.8, 2);
      }
      System.out.println();
   
      
      System.out.println("Lifo Memory 40%");
      System.out.println("----------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.4, 3);
      }
      System.out.println();
   
      
      System.out.println("Lifo Memory 60%");
      System.out.println("----------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.6, 3);
      }
      System.out.println();
   
      
      System.out.println("Lifo Memory 80%");
      System.out.println("----------------");
      for (int run = 0; run <= 19; run++) {      
         System.out.println("Test #" + (run + 1) + ":");
         new RobotMemorySimulation(40, 100, 0.8, 3);
      }
      System.out.println();
   
   }
}
      
