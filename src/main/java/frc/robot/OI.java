package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.AnalogTrigger;
import edu.wpi.first.wpilibj2.command.button.POVButton;
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
    Trigger driveButton;
    private Trigger driverLB, driverRB, pitRB, pitLB;
    private Trigger driverStart, driverBack;
    private Trigger operatorStart;
    private Trigger driverA, driverB, driverY;
    private Trigger driverDPadDown, driverDPadRight, driverDPadLeft, driverDPadUp, pitDPadUp, pitDPadDown;
    public AnalogTrigger operatorRB, operatorLT, operatorLB, operatorRT, pitRT, pitLT;
    public Trigger operatorLS, operatorBack, pitLS;
    private Trigger driverX;
    private Trigger driverRS, driverLS;
    private Trigger driverRX;
    protected Trigger operatorLeftJoystickUsed, operatorRightJoystickUsed, operatorDPadUp, operatorDPadDown, operatorDPadLeft, operatorDPadRight;
    private Trigger operatorA, operatorB, operatorX, operatorY;
    private Trigger pitA, pitB, pitX, pitY; 

    public OI(){
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        pitStick = new Joystick(2);

        initButtons();
        
        
        //initUsed();

        //MAP JOYSTICK CONTROLS HERE:

        //Intake commands 
        driverA.toggleOnTrue(new ExtendRunIntake());

        //Drive Commmands
        driveButton.whileTrue(new DriveWithJoystick(driverStick, 0.1));
        driveButton.onFalse(new stopMotors());
       
        //Intake up and down 
        driverY.toggleOnTrue(new ReverseIntake());

        // driverB.toggleOnTrue(new TurnCounterClockwisePID(-22.34));
        
        operatorDPadDown.whileTrue(new ReverseIndexer());
        operatorDPadDown.onFalse(new StopIndexer());

        operatorDPadUp.whileTrue(new RunIndexerBelts());
        operatorDPadUp.onFalse(new StopIndexer());

        operatorRB.toggleOnTrue(new RunIndexer());

        operatorRT.toggleOnTrue(new TwoBallManuel());
        operatorLT.onTrue(new PidTesting(-20.34));

        //Shooter
        // operatorA.toggleOnTrue(new UpperHubClose());
        operatorB.toggleOnTrue(new LowerHubClose());
        // operatorY.toggleOnTrue(new longShotOuterTarmac());
        // operatorX.toggleOnTrue(new GachLongShot());
        //operatorLT.onTrue(new StopFlywheel());
        

        //Climb commands
        operatorDPadRight.toggleOnTrue(new RunClimbUpWithTimer());
        operatorDPadLeft.toggleOnTrue(new RunClimbDownWithTimer());
        
        //stop climb 
        operatorDPadRight.onFalse(new StopClimb());
        operatorDPadLeft.onFalse(new StopClimb());   

        driverDPadDown.toggleOnTrue(new LeftClimbDown());
        driverDPadUp.toggleOnTrue(new LeftClimbUp());

        driverDPadLeft.toggleOnTrue(new RightClimbDown());
        driverDPadRight.toggleOnTrue(new RightClimbUp());

        //Pit controls 
        pitDPadUp.toggleOnTrue(new PitMoveClimb());
        pitDPadDown.toggleOnTrue(new PitMoveClimbDown());
        operatorLB.toggleOnTrue(new SetBrakeMode());
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
            driverDPadDown = new POVButton(driver, 180);
	    driverDPadUp = new POVButton(driver, 0);
	    driverDPadRight = new POVButton(driver, 90);
	    driverDPadLeft = new POVButton(driver, 270);
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
             pitLT = new AnalogTrigger(pitStick, 2);
             pitRT = new AnalogTrigger(pitStick, 3);
             pitLS = new AnalogTrigger(pitStick, 1);
             // pitDPadDown = new DPadButton(pitStick, DPadButton.kDPadDown);
             // pitDPadUp = new DPadButton(pitStick, DPadButton.kDPadUp);
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
