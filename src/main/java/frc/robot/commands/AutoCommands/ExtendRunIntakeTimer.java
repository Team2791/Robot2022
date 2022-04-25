// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ExtendRunIntakeTimer extends CommandBase {
  /** Creates a new ExtendRunIntake. */
  Timer timer = new Timer();
  public ExtendRunIntakeTimer() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    //Robot.intake.setRetracted();
    // Robot.intake.setMotor(Constants.intakeSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   if(timer.get()>.2){ //.375
     Robot.intake.setRetracted();
   }
    Robot.intake.setMotor(Constants.intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.intake.setMotor(0);
    Robot.intake.setExtended();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
