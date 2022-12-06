// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoShoot extends CommandBase {

  boolean elijah=false;
  Timer useless =new Timer();
  public AutoShoot() {
    // Use addRequirements() here to declare subsystem dependencies.

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
	useless.start();
	Robot.shooter.setspeedl(-0.2);
	Robot.shooter.setspeedr(0.3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
	if (useless.get()>=2){
		Robot.indexer.setUpperMotor(0.7);
		Robot.indexer.setLowerMotor(0.7);
	}
	if (useless.get()>=4){
		elijah=true;
	}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
	Robot.indexer.setUpperMotor(0);
	Robot.indexer.setLowerMotor(0);
	Robot.shooter.setspeedb(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elijah;
  }
}
