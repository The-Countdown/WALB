package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Limelight;
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
        int targetLeftPosition = Robot.HardwareDevices.leftArm.getCurrentPosition();
        int  targetRightPosition = Robot.HardwareDevices.rightArm.getCurrentPosition();

        waitForStart();
        // Runs repeatedly
        while (opModeIsActive()) {
            // Runs repeatedly
            double rMotorPower = gamepad1.right_stick_y;
            double lMotorPower = gamepad1.left_stick_y;
            //drive
            if (!walB.limelight.isRunning) {
                walB.drive.setPower(lMotorPower, rMotorPower);
            }
            //head turn
            if (gamepad1.dpad_left) {
                walB.head.servoRotation(Robot.HardwareDevices.headRotation.getPosition() - 0.02);
            } else if (gamepad1.dpad_right) {
                walB.head.servoRotation(Robot.HardwareDevices.headRotation.getPosition() + 0.02);
            }
            //eye turn
            if (gamepad1.dpad_down) {
                walB.head.eyeRotation(Robot.HardwareDevices.eyeRotation.getPosition() - 0.002);
            } else if (gamepad1.dpad_up) {
                walB.head.eyeRotation(Robot.HardwareDevices.eyeRotation.getPosition() + 0.002);
            }

            //arm up down
            double armPower = gamepad1.right_trigger - gamepad1.left_trigger;

            //walB.arm.armPower(armPower);
            //walB.arm.armPosition();

            if (gamepad1.right_bumper) {
                targetRightPosition += 1;
                targetLeftPosition += 1;
            } else if (gamepad1.left_bumper) {
                targetRightPosition -= 1;
                targetLeftPosition -= 1;
            }
            walB.arm.armPosition(targetLeftPosition, targetRightPosition);
            telemetry.addData("left arm pose", Robot.HardwareDevices.leftArm.getCurrentPosition());
            telemetry.addData("right arm pose", Robot.HardwareDevices.rightArm.getCurrentPosition());
            telemetry.addData("left target", targetLeftPosition);
            telemetry.addData("right target", targetRightPosition);

            //claw
            if (gamepad1.a) {
                walB.claw.clawPosition(1);
            } else if (gamepad1.b) {
                walB.claw.clawPosition(0);
            }

            if (walB.limelight.getAprilTagID() == -1) {
                telemetry.addLine("null");
            } else if (walB.limelight.getAprilTagID() % 2 == 1) {
                //walB.head.eyeRotation(0);
                telemetry.addLine("even");
            } else if (walB.limelight.getAprilTagID() % 2 == 0) {
                //walB.head.eyeRotation(1);
                telemetry.addLine("odd");
            }

            if (gamepad1.x) {
                walB.limelight.driveToTag();
            }
            telemetry.update();
        }
    }
}


/*
drive towards tag
 */