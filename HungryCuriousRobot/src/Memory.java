/*
   Steven Law
   stlaw9@gmail.com
   Project #3: A curious or hungry robot
   
   The Memory class is used to create memory objects of energy sources that the robot detects.
*/

import java.awt.Point;

public class Memory {

   protected EnergySource energy;

   /*
      The constructor creates an energy source in memory.
      @param place The location of the energy source.
      @param amount The amount of energy units in the energy source.
   */
   public Memory(Point place, double amount) 
   {
      energy.setLocation(place);
      energy.setAmount(amount);
   }
   
   /*
      This constructor creats an energy source from another energy source object.
      @param source The energy source to be created.
   */
   public Memory(EnergySource source)
   {
      energy = source;
   }
	
   /*
      The takeEnergy method lets the robot consume energy from an energy source.
      If the robot needs more energy than the source has, it will deplete the entire
      energy source and the energy source's energy level will be set to 0.
      @param need The amount of energy the robot needs to hit max.
      @return The amount of energy consumed.
   */
   public double takeEnergy(double need) 
   {  
      double returnAmount = 0;
      if (need > energy.getAmount()) {
         returnAmount = energy.getAmount();
         energy.setAmount(0);
      } 
      else {
         energy.energyAmount -= need;
         returnAmount = need;
      }
      return returnAmount;    //energy consumed
   }
   
   /*
      The getEnergy method retrieves the amount of energy in the energy source.
      @return The amount of energy in the source.
   */
   public double getEnergy() 
   {  
      return energy.getAmount();
   }
   
   /*
      The getLocation method retrieves the location of the energy source.
      @return The location of the source.
   */
   public Point getLocation()
   {
      return energy.getLocation();
   }
}