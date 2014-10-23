/*
   Steven Law
   stlaw9@gmail.com
   Project #3: A curious or hungry robot
   
   The RobotMemory interface contains the method headers for each memory structure.
*/

import java.awt.Point;
import java.util.*;

public interface RobotMemory<E>
{
	public void forget();
	
	public int learn(E episode);
   
   public double getAmount(double need);
	
	public Point retrieve(double probability, Point robotPosition);
   
   public boolean emptyCheck();
   
   public boolean deleteCheck();
   
   public Memory getNext();
   
   public void dupCheck(EnergySource tempE);
}
