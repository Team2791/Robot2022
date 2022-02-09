// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  private CANSparkMax flyWheelBack, flyWheelFront;

  public Shooter() {
    flyWheelBack= new CANSparkMax(RobotMap.flyWheelBackID, MotorType.kBrushless);
    flyWheelFront = new CANSparkMax(RobotMap.flyWheelFrontID, MotorType.kBrushless);

  }

  public void setBackFlyWheel(double speed){
    flyWheelBack.set(speed);
  }
  public void setFrontWheelTwo(double speed){
    flyWheelFront.set(speed);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Fly Wheel 1 Velocity", flyWheelBack.get());
    SmartDashboard.putNumber("Fly Wheel 2 Velocity", flyWheelFront.get());
  }
}
