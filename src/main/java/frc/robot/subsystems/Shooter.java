// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  private CANSparkMax flyWheelBack, flyWheelFront;

  public Shooter() {
    super.register();
    flyWheelBack= new CANSparkMax(RobotMap.flyWheelBackID, MotorType.kBrushless);
    flyWheelFront = new CANSparkMax(RobotMap.flyWheelFrontID, MotorType.kBrushless);
    flyWheelBack.setIdleMode(IdleMode.kCoast);
    flyWheelFront.setIdleMode(IdleMode.kCoast);

    flyWheelBack.getPIDController().setP(Constants.BackFlywheelkP);
    flyWheelBack.getPIDController().setFF(Constants.BackFlywheelkFF);
    flyWheelBack.getPIDController().setD(Constants.BackFlywheelkD);
    //flyWheelBack.getPIDController().setOutputRange(-1, 1);

    flyWheelFront.getPIDController().setP(Constants.FrontFlywheelkP);
    flyWheelFront.getPIDController().setFF(Constants.FrontFlywheelkFF);
    flyWheelFront.getPIDController().setD(Constants.FrontFlywheelkD);
    //flyWheelFront.getPIDController().setOutputRange(-1, 1);

    flyWheelBack.enableVoltageCompensation(11.5);
    flyWheelFront.enableVoltageCompensation(11.5);

  }

  public void setBackFlywheel(double speed){
    flyWheelBack.set(speed);
  }
  public void setFrontFlywheel(double speed){
    flyWheelFront.set(speed);
  }

  public void setFrontShooterPID(final double setpoint){
    if (setpoint == 0) {
        setFrontFlywheel(setpoint);
    } else {
        flyWheelFront.getPIDController().setReference(setpoint, CANSparkMax.ControlType.kDutyCycle);
    }
  }

  public void setBackShooterPID(final double setpoint){
    if (setpoint == 0) {
        setFrontFlywheel(setpoint);
    } else {
        flyWheelBack.getPIDController().setReference(setpoint, CANSparkMax.ControlType.kDutyCycle);
    }
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Fly Wheel 1", flyWheelBack.get());
    SmartDashboard.putNumber("Fly Wheel 2", flyWheelFront.get());
    SmartDashboard.putNumber("Front Flywheel", flyWheelFront.getEncoder().getVelocity());
    SmartDashboard.putNumber("back Flywheel", flyWheelBack.getEncoder().getVelocity()); 
  }
}
