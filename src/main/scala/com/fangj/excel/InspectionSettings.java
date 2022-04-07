package com.fangj.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 巡检配置集合
 *
 * @author jefffang
 * @date Created in 9:23 下午 2022/3/29.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionSettings {
    /**
     * 团队
     */
    private String team;
    /**
     * 业务
     */
    private String biz;
    /**
     * 项目
     */
    private String project;
    /**
     * 指标集合
     */
    private List<InspectionIndex> indexes;
    /**
     * 每日巡检时间
     */
    private List<DailyTimer> dailyTimers;
    /**
     * 巡检大盘url地址
     */
    private String url;
    /**
     * 巡检人用户名
     */
    private String username;
    /**
     * 巡检人真实姓名
     */
    private String realname;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<InspectionIndex> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<InspectionIndex> indexes) {
        this.indexes = indexes;
    }

    public List<DailyTimer> getDailyTimers() {
        return dailyTimers;
    }

    public void setDailyTimers(List<DailyTimer> dailyTimers) {
        this.dailyTimers = dailyTimers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
