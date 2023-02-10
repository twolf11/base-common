package com.lcy.common.core.util;

/**
 * @Description 主键生成类--雪花算法
 * @Author lcy
 * @Date 2019/10/16 15:39
 */
public class IdWorker {

    /**
     * 这个就是代表了机器id
     */
    private final long machineId;

    /**
     * 这个就是代表了机房id
     */
    private final long machineRoomId;

    /**
     * 这个就是代表了一毫秒内生成的多个id的最新序号
     */
    private long sequence;

    /**
     * 机房号需要唯一的bits（二进制单位）数  2的3次方，最多支持8个机房
     */
    private final long machineRoomBits = 3L;

    /**
     * 机器号需要唯一的bits（二进制单位）数 2的7次方，最多支持128台机器
     */
    private final long machineBits = 7L;

    /**
     * 最后更新的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 单例对象
     */
    private volatile static IdWorker idWorker;

    /**
     * 获取IdWorker对象
     * @return com.lcy.application.util.IdWorker
     * @author lcy
     * @date 2019/10/21 10:15
     **/
    public static IdWorker getInstance(){
        if (idWorker == null) {
            synchronized (IdWorker.class) {
                if (idWorker == null) {
                    idWorker = new IdWorker(0,0,0);
                }
            }
        }
        return idWorker;
    }

    /**
     * 静态方法，生成id
     * @return long
     * @author lcy
     * @date 2019/10/17 10:55
     **/
    public static synchronized long getNextId(){
        return getInstance().nextId();
    }

    /**
     * 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
     * @return long
     * @author lcy
     * @date 2019/10/17 10:55
     **/
    public synchronized long nextId(){
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.",lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把sequence序号给递增1，最多就是4096
        // 顺序号需要位移的bits（二进制单位）数
        long sequenceBits = 12L;
        if (lastTimestamp == timestamp) {

            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            //这个是一个意思，就是12 bit最多只能有4095个数字
            long maxSequence = ~(-1L << 12);
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        //机房号需要左移的位数
        long machineRoomShift = sequenceBits + machineBits;
        //当前时间戳需要位移的位数
        long timestampLeftShift = sequenceBits + machineBits + machineRoomBits;
        //与当前时间对比需要的值--可以是随机值
        long initTime = 1288834974657L;
        return ((timestamp - initTime) << timestampLeftShift) |
                (machineRoomId << machineRoomShift) |
                (machineId << sequenceBits) | sequence;
    }

    /**
     * 如果最后更新时间大于当前的时间，以最后更新时间为准。
     * @param lastTimestamp 最后更新的时间
     * @return long
     * @author lcy
     * @date 2019/10/17 10:55
     **/
    private long tilNextMillis(long lastTimestamp){

        //获取当前时间
        long timestamp = timeGen();

        //判断当前时间是否大于最后的修改时间，如果小于或者等于，改成当前时间
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     * @return long
     * @author lcy
     * @date 2019/10/17 10:55
     **/
    private long timeGen(){
        return System.currentTimeMillis();
    }

    private IdWorker(long machineId,long machineRoomId,long sequence){
        // 检查了一下，传递进来的机房id和机器id不能超过32，不能小于0，即机房能最大值为2的5次方-1
        //这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机房id最多只能是32以内，
        long maxMachineRoom = ~(-1L << machineRoomBits);
        if (machineId > maxMachineRoom || machineId < 0) {

            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0",maxMachineRoom));
        }

        //这个是一个意思，就是5 bit最多只能有31个数字，机器id最多只能是32以内，即机房能最大值为2的5次方-1
        long maxMachine = ~(-1L << machineBits);
        if (machineRoomId > maxMachine || machineRoomId < 0) {

            throw new IllegalArgumentException(
                    String.format("dataCenter Id can't be greater than %d or less than 0",maxMachine));
        }
        this.machineId = machineId;
        this.machineRoomId = machineRoomId;
        this.sequence = sequence;
    }
}
