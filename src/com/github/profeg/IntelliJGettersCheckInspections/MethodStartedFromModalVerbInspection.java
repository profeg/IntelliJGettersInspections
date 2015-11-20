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

public class MethodStartedFromModalVerbInspection extends BaseInspection {
  private static final String[] MODAL_VERBS = {"has","may","might","can","could","could","need","ought"};
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
    @Override
    public void visitClass(PsiClass aClass) {
      super.visitClass(aClass);
      PsiMethod[] methods = aClass.getMethods();
      PsiField[] fields = aClass.getFields();
      checkForMethodsStartedFromModalVerb(methods, fields);
    }
    private void checkForMethodsStartedFromModalVerb(PsiMethod[] methods, PsiField[] fields) {
      List<PsiField> booleanProperties = new LinkedList<PsiField>();
      for (PsiField field : fields) {
        if (GetterUtils.isThisBooleanProperty(field)) {
          booleanProperties.add(field);
        }
      }
        for (PsiMethod method : methods) {
          if (methodNameStaredWithModalVerb(method,booleanProperties)) {
            registerError(method.getNameIdentifier(), method.getName() + " method's name started with a modal verb. Maybe it's a getter?");
          }
        }
    }
    private boolean methodNameStaredWithModalVerb(PsiMethod method, List<PsiField> properties) {
      for (String verb : MODAL_VERBS) {
        for (PsiField property : properties) {
          if (method.getName().equalsIgnoreCase(verb + property.getName()) && GetterUtils.methodIsCanonicalGetter(method, property)) {
            return true;
          }
        }
      }
      return false;
    }
  }
}
