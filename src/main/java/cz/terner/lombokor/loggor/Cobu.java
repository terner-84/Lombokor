package cz.terner.lombokor.loggor;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class Cobu {

    public ConfigurationBuilder<BuiltConfiguration> x() {
        ConfigurationBuilder<BuiltConfiguration> builder
                = ConfigurationBuilderFactory.newConfigurationBuilder();
        
        AppenderComponentBuilder console = builder.newAppender("stdout", "Console");
        
        
        AppenderComponentBuilder file = builder.newAppender("log", "File");
        file.addAttribute("fileName", "C:/lombus/lomblog.log");
        
        ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
                .addComponent(builder.newComponent("CronTriggeringPolicy")
                        .addAttribute("schedule", "0 0 0 * * ?"))
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
                        .addAttribute("size", "100M"));

        /*rolling.addComponent(triggeringPolicies);*/
        AppenderComponentBuilder rollingFile
                = builder.newAppender("rolling", "RollingFile");
        rollingFile.addAttribute("fileName", "C:/lombus/rolling.log");
        rollingFile.addAttribute("filePattern", "C:/lombus/rolling-%d{MM-dd-yy}.log.gz");
        rollingFile.addComponent(triggeringPolicies);

        builder.add(rollingFile);

        RootLoggerComponentBuilder rootLogger
                = builder.newRootLogger(Level.DEBUG);
        rootLogger.add(builder.newAppenderRef("stdout"));

        

        LoggerComponentBuilder logger = builder.newLogger("cz", Level.DEBUG);
        logger.add(builder.newAppenderRef("log"));
        logger.addAttribute("additivity", false);

        builder.add(logger);
        
        LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
        standard.addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");

        console.add(standard);
        file.add(standard);
        builder.add(file);
        builder.add(console);
        builder.add(rootLogger);
        return builder;
    }

}
