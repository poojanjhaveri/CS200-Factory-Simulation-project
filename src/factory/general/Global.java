/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.general;

/**
 *
 * @author David
 * Global data, mainly for Timer times
 */
public class Global {
	// Main speed across timers
	// changing this could cause inconsistencies in speed for Dongyoung's LaneManager.
	// default should be 20
    public static final int STANDARD_TIMER_SPEED = 20; // Can change to 10 to be faster; change back to 20 when done!
}

// Old Global class, as created by Kevin
///**
//*
//* @author Kevin
//* Global data to be used to assign part type every time constructor is called
//*/
//public class Global {
//   public static int part_num=0;
//}
