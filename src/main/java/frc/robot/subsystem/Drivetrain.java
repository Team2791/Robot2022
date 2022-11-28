package frc.robot.subsystem;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
	private final AHRS gyro;
	private final CANSparkMax leftLeader;
	private final CANSparkMax rightLeader;
	private final CANSparkMax leftFollower;
	private final CANSparkMax rightFollower;
	private final RelativeEncoder rightEncoder;
	private final RelativeEncoder leftEncoder;

	public Drivetrain() {
		super.register();
		leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
		rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
		rightLeader.setInverted(true);

		leftFollower = new CANSparkMax(RobotMap.leftFollowerID, MotorType.kBrushless);
		leftFollower.follow(leftLeader, false);
		rightFollower = new CANSparkMax(RobotMap.rightFollowerID, MotorType.kBrushless);
		rightFollower.follow(rightLeader, false);

		leftLeader.setSmartCurrentLimit(80);
		leftFollower.setSmartCurrentLimit(80);
		rightLeader.setSmartCurrentLimit(80);
		rightFollower.setSmartCurrentLimit(80);

		leftEncoder = leftLeader.getEncoder();
		rightEncoder = rightLeader.getEncoder();

		leftEncoder.setPositionConversionFactor(Constants.conversionFactor);
		rightEncoder.setPositionConversionFactor(Constants.conversionFactor);
		gyro = new AHRS(Port.kMXP);
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void setMotors(double left, double right) {
		leftLeader.set(left);
		rightLeader.set(right);
	}


	public double getAngle() {
		return gyro.getYaw();
	}


	public CANSparkMax getLeftMotor() {
		return leftLeader;
	}

	public CANSparkMax getRightMotor() {
		return rightLeader;
	}

	public RelativeEncoder getLeftEncoder() {
		return leftEncoder;
	}

	public RelativeEncoder getRightEncoder() {
		return rightEncoder;
	}

	public double getLeftPosition() {
		return leftEncoder.getPosition();
	}

	public double getRightPosition() {
		return rightEncoder.getPosition();
	}

	public void resetEncoders() {
		rightEncoder.setPosition(0);
		leftEncoder.setPosition(0);
	}

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Left position", getLeftPosition());
		SmartDashboard.putNumber("Right position", getRightPosition());

	}
}