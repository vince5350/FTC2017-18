package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//Better Light needed for sensor + check for red value

@TeleOp

public class TestColorSensor extends OpMode {

    // Color Sensor and Distance center @ same IO
    ColorSensor sensorColor;
    DistanceSensor sensorDistance;
    int delayLED = 30;
    boolean stateLED = false;
    @Override
    public void init() {

    }

    @Override
    public void loop() {

        // Map Sensor
        sensorColor = hardwareMap.get(ColorSensor.class, "sensorColor");
        sensorDistance = hardwareMap.get(DistanceSensor.class,"sensorDistance");

        // Turn On/Off Led
        delayLED =+ 1;
        if (gamepad1.dpad_left && (delayLED > 30)){
            stateLED = !stateLED;
            sensorColor.enableLed(stateLED);
            delayLED = 0;
        }

        // Telemetery
        telemetry.addData("Red: ", sensorColor.red());
        telemetry.addData("Blue: ", sensorColor.blue());
        telemetry.addData("Green: ", sensorColor.green());
        telemetry.addData("Alpha: ", sensorColor.alpha());
        telemetry.addData("Distance: ",sensorDistance.getDistance(DistanceUnit.CM));
    }
}
