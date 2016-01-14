package org.usfirst.frc1991.SwerveDrive;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc1991.SwerveDrive.subsystems.SwerveModule;

/**
 * The RobotMap is a mapping from the ports, sensors, and actuators wired into a variable name.
 * This provides flexibility, makes checking the wiring easier, and significantly reduces the number of magic numbers floating around.
 * <br><br><iframe src = "2015-Mapping.html" width = "100%" height = "200px"></iframe>
*/
public class RobotMap {
/** P value to be used in internal PID loop. */
public static double P = 1;
/** I value to be used in internal PID loop. */
public static double I = 0;
/** D value to be used in internal PID loop. */
public static double D = 0;
/** The lowest setpoint the steering motor should be allowed to go to. */
public static double minSetpoint = 1.5;
/** The highest setpoint the steering motor should be allowed to go to. */
public static double maxSetpoint = 3.5;
/** The setpoint value that denotes pointing 90 degrees forward. */
public static double forwardSetpoint = 2.5;
/** The setpoint value that denotes pointing 0 degrees left. */
public static double leftSetpoint = 3.5;
/** The setpoint value that denotes pointing 180 degrees right. */
public static double rightSetpoint = 1.5;
/** The front left swerve module. */
public static SwerveModule FR;
/** The front right swerve module. */
public static SwerveModule FL;
/** The back left swerve module. */
public static SwerveModule BL;
/** The back right swerve module. */
public static SwerveModule BR;

/** Initialize Talons and encoders for each swerve module. */
public static void init() {
        FR = new SwerveModule("FR", new AnalogInput(4), new Talon(10), new Talon(0));
        FL = new SwerveModule("FL", new AnalogInput(5), new Talon(11), new Talon(1));
        BR = new SwerveModule("BR", new AnalogInput(6), new Talon(12), new Talon(2));
        BL = new SwerveModule("BL", new AnalogInput(7), new Talon(13), new Talon(3));
}

/** Proportion a value from one range to another.
 * Example: 5 in a range from 0-10 would become 500 in a range from 0-1000.
 * 1:1 recreation of <a target = "_blank" href = "https://www.arduino.cc/en/Reference/Map">Arduino's map function</a>.
 * @param value The value to proportion.
 * @param fromLow The minimum value of the original range.
 * @param fromHigh The maximum value of the original range.
 * @param toLow The minimum value of the new range.
 * @param toHigh The maximum value of the new range.
 */
public static double mapRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return (value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
}

}
