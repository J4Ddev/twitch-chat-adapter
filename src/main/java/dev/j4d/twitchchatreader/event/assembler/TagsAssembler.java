package dev.j4d.twitchchatreader.event.assembler;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TagsAssembler {

    public Map<String, String> assemble(String tags) {
        return Arrays.stream(tags.substring(1).split(";"))
                .map(tag -> tag.split("="))
                .filter(keyValue -> keyValue.length > 1)
                .collect(Collectors.toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
    }
}
