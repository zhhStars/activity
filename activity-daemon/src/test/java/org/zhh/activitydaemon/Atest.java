package org.zhh.activitydaemon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Atest {
	
	@Autowired
    UserService testService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired  
    private TaskService taskService;
	
	@Test
	@Ignore
    public void TestStartProcess() {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess");
        String pid = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID：" + pid);
		
         
        // 查询当前任务  1
        Task currentTask = taskService.createTaskQuery().processInstanceId(pid).singleResult();
        System.out.println(currentTask.getAssignee());
        System.out.println("第一次执行前，任务名称：" + currentTask.getName());
        
        
        Map<String, Object> map = new HashMap<>();
		map.put("currentUserId", "提交人id : 123");
		map.put("reviewProgress", 1);
		map.put("reviewProgressValue", "提交审批");
		map.put("state", 3);
		map.put("stateValue", "审核中");
		map.put("unread_secondStage_waitApproval", 0);// 执行任务
		taskService.complete(currentTask.getId(), map);
        
        
        // 查询当前任务  2
        currentTask = taskService.createTaskQuery().processInstanceId(pid).singleResult();
        System.out.println(currentTask.getAssignee());
        System.out.println("第二次执行前，任务名称：" + currentTask.getName());
        taskService.complete(currentTask.getId());  // 执行任务
        
        // 查询当前任务  3
        currentTask = taskService.createTaskQuery().processInstanceId(pid).singleResult();
        System.out.println("第三次执行前，任务名称：" + currentTask.getName());
        taskService.complete(currentTask.getId());  // 执行任务
        
    }
    
    @Test
    public void findTasksByUserId() {
    	List<Task> list = taskService.createTaskQuery().taskCandidateUser("经理:li4").list();
    	for(Task task : list){
    		taskService.complete(task.getId());
    	}
    }

}
