/*
   Steven Law
   stlaw9@gmail.com
   Project #3: A curious or hungry robot
   
   The EnergySource class is used to create energy sources to be stored in memory or on the field.
   Each energy source contains a Point location and an energy amount.
*/

import java.awt.Point;

public class EnergySource 
{
   protected Point energyLocation;
   protected double energyAmount;

   /*
      This constructor takes x and y coordinates for its location and
      a double for its units.
      @param x The x coordinate of the source.
      @param y The y coordinate of the source.
      @param amountUnits The energy units in the source.
   */
   public EnergySource(int x, int y, double amountUnits) 
   {
      Point energyPt = new Point(x, y);
      energyLocation = energyPt;
      energyAmount = amountUnits;
   }
   
   /*
      This constructor takes a Point for its location and a double for
      its units.
      @param pt The location of the source.
      @param amountUnits The energy units in the source.
   */
   public EnergySource(Point pt, double amountUnits)
   {
      energyLocation = pt;
      energyAmount = amountUnits;
   }
   
   /*
      The getLocation method retrieves the location of the energy source.
      @return The location of the energy source.
   */
   public Point getLocation() {
      return energyLocation;
   }
   
   /*
      The getAmount method retrieves the current energy units in the source.
      @return The current energy units in the source.
   */
   public double getAmount() {
      return energyAmount;
   }

   /*
      The setLocation method sets a location of the energy source.
      @param location The location to set for the source.
   */
   public void setLocation(Point location) {
      this.energyLocation = location;
   }

   /*
      The setAmount method sets a value for the energy units of the energy source.
      @param amount The value to set for the units of the source.
   */
   public void setAmount(double amount) {
      this.energyAmount = amount;
   }
}