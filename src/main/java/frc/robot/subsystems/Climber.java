// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private CANSparkMax climbLeft,climbRight;
  private Solenoid climbSolRight, climbSolLeft;


  public Climber() {
    climbLeft = new CANSparkMax(RobotMap.rightClimbID, MotorType.kBrushless);
    climbRight = new CANSparkMax(RobotMap.rightClimbID, MotorType.kBrushless);
    climbSolLeft = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.climb);
    climbSolRight = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.climb);


     
  }
  public void setMotors(double left, double right)
  {
    climbLeft.set(left);
    climbRight.set(right);

  }
  public void setExtended()
  {
    climbSolRight.set(true);
    climbSolLeft.set(true);
  }
  public void setRetracted()
  {
    climbSolRight.set(false);
    climbSolLeft.set(false);

  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Right Velocity", climbRight.get());
    SmartDashboard.putNumber("Left Velocity", climbLeft.get());
    // This method will be called once per scheduler run
  }
}
