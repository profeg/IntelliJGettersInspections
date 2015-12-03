package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class CanonicalGetterInspection extends BaseInspection {
  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return "Probably getter inspections";
  }
  @NotNull
  @Override
  protected String buildErrorString(Object... infos) {
    return (infos[0].toString());
  }
  @Override
  public BaseInspectionVisitor buildVisitor() {
    return new CanonicalGetterInspectionVisitor();
  }
  private class CanonicalGetterInspectionVisitor extends BaseInspectionVisitor {
    private void checkForMethodWhichCanBeGetter(PsiMethod[] methods, PsiField[] fields) {
      for (PsiField field : fields) {
        for (PsiMethod method : methods) {
          if (!GetterUtils.methodStartsWithGetterSing(field, method) && GetterUtils.methodIsCanonicalGetter(method, field)) {
            registerError(method.getNameIdentifier(), method.getName() + " method probably a getter");
          }
        }
      }
    }
    @Override
    public void visitClass(PsiClass aClass) {
      super.visitClass(aClass);
      PsiMethod[] methods = aClass.getMethods();
      PsiField[] fields = aClass.getFields();
      checkForMethodWhichCanBeGetter(methods, fields);
    }
  }
}
