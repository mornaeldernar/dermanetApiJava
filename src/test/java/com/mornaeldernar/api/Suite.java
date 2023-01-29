package com.mornaeldernar.api;

import com.mornaeldernar.api.controller.*;
import com.mornaeldernar.api.entity.*;
import com.mornaeldernar.api.ApiApplicationTests;
import org.junit.platform.suite.api.SelectClasses;

@org.junit.platform.suite.api.Suite
@SelectClasses({ApiApplicationTests.class,DiagnosticTest.class, DoctorTest.class, ImageTest.class, PatientTest.class, SpecialityTest.class, DiagnosticControllerTest.class, DoctorControllerTest.class, ImageControllerTest.class, PatientControllerTest.class, SpecialityControllerTest.class})
public class Suite {
}
