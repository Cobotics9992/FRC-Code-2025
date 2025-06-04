// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software released under the WPILib BSD licence.


package frc.robot;


import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Robot extends TimedRobot {


 // CAN IDs
 private final WPI_VictorSPX leftMaster   = new WPI_VictorSPX(11);
 private final WPI_VictorSPX rightMaster  = new WPI_VictorSPX(12);
 private final WPI_VictorSPX leftFollower = new WPI_VictorSPX(13);
 private final WPI_VictorSPX rightFollower= new WPI_VictorSPX(14);
 private final WPI_TalonSRX rightAux     = new WPI_TalonSRX(22);
 private final WPI_TalonSRX leftAux     = new WPI_TalonSRX(21);

 private DifferentialDrive drive;
 private final XboxController driver = new XboxController(0);


 // ---------------------------------------------------------------------------
 // One-time setup
 // ---------------------------------------------------------------------------
 @Override
 public void robotInit() {


   // Reset all SPX settings
   leftMaster.configFactoryDefault();
   rightMaster.configFactoryDefault();
   leftFollower.configFactoryDefault();
   rightFollower.configFactoryDefault();
   rightAux.configFactoryDefault();
   leftAux.configFactoryDefault();


   // Followers
   leftFollower.follow(leftMaster);
   rightFollower.follow(rightMaster);
   leftFollower.setInverted(InvertType.FollowMaster);
   rightFollower.setInverted(InvertType.FollowMaster);


   // Drive-side inversion (adjust if your gearbox is mirrored)
   leftMaster.setInverted(false);
   rightMaster.setInverted(true);


   // DifferentialDrive wrapper
   drive = new DifferentialDrive(leftMaster, rightMaster);
   SendableRegistry.addChild(drive, leftMaster);
   SendableRegistry.addChild(drive, rightMaster);
 }


       




 // ---------------------------------------------------------------------------
 // Tele-op loop (called every 20 ms by default)
 // ---------------------------------------------------------------------------


  // ---------------------------------------------------------------------------
 // Current system
 // ---------------------------------------------------------------------------

 //vars
 static double mainspeed = 0.75;
 static double LAuxSpeed;
 static double RAuxSpeed;
  @Override
  public void teleopPeriodic() {
   
    if (driver.getLeftBumperButton()){
      //LB();
      mainspeed = 0.5;
    }
    
    if (driver.getRightBumperButton()) {
      //RB();
      mainspeed = 0.75;

    }
    
    // Split-arcade driving: left-stick Y for throttle, right-stick X for turn
    double LeftYJoy = -driver.getLeftY();
    double RightYJoy = -driver.getLeftX();
    
    double turnSpeed = RightYJoy * mainspeed;
    double driveSpeed = LeftYJoy * mainspeed;
    drive.arcadeDrive(
        (driveSpeed),   // invert so forward is positive
        (turnSpeed)); // invert so right is positive
        
    //runAux();

 
 
    if (driver.getYButton()) {
     //Y();
     System.out.println("Y pressed");
    }
    
    if (driver.getAButton()) {
      //A();
      System.out.println("A pressed");         
    }
 
 
    // Run console log of button pressed while x is held
    if (driver.getXButton()) {
      //X();
      System.out.println("X pressed");
      }
    
     if (driver.getBButton()) {
       //B();
       System.out.println("B pressed");
     }
 
      
    if (driver.getPOV() == -1){
      RAuxSpeed = 0;
    } 
    else{
      if (driver.getPOV() == 0){
        RAuxSpeed = 0.2;
      }
      if (driver.getPOV() == 180){
        RAuxSpeed = -0.2;
      }

      System.out.println(RAuxSpeed);
    }
    leftAux.set(RAuxSpeed);
    System.out.println(driver.getPOV());
    System.out.println("POV: " + driver.getPOV() + " | RAuxS: " + RAuxSpeed + " | LJoy: " + LeftYJoy + " | RJoy: " + RightYJoy + " | Mainspeed: " + mainspeed + " | Turn Rat: " + turnSpeed + " | Drive Powah: " + driveSpeed);


     
     
    if (driver.getRightTriggerAxis() > 0.1 || driver.getLeftTriggerAxis() > 0.1) {
      //RT();
     if (driver.getRightTriggerAxis() > 0.3) {
      LAuxSpeed = driver.getRightTriggerAxis();
      System.out.println("Aux speed: " + LAuxSpeed);
     }else if (driver.getLeftTriggerAxis() > 0.3){
      LAuxSpeed = driver.getLeftTriggerAxis() * -1;
      System.out.println("Aux speed: " + LAuxSpeed);
     }else{
      LAuxSpeed = 0;
      System.out.println("Aux speed: " + LAuxSpeed);
     }
      
     rightAux.set(LAuxSpeed);
    } 
    /* 
    if (driver.getLeftTriggerAxis() > 0.1) {
      rightAux.set(driver.getRightTriggerAxis() * -1);
      //LT();
    }
    else{
      rightAux.stopMotor();       // safer helper (sets output to 0)
    }
    */
 
    //new stuff below
    
 }
    
 
 
   
    //dpad here
    //if (driver.getdpad)
   
 
 
    /*
    dpad here
    if (driver.getdpad)
    */
  
  //-----------------------------------------------------------------------------------------------------------------
  // Potential new system of calling functions below.
  // Put inside @overide below driving systems and replace other if statements with the ones below,
  // make sure static void statements are outside of the @override statement.
  //-----------------------------------------------------------------------------------------------------------------
  // Run the aux motor while RB is held
 /*
    runAux()
 
 
    if (driver.getYButton()) {
     
      Y();
    }
    else if (driver.getAButton()) {
      A();         
    }
 
 
    // Run console log of button pressed while x is held
    if (driver.getXButton()) {
      X();
      }
      else if (driver.getBButton()) {B();}
 
 
 
 
    // Run console log of button pressed while x is held
    if (driver.getRightTriggerAxis() > 0.1 || driver.getLeftTriggerAxis() > 0.1) {
      RT();
    } else {
      rightAux.stopMotor();       // safer helper (sets output to 0)
    }
 
 
    //new stuff below
    if (driver.getleftbumper()){
      LB();
    }
    else if (driver.getrightbumper()){
      RB();
    }*/
 
 
   
    //dpad here
    //if (driver.getdpad)
   /* 
   static void runAux(){
   int steps = 10;
   float speed = (float) driver.getRightTriggerAxis() - driver.getLeftTriggerAxis();
   speed = (float) Math.floor(speed * steps) / steps;
   if(speed != 0){
    rightAux.set(speed);
    System.err.println("Aux speed" + speed);
   }
   else{
    rightAux.stopMotor();
    }
   }
   */
 /* 
  static void X (){
    System.out.println("X Pressed");
  }
 
 
  static void B (){
    System.out.println("B Pressed");
  }
 
 
  static void Y (){
    System.out.println("Y Pressed");
    }
 
 
  static void A (){
    System.out.println("A Pressed");
    }
 
 
  
  static void RT (){
   
    System.out.print("R trigger ");
    System.out.println(driver.getRightTriggerAxis());
    System.out.println("R aux fwd");
    rightAux.set(driver.getRightTriggerAxis);
  }
  static void LT (){
    System.out.print("L trigger ");
    System.out.println(driver.getLefttTriggerAxis());
    System.out.println("L aux back");
    rightAux.set(driver.getLeftTriggerAxis * -1); //makes negative
  }
  
   static void LB (){
    System.out.println("Left Bumper Pressed");
    mainspeed = 1 / 3;
 }


 static void RB (){
   System.out.println("Right Bumper Pressed");
  */
 }


//}