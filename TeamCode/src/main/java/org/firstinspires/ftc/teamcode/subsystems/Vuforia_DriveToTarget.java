package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.Range;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Rotation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Pirates_Of_The_Grind_Island_10841_team_code.Team_10841_Robot;
import org.firstinspires.ftc.teamcode.subsystems.hwMap;

/**
 * Created by Luke on 8/7/2017.
 * <p>
 * Configuration file: "10841 hardware"
 */

@Autonomous(name = "Vuforia drive to target")
@Disabled
public class Vuforia_DriveToTarget extends LinearOpMode {
    private static final double DISTANCE_ERROR = 1.5;
    private static final double ANGLE_ERROR = 20;
    /**
     * Declare the hardware map for the 10841 robot
     */
    hwMap robot = new hwMap();
    double distance;
    double angle;
    boolean stalled = false;
    boolean done = false, haveMoved = false;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Initialising..."); // visually show on the driver station that we are initialising
        telemetry.update();

        /**
         * Initialise hardware
         */
        robot.init(hardwareMap);

        /**
         * Initialise Vuforia
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId); // create the VuforiaLocaliser parameters field and show the camera view
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT; // use the front camera for image tracking
        parameters.useExtendedTracking = true; // continue to track even if the target is out of view
        /** Vuforia license found at {@see https://developer.vuforia.com/targetmanager/licenseManager/licenseListingDetails}*/
        parameters.vuforiaLicenseKey = "ASIz7sT/////AAAAGfb3lVDO4Evbge6HWYydU2BT6A/r9GYtmWexQy+4JDwMW9t8z27eyX13pypPMbQTCl9YpqDukouTV+wJeZhMiAY6Sp+91uqspGGCQafg0Yf0IL40A9E8Py3QVDaY82M2El+PpiaakH8PxIMOrRD3BZwz71SiMNoToXX1ycXqE9bER5TcarUhu3m+ddfQgxR+VHkck/u1N70DK4i9at5bED1fvZ4HfaJegLQpxYFybrDgnM+odzO1mbANVERFklrS/Q1zamsqqjRoB24DuIxMXJR069n+AUN+/sU2IvcmGbkcUmkNkb22QuScaQZMeRXXlIO8TyLFqa0rPf3l2vZ81ejF+EbzhxdEMqA1+vf7Iuww";

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters); // create Vuforia
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 2); // track up to two targets simultaneously

        VuforiaTrackables targets = vuforia.loadTrackablesFromAsset("StonesAndChips"); // use the StonesAndChips target file

        targets.get(0).setName("stones"); // set the index of the Stones target to "0"
        targets.get(1).setName("chips"); // set the index of the Chips target to "1"

        telemetry.clearAll(); // clear the telemetry view
        telemetry.addLine("Ready to start"); // visually show on the driver station that we are ready to start
        telemetry.update(); // update the telemetry

        waitForStart(); // wait for the start button is pressed
        targets.activate(); // activate the targets
        boolean ok = false;

//        while (opModeIsActive()) { // loop until the stop button is pressed
        while (!ok) { // loop until the target has been reached
            for (VuforiaTrackable target : targets) { // loop for each image target
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) target.getListener()).getPose(); // read the position of the target

                if (pose != null) {
                    VectorF translation = pose.getTranslation(); // translate pose

                    distance = translation.magnitude() / 25.4 - DISTANCE_ERROR; // store the distance from the target in a double variable, convert to inches and subtract the distance error
                    angle = Math.toDegrees(Math.atan2(translation.get(0), translation.get(2)) + 10); // store the angle from the target in a double variable.
                    // camera is set back in the robot, so add that distance to the value
                    ok = true; // the target location is know

                    telemetry.log().add(target.getName() + ": "/*add space*/ + "distance from target" + distance); // display the distance from the target
                    telemetry.log().add(target.getName() + ": "/*add space*/ + "angle in degrees " + angle); // display the angle from the target
                    telemetry.update(); // update the telemetry
                }
            }
        }
        /**
         * drive to the target :)
         */
        sleep(5000); // wait 5 seconds to see the tatget on the display (not required)
        driveForDistanceInches(distance, 0.5); // drive to the target at 50% power
