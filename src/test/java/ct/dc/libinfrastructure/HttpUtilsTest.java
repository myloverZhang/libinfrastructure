package ct.dc.libinfrastructure;

import ct.dc.libinfrastructure.common.UserInfo;
import org.apache.commons.digester.Digester;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by will on 17-3-17.
 */
public class HttpUtilsTest {
    HashMap<String,String> headers = new HashMap<String,String>(){
        {
            put("Accept","application/json");
            put("Content-Type","application/json;charset=UTF-8");
        }
    };
    @Test
    public void delete() throws Exception {
        String uri = "http://localhost:8080/api/test/delete?id=444";

        String result = HttpUtils.delete(uri,headers);
        System.out.println(result);
        assert result!=null;
    }

    @Test
    public void put() throws Exception {
        String uri = "http://localhost:8080/api/test/put";
        SchoolInfo info = new SchoolInfo("ASDF","FAWEF");
        String result = HttpUtils.put(uri,JsonUtils.jsonSerialize(info),headers);
        System.out.println(result);
        assert result!=null;
    }

    @Test
    public void post3() throws Exception {
        String uri = "http://bizsyssvc.ct108.org:2047/TcManageCenter.asmx";
        Map<String,String> headers = new HashMap(){
            {
                put("Content-Type","text/xml;charset=utf-8");
            }
        };
        String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Header>\n" +
                "    <CredenceSoapHeader xmlns=\"http://tempuri.org/\">\n" +
                "      <PlatformID>38</PlatformID>\n" +
                "      <Credential>2sdcxaa12</Credential>\n" +
                "    </CredenceSoapHeader>\n" +
                "  </soap:Header>\n" +
                "  <soap:Body>\n" +
                "    <GetCookieAdminUserInfo xmlns=\"http://tempuri.org/\">\n" +
                "      <cookieValue>210C04276DEB2430C0B603288991EE7268ED340B19650A55716C77FCE7665B408F97C7217742C4C0FC712CE3B5B2B2FEBFB338582755B66651E4D91FDB27033C8408F27342AE46941A3407D84D6D8D27D1A8D1165A647B4CEA3DD80AFC18F97DDBA8CC718C2C38402B782A93D2D336FDF937A130DFCB9C7EE721DF611C4352631F6B37517523F9E8D23265A85A22EA449CE9D1CC1DACD3B5D37263644F0FAA3CFA3B4B56C49709B8B1900DC2DF8E8C75C101E394EB0B4FB42D7EE894593A661C27CEC5BF78481DD6F27AFCF86AEC46AC8442ACBD7EFD5DC29B378AB5581535A6A6A650D7B0E2D23259A1D5B583D6058C501F416DD1627998E7C097F952E78311F4B9C7085FB2B1167EA0ED2EFECF48E668B4FF0C14E61A9723B56E027F87204712EC6F2D5A48EECF6E03BA62780C344F01976DD3F2ED67DC6B1FEE02905633D956E6FD1032403944435EA1E3324916E84F4233B6C965ACC52D388A2C160E6659992AF5F4AF05870C614586C1E6E905D7A595BB36B740A6635450006D6B67591B406EB3C4EFB8D2BB782FD5B66547B2BCF69A587E9774733E58417E11881578FE6FD804214D039B5A7464452285FDD630B5183C23B59031A819A3FD693BF2D1D6B6A16C35C41CF4AD8751E382BC29E62B20EFFECE449802B8D05E5CBE042EB01D7893453FE4256C3CDF82494AF5E27B1FC8A33228E797625F6E9E3F10622262868BC1805617AD4A171C41F0D2E4AFA7D2446AE9AFDFDD61D7EECF05F5AD5E778B12C17C86F774A3A455E3E8D4659A04425EDF850F3CFE287690F5276549014CD5268974EECBA5F7309802A9944CB13F36561D4D28929DCF4D7809309458C7A62383270FB9CEAFD784C24F8D68756BBD509B635E0DBF4C5D1528B81DFFD906594E36542FCF260C6F1B333775B668D594AE62FCC8B6195495558DD57B751305F200F14CFECFC9823413BAED001796B1F8884240B81059D2BF23FE6691514C1AFF218955F0156F99C16BD8F7E1160E849EFB467966E15D23B449CB6691876D5B40B5F90384745529CC2EA46F05B7ECCE3B574D2F1E39F99538D3FD84939EF9E5C812352040AF5FCCBB34AEBF807A66AD2E060143DE73D402677729521FD108C06AC8B935DD8E0FE3BAF7A490CE5A0BA512A03F07F301DA62D8083ED3A0E63269BC57CC65720EAFC0581F8A4986F17473FA55E3DF46BB170EADAB1C0EFDAB73F87AF3E4196DC4DF4D6AA33B354AA4B4DB43B65D4D045B2B17D1D61D4A5D8C9F68987429C0EC32E4748A327863699E8C750DA896079BAE07754F2B51E62A3117FDF5EF798228EA2A919D17BDB618AF2120AC8B385979C5B008C36C9A63E7C34EE63663249E1B74B3901DD4565241536190811CFB80839442972365A4C2D1372D76BE61F8E7F68B4679C5E209D7D1A00E575F1C8C5146EBAA8F5236DBAF0DBE9A676233E8325BE505A82E93E3E85DD64FFA14BFBE5D2769F773BE0404FFCF033830582E9870EDECAF70FEA3C962815EA68CC9150A0C0FF4BB17E93B8992125E015B9DA934CC6A82323720BD074F1DDA2A979DB7E10D54E62230BED0E3A4A9421D0EAA9737578E21B1FCB7FC7CD3FE580161A27190200D8EFC5F641028074208F0C365A7F08D647B329239C692B2DB66D82FF1E507CEAA57272903AF8C8F9643C11CCBF9C38A66C62C2E8CEA5AF8AE0546C49D91E2F2482A742E432952D1F0F43BB28CEA83ACD944FB8C626D1F4B62A8CD95D7E9FB6B8687899E3C8059B0D91A172EACF3CD98ED97E7393849D9A42F320D59D8FE484F8DD333FE02E60A5A0D69BDDE8D2C6245CD902B78B9349628D7A90D9259CA577E23DCF6111079D7B2984DE07B5F23199815F22D39329D29BE41A62046BF738B187C601E4BCBEE04F556ABDF8E9291D8EE12D12C86EB0309D76D7E758A1A0FB653116AEFCEA1223CE578E8EBD281B309D4CDECC8D95E034F898F8A5FC57171FF2F735419D7674B026D987C426C745D48BA10DA42D76197957CB0CAC45BDB3C24C53A19E7F198CF0D19763AF76164ECA4EBF253B2A598D839466F5630DBCB5AF802F8CBFDD5085C3D13B4458FD42345B0CEB0D2F0AE81D7DCD735997669CF9F292E1C30431724D54E35A2676BF6779DE2A379C46AFFDC8F2D30DDFC49DD215E2A6B96057A7D1F082128C9DE0590C75245839FB0C8799335D4206EFB6F27D2F91D01DEDCA1DACDBAC5DB1F8F35EDC1744C747D9D0DDDD4B757B84F314C7861FB781541BE0AF9318A8F3B0E592F147D01DBB448DB61E36AE3F42FB64ECA4D310F3E1C05E4FB9F23F804A435296780A4D06874A218F244EA53B768476E7E1CE50487AD91C03BD7668D1318F70E9C71BDE0F5A18F042E03D9CE0ECDE7E3083B54BB452044B4B604D3F52E17F5A3D4F7B2FED0AF63B00C0E750990D4B22D4495E3182FFBAECCF004023AB5863A7ED1CACD3254AF957682155BE27120100C6BD4814A93C606FE183079169AF83193826DA8840DE0C6DD53250A5E2D74EAEA740D15D63A7E158389A901591ED707E8432353F0B49C1A8E42E7B178915E5D587CA9977C852A0C35FF0BF198A1E9545EF082F5E35501AC407F97F57D96648A3702B59B898C769936B39CC40CAE8FFEB72DC92004FB060271C16B6837D94EA7BCDDB538B5B1E85D0CA7C4B6FFB21ED66BE62BB316FC9DDDE82675225D1EED6C1C1EB08EE0B7B586468EF8DBBFB4A3BB0DDAC5B300508FEFA1D080E807551975F529D452701765E1F4DFE02629B25651188F134CE9DB6FE72C8BAB656BE4A9EBFACEF4E723AAC3F4B7263D592D</cookieValue>\n" +
                "    </GetCookieAdminUserInfo>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        String result = HttpUtils.post(uri,content,headers);
        System.out.println(result);
        UserInfo userInfo = getUserInfo(result);
        System.out.println(userInfo.toString());
        assert result.length()>0;
    }

