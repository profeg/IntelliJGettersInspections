package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.siyeh.ig.LightInspectionTestCase;
import org.jetbrains.annotations.Nullable;

public class ClashingGettersInspectionTest extends LightInspectionTestCase {
  @Nullable
  @Override
  protected InspectionProfileEntry getInspection() {
    return new ClashingGettersInspection();
  }
  public void testClashingGetters() {
    doTest("class Bean {\n" +
        "  private boolean enabled;\n" +
        "  public boolean /*Already exist getter isEnabled for this property*/getEnabled/**/() { return enabled; }\n" +
        "  public boolean /*Already exist getter getEnabled for this property*/isEnabled/**/() { return enabled; }\n" +
        "}");
  }

  public void testInheritedClashingGetters() {
    doTest(
    "class SuperBean {\n" +
      "private boolean enabled;\n" +
        "  public boolean /*Already exist getter isEnabled for this property*/getEnabled/**/() { return enabled; }\n" +
    "}\n" +
        "class Bean extends SuperBean{\n" +
        "  private boolean enabled;\n" +
        "  public boolean /*Already exist getter getEnabled for this property*/isEnabled/**/() { return enabled; }\n" +
        "}");
  }
}