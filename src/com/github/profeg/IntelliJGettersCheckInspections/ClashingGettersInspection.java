/**
 * Created by prof on 19.11.15.
 */
package com.github.profeg.IntelliJGettersCheckInspections;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiMethod;
import com.siyeh.ig.BaseInspectionVisitor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;


public class ClashingGettersInspection extends BaseJavaLocalInspectionTool {
  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return "Inappropriate use of getters names";
  }
  @NotNull
  @Override
  public PsiElementVisitor buildVisitor(ProblemsHolder holder, boolean isOnTheFly) {
    return new ClashingGettersInspectionVisitor(holder, isOnTheFly);
  }

  class ClashingGettersInspectionVisitor extends BaseInspectionVisitor {
    private final ProblemsHolder problemsHolder;
    private final boolean isOnTheFly;

    public ClashingGettersInspectionVisitor(ProblemsHolder problemsHolder, boolean isOnTheFly) {
      super();
      this.problemsHolder = problemsHolder;
      this.isOnTheFly = isOnTheFly;
    }
    @Override
    public void visitClass(PsiClass aClass) {
      PsiMethod[] methods = aClass.getAllMethods();
      Map<String,PsiMethod> propertyGetters = new HashMap<String,PsiMethod>();
      for (PsiMethod m :methods) {
        if (isPropertyGetter(m)) {
          String propertyName = extractPropertyName(m.getName());
//                System.out.println(m.getName() + " " + propertyName);
          PsiMethod previousGetter = propertyGetters.get(propertyName);
          if (previousGetter != null) {
            problemsHolder.registerProblem(m.getNameIdentifier(), "Already exist getter " + previousGetter.getName() + " for this property");
            problemsHolder.registerProblem(previousGetter.getNameIdentifier(), "Already exist getter " + m.getName() + " for this property");
//                    System.out.println("Already exist getter " + previousGetter.getName() + " for this property");
          } else {
            propertyGetters.put(propertyName,m);
          }
        }
      }
    }
    @NotNull
    private String extractPropertyName(@NotNull String methodName) {
      if (methodName.startsWith("get")) {
        return methodName.substring(3);
      }
      if (methodName.startsWith("is")) {
        return methodName.substring(2);
      }
      throw new IllegalArgumentException("method is not a getter");
    }

    private boolean isPropertyGetter(@NotNull PsiMethod m) {
      return m.getName().startsWith("get") || m.getName().startsWith("is");
    }
  }

}
