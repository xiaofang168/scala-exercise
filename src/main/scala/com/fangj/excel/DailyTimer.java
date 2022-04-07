package com.fangj.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fangjie
 * @date Created in 11:35 上午 2022/3/30.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyTimer {
    private int hour;
    private int minute;
}
