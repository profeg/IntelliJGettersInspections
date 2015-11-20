package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.impl.source.tree.java.PsiReturnStatementImpl;

public class GetterUtils {
  static boolean methodIsCanonicalGetter(PsiMethod method, PsiVariable field) {
    return (method.getParameterList().getParametersCount() == 0) && methodBodyContainsOnlyReturnStatement(method) && method.getReturnType().equals(field.getType());
  }
  static boolean methodBodyContainsOnlyReturnStatement(PsiMethod method) {
    PsiStatement[] statement = method.getBody().getStatements();
    return (statement.length == 1) && (statement[0] instanceof PsiReturnStatementImpl);
  }
  static boolean isMethodNameKindGetter(PsiMethod method, PsiVariable field) {
    return method.getName().equalsIgnoreCase("get" + field.getName()) ||
        method.getName().equalsIgnoreCase("is" + field.getName());
  }
}
