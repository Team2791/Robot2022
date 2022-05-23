package frc.robot.subsystems;

import frc.robot.*;
import frc.robot.commands.DriveWithJoystick;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.io.IOException;
import java.nio.file.Path;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax leftLeader, rightLeader, leftFollower, rightFollower;
    private RelativeEncoder rightEncoder, leftEncoder;
    private final MotorControllerGroup m_rightMotors =
        new MotorControllerGroup(rightLeader,rightFollower);
    private final MotorControllerGroup m_leftMotors =
        new MotorControllerGroup(leftLeader,leftFollower);
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
    private final DifferentialDriveOdometry m_odometry;
    
    private final AHRS m_gyro;
     public Drivetrain() {
        super.register();
        m_gyro = new AHRS(Port.kMXP);

        leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
        rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
        rightLeader.setInverted(true);
        leftFollower = new CANSparkMax(RobotMap.leftFollowerID, MotorType.kBrushless);
        leftFollower.follow(leftLeader, false);
        rightFollower = new CANSparkMax(RobotMap.rightFollowerID, MotorType.kBrushless);
        rightFollower.follow(rightLeader, false);

        leftEncoder = leftLeader.getEncoder();
        rightEncoder = rightLeader.getEncoder();
        leftEncoder.setPositionConversionFactor(Constants.kEncoderPositionConversionFactor);
        rightEncoder.setPositionConversionFactor(Constants.kEncoderPositionConversionFactor);
        leftEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
        rightEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
        resetEncoders();
        m_odometry= new DifferentialDriveOdometry(m_gyro.getRotation2d());
        //setBrakeMode();
        setCoastMode();
    }

    public void setMotors(double left, double right){
        leftLeader.set(left);
        rightLeader.set(right);
    }
    public Pose2d getPose(){
        return m_odometry.getPoseMeters();
    }
    public void resetOdometery(Pose2d pose){
        resetEncoders();
        m_gyro.zeroYaw();
        m_odometry.resetPosition(pose, m_gyro.getRotation2d());
    }
    public void setBrakeMode() {
        leftLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        leftFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);

    }
    public DifferentialDriveWheelSpeeds getWheelSpeeds(){
        return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
    }
    public void tankDriveVolts(double leftV, double rightV){
        m_leftMotors.setVoltage(leftV);
        m_rightMotors.setVoltage(rightV);
        m_drive.feed();


    }
    public double getAverageEncoderDistance(){
        return(leftEncoder.getPosition() + rightEncoder.getPosition())/2.0;

    }
    public void setCoastMode() {
        leftLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
        leftFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);

    }

    public CANSparkMax getLeftMotor(){
        return leftLeader;
    }

    public CANSparkMax getRightMotor(){
        return rightLeader;
    }
    
    public RelativeEncoder getLeftEncoder(){
        return leftEncoder;
    }

    public RelativeEncoder getRightEncoder(){
        return rightEncoder;
    }

    public double getLeftPosition(){
        return leftEncoder.getPosition();
    }

    public double getRightPosition(){
        return rightEncoder.getPosition();
    }
    public void resetEncoders()
    {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Right Velocity", rightLeader.get());
        SmartDashboard.putNumber("Left Velocity", leftLeader.get());
        SmartDashboard.putNumber("Right Position", getRightPosition());
        SmartDashboard.putNumber("Left Position", getLeftPosition());


    }

    //THIS IS ME TRYING TO MAKE SOMETHING THAT CHAINS TRAJECTORIES LIKE SOME TEAMS I HAVE SEEN
    // public Trajectory loadTrajectoryFromFile(String filename) {
              
    //   String t = "C:\\Users\\mdhus\\OneDrive\\Desktop\\ROBOT\\Robot2022\\src\\main\\deploy\\paths\\"+filename+".wpilib.json";
    //   Path trajPath = Filesystem.getDeployDirectory().toPath().resolve(t);
    //   Trajectory testTrajectory ;
    //   try{
    //     testTrajectory = TrajectoryUtil.fromPathweaverJson(trajPath);
    //     return testTrajectory;
    //   }
    //   catch (IOException ex){
    //     System.out.println("path error");
    //   } 
    //   return null;
    //   }
    
    }
    
