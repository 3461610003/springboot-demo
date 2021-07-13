package com.alicms.example.demo.blockchain.tron;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alicms.example.demo.blockchain.tron.utils.TransferMessage;
import com.alicms.example.demo.blockchain.tron.utils.Trc20DataDecoder;
import com.alicms.example.demo.utils.HttpClientUtil;


public class DemoTest {
    public static void main(String[] args) throws Exception {
//        String transferData = "a9059cbb000000000000000000000000" +
//                "415f43a4af3e2cac3f91c9d7d9f813e5f5a90769" +
//                "00000000000000000000000000000000000000000000000000000000772651c0";
//        TransferMessage bo= Trc20DataDecoder.decode(transferData);
//        System.out.println("transfer:\n"+ JSON.toJSONString(bo));
//
//        String transferFromData =
//                "23b872dd000000000000000000000000" +
//                        "bc106858d5597e8f860ab259199a3ebd3b541da7000000000000" +
//                        "295883812dd775615947718e88a8e6c7a8717cff000000000000" +
//                        "00000000000000000000000000000000000000000000000f4240";
//        bo = Trc20DataDecoder.decode(transferFromData);
//        Assert.assertEquals(bo.getTo(),"TDjpjxouXXUkTvLjaTAB6e2UdUFVt9PBsK");
//        assert bo.getValue().equals(BigInteger.valueOf( 1000000L));
//        System.out.println("transferFrom:\n"+JSON.toJSONString(bo));
//
//    }

//        String result = HttpClientUtil.doPostJson("https://api.trongrid.io/wallet/getblockbylimitnext", "{\"startNum\":31691438,\"endNum\":31691439}");
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        JSONArray jsonArray = jsonObject.getJSONArray("block").getJSONObject(0).getJSONArray("transactions");
//        for (Object tran : jsonArray) {
//            triggerSmartContract((JSONObject) tran);
//        }
//        System.out.println(result);
        for (int i = 31690995; i < 31691995; i+=5) {
            String result = HttpClientUtil.doPostJson("https://api.trongrid.io/wallet/getblockbylimitnext", "{\"startNum\":" + i + ",\"endNum\":" + (i + 5) + "}");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("block").getJSONObject(0).getJSONArray("transactions");
            for (Object tran : jsonArray) {
                triggerSmartContract((JSONObject) tran);
            }
//            System.out.println(result);
//            Thread.sleep(1000L * 10);
        }
    }

    private static void triggerSmartContract(JSONObject parseObject) throws Exception {
        if (!"SUCCESS".equals(parseObject.getJSONArray("ret").getJSONObject(0).getString("contractRet"))) {
            return;
        }

        if (!"TriggerSmartContract".equals(parseObject.getJSONObject("raw_data").getJSONArray("contract").getJSONObject(0).getString("type"))) {
//            System.out.println(parseObject.getJSONObject("raw_data").getJSONArray("contract").getJSONObject(0).getString("type"));
            return;
        }
        JSONObject rowDataContractParameterValue = parseObject.getJSONObject("raw_data").getJSONArray("contract").getJSONObject(0).getJSONObject("parameter").getJSONObject("value");
        //方法参数
        String data = rowDataContractParameterValue.getString("data");
        if (data.length() <= 32) {
            return;
        }

//        // 调用者地址
//        String owner_address = rowDataContractParameterValue.getString("owner_address");
//        owner_address = WalletApi.encode58Check(ByteArray.fromHexString(owner_address));
        // 合约地址
//        String contract_address = rowDataContractParameterValue.getString("contract_address");
//        contract_address = WalletApi.encode58Check(ByteArray.fromHexString(contract_address));
//        if (!"TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t".equals(contract_address)) {
//            return;
//        }

//        String dataStr = data.substring(8);
//        List<String> strList = TransformUtil.getStrList(dataStr, 64);
//        if (strList.size() != 2) { return; }
//        String to_address = TransformUtil.delZeroForNum(strList.get(0));
//        if (!to_address.startsWith("41")) {
//            to_address = "41" + to_address;
//        }
//        to_address = WalletApi.encode58Check(ByteArray.fromHexString(to_address));
//        if (!to_address.equals("TYwq8TdJcHMjNusNQz88Teg2wcUTUqQFeK")) {
//            return;
//        }
//        String amountStr = TransformUtil.delZeroForNum(strList.get(1));
//        if (amountStr.length() > 0) {
//            amountStr = new BigInteger(amountStr, 16).toString(10);
//        }
        TransferMessage bo = Trc20DataDecoder.decode(data);
//        System.out.println("contract_address=" + contract_address);
//        System.out.println("fromAddress=" + owner_address + ", toAddress=" + bo.getTo() + ", amount=" + bo.getValue());
        if (bo == null) {
            System.out.print("txID=" + parseObject.getString("txID"));
//            System.out.println(", data=" + data + ", contract_address=" + contract_address);
            return;
        }
        if (bo.getTo() == null || !bo.getTo().startsWith("T") || bo.getTo().length() != 34) {
            System.out.println("toAddress=" + bo.getTo() + ", amount=" + bo.getValue());
        }
    }
}
