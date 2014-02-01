package org.wso2.carbon.social;

import com.google.gson.JsonObject;
import org.mozilla.javascript.NativeObject;
import org.wso2.carbon.social.service.SocialActivityService;

import java.util.List;

public class SocialActivityServiceImpl implements SocialActivityService {


    private ActivityPublisher activityPublisher = new ActivityPublisher();
    private ActivityBrowser activityBrowser = new ActivityBrowser();

    @Override
    public String publish(NativeObject activity) {
        return activityPublisher.publish(activity);
    }

    @Override
    public String[] listActivities(String contextId, String tenantDomain, int limit, String sinceId) {
        List<Activity> activities = activityBrowser.listActivitiesChronologically(contextId, tenantDomain,limit,sinceId);
        String[] serializedActivities = new String[activities.size()];
        for (int i = 0; i < activities.size(); i++) {
            serializedActivities[i] = activities.get(i).toString();
        }
        return serializedActivities;
    }

    @Override
    public double getRating(String targetId, String tenant, int limit, String sinceId) {
        return activityBrowser.getRating(targetId, tenant, limit, sinceId);
    }

    @Override
    public String getSocialObjectJson(String targetId, String tenant, String sortOrder, int limit, String sinceId) {
        SortOrder order;
        try {
            order = SortOrder.valueOf(sortOrder);
        } catch (IllegalArgumentException e) {
            order = SortOrder.NEWEST;
        }
        JsonObject socialObject = activityBrowser.getSocialObject(targetId, tenant, order, limit, sinceId);

        if (socialObject != null) {
            return socialObject.toString();
        } else {
            return "{}";
        }
    }
}
