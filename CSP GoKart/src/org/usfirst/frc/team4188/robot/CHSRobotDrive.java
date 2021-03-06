package org.usfirst.frc.team4188.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @authors Erin King, Victoria Thornton, Vineeth Harish
 */
public class CHSRobotDrive extends RobotDrive implements PIDOutput {
    //static final int kFrontLeft_val = 0;
    //static final int kFrontRight_val = 1;
    //static final int kRearLeft_val = 2;
    //static final int kRearRight_val = 3;
    //static final double minValue = 0.17;

    protected RobotDrive robotDrive2, robotDrive3;

    public CHSRobotDrive(SpeedController leftMotor1, SpeedController rightMotor1,
            SpeedController leftMotor2, SpeedController rightMotor2,
            SpeedController leftMotor3, SpeedController rightMotor3) {

    	super(leftMotor1, rightMotor1);
        robotDrive2 = new RobotDrive(leftMotor2, rightMotor2);

        //this is for the middle motors
        robotDrive3 = new RobotDrive(leftMotor3, rightMotor3);
    }
   
    public CHSRobotDrive(final int frontLeftMotor1, final int rearLeftMotor1, final int frontRightMotor1,
      final int rearRightMotor1, final int frontLeftMotor2, final int rearLeftMotor2, final int frontRightMotor2,
      final int rearRightMotor2, final int frontLeftMotor3, final int rearLeftMotor3, final int frontRightMotor3,
      final int rearRightMotor3){
    	super(frontLeftMotor1, rearLeftMotor1, frontRightMotor1, rearRightMotor1);
    	robotDrive2 = new RobotDrive(frontLeftMotor2, rearLeftMotor2, frontRightMotor2, rearRightMotor2);
    	robotDrive3 = new RobotDrive(frontLeftMotor3, rearLeftMotor3, frontRightMotor3, rearRightMotor3);
    }
     public void setSafetyEnabled(boolean enabled){
    	 super.setSafetyEnabled(enabled);
    	 robotDrive2.setSafetyEnabled(enabled);
    	 robotDrive3.setSafetyEnabled(enabled);
     }


    public void arcadeDrive(double moveValue, double rotateValue) {

    	super.arcadeDrive(moveValue, rotateValue);
        robotDrive2.arcadeDrive(moveValue, rotateValue);
        robotDrive3.arcadeDrive(moveValue, rotateValue);
      }
    public void mecanumDrive_Cartesian(double x, double y, double rotation){
    	
    	super.mecanumDrive_Cartesian(x, y, rotation, 0);
    	robotDrive2.mecanumDrive_Cartesian(x,y,rotation,0);
    	robotDrive3.mecanumDrive_Cartesian(x,y,rotation,0);
    }
  /**
    public void drive(double outputMagnitude, double curve){
    	super.drive(outputMagnitude, curve);
    	robotDrive2.drive(outputMagnitude, curve);
    	robotDrive3.drive(-outputMagnitude, -curve);
    }
    **/
    
    private static final double OUTPUT_MIN = 0.35;
    // at 0.05, even the motors with no gears could barely run.
    // same at 0.1

    public enum PIDType {
    	turnToAngle,
    	driveToDistance
    }
    
    private static PIDType driveType = PIDType.turnToAngle;
    public static void setPIDType(PIDType type) {    	
    	driveType = type;
    	switch (driveType) {
    	case turnToAngle:
        	SmartDashboard.putString("Setting PIDType =", "turnToAngle");
        	break;
    	case driveToDistance:
        	SmartDashboard.putString("Setting PIDType =", "driveToDistance");
        	break;
    	}
    }
    
    public void pidWrite(double output){
    	if (Math.abs(output) < OUTPUT_MIN) {
    		output = OUTPUT_MIN * Math.signum(output);
    	}
    	SmartDashboard.putNumber("PID", output);
    	switch (driveType) {
    	case turnToAngle:
        	super.setLeftRightMotorOutputs(output,output);
        	robotDrive2.setLeftRightMotorOutputs(output,output);
        	robotDrive3.setLeftRightMotorOutputs(output,output);
        	break;
    	case driveToDistance:
        	super.setLeftRightMotorOutputs(output,output);
        	robotDrive2.setLeftRightMotorOutputs(output,output);
        	robotDrive3.setLeftRightMotorOutputs(output,output);
        	break;
    	}
    }

/*  private int getInverted(SpeedController motor){
    	if(motor.getInverted())
    		return -1;
    	else
    		return 1;

    } */
}

