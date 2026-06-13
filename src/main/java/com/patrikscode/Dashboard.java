package com.patrikscode;

import java.util.ArrayList;

public class Dashboard {
    ArrayList<Bug> bugs = new ArrayList<>();

    public void registerBug(Bug bug){
        bugs.add(bug);
        System.out.println("Bug registered: " + bug.getId());
    }

    public void filterBySeverity(String severity){
        System.out.println("Bugs with severity: " + severity + " --");
        for (Bug bu : bugs){
            if(bu.getSeverity().equalsIgnoreCase(severity)){
                bu.printBug();
            }
        }
    }

    public void printDashboard(ApiMonitor apiMonitor){
        int open = 0;
        int closed = 0;
        int critical = 0;

        for (Bug bu : bugs){
            if(bu.getStatus().equals("Open")) open++;
            if(bu.getStatus().equals("Closed")) closed++;
            if(bu.getSeverity().equals("Critical")) critical++;
        }

        System.out.println(System.lineSeparator() + "Dashboard");
        System.out.println("Total APIs  : " + apiMonitor.endpoints.size());
        System.out.println("Total Bugs  : " + bugs.size());
        System.out.println("Critical    : " + critical);
        System.out.println("Open Bugs   : " + open);
        System.out.println("Closed Bugs : " + closed);
    }
}
