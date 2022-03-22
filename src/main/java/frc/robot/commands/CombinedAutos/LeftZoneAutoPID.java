package frc.robot.commands.CombinedAutos;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.*;
import frc.robot.commands.IndexerCommands.RunIndexerBelts;
import frc.robot.commands.ShooterCommands.StopFlywheel;

public class LeftZoneAutoPID extends SequentialCommandGroup {
  /** Creates a new LeftZoneAuto. */
  public LeftZoneAutoPID() {

    // addCommands(
    // new ExtendRunIntakeAuto(),
    // new wait(0.7),
    // new DriveDistance(0.9,0.9, Constants.autoDriveSpeed), //.86, .3
    // new wait(0.6), 
    // new RetractStopIntake(),

    // new AutoRunIndexer(), 
    // new DriveDistanceBack(1.59,1.59,Constants.autoDriveSpeed), //1.55, .3
    // new TurnCounterClock(18),
    // new DriveDistanceBack(1,1, Constants.autoDriveSpeed), //.3
    // new AutoHighShot(), 
    // new wait(4), 
    // new AutoRunIndexerBelts(), 
    // new wait(1.3),
    // new stopIndexerAuto(), 
    // new StopFlywheel(), 
    // //new stopIndexerAuto(),

    // new ExtendRunIntakeAuto(),
    // new DriveDistance(0.13,0.13, 0.1),
    // new Turn(15), 
    // new DriveDistance(1.8,1.8,0.3),
    // new wait(.5),
    // new RetractStopIntake(),
    // new AutoRunIndexerSingleBall(),
    // new TurnCounterClock(18),
    // new ExtendRunIntakeReverse(),
    // new ReverseIndexerAuto());

    addCommands(
    new ExtendRunIntakeAuto(),
    new wait(0.7),
    new DriveDistance(0.9,0.9, Constants.autoDriveSpeed), //.86, .3
    new wait(0.6), 
    new RetractStopIntake(),

    new AutoRunIndexer(), 
    new DriveDistanceBack(0.5,0.5,Constants.autoDriveSpeed), //1.55, .3
    new TurnCounterClockwisePID(8), //TurnCounterClock(8)
    new TurnCounterClockwisePID(8),
    new TurnCounterClockwisePID(8),
    new AutoLongShot(), 
    new wait(2), 
    new AutoRunIndexerBelts(), 
    new wait(1.3),
    new stopIndexerAuto(), 
    new StopFlywheel(), 

    new ExtendRunIntakeAuto(),
    new DriveDistance(0.13,0.13, 0.1),
    new TurnPID(50), //Turn(50)
    new DriveDistance(1.8,1.8,0.3),
    new wait(.5),
    new RetractStopIntake(),
    new AutoRunIndexerSingleBall(),
    new TurnPID(90), //Turn(90)
    // new Turn(90),
    // new Turn(90),
    new AutoGachShot(),
    new wait(1),
    new RunIndexerBelts()
    );
    //new ExtendRunIntakeReverse(),
    //new ReverseIndexerAuto());
  }
}
    //ball spit (Eli's Version)
    // new DriveDistance(3.05, 3.05, Constants.autoDriveSpeed);
    // new Turn(35);
    // new ExtendRunIntakeAuto();
    // new wait(1.5);
    // new Turn(-10);
    // new AutoReverseIntakeAndIndex();
    // new RetractIntake();   