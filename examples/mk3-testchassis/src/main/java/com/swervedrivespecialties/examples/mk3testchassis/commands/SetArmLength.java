// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.swervedrivespecialties.examples.mk3testchassis.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.swervedrivespecialties.examples.mk3testchassis.subsystems.ArmSubsystem;

public class SetArmLength extends CommandBase {
  private final ArmSubsystem m_arm;
  private final double m_distance;
  private static final double m_ArmTolerance = 1;
  private int m_counter = 0;
  /** Creates a new SetArmLength. */
  public SetArmLength(ArmSubsystem arm, double distance) {
    m_arm = arm;
    m_distance = distance;
    addRequirements(m_arm);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_arm.goToPositionInches(m_distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      boolean isClose = (Math.abs(m_arm.getPositionInches() - m_distance) < m_ArmTolerance);
      if (isClose) {
        m_counter++;
      }
      else {
        m_counter = 0;
      }
    return (m_counter >= 5);
  }
}
