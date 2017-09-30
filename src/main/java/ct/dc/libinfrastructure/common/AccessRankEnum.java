package ct.dc.libinfrastructure.common;

/**
 * Created by CTWLPC on 2017/9/21.
 */
public enum AccessRankEnum {
    /**
     * 表示所有的访问权限
     */
    ALL(0),
    /**
     * 表示共有的访问权限
     */
    PUBLIC(1),
    /**
     * 表示受保护的访问权限
     */
    PROTECTED(2),
    /**
     * 表示私有的访问权限
     */
    PRIVATE(3);
    private final int value;

    AccessRankEnum(int value) {
        this.value = value;
    }

    public static AccessRankEnum valueOf(int num){
        switch (num){
            case 0:
                return ALL;
            case 1:
                return PUBLIC;
            case 2:
                return PROTECTED;
            case 3:
                return PRIVATE;
        }
        return ALL;
    }
}
