package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystems.hwMap;

//@Disabled
@TeleOp
public class digitalDrive extends OpMode {

    public digitalDrive() {
    }
    boolean driveSpeedToggle = false;
    int delay = 30;
    double turnSpeed, regularSpeed;

    private hwMap robot = new hwMap();

    public void init() {
        //Initiates variables if not put in hardware map
        robot.init(hardwareMap);
    }

    public void loop() {
        //strafe
        boolean dpadDown, dpadUp, dpadRight, dpadLeft, lBumper, rBumper;


        delay += 1;

        if (gamepad1.a && delay > 30) {
            driveSpeedToggle =! driveSpeedToggle;
        }
        if(driveSpeedToggle){
            turnSpeed = 1.0;
            regularSpeed = 1.0;
        } else if(!driveSpeedToggle){
            turnSpeed = 0.3;
            regularSpeed = 0.6;
        }


        dpadDown = gamepad1.dpad_down;
        dpadUp = gamepad1.dpad_up;
        dpadLeft = gamepad1.dpad_left;
        dpadRight = gamepad1.dpad_right;
        lBumper = gamepad1.left_bumper;
        rBumper = gamepad1.right_bumper;

        //float yStick = Range.clip(gamepad1.left_stick_y, -1 , 1);
        //float xStick = Range.clip(gamepad1.left_stick_x, -1, 1);

        //Forward
        if (dpadUp) {
            robot.FLwheel.setPower(-regularSpeed);
            robot.FRwheel.setPower(regularSpeed);
            robot.BLwheel.setPower(-regularSpeed);
            robot.BRwheel.setPower(regularSpeed);
        }

        //Backward
        if (dpadDown) {
            robot.FLwheel.setPower(regularSpeed);
            robot.FRwheel.setPower(-regularSpeed);
            robot.BLwheel.setPower(regularSpeed);
            robot.BRwheel.setPower(-regularSpeed);
        }

        //Left
        if (dpadLeft) {
            robot.FLwheel.setPower(regularSpeed);
            robot.FRwheel.setPower(regularSpeed);
            robot.BLwheel.setPower(-regularSpeed);
            robot.BRwheel.setPower(-regularSpeed);
        }

        //Right
        if (dpadRight) {
            robot.FLwheel.setPower(-regularSpeed);
            robot.FRwheel.setPower(-regularSpeed);
            robot.BLwheel.setPower(regularSpeed);
            robot.BRwheel.setPower(regularSpeed);
        }

        //Turn Left
        if (lBumper) {
            robot.FLwheel.setPower(-turnSpeed);
            robot.FRwheel.setPower(-turnSpeed);
            robot.BLwheel.setPower(-turnSpeed);
            robot.BRwheel.setPower(-turnSpeed);
        }

        //Turn Right
        if (rBumper) {
            robot.FLwheel.setPower(turnSpeed);
            robot.FRwheel.setPower(turnSpeed);
            robot.BLwheel.setPower(turnSpeed);
            robot.BRwheel.setPower(turnSpeed);
        }

        //

        //Stop robot
        if (!dpadDown && !dpadUp && !dpadLeft && !dpadRight && !rBumper && !lBumper) {
            robot.FLwheel.setPower(0);
            robot.FRwheel.setPower(0);
            robot.BLwheel.setPower(0);
            robot.BRwheel.setPower(0);
        }

        //STOP MOTORS PERMANENTLY AKA EMERGENCY STOP
       /* if (xButton) {
            robot.FLwheel.setPower(0);
            robot.FLwheel.setPower(0);
            robot.FLwheel.setPower(0);
            robot.FLwheel.setPower(0);
            }


        robot.FLwheel.setPower(yStick + xStick);
        robot.FRwheel.setPower(yStick - xStick);
        robot.BLwheel.setPower(yStick + xStick);
        robot.BRwheel.setPower(yStick - xStick);
        */
    }

    public void stop(){}
}