package com.alicms.example.demo.blockchain.tron.utils;

/**
 * @author: sunlight
 * @date: 2020/9/17 17:36
 */
public class Trc20DataDecoder {

    public static TransferMessage decode(String data) throws Exception {
        Trc20MessageDecoder decoder= matchTrc20MessageDecoder(data);
        if (decoder==null){return null;}
        return decoder.decode();
    }

    private static Trc20MessageDecoder matchTrc20MessageDecoder(String data) throws Exception {
        String methodId=data.substring(0,8);
        if (methodId.equals(encodeMethod(Trc20Method.TRANSFER))){
            return new Trc20TransferDecoder(data);
        }else if(methodId.equals(encodeMethod(Trc20Method.TRANSFER_FROM))){
            return new Trc20TransferFromDecoder(data);
        }else {
            return null;
        }
    }

    private static String encodeMethod(Trc20Method trc20Method){
//        return ByteArray.toHexString(Hash.sha3(trc20Method.getMethod().getBytes())).substring(0,8);
        return null;
    }

    public static void main(String[] args) {
        System.out.println(encodeMethod(Trc20Method.TRANSFER));
    }
}
