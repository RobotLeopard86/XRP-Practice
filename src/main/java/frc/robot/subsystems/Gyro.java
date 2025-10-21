package frc.robot.subsystems;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPGyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
    private final XRPGyro gyro = new XRPGyro();
    private MedianFilter mfx = new MedianFilter(20);
    private MedianFilter mfy = new MedianFilter(20);
    private MedianFilter mfz = new MedianFilter(20);
    private MedianFilter mfh = new MedianFilter(20);

    public Gyro() {
        gyro.reset();
    }

    public double getPitch() {
        return mfy.calculate(gyro.getAngleY());
    }

    public double getYaw() {
        return mfz.calculate(gyro.getAngleZ());
    }

    public double getRoll() {
        return mfx.calculate(gyro.getAngleX());
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Roll  (X)", getRoll());
        SmartDashboard.putNumber("Pitch (Y)", getPitch());
        SmartDashboard.putNumber("Yaw   (Z)", getYaw());
    }
}
