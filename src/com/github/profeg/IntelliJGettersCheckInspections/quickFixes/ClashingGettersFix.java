package com.github.profeg.IntelliJGettersCheckInspections.quickFixes;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ClashingGettersFix implements LocalQuickFix {

  @NotNull
  @Override
  public String getName() {
    return "rename getProperty to isProperty";
  }

  @Override
  public void applyFix(@NotNull final Project project, @NotNull final ProblemDescriptor descriptor) {
    //TODO implement fix here

  }

  @NotNull
  @Override
  public String getFamilyName() {
    return getName();
  }

}
