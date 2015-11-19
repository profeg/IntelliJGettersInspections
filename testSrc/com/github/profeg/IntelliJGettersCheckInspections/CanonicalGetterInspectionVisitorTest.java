package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.codeInspection.canBeFinal.CanBeFinalInspection;
import com.siyeh.ig.LightInspectionTestCase;
import org.jetbrains.annotations.Nullable;

import static org.junit.Assert.*;

/**
 * Created by prof on 19.11.15.
 */
public class CanonicalGetterInspectionVisitorTest extends LightInspectionTestCase {
  @Nullable
  @Override
  protected InspectionProfileEntry getInspection() {
    return new CanonicalGetterInspection();
  }
  public void testProbablyGetter() {
    doTest("class Bean {" +
        "  private int foo;" +
        "  public int /*isNotDisabled method can be a getter with high probability*/isNotDisabled/**/() { return foo; }" +
        "}");
  }
}