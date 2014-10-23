/*
   Steven Law
   stlaw9@gmail.com
   Project #3: A curious or hungry robot
   
   The Robot class is used to create a robot with a particular memory structure and retrieval probability.
   The robot can move +/- 7 units in any direction and detect energy sources in a 15 unit radius. It can
   also eat energy sources when it is in the hungry state.
*/

import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;

public class Robot<E extends Memory> 
{
   private RobotMemory<Memory> memoryStructure;
	
   private double energyLevel, startLevel;
   private Point position;
   double totalDistance = 0;
   
   /*
      The constructor takes in a starting position, energy level, and memory type.
      It then creats a robot according to the specifications. The robot can utilize
      one of four memory types: Closest First, Random, Fifo, or Lifo.
      @param startPos The robot's starting position.
      @param energyLvl The robot's starting and max energy level.
      @param memoryType The memory structure to be used.
   */
   public Robot(Point startPos, double energyLvl, int memoryType) 
   {
      position = startPos;
      energyLevel = energyLvl;
      startLevel = energyLvl;
      
      switch (memoryType) {
         case 0: memoryStructure = new ClosestFirstStructure<Memory>();
            break;
         case 1: memoryStructure = new RandomStructure<Memory>();
            break;
         case 2: memoryStructure = new FifoStructure<Memory>();
            break;
         case 3: memoryStructure = new LifoStructure<Memory>();
            break;
      }
   }
   
   /*
      The move method moves the robot 7 units in a random direction. It
      cannot go back to it's previous position. This method uses a random
      integer generator to generate its moves. It also checks to make sure 
      the robot does not move outside of the designated field.
      @param currentPos The robot's current position prior to moving.
      @return The point that the robot moves to.
   */
   public Point move(Point currentPos)
   {
      Point tempPos = new Point(currentPos);
      Random random = new Random();
      double nextMove = 0;
      double x = 0;
      double y = 0;
      
      do {
         for (int i = 0; i < 2; i++) {       //Make random move
            switch (random.nextInt(3)) {
               case 0: nextMove = -7;
                  break;
               case 1: nextMove = 0;
                  break;
               case 2: nextMove = 7;
                  break;
            }
            if (i == 0) {
               x = nextMove;
            } 
            else {
               y = nextMove;
            }
         }
      } while (x == 0 && y == 0);            //Repeat if robot does not move
      
      Point newPosition = new Point((int)(tempPos.getX() + x), (int)(tempPos.getY() + y));
      
      if (newPosition.x > 200 || newPosition.x < 0 || newPosition.y > 200 || newPosition.y < 0) {  //Robot cannot leave field
         move(currentPos);
      } 
      else {
         position = newPosition;
         double distance = currentPos.distance(position);
         totalDistance += distance;          //Add to distance accumulator
         energyLevel -= distance;            //Consume energy of distance traveled
      }
      return position;
   }
   
   /*
      The hungry method is called when the robot enters the hungry state. If the 
      robot has a memory of an energy source, it will attempt to retrieve the
      location of the energy source. If the robot ends up moving to that location,
      it will consume the energy required to get there (it can go negative), and then
      consume the energy and the energy source to max level or as close as it can.
      @param current The robot's current position.
      @param probability The retrieval probability.
      @return The new position of the robot.
   */
   public Point hungry(Point current, double probability)
   {
      Point targetEnergy = new Point(getStructure().retrieve(probability, current));      //Retrieve point of energy source
      if (targetEnergy == current) {               //Returns same position if no energy source or probability failed
         move(current);                            //Move one more step
      }
      energyLevel -= position.distance(targetEnergy);                         //Consume energy of distance traveled
      totalDistance += position.distance(targetEnergy);                       //Distance accumulator
      energyLevel += getStructure().getAmount(startLevel - energyLevel);      //Eat enough energy to fill up to max
      position = targetEnergy;
      return position;
   }
   
   /*
      The getPosition method retrieves the current position of the robot.
      @return The position of the robot.
   */
   public Point getPosition()
   {
      return position;
   }
   
   /*
      The getLevel method retrieves the current energy level of the robot.
      @return The energy level of the robot.
   */
   public double getLevel()
   {
      return energyLevel;
   }
   
   /*
      The setLevel method sets the robot's energy level to a certain value.
      @param level The value to set the robot's energy level.
   */
   public void setLevel(double level)
   {
      energyLevel = level;
   }
   
   /*
      The getStructure method retrieves the memory structure of the robot.
      @return The memory structure of the robot.
   */
   public RobotMemory getStructure()
   {
      return memoryStructure;
   }
   
   /*
      The getDistance method retrieves the distance traveled by the robot.
      @return The distance traveled by the robot.
   */
   public double getDistance() 
   {
      return totalDistance;
   }
}