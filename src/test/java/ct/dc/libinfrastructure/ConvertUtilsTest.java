package ct.dc.libinfrastructure;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

class RestApiResult<T>{
    private Boolean status;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;
    private String code;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

class Loc2AreaResult extends RestApiResult<AddressDetailInfo>{}

class AddressDetailInfo implements Serializable {
    private String city;
    private String country;
    private String district;
    private String province;
    private String township;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }
}

/**
 * Created by will on 17-3-21.
 */
public class ConvertUtilsTest {
    @Test
    public void jsonDeserialize() throws Exception {
        String jsonStr = "{\n" +
                "\n" +
                "    \"code\": 200,\n" +
                "    \"status\": true,\n" +
                "    \"message\": null,\n" +
                "    \"data\": {\n" +
                "        \"city\": \"未知\",\n" +
                "        \"country\": \"中国\",\n" +
                "        \"district\": \"未知\",\n" +
                "        \"province\": \"未知\",\n" +
                "        \"township\": \"未知\"\n" +
                "    }\n" +
                "\n" +
                "}";
        Loc2AreaResult result = ConvertUtils.jsonDeserialize(jsonStr, Loc2AreaResult.class);
        AddressDetailInfo detail = result.getData();
        Assert.assertTrue(detail != null);
    }


    @Test
    public void bytes2hexstr() throws Exception {
        final byte[] bytes = new byte[]{
                -12, 35, -25, 65, 45, -87, 95, -22, -15, 45, 55, -66, 32, 5 - 4, 84, 55
        };
        String hexstr = ConvertUtils.bytes2hexstr(bytes);
        String str = ConvertUtils.bytes2str(bytes);

        System.out.println("hexstr = " + hexstr);
        System.out.println("str = " + str);
    }
}