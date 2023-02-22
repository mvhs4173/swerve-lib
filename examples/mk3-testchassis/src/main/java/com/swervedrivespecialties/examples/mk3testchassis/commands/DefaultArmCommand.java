// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.swervedrivespecialties.examples.mk3testchassis.commands;

import com.swervedrivespecialties.examples.mk3testchassis.subsystems.ArmSubsystem;
import com.swervedrivespecialties.examples.mk3testchassis.subsystems.ControllerRumbler;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultArmCommand extends CommandBase {
  private ArmSubsystem m_arm;
  private XboxController m_controller;
  private ControllerRumbler m_rumbler;
  /** Creates a new DefaultArmCommand. */
  public DefaultArmCommand(ArmSubsystem arm, XboxController controller, ControllerRumbler rumbler) {
    m_arm = arm;
    m_controller = controller;
    m_rumbler = rumbler;
    addRequirements(m_arm); 
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_arm.setZero();
    m_arm.goToPositionInches(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y = m_controller.getLeftY();
    double y1 = m_controller.getRightY();
    if (!(Math.abs(y) <= 0.025 && Math.abs(y1) <= 0.025)) {
      if(!(m_arm.getCurrentDrawArm() > 5 && m_arm.getCurrentDrawPivot() > 5)) {
      m_arm.goToPositionInches(m_arm.getPositionInches() + y);
      m_arm.goToPositionRotations(m_arm.getPositionRotations() + y1);
      SmartDashboard.putNumber("Arm Length", m_arm.getPositionInches());
      SmartDashboard.putNumber("arm current draw", m_arm.getCurrentDrawArm());
      }
      else {
        m_rumbler.setRumble(.5);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
