package ir.digixo.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MyItemReader implements ItemReader<Integer> {

    int i = 0;
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        Integer item;
        if (i < list.size()) {
            item = list.get(i++);
            System.out.println("Inside item reader, item: " + item);
            return item;
        }
        i = 0;  //reset index
        return null;
    }
}
