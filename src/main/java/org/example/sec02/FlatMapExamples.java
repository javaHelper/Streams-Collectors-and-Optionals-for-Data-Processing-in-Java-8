package org.example.sec02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FlatMapExamples {
    public static void main(String[] args) throws IOException {
        // http://introcs.cs.princeton.edu/java/data/TomSawyer.txt

        Stream<String> stream1 = Files.lines(Paths.get("src/main/resources/files.sec02/TomSawyer_01.txt")) ;
        Stream<String> stream2 = Files.lines(Paths.get("src/main/resources/files.sec02/TomSawyer_02.txt")) ;
        Stream<String> stream3 = Files.lines(Paths.get("src/main/resources/files.sec02/TomSawyer_03.txt")) ;
        Stream<String> stream4 = Files.lines(Paths.get("src/main/resources/files.sec02/TomSawyer_04.txt")) ;

        Stream<Stream<String>> streamOfStreams = Stream.of(stream1, stream2, stream3, stream4);

        Stream<String> streamOfLines = streamOfStreams.flatMap(Function.identity());

        Stream<String> streamOfWords = streamOfLines
                .flatMap(line -> Pattern.compile(" ").splitAsStream(line))
                .map(String::toLowerCase)
                .filter(word -> word.length() == 4)
                .distinct();

        System.out.println("# words :" + streamOfWords.count());
    }
}