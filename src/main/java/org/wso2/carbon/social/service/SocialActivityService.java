package org.wso2.carbon.social.service;


import org.mozilla.javascript.NativeObject;

import java.util.List;

public interface SocialActivityService {
    String publish(NativeObject activity);

    String[] listActivities(String contextId, String tenantDomain, int limit, String sinceId);

    double getRating(String targetId, String tenant, int limit, String sinceId);

    String getSocialObjectJson(String targetId, String sortOrder, String tenant, int limit, String sinceId);
}
