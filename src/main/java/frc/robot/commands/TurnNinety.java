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
    private final double speed = 0.5;

    public TurnNinety(Drivetrain dt, Gyro gy) {
        drive = dt;
        gyro = gy;
        addRequirements(drive);
        addRequirements(gyro);
    }

    @Override
    public void initialize() {
        initial = gyro.getYaw();
        if(initial < 0) initial += 360;
        target = (initial + 90);
        if(target < 0) target += 360;
        SmartDashboard.putNumber("TN - Initial", initial);
        SmartDashboard.putNumber("TN - Target", target);
    }

    @Override
    public void execute() {
        drive.setMotors(speed, speed);
        double yaw = gyro.getYaw();
        if(yaw < 0) yaw += 360;
        if(target > 360) yaw = 360 - yaw;
        SmartDashboard.putNumber("TN - Current", yaw);
    }

    @Override
    public boolean isFinished() { 
        double yaw = gyro.getYaw();
        if(yaw < 0) yaw = 360 - yaw;
        if(target > 360) yaw = 360 - yaw;
        return yaw >= target;
    }

    @Override
    public void end(boolean interrupt) {
        double yaw = gyro.getYaw();
        if(yaw < 0) yaw = 360 - yaw;
        if(target > 360) yaw = 360 - yaw;
        SmartDashboard.putNumber("TN - Current", yaw);
        drive.setMotors(0, 0);
    }
}
