package exemplos;

import java.util.List;

public class Decomposicao {

	public static void main(String[] args) {

		/// SEM PARALELO STREAM
		System.out.println(">>> SEM PARALELO STREAM");
		long inicio = System.currentTimeMillis();//
		List.of(1, 2, 3, 4, 5)//
				.stream()//
				.map(x -> trabalhar(x))//
				.forEach(result -> System.out.print(result + " "));
		System.out.println();
		var time = (System.currentTimeMillis() - inicio) / 1000;
		System.out.println("Time: " + time + " segundos");

		System.out.println();

		/// COM PARALELO STREAM
		System.out.println(">>> COM PARALELO STREAM");
		long inicio2 = System.currentTimeMillis();//
		List.of(4, 2, 5, 1, 3)//
				.parallelStream()//
				.map(x -> trabalhar(x))//
				.forEachOrdered(result -> System.out.print(result + " "));
				//.forEach(result -> System.out.print(result + " "));
		System.out.println();
		var time2 = (System.currentTimeMillis() - inicio2) / 1000;
		System.out.println("Time: " + time2 + " segundos");
	}

	private static int trabalhar(int input) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		return input;
	}

}