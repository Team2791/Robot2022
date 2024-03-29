// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  private CANSparkMax motor;
  private Solenoid left, right;

  public Intake() {
      super.register();
      motor = new CANSparkMax(RobotMap.intakeMotor, MotorType.kBrushless);
      motor.setIdleMode(IdleMode.kBrake);

      left = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.intakeLeft);
      right = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.intakeRight);
  }

  public void setMotor(double speed) {
      motor.set(speed);
  }

  //Intake goes up (driver x, cant intake pieces)
  public void setExtended() {
      left.set(false);
      right.set(false);
  }

  //Intake goes down (driver b, intake pieces)
  public void setRetracted() {
      left.set(true);
      right.set(true);
  }

  @Override
  public void periodic() {
    //SmartDashboard.putBoolean("Intake", left.get());
    // This method will be called once per scheduler run
  }
}
