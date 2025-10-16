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

    public Gyro() {
        gyro.reset();
    }

    public double getPitch() {
        return mfx.calculate(gyro.getAngleY());
    }

    public double getYaw() {
        return mfx.calculate(gyro.getAngleZ());
    }

    public double getRoll() {
        return mfx.calculate(gyro.getAngleX());
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Rotation (X)", mfx.calculate(gyro.getAngleX()));
        SmartDashboard.putNumber("Rotation (Y)", mfy.calculate(gyro.getAngleY()));
        SmartDashboard.putNumber("Rotation (Z)", mfz.calculate(gyro.getAngleZ()));
    }
}
