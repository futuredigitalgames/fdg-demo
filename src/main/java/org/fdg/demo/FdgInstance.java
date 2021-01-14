package org.fdg.demo;

import com.github.rpc.RpcClient;

public class FdgInstance {

       public static RpcClient client=null;

       public static RpcClient getInstance(){
           if(client==null){
               client=new RpcClient("18.181.62.15",8545);

           }
           return client;
       }
}
