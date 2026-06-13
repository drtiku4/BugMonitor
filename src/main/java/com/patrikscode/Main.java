package com.patrikscode;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ApiMonitor monitor = new ApiMonitor();
        Dashboard dashboard = new Dashboard();
        ReportGenerator report = new ReportGenerator();

        monitor.addResponseTime("/api/login",  350);
        monitor.addResponseTime("/api/users",  150);
        monitor.addResponseTime("/api/orders", 600);

        dashboard.registerBug(new Bug("BUG-001", "Login Failure",         "Critical", "High"));
        dashboard.registerBug(new Bug("BUG-002", "Users not loading",     "High",     "High"));
        dashboard.registerBug(new Bug("BUG-003", "Order timeout",         "High",     "Medium"));
        dashboard.registerBug(new Bug("BUG-004", "Typo on error message", "Low",      "Low"));
        dashboard.registerBug(new Bug("BUG-005", "401 on valid token",    "Critical", "High"));

        dashboard.filterBySeverity("Critical");
        dashboard.filterBySeverity("High");
        dashboard.filterBySeverity("Low");

        dashboard.bugs.get(3).setStatus("Closed");

        report.generateReport(dashboard, monitor);
    }
}