package ir.digixo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyItemProcessor implements ItemProcessor<Integer, Long> {

    @Override
    public Long process(Integer item) throws Exception {

        System.out.println("Inside item processor, Item before : " + item + " after : " + Long.valueOf(item + 10));
        return Long.valueOf(item + 10);
    }
}
