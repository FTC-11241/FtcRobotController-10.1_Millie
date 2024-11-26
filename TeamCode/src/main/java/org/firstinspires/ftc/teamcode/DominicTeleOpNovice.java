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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/*
 * clawbot control scheme
 */

@TeleOp(name="Blue Steel TeleOp but for Dominic", group="Linear Opmode")
//@Disabled
public class DominicTeleOpNovice extends LinearOpMode {

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
        double leftFrontPower = 0;
        double rightFrontPower = 0;

        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            // POV Mode uses right stick to go forward/back, and side to side, left x stick to turn/spin.
            // - This uses basic math to combine motions and is easier to drive straight.
            double leftDriveForward = gamepad1.left_stick_y;
            double rightDriveForward = gamepad1.right_stick_y;
            //double driveSideways = gamepad1.left_stick_x/2;
            //double turn = -gamepad1.right_stick_x/2;

            leftFrontPower = Range.clip(leftDriveForward /*- driveSideways + turn*/, -1, 1);
            rightFrontPower = Range.clip(rightDriveForward /*+ driveSideways - turn*/, -1, 1);


            /*
            // Slows down movement when the right stick is in use, making it easier to turn
            if((turn == 0) && (leftDriveForward + driveSideways !=0)){
                leftFrontPower = Range.clip(leftDriveForward - driveSideways, -0.75, 0.75);
                rightFrontPower = Range.clip(leftDriveForward + driveSideways, -0.75, 0.75);
                leftRearPower = Range.clip(leftDriveForward + driveSideways, -0.75, 0.75);
                rightRearPower = Range.clip(leftDriveForward - driveSideways, -0.75, 0.75);
            }else if((turn == 0) && (leftDriveForward + driveSideways == 0)){
                leftFrontPower = 0;
                rightFrontPower = 0;
                leftRearPower = 0;
                rightRearPower = 0;
            }else{
                leftFrontPower = Range.clip(leftDriveForward - driveSideways + turn, -0.5, 0.5);
                rightFrontPower = Range.clip(leftDriveForward + driveSideways - turn, -0.5, 0.5);
                leftRearPower = Range.clip(leftDriveForward + driveSideways + turn, -0.5, 0.5);
                rightRearPower = Range.clip(leftDriveForward - driveSideways - turn, -0.5, 0.5);
            }
            */
            // D pad controls liftArm.

            //Liftmotor is the shoulder, defines the height of the hand
            if (gamepad1.y) {
                robot.liftMotor.setPower(-1);
            }
            else
                robot.liftMotor.setPower(0);

            if (gamepad1.a)
                robot.liftMotor.setPower(0.5);
            else
                robot.liftMotor.setPower(0);

            //Hangmotor is the arm, defines the length of the arm
            if (gamepad1.x) {
                robot.hangMotor.setPower(-1);
            }
            else {
                robot.hangMotor.setPower(0);
            }
            if (gamepad1.b)
                robot.hangMotor.setPower(1);
            else
                robot.hangMotor.setPower(0);


            // Orient is the wrist, determines the orientation of the hand *DEPRECATED*
             //intake is the fingers, grips the piece [probably wont need to touch this]
            if (gamepad1.left_bumper) {

                if(robot.intake.getPosition() == 1.0){
                    robot.intake.setPosition(0.0);
                } else {
                    robot.intake.setPosition(0.45);
                }
            }



            // left bumper controls intakeL.
            //if (gamepad1.left_bumper) {
            //robot.intakeL.setPosition(0.50);

            // Send calculated power to wheels

            robot.leftDrive.setPower(leftFrontPower);
            robot.rightDrive.setPower(rightFrontPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "LF (%.2f), RF (%.2f), LR (%.2f), RR (%.2f)", leftFrontPower, rightFrontPower, leftRearPower, rightRearPower);
            telemetry.update();
        }
    }
}

