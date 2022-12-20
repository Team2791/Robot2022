// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
	private CANSparkMax leftLeader, rightLeader, leftFollower, rightFollower, flag;

	/** Creates a new Drivetrain. */
	public Drivetrain() {
		super.register();

		// init motors
		leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
		rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
		leftFollower = new CANSparkMax(RobotMap.leftFollowerID, MotorType.kBrushless);
		rightFollower = new CANSparkMax(RobotMap.rightFollowerID, MotorType.kBrushless);
		flag = new CANSparkMax(RobotMap.flagmID, MotorType.kBrushless);

		// set following
		leftFollower.follow(leftLeader);
		rightFollower.follow(rightLeader);

		// limit speed
		leftLeader.setSmartCurrentLimit(80);
		rightLeader.setSmartCurrentLimit(80);
	}

	public void setMotors(double speedL, double speedR) {
		leftLeader.set(speedL);
		rightLeader.set(speedR);

		leftFollower.set(speedL);
		rightFollower.set(speedR);
	}

	public void setFlag(double speed) {
		flag.set(speed);
	}

	public void brake() {
		leftLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
		rightLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
		leftFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
		rightFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
	}

	public void cruise() {
		leftLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
		rightLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
		leftFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
		rightFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
	}

	public double leftMotorSpeed() {
		return leftLeader.get();
	}

	public double rightMotorSpeed() {
		return rightLeader.get();
	}

	public RelativeEncoder getLeftEncoder() {
		return leftLeader.getEncoder();
	}

	public RelativeEncoder getRightEncoder() {
		return rightLeader.getEncoder();
	}

	public double getLeftPosition() {
		return getLeftEncoder().getPosition();
	}

	public double getRightPosition() {
		return getRightEncoder().getPosition();
	}

	public void resetEncoders() {
		getLeftEncoder().setPosition(0);
		getRightEncoder().setPosition(0);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Left Position", getLeftPosition());
		SmartDashboard.putNumber("Right Position", getRightPosition());

		SmartDashboard.putNumber("Left Speed", leftMotorSpeed());
		SmartDashboard.putNumber("Right Speed", rightMotorSpeed());
	}
}
