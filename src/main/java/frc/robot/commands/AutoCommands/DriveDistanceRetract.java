// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

public class DriveDistanceRetract extends CommandBase {
  /** Creates a new DriveDistance. */
  double distanceLeft, distanceRight, motorSpeed;
  boolean finished;
  Timer timer;
  public DriveDistanceRetract(double left, double right, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
    distanceLeft = left;
    distanceRight = right;
    motorSpeed = speed;
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drivetrain.resetEncoders();
    Robot.drivetrain.setRampUp(Constants.autoRampUp);
    Robot.drivetrain.setMotors(motorSpeed,motorSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.drivetrain.getLeftPosition()>distanceLeft && Robot.drivetrain.getRightPosition()> distanceRight) {
      Robot.drivetrain.setMotors(0,0);
      timer.start();
    }
    if(timer.get()>1.2) {
      finished = true; 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.setRampUp(0);

    Robot.intake.setExtended();
    Robot.intake.setMotor(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
