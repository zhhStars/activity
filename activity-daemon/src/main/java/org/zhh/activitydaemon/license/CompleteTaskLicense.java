package org.zhh.activitydaemon.license;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class CompleteTaskLicense implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5907130895676586928L;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println(delegateTask.getTaskDefinitionKey());
		System.out.println(delegateTask.getEventName());
	}

}
