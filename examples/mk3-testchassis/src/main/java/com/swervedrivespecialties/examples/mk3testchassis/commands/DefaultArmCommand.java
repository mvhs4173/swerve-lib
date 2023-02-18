// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.swervedrivespecialties.examples.mk3testchassis.commands;

import com.swervedrivespecialties.examples.mk3testchassis.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultArmCommand extends CommandBase {
  private ArmSubsystem m_arm;
  private XboxController m_controller;
  /** Creates a new DefaultArmCommand. */
  public DefaultArmCommand(ArmSubsystem arm, XboxController controller) {
    m_arm = arm;
    m_controller = controller;
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
    if (!(Math.abs(y) <= 0.025)) {
      m_arm.goToPositionInches(m_arm.getPositionInches() + y);
    
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
