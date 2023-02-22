package com.swervedrivespecialties.examples.mk3testchassis;

import com.swervedrivespecialties.examples.mk3testchassis.commands.DefaultArmCommand;
import com.swervedrivespecialties.examples.mk3testchassis.commands.DriveCommand;
import com.swervedrivespecialties.examples.mk3testchassis.commands.TurnSteerMotors;
import com.swervedrivespecialties.examples.mk3testchassis.subsystems.ArmSubsystem;
import com.swervedrivespecialties.examples.mk3testchassis.subsystems.ControllerRumbler;
import com.swervedrivespecialties.examples.mk3testchassis.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.swervedrivespecialties.examples.mk3testchassis.commands.SetArmLength;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    private final ArmSubsystem arm = new ArmSubsystem();
    private final XboxController controller = new XboxController(0);
    private final Joystick joy = new Joystick(1);
    private final ControllerRumbler rumbler = new ControllerRumbler(controller);

    public RobotContainer() {
        
         SmartDashboard.putData(drivetrain);
        drivetrain.register();

        drivetrain.setDefaultCommand(new DriveCommand(
                drivetrain,
                () -> -modifyAxis(joy.getY()), // Axes are flipped here on purpose
                () -> -modifyAxis(joy.getX()),
                () -> -modifyAxis(joy.getTwist())
        )); 

        new Trigger(controller::getBackButtonPressed)
                .onTrue(new RunCommand(drivetrain::zeroGyroscope));

        Trigger rightBumperButton = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
        rightBumperButton.whileTrue(new TurnSteerMotors(drivetrain)); 
        arm.setDefaultCommand(new DefaultArmCommand(arm, controller, rumbler));
        SmartDashboard.putData(arm);
        Trigger xButton = new JoystickButton(controller, XboxController.Button.kX.value);
        xButton.onTrue(new SetArmLength(arm, 5));
        Trigger aButton = new JoystickButton(controller, XboxController.Button.kA.value);
        aButton.onTrue(new SetArmLength(arm, -5));
        Trigger bButton = new JoystickButton(controller, XboxController.Button.kB.value);
        bButton.onTrue(new RunCommand(arm::setZero));
        
        
    }

    /* public DrivetrainSubsystem getDrivetrain() {
        return drivetrain;
    } */

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    private static double modifyAxis(double value) {
        // Deadband
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        return value;
    }
}
