// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private TalonFX climbLeft,climbRight;
  private Solenoid climbSol;


  public Climber() {
    super.register();
    climbLeft = new TalonFX(RobotMap.rightClimbID);
    climbRight = new TalonFX(RobotMap.rightClimbID);
    

    climbSol = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.climb);
    


     
//   }
//   public void setMotors(double speed) {
//     climbLeft.set(TalonFXControlMode.PercentOutput,speed);
//     climbRight.set(TalonFXControlMode.PercentOutput,speed);
    

//   }
//   public void setExtended() {
//     //pins in (motors can run)
//     climbSol.set(true);
//   }
//   public void setRetracted() {
//     //pins out (motors cannot run)
//     climbSol.set(false);
//   }
//   @Override
//   public void periodic() {
//     SmartDashboard.putNumber("Right Velocity", climbRight.getActiveTrajectoryVelocity());
//     SmartDashboard.putNumber("Left Velocity", climbLeft.getActiveTrajectoryVelocity());
//     // This method will be called once per scheduler run
//   }
// }
