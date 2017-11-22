package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.hwMap;


@Autonomous (name = "Left Red")
public class redLeftAuto extends LinearOpMode {

    private hwMap robot = new hwMap();
    private ElapsedTime runtime = new ElapsedTime();

    double turn     = 0.5;
    double forward  = 0.3;

    public void runOpMode() throws InterruptedException {
        //Init hwMap
        robot.init(hardwareMap);

        telemetry.addData("Status:","Waiting for start command");
        telemetry.update();

        waitForStart();

        //Step 1
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < 5)) {
        }

        //Step 5
        telemetry.addLine("Autonomous Complete");
        telemetry.update();
        idle();
    }

}
