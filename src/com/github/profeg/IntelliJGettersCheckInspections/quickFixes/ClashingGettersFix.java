package com.github.profeg.IntelliJGettersCheckInspections.quickFixes;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiMethod;
import org.jetbrains.annotations.NotNull;

public class ClashingGettersFix implements LocalQuickFix {

  @NotNull
  @Override
  public String getName() {
    return "ClashingGettersFix";
  }

  @Override
  public void applyFix(@NotNull final Project project, @NotNull final ProblemDescriptor descriptor) {
    @NotNull final PsiMethod instanceGetter = (PsiMethod)descriptor.getPsiElement();
    instanceGetter.getModifierList().setModifierProperty("public", true);
    instanceGetter.getModifierList().setModifierProperty("static", true);
  }

  @NotNull
  @Override
  public String getFamilyName() {
    return getName();
  }

}
