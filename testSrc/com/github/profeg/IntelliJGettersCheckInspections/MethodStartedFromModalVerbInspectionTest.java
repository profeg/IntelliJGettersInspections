package com.github.profeg.IntelliJGettersCheckInspections;

import com.intellij.codeInspection.InspectionProfileEntry;
import com.siyeh.ig.LightInspectionTestCase;
import org.jetbrains.annotations.Nullable;

import static org.junit.Assert.*;

/**
 * Created by prof on 19.11.15.
 */
public class MethodStartedFromModalVerbInspectionTest extends LightInspectionTestCase {
  @Nullable
  @Override
  protected InspectionProfileEntry getInspection() {
    return new MethodStartedFromModalVerbInspection();
  }
  public void testModalPrefix() {
    doTest("class Bean {\n" +
        "  private int enabled;\n" +
        "  public int /*hasEnabled method's name started with a modal verb. Maybe it's a getter?*/hasEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private int enabled;\n" +
        "  public int /*canEnabled method's name started with a modal verb. Maybe it's a getter?*/canEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private int enabled;\n" +
        "  public int /*couldEnabled method's name started with a modal verb. Maybe it's a getter?*/couldEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private int enabled;\n" +
        "  public int /*mayEnabled method's name started with a modal verb. Maybe it's a getter?*/mayEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private int enabled;\n" +
        "  public int  /*mightEnabled method's name started with a modal verb. Maybe it's a getter?*/mightEnabled/**/() { return enabled; }\n" +
        "}");
    doTest("class Bean {\n" +
        "  private int enabled;\n" +
        "  public int /*needEnabled method's name started with a modal verb. Maybe it's a getter?*/needEnabled/**/() { return enabled; }\n" +
        "}");
  }
}