package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class hwMap {
    public DcMotor FLwheel  = null;
    public DcMotor FRwheel  = null;
    public DcMotor BLwheel  = null;
    public DcMotor BRwheel  = null;
    public DcMotor Arm      = null;

    public Servo   colorServo = null;
    public Servo   clawLeft    = null;
    public Servo   clawRight   = null;


    private com.qualcomm.robotcore.hardware.HardwareMap hwMap       = null;

    public hwMap(){}

    public void init(com.qualcomm.robotcore.hardware.HardwareMap ahwMap){
        hwMap = ahwMap;

        //Motors
            //Define and init motors
            FLwheel  = hwMap.dcMotor.get("FLwheel");
            FRwheel  = hwMap.dcMotor.get("FRwheel");
            BLwheel  = hwMap.dcMotor.get("BLwheel");
            BRwheel  = hwMap.dcMotor.get("BRwheel");
            Arm      = hwMap.dcMotor.get("Arm");

            //Set all motors to zero power
            FLwheel.setPower(0);
            FRwheel.setPower(0);
            BLwheel.setPower(0);
            BRwheel.setPower(0);
            Arm.setPower(0);

            //Set motors to run w/o encoders
            FLwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FRwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BLwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BRwheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //Servos
            //Define and init servos
            colorServo = hwMap.servo.get("colorServo");
            clawLeft   = hwMap.servo.get("clawLeft");
            clawRight  = hwMap.servo.get("clawRight");

            //Set Servo direction for ease of coding later on
            clawLeft.setDirection(Servo.Direction.REVERSE);

            //Set motors to default position [0] if not already there.
            if(clawRight.getPosition() < 0 || clawRight.getPosition() > 0){
                clawRight.setPosition(0);
            }
            if(clawLeft.getPosition() < 0 || clawLeft.getPosition() > 0 ){
                clawLeft.setPosition(0);
            }
    }
}