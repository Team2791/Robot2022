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
  private CANSparkMax flyWheelOne, flyWheelTwo;

  public Shooter() {
    flyWheelOne = new CANSparkMax(RobotMap.flyWheelOneID, MotorType.kBrushless);
    flyWheelTwo = new CANSparkMax(RobotMap.flyWheelTwoID, MotorType.kBrushless);

  }

  public void setFlyWheelOne(double speed){
    flyWheelOne.set(speed);
  }
  public void setFlyWheelTwo(double speed){
    flyWheelTwo.set(speed);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
