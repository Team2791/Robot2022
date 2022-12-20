// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
	private CANSparkMax shooter1, shooter2;

	/** Creates a new Shooter. */
	public Shooter() {
		super.register();

		// init motors
		shooter1 = new CANSparkMax(31, MotorType.kBrushless);
		shooter2 = new CANSparkMax(32, MotorType.kBrushless);
	}

	public void setMotors(double speed1, double speed2) {
		shooter1.set(speed1);
		shooter2.set(speed2);
	}

	public double getSpeed1() {
		return shooter1.get();
	}

	public double getSpeed2() {
		return shooter2.get();
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Shooter1 Speed", getSpeed1());
		SmartDashboard.putNumber("Shooter2 Speed", getSpeed2());
	}
}
