// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight.CamMode;
import frc.robot.subsystems.Limelight.LedMode;

public class DriveTo extends CommandBase {
  PIDController pid;
  /** Creates a new JoeAim. */
  
  public DriveTo() {
    addRequirements(Robot.drivetrain);
    addRequirements(Robot.limelight);
    pid = new PIDController(Constants.LimelightkP, Constants.LimelightkI, Constants.LimelightkD);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.limelight.setCamMode(CamMode.VISION_CAM);
    Robot.limelight.setLedMode(LedMode.PIPELINE);
    pid.setSetpoint(Constants.LimelightSetpoint);
    pid.setTolerance(0.1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double output = pid.calculate(Robot.limelight.getHorizontalDist());
    Robot.drivetrain.setMotors(output, output);
    SmartDashboard.putBoolean("Drivetrain Align Complete", false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Drivetrain Align Complete", true);
    Robot.drivetrain.setMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
    
  }
}
