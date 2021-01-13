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
        BigDecimal amount=client.getBalance("Gs950d02552fCce39B6eF23Bc15081DA6F0ab00132");
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

    public void sendTrade(){
        RpcClient client= FdgInstance.getInstance();
        String tradeHash=client.sendRawTransaction("Gs950d02552fCce39B6eF23Bc15081DA6F0ab00132");
        System.out.println(tradeHash);
    }

    public static void main(String[] args) {
        FdgDemo demo=new FdgDemo();
        demo.sendTrade();
    }



}
