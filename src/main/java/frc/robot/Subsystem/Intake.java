// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax motor;
  private Solenoid piston1, piston2;
  public Intake()
  {
    motor = new CANSparkMax(RobotMap.intakeMotor, MotorType.kBrushless);
    piston1 = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.intakeLeft); // piston1 does not exist
    piston2 = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.intakeRight);
  }

  public void setIntake(boolean state)
  {
    piston1.set(state);

    piston2.set(state);
  }

  public void setMotor (double speed) 
  {
    motor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
