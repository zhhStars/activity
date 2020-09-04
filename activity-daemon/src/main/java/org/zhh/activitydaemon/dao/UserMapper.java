package org.zhh.activitydaemon.dao;

import org.zhh.activitydaemon.entity.User;

public interface UserMapper{

    User getOne(String id);

}
