package org.usfirst.frc1991.SwerveDrive.commands;

import org.usfirst.frc1991.SwerveDrive.Robot;
import org.usfirst.frc1991.SwerveDrive.subsystems.SwerveDrive.SwerveMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward for 2 seconds, and strafe right for 3 seconds
 */
public class TestDrive extends Command {

public TestDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.swerveDrive);
}

// Called just before this Command runs the first time
protected void initialize() {
}

// Called repeatedly when this Command is scheduled to run
protected void execute() {
        Robot.swerveDrive.setMode(SwerveMode.FrontDriveBackLock);
        Robot.swerveDrive.drive(1, 0.5);
        Timer.delay(2);
        Robot.swerveDrive.setMode(SwerveMode.StrafeLeft);
        Robot.swerveDrive.drive(1, -0.5);
        Timer.delay(3);
}

protected boolean isFinished() {
        return false;
}
protected void end() {
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted() {
}
}
