package com.company.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

  private static final String FILE = ExecuteAround.class.getResource("./data.txt").getFile();

  public static void main(String... args) throws IOException {
    // 더 유연하게 리팩토링할 메서드
    String result = processFileLimited();
    System.out.println(result);

    System.out.println("---");

    String oneLine = processFile((BufferedReader b) -> b.readLine());
    System.out.println(oneLine);

    //한번에 두행을 읽도록
    String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
    System.out.println(twoLines);
  }

  public static String processFileLimited() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return br.readLine();
    }
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    //try-with-resources 구문 사용 - 자원을 명시적으로 닫을 필요가 없음
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return p.process(br);
    }
  }

  //함수형 인터페이스 정의
  public interface BufferedReaderProcessor {

    String process(BufferedReader b) throws IOException;

  }

}
