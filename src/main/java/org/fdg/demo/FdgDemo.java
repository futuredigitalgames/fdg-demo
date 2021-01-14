package org.fdg.demo;

import com.github.rpc.RpcClient;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FdgDemo {

    /**
     * 获取资金
     */
    public void getBalance(){
        RpcClient client= FdgInstance.getInstance();
        BigDecimal amount=client.getBalance("0x1e4416ff61a7899225a1e025398d3a63a9352110");
        System.out.println(amount);
    }

    /***
     * 获取当前区块
     */
    public void getBlock(){
        RpcClient client= FdgInstance.getInstance();
        BigInteger integer=client.getCurrentBlock();
        System.out.println(integer);
    }


    /***
     * 获取地址的交易笔数
     */
    public void getTradeCount(){
        RpcClient client= FdgInstance.getInstance();
        BigInteger integer=client.getTradeCount("Gs950d02552fCce39B6eF23Bc15081DA6F0ab00132");
        System.out.println(integer);
    }


    /**
     * eth发送交易
     */
    public void sendTrade(){
        RpcClient client= FdgInstance.getInstance();
        String tradeHash=client.sendRawTransaction("Gs950d02552fCce39B6eF23Bc15081DA6F0ab00132");
        System.out.println(tradeHash);
    }

    /**
     *  获取地址交易笔数
     */
    public void syncTrade(){
        RpcClient client= FdgInstance.getInstance();
        BigInteger count=client.getTradeCount("Gs950d02552fCce39B6eF23Bc15081DA6F0ab00132");
        System.out.println(count);
    }

    public static void main(String[] args) {
        FdgDemo demo=new FdgDemo();
        demo.getBlock();
    }



}
