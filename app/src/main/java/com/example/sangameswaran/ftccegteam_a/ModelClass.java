package com.example.sangameswaran.ftccegteam_a;

/**
 * Created by Sangameswaran on 02-03-2017.
 */

public class ModelClass {
    public String ideey,message;
    ModelClass(String ideey,String message)
    {
        this.ideey=ideey;
        this.message=message;
    }
    ModelClass()
    {}


    public String getIdeey() {
        return ideey;
    }

    public void setIdeey(String ideey) {
        this.ideey = ideey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
