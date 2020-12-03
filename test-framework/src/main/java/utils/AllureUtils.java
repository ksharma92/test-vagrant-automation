package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;

public class AllureUtils {
    public static void updateTestCaseDetails(String testCaseName, String testCaseDescription) {
        AllureLifecycle lifecycle = Allure.getLifecycle();

        lifecycle.updateTestCase(testResult -> {
            testResult.setName(testCaseName);
            testResult.setDescription(testCaseDescription);
        });
    }
}
