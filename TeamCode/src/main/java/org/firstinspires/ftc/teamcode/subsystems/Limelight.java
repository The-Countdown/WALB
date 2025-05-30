package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

public class Limelight extends Robot.HardwareDevices {
    private final Robot walB;
    public boolean isRunning = false;

    public Limelight(Robot walB) {
        this.walB = walB;
    }

    public int getAprilTagID() {
        if (Robot.HardwareDevices.limelight.getLatestResult().isValid()) {
            return Robot.HardwareDevices.limelight.getLatestResult().getFiducialResults().get(0).getFiducialId();
        } else {
            return -1;
        }
    }
    public void driveToTag() {
        //if theres an april tag (?? does .isValid means theres an april tag?)
        if (Robot.HardwareDevices.limelight.getLatestResult().isValid()) {
            isRunning = true;
            Pose3D pose = Robot.HardwareDevices.limelight.getLatestResult().getFiducialResults().get(0).getTargetPoseCameraSpace();
            double y = pose.getPosition().y;
            double z = pose.getPosition().z;
            double yaw = Math.toDegrees(pose.getOrientation().getYaw());//deg
            double goalDistance = 0.3; //0.3 meters away from tag
            double distanceError = z - goalDistance;

            if (distanceError < 0.05 && yaw < 2) {
                walB.drive.setPower(0, 0);
                isRunning = false;
            }
            //tune
            double kforward = 4;
            double kturn = 0;
            //drive values
            double forwardPower = kforward * distanceError;
            double turnPower = yaw * kturn;
            //motor powers (make sure right wheels turning might have to reverse)
            double leftpower = -forwardPower - turnPower;
            double rightpower = -forwardPower + turnPower;

            walB.drive.setPower(leftpower, rightpower);

        } else {
            isRunning = false;
            walB.drive.setPower(0, 0);
        }
    }


}

        /*
     fiducial.getRobotPoseTargetSpace(); // Robot pose relative it the AprilTag Coordinate System (Most Useful)
     fiducial.getCameraPoseTargetSpace(); // Camera pose relative to the AprilTag (useful)
     fiducial.getRobotPoseFieldSpace(); // Robot pose in the field coordinate system based on this tag alone (useful)
     fiducial.getTargetPoseCameraSpace(); // AprilTag pose in the camera's coordinate system (not very useful)
     fiducial.getTargetPoseRobotSpace(); // AprilTag pose in the robot's coordinate system (not very useful)
      */
