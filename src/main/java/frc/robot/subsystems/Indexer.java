// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Indexer extends SubsystemBase {

  /** Creates a new Indexer. */
  private CANSparkMax bottomIndexer;  
  private CANSparkMax topIndexer;  

  public Indexer() {
    bottomIndexer = new CANSparkMax(RobotMap.bottomIndexerID, MotorType.kBrushless);
    topIndexer = new CANSparkMax(RobotMap.topIndexerID, MotorType.kBrushless);
  }

  public void setIndexerSpeed(double speed)
  {
    bottomIndexer.set(speed);
    topIndexer.set(speed);
  }

  public void stopIndexer()
  {
    bottomIndexer.set(0);
    topIndexer.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
