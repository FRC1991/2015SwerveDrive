// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1991.SwerveDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc1991.SwerveDrive.commands.*;
import org.usfirst.frc1991.SwerveDrive.subsystems.*;

/**
 * The main class that runs the show.
 * If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

Command autonomousCommand;

public static OI oi;
public static SwerveDrive swerveDrive;
/**
 * This function is run when the robot is first started up and should be
 * used for any initialization code.
 */
public void robotInit() {
        RobotMap.init();
        swerveDrive = new SwerveDrive(RobotMap.FL, RobotMap.FR, RobotMap.BL, RobotMap.BR);
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        autonomousCommand = new TestDrive();
}

/**
 * This function is called when the disabled button is hit. You can use it to reset subsystems before shutting down.
 */
public void disabledInit() {

}

public void disabledPeriodic() {
        Scheduler.getInstance().run();
}

public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
}

/**
 * This function is called periodically during autonomous.
 */
public void autonomousPeriodic() {
        Scheduler.getInstance().run();
}

public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
}

/**
 * This function is called periodically during operator control.
 */
public void teleopPeriodic() {
        Scheduler.getInstance().run();
}

/**
 * This function is called periodically during test mode.
 */
public void testPeriodic() {
        LiveWindow.run();
}
}