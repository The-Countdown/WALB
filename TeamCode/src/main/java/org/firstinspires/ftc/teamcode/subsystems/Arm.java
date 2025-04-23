package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Arm extends Robot.HardwareDevices {
    private Robot walB;

    public Arm(Robot walB) {
        this.walB = walB;
    }

    public void armPower(double power) {
        Robot.HardwareDevices.leftArm.setPower(power);
        Robot.HardwareDevices.rightArm.setPower(power);
    }

    public void armPosition(int left, int right) {
        Robot.HardwareDevices.leftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.HardwareDevices.rightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.HardwareDevices.leftArm.setTargetPosition(left);
        Robot.HardwareDevices.rightArm.setTargetPosition(right);
        Robot.HardwareDevices.leftArm.setPower(0.2);
        Robot.HardwareDevices.rightArm.setPower(0.2);
    }
}