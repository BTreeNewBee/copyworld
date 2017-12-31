/*
 * Copyright (C), 1995-2017, 没钱有限公司
 * FileName: Test
 * Author:   Neo Geng
 * Date:     2017/12/28 10:49
 * Description: 
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.copyworld.killerwhale.sso;

import com.sun.management.OperatingSystemMXBean;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2017/12/28
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) throws IOException {
        //获取JVM的启动时间，版本及名称，当前进程ID
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("正在运行的Java虚拟机的名称："+runtimeMXBean.getName());
        System.out.println("Java虚拟机实现名称："+runtimeMXBean.getVmName());
        System.out.println("Java虚拟机实现版本："+runtimeMXBean.getVmVersion());
        System.out.println("以毫秒为单位返回Java虚拟机的大致启动时间："+runtimeMXBean.getStartTime());
        //获取JVM内存使用状况，包括堆内存和非堆内存
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println("堆内存："+memoryMXBean.getHeapMemoryUsage());//堆内存
        System.out.println("非堆内存："+memoryMXBean.getNonHeapMemoryUsage());//非堆内存
        //操作系统及硬件信息：系统名称、版本，CPU内核，机器总内存、可用内存、可用内存占比
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println("操作系统："+operatingSystemMXBean.getName());
        System.out.println("版本："+operatingSystemMXBean.getVersion());
        System.out.println("Java虚拟机可用的处理器数："+operatingSystemMXBean.getAvailableProcessors());
        System.out.println("物理内存："+operatingSystemMXBean.getTotalPhysicalMemorySize());
        System.out.println("可用物理内存："+operatingSystemMXBean.getFreePhysicalMemorySize());
        DecimalFormat decimalFormat = new DecimalFormat("0.00%");
        if (operatingSystemMXBean.getFreePhysicalMemorySize() > 0) {
            System.out.println("剩余可用内存占比："+decimalFormat.format((double) operatingSystemMXBean.getFreePhysicalMemorySize() / operatingSystemMXBean.getTotalPhysicalMemorySize()));
        }
    }

}
