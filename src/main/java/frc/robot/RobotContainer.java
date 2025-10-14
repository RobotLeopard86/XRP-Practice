// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ArmAdjust;
import frc.robot.commands.LineFollow;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Drivetrain drivetrain = new Drivetrain();
  private final Gyro gyro = new Gyro();

  private final ArmAdjust aa;
  private final LineFollow lf;
  private final ArcadeDrive adrive;// = null;
  private final TankDrive tdrive = null;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController driverController = new XboxController(OperatorConstants.kDriverControllerPort);

  public SendableChooser<Command> chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    adrive = new ArcadeDrive(driverController, drivetrain);
    //tdrive = new TankDrive(driverController, drivetrain);

    aa = new ArmAdjust(driverController, new Arm());

    lf = new LineFollow(drivetrain);
    chooser.addOption("Line Follow", lf);
    SmartDashboard.putData(chooser);

    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {}
}
