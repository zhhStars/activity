package org.zhh.activitydaemon;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zhh.activitydaemon.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Btest {
	
	@Autowired
    UserService testService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired  
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;
    
	@Test
	@Ignore
    public void TestStartProcess() {
		
		identityService.setAuthenticatedUserId("流程发起人id123");
		
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess");
        String pid = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID：" + pid);
         
        // 查询当前任务  1
        Task currentTask = taskService.createTaskQuery().processInstanceId(pid).singleResult();
        System.out.println(currentTask.getAssignee());
        System.out.println("第一次执行前，任务名称：" + currentTask.getName());
        taskService.complete(currentTask.getId());  // 执行任务
    }
	
}
