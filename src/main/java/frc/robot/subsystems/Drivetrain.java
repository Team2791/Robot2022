package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
    private CANSparkMax leftLeader, rightLeader, leftFollower, rightFollower;
    private RelativeEncoder rightEncoder, leftEncoder;
    private final AHRS gyro;
    private DifferentialDriveKinematics kinematics;
    private DifferentialDriveOdometry odometry;
    private Pose2d pose;
    private SimpleMotorFeedforward feedForward;
    private PIDController leftPIDController, rightPIDController;

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

        kinematics = new DifferentialDriveKinematics(Constants.TrackWidthMeters);
        odometry = new DifferentialDriveOdometry(getRotation());
        feedForward = new SimpleMotorFeedforward(Constants.kS, Constants.kV, Constants.kA);
        leftPIDController = new PIDController(Constants.kP, 0.0, 0.0);
        rightPIDController = new PIDController(Constants.kP, 0.0, 0.0);
    }

    public void resetGyro() {
        gyro.reset();
    }

    public void setMotors(double left, double right) {
        leftLeader.set(left);
        rightLeader.set(right);
    }

    public void setBrakeMode() {
        leftLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        leftFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);

    }

    public void setCoastMode() {
        leftLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
        leftFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);

    }

    public double getAngle() {
        return gyro.getYaw();
    }

    public Rotation2d getRotation() {
        return Rotation2d.fromDegrees(-gyro.getYaw());
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

    public void setRampUp(double time) {
        leftLeader.setOpenLoopRampRate(time);
        rightLeader.setOpenLoopRampRate(time);
    }

    Pose2d getPose() {
        return pose;
    }

    DifferentialDriveWheelSpeeds getSpeeds() {
        return new DifferentialDriveWheelSpeeds(
                leftEncoder.getVelocity(),
                rightEncoder.getVelocity());
    }

    void setOutput(double leftVolts, double rightVolts) {
        leftLeader.set(leftVolts / 12.0);
        rightLeader.set(rightVolts / 12.0);
    }

    public Command followTrajectory(Trajectory t) {
        return new RamseteCommand(
                t,
                this::getPose,
                new RamseteController(),
                feedForward,
                kinematics,
                this::getSpeeds,
                leftPIDController,
                rightPIDController,
                this::setOutput,
                this);
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Right Velocity", rightLeader.get());
        // SmartDashboard.putNumber("Left Velocity", leftLeader.get());
        SmartDashboard.putNumber("Right Position", getRightPosition());
        SmartDashboard.putNumber("Left Position", getLeftPosition());
        SmartDashboard.putNumber("angle", getAngle());

        pose = odometry.update(getRotation(), getLeftPosition(), getRightPosition());
    }
}