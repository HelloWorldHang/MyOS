package com.os.device.funInterface;

import com.os.device.PhysicalAddr;

public interface DeviceControll {
    void format();
    PhysicalAddr transtAddr(long logicId);
}
