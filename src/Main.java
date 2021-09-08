import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		// parallel(); - Criando um stream paralelo, a partir de um outro stream.
		Stream<Integer> s1 = List.of(1, 2).stream();
		Stream<Integer> s2 = s1.parallel();
		System.out.println(s2.findAny());

		// parallelStream(); - Criando um stream paralelo, a partir de uma collection.
		Stream<Integer> s3 = List.of(1, 2, 3, 4, 5).parallelStream();
		System.out.println(s3);
	}

}