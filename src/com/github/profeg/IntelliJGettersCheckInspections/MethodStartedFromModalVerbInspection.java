package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class MethodStartedFromModalVerbInspection extends BaseInspection {
  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return "A method name started with a modal verb";
  }
  @NotNull
  @Override
  protected String buildErrorString(Object... infos) {
    return (infos[0].toString());
  }
  @Override
  public BaseInspectionVisitor buildVisitor() {
    return new MethodStartedFromModalVerbInspectionVisitor();
  }
  private class MethodStartedFromModalVerbInspectionVisitor extends BaseInspectionVisitor {
    private void checkForMethodsStartedFromModalVerb(PsiMethod[] methods, PsiField[] fields) {
      for(PsiMethod method : methods) {
        for (PsiField field : fields) {
          if (GetterUtils.isThisBooleanProperty(field) && GetterUtils.methodNameStaredWithModalVerb(method, field)) {
            registerError(method.getNameIdentifier(), method.getName() + " method's name started with a modal verb. Maybe it's a getter?");
          }
        }
      }
    }
    @Override
    public void visitClass(PsiClass aClass) {
      super.visitClass(aClass);
      PsiMethod[] methods = aClass.getMethods();
      PsiField[] fields = aClass.getFields();
      checkForMethodsStartedFromModalVerb(methods, fields);
    }
  }
}
