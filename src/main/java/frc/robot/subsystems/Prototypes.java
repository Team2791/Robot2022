package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Prototypes extends SubsystemBase {
    private CANSparkMax motor;
    private Solenoid extender; 


    public Prototypes() {
        motor = new CANSparkMax(RobotMap.rightID, MotorType.kBrushless);
        extender = new Solenoid(PneumaticsModuleType.CTREPCM, RobotMap.solenoidID);
        setBrakeMode();
    }
    public void setBrakeMode() {
        motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }
    public void setMotor(double Speed) {
        motor.set(Speed);
    }
    public void setExtended() {
        extender.set(true);
    }
    public void setRetracted() {
        extender.set(false);
    }
    public double getVelocity() {
        return motor.get();
    }
}
