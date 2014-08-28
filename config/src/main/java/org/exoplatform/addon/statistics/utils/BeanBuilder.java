package org.exoplatform.addon.statistics.utils;

import org.exoplatform.addons.storage.model.*;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;

/**
 * Created by kmenzli on 28/08/2014.
 */
abstract public class BeanBuilder {

    private static final Log LOG = ExoLogger.getLogger(BeanBuilder.class.getName());

    public static void saveAddActivityAsStat(StatisticsBean statisticsBean, ExoSocialActivity exoSocialActivity) {

        //--- Principal User
        ActorBean actorBean = new ActorBean();
        actorBean.setObjectType(ObjectType.PERSON);
        actorBean.setUserName(exoSocialActivity.getUserId());
        statisticsBean.setActor(actorBean);

        //--- Verb
        statisticsBean.setVerb(VerbType.SAVE);

        //--- Object to Save
        ObjectBean objectBean = new ObjectBean();
        objectBean.setContent(exoSocialActivity.getTitle());
        objectBean.setDisplayName(exoSocialActivity.getStreamOwner());
        objectBean.setLink(exoSocialActivity.getActivityStream().getPermaLink());
        objectBean.setObjectType(ObjectType.ACTIVITY);
        //objectBean.setSpentTime();
        objectBean.setUrl(exoSocialActivity.getStreamSourceUrl());
        statisticsBean.setObject(objectBean);

        //--- Target
        TargetBean targetBean = new TargetBean();
        //targetBean.setObjectType();
        //targetBean.setDisplayName();
        //targetBean.setActorBean();

    }



}
