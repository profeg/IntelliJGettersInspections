package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.siyeh.ig.LightInspectionTestCase;
import org.jetbrains.annotations.Nullable;

public class MethodStartedFromModalVerbInspectionTest extends LightInspectionTestCase {
  @Nullable
  @Override
  protected InspectionProfileEntry getInspection() {
    return new MethodStartedFromModalVerbInspection();
  }
  public void testModalPrefix() {
    doTest("class Bean {\n" +
        "  private boolean enabled;\n" +
        "  public boolean /*hasEnabled method's name started with a modal verb. Maybe it's a getter?*/hasEnabled/**/() { return enabled; }\n" +
        "  public boolean /*canEnabled method's name started with a modal verb. Maybe it's a getter?*/canEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private boolean enabled;\n" +
        "  public boolean /*couldEnabled method's name started with a modal verb. Maybe it's a getter?*/couldEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private boolean enabled;\n" +
        "  public boolean /*mayEnabled method's name started with a modal verb. Maybe it's a getter?*/mayEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private boolean enabled;\n" +
        "  public boolean  /*mightEnabled method's name started with a modal verb. Maybe it's a getter?*/mightEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private boolean enabled;\n" +
        "  public boolean /*needEnabled method's name started with a modal verb. Maybe it's a getter?*/needEnabled/**/() { return enabled; }\n" +
        "}");
  }
}