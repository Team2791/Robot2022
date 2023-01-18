// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  private CANSparkMax climberLeft; 
  private CANSparkMax climberRight;
  /** Creates a new Climber. */
  public Climber() {
    super.register(); 
    climberLeft = new CANSparkMax(10, MotorType.kBrushless);
    climberRight = new CANSparkMax(11, MotorType.kBrushless);

     
  }

  public void setClimberLeft(double leftSpeed) {
    climberLeft.set(leftSpeed);
}
public void setClimberRight(double rightSpeed) {
    climberRight.set(rightSpeed);
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
