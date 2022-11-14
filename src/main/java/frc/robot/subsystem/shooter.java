// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooter extends SubsystemBase {
  private CANSparkMax left,right;
  /** Creates a new shooter. */
  public shooter() {
      left= new CANSparkMax (7, MotorType.kBrushless);
      right = new CANSparkMax (8,MotorType.kBrushless);
  }
  public void setspeedl(double s){
    left.set(s); 
  }
  public void setspeedr(double s){
    right.set(s);
  }
  public void setspeedb(double s){
    right.set(s);
    left.set(s);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
