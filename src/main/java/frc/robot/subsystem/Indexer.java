// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
	private CANSparkMax indexer1, indexer2;

	/** Creates a new Indexer. */
	public Indexer() {
		super.register();

		// init motors
		indexer1 = new CANSparkMax(42, MotorType.kBrushless);
		indexer2 = new CANSparkMax(41, MotorType.kBrushless);
	}

	public void setMotors(double speed1, double speed2) {
		indexer1.set(speed1);
		indexer2.set(speed2);
	}

	public double getSpeed1() {
		return indexer1.get();
	}

	public double getSpeed2() {
		return indexer2.get();
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Indexer1 Speed", getSpeed1());
		SmartDashboard.putNumber("Indexer2 Speed", getSpeed2());
	}
}
