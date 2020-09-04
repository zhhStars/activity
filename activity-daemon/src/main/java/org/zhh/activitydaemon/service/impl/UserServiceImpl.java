package org.zhh.activitydaemon.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhh.activitydaemon.dao.UserMapper;
import org.zhh.activitydaemon.entity.User;
import org.zhh.activitydaemon.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getById(String id) {
		return userMapper.getOne(id);
	}

}
