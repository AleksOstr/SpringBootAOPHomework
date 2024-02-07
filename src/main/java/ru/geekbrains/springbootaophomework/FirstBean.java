package ru.geekbrains.springbootaophomework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@Timer
@Slf4j
public class FirstBean {
    public void firstMethod() {
        List<Integer> nums = IntStream.range(0, 10000).boxed().toList();
        log.info(nums.toString());
    }

}
