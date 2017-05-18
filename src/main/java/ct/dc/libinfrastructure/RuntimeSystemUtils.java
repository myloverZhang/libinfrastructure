/**
 * @auther wangzs
 * @createDate 17-4-4
 */

package ct.dc.libinfrastructure;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * 运行时系统工具类
 */
public class RuntimeSystemUtils {
    private final static String OS_NAME = System.getProperty("os.name").toLowerCase();
    private final static String OS_VERS = System.getProperty("os.version").toLowerCase();
    private final static String OS_ARCH = System.getProperty("os.arch").toLowerCase();
    private final static String PROJECT_ROOT_PATH = System.getProperty("user.dir");

    private final static String JAVA_VERS = System.getProperty("java.version").toLowerCase();

    private final static Integer CPU_TOTAL_CORES = Runtime.getRuntime().availableProcessors();

    private final static OperatingSystemMXBean OSMXB = (OperatingSystemMXBean) ManagementFactory.
            getOperatingSystemMXBean();
    private final static Integer KB = 1024;
    private final static Double TO_DOUBLE = 1.00;

    private static Double physicalMemMax = null;

    /**
     * 获取程序根目录
     * @return
     */
    public static String getProjectRootPath(){
        return PROJECT_ROOT_PATH;
    }


    /**
     * 获取当前Swap空闲空间尺寸（MB）
     * @return
     */
    public static Double getFreeSwapSpaceSize(){
        return OSMXB.getFreeSwapSpaceSize() * TO_DOUBLE/ KB / KB;
    }

    /**
     * 获取当前总的Swap空间尺寸（MB）
     * @return
     */
    public static Double getTotalSwapSpaceSize(){
        return OSMXB.getTotalSwapSpaceSize()* TO_DOUBLE / KB / KB;
    }

    /**
     * 获取当前进程cpu时间
     * @return
     */
    public static Long getProcessCpuTime(){
        return OSMXB.getProcessCpuTime();
    }

    /**
     * 获取线程总数
     * @return
     */
    public static Integer getThreadCount(){
        ThreadGroup parentThread;
        for (parentThread = Thread.currentThread().getThreadGroup();
             parentThread.getParent() != null;
             parentThread = parentThread.getParent());
        return parentThread.activeCount();
    }

    /**
     * 获取当前进程负载
     * @return
     */
    public static Double getProcessCpuLoad(){
        return OSMXB.getProcessCpuLoad();
    }

    /**
     * 获取当前已经提交的虚机内存（MB）
     * @return
     */
    public static Double getCommittedVirtualMemorySize(){
        return OSMXB.getCommittedVirtualMemorySize() * TO_DOUBLE / KB / KB;
    }

    /**
     * 获取当前系统负载
     * @return
     */
    public static Double getSystemLoadAverage(){
        return OSMXB.getSystemLoadAverage();
    }

    /**
     * 获取总的物理内存（MB）
     * @return
     */
    public static Double getMaxPhysicalMemory(){
        if(physicalMemMax == null || physicalMemMax <= 0){
            physicalMemMax = OSMXB.getTotalPhysicalMemorySize()* TO_DOUBLE / KB / KB;
        }
        return physicalMemMax;
    }

    /**
     * 获取总的物理内存（MB）
     * @return
     */
    public static Double getFreePhysicalMemory(){
        return OSMXB.getFreePhysicalMemorySize()* TO_DOUBLE / KB / KB;
    }

    /**
     * 获取CPU的核心数
     * @return
     */
    public static Integer getTotalCpuCores(){
        return CPU_TOTAL_CORES;
    }

    /**
     * 获取jvm已经申请到的内存总量（MB）
     * @return
     */
    public static Double getTotalJvmMemory(){
        return Runtime.getRuntime().totalMemory()* TO_DOUBLE / KB / KB;
    }

    /**
     * 获取jvm可以空闲的内存（MB）
     * @return
     */
    public static Double getFreeJvmMemory(){
        return Runtime.getRuntime().freeMemory()* TO_DOUBLE / KB / KB;
    }

    /**
     * 获取jvm可以申请的最大内存（MB）
     * @return
     */
    public static Double getMaxJvmMemory(){
        return Runtime.getRuntime().maxMemory() * TO_DOUBLE / KB / KB;
    }


    /**
     * 获取Java版本
     * @return
     */
    public static String getJavaVers(){
        return JAVA_VERS;
    }

    /**
     * 获取操作系统架构
     * @return
     */
    public static String getOsArch(){
        return OS_ARCH;
    }

    /**
     * 获取操作系统名称
     * @return
     */
    public static String getOsName(){
        return OS_NAME;
    }

    /**
     * 获取操作系统版本
     * @return
     */
    public static String getOsVers(){
        return OS_VERS;
    }
}
