package com.sankuai.inf.leaf.util;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.exception.InitException;
import com.sankuai.inf.leaf.exception.LeafServerException;
import com.sankuai.inf.leaf.exception.NoKeyException;
import com.sankuai.inf.leaf.service.SegmentService;

import java.sql.SQLException;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/11/25 17:16
 */

public class LeafSegmentUtil {

    private static SegmentService segmentService;

    static {
        try {
            segmentService = new SegmentService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InitException e) {
            e.printStackTrace();
        }
    }

    public static long getSegmentId(String key) {
        if (key == null || key.isEmpty()) {
            throw new NoKeyException();
        }
        Result result = segmentService.getId(key);
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException("很抱歉，获取分布式id失败:"+result.toString());
        }
        return result.getId();
    }
}
