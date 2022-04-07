package com.fangj.excel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 巡检指标
 *
 * @author fangjie
 * @date Created in 1:57 下午 2022/4/1.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionIndex {
    private String name;
    private String type;
    private String desc;
}
