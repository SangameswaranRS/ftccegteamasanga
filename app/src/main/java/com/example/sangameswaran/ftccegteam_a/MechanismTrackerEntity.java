package com.example.sangameswaran.ftccegteam_a;

/**
 * Created by Sangameswaran on 23-05-2017.
 */

public class MechanismTrackerEntity {
    String reporting_time,name_of_mechanism,purpose_of_mechanism,percentage_of_success,failure_reason,priority,comment;


    public MechanismTrackerEntity()
    {}

    public MechanismTrackerEntity(String reporting_time, String name_of_mechanism, String purpose_of_mechanism, String percentage_of_success, String failure_reason, String priority,String comment) {
        this.reporting_time = reporting_time;
        this.name_of_mechanism = name_of_mechanism;
        this.purpose_of_mechanism = purpose_of_mechanism;
        this.percentage_of_success = percentage_of_success;
        this.failure_reason = failure_reason;
        this.priority = priority;
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReporting_time() {
        return reporting_time;
    }

    public void setReporting_time(String reporting_time) {
        this.reporting_time = reporting_time;
    }

    public String getName_of_mechanism() {
        return name_of_mechanism;
    }

    public void setName_of_mechanism(String name_of_mechanism) {
        this.name_of_mechanism = name_of_mechanism;
    }

    public String getPurpose_of_mechanism() {
        return purpose_of_mechanism;
    }

    public void setPurpose_of_mechanism(String purpose_of_mechanism) {
        this.purpose_of_mechanism = purpose_of_mechanism;
    }

    public String getPercentage_of_success() {
        return percentage_of_success;
    }

    public void setPercentage_of_success(String percentage_of_success) {
        this.percentage_of_success = percentage_of_success;
    }

    public String getFailure_reason() {
        return failure_reason;
    }

    public void setFailure_reason(String failure_reason) {
        this.failure_reason = failure_reason;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
