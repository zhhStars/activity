package org.zhh.activitydaemon.license;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class CreateTaskLicense implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 888583944346901287L;

	@Override
	public void notify(DelegateTask delegateTask) {
		if("usertask2".equals(delegateTask.getTaskDefinitionKey())){
			delegateTask.addCandidateUser("组长:zhh123");
		}
		if("usertask3".equals(delegateTask.getTaskDefinitionKey())){
			delegateTask.addCandidateUser("经理:li4");
		}
		System.out.println(delegateTask.getName());
		System.out.println(delegateTask.getTaskDefinitionKey());
		System.out.println(delegateTask.getEventName());
		
	}

}
