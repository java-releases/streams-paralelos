![](https://github.com/ocpjp-study/streams-paralelos/blob/main/ocpjp.png)

[Preparação para o Exame 1Z0-817](https://education.oracle.com/pt_BR/upgrade-ocp-java-6-7-8-to-java-se-11-developer/pexam_1Z0-817)

### Tópico: Stream Paralelos
### Objetivos
- Desenvolver código que use stream paralelo;
- Implementar decomposição e redução com stream;

### Decomposição
- É o processo de pegar uma tarefa, dividi-la em partes menores que podem ser executadas simultaneamente, e em seguida, remontar os resultados.
- Quando usamos um `.forEachOrdered()`, isso força que a iteração seja ordenada conforme o input de dados inicial.

### Redução
- Os resultados de redução paralela, podem ser diferentes dos resultados com redução serial stream.
- Para performar um reuction com collector:
  - O stream deve ser em paralelo;
  - O parâmetro da operação de collect() deve ter um Characteristics.CONCURRENT;
  - O Stream não deverá ser ordenado, ou colector deverá ter características unordered;

## Performance com ordenação
- Qualquer operação que é baseada na ordem, incluindo: findFirst(), limit(), ou skip(), podem performar mais lentamente em stream paralelo.

### Regras
- Ao criar um stream paralelo a partir de outro stream, e aplicar um `terminal operation`, isso torna o stream original indisponível para o uso. Caso tente utilizar o stream original, então irá gerar uma java.lang.IllegalStateException: stream has already been operated upon or closed; 
