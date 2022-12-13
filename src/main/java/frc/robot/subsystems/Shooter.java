// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  private CANSparkMax flyWheelBack, flyWheelFront;
  private Solenoid shooterHood;

  public Shooter() {
    super.register();
    flyWheelBack= new CANSparkMax(31, MotorType.kBrushless);
    flyWheelFront = new CANSparkMax(32, MotorType.kBrushless);
    flyWheelBack.setIdleMode(IdleMode.kCoast);
    flyWheelFront.setIdleMode(IdleMode.kCoast);
    shooterHood = new Solenoid(PneumaticsModuleType.REVPH, 3); 
  }

  public void openHood(){
    shooterHood.set(true);
  }
  public void closeHood(){
    shooterHood.set(false);
  }

  public void setFlywheel1(double speed){
    flyWheelBack.set(speed);
  }
  public void setFlywheel2(double speed){
    flyWheelFront.set(-speed);
  }

 
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  
  }
}