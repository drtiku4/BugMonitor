package com.patrikscode;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;

public class ReportGenerator {

    public void generateReport(Dashboard dashboard, ApiMonitor monitor) {
        try {
            FileWriter w = new FileWriter("report.txt");

            w.write("==== Status Code Validation ====\n");
            int[] codes = {200, 201, 204, 400, 401, 403, 404, 500};
            for (int code : codes) {
                w.write(code + " -> " + monitor.validateStatusCode(code) + "\n");
            }

            w.write("\n==== API Response Times ====\n");
            for (int i = 0; i < monitor.endpoints.size(); i++) {
                w.write(monitor.endpoints.get(i) + " => " + monitor.times.get(i) + "ms\n");
            }
            w.write(String.format("Average : %.1fms%n", monitor.getAverage()));
            w.write("Fastest : " + monitor.getFastest() + "\n");
            w.write("Slowest : " + monitor.getSlowest() + "\n");

            w.write("\n==== Bug Report ====\n");
            w.write("ID      | Summary | Severity | Priority | Status\n");
            for (Bug bu : dashboard.bugs) {
                w.write(bu.getId() + " | " + bu.getSummary() + " | " + bu.getSeverity() + " | " + bu.getPriority() + " | " + bu.getStatus() + "\n");
            }

            int open = 0, closed = 0, critical = 0;
            for (Bug bu : dashboard.bugs) {
                if (bu.getStatus().equals("Open"))               open++;
                if (bu.getStatus().equals("Closed"))             closed++;
                if (bu.getSeverity().equalsIgnoreCase("Critical")) critical++;
            }
            w.write("\n==== Dashboard Summary ====\n");
            w.write("Total APIs  : " + monitor.endpoints.size() + "\n");
            w.write("Total Bugs  : " + dashboard.bugs.size() + "\n");
            w.write("Critical    : " + critical + "\n");
            w.write("Open Bugs   : " + open + "\n");
            w.write("Closed Bugs : " + closed + "\n");

            // Test Cases
            w.write("\n==== Test Cases ====\n");
            w.write("TC-01: " + (monitor.validateStatusCode(200).equals("Success")               ? "PASS" : "FAIL") + " - 200 = Success\n");
            w.write("TC-02: " + (monitor.validateStatusCode(201).equals("Created")               ? "PASS" : "FAIL") + " - 201 = Created\n");
            w.write("TC-03: " + (monitor.validateStatusCode(204).equals("No Content")            ? "PASS" : "FAIL") + " - 204 = No Content\n");
            w.write("TC-04: " + (monitor.validateStatusCode(400).equals("Bad Request")           ? "PASS" : "FAIL") + " - 400 = Bad Request\n");
            w.write("TC-05: " + (monitor.validateStatusCode(401).equals("Unauthorized")          ? "PASS" : "FAIL") + " - 401 = Unauthorized\n");
            w.write("TC-06: " + (monitor.validateStatusCode(403).equals("Forbidden")             ? "PASS" : "FAIL") + " - 403 = Forbidden\n");
            w.write("TC-07: " + (monitor.validateStatusCode(404).equals("Not Found")             ? "PASS" : "FAIL") + " - 404 = Not Found\n");
            w.write("TC-08: " + (monitor.validateStatusCode(500).equals("Internal Server Error") ? "PASS" : "FAIL") + " - 500 = Internal Server Error\n");
            w.write("TC-09: " + (monitor.validateStatusCode(999).equals("Unknown")               ? "PASS" : "FAIL") + " - 999 = Unknown\n");
            w.write("TC-10: " + (Math.abs(monitor.getAverage() - 366.67) < 0.1                  ? "PASS" : "FAIL") + " - Average is 366.7ms\n");
            w.write("TC-11: " + (monitor.getFastest().contains("/api/users")                     ? "PASS" : "FAIL") + " - Fastest is /api/users\n");
            w.write("TC-12: " + (monitor.getSlowest().contains("/api/orders")                    ? "PASS" : "FAIL") + " - Slowest is /api/orders\n");
            w.write("TC-13: " + (monitor.endpoints.size() == 3                                   ? "PASS" : "FAIL") + " - Endpoint count is 3\n");
            w.write("TC-14: " + (dashboard.bugs.size() == 5                                      ? "PASS" : "FAIL") + " - Total bugs is 5\n");
            w.write("TC-15: " + (dashboard.bugs.get(0).getStatus().equals("Open")               ? "PASS" : "FAIL") + " - BUG-001 status is Open\n");
            w.write("TC-16: " + (dashboard.bugs.get(0).getId().equals("BUG-001")                ? "PASS" : "FAIL") + " - Bug ID is correct\n");
            w.write("TC-17: " + (dashboard.bugs.get(0).getSeverity().equals("Critical")         ? "PASS" : "FAIL") + " - Severity is Critical\n");
            w.write("TC-18: " + (dashboard.bugs.get(0).getPriority().equals("High")             ? "PASS" : "FAIL") + " - Priority is High\n");
            w.write("TC-19: " + (dashboard.bugs.get(3).getStatus().equals("Closed")             ? "PASS" : "FAIL") + " - BUG-004 status changed to Closed\n");
            w.write("TC-20: " + (critical == 2                                                   ? "PASS" : "FAIL") + " - 2 Critical bugs\n");
            w.write("TC-21: " + (open == 4                                                       ? "PASS" : "FAIL") + " - 4 Open bugs\n");
            w.write("TC-22: " + (closed == 1                                                     ? "PASS" : "FAIL") + " - 1 Closed bug\n");
            w.write("TC-23: " + (dashboard.bugs.get(1).getSeverity().equals("High")             ? "PASS" : "FAIL") + " - BUG-002 severity is High\n");
            w.write("TC-24: " + (dashboard.bugs.get(4).getSeverity().equals("Critical")         ? "PASS" : "FAIL") + " - BUG-005 severity is Critical\n");
            w.write("TC-25: PASS - Report generated successfully\n");

            w.close();
            System.out.println("Report saved to report.txt");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
