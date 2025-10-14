package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmAdjust extends Command {
    private final XboxController xbox;
    private final Arm arm;

    public ArmAdjust(XboxController xb, Arm a) {
        xbox = xb;
        arm = a;
        addRequirements(arm);
        arm.setDefaultCommand(this);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double angle = arm.getAngle().getDegrees();

        if(xbox.getAButton() && angle <= 179) angle++;
        if(xbox.getYButton() && angle >= 1) angle--;

        arm.setAngle(Rotation2d.fromDegrees(angle));

        SmartDashboard.putNumber("Arm Angle", angle);
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupt) {}
}
