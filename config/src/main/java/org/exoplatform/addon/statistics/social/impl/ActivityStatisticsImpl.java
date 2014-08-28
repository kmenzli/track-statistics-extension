package org.exoplatform.addon.statistics.social.impl;
import org.exoplatform.addon.statistics.utils.BeanBuilder;
import org.exoplatform.addons.storage.model.StatisticsBean;
import org.exoplatform.addons.storage.services.StatisticsService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.activity.ActivityLifeCycleEvent;
import org.exoplatform.social.core.activity.ActivityListenerPlugin;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
/**
 * Created by kmenzli on 27/08/2014.
 */
public class ActivityStatisticsImpl extends ActivityListenerPlugin {

    private static final Log LOG = ExoLogger.getLogger(ActivityStatisticsImpl.class.getName());

    private static StatisticsService statisticsService;

    public ActivityStatisticsImpl (StatisticsService statisticsServiceImpl) {

        this.statisticsService = statisticsServiceImpl;

    }

    @Override
    public void saveActivity(ActivityLifeCycleEvent event) {

        ExoSocialActivity activity = event.getSource();

        //--- Statistics Bean
        StatisticsBean statisticsBean = new StatisticsBean();

        //--- Populate StatisticsBean with data from Business layer

        BeanBuilder.saveAddActivityAsStat(statisticsBean,activity);
        try {

            statisticsService.insert(statisticsBean);

        } catch (Exception E) {

            LOG.error(E.getMessage(),E);

        }


    }

    @Override
    public void updateActivity(ActivityLifeCycleEvent event) {
    }

    @Override
    public void saveComment(ActivityLifeCycleEvent event) { }

    @Override
    public void likeActivity(ActivityLifeCycleEvent event) { }

}
