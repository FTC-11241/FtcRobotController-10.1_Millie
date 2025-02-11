/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/*
 *Opmode for Millie yay!!!
 */

@TeleOp(name="Blue Steel TeleOp Pro", group="Linear Opmode")

public class BlueSteelTeleOpPro extends LinearOpMode {

    // Declare OpMode members.
    Hardware11241 robot = new Hardware11241();   // Use Team 11241's hardware

    public ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables.
        // * The init() method of the hardware class does all the work here
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)


        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            // POV Mode uses right stick to go forward/back, and side to side, left x stick to turn/spin.
            // - This uses basic math to combine motions and is easier to drive straight.
            // Setup a variable for each drive wheel to save power level for telemetry
            double leftFrontPower;
            double rightFrontPower;
            double leftBackPower;
            double rightBackPower;
            //init for claw
            //int i = 0;
            // POV Mode uses right stick to go forward/back, and side to side, left x stick to turn/spin.
            // - This uses basic math to combine motions and is easier to drive straight.
            double driveForward = gamepad1.left_stick_y;
            double driveSideways  =  gamepad1.left_stick_x;
            double turn  =  - gamepad1.right_stick_x/2;

            leftFrontPower    = Range.clip(driveForward - driveSideways + turn, -1, 1) ;
            rightFrontPower   = Range.clip(driveForward + driveSideways - turn, -1, 1) ;
            leftBackPower    = Range.clip(driveForward + driveSideways + turn, -1, 1) ;
            rightBackPower = Range.clip(driveForward - driveSideways - turn, -1, 1) ;

            /*
            // Slows down movement when the right stick is in use, making it easier to turn
            if((turn == 0) && (driveForward + driveSideways !=0)){
                leftFrontPower = Range.clip(driveForward - driveSideways, -0.75, 0.75);
                rightFrontPower = Range.clip(driveForward + driveSideways, -0.75, 0.75);
                leftRearPower = Range.clip(driveForward + driveSideways, -0.75, 0.75);
                rightRearPower = Range.clip(driveForward - driveSideways, -0.75, 0.75);
            }else if((turn == 0) && (driveForward + driveSideways == 0)){
                leftFrontPower = 0;
                rightFrontPower = 0;
                leftRearPower = 0;
                rightRearPower = 0;
            }else{
                leftFrontPower = Range.clip(driveForward - driveSideways + turn, -0.5, 0.5);
                rightFrontPower = Range.clip(driveForward + driveSideways - turn, -0.5, 0.5);
                leftRearPower = Range.clip(driveForward + driveSideways + turn, -0.5, 0.5);
                rightRearPower = Range.clip(driveForward - driveSideways - turn, -0.5, 0.5);
            }
                */
            // D pad controls liftArm and hangMotor.
            if (gamepad2.dpad_up) {
                robot.liftMotor.setPower(-1);
                telemetry.addLine("yes");
            }
            if (gamepad2.dpad_down)
                robot.liftMotor.setPower(0.5);
            else
                robot.liftMotor.setPower(0);

            if (gamepad2.dpad_left) {
                robot.hangMotor.setPower(1);
            }
            if (gamepad2.dpad_right)
                robot.hangMotor.setPower(-1);
            else
                robot.hangMotor.setPower(0);

            // left bumper controls intake

            if (gamepad2.left_bumper) {
                robot.intake.setPosition(0.8);
            }
            else
                robot.intake.setPosition(0.2);


            // left bumper controls liftMotor2.
            if (gamepad2.x) {
                robot.hangMotor2.setPower(1);
            }
            else{
                robot.hangMotor2.setPower(0);
                }
            if (gamepad2.b) {
                robot.hangMotor2.setPower(-1);
            }
            else{
                robot.hangMotor2.setPower(0);
            }


            // Send calculated power to wheels

            robot.leftBackDrive.setPower(leftBackPower);
            robot.rightBackDrive.setPower(rightBackPower);
            robot.leftFrontDrive.setPower(leftFrontPower);
            robot.rightFrontDrive.setPower(rightFrontPower);

            SparkFunOTOS.Pose2D pos = robot.mouseSensor.getPosition();

            // Reset the tracking if the user requests it
            if (gamepad1.y) {
                robot.mouseSensor.resetTracking();
            }

            // Re-calibrate the IMU if the user requests it
            if (gamepad1.x) {
                if(robot.mouseSensor.calibrateImu()){
                    telemetry.addLine("IMU Calibration Complete");
                }else{
                    telemetry.addLine("IMU Calibration Failed");
                }
            }

            // Inform user of available controls
            telemetry.addLine("Press Y (triangle) on Gamepad to reset tracking");
            telemetry.addLine("Press X (square) on Gamepad to calibrate the IMU");
            telemetry.addLine("LeftFront: " + leftFrontPower);
            telemetry.addLine("LeftBack: " + leftBackPower);
            telemetry.addLine("RightFront: " + rightFrontPower);
            telemetry.addLine("RightBack: " + rightBackPower);

            // Log the position to the telemetry
            telemetry.addData("X coordinate", pos.x);
            telemetry.addData("Y coordinate", pos.y);
            telemetry.addData("Heading angle", pos.h);
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "LF (%.2f), RF (%.2f), LR (%.2f), RR (%.2f)", leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
            telemetry.update();
        }
    }
}

