package com.noodle.leetcode.okhttp;


import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.local.Resolver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Dns;
import okhttp3.internal.connection.RealConnectionPool;

/**
 * @author heshufan
 * @date 2021-01-15
 */
public class HttpDns implements Dns {

    private DnsManager dnsManager;
    RealConnectionPool

    public HttpDns() {
        IResolver[] resolvers = new IResolver[1];
        try {
            resolvers[0] = new Resolver(InetAddress.getByName("119.29.29.29"));
            dnsManager = new DnsManager(NetworkInfo.normal, resolvers);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        System.out.println(hostname);
        if (dnsManager == null) {
            //默认的解析方式
            FutureTask<List<InetAddress>> task = new FutureTask<>(
                    new Callable<List<InetAddress>>() {
                        @Override
                        public List<InetAddress> call() throws Exception {
                            return Arrays.asList(InetAddress.getAllByName(hostname));
                        }
                    });
            new Thread(task).start();
            try {
                return task.get(1000, TimeUnit.MILLISECONDS);

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return Dns.SYSTEM.lookup(hostname);
        }
        try {
            //七牛云解析出来的ips地址
            long t1 = System.nanoTime();
            String[] ips = dnsManager.query(hostname);
            long t2 = System.nanoTime();
            System.out.println((t2 - t1) / 1e6d +"ms");
            if (ips == null || ips.length == 0) {
                return Dns.SYSTEM.lookup(hostname);
            }

            List<InetAddress> result = new ArrayList<>();
            for (String ip : ips) {
                //将ip地址转化为所需要的对象列表
                System.out.println(ip);
                result.addAll(Arrays.asList(InetAddress.getAllByName(ip)));
            }
            for (InetAddress inetAddress : result) {
                System.out.println("heshufan"+"inetAddress is" + inetAddress.getHostAddress());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Dns.SYSTEM.lookup(hostname);
    }
}
