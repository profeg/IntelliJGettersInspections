/**
 * Created by prof on 19.11.15.
 */
package com.github.profeg.IntelliJGettersCheckInspections;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiReturnStatementImpl;
import com.siyeh.ig.BaseInspection;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class MethodStartedFromModalVerbInspection extends BaseInspection {
  private static final String[] MODAL_VERBS = {"has","may","might","can","could","could","need","ought"};
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
    return new MethodStartedFromModalVerbInspectionVisitor();
  }
  private class MethodStartedFromModalVerbInspectionVisitor extends BaseInspectionVisitor {
    @Override
    public void visitClass(PsiClass aClass) {
      super.visitClass(aClass);
      PsiMethod[] methods = aClass.getMethods();
      PsiField[] fields = aClass.getFields();
      checkForMethodsStartedFromModalVerb(methods, fields);
    }
    private void checkForMethodsStartedFromModalVerb(PsiMethod[] methods, PsiField[] fields) {
      for (PsiField field : fields) {
        for (PsiMethod method : methods) {
          if (methodNameStaredWithModalVerb(method.getName(),field.getName()) &&
              methodIsCanonicalGetter(method, field)) {
            registerError(method.getNameIdentifier(), method.getName() + " method's name started with a modal verb. Maybe it's a getter?");
          }
        }
      }
    }
    private boolean methodNameStaredWithModalVerb(String methodName, String property) {
      for (String verb : MODAL_VERBS) {
        if (methodName.equalsIgnoreCase(verb + property)) {
          return true;
        }
      }
      return false;
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
