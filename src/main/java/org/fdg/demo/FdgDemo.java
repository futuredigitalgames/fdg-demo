package org.fdg.demo;

import com.github.fdg.FDGRawTransaction;
import com.github.fdg.Numeric;
import com.github.model.BlockResult;
import com.github.rpc.RpcClient;
import com.github.model.TransactionRecepit;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import sun.security.pkcs11.wrapper.Constants;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;

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

    /**
     * 代币交易
     */
    public void sendTrade(String privateKey,String from ,String contractAddress,String to,BigInteger value){
         Credentials credentials = Credentials.create(privateKey);
         //获取nonce，交易笔数
         BigInteger nonce = getTradeCount(from);
         //get gasPrice
        BigInteger gasPrice= Convert.toWei("45", Convert.Unit.GWEI).toBigInteger();


         //创建RawTransaction交易对象
         Function function = tranFunction(to, value);

         String encodedFunction = FunctionEncoder.encode(function);


         RawTransaction rawTransaction = FDGRawTransaction.createTransaction(nonce, gasPrice, new BigInteger("60000"), to, encodedFunction);

         //签名Transaction，这里要对交易做签名
         byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);

         String hexValue = Numeric.toHexString(signMessage);
         //发送交易
        RpcClient client= FdgInstance.getInstance();
        String tradeHash=client.sendRawTransaction(hexValue);
        System.out.println(tradeHash);



     }
    private Function tranFunction(String to, BigInteger value) {
        Function function = null;
        try {
             function = new Function(
                    "",
                    Arrays.asList(new Address(to), new Uint256(value)),
                    Collections.singletonList(new TypeReference<Type>() {
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return function;

    }

    private static final String DATA_PREFIX = "0x70a08231000000000000000000000000";

    /**
     * 获取合约地址的余额
     * @param address
     * @param contractAddress
     * @return
     * @throws Exception
     */
    public static BigDecimal getBalance(String address, String contractAddress)  {
        System.out.println(address.substring(2));
        Transaction transaction =FDGRawTransaction.createEthCallTransaction(address, contractAddress, DATA_PREFIX + address.substring(2) );

        RpcClient client= FdgInstance.getInstance();
        BigDecimal value = client.getBalance(transaction, DefaultBlockParameterName.PENDING);

        System.out.println(value);

        return value;
    }


    public static void main(String[] args) {
        FdgDemo demo=new FdgDemo();
        demo.getBalance("Gsabff5fb38e4ed2da3009c6b97a368ea2fb58f3c5","Gsdac17f958d2ee523a2206206994597c13d831ec7");
    }





}
