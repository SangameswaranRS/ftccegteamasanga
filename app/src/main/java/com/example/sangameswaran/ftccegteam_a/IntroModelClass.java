package com.example.sangameswaran.ftccegteam_a;

import android.content.Intent;

/**
 * Created by Sangameswaran on 26-04-2017.
 */

public class IntroModelClass  {
    String about;
    int url;

    IntroModelClass()
    {}
    IntroModelClass(int url,String about)
    {
        this.url=url;
        this.about=about;
    }


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }
}
