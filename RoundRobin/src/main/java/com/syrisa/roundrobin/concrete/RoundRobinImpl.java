package com.syrisa.roundrobin.concrete;

import com.syrisa.roundrobin.abstrct.RoundRobingAbs;

import java.util.Map;
import java.util.Set;

public class RoundRobinImpl extends RoundRobingAbs {
    @Override
    public void writeProcess(Map<String, Integer> map) {
        map.keySet().stream().filter(value -> !map.get(value).equals(0))
                .forEach(value -> System.out.print(value + " - " + map.get(value) + " "));
    }

    @Override
    protected void accAvgTime(String message, Map<String, Integer> map) {
        System.out.println(message + ((double) map.values().stream().reduce(0, getSum) / map.size()));
    }

    public void roundRobin() {
        System.out.println("\nRound Robin");
        Boolean isFlag = true;
        while (isFlag) {
            isFlag = startProcess();
        }
        System.out.println("Process Time ::: " + processTime);
        writeProcess(waitTime);
        System.out.println("\nTurn Around Time ::: ");
        writeProcess(turnAroundTime);
        accAvgTime("Avg Turn Around ::: ", turnAroundTime);
        System.out.println("Burst Time ::: ");
        fillAccAvgWaitingTime();
    }

    private void fillAccAvgWaitingTime() {
        if (waitTime.size() == turnAroundTime.size()) {
            for (String key : turnAroundTime.keySet()) {
                averageWaitingTime.put(key, (turnAroundTime.get(key) - waitTime.get(key)));
            }
        }

        writeProcess(averageWaitingTime);
        accAvgTime("Average Waiting Time  ::: ",averageWaitingTime);
    }

    private Boolean startProcess() {
        Set<String> keys = process.keySet();
        for (String key : keys) {
            Integer value = process.get(key);
            if (value == 0) continue;
            writeProcess(process);
            System.out.println();
            if (value >= TIME_QUADRANT) {
                value -= TIME_QUADRANT;
                process.put(key, value);
                accProcessTime(TIME_QUADRANT);
                accWaitTime(key, TIME_QUADRANT);
                time = TIME_QUADRANT;
            } else {
                process.put(key, 0);
                accProcessTime(value);
                accWaitTime(key, value);
                time = value;
            }
            timeCount += time;
            isStart = true;
            checkProcessKey(key);
        }
        return !keys.stream().allMatch(value -> process.get(value).equals(0));
    }

    private void checkProcessKey(String key) {
        if (process.get(key) == (0)) {
            if (turnAroundTime.containsKey(key)) {
                Integer t = turnAroundTime.get(key);
                t += timeCount;
                turnAroundTime.put(key, t);
            } else {
                turnAroundTime.put(key, timeCount);
            }
        }
    }

    private void accWaitTime(String key, Integer quadrant) {
        if (waitTime.containsKey(key)) {
            Integer time = waitTime.get(key);
            time += quadrant;
            waitTime.put(key, time);
            return;
        }
        waitTime.put(key, quadrant);
    }

    private void accProcessTime(Integer value) {
        if (Boolean.FALSE.equals(isStart)) return;
        processTime += value;
    }
}
