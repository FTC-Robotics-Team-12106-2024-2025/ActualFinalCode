package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="Auton For Right Field", group="Robot")
public class AutonForRightField extends LinearOpMode {
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;
    public DcMotor leftSlide = null;
    public DcMotor rightSlide = null;
    public DcMotor Arm = null;
    @Override
    public void runOpMode() {
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft"); 
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor leftSlide = hardwareMap.get(DcMotor.class,"leftSlide");
        DcMotor rightSlide = hardwareMap.get(DcMotor.class,"rightSlide");
        DcMotor Arm = hardwareMap.get(DcMotor.class,"Arm");
    }
    public void  moveWheel(float x,float y) {
         float fl = (y+x);
         float fr = (x-y);
         float bl = (y-x);
         float br = (-y-x);

        //stops it from going greater than 1/-1
         float maxNumber = Math.max(Math.abs(x)+Math.abs(y),1);
         //powers the motor for wheels
        frontLeft.setPower(fl/maxNumber);
        frontRight.setPower(fr/maxNumber);
        backLeft.setPower(bl/maxNumber);
        backRight.setPower(br/maxNumber);
    }
   public void turn(boolean rotateLeft, boolean rotateRight) {
        double fl, fr, bl, br;
        fl = fr = bl = br = 0;
        if (rotateRight) {
            fl = 0.5;
            fr = 0.5;
            bl = 0.5;
            br = 0.5;
         }
         if (rotateLeft) {
             fl = -0.5;
             fr = -0.5;
             bl = -0.5;
             br = -0.5;
         }
    } 
    public void linearVertical(float verticalPower) {
        if (verticalPower>0) {
            leftSlide.setPower(verticalPower);
            rightSlide.setPower(verticalPower);
        }                       //Assumes triggers to be positive values
        else if (verticalPower<0) {
            leftSlide.setPower(verticalPower);
            rightSlide.setPower(verticalPower);
        }
    }
    public void armPleaseWork(float armVertical) {
       if (armVertical > 0) {
           Arm.setPower(armVertical);
       } else if (armVertical < 0) {
           Arm.setPower(armVertical);
       }
    }

    }

