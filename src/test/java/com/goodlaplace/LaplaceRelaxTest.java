package com.goodlaplace;

import org.junit.Test;

import java.util.Scanner;

public class LaplaceRelaxTest {

    @Test
    public void highAndLowCorners() {
        int dim = 20;
        LaplaceRelax sim = new LaplaceRelax(dim,600/dim,100,0,0,100);
        double[][] endingWorld = sim.simulate(600);
        new Scanner(System.in).next();
    }

    @Test
    public void oneHighCorner() {
        int dim = 20;
        LaplaceRelax sim = new LaplaceRelax(dim,600/dim,0,100,0,0);
        double[][] endingWorld = sim.simulate(600);
        new Scanner(System.in).next();
    }

    @Test
    public void threeHighCorners() {
        int dim = 20;
        LaplaceRelax sim = new LaplaceRelax(dim,600/dim,100,100,100,0);
        double[][] endingWorld = sim.simulate(600);
        new Scanner(System.in).next();
    }
}