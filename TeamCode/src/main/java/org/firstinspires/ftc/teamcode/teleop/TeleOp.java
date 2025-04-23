package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOpTesting", group="TeleOp")
@Config
public class TeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot walB = new Robot(this);
        // Runs once on init
        Robot.HardwareDevices.headRotation.setPosition(0.5);
        Robot.HardwareDevices.eyeRotation.setPosition(0.5);

        Robot.HardwareDevices.headRotation.scaleRange(0.2, 0.8);

        waitForStart();
        // Runs repeatedly
        while (opModeIsActive()) {
            // Runs repeatedly
            double rMotorPower = gamepad1.right_stick_y;
            double lMotorPower = gamepad1.left_stick_y;
            //drive
            walB.drive.setPower(lMotorPower, rMotorPower);
            //head turn
            if (gamepad1.dpad_left) {
                walB.head.servoRotation(Robot.HardwareDevices.headRotation.getPosition() - 0.002);
            } else if (gamepad1.dpad_right) {
                walB.head.servoRotation(Robot.HardwareDevices.headRotation.getPosition() + 0.002);
            }
            //eye turn
            if (gamepad1.dpad_down) {
                walB.head.eyeRotation(Robot.HardwareDevices.eyeRotation.getPosition() - 0.002);
            } else if (gamepad1.dpad_up) {
                walB.head.eyeRotation(Robot.HardwareDevices.eyeRotation.getPosition() + 0.002);
            }

            //arm up down
            double armPower = gamepad1.right_trigger - gamepad1.left_trigger;

            walB.arm.armPower(armPower);
            //walB.arm.armPosition();

            int currentLeftPosition = 0;
            int currentRightPosition = 0;

            if (gamepad1.right_bumper) {
                walB.arm.armPower(0.2);
                currentLeftPosition = Robot.HardwareDevices.leftArm.getCurrentPosition();
                currentRightPosition = Robot.HardwareDevices.rightArm.getCurrentPosition();
            } else if (gamepad1.left_bumper) {
                walB.arm.armPower(-0.2);
                currentLeftPosition = Robot.HardwareDevices.leftArm.getCurrentPosition();
                currentRightPosition = Robot.HardwareDevices.rightArm.getCurrentPosition();
            } else {
                walB.arm.armPosition(currentLeftPosition, currentRightPosition);
            }

            //claw
            if (gamepad1.a) {
                walB.claw.clawPosition(1);
            } else if (gamepad1.b) {
                walB.claw.clawPosition(0);
            }

            telemetry.addData("left arm pose", Robot.HardwareDevices.leftArm.getCurrentPosition());
            telemetry.addData("right arm pose", Robot.HardwareDevices.rightArm.getCurrentPosition());
            telemetry.update();
        }
    }
}