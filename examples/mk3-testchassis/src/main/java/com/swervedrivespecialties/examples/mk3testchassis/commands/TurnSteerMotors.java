// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.swervedrivespecialties.examples.mk3testchassis.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import com.swervedrivespecialties.examples.mk3testchassis.subsystems.DrivetrainSubsystem;

public class TurnSteerMotors extends CommandBase {
  public double m_theta = 0.0;
  public ChassisSpeeds chassisSpeeds;
  private final DrivetrainSubsystem m_drivetrain;
  /** Creates a new TurnSteerMotors. */
  public TurnSteerMotors(DrivetrainSubsystem drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_theta = 0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_theta = m_theta + (Math.PI * 2/250);
    chassisSpeeds = new ChassisSpeeds(Math.cos(m_theta), Math.sin(m_theta),  0.0);
    m_drivetrain.drive(chassisSpeeds);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassisSpeeds = new ChassisSpeeds(0.0, 0.0,  0.0);
    m_drivetrain.drive(chassisSpeeds);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