    @Test
    public void post1() throws Exception {
        String uri = "http://source.dc.uc108.org:8899/api/app/allgames";
        String result = HttpUtils.post(uri);
        System.out.println(result);
        assert result.length() > 0;
    }

    @Test
    public void post2() throws Exception {
          String uri = "http://192.168.101.39:8989/api/happyCoin/presentCost";
        Map<String,Object> params = new HashMap(){
            {
                put("beginDate", 20170101);
                put("endDate", 20170901);
                put("dealerId",130);
            }
        };
        String result = HttpUtils.post(uri, JsonUtils.jsonSerialize(params));
        System.out.println(result);
        assert result.length() > 0;
    }

    @Test
    public void post() throws Exception {
//        String uri = "http://192.168.101.39:8989/api/happyCoin/presentCost";
//        Map<String,Object> params = new HashMap(){
//            {
//                put("beginDate", 20170101);
//                put("endDate", 20170901);
//                put("dealerId",130);
//            }
//        };
//        Map<String,String> headers = new HashMap(){
//            {
//                put("Accept","application/json");
//                put("Content-Type","application/json");
//            }
//        };
//        String result = HttpUtils.post(uri, JsonUtils.jsonSerialize(params),headers);
//        System.out.println(result);
//        assert result.length() > 0;
//        String uri = "http://userapi.uc158.org:1505/api/dealer/allinfo";
//        Map<String,Object> params = new HashMap(){
//            {
//                put("AppId", "14807");
//                put("Time", "1506665626000");
//            }
//        };
//        Map<String,String> headers = new HashMap(){
//            {
//                put("Content-Type", "application/json");
//                put("Authorization","Basic sign=92C1F1ED27324E14E03D99564E881C98");
//            }
//        };
//        String result = HttpUtils.post(uri, params,headers);
//        System.out.println(result);
//        assert result.length() > 0;
    }

