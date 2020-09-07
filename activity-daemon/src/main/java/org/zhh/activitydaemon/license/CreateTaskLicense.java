package org.zhh.activitydaemon.license;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class CreateTaskLicense implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 888583944346901287L;

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println(delegateTask.getName());
		System.out.println(delegateTask.getTaskDefinitionKey());
		System.out.println(delegateTask.getEventName());
		
		
		//delegateTask.setAssignee("zhh11234123");
		List<String> list = new ArrayList<String>();
		list.add("张三");
		list.add("张三2");
		list.add("张三3");
		list.add("张三4");
		list.add("张三5");
		delegateTask.addCandidateUsers(list);
	}

}
