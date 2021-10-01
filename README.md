![](https://github.com/ocpjp-study/streams-paralelos/blob/main/paralelo.png)

### Tópico: Stream Paralelos
### Objetivos
- Desenvolver código que use stream paralelo;
- Implementar decomposição e redução com stream;

<hr>

### Resumo
- Ao criar um stream paralelo a partir de outro stream, e aplicar um `terminal operation`, isso torna o stream original indisponível para o uso. Caso tente utilizar o stream original, então irá gerar uma java.lang.IllegalStateException: stream has already been operated upon or closed;
- Para criar um stream paralelo, podemos chamar o método `parallel()`, a partir de uma instância de Stream<T>, ou chamar o método `parallelStream()` a partir de alguma instância de alguma Collection.
- O método `findFirst()`, garante que o 1º elemento do Stream será chamado, mesmo sendo serial ou paralelo.
<br/><br/>

> #### Decomposição
- É o processo de pegar uma tarefa, dividi-la em partes menores que podem ser executadas simultaneamente, e em seguida, remontar os resultados.
- Quando usamos um `.forEachOrdered()`, isso força que a iteração seja ordenada conforme o input de dados inicial.
<br/><br/>

> #### Redução
- Os resultados de redução paralela, podem ser diferentes dos resultados com redução serial stream.
- Para performar um reduction com collector:
  - O stream deve ser em paralelo;
  - O parâmetro da operação de collect() deve ter um Characteristics.CONCURRENT;
  - O Stream não deverá ser ordenado, ou colector deverá ter características unordered;
<br/><br/>

> #### Performance com ordenação
- Qualquer operação que é baseada na ordem, incluindo: findFirst(), limit(), ou skip(), podem performar mais lentamente em stream paralelo.
