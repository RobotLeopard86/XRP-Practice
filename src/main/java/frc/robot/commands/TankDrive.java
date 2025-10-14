package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class TankDrive extends Command {
    private final XboxController xbox;
    private final Drivetrain drive;

    public TankDrive(XboxController xb, Drivetrain dt) {
        xbox = xb;
        drive = dt;
        addRequirements(drive);
        drive.setDefaultCommand(this);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        drive.setMotors(-xbox.getLeftY(), xbox.getRightY());
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupt) {}
}
