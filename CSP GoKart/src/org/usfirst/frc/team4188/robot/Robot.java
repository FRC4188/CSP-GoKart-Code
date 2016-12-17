package org.usfirst.frc.team4188.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * Columbus Space Program GoKart Code using Mecanum drive
 * @author Vineeth Harish
 */
public class Robot extends SampleRobot {
	
    CHSRobotDrive robotDrive;
    CHSJoystick pilotJoystick;
    Joystick driveController;
    Joystick liftStick;
    RobotDrive liftDrive;
    Relay spike;
    

    // Channels for the wheels
    final int frontLeftChannel	= 2;
    final int rearLeftChannel	= 3;
    final int frontRightChannel	= 1;
    final int rearRightChannel	= 0;
  //SUBJECT TO CHANGE  
    final int frontLeftChannel2  = 6;
    final int rearLeftChannel2   = 7;
    final int frontRightChannel2 = 5;
    final int rearRightChannel2  = 4;
    
    final int frontLeftChannel3  = 10;
    final int rearLeftChannel3   = 11;
    final int frontRightChannel3 = 9;
    final int rearRightChannel3  = 8;
    

    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;

    public Robot() {
    	robotDrive.setExpiration(0.1);
        robotDrive = new CHSRobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel,
        							   frontLeftChannel2, rearLeftChannel2, frontRightChannel2, rearRightChannel2,
        							   frontLeftChannel3, rearLeftChannel3, frontRightChannel3, rearRightChannel3);
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	// invert the left side motors
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);		// you may need to change or remove this to match your robot

    	pilotJoystick = new CHSJoystick(0,4,12,-12.0,12.0,1,1.0,-12.0,12.0,1,1.0,-12.0,12.0,1,1.0);
    	driveController = new Joystick(joystickChannel);
    }
        

    /**
     * Runs the motors with Mecanum drive.
     */
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	
        	// Use the pilotJoystick X axis for lateral movement, Y axis for forward movement, and twist axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
        	
           // robotDrive.mecanumDrive_Cartesian(pilotJoystick.getX(), pilotJoystick.getY(), pilotJoystick.getTwist()); //For joystick
           robotDrive.mecanumDrive_Cartesian(-driveController.getRawAxis(1), driveController.getRawAxis(2), driveController.getRawAxis(4)); 
            Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
        }
        /*	
        spike = new Relay(0);
        if(driveController.getRawButton(1)){
        	
        	spike.set(Relay.Value.kOn);
        }
        else{
        	
			spike.set(Relay.Value.kOff);
        }
        */
    
    
    }
    
}
