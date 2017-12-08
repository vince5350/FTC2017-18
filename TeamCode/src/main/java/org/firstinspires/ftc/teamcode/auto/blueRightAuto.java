package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.hwMap;

//Encoder counts per revolution is 537.6
//Counts per inch has to be divided by 2 to compensate for wheel angle

@Autonomous (name = "Right Blue")
public class blueRightAuto extends LinearOpMode {

    private hwMap robot = new hwMap();
    private ElapsedTime runtime = new ElapsedTime();

    static private final double COUNTS_PER_MOTOR_REV = 537.6;
    static private final double WHEEL_DIAMETER       = 4.0;
    static final double COUNTS_PER_INCH              = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER * 3.1415);


    static final double turn                 = 0.5;
    static final double forward              = 0.3;


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
    }
}
