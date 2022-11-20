// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
	private final CANSparkMax left;
	private final CANSparkMax right;

	public Shooter() {
		left = new CANSparkMax(RobotMap.flyWheelBackID, MotorType.kBrushless);
		right = new CANSparkMax(RobotMap.flyWheelFrontID, MotorType.kBrushless);
	}

	public void setSpeedL(double s) {
		left.set(s);
	}

	public void setSpeedR(double s) {
		right.set(s);
	}

	public void setSpeed(double s) {
		setSpeedR(s);
		setSpeedL(s);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
