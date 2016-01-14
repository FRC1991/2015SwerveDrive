package org.usfirst.frc1991.SwerveDrive.subsystems;

import org.usfirst.frc1991.SwerveDrive.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An implementation of {@link edu.wpi.first.wpilibj.command.PIDSubsystem PIDSubsystem} that enables a motor to be driven on a PID loop with one not on the loop at the same time. */
public class SwerveModule extends PIDSubsystem {
String name;
AnalogInput steerEncoder;
SpeedController steerMotor;
SpeedController driveMotor;
/** Whether the setpoint has been locked or not. */
public Boolean setPointLocked;

/**
 * Create a new SwerveModule using the name, encoder, steering motor, and driving motor specified. P, I, D, and input range values will be adopted from {@link RobotMap}.
 * @param moduleName The name that will be visible in the SmartDashboard.
 * @param encoder The encoder to use as PID input.
 * @param steer The speed controller to use as PID output.
 * @param drive The speed controller that should not be connected to the PID loop; used for driving the wheel.
 */
public SwerveModule(String moduleName, AnalogInput encoder, SpeedController steer, SpeedController drive) {
        super("SwerveModule", RobotMap.P, RobotMap.I, RobotMap.D);
        steerEncoder = encoder;
        steerMotor = steer;
        driveMotor = drive;
        name = moduleName;
        setAbsoluteTolerance(0.2);
        setInputRange(RobotMap.minSetpoint, RobotMap.maxSetpoint);
        getPIDController().setContinuous(false); // By not being continuous, controller won't wrap around to reach destination (prevents wires from getting ripped out)
        setPointLocked = false;
        setSetpoint(RobotMap.forwardSetpoint); // Point forward by default
        enable();
        LiveWindow.addActuator(moduleName, "PID Controller", getPIDController());
        LiveWindow.addSensor(moduleName, "Encoder Reading", steerEncoder);
        LiveWindow.addActuator(moduleName, "Steer Motor", (LiveWindowSendable) steerMotor);
        LiveWindow.addActuator(moduleName, "Drive Motor", (LiveWindowSendable) driveMotor);
        SmartDashboard.putNumber(name + " offset", 0);
}

/** This subsystem has no default command, so nothing is initialized. */
public void initDefaultCommand() {
        // This subsystem has no default command
        return;
}


/**
 * Disables the subsystem by disabling the PID loop and the drive motor.
*/
@Override
public void disable() {
  super.disable(); // Shut down the PID loop
  driveMotor.disable(); // Shut down the driving motor
}

/**
 * Sets the speed of the driving motor controller.
 * @param speed The speed to set the motor controller to; this should be between -1 and 1.
*/
public void setSpeed(double speed) {
		SmartDashboard.putNumber(name + " drive", speed);
        driveMotor.pidWrite(speed);
}

/**
 * Sets the setpoint of the steering speed controller.
 * @param setpoint The setpoint to steer to. This must be between the min and max setpoints specified in {@link RobotMap} or a crash will most likely occur.
*/
@Override
public void setSetpoint(double setpoint) {
        if (setPointLocked != true) {
                super.setSetpoint(setpoint);
        }
}

/**
 * Locks the setpoint to the value specified. Future calls to {@link #setSetpoint(double)} will be ignored until {@link #unlockSetpoint()} is called.
 * @param setpointToLock The setpoint to lock the module to. This must be between the min and max setpoints specified in {@link RobotMap} or a crash will most likely occur.
*/
public void lockSetpoint(double setpointToLock) {
        setSetpoint(setpointToLock);
        setPointLocked = true;
}

/**
 * Unlocks setpoint. If the setpoint was not locked, nothing will occur.
 */
public void unlockSetpoint() {
        setPointLocked = false;
}

protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	double offset = SmartDashboard.getNumber(name + " offset");
        return steerEncoder.getAverageVoltage() + offset;
}

protected void usePIDOutput(double output) {
		SmartDashboard.putNumber(name + " steer", output);
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        steerMotor.pidWrite(output);
}
}
