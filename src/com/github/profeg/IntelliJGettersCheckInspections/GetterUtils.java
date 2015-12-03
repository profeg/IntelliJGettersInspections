package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.impl.source.tree.java.PsiReturnStatementImpl;

public class GetterUtils {
  private static final String[] MODAL_VERBS = {"has","may","might","can","could","could","need","ought"};
  static boolean methodIsCanonicalGetter(PsiMethod method, PsiVariable field) {
    return (method.getParameterList().getParametersCount() == 0) && methodBodyContainsOnlyReturnStatement(method) && method.getReturnType().equals(field.getType());
  }
  static boolean methodBodyContainsOnlyReturnStatement(PsiMethod method) {
    PsiStatement[] statement = method.getBody().getStatements();
    return (statement.length == 1) && (statement[0] instanceof PsiReturnStatementImpl);
  }
  static boolean isThisBooleanProperty(PsiField field) {
    String fieldType = field.getType().getCanonicalText();
    return "boolean".equals(fieldType) || "java.lang.Boolean".equals(fieldType);
  }
  static boolean methodNameStaredWithModalVerb(PsiMethod method,PsiField property) {
    for (String verb : MODAL_VERBS) {
        if (method.getName().equalsIgnoreCase(verb + property.getName()) && GetterUtils.methodIsCanonicalGetter(method, property)) {
          return true;
      }
    }
    return false;
  }
  static boolean methodStartsWithGetterSing(PsiField field, PsiMethod method) {

    return method.getName().equalsIgnoreCase("get" + field.getName()) ||
        method.getName().equalsIgnoreCase("is" + field.getName()) ||
        GetterUtils.methodNameStaredWithModalVerb(method,field);
  }
}
