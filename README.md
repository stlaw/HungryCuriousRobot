HungryCuriousRobot
==================
####Introduction

This project's goal was to write a simulation experiment for a robot that is looking for energy in a virtual environment. Conceptually, the environment is a plane with dimensions 200 by 200 (X,Y). The robot is randomly placed at a location on the plane and is active. The robot becomes inactive when it runs out of energy. There are a total of 40 energy sources randomly placed in the envrionment at least 20 units apart. Each energy source contains 125 energy units. The simulation ends when the robot becomes inactive.

####Robot States

There are 3 robot states: curious, hungry, and inactive. The robot's energy storage is a fixed amount starting at 100.

* Curious: The robot is curious when it has more than half its energy.

* Hungry:The robot is hungry when it has half or less energy units. The robot only consumes energy when it is hungry. When the robot is hungry, it will attempt to retrieve a memory of an energy source location (if it has any). If it retrieves a memory, it will move to the energy source. If there is no memory, it will explore until it finds an energy source or becomes inactive.

* Inactive: The robot is inactive when its energy reaches < 0.

####Robot Memory Structure

The robot's memory stores the Point location (x,y) of any detected energy source. It will also remember the amount of energy at the sou rce. The robot's memory is maintained by a JCF LinkedList<E> that implements different "Memory Structures". The robot's memory can be structured to have either FIFO, LIFO, random, or closest-first retrieval policy. FIFO and LIFO structures use a queue and stack, respectively, whereas random will randomly retrieve a memory and closest-first will retrieve the energy source closest to its current location.

####Robot Memory Retrieval

The robot's memory retrieval program is not fully functional. It is not always able to retrieve the memory it stored. The robot's retrieval probability for any step is either 80%, 60%, or 40%. 

####Robot Moves

When the robot is hungry and retrieves an energy location, it moves directly to that location in its current step. At its destination, but before consuming energy from the source, the robot's energy level is decremented by the distance it just move (The energy level can become negative). The robot consumes energy until it is either full or there is no more energy left at the source. Empty sources are not detected on future moves and are forgotten from the robot's memory. 

The energy required for the robot to move is equal to the distance it moves. The robot always moves to a location that is +/- moveDistance (which is 7). For example, the robot can move +7, 0, or -7 units on X and +7, 0, or -7 units on Y from its current location. Thus, the longest diagonal move the robot can make is nearly 10 units (9.899) and the shortest move is 7 units. The robot cannot move off the plane. While in the curious state, the robot will generate random moves until it is a valid move. If the robot is in the hungry state, it moves directly to its target.

####Energy Detection

After each move, the robot detects and learns all energy sources within a 15 unit radius of its current location. A robot only stores one memory for each energy location. Re-detection wil change the order of the memory stores.

####Simulation Experiment

The goal is to gather statistics on the average distance traveled by the robot for 20 samples in each of the 16 (4 memory structures by 4 retrieval probabilities) experimental conditions.
