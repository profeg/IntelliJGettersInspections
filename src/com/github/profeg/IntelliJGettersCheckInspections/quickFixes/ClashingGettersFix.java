package com.github.profeg.IntelliJGettersCheckInspections.quickFixes;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class ClashingGettersFix implements LocalQuickFix {

  @NotNull
  @Override
  public String getName() {
    return "rename getProperty to isProperty";
  }

  @Override
  public void applyFix(@NotNull final Project project, @NotNull final ProblemDescriptor descriptor) {
    //TODO implement fix here instead the example below
    final PsiElement element = descriptor.getPsiElement();
    final PsiMethod method = PsiTreeUtil.getParentOfType(element, PsiMethod.class);
    if (method != null) {
      String nameWithIs = "is" + method.getName().substring(3);
      method.setName(nameWithIs);
    }
  }

  @NotNull
  @Override
  public String getFamilyName() {
    return getName();
  }

}
