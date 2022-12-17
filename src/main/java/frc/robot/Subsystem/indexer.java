// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class indexer extends SubsystemBase {
  /** Creates a new indexer. */
  
private CANSparkMax motor1,motor2;


  public indexer() {
    motor1= new CANSparkMax(42, MotorType.kBrushless);
    motor2= new CANSparkMax(41, MotorType.kBrushless);

  }
  public void setMotor1(double speed){
    motor1.set(speed);
  }
  public void setMotor2(double speed){
    motor2.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
