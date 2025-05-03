package com.ususstudios.torgrays_datagen;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class TorgraysDatagenPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().register("helloTask", task ->
                task.doLast(_ -> System.out.println("Hello from TorgraysDatagenPlugin!")));
    }
}
