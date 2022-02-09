// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private CANSparkMax upperClimber, lowerClimber;
  private Solenoid climberPiston;
  public Climber() {
    upperClimber = new CANSparkMax(RobotMap.upperClimbID, MotorType.kBrushless);
    lowerClimber = new CANSparkMax(RobotMap.lowrClimbID, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
