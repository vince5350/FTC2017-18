package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.hwMap;

//Encoder counts per revolution is 537.6
//Counts per inch has to be divided by 2 to compensate for wheel angle

@Autonomous (name = "Right Red")
public class redRightAuto extends LinearOpMode {

    private hwMap robot = new hwMap();
    private ElapsedTime runtime = new ElapsedTime();

    static private final double COUNTS_PER_MOTOR_REV = 537.6;
    static private final double WHEEL_DIAMETER = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER * 3.1415);


    static final double TURN_SPEED = 0.5;
    static final double DRIVE_SPEED = 0.3;


    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        //Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        robot.BLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.BLwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.BRwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FLwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FRwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Send message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at: %7d :%7d :%7d :%7d",
                robot.FLwheel.getCurrentPosition(),
                robot.FRwheel.getCurrentPosition(),
                robot.BLwheel.getCurrentPosition(),
                robot.BRwheel.getCurrentPosition());
        telemetry.update();


        //Wait for game to start (driver presses PLAY)
        waitForStart();

        //Step through each leg of the path
        //Reverse is obtained by setting negative distance, not speed
        encoderDrive(DRIVE_SPEED, 24, 24, 5.0);
        encoderDrive(DRIVE_SPEED, 12, 8, 3.0);
        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    private void encoderDrive ( double speed,
                                double leftInches,
                                double rightInches,
                                double timeoutS){
        int newFLTarget;
        int newBLTarget;
        int newFRTarget;
        int newBRTarget;

        // Ensure the opmode is still active
        if (opModeIsActive()) {

            // Determind new position and pass to motor controller
            newFLTarget = robot.FLwheel.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newBLTarget = robot.BLwheel.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);

            newFRTarget = robot.FRwheel.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newBRTarget = robot.BRwheel.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            robot.FLwheel.setTargetPosition(newFLTarget);
            robot.BLwheel.setTargetPosition(newBLTarget);

            robot.FRwheel.setTargetPosition(newFRTarget);
            robot.BRwheel.setTargetPosition(newBRTarget);

            // Turn on RUN_TO_POSITION
            robot.FLwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.BLwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.FRwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.BRwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset timeout time and start motion
            runtime.reset();
            robot.FLwheel.setPower(Math.abs(speed));
            robot.BLwheel.setPower(Math.abs(speed));
            robot.FRwheel.setPower(Math.abs(speed));
            robot.BRwheel.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.FLwheel.isBusy() && robot.BLwheel.isBusy() &&
                            robot.FRwheel.isBusy() && robot.BRwheel.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",
                        "Running to %7d :%7d :%7d :%7d",
                        newFLTarget, newBLTarget, newFRTarget, newBRTarget);
                telemetry.addData("Path2",
                        "Running at %7d :%7d :%7d :%7d",
                        robot.FLwheel.getCurrentPosition(),
                        robot.BLwheel.getCurrentPosition(),
                        robot.FRwheel.getCurrentPosition(),
                        robot.BRwheel.getCurrentPosition());
                telemetry.update();
            }

            // Stop motors
            robot.FLwheel.setPower(0);
            robot.BLwheel.setPower(0);
            robot.FRwheel.setPower(0);
            robot.BRwheel.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.FLwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.BLwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.FRwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.BRwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}