package ru.geekbrains.springbootaophomework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@Slf4j
public class SecondBean {

    @Timer
    public void secondMethod() {
        List<Integer> nums = IntStream.range(0, 10000).boxed().toList();
        log.info(nums.toString());
    }

}
