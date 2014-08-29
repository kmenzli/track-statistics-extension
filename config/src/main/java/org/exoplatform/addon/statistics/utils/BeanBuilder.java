package org.exoplatform.addon.statistics.utils;

import org.exoplatform.addons.storage.model.*;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.manager.IdentityManager;

/**
 * Created by kmenzli on 28/08/2014.
 */
abstract public class BeanBuilder {

    private static final Log LOG = ExoLogger.getLogger(BeanBuilder.class.getName());

    /**
     * The poster ${poster} save ${Verb} the activity ${activity} on the activities stream ${activities} of user ${targetUser}
     * @param statisticsBean
     * @param exoSocialActivity
     */
    public static void saveAddActivityAsStat(StatisticsBean statisticsBean, ExoSocialActivity exoSocialActivity) {

        //--- Poster User ${poster} save ${verb}
        ActorBean poster = new ActorBean();
        poster.setObjectType(ObjectType.PERSON);
        //--- Get poster name from social module
        Identity identity = getIdentityManager().getIdentity(exoSocialActivity.getPosterId(), false);
        poster.setUserName(identity.getRemoteId());
        statisticsBean.setActor(poster);

        //--- Verb
        statisticsBean.setVerb(VerbType.SAVE);

        //--- Activity to Save (activity data details)
        ObjectBean activity = new ObjectBean();
        activity.setContent(exoSocialActivity.getTitle());
        activity.setDisplayName(exoSocialActivity.getStreamOwner());
        activity.setLink(exoSocialActivity.getActivityStream().getPermaLink());
        activity.setObjectType(ObjectType.ACTIVITY);
        //objectBean.setSpentTime();
        activity.setUrl(exoSocialActivity.getStreamSourceUrl());
        statisticsBean.setObject(activity);

        //--- Target (Activity will be saved in the activities_stream )
        TargetBean activities = new TargetBean();
        activities.setObjectType(ObjectType.ACTIVITIES_STREAM);
        activities.setDisplayName(exoSocialActivity.getType());
        //--- Activities_Stream of ${User}
        ActorBean targetUser = new ActorBean();
        targetUser.setObjectType(ObjectType.PERSON);
        targetUser.setUserName(exoSocialActivity.getStreamOwner());
        activities.setActorBean(targetUser);
        statisticsBean.setTarget(activities);

    }

    /**
     * Load eXo kernel Services
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> clazz) {
        return (T) PortalContainer.getInstance().getComponentInstanceOfType(clazz);
    }

    /**
     * Load IdentityManager Service (Social Module)
     * @return
     */
    public static IdentityManager getIdentityManager() {
        return getService(IdentityManager.class);
    }



}
