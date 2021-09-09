package exemplos;

import java.util.List;
import java.util.stream.Stream;

public class Reducao {

	public static void main(String[] args) {

		// SEM STREAM PARALELO
		List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6);
		Integer numero = numeros.stream()//
				.findAny()//
				.get();//
		System.out.println(numero);

		// COM STREAM PARALELO
		List<Integer> numeros2 = List.of(0, 1, 2, 3, 9, 4, 5, 6);
		Integer numero2 = numeros2.parallelStream()//
				.findAny()//
				.get();//
		System.out.println(numero2);

		// STREAM NAO ORDENADO - NAO PARALELO
		Stream<Integer> numeros3 = List.of(0, 1, 2, 3, 9, 4, 5, 6).stream().unordered();
		System.out.println(numeros3.findAny());

		// STREAM NAO ORDENADO - PARALELO
		Stream<Integer> numeros4 = List.of(0, 1, 2, 3, 9, 4, 5, 6).parallelStream().unordered();
		System.out.println(numeros4.findAny());

		// REDUCE
		List<Character> letras = List.of('l', 'o', 'b', 'o');//
		String palavra = letras.parallelStream().reduce("", (s1, c) -> s1 + c, (s2, s3) -> s2 + s3);//
		System.out.println("palavra: " + palavra);//

		System.out.println();
		
		// REDUCE SEM PARALELO
		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);//
		Integer n = numbers.stream().reduce(0, (a, b) -> a - b);// -21
		System.out.println("numero: " + n + " com stream()");//

		// REDUCE EM PARALELO
		List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 6);//
		Integer n2 = numbers2.parallelStream().reduce(0, (a, b) -> a - b);//
		System.out.println("numero: " + n2 + " com parallelStream()");// -1, -2, -3, -4, -5, OU -6

		System.out.println();

		// REDUCE SEM PARALELO
		List<String> animais = List.of("G", "A", "T", "O");//
		String animal = animais.stream().reduce("X", String::concat);//
		System.out.println("animal: " + animal + " com stream();");//

		// REDUCE EM PARALELO
		List<String> animais2 = List.of("G", "A", "T", "O");//
		String animal2 = animais2.parallelStream().reduce("X", String::concat);//
		System.out.println("animal: " + animal2 + " com parallelStream();");//

	}

}