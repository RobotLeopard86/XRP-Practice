package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final XRPServo servo = new XRPServo(4);

    public void setAngle(Rotation2d rot) {
        servo.setAngle(rot.getDegrees());
    }

    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees(servo.getAngle());
    }
}
