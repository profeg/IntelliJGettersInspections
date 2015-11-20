package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

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
      List<PsiField> booleanProperties = new LinkedList<PsiField>();
      for (PsiField field : fields) {
        if (GetterUtils.isThisBooleanProperty(field)) {
          booleanProperties.add(field);
        }
      }
      for (PsiMethod method : methods) {
        if (booleanGetterStartedWithGetPrefix(booleanProperties, method)) {
          registerError(method.getNameIdentifier(), "Boolean type property must have getter started with is");
        }
      }
    }

    private boolean booleanGetterStartedWithGetPrefix(List<PsiField> fields, PsiMethod method) {
      for (PsiField field : fields) {
        return method.getName().equalsIgnoreCase("get" + field.getName()) && GetterUtils.methodIsCanonicalGetter(method, field);
      }
      return false;
    }


  }
}
