package org.fdg.demo;

import com.github.fdg.FDGRawTransaction;
import com.github.fdg.Numeric;
import com.github.model.BlockResult;
import com.github.rpc.RpcClient;
import com.github.model.TransactionRecepit;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.utils.Convert;

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
    public BigInteger getTradeCount(String from ){
        RpcClient client= FdgInstance.getInstance();
        BigInteger integer=client.getTradeCount(from);
        System.out.println(integer);
        return integer;
    }


    /**
     *
     * @param privateKey  用户私钥
     * @param to    发送地址
     */
    public void sendTrade(String privateKey,String to){
        RpcClient client= FdgInstance.getInstance();
        Credentials credentials = Credentials.create(privateKey);
        BigInteger nonce = getTradeCount("from");
        BigInteger gasPrice= Convert.toWei("45", Convert.Unit.GWEI).toBigInteger();
        RawTransaction rawTransaction = FDGRawTransaction.createEtherTransaction(nonce, gasPrice, new BigInteger("60000"), to, null);
        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signMessage);
        String tradeHash=client.sendRawTransaction(hexValue);
        System.out.println(tradeHash);
    }

    /**
     *  根据hash获取交易信息
     */
    public void getTradeByTxHash(){
        RpcClient client= FdgInstance.getInstance();
        TransactionRecepit receipt =client.getTransactionReceipt("xxx"); //写入交易hash
        System.out.println(receipt);
    }

    /**
     * 获取指定区块
     * @param start
     */
     public void getBlockInfoByNumber(Long start){
         RpcClient client= FdgInstance.getInstance();
         BlockResult blockResult= client.getBlock(start,false);
     }



    public static void main(String[] args) {
        FdgDemo demo=new FdgDemo();
        demo.getBlock();
    }



}
