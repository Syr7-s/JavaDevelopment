package com.syrisa.roundrobin.abstrct;

import com.syrisa.roundrobin.abstrct.RoundRobin;

import java.util.Map;
import java.util.function.BinaryOperator;

public abstract class RoundRobingAbs implements RoundRobin {

    protected static final Integer TIME_QUADRANT = 5;
    protected static Integer time = 0;
    protected static Integer timeCount = 0;
    protected static Boolean isStart = false;
    protected static Integer processTime = 0;

    public abstract void writeProcess(Map<String, Integer> map);

    protected abstract void accAvgTime(String message,Map<String,Integer> map);

    static {
        process.put("P1", 6);
        process.put("P2", 3);
        process.put("P3", 1);
        process.put("P4", 7);
    }

    public static BinaryOperator<Integer> getSum = (acc, x) -> acc + x;


}
