package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gyro;

public class TurnNinety extends Command {
    private Gyro gyro;
    private Drivetrain drive;

    private double initial;
    private double target;
    private final double speedMultiplier = 0.5;
    private final double minSpeed = 0.15;

    private PIDController pid = new PIDController(1, 0, 0);

    public TurnNinety(Drivetrain dt, Gyro gy) {
        drive = dt;
        gyro = gy;
        pid.enableContinuousInput(0, 360);
        pid.setTolerance(0.5);
        addRequirements(drive);
        addRequirements(gyro);
    }

    @Override
    public void initialize() {
        initial = gyro.getYaw();
        target = (initial + 90) % 360;
        SmartDashboard.putNumber("TN - Initial", initial);
        SmartDashboard.putNumber("TN - Target", target);
        SmartDashboard.putBoolean("TN - Active", true);
    }

    @Override
    public void execute() {
        double yaw = gyro.getYaw();
        double pidOut = pid.calculate(yaw, target);
        double speed = MathUtil.clamp(pidOut / (360 * -speedMultiplier), -1, 1);
        if(Math.abs(speed) < minSpeed) speed = minSpeed * (speed / Math.abs(speed));
        drive.setMotors(speed, speed);
        SmartDashboard.putNumber("TN - Current", yaw);
        SmartDashboard.putNumber("TN - PID Out", pidOut);
    }

    @Override
    public boolean isFinished() { 
        return pid.atSetpoint();
    }

    @Override
    public void end(boolean interrupt) {
        double yaw = gyro.getYaw();
        SmartDashboard.putNumber("TN - Current", yaw);
        SmartDashboard.putBoolean("TN - Active", false);
        drive.setMotors(0, 0);
    }
}
