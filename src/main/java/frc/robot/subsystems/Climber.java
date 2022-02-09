// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private CANSparkMax LCM,RCM;

  public Climber() {
    LCM = new CANSparkMax(RobotMap.rightClimbID, MotorType.kBrushless);
    RCM = new CANSparkMax(RobotMap.rightClimbID, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Right Velocity", LCM.get());
    SmartDashboard.putNumber("Left Velocity", RCM.get());
    // This method will be called once per scheduler run
  }
}
