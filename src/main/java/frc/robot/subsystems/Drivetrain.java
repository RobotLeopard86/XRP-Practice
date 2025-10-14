package frc.robot.subsystems;

import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private final XRPMotor leftMotor = new XRPMotor(0);
    private final XRPMotor rightMotor = new XRPMotor(1);

    public void setMotors(double lm, double rm) {
        leftMotor.set(lm);
        rightMotor.set(rm);
    }

    public void setLeftMotor(double lm) {
        leftMotor.set(lm);
    }

    public void setRightMotor(double rm) {
        rightMotor.set(rm);
    }
}
