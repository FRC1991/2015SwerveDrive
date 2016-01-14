package org.usfirst.frc1991.SwerveDrive.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc1991.SwerveDrive.RobotMap;
import org.usfirst.frc1991.SwerveDrive.commands.DriveWithGamepad;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class implements a basic swerve drive with multiple modes using {@link SwerveModule SwerveModules}.
 * @see SwerveModule
 */
public class SwerveDrive extends Subsystem {
List<SwerveModule> modules = new ArrayList<SwerveModule>();

/** The modes the swerve drive can be set to. */
public enum SwerveMode {
        /** Use the front wheels to steer and keep the back wheels straight. */
        FrontDriveBackLock,
        /** Use the back wheels to steer and keep the front wheels straight. */
        FrontLockBackDrive,
        /** Use the front and back wheels to steer in the same direction. */
        FrontDriveBackDrive,
        /** Keep all the wheels at a 0 degree angle. */
        StrafeLeft,
        /** Keep all the wheels at an 180 degree angle. */
        StrafeRight,
        /** Prevent all motors from moving. */
        Disabled;
        /** Get the next available swerve mode. If at the end of the list, wrap around to the first mode. */
        public SwerveMode nextMode() {
                return values()[(ordinal() + 1) % values().length];
        }
        /** Get the previous swerve mode. If at the beginning of the list, wrap around to the last mode. */
        public SwerveMode previousMode() {
                return values()[(this.ordinal() + values().length - 1) % values().length];
        }
};
/** The current mode the swerve drive is in. */
public SwerveMode mode;

/** Initialize a four-wheel swerve drive using four SwerveModules. */
public SwerveDrive(SwerveModule FrontLeft, SwerveModule FrontRight, SwerveModule BackLeft, SwerveModule BackRight) {
        super("SwerveDrive");
        modules.add(FrontLeft);
        modules.add(FrontRight);
        modules.add(BackLeft);
        modules.add(BackRight);
        setMode(SwerveMode.FrontDriveBackLock); // Default mode
}


/**
 * Changes the swerve drive mode to the mode specified.
 * @param newMode The mode to set the swerve drive to.
 */
public void setMode(SwerveMode newMode) {
		SmartDashboard.putString("mode", newMode.toString());
        // Re-enable SwerveModules after mode changed from Disabled
        if (mode == SwerveMode.Disabled) {
                for (SwerveModule mod: modules) {
                        mod.enable();
                }
        }
        mode = newMode;
        int index = 0; // Used for iteration
        switch(newMode) {
        case Disabled:
                for (SwerveModule mod: modules) {
                        mod.setSpeed(0);
                        mod.disable();
                }
                break;
        case FrontDriveBackDrive:
                for (SwerveModule mod: modules) {
                        mod.unlockSetpoint();
                        mod.setSetpoint(RobotMap.forwardSetpoint);
                }
                break;
        case FrontDriveBackLock:
                for (SwerveModule mod: modules) {
                        if (index < 2) {
                                mod.unlockSetpoint();
                                mod.setSetpoint(RobotMap.forwardSetpoint);
                        }
                        else {
                            mod.unlockSetpoint();

                                mod.lockSetpoint(RobotMap.forwardSetpoint);
                        }
                        index++;
                }
                break;
        case FrontLockBackDrive:
                for (SwerveModule mod: modules) {
                        if (index > 1) {
                                mod.unlockSetpoint();
                        }
                        else {
                            mod.unlockSetpoint();

                                mod.lockSetpoint(RobotMap.forwardSetpoint);
                        }
                        index++;
                }
                break;
        case StrafeLeft:
                for (SwerveModule mod: modules) {
                		mod.unlockSetpoint();
                        mod.lockSetpoint(RobotMap.leftSetpoint);
                }
                break;
        case StrafeRight:
                for (SwerveModule mod: modules) {
                		mod.unlockSetpoint();
                        mod.lockSetpoint(RobotMap.rightSetpoint);
                }
                break;
        default:
                break;

        }
}

/**
 * The main command used to drive the swerve drive.
 * @param direction A number between minSetpoint and maxSetpoint that the unlocked wheels will be directed to.
 * @param speed A number between -1 and 1 that every wheel's speed will be set to.
 */
public void drive(double direction, double speed) {
        if (mode != SwerveMode.Disabled) {
                for (SwerveModule mod: modules) {
                        mod.setSetpoint(direction);
                }
        		modules.get(0).setSpeed(speed * -1);
        		modules.get(1).setSpeed(speed);
        		modules.get(2).setSpeed(speed * -1);
        		modules.get(3).setSpeed(speed);


        }

}



/** The default command for this subsystem is {@link DriveWithGamepad}. */
@Override
protected void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
}

}
