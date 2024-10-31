package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.robotcore.hardware.SoundPlayer;

@TeleOp

public class MotorControlsForTeleOp extends LinearOpMode {
     Gamepad stupidGamepad = new Gamepad();
     Gamepad RsFault = new Gamepad();
    @Override
    
    //Defines the motor
    public void runOpMode() {
        //Motors for Wheels
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft"); //Port 0
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight"); //Port 1
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft"); 
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        //Motors for Linear Slide
        DcMotor leftSlide = hardwareMap.get(DcMotor.class, "leftSlide"); //Slot 0
        DcMotor rightSlide = hardwareMap.get(DcMotor.class, "rightSlide"); //Slot 1
        
        DcMotor Arm = hardwareMap.get(DcMotor.class,"Arm");
        Servo Claw = hardwareMap.get(Servo.class,"Claw");
        //resets motor
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //turns motor back on
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        
        /*
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters(/*
            new RevHUBOrientationOnRobot (
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
                )
            );
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);*/
        waitForStart();
        
        if (isStopRequested()) return;
        
        while (opModeIsActive()) {
         stupidGamepad.copy(gamepad1);
         RsFault.copy(gamepad2);
        //Makes the robot go move I hope. Prob not going to work
         float y = -stupidGamepad.left_stick_y;
         float x = stupidGamepad.left_stick_x;
        
         boolean rotateRight = stupidGamepad.right_bumper;
         boolean rotateLeft = stupidGamepad.left_bumper;
         boolean honk = stupidGamepad.left_stick_button;
         
         //For up-movement of linear slide
         boolean verticalUp = RsFault.dpad_up;
         //For down-movement of linear slide
         boolean verticalDown = RsFault.dpad_down;
         
         double armUp = RsFault.left_trigger;
         double armDown = RsFault.right_trigger;
         double clawPosX = RsFault.left_stick_x;
         double clawPosY = -RsFault.left_stick_y;
         
         
         double wheelCPR = 423.2116; //Counts per revolution
         double linearCPR = 72.1; 
         
         int Flposition = frontLeft.getCurrentPosition();
         int Frposition = frontRight.getCurrentPosition();
         int Blposition = backLeft.getCurrentPosition();
         int Brposition = backRight.getCurrentPosition();
         //YAY MORE VARIABLES
         double Flrevolutions = Flposition/wheelCPR;
         double Frrevolutions = Frposition/wheelCPR;
         double Blrevolutions = Blposition/wheelCPR;
         double Brrevolutions = Brposition/wheelCPR;
         //Linear Slides Revolutions
         
        /*int Lsposition = leftSlide.getCurrentPosition();
         int Rsposition = rightSlide.getCurrentPosition();
         double Lsrevolutions = Lsposition/linearCPR;
         double Rsrevolutions = Rsposition/linearCPR;
        double lsModifier = 1;
         double rsModifier = 1;*/
         
         double leadMotor = Math.min(Math.min(Flrevolutions,Frrevolutions),Math.min(Blrevolutions,Brrevolutions));
         //Modifier Variables YAY
        // double linearMotor = Math.min(Lsposition,Rsposition);
         double flModifier = 1;
         double frModifier = 1;
         double blModifier = 1;
         double brModifier = 1;
            if (Flrevolutions > leadMotor) {
                flModifier = leadMotor/Flrevolutions;
            }
            if (Frrevolutions > leadMotor) {
                frModifier = leadMotor/Frrevolutions;
            }
            if (Blrevolutions > leadMotor) {
                blModifier = leadMotor/Blrevolutions;
            }
            if (Brrevolutions > leadMotor) {
                brModifier = leadMotor/Brrevolutions;
            }
        //Linear Slide
        /*
              if (Lsrevolutions > linearMotor) {
                  lsModifier = linearMotor/Lsrevolutions;
                  
              }
              if (Rsrevolutions > linearMotor) {
                  rsModifier = linearMotor/Rsrevolutions;
              }
              */
              
         double fl = (y+x);
         double fr = (x-y);
         double bl = (y-x);
         double br = (-y-x);
           if (rotateRight) {
            fl = fr = bl = br = 1;
         }
         if (rotateLeft) {
             fl = fr = bl = br = -1;
         }
        if (rotateRight && rotateLeft == false) {
            if (x == 0 || y == 0) {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);   
            }
        }
        //stops it from going greater than 1/-1
         double maxNumber = Math.max(Math.abs(x)+Math.abs(y),1);
         //powers the motor for wheels
        frontLeft.setPower(fl/maxNumber*flModifier);
        frontRight.setPower(fr/maxNumber*frModifier);
        backLeft.setPower(bl/maxNumber*blModifier);
        backRight.setPower(br/maxNumber*brModifier); 
        
        if (verticalUp) {
            leftSlide.setPower(0.5);
            rightSlide.setPower(0.5);
        }                       //Prob works
        if (verticalDown) {
            leftSlide.setPower(-0.5);
            rightSlide.setPower(-0.5);
        }
            if (verticalUp && verticalDown == false) {
            leftSlide.setPower(0);
            rightSlide.setPower(0);
        }
        //Arm Code I USED TRIGGERS LETS GOOO
        Arm.setPower(armUp);
        Arm.setPower(-armDown);
        if (clawPosY > 0) {
            Claw.setPosition(1);
        } 
        if (clawPosY < 0) {
            Claw.setPosition(0);
        }
        if (clawPosX < 0) {
            Claw.setPosition(0.5);
        }
        if (clawPosX > 0) {
            Claw.setPosition(1.5);
        }
  
        /*double botHeading = imu.getAngularOrientation().firstAngle;
         
         double triX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
         double triY = x * Math.sin(botHeading) + y * Math.cos(botHeading);
         telemetry.addData("TriX: ",triX);
         telemetry.addData("TriY: ",triY);
         telemetry.addData("botHeading: ",botHeading);*/
         //fl is front left, br is back right, etc.
        
         /*
         double fl = (triY+triX);
         double fr = (triX-triY);
         double bl = (triY-triX);
         double br = (-triY-triX);
         */
         //Rotation Code, adjust it l8r
         if (rotateRight) {
            fl = fr = bl = br = 1;
         }
         if (rotateLeft) {
             fl = fr = bl = br = -1;
         }
        //stops it from going greater than 1/-1
         maxNumber = Math.max(Math.abs(x)+Math.abs(y),1);
         //powers the motor for wheels
        frontLeft.setPower(fl/maxNumber*flModifier);
        frontRight.setPower(fr/maxNumber*frModifier);
        backLeft.setPower(bl/maxNumber*blModifier);
        backRight.setPower(br/maxNumber*brModifier); 
        
    
        
       /*int soundID = hardwareMap.apContext.getResources().getIdentifier("horn", "raw", hardwareMap.appContext.getPackageName());
         if (soundID != 0 && honk == True) {
             SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, soundID);
         }*/

        
        
        
        
        
        
        }
    }
}

