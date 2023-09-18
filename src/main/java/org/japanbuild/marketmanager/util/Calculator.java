package org.japanbuild.marketmanager.util;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Calculator {
    
    public long calc(long stock, long normal, double factor, long maxStock) {

        //double reference SUM
        AtomicReference<Double> sum = new AtomicReference<>(0D);

        //sum calc
        double t = normal / 100;
        sum.set(t * 100);

        //need calc
        if(stock < (maxStock / 3)) {
            //need cost
            long f = Math.round(1 + ((factor / 200) * need));
            sum.set(sum.get()*f);
        }
        double max = sum.get()*3;
        double min = sum.get()/3;
        if(sum.get() > max) {
            while (sum.get() > max) {
                sum.set(sum.get() - 0.01);
            }
        }
        if(sum.get() < min) {
            while (sum.get() < min) {
                sum.set(sum.get() + 0.01);
            }
        }
        return Math.round(Math.ceil(swing(sum.get())));
    }

    private int need = 1;
    private int now = 0;
    private double price = 0;
    private boolean up = true;
    private int last = 5;

    private double swing(double price) {
        Random random = new Random();
        double tmp;
        if(this.price == 0) {
            this.price = price;
            up = true;
            tmp = this.price;
        } else {
            if(up) {
                if(now == need) {
                    now = 0;
                    need = last + random.nextInt(3);
                    up = false;
                }
                tmp = this.price * Double.parseDouble("1." + random.nextInt(2) + random.nextInt(3) + random.nextInt(2));
            } else {
                if(now == need) {
                    now = 0;
                    need = last + random.nextInt(3);
                    up = true;
                }
                tmp = this.price / Double.parseDouble("1." + random.nextInt(2) + random.nextInt(3) + random.nextInt(2));
            }
            now = now + 1;
        }
        this.price = tmp;
        return tmp;
    }
}
