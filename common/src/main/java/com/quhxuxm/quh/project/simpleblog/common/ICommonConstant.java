package com.quhxuxm.quh.project.simpleblog.common;

public interface ICommonConstant {
    interface RoleName {
        String AUTHOR = "AUTHOR";
        String ADMIN = "ADMIN";
    }

    interface DefaultValue {
        long AUTHOR_SELECTED_TAG_INIT_WEIGHT = 10L;
        long ARTICLE_SELECTED_TAG_INIT_WEIGHT = 10L;
        long ANTHOLOGY_SELECTED_TAG_INIT_WEIGHT = 10L;
    }

    interface RequestParameterName {
        String PAGE_INDEX_REQUEST_PARAM_NAME = "pindex";
        String PAGE_SIZE_REQUEST_PARAM_NAME = "psize";
        String PAGE_IS_ASC="pasc";
    }
}
