package com.patrikscode;

public class Bug {
        private String id;
        private String summary;
        private String severity;
        private String priority;
        private String status;

        public Bug(String id, String summary, String severity, String priority) {
            this.id = id;
            this.summary = summary;
            this.severity = severity;
            this.priority = priority;
            this.status = "Open";
        }

        public String getId()       { return id; }
        public String getSummary()  { return summary; }
        public String getSeverity() { return severity; }
        public String getPriority() { return priority; }
        public String getStatus()   { return status; }
        public void setStatus(String status) { this.status = status; }

        public void printBug() {
            System.out.println(id + " | " + summary + " | " + severity + " | " + priority + " | " + status);
        }
    }


