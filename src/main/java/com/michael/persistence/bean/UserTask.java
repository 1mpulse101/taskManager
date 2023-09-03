package com.michael.persistence.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserTask {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private List<Task> tasks;

    public UserTask() {

    }

    public UserTask(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static class Task {
        private String taskName;
        private String taskDesc;
        private String status;
        private LocalDate creationDate;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public Task() {

        }

        public Task(String taskName, String taskDesc, String status, LocalDateTime startDate, LocalDateTime endDate) {
            this.taskName = taskName;
            this.taskDesc = taskDesc;
            this.status = status;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskDesc() {
            return taskDesc;
        }

        public void setTaskDesc(String taskDesc) {
            this.taskDesc = taskDesc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public LocalDate getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
        }
    }
}
