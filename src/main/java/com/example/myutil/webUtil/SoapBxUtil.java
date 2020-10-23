package com.example.myutil.webUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * WebServiceUtil
 */
public class SoapBxUtil {

    private static Logger log = LoggerFactory.getLogger(SoapBxUtil.class);

    private static Map<String,String> dataTagMap = new HashMap<>();
    private static Map<String,String> responseStrMap = new HashMap<>();
    private static Map<String,String> resultStrMap = new HashMap<>();
    static {
        // 实际出库
        dataTagMap.put("UploadStockOut","AData");
        responseStrMap.put("UploadStockOut","UploadStockOutResponse");
        resultStrMap.put("UploadStockOut","UploadStockOutResult");
        // 实际入库
        dataTagMap.put("UploadStockIn","AData");
        responseStrMap.put("UploadStockIn","UploadStockInResponse");
        resultStrMap.put("UploadStockIn","UploadStockInResult");
        // 实际入库撤销
        dataTagMap.put("UploadRollbackStockIn","AData");
        responseStrMap.put("UploadRollbackStockIn","UploadRollbackStockInResponse");
        resultStrMap.put("UploadRollbackStockIn","UploadRollbackStockInResult");
    }


    public static SOAPMessage formatSoapMessage(String soapString){
        MessageFactory factory;
        try {
            factory = MessageFactory.newInstance();
            SOAPMessage reqMsg = factory.createMessage(new MimeHeaders(),new ByteArrayInputStream(soapString.getBytes("UTF-8")));
            reqMsg.saveChanges();
            return reqMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SOAPMessage formatSoapMessage(byte [] bytes){
        MessageFactory factory;
        try {
            factory = MessageFactory.newInstance();
            SOAPMessage reqMsg = factory.createMessage(new MimeHeaders(),new ByteArrayInputStream(bytes));
            reqMsg.saveChanges();
            return reqMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 往XML注入参数
     * @param json
     * @return
     */
    public static String getXmlTemplay(String json,String methodName){
        String dataTag = dataTagMap.get(methodName);
        //拼接好xml
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        sb.append("<soap:Body>");
        sb.append("<").append(methodName).append("  xmlns=\"http://tempuri.org/\">");
        sb.append("<").append(dataTag).append(">");
        sb.append(json);
        sb.append("</").append(dataTag).append(">");
        sb.append("</").append(methodName).append(" >");
        sb.append("</soap:Body>");
        sb.append("</soap:Envelope>");
        return sb.toString();
    }

    /**
     * 获取XML返回结果
     * @param iterator
     * @param targetName 标签名
     * @return
     */
    public static String printBody(Iterator<SOAPElement> iterator, String targetName,String methodName) {
        String str = "";
        String responseStr = responseStrMap.get(methodName);
        while (iterator.hasNext()) {
            SOAPElement element = (SOAPElement) iterator.next();
            if(element.getTagName().equals(responseStr) && element.getChildElements().hasNext()){
                while (element.getChildElements().hasNext()){
                    element = (SOAPElement)element.getChildElements().next();
                    if(element.getTagName().equals(targetName)){
                        str = element.getValue();
                        break;
                    }
                }
                break;
            }else {
                continue;
            }
        }
        return str;
    }

    public static String callBack(String host, String body, String methodName){
        String xml = getXmlTemplay(body,methodName);
        log.info("请求xml："+xml);
        String responsMess = "";
        try {
            //地址
            URL url = new URL(host);
            //打开链接
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            //设置好header信息
            con.setRequestMethod("POST");
            con.setRequestProperty("content-type", "text/xml; charset=utf-8");
            con.setRequestProperty("Content-Length", String.valueOf(xml.getBytes().length));
            //post请求需要设置
            con.setDoOutput(true);
            con.setDoInput(true);
            //对请求body 往里写xml 设置请求参数
            OutputStream ops = con.getOutputStream();
            ops.write(xml.getBytes());
            ops.flush();
            ops.close();
            //设置响应回来的信息
            InputStream ips = con.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int length = 0;
            while( (length = ips.read(buf)) != -1){
                baos.write(buf, 0, length);
                baos.flush();
            }
            byte[] responsData = baos.toByteArray();
            baos.close();
            //处理写响应信息
            responsMess = new String(responsData,"utf-8");
            log.info(responsMess);
            log.info("callback status:"+con.getResponseCode());
        } catch (Exception e) {
            log.info("回调失败");
            e.printStackTrace();
        }
        return changeXMLResult(responsMess,methodName);
    }

    public static String changeXMLResult(String xml,String methodName){
        SOAPMessage msg = formatSoapMessage(xml);
        SOAPBody soapBody = null;
        String body = "";
        try {
            soapBody = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = soapBody.getChildElements();
            String resultStr = resultStrMap.get(methodName);
            body = printBody(iterator,resultStr,methodName);
//            List<StInfoSendReqData> list = JSON.parseArray(body,StInfoSendReqData.class);
//            Map<String,String> data = new HashMap<>();
//            for(StInfoSendReqData req :list){
//                data.put(req.getCoilId(),req.getResult());
//            }
//            StCallBackReqData reqData = new StCallBackReqData();
//            reqData.setData(data);
//            body = JSON.toJSONString(reqData);
            log.info("回传json："+body);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return body;
    }

    public static void main(String[] args) {
        // 示例数据
//       String s = "[{\"voucherNum\":\"C00000001\",\"putoutId\":\"PP000000001\",\"putoutStatus\":\"00\",\"putoutType\":\"10\",\"wproviderId\":\"baoxing\",\"putoutDate\":\"2020-07-20\",\"clientId\":\"11111\",\"putoutHandler\":\"张三\",\"providerPutoutId\":\"sdafsd13465485645\",\"storeType\":\"原料\",\"businessType\":\"贸易\",\"putoutStyle\":\"入库类别\",\"batchId\":\"13254856\",\"createDate\":\"2020-07-20\",\"confirmDate\":\"2020-07-20\",\"workingShift\":\"1\",\"tproviderId\":\"123\",\"conveyanceId\":\"bbb\",\"shipName\":\"和谐号\",\"driverName\":\"S\",\"driverCertNo\":\"123456789\",\"custId\":\"honda\",\"custName\":\"本田贸易\",\"dataList\":[{\"putoutId\":\"PP000000001\",\"putoutSubid\":\"01\",\"productId\":\"jfhasdkgfadu1568\",\"factoryProductId\":\"156841\",\"packId\":\"Pack1\",\"productTypeId\":\"tl1\",\"location\":\"1\",\"putoutWeight\":\"1\",\"weightUnit\":\"吨\",\"putoutQty\":\"1\",\"qtyUnit\":\"\",\"unitWeight\":\"\",\"grossWeight\":\"2\",\"techStandard\":\"IOS-021\",\"shopsign\":\"ssd\",\"qualityGrade\":\"1\",\"spec\":\"1*2*3\",\"voucherSubnum\":\"GG000000001\"}]}]";
        // 自己的业务数据
        String s = "[{" +
                "\"putinId\":\"CT19072900001\"," +
                "\"putinStatus\":\"00\"," +
                "\"dataList\":[{" +
                "\"putinId\":\"CT19072900001\"," +
                "\"putinSubid\":\"01\"," +
                "\"status\":\"00\"" +
                "}," +
                "{" +
                "\"putinId\":\"CT19072900001\"," +
                "\"putinSubid\":\"02\"," +
                "\"status\":\"00\"" +
                "}" +
                "]" +
                "}]";


        String result = SoapBxUtil.callBack("http://39.108.108.157:90/AJBX_Svr/WebSvr.asmx",s,"UploadRollbackStockIn");

        System.out.println(result);
    }
}
