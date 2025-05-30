package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    HardwareMap hardwareMap;
    Telemetry telemetry;
    LinearOpMode opMode;
    public static class HardwareDevices {
        public static DcMotorEx leftTrack;
        public static DcMotorEx rightTrack;
        public static ServoImplEx headRotation;
        public static ServoImplEx eyeRotation;
        public static DcMotorEx leftArm;

        public static DcMotorEx rightArm;
        public static ServoImplEx leftClaw;
        public static ServoImplEx rightClaw;
        public static Limelight3A limelight;
    }
    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;

        HardwareDevices.leftTrack = hardwareMap.get(DcMotorEx.class, "leftTrack");
        HardwareDevices.rightTrack = hardwareMap.get(DcMotorEx.class, "rightTrack");
        HardwareDevices.headRotation = hardwareMap.get(ServoImplEx.class, "headRotation");
        HardwareDevices.eyeRotation = hardwareMap.get(ServoImplEx.class, "eyeRotation");
        HardwareDevices.leftArm = hardwareMap.get(DcMotorEx.class, "leftArm");
        HardwareDevices.rightArm = hardwareMap.get(DcMotorEx.class, "rightArm");
        HardwareDevices.leftClaw = hardwareMap.get(ServoImplEx.class, "leftClaw");
        HardwareDevices.rightClaw = hardwareMap.get(ServoImplEx.class, "rightClaw");


        HardwareDevices.leftTrack.setDirection(DcMotorEx.Direction.REVERSE);
        HardwareDevices.leftArm.setDirection(DcMotorSimple.Direction.REVERSE);
        HardwareDevices.leftTrack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        HardwareDevices.rightTrack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        HardwareDevices.leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        HardwareDevices.rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


// set zero stops motor from movement when no signal

        HardwareDevices.leftTrack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HardwareDevices.rightTrack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HardwareDevices.leftTrack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HardwareDevices.rightTrack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        HardwareDevices.leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HardwareDevices.rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //limelight

        HardwareDevices.limelight = hardwareMap.get(Limelight3A.class, "limelight");
        HardwareDevices.limelight.pipelineSwitch(0);
        HardwareDevices.limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        HardwareDevices.limelight.start(); // This tells Limelight to start looking!

    }
    public Drive drive = new Drive(this);
    public Head head = new Head(this);
    public Arm arm = new Arm(this);
    public Claw claw = new Claw(this);
    public Limelight limelight = new Limelight(this);

    //extends Robot.HardwareDevices means it only extends things starting with HardwareDevices in the Robot class
}
