/**
 * Created by prof on 19.11.15.
 */
package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class BooleanGetterInspection extends BaseInspection {
  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return "Getter name for boolean type property";
  }

  @NotNull
  @Override
  protected String buildErrorString(Object... infos) {
    return (infos[0].toString());
  }

  @Override
  public BaseInspectionVisitor buildVisitor() {
    return new GetterBooleanInspectionVisitor();
  }

  private class GetterBooleanInspectionVisitor extends BaseInspectionVisitor {
    @Override
    public void visitClass(PsiClass aClass) {
      super.visitClass(aClass);
      PsiMethod[] methods = aClass.getMethods();
      PsiField[] fields = aClass.getFields();
      checkForBooleanFields(methods, fields);
    }

    private void checkForBooleanFields(PsiMethod[] methods, PsiField[] fields) {
      for (PsiField field : fields) {
        String fieldType = field.getType().getCanonicalText();
        if ("boolean".equals(fieldType) || "java.lang.Boolean".equals(fieldType)) {
          for (PsiMethod method : methods) {
            if (method.getName().equalsIgnoreCase("get" + field.getName())) {
              registerError(method.getNameIdentifier(), "Boolean type property must have getter started with is");
            }
          }
        }
      }
    }
  }
}
