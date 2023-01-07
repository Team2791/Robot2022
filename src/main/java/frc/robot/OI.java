package frc.robot;

import com.revrobotics.CANSparkMax;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DrivetrainCommands.ArcadeAim;
import frc.robot.commands.DrivetrainCommands.ArcadeDrive;
import frc.robot.commands.DrivetrainCommands.JoeJoeAim;
import frc.robot.commands.DrivetrainCommands.SimpleAlign;
import frc.robot.commands.DrivetrainCommands.VerySimpleAim;
// import frc.robot.commands.DrivetrainCommands.JoeJoeAim;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.DPadButton;
import frc.robot.controller.MultiButton;


public class OI {
    public static Joystick driverStick;
    public static Joystick operatorStick;
    public static Joystick pitStick; 

public CANSparkMax spin;

     Button driveButton;
    private Button driverLB, driverRB, pitRB, pitLB;
    private Button driverStart, driverBack;
    private Button operatorStart;
    private Button driverA, driverB, driverY;
    private Button driverDPadDown, driverDPadRight, driverDPadLeft, driverDPadUp, pitDPadUp, pitDPadDown;
    public Button operatorRB, operatorLT, operatorLB, operatorRT, pitRT, pitLT;
    public Button operatorLS, operatorBack, pitLS;
    private Button driverX;
    private Button driverRS, driverLS;
    private Button driverRX;
    protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed, operatorDPadUp, operatorDPadDown, operatorDPadLeft, operatorDPadRight;
    private Button operatorA, operatorB, operatorX, operatorY;
    private Button pitA, pitB, pitX, pitY; 
    private PIDController pid;
    private double output = 0;
  
    public OI(){
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        initButtons();

        Robot.drivetrain.arcadeDrive(-driverStick.getRawAxis(0), driverStick.getRawAxis(1));
        // driveButton.whenHeld(new ArcadeDrive(driverStick));
      
        operatorLT.whenHeld(new JoeJoeAim());
        operatorRT.whenHeld(new SimpleAlign());
        operatorA.whenHeld(new ArcadeAim());
        operatorY.whenHeld(new VerySimpleAim());
    }
       


    private void initButtons(){
        try{
//DRIVER BUTTONS//
            driverA = new JoystickButton(driverStick, 1);
            driverB = new JoystickButton(driverStick, 2);
            driverX = new JoystickButton(driverStick,3);
            driverY = new JoystickButton(driverStick,4);
            driverBack = new JoystickButton(driverStick, 7);
            driverStart = new JoystickButton(driverStick, 8);
            driverRB = new JoystickButton(driverStick, 6);
            driverLB = new JoystickButton(driverStick, 5);
            driverLS = new JoystickButton(driverStick,9);
            driverRS = new JoystickButton(driverStick,10);
            driverRX = new AnalogButton(driverStick, 4);
            driverDPadDown = new DPadButton(driverStick, DPadButton.kDPadDown);
            driverDPadRight = new DPadButton(driverStick, DPadButton.kDPadRight);
            driverDPadUp = new DPadButton(driverStick, DPadButton.kDPadUp);
            driverDPadLeft = new DPadButton(driverStick, DPadButton.kDPadLeft);
            driveButton = new MultiButton(new Button[] {
                new AnalogButton(driverStick, 3,2,0,0.2),
                driverRB, driverLB
            });

//OPERATOR BUTTONS//
            operatorA = new JoystickButton(operatorStick, 1);
            operatorB = new JoystickButton(operatorStick, 2);
            operatorX = new JoystickButton(operatorStick, 3);
            operatorY = new JoystickButton(operatorStick, 4);
            operatorBack = new JoystickButton(operatorStick,7);
            operatorStart = new JoystickButton(driverStick, 8);
            operatorRB = new JoystickButton(operatorStick, 6);
            operatorLB = new JoystickButton(operatorStick, 5);
            operatorLT = new AnalogButton(operatorStick, 2);
            operatorRT = new AnalogButton(operatorStick, 3);
            operatorLS = new AnalogButton(operatorStick, 1);

            operatorDPadDown = new DPadButton(operatorStick, DPadButton.kDPadDown);
            operatorDPadUp = new DPadButton(operatorStick, DPadButton.kDPadUp);

            operatorDPadLeft = new DPadButton(operatorStick, DPadButton.kDPadLeft);
            operatorDPadRight = new DPadButton(operatorStick, DPadButton.kDPadRight);

        }

        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }

    }
    

}