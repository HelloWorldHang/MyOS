package com.os.device.funInterface;

import com.os.device.PhysicalAddr;

public interface DeviceControll {
    void format();
    PhysicalAddr transtAddr(long logicId);

    // 磁盘分配回收

}
