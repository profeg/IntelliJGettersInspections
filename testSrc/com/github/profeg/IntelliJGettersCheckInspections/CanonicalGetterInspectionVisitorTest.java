package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.siyeh.ig.LightInspectionTestCase;
import org.jetbrains.annotations.Nullable;

public class CanonicalGetterInspectionVisitorTest extends LightInspectionTestCase {
  @Nullable
  @Override
  protected InspectionProfileEntry getInspection() {
    return new CanonicalGetterInspection();
  }
  public void testProbablyGetter() {
    doTest("class Bean {" +
        "  private int foo;" +
        "  private boolean boo;" +
        "  public int /*takeFoo method probably a getter*/takeFoo/**/() { return foo; }" +
        "  public int takeDoubleFoo() { foo *= foo; return foo; }" +
        "  public boolean isBoo() { return boo; }" +
        "}");
  }
}