package model;

import java.util.List;

public class Module {
    private String moduleTitle;
    private Integer durationMinutes;
    private List<Resource> resources; 

    public Module() {}
    public Module(String moduleTitle, Integer durationMinutes, List<Resource> resources) {
        this.moduleTitle = moduleTitle;
        this.durationMinutes = durationMinutes;
        this.resources = resources;
    }

    public String getModuleTitle() { return moduleTitle; }
    public void setModuleTitle(String moduleTitle) { this.moduleTitle = moduleTitle; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public List<Resource> getResources() { return resources; }
    public void setResources(List<Resource> resources) { this.resources = resources; }
}