package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import frc.robot.commands.IntakeIndexerEnd;
//import frc.robot.commands.IntakeIndexerStart;
import frc.robot.commands.IntakeCommands.*;
import frc.robot.commands.ShooterCommands.*;
import frc.robot.commands.AutoCommands.PidTesting;
import frc.robot.commands.AutoCommands.TurnCounterClock;
import frc.robot.commands.AutoCommands.TurnCounterClockwisePID;
import frc.robot.commands.ClimberCommands.*;
import frc.robot.commands.DrivetrainCommands.DriveWithJoystick;
import frc.robot.commands.DrivetrainCommands.SetBrakeMode;
import frc.robot.commands.DrivetrainCommands.SetCoastMode;
import frc.robot.commands.DrivetrainCommands.creep;
import frc.robot.commands.DrivetrainCommands.creep2;
import frc.robot.commands.DrivetrainCommands.stopMotors;
import frc.robot.commands.IndexerCommands.*;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.DPadButton;
import frc.robot.controller.MultiButton;

public class OI {
    public static Joystick driverStick;
    public static Joystick operatorStick;
    public static Joystick pitStick; 
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

    public OI(){
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        pitStick = new Joystick(2);

        initButtons();
        
        
        //initUsed();

        //MAP JOYSTICK CONTROLS HERE:

        //Intake commands 
        driverA.whenHeld(new ExtendRunIntake());

        //Drive Commmands
        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        driveButton.whenReleased(new stopMotors());
       
        //Intake up and down 
        driverY.whenHeld(new ReverseIntake());

        // driverB.whenHeld(new TurnCounterClockwisePID(-22.34));
        
        operatorDPadDown.whileHeld(new ReverseIndexer());
        operatorDPadDown.whenReleased(new StopIndexer());

        operatorDPadUp.whileHeld(new RunIndexerBelts());
        operatorDPadUp.whenReleased(new StopIndexer());

        operatorRB.whenHeld(new RunIndexer());

        operatorRT.whenHeld(new TwoBallManuel());
        operatorLT.whenPressed(new PidTesting(-20.34));

        //Shooter
        // operatorA.whenHeld(new UpperHubClose());
        operatorB.whenHeld(new LowerHubClose());
        // operatorY.whenHeld(new longShotOuterTarmac());
        // operatorX.whenHeld(new GachLongShot());
        //operatorLT.whenPressed(new StopFlywheel());
        

        //Climb commands
        operatorDPadRight.whenHeld(new RunClimbUpWithTimer());
        operatorDPadLeft.whenHeld(new RunClimbDownWithTimer());
        
        //stop climb 
        operatorDPadRight.whenReleased(new StopClimb());
        operatorDPadLeft.whenReleased(new StopClimb());   

        driverDPadDown.whenHeld(new LeftClimbDown());
        driverDPadUp.whenHeld(new LeftClimbUp());

        driverDPadLeft.whenHeld(new RightClimbDown());
        driverDPadRight.whenHeld(new RightClimbUp());

        //Pit controls 
        pitDPadUp.whenHeld(new PitMoveClimb());
        pitDPadDown.whenHeld(new PitMoveClimbDown());
        operatorLB.whenHeld(new SetBrakeMode());
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
            // driverRS = new JoystickButton(driverStick,10);
            driverRX = new AnalogButton(driverStick, 4);
            driverDPadDown = new DPadButton(driverStick, DPadButton.kDPadDown);
            driverDPadRight = new DPadButton(driverStick, DPadButton.kDPadRight);
            driverDPadUp = new DPadButton(driverStick, DPadButton.kDPadUp);
            driverDPadLeft = new DPadButton(driverStick, DPadButton.kDPadLeft);
            driveButton = new MultiButton(new Button[] {
                new AnalogButton(driverStick, 3,2,0,0.2),
                driverRB, driverLB
            });

            // driveButton = new MultiButton(new Button[] {
            //     new AnalogButton(driverStick, 0),
            //     driverLB,
            //     driverRB
            // });
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

            pitA = new JoystickButton(pitStick, 1);
            pitB = new JoystickButton(pitStick, 2);
            pitX = new JoystickButton(pitStick, 3);
            pitY = new JoystickButton(pitStick, 4);
            //  pitBack = new JoystickButton(pitStick,7);
            //  pitStart = new JoystickButton(pitStick, 8);
             pitRB = new JoystickButton(pitStick, 6);
             pitLB = new JoystickButton(pitStick, 5);
             pitLT = new AnalogButton(pitStick, 2);
             pitRT = new AnalogButton(pitStick, 3);
             pitLS = new AnalogButton(pitStick, 1);
             pitDPadDown = new DPadButton(pitStick, DPadButton.kDPadDown);
             pitDPadUp = new DPadButton(pitStick, DPadButton.kDPadUp);
        }

        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
//TEMPORARY PIT CONTROLS//
            // pitA = new JoystickButton(pitStick, 1);
            // pitB = new JoystickButton(pitStick, 2); 
            // pitX = new JoystickButton(pitStick, 3); 
            // pitY = new JoystickButton(pitStick, 4); 
    }
    
    // private void initUsed(){
    //     operatorLeftJoystickUsed = new Button() {
	// 		@Override
	// 		public boolean get() {
	// 			return Math.abs(Util.deadzone(Constants.DEADZONE, operatorStick.getRawAxis(1), 1.0)) > 0.08;
	// 		}
	// 	};
    // }
}