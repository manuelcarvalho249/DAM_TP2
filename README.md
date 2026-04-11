# Trabalho 2 - Tutorial 2: Exercícios de Kotlin Avançado

Unidade Curricular: Desenvolvimento de Aplicações Móveis (DAM)

Aluno: Manuel Carvalho A51586 

Data: 11/04/2026

---

## 1. Introdução

Este projeto contém as aplicações de consola puramente em Kotlin desenvolvidas para a Secção 1 do Tutorial 2. Este trabalho aprofunda o conhecimento na linguagem Kotlin, focando-se em conceitos avançados que promovem a escrita de código mais seguro, expressivo e funcional. O objetivo é estabelecer uma base sólida nestes conceitos antes de avançar para a construção da aplicação Android (Cool Weather App).

---

## 2. Visão Geral do Sistema

A solução é composta por quatro guiões distintos baseados na consola, cada um abordando funcionalidades e paradigmas específicos do Kotlin:

**Exercício 1 (Processamento de Eventos):** Um sistema de modelação e processamento de logs de eventos. Utiliza *sealed classes*, funções de extensão e funções de ordem superior para filtrar e processar ações de utilizadores (logins, compras, logouts).

**Exercício 2 (Cache em Memória):** Implementação de uma cache genérica e *type-safe* (`Cache<K, V>`). Permite o mapeamento de chaves para valores de qualquer tipo, gerindo a inserção, transformação e captura do estado (snapshot) da memória de forma segura.

**Exercício 3 (Pipeline de Dados):** Um pipeline de processamento de dados altamente configurável. Processa listas de strings através de uma sequência de etapas de transformação (filtros, formatação, índices) criadas a partir de funções de ordem superior e lambdas.

**Exercício 4 (Biblioteca de Vetores 2D):** Uma classe matemática `Vec2` que modela vetores bidimensionais. Recorre ao *operator overloading* (sobrecarga de operadores) para permitir que as instâncias se comportem como tipos numéricos de primeira classe em operações algébricas.

---

## 3. Arquitetura e Design

O projeto está organizado em pacotes modulares (`dam.exer_1`, `dam.exer_2`, etc.) para separar cada exercício de forma concetual. O design de cada módulo explora paradigmas modernos:

* **Sealed Classes (Ex 1):** O uso da `sealed class Event` garante uma hierarquia de tipos fechada, permitindo que as expressões `when` sejam exaustivas no processamento dos eventos (sem necessidade de um ramo `else`).
* **Generics (Ex 2):** A utilização de tipos parametrizados `<K : Any, V : Any>` impõe um limite (*upper bound*) que proíbe tipos nulos, garantindo *type-safety* em tempo de compilação.
* **Programação Funcional (Ex 3):** Tratamento de funções como "cidadãos de primeira classe". O armazenamento de transformações do tipo `(List<String>) -> List<String>` permite criar fluxos de execução flexíveis e encadeados.
* **Operator Overloading (Ex 4):** A abstração matemática é reforçada através da redefinição de operadores nativos (como `+`, `-`, `*`, `[]`, `<` e `>`), simplificando a leitura e a escrita de cálculos vetoriais.

---

## 4. Implementação

Os principais detalhes de implementação ao longo dos módulos incluem:

* **Exercício 1:** * Uso de `filterIsInstance<Event.Purchase>()` acoplado com `sumOf` para extrair de forma declarativa o total gasto por um utilizador.
* **Exercício 2:** * A classe encapsula um `MutableMap` internamente, mas protege os seus dados devolvendo uma cópia de leitura nativa através do método `.toMap()` no momento do `snapshot`.
    * Implementação fluente do `getOrPut` e do `transform` recorrendo a *lambdas*.
* **Exercício 3:** * Gere uma lista mutável de pares (Nome da Etapa e Função de Transformação).
    * Implementação dos desafios: `compose` (para fundir duas etapas) e `fork` (para dividir e testar a saída de dois pipelines independentes).
* **Exercício 4:** * Implementa a interface `Comparable<Vec2>` comparando magnitudes (comprimento Euclidiano).
    * Resolução do desafio da multiplicação escalar à esquerda (`Double * Vec2`) estendendo nativamente a classe `Double` com uma *extension function* operator `times`.

---

## 5. Testes e Validação

Os testes foram realizados manualmente através da execução na consola, usando os dados de amostra fornecidos no tutorial para validar a exatidão das lógicas construídas:

* **Processamento de Eventos:** Validação rigorosa dos totais gastos agrupados por utilizador, assegurando que eventos de *Login* e *Logout* são ignorados nas somas matemáticas.
* **Cache em Memória:** Testado com duas combinações de tipos distintas (`Cache<String, Int>` e `Cache<Int, String>`). Comportamento verificado quer em cenários onde a chave já existe, quer quando requer computação *lazy* pelo lambda de predefinição.
* **Pipeline de Dados:** Tratamento e limpeza correta de um lote de logs repletos de espaços e casos mistos, aplicando *Trim*, filtro de erros ("ERROR") e maiúsculas de forma estritamente sequencial.
* **Vetores 2D:** Operações matemáticas testadas com sucesso. Casos críticos foram acautelados, lançando-se `IllegalStateException` ao tentar normalizar um vetor zero e `IndexOutOfBoundsException` perante a invocação de coordenadas fora dos eixos X(0) e Y(1).

---

## 6. Instruções de Utilização

* **Requisitos:** Certifique-se de que tem a versão mais recente do IntelliJ IDEA e o plugin oficial da linguagem Kotlin instalados.
* **Configuração:** Clone o repositório e abra a raiz do projeto no IntelliJ IDEA.
* **Execução:**
    * Navegue até aos guiões `exer_1.kt`, `exer_2.kt`, `exer_3.kt` ou `exer_4.kt` localizados dentro do pacote `dam`.
    * Clique no ícone verde **"Play"** situado junto a cada declaração `fun main()` dentro do editor do IDE.
    * Os resultados de cada teste serão impressos na consola de Execução/Depuração (**Run/Debug**) na parte inferior do IDE.