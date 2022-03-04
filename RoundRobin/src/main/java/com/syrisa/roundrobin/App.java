package com.syrisa.roundrobin;

import com.syrisa.roundrobin.abstrct.RoundRobin;
import com.syrisa.roundrobin.concrete.RoundRobinImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RoundRobinImpl roundRobin = new RoundRobinImpl();
        roundRobin.writeProcess(RoundRobin.process);
        roundRobin.roundRobin();
    }
}
