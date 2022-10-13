package com.qks.jdkcracter.jdk12;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-20 14:18
 */
public class switchdemo {
    public static void main(String[] args) {
        String day = "M";
        System.out.println(switch (day) {
            case "M", "W", "F" -> "MWF";
            case "T", "TH", "S" -> "TTS";
            default -> {
                if(day.isEmpty()) {
                    // yield 关键字用于终止switch并且返回一个值
                    yield "Please insert a valid day.";
                } else {
                    yield "Looks like a Sunday.";
                }
            }
        });
    }
}
