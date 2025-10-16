package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gyro;

public class TurnNinety extends Command {
    private Gyro gyro;
    private Drivetrain drive;

    private double initial;
    private double target;
    private final double speed = 0.3;

    public TurnNinety(Drivetrain dt, Gyro gy) {
        drive = dt;
        gyro = gy;
        addRequirements(drive);
        addRequirements(gyro);
    }

    @Override
    public void initialize() {
        initial = gyro.getYaw();
        target = (initial + 90) % 360;
    }

    @Override
    public void execute() {
        drive.setMotors(speed, speed);
        SmartDashboard.putNumber("TN - Initial", initial);
        SmartDashboard.putNumber("TN - Target", target);
        SmartDashboard.putNumber("TN - Current", gyro.getYaw());
    }

    @Override
    public boolean isFinished() { 
        double yaw = gyro.getYaw();
        return (target < initial ? yaw + 360 : yaw) >= (target < initial ? target + 360 : target);
    }

    @Override
    public void end(boolean interrupt) {}
}
