package com.syrisa.roundrobin.abstrct;

import java.util.HashMap;
import java.util.Map;

public interface RoundRobin {
    Map<String, Integer> process = new HashMap<>();
    Map<String, Integer> waitTime = new HashMap<>();
    Map<String, Integer> turnAroundTime = new HashMap<>();
    Map<String, Integer> averageWaitingTime = new HashMap<>();
}
