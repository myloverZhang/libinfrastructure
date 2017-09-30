/**
 * @author:      wangzs
 * @createDate:  2017/03/16
 */

package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.exception.ConfigNotFoundException;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 配置相关工具库
 */
public class ConfigUtils {
    private final static String CUSTOM_PROPERTIES = "customProperties";
    private String configFileName;
    private boolean resourceConfig;

    /**
     * 配置类型枚举
     */
    public enum ConfigTypeEnums {
        /**
         *资源文件
         */
        RESOURCE(true),
        /**
         *自定义配置文件
         */
        CUSTOM(false);

        private final boolean value;

        /**
         * 配置类型枚举
         */
        ConfigTypeEnums(boolean value) {
            this.value = value;
        }

        /**
         * 获取枚举值
         * @return
         */
        public boolean getValue() {
            return this.value;
        }
    }

    /**
     * 初始化配置文件
     * @param configFileName
     */
    public ConfigUtils(String configFileName){
        this.configFileName = configFileName;
        initConfigStatus();
    }

    /**
     * 初始化配置文件
     * @param configFileName
     * @param isResourceConfig
     */
    public ConfigUtils(String configFileName, ConfigTypeEnums configTypeEnums){
        this.configFileName = configFileName;
        this.resourceConfig = configTypeEnums.getValue();
    }

    /**
     * 设置配置状态
     */
    private void initConfigStatus(){
        ConfigUtils utils = new ConfigUtils(CUSTOM_PROPERTIES, ConfigTypeEnums.RESOURCE);
        String value = utils.getConfig(this.configFileName, null);
        if(!StringUtils.isNullOrWhiteSpace(value)){
            String osName = RuntimeSystemUtils.getOsName().toLowerCase();
            if(osName.indexOf("windows") >= 0) {
                this.configFileName = String.format("%s\\%s",RuntimeSystemUtils.getProjectRootPath(), value);
            }else if(osName.indexOf("linux") >= 0){
                this.configFileName = String.format("%s/%s",RuntimeSystemUtils.getProjectRootPath(), value);
            }else{
                this.configFileName = value;
            }
            this.resourceConfig = false;
        }else{
            this.resourceConfig = true;
        }
    }


    /**
     * 通过配置key获取配置信息
     * @param key
     * @param default_value
     * @return
     */
    public String getConfig(String key, String default_value) {
        String value = default_value;
        if(this.resourceConfig) {
            value = getStringFromResource(key);
        }else{
            value = getStringFromProperties(key);
        }
        if(StringUtils.isNullOrWhiteSpace(value)){
            value = default_value;
        }
        return value;
    }
    /**
     * 通过配置key获取配置信息
     * @param key
     * @return
     */
    public String getConfig(String key) throws ConfigNotFoundException {
        String value;
        if(this.resourceConfig) {
            value = getStringFromResource(key);
        }else{
            value = getStringFromProperties(key);
        }
        if(StringUtils.isNullOrWhiteSpace(value)){
            throw new ConfigNotFoundException(String.format("key:%d 配置属性未找到",key));
        }
        return value;
    }
    /**
     * 从资源配置文件中获取配置值
     * @param key
     * @return
     */
    private String getStringFromResource(String key) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(this.configFileName);
            if(rb.containsKey(key)) {
                return rb.getString(key);
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从自定义配置文件中获取配置信息
     * @param key
     * @return
     */
    private String getStringFromProperties(String key){
        Properties pro = new Properties();
        try {
            pro.load(new FileInputStream(this.configFileName));
            return pro.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
