package org.usfirst.frc1991.SwerveDrive.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1991.SwerveDrive.OI;
import org.usfirst.frc1991.SwerveDrive.Robot;
import org.usfirst.frc1991.SwerveDrive.RobotMap;

/** Control the swerve drive using the gamepad. */
public class DriveWithGamepad extends Command {

public DriveWithGamepad() {
        requires(Robot.swerveDrive);
}

protected void initialize() {
}

protected void execute() {
        double leftTriggerAxis =  OI.getGamepad().getRawAxis(2);
        double rightTriggerAxis = OI.getGamepad().getRawAxis(3);
        double leftJoystickXAxis = OI.getGamepad().getRawAxis(0);
        // Depress right trigger to apply power, depress left trigger to remove power
        double speed = leftTriggerAxis - rightTriggerAxis;
        double direction = RobotMap.mapRange(leftJoystickXAxis, 1, -1, RobotMap.minSetpoint, RobotMap.maxSetpoint);
        SmartDashboard.putNumber("direction", direction);
        SmartDashboard.putNumber("speed", speed);
        Robot.swerveDrive.drive(direction, speed);
}

protected boolean isFinished() {
        return false;
}

protected void end() {
}

protected void interrupted() {
}

}
