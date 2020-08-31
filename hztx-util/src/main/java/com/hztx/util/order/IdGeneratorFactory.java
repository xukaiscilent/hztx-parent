package com.hztx.util.order;






import com.hztx.util.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ID生成器工厂，通过create(String type)方法生成Generator。type参数为使用者定义的业务类型，需要使用者自己维护。
 * 生成的单个Generator对象都是线程安全的，针对每一种不同的需求可分别生成一个Generator。
 * 注意Generator的实例与实例之间生成的ID是无法保证唯一的。某一种需求需使用同一个Generator事例。
 * 比如对于订单表，所有获取订单号的方法必须使用同一个Generator；而退单表则最好创建另外一个Generator实例来保证并发性。
 *
 * @author InThEnd
 * @see #create(String type)
 */
public class IdGeneratorFactory {

    private static final String PROPERTY_TABLE = "provider.tableName";
    private static final String PROPERTY_DRIVER_NAME = "provider.driverName";
    private static final String PROPERTY_URL = "provider.url";
    private static final String PROPERTY_USERNAME = "provider.username";
    private static final String PROPERTY_PASSWORD = "provider.password";

    private static final String COLUMN_MAC = "mac_address";

   // private static final ClasspathPropertiesHelper helper = new ClasspathPropertiesHelper("id-generator.properties");

    private static int serverId=1;

    private static final Map<String, Generator> generatorMap = new ConcurrentHashMap<>();

    /**
     * 服务器ID部分长度。
     */
    public enum SPartSize {

        ONE(1), TWO(2), THREE(3);

        private int digit;

        SPartSize(int digit) {
            this.digit = digit;
        }

        public int getDigit() {
            return digit;
        }
    }

    /**
     * 流水号部分长度。
     */
    public enum NPartSize {

        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);

        private int digit;

        NPartSize(int digit) {
            this.digit = digit;
        }

        public int getDigit() {
            return digit;
        }
    }

    static {
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private IdGeneratorFactory() {
    }


    public static synchronized Generator create(String type) {
        return create(type, SPartSize.TWO, NPartSize.THREE);
    }

    public static synchronized Generator create(String type, SPartSize sps, NPartSize nps) {
        Generator generator = generatorMap.get(type);
        if (generator == null) {
            generator = new InternalGenerator(serverId, sps.getDigit(), nps.getDigit());
            generatorMap.put(type, generator);
        }
        return generator;
    }


    /**
     * 此ID生成器生成的ID由4部分组成，日期（6位），服务器ID(n位)，秒数（5位），序列号（m位）。
     * 总位数不能大于19位（java long类型上限）。
     */
    private static class InternalGenerator implements Generator {

        private static final DateFormat format = new SimpleDateFormat("yyMMdd");

        private int serverId;

        private int secondOffSet = (TimeZone.getDefault().getRawOffset()) / 1000;

        /**
         * @param serverId 当前服务器唯一标识Id(由外部数据库实现)。
         * @param s0       服务器Id部分长度。此长度意味着最大支持的服务器数量(10的s0次方-1)。
         * @param n0       序列号部分长度，此长度意味着每秒钟最多能够生成的ID数量(10的n0次方-1)。如果超过这个数量将顺延到下一秒。
         */
        InternalGenerator(int serverId, int s0, int n0) {
            Assert.isTrue(s0 > 0 && s0 <= 2, "生成器服务器ID部分占用的长度必须为1或者2.");
            Assert.isTrue(n0 > 0, "生成器序列号部分占用的长度必须大于0.");
            Assert.isTrue(s0 + n0 < 8, "服务器部分的长度和序列号部分的长度之和必须小于8.");
            Assert.isTrue(serverId <= tenX(s0) - 1, "服务器Id必须小于(10的n0次方-1)。");
            this.serverId = serverId;
            this.s0 = s0;
            this.n0 = n0;
            dateX = tenX(s0 + 5 + n0);
            serverIdX = tenX(5 + n0);
            secondX = tenX(n0);
        }

        private long lastTime = System.currentTimeMillis();

        private int seqNumber = 0;

        private int s0;

        private int n0;

        private long dateX;

        private long serverIdX;

        private long secondX;

        public long generate() {
            long[] longs = compute();
            long time = longs[0];
            long number = longs[1];
            long date = Long.parseLong(format.format(new Date(time)));
            long second = ((time / 1000) + secondOffSet) % (3600 * 24);
            return date * dateX + serverId * serverIdX + second * secondX + number;
        }

        private synchronized long[] compute() {
            long current = System.currentTimeMillis();
            if (inOneSecond(lastTime, current)) {
                seqNumber++;
                if (seqNumber >= tenX(n0) - 1) {
                    try {
                        Thread.sleep(1001 - current % 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                seqNumber = 0;
            }
            lastTime = current;
            return new long[]{current, seqNumber};
        }


        private boolean inOneSecond(long time1, long time2) {
            return time1 / 1000 == time2 / 1000;
        }

        private long tenX(int size) {
            long result = 1;
            for (int i = 0; i < size; i++) {
                result *= 10;
            }
            return result;
        }

    }


    /**
     * 初始化获取serverId，如果是单机应用，serverId可以指定为常量值。
     * @throws Exception
     */
    private static void init() throws Exception {
        //获取mac地址
        String mac = "12345678";

    }

    public static void main(String[] args) {
          Generator generator = IdGeneratorFactory.create("aliOrder");
          long code=generator.generate();
        System.out.println("code:"+code);
    }

}
