package com.github.profeg.IntelliJGettersCheckInspections;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiReturnStatementImpl;
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
    @Override
    public void visitClass(PsiClass aClass) {
      super.visitClass(aClass);
      PsiMethod[] methods = aClass.getMethods();
      PsiField[] fields = aClass.getFields();
      checkForMethodWhichCanBeGetter(methods, fields);
    }
    private void checkForMethodWhichCanBeGetter(PsiMethod[] methods, PsiField[] fields) {
      for (PsiField field : fields) {
        for (PsiMethod method : methods) {
          if (!methodStartsWithGetterSing(field, method) && methodIsCanonicalGetter(method, field)) {
            registerError(method.getNameIdentifier(), method.getName() + " method probably a getter");
          }
        }
      }
    }
    private boolean methodStartsWithGetterSing(PsiField field, PsiMethod method) {
      return method.getName().equalsIgnoreCase("get" + field.getName()) || method.getName().equalsIgnoreCase("is" + field.getName());
    }
    private boolean methodIsCanonicalGetter(PsiMethod method, PsiVariable field) {
      return (method.getParameterList().getParametersCount() == 0) && methodBodyContainsOnlyReturnStatement(method) && method.getReturnType().equals(field.getType());
    }
    private boolean methodBodyContainsOnlyReturnStatement(PsiMethod method) {
      PsiStatement[] statement = method.getBody().getStatements();
      return (statement.length == 1) && (statement[0] instanceof PsiReturnStatementImpl);
    }
  }
}
