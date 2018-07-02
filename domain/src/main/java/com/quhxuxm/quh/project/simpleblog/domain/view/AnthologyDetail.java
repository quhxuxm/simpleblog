package com.quhxuxm.quh.project.simpleblog.domain.view;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.Anthology;
import com.quhxuxm.quh.project.simpleblog.domain.pojo.AnthologyAdditionalInfo;

import java.io.Serializable;

public class AnthologyDetail implements Serializable {
    private Anthology anthology;
    private AnthologyAdditionalInfo additionalInfo;
    private String authorNickName;
    private Long authorIconImageId;

    public AnthologyDetail() {
    }

    public Anthology getAnthology() {
        return anthology;
    }

    public void setAnthology(Anthology anthology) {
        this.anthology = anthology;
    }

    public AnthologyAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AnthologyAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }

    public Long getAuthorIconImageId() {
        return authorIconImageId;
    }

    public void setAuthorIconImageId(Long authorIconImageId) {
        this.authorIconImageId = authorIconImageId;
    }
}
