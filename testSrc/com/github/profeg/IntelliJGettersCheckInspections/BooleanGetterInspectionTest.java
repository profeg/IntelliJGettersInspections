package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.siyeh.ig.LightInspectionTestCase;
import org.jetbrains.annotations.Nullable;

import static org.junit.Assert.*;

/**
 * Created by prof on 19.11.15.
 */
public class BooleanGetterInspectionTest extends LightInspectionTestCase {
  @Nullable
  @Override
  protected InspectionProfileEntry getInspection() {
    return new BooleanGetterInspection();
  }
  public void testSimple() {
    doTest("class Bean {\n" +
        "  private boolean enabled;\n" +
        "  public boolean /*Boolean type property must have getter started with is*/getEnabled/**/() { return enabled; }\n" +
        "  public boolean isEnabled() { return enabled; }\n" +
        "}");
  }
}