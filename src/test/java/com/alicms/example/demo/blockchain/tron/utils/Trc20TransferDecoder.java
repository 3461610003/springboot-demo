package com.alicms.example.demo.blockchain.tron.utils;

import java.math.BigInteger;

/**
 * @author: sunlight
 * @date: 2020/9/17 18:25
 */
public class Trc20TransferDecoder implements Trc20MessageDecoder {
    private String data;

    private Trc20TransferDecoder() {
    }

    public Trc20TransferDecoder(String data) {
        this.data = data;
    }

    @Override
    public TransferMessage decode() {
//        data=data.substring(32);
//        String toAddress= AddressHelper.addressHexToBase58("41"+data.substring(0,40));
//        String hexValue=data.substring(52);
//        BigInteger value = new BigInteger(hexValue,16);
//        return new TransferMessage(toAddress,value);
        return null;
    }
}
