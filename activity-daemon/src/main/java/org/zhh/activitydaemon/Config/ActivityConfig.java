package org.zhh.activitydaemon.Config;

import org.activiti.engine.*;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;

@Configuration
public class ActivityConfig {

    @Value("${spring.activiti.schema:}")
    private String databaseSchema;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    // 配置并注入org.activiti.spring.SpringProcessEngineConfiguration，通过它设置activity一系列参数
    @Bean(name = {"processEngineConfiguration"})
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();

        // 配置activity数据源，使用默认的数据源
        configuration.setDataSource(dataSource);

        // 让activity提供的实现类和项目中的实现类使用同一个事物控制器
        configuration.setTransactionManager(platformTransactionManager);

        // 如果oracle数据库有多个实例需要指定当前实例(库名)
        System.out.println("zhh"+databaseSchema);
        if (!StringUtils.isEmpty(this.databaseSchema)) {
            configuration.setDatabaseSchema(this.databaseSchema);
        }

        // 若表不存在，则自动创建
        configuration.setDatabaseSchemaUpdate("true");


        // 是否开启异步执行
        configuration.setAsyncExecutorActivate(false);

        // 对于历史数据，保存到何种粒度，Activiti提供了history-level属性对其进行配置
        // 该属性有以下四个值：
        // none：不保存任何的历史数据，因此，在流程执行过程中，这是最高效的。
        // activity：级别高于none，保存流程实例与流程行为，其他数据不保存。
        // audit：除activity级别会保存的数据外，还会保存全部的流程任务及其属性。audit为history的默认值。
        // full：保存历史数据的最高级别，除了会保存audit级别的数据外，还会保存其他全部流程相关的细节数据，包括一些流程参数等。
        configuration.setHistoryLevel(HistoryLevel.FULL);

        // 设置activity默认的id生成策略
        // 默认策略为 数字自增(或者随机自增)；在高并发场景下建议采用UUID的策略   这里不配置
        //configuration.setIdGenerator();

        // 启动自动部署流程
        try {
            Resource[] resources = (new PathMatchingResourcePatternResolver()).getResources("classpath:processes/*.bpmn");
            configuration.setDeploymentResources(resources);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        configuration.setDbIdentityUsed(true);
        return configuration;
    }

    // 创建并注入ProcessEngine核心引擎对象，通过该类创建activity相关实现类
    @Bean
    public ProcessEngine processEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) { return processEngine.getRepositoryService(); }


    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) { return processEngine.getRuntimeService(); }


    @Bean
    public TaskService taskService(ProcessEngine processEngine) { return processEngine.getTaskService(); }

    @Bean
    public FormService formService(ProcessEngine processEngine) { return processEngine.getFormService(); }


    @Bean
    public HistoryService historyService(ProcessEngine processEngine) { return processEngine.getHistoryService(); }


    @Bean
    public IdentityService identityService(ProcessEngine processEngine) { return processEngine.getIdentityService(); }


    @Bean
    public ManagementService managementService(ProcessEngine processEngine) { return processEngine.getManagementService(); }







}
