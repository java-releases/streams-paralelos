package exemplos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
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

		System.out.println();

		// PARALELO
		Stream<String> musculos = Stream.of("perna", "biceps", "panturrilha", "abdomem").parallel();
		SortedSet<String> set = musculos.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
		System.out.println(set);

		System.out.println();

		// PARALELO
		Stream<String> bichos = Stream.of("leao", "tamandua", "gorila", "gato", "bem-te-vi").parallel();
		ConcurrentMap<Integer, String> concurrentMap = bichos.collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
		System.out.println(concurrentMap);
		System.out.println(concurrentMap.getClass());

		System.out.println();

		// PARALELO
		Stream<String> bichos2 = Stream.of("leao", "tamandua", "gorila", "gato", "bem-te-vi").parallel();
		ConcurrentMap<Integer, List<String>> concurrentMap2 = bichos2.collect(Collectors.groupingByConcurrent(String::length));
		System.out.println(bichos.isParallel());
		System.out.println(concurrentMap2);
		System.out.println(concurrentMap2.size());
		System.out.println(concurrentMap2.getClass());

		//
		List<Integer> sorteio = List.of(1,2,3,4,5,6,7,8,9,10);
		var dados = Collections.synchronizedList(new ArrayList<Integer>());
		sorteio.stream()//
			.filter(s -> s % 2 == 0)//
			.forEach(i -> dados.add(i));//
		System.out.println("dados: " + dados);
	}

}