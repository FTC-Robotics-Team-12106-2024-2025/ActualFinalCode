//For testing purposes only. NOT FOR ACTUAL USE
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "AutonomousCenterBlue")
public class AutonomousCenterBlue extends LinearOpMode {
 
 //Define motors and servos
 private DcMotor rightMotor;
 private DcMotor leftMotor; 
 private Servo intakeLeft;
 private Servo intakeRight;
 private DcMotor climbRight;
 private DcMotor climbLeft;
 //Put functions here
 /*public void goToPosVert(int pos) {
  armVertical.setTargetPosition(pos);
  armVertical.setPower(1);
  armVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
 }*/
 public void goForward(int mm) {
int ticks = (int)(mm*17.8253);
  leftMotor.setTargetPosition(leftMotor.getCurrentPosition()+ticks);
  rightMotor.setTargetPosition(rightMotor.getCurrentPosition()+ticks);
 }
 public void goBackward(int mm) {
int ticks = (int)(mm*17.8253);
  leftMotor.setTargetPosition(leftMotor.getCurrentPosition()-ticks);
  rightMotor.setTargetPosition(rightMotor.getCurrentPosition()-ticks);
 }
  public void turnRight(int mm) {
int ticks = (int)(mm*17.8253);
  leftMotor.setTargetPosition(leftMotor.getCurrentPosition()-ticks);
  rightMotor.setTargetPosition(rightMotor.getCurrentPosition()+ticks);
 }
 public void turnLeft(int mm) {
int ticks = (int)(mm*17.8253);
  leftMotor.setTargetPosition(leftMotor.getCurrentPosition()+ticks);
  rightMotor.setTargetPosition(rightMotor.getCurrentPosition()-ticks);
 }

public void openCollector() {
 intakeRight.setPosition(0.75);
 intakeLeft.setPosition(0.75);
 }
 public void closeCollector() {
 intakeRight.setPosition(-0.75);
 intakeLeft.setPosition(-0.75);
 }
 @Override
 public void runOpMode() {
  //Define variables here
// int vertPos;


  //Make hardware maps
  leftMotor = hardwareMap.get(DcMotor.class, "leftDrive") ;
  leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  rightMotor = hardwareMap.get(DcMotor.class, "rightDrive");
  rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  intakeLeft = hardwareMap.get(Servo.class, "intakeLeft");
  intakeRight = hardwareMap.get(Servo.class, "intakeRight");
  rightMotor.setDirection(DcMotor.Direction.REVERSE);
   intakeRight.setDirection(Servo.Direction.REVERSE);
  waitForStart();
  //Main loop
  if (opModeIsActive()) { 
 goForward (130);
 sleep(2000);
 goBackward (100);
sleep(2000);
 turnLeft (54);
 sleep(2000);
 goForward (222);
 sleep(2000);
 
  }
 }
}
