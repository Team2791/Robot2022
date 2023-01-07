// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;


public class VerySimpleAim extends CommandBase {
  
  private boolean finished = false;
  PhotonCamera camera = new PhotonCamera("2791photonvision");
  public VerySimpleAim() {
    addRequirements(Robot.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    var result = camera.getLatestResult();
    if(result.hasTargets() && result.getBestTarget().getYaw() > 0.5) {
      Robot.drivetrain.setMotors(-.2,2);
    }
    if(result.hasTargets() && result.getBestTarget().getYaw() < 0.5) {
      Robot.drivetrain.setMotors(.2,-.2);
    }
    if(result.getBestTarget().getYaw()<0.5 && result.getBestTarget().getYaw()>-0.5) {
      finished = true; 
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("VERY SIMPLE Complete", true);
    Robot.drivetrain.setMotors(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
    
  }
}
