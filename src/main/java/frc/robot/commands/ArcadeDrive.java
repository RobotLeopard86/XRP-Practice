package frc.robot.commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends Command {
    private final CommandXboxController xbox;
    private final DifferentialDrive dd;
    private final Drivetrain drive;

    public ArcadeDrive(CommandXboxController xb, Drivetrain dt) {
        xbox = xb;
        drive = dt;
        addRequirements(drive);
        drive.setDefaultCommand(this);

        dd = new DifferentialDrive(lm -> {drive.setLeftMotor(lm);}, rm -> {drive.setRightMotor(-rm);});
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double angle = Math.atan2(-xbox.getLeftX(), -xbox.getLeftY());
        double mag = Math.sqrt(Math.pow(xbox.getLeftX(), 2) + Math.pow(xbox.getLeftY(), 2));
        SmartDashboard.putNumber("Angle (Deg)", Units.radiansToDegrees(angle));
        SmartDashboard.putNumber("Angle (Rad)", angle);
        SmartDashboard.putNumber("Magnitude", mag);
        if(mag <= 0.15) {
            drive.setMotors(0, 0);
            return;
        }
        double adMag = Math.abs(angle) > (Math.PI / 2) ? -mag : mag;
        double rot = -xbox.getLeftX();
        SmartDashboard.putNumber("Angle-Adjusted Magn", adMag);
        SmartDashboard.putNumber("Robot Rotation Rate", rot);
        dd.arcadeDrive(adMag, rot);
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupt) {}
}
