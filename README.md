# Aeroporto Painel Informativo
Este projeto implementa um painel informativo para um aeroporto, **utilizando apenas estruturas de dados**, permitindo o cadastro de voos, mudanças de status, controle de pousos e aterrissagens. Um recurso crucial é a utilização de uma fila de prioridade para organizar os voos com base no horário, garantindo uma exibição ordenada no painel.

## Funcionalidades
1. Cadastro de Voos: O sistema permite o registro de informações de voos, incluindo número do voo, horário, status e portão de embarque.

2. Controle de Status: Os status dos voos podem ser facilmente alterados, refletindo mudanças em tempo real, como atrasos, cancelamentos ou embarques.

3. Controle de Pousos e Aterrissagens: O painel acompanha os eventos de pousos e aterrissagens, mantendo os passageiros e a equipe atualizados.

4. Fila de Prioridade Dinâmica: Utiliza alocação dinâmica para organizar os voos em uma fila de prioridade com base no horário. Isso garante que os voos sejam exibidos ordenadamente no painel.

5. Busca por Número do Voo: Fornece a capacidade de realizar buscas eficientes usando o número do voo, permitindo a retirada e alteração de status e portão de embarque.

6. Atualização Automática de Status: Implementa uma funcionalidade que, automaticamente, altera o status dos voos que estão atrasados para "Atrasado". Voos cancelados ou já decolados permanecem na lista até o horário designado.

# Estrutura de Dados
- Lista simplesmente encadeada
- Árvore Binária

