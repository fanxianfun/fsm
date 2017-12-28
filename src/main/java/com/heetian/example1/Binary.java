package com.heetian.example1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Scanner;

/**
 * 从输入的二进制数中判断这个数是否为偶数的程序，如果是偶数就变更状态，如果不是偶数不改变状态。
 *
 *  s1 s2
 *  是偶数 不是偶数
 */
public class Binary {

    private static final Logger logger = LoggerFactory.getLogger(Binary.class);

    private Binary(String currentState) {
        this.currentState = currentState;
    }

    private String currentState;

    private void fire(String event) {

        switch (event) {
            case "1":
                break;
            case "0":
                if(Objects.equals(this.currentState,"S2")){
                    this.currentState = "S1";
                    logger.info("from [{}] to [{}] on [{}]","S2","S1","0");
                }else {
                    this.currentState = "S2";
                    logger.info("from [{}] to [{}] on [{}]","S1","S2","0");
                }
                break;
            default:
                logger.error("Invalid input.");
                break;
        }
    }


    private String getCurrentState() {
        return currentState;
    }

    public static void main(String[] args) {

        Binary binary = new Binary("S1");

        logger.info("Application is heartbeat! init state ==>> {}",binary.getCurrentState());
        String line;
        Scanner sc = new Scanner(System.in);

        while ((line = sc.nextLine()) != null){
            if(line.length() == 0){
                continue;
            }

            String event = line.substring(line.length() - 1, line.length());
            binary.fire(event);
            logger.debug("current state ==>> {} ",binary.getCurrentState());

        }

    }
}
