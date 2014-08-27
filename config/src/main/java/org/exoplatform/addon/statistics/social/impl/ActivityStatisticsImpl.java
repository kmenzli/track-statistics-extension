package org.exoplatform.addon.statistics.social.impl;
import org.exoplatform.addons.storage.services.StatisticsService;
import org.exoplatform.social.core.activity.ActivityLifeCycleEvent;
import org.exoplatform.social.core.activity.ActivityListenerPlugin;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
/**
 * Created by kmenzli on 27/08/2014.
 */
public class ActivityStatisticsImpl extends ActivityListenerPlugin {

    private static StatisticsService statisticsService;

    public ActivityStatisticsImpl (StatisticsService statisticsServiceImpl) {

        this.statisticsService = statisticsServiceImpl;

    }

    @Override
    public void saveActivity(ActivityLifeCycleEvent event) {

        ExoSocialActivity activity = event.getSource();
    }

    @Override
    public void updateActivity(ActivityLifeCycleEvent event) {
    }

    @Override
    public void saveComment(ActivityLifeCycleEvent event) { }

    @Override
    public void likeActivity(ActivityLifeCycleEvent event) { }

}
