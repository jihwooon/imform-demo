/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.imform.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("App 클래스")
class AppTest {

    @DisplayName("정상적으로 작동합니다.")
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}