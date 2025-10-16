package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPReflectanceSensor;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class LineFollow extends Command {
    private final XRPReflectanceSensor reflector = new XRPReflectanceSensor();
    private final Drivetrain drive;
    private final double tolerance = 0.001;
    private final double speed = 0.25;
    private final double leftCorrection = 1.025;
    private final double rightCorrection = -1;
    private final double turnMultiplier = 1.1;
    private double leftTurnAdjust = 1;
    private double rightTurnAdjust = 1;

    public LineFollow(Drivetrain dt) {
        drive = dt;
        addRequirements(drive);
        //drive.setDefaultCommand(this);
    }

    @Override
    public void initialize() {}

    //-1: a is smaller, 0: equivalent, 1: b is smaller
    private int toleranceCmp(double a, double b, double t) {
        int r = 0;
        if((a + t) < (b - t)) {
            r = -1;
        } else if((b + t) < (a + t)) {
            r = 1;
        }
        return r;
    }

    @Override
    public void execute() {
        double left = reflector.getLeftReflectanceValue();
        double right = reflector.getRightReflectanceValue();
        SmartDashboard.putNumber("Left Rfl", left);
        SmartDashboard.putNumber("Right Rfl", right);
        int cmp = toleranceCmp(left, right, tolerance);

        if(cmp == -1) {
            //Too far right
            rightTurnAdjust = turnMultiplier;
            leftTurnAdjust = 1;
        } else if(cmp == 1) {
            //Too far left
            leftTurnAdjust = turnMultiplier;
            rightTurnAdjust = 1;
        } else {
            leftTurnAdjust = 1;
            rightTurnAdjust = 1;
        }

        drive.setMotors(speed * leftCorrection * leftTurnAdjust, speed * rightCorrection * rightTurnAdjust);
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public void end(boolean interrupt) {}
}