//            if (done && haveMoved) break; // if we are done driving break out of the loop
//        }
    }

    void driveForDistanceInches(double driveToDistance, double setSpeed) throws InterruptedException {
        int startPosition;
        if (driveToDistance < 0)
            setSpeed = -Math.abs(setSpeed);
        else
            setSpeed = Math.abs(setSpeed);
        robot.ticksToMove = -robot.setTargetInches(driveToDistance);
        robot.rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int Target_Right = robot.rightDriveMotor.getCurrentPosition() - robot.ticksToMove;
        int Target_Left = robot.leftDriveMotor.getCurrentPosition() - robot.ticksToMove;
        startPosition = robot.leftDriveMotor.getCurrentPosition();

//        robot.leftDriveMotor.setTargetPosition(robot.leftDriveMotor.getCurrentPosition() - robot.ticksToMove);
//        robot.rightDriveMotor.setTargetPosition(robot.rightDriveMotor.getCurrentPosition() - robot.ticksToMove);

//        runtime.reset();
//        stalled = false;
//        secs =  runtime.seconds();
//        int stalledTestRight = robot.rightDriveMotor.getCurrentPosition();
//        int stalledTestLeft = robot.leftDriveMotor.getCurrentPosition();

        if (driveToDistance < 0) {
            while (opModeIsActive() && ((robot.leftDriveMotor.getCurrentPosition() > Target_Left) || (robot.rightDriveMotor.getCurrentPosition() > Target_Right) && !stalled)) {
                robot.drivePower = -setDrivePower(startPosition, robot.leftDriveMotor.getCurrentPosition(),
                        Target_Left, robot.slowZone, setSpeed);
                robot.leftDriveMotor.setPower(Range.clip(robot.drivePower, -1.0, 1.0));
                robot.rightDriveMotor.setPower(Range.clip(robot.drivePower, -1.0, 1.0));

                telemetry.addData("< setspeed", setSpeed);
                telemetry.addData("left pos", robot.leftDriveMotor.getCurrentPosition());
                telemetry.addData("target ", Target_Left);
                telemetry.update();
                haveMoved = true;

            }
        } else {
            while (opModeIsActive() && ((robot.leftDriveMotor.getCurrentPosition() < Target_Left) || (robot.rightDriveMotor.getCurrentPosition() < Target_Right) && !stalled)) {
                robot.drivePower = setDrivePower(startPosition, robot.leftDriveMotor.getCurrentPosition(),
                        Target_Left, robot.slowZone, setSpeed);


                robot.leftDriveMotor.setPower(Range.clip(robot.drivePower, -1.0, 1.0));
                robot.rightDriveMotor.setPower(Range.clip(robot.drivePower, -1.0, 1.0));
                telemetry.addData("> setspeed", setSpeed);
                telemetry.addData("left pos", robot.leftDriveMotor.getCurrentPosition());
                telemetry.addData("target ", Target_Left);
                telemetry.update();

            }
        }

        robot.rightDriveMotor.setPower(0);
        robot.leftDriveMotor.setPower(0);
        sleep(100);
        robot.rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (robot.leftDriveMotor.isBusy() && robot.rightDriveMotor.isBusy()) done = false;
        else done = true;
    }

    private double setDrivePower(int startPos, int currentPos, int targetPos, int slowDistance, double targetMaxPower) {
        /*
        We want to ramp up and down the power at the beginning and end of the run.
         */
        double minSpeed = 0.1;

        double drivePower;  // this value will be returned
        int totalTravelDistance = (Math.abs(targetPos - startPos));

        // Decrease the max speed if the ramp up and ramp down distances are shorter then
        // the total travel distance. Keep it on the same vector.
        double maxSpeed;// = robot.calcMaxSpeed(slowDistance, targetPos - startPos, minSpeed, targetMaxPower);
        maxSpeed = targetMaxPower; // for now ignore the value we just calculated.  delete this later

        boolean neg = false;
        if (targetMaxPower < 0) {
            neg = true;
            maxSpeed = -targetMaxPower;
        }

        int DistanceRemaining = Math.abs(targetPos - currentPos);
        int DistanceTraveled = Math.abs(currentPos - startPos);
        double rUp = robot.RampUp(DistanceTraveled, slowDistance / 2, minSpeed * 2, maxSpeed);
        double rDn = robot.RampDown(DistanceRemaining, slowDistance * 3, minSpeed, maxSpeed);

        /*
        if the drive power is neg we need to set it to the max instead of min.
         */
        if (rUp < 0)
            drivePower = Math.max(rUp, rDn);
        else
            drivePower = Math.min(rUp, rDn);

        if (targetPos < currentPos)
            drivePower = -drivePower;

        if (neg)
            drivePower = -drivePower;

        return drivePower;
    }

    /*
     * Use encoder ticks to cause the robot to pivot at the center of the robot. It over shoots the
     * target angle since the robot coasts after the motors are turned off.
     */
    void PointTurnUsingEncoders(int TurnAngle, double setSpeed) throws InterruptedException {
        int TicksToTurn = (int) robot.wheel_angleToTicks(TurnAngle);

        int leftM_target = robot.leftDriveMotor.getCurrentPosition() + TicksToTurn;
        int rightM_target = robot.rightDriveMotor.getCurrentPosition() - TicksToTurn;
        int startPosition = robot.leftDriveMotor.getCurrentPosition();
        robot.leftDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.leftDriveMotor.setTargetPosition(leftM_target);
        robot.rightDriveMotor.setTargetPosition(rightM_target);

        boolean done = false;
        while (opModeIsActive() && robot.rightDriveMotor.isBusy() && robot.leftDriveMotor.isBusy()) {
            robot.drivePower = setDrivePower(startPosition, robot.leftDriveMotor.getCurrentPosition(),
                    robot.leftDriveMotor.getTargetPosition(), robot.setTargetInches(3), setSpeed);
            robot.leftDriveMotor.setPower(Range.clip(robot.drivePower, -1.0, 1.0));
            robot.rightDriveMotor.setPower(-Range.clip(robot.drivePower, -1.0, 1.0));
        }
        //sleep(100);
        robot.leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}