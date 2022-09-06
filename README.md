# BarreiraSimples
Sistema de barreira simples usando Threads no java

#### ENUNCIADO:

Implemente um sistema composto por 5 threads (numeradas de 0 a 4) para a geração
da folha de pagamento de uma empresa, da seguinte forma:
Thread 0:

1. Cria uma lista de n funcionários, onde n é um múltiplo de 4, de forma que, para
cada funcionário, conste:

  - código (inteiro): definido na criação do funcionário (valor único)
  - salário bruto (double): definido na criação do funcionário (valor
aleatório entre 1000 e 5000)
  - desconto de imposto de renda: calculado pela Thread 1
  - desconto de INSS: calculado pela Thread 2
  - desconto de previdência privada (double): calculado pela Thread 3
  - desconto de plano de saúde (double): calculado pela Thread 4
  - total de descontos (double): atualizado quando cada desconto é
  calculado (pelas threads 1, 2, 3 e 4)
  - salário líquido (double): diferença entre o salário bruto e o total dos
  descontos; valor atualizado toda vez que o total de descontos é
  atualizado.
  
2. Divide a lista em quatro partes de igual tamanho, denominadas parte 1, parte 2,
parte 3 e parte 4.

3. Cria e inicia as threads 1 a 4, informando-as sobre o início e o fim de cada parte
da lista.

Thread 1:
A. Calcula o imposto de renda retido na fonte para cada funcionário, atualizando
o seu total de descontos e o seu salário líquido. O valor de imposto de renda é
calculado como 20% do salário bruto. Executa quatro passos em sequência, um
para cada parte da lista, iniciando pela parte 1 e depois as partes 2, 3 e 4.
B. Espera as 4 threads concluírem a fase A (rendezvous)
C. Imprime os contra-cheques dos funcionários da parte 1 da lista, gerando um
arquivo chamado parte1.txt


Thread 2:
A. Calcula o valor de previdência obrigatória (INSS) para cada funcionário,
atualizando o seu total de descontos e o seu salário líquido. O valor de INSS é
calculado como 8% do salário bruto. Executa quatro passos em sequência, um
para cada parte da lista, iniciando pela parte 2, depois as partes 3, 4 e 1.
B. Espera as 4 threads concluírem a fase A (rendezvous)
C. Imprime os contra-cheques dos funcionários da parte 2 da lista, gerando um
arquivo chamado parte2.txt


Thread 3:
A. Calcula a previdência privada para cada funcionário, atualizando o seu total de
descontos e o seu salário líquido. O valor de previdência privada é calculado
como 4% do salário bruto. Executa quatro passos em sequência, um para cada
parte da lista, iniciando pela parte 3, depois as partes 4, 1 e 2.
B. Espera as 4 threads concluírem a fase A (rendezvous)
C. Imprime os contra-cheques dos funcionários da parte 3 da lista, gerando um
arquivo chamado parte3.txt


Thread 4:
A. Calcula o valor de plano de saúde para cada funcionário e atualizando o seu
total de descontos e o seu salário líquido. O valor do plano de saúde é
calculado como 2% do salário bruto. Executa quatro passos em sequência, um
para cada parte da lista, iniciando pela parte 4, depois as partes 1, 2 e 3.
B. Espera as 4 threads concluírem a fase A (rendezvous)
C. Imprime os contra-cheques dos funcionários da parte 4 da lista, gerando um
arquivo chamado parte4.txt


Importante: Cada parte da lista é manipulada com exclusão mútua entre as threads.
Essa propriedade pode ser implementanda usando-se o Padrão Mutex ou o Padrão
Sinalizaçã
