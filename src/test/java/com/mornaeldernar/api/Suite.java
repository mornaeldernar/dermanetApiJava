package com.mornaeldernar.api;

import com.mornaeldernar.api.controller.*;
import com.mornaeldernar.api.entity.*;
import com.mornaeldernar.api.ApiApplicationTests;
import org.junit.platform.suite.api.SelectClasses;

@org.junit.platform.suite.api.Suite
@SelectClasses({ApiApplicationTests.class,DoctorTest.class, ImageTest.class, PatientTest.class, SpecialityTest.class, DoctorControllerTest.class,PatientControllerTest.class})
public class Suite {
}
