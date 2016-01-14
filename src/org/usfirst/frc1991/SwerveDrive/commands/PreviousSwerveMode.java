package org.usfirst.frc1991.SwerveDrive.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1991.SwerveDrive.Robot;

/** Changes the swerve drive mode to the next available one. */
public class PreviousSwerveMode extends Command {

Boolean finished = false;

public PreviousSwerveMode() {
}

protected void initialize() {
	Robot.swerveDrive.setMode(Robot.swerveDrive.mode.previousMode());
	finished = true;
}

protected boolean isFinished() {
        return finished;
}

@Override
protected void execute() {
	// TODO Auto-generated method stub

}

@Override
protected void end() {
	// TODO Auto-generated method stub

}

@Override
protected void interrupted() {
	// TODO Auto-generated method stub

}
}