    @Test
    public void get2() throws Exception {
        String uri = "http://source.dc.uc108.org:8899/api/area/loc2address";
        HashMap<String,String> maps = new HashMap<String,String>(){
            {
                put("lat","39.2");
                put("lng", "120.32");
            }
        };
        HashMap<String,String> headers = new HashMap<String,String>(){
            {
                put("Accept-Encoding","gzip,deflate");
                put("Accept-Language","zh-CN,zh;q=0.8");
            }
        };
        String res = HttpUtils.get(uri,maps,headers);
        System.out.println(res);
        assert res.length()>0;
    }

    @Test
    public void get1() throws Exception {
        String uri = "http://source.dc.uc108.org:8899/api/area/loc2address";
        HashMap<String,String> maps = new HashMap<String,String>(){
            {
                put("lat","39.2");
                put("lng", "120.32");
            }
        };
        String res = HttpUtils.get(uri,maps);
        System.out.println(res);
        assert res.length()>0;
    }

    @Test
    public void get() throws Exception {
        String uri = "http://source.dc.uc108.org:8899/api/app/allgames";
        String result = HttpUtils.get(uri);
        System.out.println(result);
        assert result.length()> 0;
    }


    private UserInfo getUserInfo(String content){
        String baseStr = "soap:Envelope/soap:Body/GetCookieAdminUserInfoResponse/GetCookieAdminUserInfoResult";
        Digester digester = new Digester();
        digester.addObjectCreate(baseStr,"ct.dc.libinfrastructure.common.UserInfo");
        digester.addBeanPropertySetter(String.format("%s/InUseBeginTime",baseStr),"inUseBeginTime");
        digester.addBeanPropertySetter(String.format("%s/ID",baseStr),"id");
        digester.addBeanPropertySetter(String.format("%s/UserName",baseStr),"userName");
        digester.addBeanPropertySetter(String.format("%s/RoleID",baseStr),"roleId");
        digester.addBeanPropertySetter(String.format("%s/SiteID",baseStr),"siteId");
        digester.addBeanPropertySetter(String.format("%s/MacFlag",baseStr),"macFlag");
        digester.addBeanPropertySetter(String.format("%s/Status",baseStr),"status");
        digester.addBeanPropertySetter(String.format("%s/TrueName",baseStr),"trueName");
        digester.addBeanPropertySetter(String.format("%s/OverdueTime",baseStr), "overDueTime");
        try {
            return  (UserInfo)digester.parse(new ByteArrayInputStream(content.getBytes(ConstantResource.ENCODING_UTF8)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
}