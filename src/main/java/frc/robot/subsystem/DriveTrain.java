package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
	private CANSparkMax leftLeader;
	private CANSparkMax rightLeader;

	private Solenoid climber;

	/**
	 * Will get and return Singleton instance of this DriveTrain. Code should use
	 * the {@link #getInstance()} method to get the single instance (rather
	 * than trying to construct an instance of this class.)
	 */
	private final static DriveTrain INSTANCE = new DriveTrain();

	@SuppressWarnings("WeakerAccess")
	public static DriveTrain getInstance() {
		return INSTANCE;
	}

	public void setSpeed(double left, double right) {
		leftLeader.set(left);
		rightLeader.set(right);
	}

	public void climb() {
		setSpeed(0,0);
		climber.set(true);
	}

	private DriveTrain() {
		leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
		rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
		climber = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.climb);
	}
}

