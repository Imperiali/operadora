# Assessment de Fundamentos Java

### Prof. LP Maia

> Descrição

_Faça um programa que leia um arquivo texto de clientes de uma operadora de celular com o
seguintes campos: número do celular, nome do cliente, tipo de plano (pré-pago ou pós-pago) e
números de créditos. Depois o programa deve exibir um menu com as seguintes opções:
inclusão de cliente, alteração de cliente, exclusão de cliente, relatórios gerenciais e saída do
programa._

- No caso de inclusão, o programa deverá evitar que seja criada um cliente com um
número de celular que já exista, caso contrário deverá enviar uma mensagem de erro. O
número do celular deve ter oito números, o plano deve ser do tipo 1 (pré-pago) ou 2
(pós-pago) e o número de créditos deve ser maior ou igual a zero.

- No caso da alteração, apenas o nome do cliente, tipo de plano e o número de créditos
podem ser alterados.

- Nos casos de alteração e exclusão, o programa deverá garantir que o cliente exista,
caso contrário deverá enviar uma mensagem de erro.

- No caso da opção de relatórios gerenciais, serão oferecidas as seguintes opções: listar
todos os clientes, clientes com número de créditos igual ou menor a zero, listar os
clientes que tem crédito acima de um determinado valor, listar a conta com o maior
número de crédito. Além disso, o programa deverá ter uma opção de gerar boletos para
clientes pós-pago a partir de um outro arquivo de ligações que contém o número do
celular, a hora de início da ligação e de término da ligação. No caso de clientes
pré-pago o programa deverá abater do número de créditos a partir do tempo da ligação.
Um minuto ou menos de ligação equivale a R$ 1,00.

- Ao término, o programa deverá gravar os dados atualizados no arquivo em disco.
O programa deve implementar classe(s), métodos estáticos, gets/sets, variáveis locais,
passagem de parâmetros, vetores e todas as boas práticas apresentadas durante o curso.
 
